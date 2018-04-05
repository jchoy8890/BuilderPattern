package com.belatrix.logger.connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.belatrix.datasource.connection.DataBaseConnection;
import com.belatrix.logger.utils.Constants;

public class DBConnectionTest {

	static Connection cn;

	@BeforeClass
	public static void loadConnection() {
		cn = DataBaseConnection.getBelatrixDBConnection();
	}

	@Ignore
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void failedConnection() {
		Connection con = DataBaseConnection.getBelatrixDBConnectionWithOtherUser(Constants.OTHER_USER_DB);
		assertNull(con);
	}

	@Test
	public void successConnection() {
		Connection con = cn;
		assertNotNull(con);
	}

	@Test
	public void validateSchema() throws SQLException {
		assertEquals(cn.getCatalog(), Constants.SCHEMA_DB);
	}

	@Ignore
	@Test
	public void failedValidateSchema() throws SQLException {
		assertEquals(cn.getCatalog(), Constants.OTHER_SCHEMA_DB);
	}

}
