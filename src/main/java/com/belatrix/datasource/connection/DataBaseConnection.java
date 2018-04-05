package com.belatrix.datasource.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.belatrix.logger.builder.CustomLog;
import com.belatrix.logger.utils.Constants;
import com.belatrix.logger.utils.PropertiesUtil;

public class DataBaseConnection {

	static Properties props;
	private static String user;
	private static String pwd;
	private static String url;
	private static Connection cn;

	private static boolean readProperties() {
		props	= PropertiesUtil.loadProperties();
		if(props != null) {
			user	= props.getProperty(Constants.PROP_DB_USER);
			pwd		= props.getProperty(Constants.PROP_DB_PWD);
			url		= props.getProperty(Constants.PROP_DB_URL);
			return true;
		}
		return false;
	}

	public static Connection getBelatrixDBConnection() {
		try {
			if (cn == null) {
				if(readProperties()) {
					Class.forName(Constants.PROP_DB_CLASS);
					cn = DriverManager.getConnection(url, user, pwd);
					return cn;
				}
				new CustomLog
				.Builder("Connection not created because the parameters doesn't valid")
				.build()
				.saveLog();
			}
		} catch (Exception e) {
			new CustomLog
				.Builder(e.getMessage())
				.build()
				.saveLog();
		}
		return cn;
	}

	public static Connection getBelatrixDBConnectionWithOtherUser(String user) {
		Connection cn = null;
		try {
			Class.forName(Constants.PROP_DB_CLASS);
			cn = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			new CustomLog
				.Builder(e.getMessage())
				.build()
				.saveLog();
		}
		return cn;
	}

}
