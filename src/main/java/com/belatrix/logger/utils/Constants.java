package com.belatrix.logger.utils;

public class Constants {

	public static final int LEVEL_INFO = 1;
	public static final int LEVEL_DEBUG = 2;
	public static final int LEVEL_WARN = 3;
	public static final int LEVEL_ERROR = 4;

	public static final String PROP_DB_USER = "database.connection.user";
	public static final String PROP_DB_PWD = "database.connection.password";
	public static final String PROP_DB_URL = "database.connection.url";
	public static final String PROP_DB_CLASS = "com.mysql.jdbc.Driver";
	
	// Change the path file
	public static final String LOG_FILE = "/home/jchoy/developer/customLog.log";
	
	public static final String CONSOLE_SEPARATOR = " - ";
	
	public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
	
	public static final String QUERY_LOG_DB = "INSERT INTO Log_Values (message, messageDetail) values (?,?)";
	
	public static final String FILE_DB_PROPERTIES = "database.properties";
	
	public static final String SCHEMA_DB = "belatrix";
	
	public static final String OTHER_USER_DB = "otherUser";
	public static final String OTHER_SCHEMA_DB = "otherSchema";

}
