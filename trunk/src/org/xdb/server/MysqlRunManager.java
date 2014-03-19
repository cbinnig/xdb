package org.xdb.server;

import org.xdb.Config;

/**
 * @author Abdallah Fault Tolerance DoomDB Thread used to simulate the node
 *         failure by restarting the mysql server on a certian compute node.
 */
public class MysqlRunManager extends Thread {

	@Override
	public void run() {
		MysqlRunManager obj = new MysqlRunManager();
		// stopping the server
		String stopCommand = "sudo " + Config.MYSQL_DIR
				+ "mysqladmin shutdown -u" + Config.COMPUTE_DB_USER + " -p"
				+ Config.COMPUTE_DB_PASSWD;
		obj.executeCommand(stopCommand, true);

		// Starting the server
		String runCommand = "sudo " + Config.MYSQL_DIR + "mysqld_safe";
		obj.executeCommand(runCommand, false);
	}

	private void executeCommand(String command, boolean doWait) {
		try {
			System.out.println("Starting " + command);
			Process p = Runtime.getRuntime().exec(command);
			if (doWait) {
				int returnCode = p.waitFor();
				if (returnCode > 0)
					throw new RuntimeException("Could not restart Mysql");
			}
			System.out.println("Executed " + command);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}