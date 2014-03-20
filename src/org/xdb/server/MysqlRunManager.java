package org.xdb.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.Config;
import org.xdb.execute.ComputeNode;

/**
 * Manager for MySQL database e.g. for killing all running queries
 * 
 * @author cbinnig
 *
 */
public class MysqlRunManager {

	/**
	 * Kill all queries runing in MySQL
	 * @return
	 */
	public Error killAllQueries() {
		Error err = new Error();

		try {
			Class.forName(Config.COMPUTE_DRIVER_CLASS);
			Connection conn = DriverManager.getConnection(
					Config.COMPUTE_DB_URL, Config.COMPUTE_DB_USER,
					Config.COMPUTE_DB_PASSWD);

			// get all process IDs
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT concat('KILL ',id,';') FROM information_schema.processlist where state='executing'");
			
			Vector<String> killQueries = new Vector<String>();
			while (rs.next()) {
				killQueries.add(rs.getString(1));
			}
			stmt.close();
			
			// kill all processes
			Statement killStmt = conn.createStatement();
			for(String killQuery: killQueries){
				System.err.println(killQuery);
				try {
					killStmt.execute(killQuery);
				}
				catch (Exception e) {
					//no errors
				}
			}
			killStmt.close();
			
			conn.close();

		} catch (SQLException e) {
			err = ComputeNode.createMySQLError(e);
		} catch (Exception e) {
			err = ComputeNode.createMySQLError(e);
		}
		return err;
	}
}