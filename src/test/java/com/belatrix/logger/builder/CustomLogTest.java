package com.belatrix.logger.builder;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CustomLogTest {

	static CustomLog customLog;

	@BeforeClass
	public static void init() {
		customLog = new CustomLog.Builder("Testing for junit").build();
	}

	@Ignore
	@Test
	public void failedValidateEnableDatabaseLog() {
		// fail("Not yet implemented");
		assertTrue(customLog.isLogDB()); // not Enable
		customLog.setLogDB(true);
		assertTrue(customLog.isLogDB());
	}

	@Test
	public void validateEnableDatabaseLog() {
		// fail("Not yet implemented");
		customLog.setLogDB(true);
		assertTrue(customLog.isLogDB());
	}

	@Ignore
	@Test
	public void failedValidateEnableFileLog() {
		customLog.setLogFile(false);
		assertTrue(customLog.isLogFile());
	}

	@Test
	public void validateEnableFileLog() {
		assertTrue(customLog.isLogDB());
	}

	@Ignore
	@Test
	public void failedValidateEnableConsoleLog() {
		customLog.setLogConsole(false);
		assertTrue(customLog.isLogConsole());
	}

	@Test
	public void validateEnableConsoleLog() {
		assertTrue(customLog.isLogConsole());
	}

}
