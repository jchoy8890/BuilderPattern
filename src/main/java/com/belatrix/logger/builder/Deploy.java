package com.belatrix.logger.builder;

import java.sql.Connection;
import java.sql.SQLException;

import com.belatrix.datasource.connection.DataBaseConnection;
import com.belatrix.logger.utils.Constants;

public class Deploy {

	public static void main(String[] args) throws SQLException {
		CustomLog customLog = new CustomLog
				.Builder("Error Message")
				.setLevel(Constants.LEVEL_ERROR)
				.enableLogDB()
				.build();

		System.out.println(customLog);
		CustomLog customLog2 = new CustomLog.Builder("Info Message").build();
		System.out.println(customLog2);

		// System.out.println(props);
		// customLog.saveLog();
		// customLog2.saveLog();

		Connection cn = DataBaseConnection.getConnection();
		System.out.println(cn.getCatalog().toString());
	}

}
