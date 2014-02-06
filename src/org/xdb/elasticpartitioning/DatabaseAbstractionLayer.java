package org.xdb.elasticpartitioning;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


public class DatabaseAbstractionLayer {
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement preparedStatement = null;

	private String username;
	private String password;
	private String servername;
	private String portnumber;
	private String dbname;

	private static DatabaseAbstractionLayer singleton = null;

	private DatabaseAbstractionLayer(String username, String password, String serverName, String portName, String dbName) {
		this.username = username;
		this.password = password;
		this.servername = serverName;
		this.portnumber = portName;
		this.dbname = dbName;


		Properties connectionProps = new Properties();
		connectionProps.put("user", this.username);
		connectionProps.put("password", this.password);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql" + "://" + this.servername +
					":" + this.portnumber + "/" + this.dbname,
					connectionProps);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			System.out.println("Connected to database");
		}
	}

	public static DatabaseAbstractionLayer getInstance() throws DatabaseNotInitializedException{
		if (singleton != null) return singleton;
		else{
			throw new DatabaseNotInitializedException();
		}
	}

	public static void initialize(String username, String password, String serverName, String portName, String dbName){
		singleton = new DatabaseAbstractionLayer(username, password, serverName, portName, dbName);
	}

	public ResultSet select(Table table, List<String> columnNames) throws SQLException{
		try{
			StringBuilder cnames = new StringBuilder();
			for (int i=0; i< columnNames.size()-1; i++){
				cnames.append(columnNames.get(i) + ", ");
			}
			cnames.append(columnNames.get(columnNames.size()-1));
			String query = "SELECT " + cnames + " FROM " + table.getTableName();
			preparedStatement = conn.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			return rs;

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			System.out.println("SQLState: " + ex.getSQLState()); 
			System.out.println("VendorError: " + ex.getErrorCode()); 
			throw ex;
		} 
	}
	public static void main(String[] args) {
	}

	public int getTableSize(Table table) throws SQLException {
		try{
			String query = "SELECT COUNT(*) FROM " + table.getTableName();
			preparedStatement = conn.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			int size = 0;
			while(rs.next())
				size = rs.getInt(1);
			return size;

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			System.out.println("SQLState: " + ex.getSQLState()); 
			System.out.println("VendorError: " + ex.getErrorCode()); 
			throw ex;
		} 
	}

}
