package org.xdb.client;

import org.xdb.Config;
import org.xdb.client.statement.ClientStmt;
import org.xdb.error.Error;
import org.xdb.logging.XDBLog;
import org.xdb.server.CompileServer;

/**
 * Client to talk to Compute Server.
 */
public class CompileClient extends AbstractClient {

	// constructors
	public CompileClient() {
		this.logger = XDBLog.getLogger(this.getClass().getName());
		this.port = Config.COMPILE_PORT;
		this.url = Config.COMPILE_URL;
	}

	/**
	 * Execute a given FunSQL statement on compile server
	 * 
	 * @param stmt
	 * @return
	 */
	public Error executeStmt(String stmt) {
		ClientStmt clientStmt = new ClientStmt(stmt);
		Object[] args = { clientStmt };
		return this.executeCmd(this.url, this.port,
				CompileServer.CMD_EXECUTE_WO_RESULT, args);
	}
	
	public Error resetCatalog() {
		Object[] args = { };
		return this.executeCmd(this.url, this.port,
				CompileServer.CMD_RESET_CATALOG, args);
	}
}
