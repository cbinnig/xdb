package org.xdb.client;

import java.util.List;
import java.util.Map;

import org.xdb.Config;
import org.xdb.client.statement.ClientStmt;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.doomdb.QueryStats;
import org.xdb.doomdb.QueryWithStats;
import org.xdb.error.Error;
import org.xdb.logging.EnumXDBComponents;
import org.xdb.server.CompileServer;
import org.xdb.utils.Identifier;
import org.xdb.utils.Tuple;

/**
 * Client to talk to Compute Server.
 */
public class CompileClient extends AbstractClient {

	// constructors
	public CompileClient() {
		super(Config.COMPILE_URL, Config.COMPILE_PORT,
				EnumXDBComponents.COMPILE_CLIENT);
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

	public Tuple<Error, DoomDBPlan> compileDoomStmtWithStats(String stmt,
			Map<Identifier, Double> queryRuntimesStat,
			Map<Identifier, Double> queryMattimesStat,
			List<Identifier> nonMatOps) {
		ClientStmt clientStmt = new ClientStmt(stmt);
		QueryStats queryStats = new QueryStats(queryRuntimesStat,
				queryMattimesStat, nonMatOps);
		QueryWithStats queryWithStats = new QueryWithStats(clientStmt,
				queryStats);
		Object[] args = { queryWithStats };

		Tuple<Error, Object> result = this.executeCmdWithResult(
				CompileServer.CMD_DOOMDB_COMPILE, args);

		DoomDBPlan plan = (DoomDBPlan) result.getObject2();
		plan.setQueryRuntimesStat(queryRuntimesStat);
		return new Tuple<Error, DoomDBPlan>(result.getObject1(), plan);
	}

	public Error resetCatalog() {
		Object[] args = {};
		return this.executeCmd(CompileServer.CMD_RESET_CATALOG, args);
	}
}
