package org.xdb.store;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.funsql.types.TableType;
import org.xdb.funsql.types.TupleType;

public abstract class AbstractStore{
	
	/**
	 * Checks which operators are supported by a concrete data store
	 * @param operator
	 * @return
	 */
	public abstract boolean supportsOperator(EnumOperator operator);
	
	/**
	 * Returns a query string for a given operator tree
	 * @param operator
	 * @return
	 */
	public abstract String generateQuery(AbstractCompileOperator operator);
	
	/**
	 * Opens connection to store
	 * @param url
	 * @param user
	 * @param passwd
	 * @return
	 */
	public abstract Error openConnection(String url, String user, String passwd);
	
	/**
	 * Executes query and provides cursor to result (internally)
	 * @param query
	 * @return
	 */
	public abstract Error executeQuery(String query, TupleType resultPrototype);
	
	/**
	 * Fetches result in blocks
	 * @return
	 */
	public abstract TableType fetchResult();
	
	
	/**
	 * Update data in store 
	 * @param tuple
	 * @return
	 */
	public abstract Error executeUpdate(String update);
	
	/**
	 * Execute ddl in store
	 * @param tuple
	 * @return
	 */
	public abstract Error execute(String ddl);
	
	/**
	 * Execute ddl in store
	 * @param tuple
	 * @return
	 */
	public abstract void executeWithException(String ddl) throws Exception;
	
	/**
	 * Closes connection
	 * @return
	 */
	public abstract Error closeConnection();
	
	/**
	 * Create error when connection is not possible
	 * @param url
	 * @param msg
	 * @return
	 */
	public static synchronized Error createConnectionNotPossible(String url, String msg) {
		String args[] = { url, msg };
		Error error = new Error(EnumError.STORE_CONNECTION_ERROR, args);
		return error;
	}
	
	/**
	 * Create error when query execution is not possible
	 * @param query
	 * @param msg
	 * @return
	 */
	public static synchronized Error createExecutionError(String query, String msg) {
		String args[] = { query, msg };
		Error error = new Error(EnumError.STORE_EXECUTION_ERROR, args);
		return error;
	}
}