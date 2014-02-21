package org.xdb.elasticpartitioning.database;

public class DatabaseConfig {
	private String userName;
	private String password;
	private String serverName;
	private String port;
	private String databaseName;
	
	public DatabaseConfig(String userName, String password, String serverName, String port, String databaseName) {
		this.userName = userName;
		this.password = password;
		this.serverName = serverName;
		this.port = port;
		this.databaseName = databaseName;
	}
	
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getServerName() {
		return serverName;
	}
	public String getPort() {
		return port;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	
	

}
