package org.xdb.funsql.compile.operator;

import java.io.Serializable;

public enum EnumOperator implements Serializable {
	EQUI_JOIN,
	GENERIC_SELECTION,
	GENERIC_AGGREGATION,
	GENERIC_PROJECTION,
	TABLE,
	FUNCTION_CALL,
	RENAME,
	SQL_UNARY,
	SQL_JOIN,
	SQL_COMBINED,
	UNION;
	
	public boolean isJoin(){
		switch(this){
		case EQUI_JOIN:
		case SQL_JOIN:
			return true;
		default:
			return false;
		}
	}
	
	public boolean isSQLJoin(){
		switch(this){
		case SQL_JOIN:
			return true;
		default:
			return false;
		}
	}
	
	public boolean isSelection(){
		switch(this){
		case GENERIC_SELECTION:
			return true;
		default:
			return false;
		}
	}
}