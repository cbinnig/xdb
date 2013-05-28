package org.xdb.funsql.statement;

import org.xdb.error.Error;

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
	 * Execute a statement
	 * 
	 * @return
	 */
	public abstract Error execute();

	/**
	 * Return SQL representation of statement
	 * 
	 * @return
	 */
	public String toSqlString() {
		return this.stmtString;
	}
}
