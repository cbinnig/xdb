package org.xdb.client;

import org.xdb.Config;
import org.xdb.client.statement.ClientStmt;
import org.xdb.error.Error;
import org.xdb.logging.EnumXDBComponents;
import org.xdb.server.CompileServer;

/**
 * Client to talk to Compute Server.
 */
public class CompileClient extends AbstractClient {

	// constructors
	public CompileClient() {
		super(Config.COMPILE_URL, Config.COMPILE_PORT, EnumXDBComponents.COMPILE_CLIENT);
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
		return this.executeCmd(CompileServer.CMD_EXECUTE_WO_RESULT, args);
	}

	public Error resetCatalog() {
		Object[] args = {};
		return this.executeCmd(CompileServer.CMD_RESET_CATALOG, args);
	}
}
