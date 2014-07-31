package org.xdb.funsql.statement;

import org.xdb.doomdb.DoomDBPlan;
import org.xdb.doomdb.QueryStats;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.utils.Tuple;


/**
 * Implements a statement on the server side
 * 
 * @author cbinnig
 * 
 */
public abstract class AbstractServerStmt {
	protected EnumStatement statementType;
	protected String stmtString;
	protected boolean doSemanticAnalysis = true;
    protected QueryStats queryStats; 
    
	// getter and setters
	public void doSemanticAnalysis(boolean doSemanticAnalysis) {
		this.doSemanticAnalysis = doSemanticAnalysis;
	}

	public String getStmtString() {
		return stmtString;
	}

	public void setStmtString(String stmtString) {
		this.stmtString = stmtString;
	}

	public EnumStatement getType() {
		return statementType;
	}

	public void setType(EnumStatement type) {
		this.statementType = type;
	} 
	
	public void setQueryStats(QueryStats queryStats){
		this.queryStats = queryStats;
	} 
	
	public QueryStats getQueryStats(){
		return this.queryStats;
	}

	// methods

	/**
	 * Checks if statement is a DDL statement
	 * 
	 * @return
	 */
	public boolean isDDL() {
		switch (this.statementType) {
		case DROP_CONNECTION:
		case CREATE_CONNECTION:
		case DROP_SCHEMA:
		case CREATE_SCHEMA:
		case CREATE_TABLE:
		case DROP_TABLE:
		case CREATE_FUNCTION:
		case DROP_FUNCTION:
			return true;
		default:
			return false;
		}
	}

	/**
	 * Checks if statement is a DML statement
	 * 
	 * @return
	 */
	public boolean isDML() {
		switch (this.statementType) {
		case CALL_FUNCTION:
		case SELECT:
			return true;
		default:
			return false;
		}
	}

	/**
	 * Compile a statement
	 * 
	 * @return
	 */
	public abstract Error compile();

	/**
	 * Optimizes a given statement
	 * 
	 * @return
	 */
	public Error optimize() {
		// Nothing to do in most cases
		return new Error();
	}

	/**
	 * Parallelizes a given statement
	 * 
	 * @return
	 */
	public Error parallelize() {
		// Nothing to do in most cases
		return new Error();
	}
	
	/**
	 * Execute a statement
	 * 
	 * @return
	 */
	public abstract Error execute();

	
	/**
	 * Generates query tracker plan for statement and returns DoomDBPlan
	 * @return
	 */
	public Tuple<Error, DoomDBPlan> generateDoomDBQPlan(){
		return new Tuple<Error, DoomDBPlan>(null, null);
	}
	
	/**
	 * Return SQL representation of statement
	 * 
	 * @return
	 */
	public String toSqlString() {
		return this.stmtString;
	}
	
	public Error createGenericCompileErr(
			String errMsg) {
		String[] args = { errMsg };
		Error lastError = new Error(EnumError.COMPILER_GENERIC, args);
		return lastError;
	}

	public Error applyFaultTolerance(QueryStats queryStats) {
		// TODO Auto-generated method stub
		return new Error();
	}
}
