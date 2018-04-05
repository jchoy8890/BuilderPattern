package com.belatrix.logger.builder;

import com.belatrix.logger.utils.Constants;

/**
 * 
 * @author jchoy
 * 
 */
public class Deploy {

	/**
	 * * In a fast review into the code, i can see too much code smell because the
	 * code have code block with repeated code, this makes the heavy and reduces the
	 * app performance. The snippet code has methods with too many parameters. This
	 * code needs to be refactored urgently.
	 * 
	 * the snippet code have too many responsabilities like manage the database
	 * connection, read file properties, etc. One principle of SOLID is a one class
	 * with unique responsability. I isolated the responsabilities into many
	 * classes.
	 * 
	 * Also i implemented the builder pattern for create dinamicly and faster
	 * objects with differentes behaivor and attributes. In this case, we need store
	 * logs into files or database, also shows in console. Each behavior maybe be a
	 * change depending on what we want to store. See the code for details.
	 * 
	 * Finally also implemented the singleton pattern for the database connection.
	 * The connection it will be create only once. Then it's not necessary to create
	 * it again, only get the connection created.
	 * 
	 * The refactor of code is very important for app performance and have the code
	 * ordered for a fast understanding. This is a difference with one developer and
	 * one good developer.
	 * 
	 * The code have unit tests with Junit follow the TDD best practices.
	 */

	public static void main(String[] args) {
		CustomLog customLog = new CustomLog
				.Builder("Info message for testing with database store")
				.setLevel(Constants.LEVEL_INFO)
				// Enabling log for Database
				.enableLogDB()
				.build();
		customLog.saveLog();

		CustomLog customLog2 = new CustomLog
				.Builder("Error or severe message for testing")
				.setLevel(Constants.LEVEL_ERROR)
				.build();
		customLog2.saveLog();

		// Testing the singleton database connection
		CustomLog customLog3 = new CustomLog
				.Builder("Info message for testing with database store 2")
				.setLevel(Constants.LEVEL_INFO)
				.enableLogDB()
				.build();
		customLog3.saveLog();

	}

}
