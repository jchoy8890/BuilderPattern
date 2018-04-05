package com.belatrix.logger.properties;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.belatrix.logger.utils.PropertiesUtil;

public class PropertiesTest {

	static Properties props;

	@BeforeClass
	public static void loadProps() {
		props = PropertiesUtil.loadProperties();
	}

	@Ignore
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void failedLoadProperties() {
		assertNull(props);
	}

	@Test
	public void successLoadProperties() {
		assertNotNull(props);
	}

	@Test
	public void readAttributesProperties() {
		String url, user, password;
		url = props.getProperty("database.connection.url");
		user = props.getProperty("database.connection.user");
		password = props.getProperty("database.connection.password");
		if (url == null || url.equals(""))
			fail("Attribute not found!");
		if (user == null || user.equals(""))
			fail("Attribute not found!");
		if (password == null || password.equals(""))
			fail("Attribute not found!");
		assertTrue(true);
	}

}
