package org.xdb.client;

import java.util.Set;

import org.xdb.Config;
import org.xdb.client.statement.ClientStmt;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.doomdb.DoomDBSchema;
import org.xdb.doomdb.IDoomDBClient;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.logging.XDBLog;
import org.xdb.server.CompileServer;
import org.xdb.utils.Identifier;
import org.xdb.utils.Tuple;

/**
 * DoomDBClient to talk to XDB
 */
public class DoomDBClient extends AbstractClient implements IDoomDBClient {
	private DoomDBPlan dplan = null;
	private DoomDBSchema schema = null;

	private CompileClient cClient = new CompileClient();
	private MasterTrackerClient mClient = new MasterTrackerClient();

	// constructors
	public DoomDBClient() {
		this.logger = XDBLog.getLogger(this.getClass().getName());
		this.port = Config.COMPILE_PORT;
		this.url = Config.COMPILE_URL;
	}

	// getters and setters
	private void raiseError(Error err) {
		if (err.isError()) {
			throw new RuntimeException(err.toString());
		}
	}

	// methods
	private Error executeDDLs(String[] schemaDDLs) {
		Error err = new Error();
		for (String schemaDDL : schemaDDLs) {
			err = cClient.executeStmt(schemaDDL);
			if (err.isError())
				return err;
		}
		return err;
	}

	@Override
	public void setSchema(DoomDBSchema schema) {
		Error err = cClient.resetCatalog();
		this.raiseError(err);

		err = this.executeDDLs(schema.getCreateDDL());
		this.raiseError(err);

		this.schema = schema;
	}

	@Override
	public void setQuery(String query) {
		if (this.schema == null) {
			throw new RuntimeException("Provide a schema before!");
		}

		ClientStmt clientStmt = new ClientStmt(query);
		Object[] args = { clientStmt };
		Tuple<Error, Object> result = this.executeCmdWithResult(this.url,
				this.port, CompileServer.CMD_DOOMDB_COMPILE, args);
		this.raiseError(result.getObject1());

		this.dplan = (DoomDBPlan) result.getObject2();
	}

	@Override
	public DoomDBPlan getPlan() {
		return this.dplan;
	}

	@Override
	public void startQuery() {
		if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}

		new Thread() {
			public void run() {
				Error err = mClient.executeDoomDBPlan(dplan.getPlanDesc());
				raiseError(err);
			}
		}.start();
	}

	@Override
	public boolean isQueryFinished() {
		if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}
		Tuple<Error, Boolean> result = mClient.isDoomDBPlanFinished(dplan
				.getPlanDesc());
		this.raiseError(result.getObject1());
		return result.getObject2();
	}

	@Override
	public int getNodeCount() {
		if (this.schema != null)
			return this.schema.getPartitions();

		return 0;
	}

	@Override
	public long getEstimatedTime() {
		if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}
		return dplan.getEstimatedTime();
	}

	@Override
	public ComputeNodeDesc getNode(Identifier opId) {
		if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}
		return this.dplan.getComputeNode(opId);
	}

	@Override
	public void killNode(ComputeNodeDesc nodeDesc) {
		@SuppressWarnings("unused")
		Set<Identifier> killedOps = this.dplan.getOperators(nodeDesc);
		
		//TODO: set status of all killed operators to ABORTED in query tracker plan
	}


	@Override
	public void setMTTR(int time) {
		// TODO: change monitoring interval in query tracker plan
		
	}
}
