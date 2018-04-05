package com.belatrix.logger.builder;

import java.sql.SQLException;

import com.belatrix.logger.utils.Constants;

public class Deploy {

	public static void main(String[] args) throws SQLException {
		CustomLog customLog = new CustomLog
				.Builder("Info message for testing with database store")
				.setLevel(Constants.LEVEL_INFO)
				// Enabling log for Database
				.enableLogDB()
				.build();
		customLog.saveLog();
		
		CustomLog customLog2 = new CustomLog
				.Builder("Error or severe message for testing")
				.setLevel(Constants.LEVEL_ERROR)
				.build();
		customLog2.saveLog();

	
	}

}
