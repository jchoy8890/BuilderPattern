package com.belatrix.logger.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.belatrix.logger.builder.CustomLogTest;
import com.belatrix.logger.connection.DBConnectionTest;
import com.belatrix.logger.properties.PropertiesTest;

@RunWith(Suite.class)
@SuiteClasses({ CustomLogTest.class, PropertiesTest.class, DBConnectionTest.class })
public class AllTests {

}
