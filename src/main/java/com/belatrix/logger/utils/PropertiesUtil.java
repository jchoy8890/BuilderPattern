package com.belatrix.logger.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.belatrix.logger.builder.CustomLog;

public class PropertiesUtil {

	public static Properties loadProperties() {
		Properties props = new Properties();
		try {
			String fileName = "database.properties";
			InputStream resourceStream = ClassLoader
					.getSystemResourceAsStream(fileName);
			props.load(resourceStream);
		} catch (IOException e) {
			new CustomLog.Builder(e.getMessage()).build().saveLog();
			return null;
		}
		return props;
	}

}
