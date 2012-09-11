package org.xdb.store;

import org.xdb.error.EnumError;
import org.xdb.error.Error;


public enum EnumStore {
	POSTGRES,
	MYSQL_FEDERATED;
	
	private static final EnumStore[] values  = { POSTGRES, MYSQL_FEDERATED };
	
	public static EnumStore get(int i){
		return values[i];
	}
	
	public static EnumStore get(String type){
		if(type.equalsIgnoreCase("POSTGRES"))
			return POSTGRES;
		else if(type.equalsIgnoreCase("MYSQLF"))
			return MYSQL_FEDERATED;
		return null;
	}
	
	public static synchronized Error createStoreNotExistsErr(
			String type) {
		String args[] = { type };
		Error error = new Error(EnumError.STORE_NOT_EXISTS, args);
		return error;
	}
}
