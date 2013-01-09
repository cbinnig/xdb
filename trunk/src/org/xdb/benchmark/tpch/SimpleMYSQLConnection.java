package org.xdb.benchmark.tpch;

public class SimpleMYSQLConnection extends SimpleJDBCConnection{
	
	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	public SimpleMYSQLConnection(String url) {
		super(url,DRIVER_CLASS);
	}

}
