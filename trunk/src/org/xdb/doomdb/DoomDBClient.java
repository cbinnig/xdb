package org.xdb.doomdb;

import org.xdb.Config;
import org.xdb.client.CompileClient;
import org.xdb.client.MasterTrackerClient;
import org.xdb.client.statement.ClientStmt;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.server.CompileServer;
import org.xdb.utils.Tuple;

/**
 * DoomDBClient to talk to XDB
 */
public class DoomDBClient implements IDoomDBClient {
	
	// main info
	private DoomDBPlan dplan = null;
	private EnumDoomDBSchema schema = null;
	private String query = null;
	private DoomDBClusterDesc clusterDesc = null;
	private Error err = new Error();
	
	// clients for communication
	private CompileClient cClient = new CompileClient();
	private MasterTrackerClient mClient = new MasterTrackerClient();
	
	//time in seconds
	private int mttr = Config.DOOMDB_MTTR;
	private int mtbf = Config.DOOMDB_MTBF;
	private long startTime = 0;
	private long endTime = 0;
	private long runTime = 0;
	private int killedNodes = 0;

	// constructors
	public DoomDBClient(DoomDBClusterDesc clusterDesc) {
		this.clusterDesc = clusterDesc;
	}
	
	@Override
	public boolean startDB() {
		this.err = this.mClient.startDoomDBCluster(clusterDesc);
		if(this.err.isError()){
			System.err.println(err.toString());
			return false;
		}
		
		return true;
	}

	// internal methods
	private void raiseError(Error err) {
		this.err = err;
	}

	private Error executeDDLs(String[] schemaDDLs) {
		Error err = new Error();
		for (String schemaDDL : schemaDDLs) {
			err = cClient.executeStmt(schemaDDL);
			if (err.isError())
				return err;
		}
		return err;
	}
	
	public void killNode(ComputeNodeDesc computeNodeDesc) { 
		this.killedNodes++;
		Error err = mClient.restartComputeNode(computeNodeDesc, this.mttr*1000);
		raiseError(err);
	}

	
	// interface methods
	@Override
	public void setSchema(String schemaName) {
		EnumDoomDBSchema schema = EnumDoomDBSchema.enumOf(schemaName);
		Error err = cClient.resetCatalog();
		this.raiseError(err);

		err = this.executeDDLs(schema.getDDL());
		this.raiseError(err);

		this.schema = schema;
	}

	@Override
	public void setQuery(int queryNum) {
		if (this.schema == null) {
			throw new RuntimeException("Provide a schema before!");
		}

		this.query = this.schema.getQuery(queryNum);
		ClientStmt clientStmt = new ClientStmt(query);
		Object[] args = { clientStmt };
		Tuple<Error, Object> result = this.cClient.executeCmdWithResult(CompileServer.CMD_DOOMDB_COMPILE, args);
		this.raiseError(result.getObject1());

		this.dplan = (DoomDBPlan) result.getObject2();
		this.dplan.tracePlan();
	}

	@Override
	public DoomDBPlan getPlan() {
		return this.dplan;
	}
	
	@Override
	public String getQuery(){
		return this.query;
	}

	@Override
	public void startQuery() {
		if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}

		this.startTime = System.currentTimeMillis()/1000;
		
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
		
		//get doom plan status and stop if execution error occurred
		Tuple<Error, DoomDBPlanStatus> result = mClient.isDoomDBPlanFinished(dplan
				.getPlanDesc());
		this.raiseError(result.getObject1());
		DoomDBPlanStatus planStatus =  result.getObject2();
		if(planStatus.getError().isError()){
			throw new RuntimeException(planStatus.getError().toString());
		}
		
		//set deployment
		dplan.setDeployment(planStatus.getDeployment());
		dplan.tracePlan();
		
		//measure time if plan has finished
		if(planStatus.isFinished()){
			this.endTime = System.currentTimeMillis() / 1000;
			this.runTime = this.endTime - this.startTime;
			if(this.killedNodes>0)
				this.mtbf = (int)this.runTime * this.getNodeCount() / this.killedNodes;
			Config.writeDoom("DOOMDB_MTBF", ""+this.mtbf);
		}
		return planStatus.isFinished();
	}

	@Override
	public int getNodeCount() {
		if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}
		
		return this.dplan.getComputeNodeCount();
	}

	@Override
	public void killNode(String node) { 
		if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}
		
		ComputeNodeDesc computeNodeDesc = this.dplan.getComputeNode(node);
		this.killNode(computeNodeDesc);
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
