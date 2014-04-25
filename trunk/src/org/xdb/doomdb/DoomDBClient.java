package org.xdb.doomdb;

import java.util.List;
import java.util.Map;

import org.xdb.Config;
import org.xdb.client.CompileClient;
import org.xdb.client.MasterTrackerClient;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.ThreadWithArgs;
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
	private boolean queryRunning = false;
	private boolean clusterRunning = false;
	
	// constructors
	public DoomDBClient(DoomDBClusterDesc clusterDesc) {
		this.clusterDesc = clusterDesc;
	}

	// internal methods
	private void stopOnError(Error err) {
		this.err = err;
		
		if(this.err.isError()){
			this.queryRunning = false;
			throw new RuntimeException(err.toString());
		}
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
	
	public String tracePlan(){
		if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}
		
		return this.dplan.tracePlan();
	}
	
	/**
	 * Kills node
	 * @param nodeDesc
	 */
	public void killNode(ComputeNodeDesc nodeDesc) { 
		this.dplan.killNode(nodeDesc);
		this.killedNodes++;
		
		Object[] args = {nodeDesc};
		ThreadWithArgs runner = new ThreadWithArgs(args) {
			public void run() {
				//System.out.println("DoomDBClient: killNode started!");
				ComputeNodeDesc nodeDesc = (ComputeNodeDesc) this.args[0];
				mClient.restartComputeNode(nodeDesc, DoomDBClient.this.mttr*1000);
				//System.out.println("DoomDBClient: killNode stopped!");
			}
		};
		
		runner.start();
	}

	// interface methods
	@Override
	public synchronized void startDB() {
		if(this.clusterRunning){
			throw new RuntimeException("DB is running already!");
		}
		
		this.err = this.mClient.startDoomDBCluster(clusterDesc);
		this.stopOnError(err);
		this.clusterRunning = true;
	}
	
	@Override
	public synchronized void setSchema(String schemaName) {
		if(this.queryRunning){
			throw new RuntimeException("Query is running already!");
		}
		
		EnumDoomDBSchema schema = EnumDoomDBSchema.enumOf(schemaName);
		Error err = cClient.resetCatalog();
		this.stopOnError(err);

		err = this.executeDDLs(schema.getDDL());
		this.stopOnError(err);

		this.schema = schema;
	}

	@Override
	public synchronized void setQuery(int queryNum) {
		if(this.queryRunning){
			throw new RuntimeException("Query is running already!");
		}
		else if (this.schema == null) {
			throw new RuntimeException("Provide a schema before!");
		}

		this.query = this.schema.getQuery(queryNum); 
		
		Map<Identifier, Double> queryRuntimesStat = this.schema.getQueryRunTimesStat(queryNum); 
		Map<Identifier, Double> queryMattimesStat = this.schema.getQueryMatTimesStat(queryNum); 
		List<Identifier> nonMatOps = this.schema.getNonMaterializableOps(queryNum);
		             
		Tuple<Error, DoomDBPlan> result = this.cClient.compileDoomStmtWithStats(this.query, queryRuntimesStat, queryMattimesStat, nonMatOps);
		this.stopOnError(result.getObject1());
		
		this.dplan = result.getObject2();
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
	public synchronized long getEstimatedTime() {
		if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}
		
		return this.dplan.getEstimatedTime();
	}

	@Override
	public synchronized void startQuery() {
		if(this.queryRunning){
			throw new RuntimeException("Query is running already!");
		}
		else if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}
		
		this.queryRunning = true;
		this.startTime = System.currentTimeMillis()/1000;
		
		Thread runner = new Thread() {
			public void run() {
				//System.out.println("DoomDBClient: startQuery started!");
				Error err = mClient.executeDoomDBPlan(dplan.getPlanDesc());
				DoomDBClient.this.stopOnError(err);
				//System.out.println("DoomDBClient: startQuery stopped!");
			}
		};
		
		runner.start();
		
		while(!runner.isAlive()){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	@Override
	public synchronized void stopQuery() {
		// check if query is running
		if(!this.queryRunning || this.dplan == null) {
			return;
		}
		
		System.err.println("Force stopping query!!!");
		
		// stop query
		this.mClient.stopDoomDBPlan(this.dplan.getPlanDesc());
		
		// restart compute nodes
		int mttr = this.getMTTR();
		this.setMTTR(0);
		for(String nodeDesc: this.dplan.getNodes()){
			this.killNode(nodeDesc);
		}
		this.setMTTR(mttr);
	}
	
	@Override
	public void killNode(String nodeDesc) { 
		if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}
		
		ComputeNodeDesc computeNodeDesc = this.dplan.getComputeNode(nodeDesc);
		this.killNode(computeNodeDesc);
	}
	
	@Override
	public boolean nodeAlive(String nodeDesc) {
		if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}
		
		return this.dplan.nodeAlive(nodeDesc);
	}
	

	@Override
	public synchronized boolean isQueryFinished() {
		if (this.dplan == null) {
			throw new RuntimeException("Provide a query before!");
		}
		
		//get doom plan status and stop if execution error occurred
		Tuple<Error, DoomDBPlanStatus> result = mClient.isDoomDBPlanFinished(dplan
				.getPlanDesc());
		this.stopOnError(result.getObject1());
		DoomDBPlanStatus planStatus =  result.getObject2();
		this.stopOnError(planStatus.getError());
		
		//set deployment
		dplan.setDeployment(planStatus.getDeployment());
		
		//measure time if plan has finished
		if(planStatus.isFinished()){
			this.endTime = System.currentTimeMillis() / 1000;
			this.runTime = this.endTime - this.startTime;
			this.queryRunning = false;
			
			if(this.killedNodes>0 && Config.DOOMDB_MTBF_UPDATE){
				this.mtbf = (int)this.runTime * this.getNodeCount() / this.killedNodes;
				Config.writeDoom("DOOMDB_MTBF", ""+this.mtbf);
			}
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
