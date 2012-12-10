package org.xdb.funsql.types;

import org.xdb.error.EnumError;
import org.xdb.error.Error;

public enum EnumSimpleType implements Cloneable {
	SQL_NOTYPE, 
	SQL_INTEGER, 
	SQL_VARCHAR, 
	SQL_CHAR, 
	SQL_DECIMAL, 
	SQL_DATE;

	private static final EnumSimpleType[] values = { SQL_INTEGER, SQL_VARCHAR,
			SQL_CHAR, SQL_DECIMAL, SQL_DATE };


	public boolean isSet(){
		if(this.equals(EnumSimpleType.SQL_NOTYPE))
			return false;
		
		return true;
	}
	
	public static EnumSimpleType get(int i) {
		return values[i];
	}

	public static EnumSimpleType get(String type) {
		if (type.equalsIgnoreCase("INTEGER"))
			return SQL_INTEGER;
		else if (type.equalsIgnoreCase("INT"))
			return SQL_INTEGER;
		else if (type.equalsIgnoreCase("VARCHAR"))
			return SQL_VARCHAR;
		else if (type.equalsIgnoreCase("CHAR"))
			return SQL_CHAR;
		else if (type.equalsIgnoreCase("DECIMAL"))
			return SQL_DECIMAL;
		else if (type.equalsIgnoreCase("DATE"))
			return SQL_DATE;
		return null;
	}

	public static Error createTypeNotExistsErr(String att) {
		String args[] = { att };
		Error error = new Error(EnumError.CREATE_TABLE_TYPE_NOT_EXISTS, args);
		return error;
	}

	public String toString() {
		//TODO: Modify SQL string for VARCHAR and DECIMAL to have max. precision
		switch (this.ordinal()) {
		case 1:
			return "INTEGER";
		case 2:
			return "VARCHAR(255)";
		case 3:
			return "CHAR";
		case 4:
			return "DECIMAL";
		case 5:
			return "DATE";
		}
		return "";
	}

	public static EnumSimpleType promote(EnumSimpleType type1,
			EnumSimpleType type2){
		EnumSimpleType resultType = promoteType(type1, type2);
		if(!resultType.isSet())
			resultType = promoteType(type2, type1);
		
		return resultType;
	}

	public static EnumSimpleType promoteType(EnumSimpleType source,
			EnumSimpleType target) {
		switch (source) {
			// SQL_INTEGER -> SQL_INTEGER -> SQL_DECIMAL
		case SQL_INTEGER:
			switch (target) {
			case SQL_INTEGER:
				return SQL_INTEGER;
			case SQL_DECIMAL:
				return SQL_DECIMAL;
			}
			return SQL_NOTYPE;
			// SQL_DECIMAL -> SQL_DECIMAL
		case SQL_DECIMAL:
			switch (target) {
			case SQL_DECIMAL:
				return SQL_DECIMAL;
			}
			return SQL_NOTYPE;
			// SQL_CHAR -> SQL_CHAR -> SQL_VARCHAR
		case SQL_CHAR:
			switch (target) {
			case SQL_CHAR:
				return SQL_CHAR;
			case SQL_VARCHAR:
				return SQL_VARCHAR;
			}
			return SQL_NOTYPE;
			// SQL_VARCHAR -> SQL_VARCHAR
		case SQL_VARCHAR:
			switch (target) {
			case SQL_VARCHAR:
				return SQL_VARCHAR;
			}
			return SQL_NOTYPE;
			// SQL_DATE -> SQL_DATE
		case SQL_DATE:
			switch (target) {
			case SQL_DATE:
				return SQL_DATE;
			}
			return SQL_NOTYPE;
		}

		return SQL_NOTYPE;
	}
}
