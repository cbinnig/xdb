package org.xdb.store;

import org.xdb.error.EnumError;
import org.xdb.error.Error;


public enum EnumStore {
	XDB("xdb"), //??
	MYSQL("mysql"),
	POSTGRES("postgres");
	
	private final String stringRepr; 
	
	private EnumStore(final String str) {
		stringRepr = str;
	}
	
	private static final EnumStore[] values  = { XDB, MYSQL, POSTGRES };
	
	public static EnumStore get(int i){
		return values[i];
	}
	
	public static EnumStore get(String type){
		if(type.equalsIgnoreCase("XDB"))
			return XDB;
		else if(type.equalsIgnoreCase("MYSQL"))
			return MYSQL;
		else if(type.equalsIgnoreCase("POSTGRES"))
			return POSTGRES;
		return null;
	}
	
	public static synchronized Error createStoreNotExistsErr(
			String type) {
		String args[] = { type };
		Error error = new Error(EnumError.STORE_NOT_EXISTS, args);
		return error;
	}
	
	public String toString() {
		return stringRepr;
	}
}
