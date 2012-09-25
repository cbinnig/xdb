package org.xdb.funsql.compile.predicate;

import org.xdb.funsql.compile.tokens.AbstractToken;

public enum EnumCompOperator {
	SQL_NOOP,
	SQL_EQUAL,
	SQL_NOT_EQUAL,
	SQL_LESS_EQUAL,
	SQL_LESS_THAN,
	SQL_GREATER_EQUAL,
	SQL_GREATER_THAN;
	
	public static EnumCompOperator get(String type){
		if(type.equals(AbstractToken.EQUAL1))
			return SQL_EQUAL;
		else if(type.equals(AbstractToken.NOT_EQUAL1))
			return SQL_NOT_EQUAL;
		else if(type.equals(AbstractToken.NOT_EQUAL2))
			return SQL_NOT_EQUAL;
		else if(type.equals(AbstractToken.LESS_EQUAL))
			return SQL_LESS_EQUAL;
		else if(type.equals(AbstractToken.LESS_THAN))
			return SQL_LESS_THAN;
		else if(type.equals(AbstractToken.GREATER_EQUAL))
			return SQL_GREATER_EQUAL;
		else if(type.equals(AbstractToken.GREATER_THAN))
			return SQL_GREATER_THAN;
		return null;
	}
	
	public String toString(){
		switch(this.ordinal()){
		case 1:
			return AbstractToken.EQUAL1;
		case 2:
			return AbstractToken.NOT_EQUAL1;
		case 3:
			return AbstractToken.NOT_EQUAL2;
		case 4:
			return AbstractToken.LESS_EQUAL;
		case 5:
			return AbstractToken.LESS_THAN;
		case 6:
			return AbstractToken.GREATER_EQUAL;
		case 7:
			return AbstractToken.GREATER_THAN;
		}
		return "";
	}
}
