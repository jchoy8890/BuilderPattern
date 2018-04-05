package com.belatrix.logger.builder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.belatrix.datasource.connection.DataBaseConnection;
import com.belatrix.logger.utils.Constants;

/**
 * @author Jonathan Choy
 * 
 *         <pre>
 *Builder Pattern for handle system's logs with file,
 *console or database datasource
 * 
 *For default the file and console options are enable, but the database
 *option is disable because store logs into SGDB consumes more
 *resources than file and console options.
 * 
 *So, to enable the database log option, into the Builder pattern have
 *a enableLogDB method, which allows save log into some database
 *
 *Also, if we need disable the file or console log, the implementation
 *have two methods which disables them
 *         </pre>
 */
public class CustomLog {

	private boolean flagInitialize;
	private boolean logDB;
	private boolean logFile;
	private boolean logConsole;
	private int logLevel;
	private String message;
	private final Logger logger = Logger.getLogger(CustomLog.class.getName());
	private Level levelLogger;
	private FileHandler fileHandler;
	private StringBuilder query;

	private CustomLog() {

	}

	public static class Builder {

		public boolean flagInitialize;
		private boolean logDB = false;
		private boolean logFile = true;
		private boolean logConsole = true;
		private String message;
		private int logLevel;

		public Builder(String message) {
			this.message = message;
			this.flagInitialize = true;
		}

		public Builder setLevel(int logLevel) {
			this.logLevel = logLevel;
			return this;
		}

		public Builder enableLogDB() {
			this.logDB = true;
			return this;
		}

		public Builder disableFile() {
			this.logFile = false;
			return this;
		}

		public Builder disableConsole() {
			this.logConsole = false;
			return this;
		}

		public CustomLog build() {
			CustomLog customLog = new CustomLog();
			customLog.message = this.message;
			customLog.flagInitialize = this.flagInitialize;
			customLog.logConsole = this.logConsole;
			customLog.logDB = this.logDB;
			customLog.logFile = this.logFile;
			customLog.logLevel = this.logLevel;
			return customLog;
		}
	}

	public void saveLog() {
		levelLogger = getLevel();
		if (!flagInitialize)
			return;
		if (logConsole)
			printConsole();
		if (logFile)
			printFile();
		if (logDB)
			saveDB();
	}

	private void printConsole() {
		System.out.println(getTypeLog().concat(Constants.CONSOLE_SEPARATOR.concat(message)));
	}

	private void printFile() {
		try {
			fileHandler = new FileHandler(Constants.LOG_FILE);
			SimpleFormatter formater = new SimpleFormatter();
			fileHandler.setFormatter(formater);
			logger.addHandler(fileHandler);
			logger.log(levelLogger, message);
		} catch (SecurityException e) {
			message = e.getMessage();
			printConsole();
		} catch (IOException e) {
			message = e.getMessage();
			printConsole();
		}
		logger.log(Level.INFO, message);
	}

	private void saveDB() {
		Connection cn = DataBaseConnection.getBelatrixDBConnection();
		StringBuilder messageDetail = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
		Date dt = new Date();
		messageDetail.append(levelLogger.toString());
		messageDetail.append(Constants.CONSOLE_SEPARATOR);
		messageDetail.append(sdf.format(dt));
		messageDetail.append(Constants.CONSOLE_SEPARATOR);
		messageDetail.append(message);
		query = new StringBuilder();
		query.append(Constants.QUERY_LOG_DB);
		try {
			PreparedStatement pst = cn.prepareStatement(query.toString());
			pst.setString(1, message);
			pst.setString(2, messageDetail.toString());
			pst.executeUpdate();
		} catch (SQLException e) {
			message = e.getMessage();
			printConsole();
			printFile();
		}
	}

	private Level getLevel() {
		if (logLevel == Constants.LEVEL_ERROR)
			return Level.SEVERE;
		if (logLevel == Constants.LEVEL_DEBUG)
			return Level.FINE;
		if (logLevel == Constants.LEVEL_WARN)
			return Level.WARNING;
		return Level.INFO;
	}
	
	
	private String getTypeLog() {
		switch(logLevel) {
		case Constants.LEVEL_ERROR: return "Error";
		case Constants.LEVEL_WARN:  return "Warning";
		case Constants.LEVEL_DEBUG: return "Debug";
		default: return "Info";
		}
	}


	public boolean isFlagInitialize() {
		return flagInitialize;
	}

	public void setFlagInitialize(boolean flagInitialize) {
		this.flagInitialize = flagInitialize;
	}

	public boolean isLogDB() {
		return logDB;
	}

	public void setLogDB(boolean logDB) {
		this.logDB = logDB;
	}

	public boolean isLogFile() {
		return logFile;
	}

	public void setLogFile(boolean logFile) {
		this.logFile = logFile;
	}

	public boolean isLogConsole() {
		return logConsole;
	}

	public void setLogConsole(boolean logConsole) {
		this.logConsole = logConsole;
	}

	public int getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(int logLevel) {
		this.logLevel = logLevel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Logger getLogger() {
		return logger;
	}

	@Override
	public String toString() {
		return "CustomLog [flagInitialize=" + flagInitialize + ", logDB=" + logDB + ", logFile=" + logFile
				+ ", logConsole=" + logConsole + ", logLevel=" + logLevel + ", message=" + message + ", logger="
				+ logger + "]";
	}

}
