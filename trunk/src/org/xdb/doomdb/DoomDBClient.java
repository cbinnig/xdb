package org.xdb.doomdb;

import org.xdb.client.CompileClient;
import org.xdb.client.ComputeClient;
import org.xdb.client.MasterTrackerClient;
import org.xdb.client.statement.ClientStmt;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.doomdb.EnumDoomDBSchema;
import org.xdb.doomdb.IDoomDBClient;
import org.xdb.doomdb.IDoomDBPlan;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.server.CompileServer;
import org.xdb.utils.Tuple;

/**
 * DoomDBClient to talk to XDB
 */
public class DoomDBClient implements IDoomDBClient {
	
	private DoomDBPlan dplan = null;
	private EnumDoomDBSchema schema = null;
	private DoomDBClusterDesc clusterDesc = null;
	
	private CompileClient cClient = new CompileClient();
	private MasterTrackerClient mClient = new MasterTrackerClient();
	private ComputeClient compClient = new ComputeClient();
	
	//time in seconds
	private int mttr;
	private int mtbf;

	// constructors
	public DoomDBClient(DoomDBClusterDesc clusterDesc, int mttr, int mtbf) {
		this.mtbf = mtbf;
		this.mttr = mttr;
		
		this.clusterDesc = clusterDesc;
	}
	
	@Override
	public boolean startDB() {
		Error err = this.mClient.startDoomDBCluster(clusterDesc);
		if(err.isError())
			return false;
		
		return true;
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
	public void setSchema(String schemaName) {
		EnumDoomDBSchema schema = EnumDoomDBSchema.enumOf(schemaName);
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
		Tuple<Error, Object> result = this.cClient.executeCmdWithResult(CompileServer.CMD_DOOMDB_COMPILE, args);
		this.raiseError(result.getObject1());

		this.dplan = (DoomDBPlan) result.getObject2();
		this.dplan.tracePlan();
	}

	@Override
	public IDoomDBPlan getPlan() {
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
	
	public boolean isAlive(String opId){
		if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}
		
		return this.dplan.isAlive(opId);
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
		if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}
		
		return this.dplan.getComputeNodeCount();
	}

	@Override
	public long getEstimatedTime() {
		if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}
		return dplan.getEstimatedTime();
	}

	@Override
	public String getNode(String opId) {
		if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}
		return this.dplan.getComputeNodeByOp(opId).toString();
	}

	@Override
	public void killNode(ComputeNodeDesc computeNodeDesc) { 
		//ComputeNodeDesc computeNodeDesc = this.dplan.getComputeNode(nodeDesc);
		compClient.restartComputeNode(computeNodeDesc, this.mttr*1000);
		//RestartComputeServer restartThread = new RestartComputeServer(computeNodeDesc);
		//restartThread.start();
	}

	@Override
	public void setMTTR(int mttr) {
		this.mttr = mttr;
	}

	@Override
	public void setMTBF(int mtbf) {
		this.mtbf = mtbf;
		
	}

	@Override
	public int getMTTR() {
		return this.mttr;
	}

	@Override
	public int getMTBF() {
		return this.mtbf;
	}
}
