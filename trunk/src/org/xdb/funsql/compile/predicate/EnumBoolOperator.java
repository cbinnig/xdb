package org.xdb.funsql.compile.predicate;

import org.xdb.funsql.compile.tokens.AbstractToken;

public enum EnumBoolOperator {
	SQL_NOOP,
	SQL_AND,
	SQL_OR,
	SQL_NOT;
	
	public static EnumBoolOperator get(String type){
		if(type.equals(AbstractToken.AND))
			return SQL_AND;
		else if(type.equalsIgnoreCase(AbstractToken.OR))
			return SQL_OR;
		else if(type.equalsIgnoreCase(AbstractToken.NOT))
			return SQL_NOT;
		return null;
	}
	
	public String toString(){
		switch(this.ordinal()){
		case 1:
			return AbstractToken.AND;
		case 2:
			return AbstractToken.OR;
		case 3:
			return AbstractToken.NOT;
		}
		return "";
	}
}
