package org.xdb.funsql.compile.predicate;

import org.xdb.funsql.compile.tokens.AbstractToken;

public enum EnumBoolOperator {
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
		case 0:
			return AbstractToken.AND;
		case 1:
			return AbstractToken.OR;
		case 2:
			return AbstractToken.NOT;
		}
		return "";
	}
}
