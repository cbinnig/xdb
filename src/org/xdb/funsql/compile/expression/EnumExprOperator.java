package org.xdb.funsql.compile.expression;

import org.xdb.funsql.compile.tokens.AbstractToken;

public enum EnumExprOperator {
	SQL_NOOP,
	SQL_PLUS,
	SQL_MINUS,
	SQL_MULT,
	SQL_DIV;
	
	public static EnumExprOperator get(String type){
		if(type.equals(AbstractToken.PLUS))
			return SQL_PLUS;
		else if(type.equalsIgnoreCase(AbstractToken.MINUS))
			return SQL_MINUS;
		else if(type.equalsIgnoreCase(AbstractToken.MULT))
			return SQL_MULT;
		else if(type.equalsIgnoreCase(AbstractToken.DIV))
			return SQL_DIV;
		return null;
	}
	
	public String toString(){
		switch(this.ordinal()){
		case 1:
			return AbstractToken.PLUS;
		case 2:
			return AbstractToken.MINUS;
		case 3:
			return AbstractToken.MULT;
		case 4:
			return AbstractToken.DIV;
		}
		return "";
	}
}
