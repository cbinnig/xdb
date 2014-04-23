package org.xdb.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.statement.AbstractServerStmt;

/**
 * Implements an XDB test case 
 * 
 * @author cbinnig
 *
 */
public abstract class TestCase extends junit.framework.TestCase {

	/**
	 * Asserts if error occurred
	 * 
	 * @param error
	 */
	protected void assertNoError(Error error) {
		TestCase.assertEquals(Error.NO_ERROR, error);
	}

	/**
	 * Asserts if no error occurred
	 * 
	 * @param error
	 */
	protected void assertError(Error error) {
		TestCase.assertNotSame(Error.NO_ERROR, error);
	}

	/**
	 * Asserts result size
	 * 
	 * @param rs
	 * @param expectedCnt
	 * @throws Exception
	 */
	protected void assertResultSize(ResultSet rs, int expectedCnt)
			throws Exception {
		if (rs.next()) {
			int actualCnt = rs.getInt(1);
			TestCase.assertEquals(expectedCnt, actualCnt);
		} else {
			TestCase.assertTrue(false);
		}
	}

	/**
	 * Executes query on local node and returns result
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	protected ResultSet executeComputeQuery(String query) {
		return this.executeComputeQuery(Config.COMPUTE_DB_URL
				+ Config.COMPUTE_DB_NAME, query);
	}

	/**
	 * Executes query on a given node and returns result
	 * 
	 * @param dburl
	 * @param query
	 * @return
	 */
	protected ResultSet executeComputeQuery(String dburl, String query) {
		try {
			Class.forName(Config.COMPUTE_DRIVER_CLASS);
			Connection conn = DriverManager.getConnection(dburl,
					Config.COMPUTE_DB_USER, Config.COMPUTE_DB_PASSWD);

			Statement stmt = conn.createStatement();
			return stmt.executeQuery(query);
		} catch (Exception e) {
			assertTrue(e.toString(), false);
			return null;
		}
	}

	/**
	 * Executes statement on local node without returning a result
	 * @param query
	 */
	protected void executeComputeStmt(String query) {
		this.executeComputeStmt(Config.COMPUTE_DB_URL
				+ Config.COMPUTE_DB_NAME, query);
	}
	
	/**
	 * Executes statement on a given node without returning a result
	 * @param dburl
	 * @param query
	 */
	protected void executeComputeStmt(String dburl, String query) {
		try {
			Class.forName(Config.COMPUTE_DRIVER_CLASS);
			Connection conn = DriverManager.getConnection(dburl,
					Config.COMPUTE_DB_USER, Config.COMPUTE_DB_PASSWD);

			Statement stmt = conn.createStatement();
			stmt.execute(query);
		} catch (Exception e) {
			assertTrue(e.toString(), false);
		}
	}
	
	/**
	 * Executes a statement and checks if no error occurred
	 * 
	 * @param stmt
	 */
	protected void execute(AbstractServerStmt stmt) {
		assertNoError(stmt.execute());
	}

	/**
	 * Compiles and executes a statement and checks if no error occurred
	 * 
	 * @param stmt
	 */
	protected void compileAndExecute(AbstractServerStmt stmt) {
		assertNoError(stmt.compile());
		assertNoError(stmt.execute());
	}
	
	/**
	 * Executes a statement and checks if error occurred
	 * 
	 * @param stmt
	 */
	protected void compileAndExecuteWithErr(AbstractServerStmt stmt) {
		Error lastError = stmt.compile();

		if (!lastError.isError())
			assertError(stmt.execute());
	}
}
