package com.belatrix.logger.builder;

import java.util.logging.Level;
import java.util.logging.Logger;

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
	private Logger logger;

	private CustomLog() {

	}

	public static class Builder {

		private boolean flagInitialize;
		private boolean logDB = false;
		private boolean logFile = true;
		private boolean logConsole = true;
		private String message;
		private int logLevel = Constants.LEVEL_INFO;

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
		Level level = getLevel();
		if (flagInitialize)
			return;
		if (logConsole)
			printConsole();
		if (logFile)
			printFile();
		if (logDB)
			System.out.println(message);
	}

	private void printConsole() {
		if (logLevel == Constants.LEVEL_ERROR)
			System.err.println("Error: " + message);
		else
			System.err.println("Info: " + message);
	}

	private void printFile() {
		logger.log(Level.INFO, message);
	}

	private Level getLevel() {
		Level level = Level.INFO;
		if (logLevel == Constants.LEVEL_ERROR)
			level = Level.SEVERE;
		if (logLevel == Constants.LEVEL_DEBUG)
			level = Level.FINE;
		if (logLevel == Constants.LEVEL_WARN)
			level = Level.WARNING;
		return level;
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

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	@Override
	public String toString() {
		return "CustomLog [flagInitialize=" + flagInitialize + ", logDB=" + logDB + ", logFile=" + logFile
				+ ", logConsole=" + logConsole + ", logLevel=" + logLevel + ", message=" + message + ", logger="
				+ logger + "]";
	}

}
