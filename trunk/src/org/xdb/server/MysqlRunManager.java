package org.xdb.server;

import org.xdb.Config;


/**
 * @author Abdallah 
 * Fault Tolerance DoomDB 
 * Thread used to simulate the node failure 
 * by restarting the mysql server on a certian 
 * compute node. 
 */
public class MysqlRunManager extends Thread {
 
	@Override
	public void run() {
		MysqlRunManager obj = new MysqlRunManager(); 
		//stopping the server 
		String stopCommand = "sudo "+Config.MYSQL_DIR+"mysqladmin shutdown -proot";
		obj.executeCommand(stopCommand); 
		//Starting the server
		String runCommand = "sudo "+Config.MYSQL_DIR+"mysqld_safe"; 
		obj.executeCommand(runCommand);
        	
	} 
	
	private void executeCommand(String command) {
        try{
		   Runtime.getRuntime().exec(command);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}