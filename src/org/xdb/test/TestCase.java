package org.xdb.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import junit.framework.Assert;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.statement.AbstractStatement;
import org.xdb.server.MetadataServer;

public abstract class TestCase extends junit.framework.TestCase {
	public TestCase() {
		super();
	}

	@Override
	protected void setUp(){
		assertNoError(MetadataServer.delete());
		assertNoError(MetadataServer.start());
	}
	
	/**
	 * Asserts if error occurred
	 * 
	 * @param error
	 */
	protected void assertNoError(Error error) {
		Assert.assertEquals(Error.NO_ERROR, error);
	}

	/**
	 * Asserts if no error occurred
	 * 
	 * @param error
	 */
	protected void assertError(Error error) {
		Assert.assertNotSame(Error.NO_ERROR, error);
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
			Assert.assertEquals(expectedCnt, actualCnt);
		} else {
			Assert.assertTrue(false);
		}
	}

	/**
	 * Executes query on local node and returns result
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	protected ResultSet execute(String query) throws Exception {
		Class.forName(Config.COMPUTE_DRIVER_CLASS);
		Connection conn = DriverManager.getConnection(Config.COMPUTE_DB_URL
				+ Config.COMPUTE_DB_NAME, Config.COMPUTE_DB_USER,
				Config.COMPUTE_DB_PASSWD);

		Statement stmt = conn.createStatement();
		return stmt.executeQuery(query);
	}
	
	/**
	 * Executes a statement and checks if no error occurred
	 * @param stmt
	 */
	protected void execute(AbstractStatement stmt){
		assertNoError(stmt.execute());
	}
	
	/**
	 * Compiles and executes a statement and checks if no error occurred
	 * @param stmt
	 */
	protected void compileAndExecute(AbstractStatement stmt){
		assertNoError(stmt.compile());
		assertNoError(stmt.execute());
	}
	

	/**
	 * Executes a statement and checks if error occurred
	 * @param stmt
	 */
	protected void compileAndExecuteWithErr(AbstractStatement stmt){
		Error lastError = stmt.compile();
		assertError(lastError);
		
		if(lastError == Error.NO_ERROR)
			assertError(stmt.execute());
	}
}
