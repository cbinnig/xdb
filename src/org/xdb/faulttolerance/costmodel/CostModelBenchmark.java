package org.xdb.faulttolerance.costmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.xdb.Config;

public class CostModelBenchmark { 

	private Connection conn; 
	
	private long insertTime;

	public CostModelBenchmark(){

		try {
			Class.forName(Config.COMPUTE_DRIVER_CLASS); 
			conn = DriverManager.getConnection(
					Config.COMPUTE_DB_URL, Config.COMPUTE_DB_USER,
					Config.COMPUTE_DB_PASSWD);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @return the insert speed of mysql Bytes/S
	 */
	public double calculateMysqlInsertIntoTableSpeed(){ 
		int dataLength = getDataLength(); 
		System.out.println("Insert Time: "+insertTime +" ms"); 
		System.out.println("Data Size: "+dataLength);  
		System.out.println(1000*Config.COMPILE_FT_BENCHMARK_ROWS_NUMBER/insertTime +" Tuples/s");
		return 1000*dataLength/insertTime; 
	}  

	/**
	 * 
	 */
	private void createSourceAndDestinationTableTable(){  

		String dropSourceTableStmt = "DROP TABLE IF EXISTS SOURCE_TBL";
		String createSourceTableStmt = "CREATE TABLE SOURCE_TBL ("; 
		for(int i=0; i<Config.COMPILE_FT_BENCHMARK_COLUMNS_NUMBER; i++){
			createSourceTableStmt += " COL"+i+" INT,";
		}

		createSourceTableStmt = createSourceTableStmt.trim().substring(0, createSourceTableStmt.lastIndexOf(",")) +" )"; 

		String dropDestinationTableStmt = "DROP TABLE IF EXISTS DESTINATION_TBL"; 
		String createDestinationTableStmt = createSourceTableStmt.replaceAll("SOURCE", "DESTINATION")+" ENGINE="+Config.COMPUTE_ENGINE; 
		try {
			Statement stmt = conn.createStatement(); 
			stmt.execute("USE "+Config.COMPUTE_DB_NAME); 
			stmt.execute(dropSourceTableStmt);
			stmt.execute(createSourceTableStmt);  
			stmt.execute(dropDestinationTableStmt); 
			stmt.execute(createDestinationTableStmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}  

	/**
	 * 
	 */
	private void insertDataIntoSourceTable(){
		String insertStmt = "INSERT INTO SOURCE_TBL VALUES  "; 
		String subInsertStmt = "";
		//for(int i=0; i< Config.COMPILE_FT_BENCHMARK_ROWS_NUMBER; i++){
		subInsertStmt += "(";
		for(int j=0; j<Config.COMPILE_FT_BENCHMARK_COLUMNS_NUMBER; j++){
			subInsertStmt += j+","; 
		}

		subInsertStmt = subInsertStmt.trim().substring(0, subInsertStmt.lastIndexOf(",")) +")"; 

		insertStmt += subInsertStmt;
		System.out.println(insertStmt);
		try {
			Statement stmt = conn.createStatement();   
			stmt.execute("USE "+Config.COMPUTE_DB_NAME); 
			for(int i=0; i< Config.COMPILE_FT_BENCHMARK_ROWS_NUMBER; i++)
				stmt.execute(insertStmt);  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	/**
	 * 
	 */
	private void insertDataIntoDestinationTable(){ 
		String insertIntoDestTable = "INSERT INTO DESTINATION_TBL SELECT * from SOURCE_TBL";  
		try {
			Statement stmt = conn.createStatement();  
			stmt.execute("USE "+Config.COMPUTE_DB_NAME); 
			long startTime = System.currentTimeMillis(); 
			stmt.execute(insertIntoDestTable);   
			long endTime = System.currentTimeMillis(); 
			this.insertTime = (endTime - startTime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}  
	
	/**
	 * 
	 */
	private int getDataLength(){ 
		
		String query = "SELECT Data_length FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'DESTINATION_TBL'";  
		int dataLength = 0;
		try {
			Statement stmt = conn.createStatement();
			// wait until the table status gets updated! 
			Thread.sleep(10000);
			ResultSet rs = stmt.executeQuery(query);   
			while(rs.next()){
				dataLength = rs.getInt("Data_length");
			}
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		System.out.println(dataLength);
		return dataLength;
	}

	public static void main(String[] args) {
		CostModelBenchmark benchmark = new CostModelBenchmark(); 
		benchmark.createSourceAndDestinationTableTable(); 
		benchmark.insertDataIntoSourceTable(); 
		benchmark.insertDataIntoDestinationTable(); 
		System.out.println(benchmark.calculateMysqlInsertIntoTableSpeed() +"Bytes/S"); 
	}

}
