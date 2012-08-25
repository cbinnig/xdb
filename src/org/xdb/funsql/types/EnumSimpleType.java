package org.xdb.funsql.types;

import org.xdb.error.EnumError;
import org.xdb.error.Error;

public enum EnumSimpleType {
	SQL_INTEGER,
	SQL_VARCHAR;
	
	private static final EnumSimpleType[] values  = { SQL_INTEGER, SQL_VARCHAR };
	
	public static EnumSimpleType get(int i){
		return values[i];
	}
	
	public static EnumSimpleType get(String type){
		if(type.equalsIgnoreCase("INTEGER"))
			return SQL_INTEGER;
		else if(type.equalsIgnoreCase("INT"))
			return SQL_INTEGER;
		else if(type.equalsIgnoreCase("VARCHAR"))
			return SQL_VARCHAR;
		return null;
	}
	
	public static Error createTypeNotExistsErr(String att) {
		String args[] = { att };
		Error error = new Error(EnumError.CREATE_TABLE_TYPE_NOT_EXISTS, args);
		return error;
	}
	
	public String toString(){
		switch(this.ordinal()){
		case 0:
			return "INTEGER";
		case 1:
			return "VARCHAR";
		}
		return "";
	}
}
