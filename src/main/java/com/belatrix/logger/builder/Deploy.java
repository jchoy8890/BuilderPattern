package com.belatrix.logger.builder;

import java.util.Properties;

import com.belatrix.logger.utils.Constants;
import com.belatrix.logger.utils.PropertiesUtil;

public class Deploy {

	public static void main(String[] args) {
		CustomLog customLog = new CustomLog
				.Builder("Error Message")
				.setLevel(Constants.LEVEL_ERROR)
				.enableLogDB()
				.build();

		//System.out.println(customLog);
		CustomLog customLog2 = new CustomLog.Builder("Info Message").build();
		//System.out.println(customLog2);

		Properties props = PropertiesUtil.loadProperties();
		//System.out.println(props);
		// customLog.saveLog();
		// customLog2.saveLog();
	}

}
