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
		System.out.println(props);
		assertNull(props);
	}

	@Test
	public void successLoadProperties() {
		assertNotNull(props);
	}

	@Test
	public void readAttributesProperties() {
		if (props.getProperty("database.connection.url") == null)
			fail("Attribute not found!");
		if (props.getProperty("database.connection.user") == null)
			fail("Attribute not found!");
		if (props.getProperty("database.connection.password") == null)
			fail("Attribute not found!");
		assertTrue(true);
	}

}
