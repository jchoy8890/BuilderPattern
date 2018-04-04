package com.belatrix.logger.builder;

import com.belatrix.logger.utils.Constants;

public class Deploy {

	public static void main(String[] args) {
		CustomLog customLog = new CustomLog
				.Builder("Error Message")
				.setLevel(Constants.LEVEL_ERROR)
				.enableLogDB()
				.build();
		
		System.out.println(customLog);
		CustomLog customLog2 = new CustomLog.Builder("Info Message").build();
		System.out.println(customLog2);

		// customLog.saveLog();
		// customLog2.saveLog();
	}

}
