package com.belatrix.datasource.connection;

import java.util.Properties;

import com.belatrix.logger.utils.PropertiesUtil;

public class DataBaseConnection {

	Properties props;

	public DataBaseConnection() {
		props = PropertiesUtil.loadProperties();
	}

}
