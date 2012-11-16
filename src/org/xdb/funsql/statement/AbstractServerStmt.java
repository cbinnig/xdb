package org.xdb.funsql.statement;

import org.xdb.error.Error;

public abstract class AbstractServerStmt {
	protected EnumStatement statementType;
	protected String stmtString;
	
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
	
	public boolean isDDL(){
		switch(this.statementType){
		case CREATE_CONNECTION:
		case CREATE_SCHEMA:
		case CREATE_TABLE:
		case DROP_TABLE:
			return true;
		}
		return false;
	}
	
	public boolean isDML(){
		switch(this.statementType){
		case SELECT:
		case CREATE_FUNCTION:	
			return true;
		}
		return false;
	}
	
	public abstract Error compile();
	
	public abstract Error execute();
	
	public String toSqlString() {
		return this.stmtString;
	}
}
