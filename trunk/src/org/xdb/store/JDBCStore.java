package org.xdb.store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.types.AbstractSimpleType;
import org.xdb.funsql.types.SQLInteger;
import org.xdb.funsql.types.SQLVarchar;
import org.xdb.funsql.types.TableType;
import org.xdb.funsql.types.TupleType;
import org.xdb.logging.XDBLog;

public abstract class JDBCStore extends AbstractStore {
	//log component
	protected Logger logger;
	//connection
	protected String driver;
	protected Connection conn;
	protected String url;
	//result
	protected String query;
	protected ResultSet result;
	protected TupleType resultPrototype;
	//error
	protected Error lastError = Error.NO_ERROR;
	
	//constructors
	public JDBCStore(String driver) {
		super();
		this.driver = driver;
		this.logger = XDBLog.getLogger(this.getClass().getName());
	}

	//methods
	@Override
	public Error openConnection(String url, String user, String passwd) {
		lastError = Error.NO_ERROR;
		
		try {
			this.url = url;
			Class.forName(this.driver);
			this.conn = DriverManager.getConnection(this.url, user, passwd);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			lastError =  AbstractStore.createConnectionNotPossible(url,
					e.getMessage());
		}
		return lastError;
	}

	@Override
	public Error executeQuery(String query, TupleType resultPrototype) {
		lastError = Error.NO_ERROR;
		
		try {
			this.query = query;
			this.resultPrototype = resultPrototype;
			
			Statement stmt = this.conn.createStatement();
			this.result = stmt.executeQuery(query);
		
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			lastError = AbstractStore.createExecutionError(query, e.getMessage());
		}
		return lastError;
	}

	@Override
	public TableType fetchResult() {
		lastError = Error.NO_ERROR;
		
		// read next block
		int fetchedRows = 0;
		TableType table = new TableType();
		try {
			while (fetchedRows < Config.COMPUTE_MAX_FETCHSIZE && this.result.next()) {
				TupleType tuple = this.resultPrototype.clone();
				
				for(int i=0; i<tuple.size(); ++i){
					AbstractSimpleType value = tuple.getValue(i);
					switch(value.getType()){
					case SQL_INTEGER:
						SQLInteger intValue = (SQLInteger)value;
						intValue.setValue(this.result.getInt(i+1));
						break;
					case SQL_VARCHAR:
						SQLVarchar stringValue = (SQLVarchar)value;
						stringValue.setValue(this.result.getString(i+1));
						break;
					}
				}
				
				table.addTuple(tuple);
				fetchedRows++;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			lastError = AbstractStore.createExecutionError(this.query, e.getMessage());
		}

		// return result
		if (fetchedRows == 0)
			return null;
		else
			return table;
	}
	
	@Override
	public Error executeUpdate(String update){
		lastError = Error.NO_ERROR;
		
		try {
			Statement stmt = this.conn.createStatement();
			stmt.executeUpdate(update);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			lastError = AbstractStore.createExecutionError(update, e.getMessage());
		}
		
		return lastError;
	}
	
	@Override
	public Error execute(String ddl){
		lastError = Error.NO_ERROR;
		
		try {
			Statement stmt = this.conn.createStatement();
			stmt.execute(ddl);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			lastError = AbstractStore.createExecutionError(ddl, e.getMessage());
		}
		
		return lastError;
	}
	
	@Override
	public void executeWithException(String ddl) throws Exception{
		Statement stmt = this.conn.createStatement();
		stmt.execute(ddl);
	}

	@Override
	public Error closeConnection() {
		lastError = Error.NO_ERROR;
		
		try {
			this.conn.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			lastError = AbstractStore.createConnectionNotPossible(this.url,
					e.getMessage());
		}
		return lastError;
	}
}
