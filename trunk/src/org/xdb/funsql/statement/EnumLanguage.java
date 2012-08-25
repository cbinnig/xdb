package org.xdb.funsql.statement;


public enum EnumLanguage {
	FUNSQL;
	
	private static final EnumLanguage[] values  = { FUNSQL };
	
	public static EnumLanguage get(int i){
		return values[i];
	}
	
	public static EnumLanguage get(String type){
		if(type.equalsIgnoreCase("FUNSQL"))
			return FUNSQL;
		return null;
	}
}
