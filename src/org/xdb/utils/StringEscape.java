package org.xdb.utils;

public class StringEscape {
	public static String escapeSql(String sql){
		sql = sql.trim();
		sql = sql.replaceAll("'", "''");
		return sql;
	}
}
