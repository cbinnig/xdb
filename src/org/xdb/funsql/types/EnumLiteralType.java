package org.xdb.funsql.types;

public enum EnumLiteralType {
	SQL_NO_LITERAL,
	SQL_STRING_LITERAL,
	SQL_INTEGER_LITERAL,
	SQL_DECIMAL_LITERAL,
	SQL_DATE_LITERAL;
	
	public EnumSimpleType getSimpleType(){
		switch(this){
		case SQL_STRING_LITERAL:
			return EnumSimpleType.SQL_VARCHAR;
		case SQL_INTEGER_LITERAL:
			return EnumSimpleType.SQL_INTEGER;
		case SQL_DECIMAL_LITERAL:
			return EnumSimpleType.SQL_DECIMAL;
		case SQL_DATE_LITERAL:
			return EnumSimpleType.SQL_DATE;
		}
		return EnumSimpleType.SQL_NOTYPE;
	}
}