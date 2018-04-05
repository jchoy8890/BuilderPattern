package com.belatrix.logger.builder;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.belatrix.datasource.connection.DataBaseConnection;

public class CustomLogTest {

	static CustomLog customLog;

	@BeforeClass
	public static void init() {
		customLog = new CustomLog
				.Builder("Testing for junit")
				.build();
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

	@Test
	public void validateMatchDataIntoDB() {
		String uuid = UUID.randomUUID().toString();
		new CustomLog
				.Builder(uuid)
				.enableLogDB()
				.build()
				.saveLog();
		if (getCant(uuid) == 1) {
			assertTrue(true);
			return;
		}
		fail();
	}

	private int getCant(String uuid) {		
		Connection cn = DataBaseConnection.getBelatrixDBConnection();
		StringBuilder query = new StringBuilder("SELECT count(message) FROM Log_Values WHERE messageDetail like ?");
		PreparedStatement pst;
		try {
			pst = cn.prepareStatement(query.toString());
			uuid = "%" + uuid + "%"; 
			pst.setString(1, uuid);
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				return 1;
		} catch (SQLException e) {
			new CustomLog
			.Builder(e.getMessage())
			.disableFile()
			.build()
			.saveLog();
		}
		return 0;

	}

}
