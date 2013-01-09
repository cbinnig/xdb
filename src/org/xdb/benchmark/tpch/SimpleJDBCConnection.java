package org.xdb.benchmark.tpch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class SimpleJDBCConnection {
	
	protected String driver;
	protected Connection conn;
	protected String url;
	
	
	
	public SimpleJDBCConnection(String url, String driver) {
		super();
		this.url = url;
		this.driver = driver;
	}

	public void openConnection(String user, String passwd) {
		
		try {
			Class.forName(this.driver);
			this.conn = DriverManager.getConnection(this.url, user, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean executeQuery(String query) throws SQLException{
		PreparedStatement statement = this.conn.prepareStatement(query);
		return statement.execute();
	}
	public void close(){
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
