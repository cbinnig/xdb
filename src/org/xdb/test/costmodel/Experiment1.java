package org.xdb.test.costmodel;

import org.xdb.Config;
import org.xdb.doomdb.DoomDBClient;
import org.xdb.doomdb.DoomDBClusterDesc;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.server.MasterTrackerServer;
import org.xdb.test.doomdb.DoomDBFailureSimulator;

public class Experiment1 {
	private DoomDBClient dClient;
	private MasterTrackerServer mTrackerServer;
	
	public void setUp() {
		// Activate monitors
		Config.MASTERTRACKER_MONITOR_ACTIVATED = true;
		Config.QUERYTRACKER_MONITOR_ACTIVATED = true;

		// start master tracker if test is executed locally
		if (Config.TEST_RUN_LOCAL) {
			this.mTrackerServer = new MasterTrackerServer();
			this.mTrackerServer.startServer();
		}
		// wait for compute servers given in cluster specification
		DoomDBClusterDesc clusterDesc = new DoomDBClusterDesc(
				Config.DOOMDB_CLUSTER_SIZE);
		this.dClient = new DoomDBClient(clusterDesc);
		this.dClient.startDB();
	}
	
	
	public void tearDown() {
		if (Config.TEST_RUN_LOCAL) {
			this.mTrackerServer.stopServer();
		}
	}
	
	private void runPlan() {
		DoomDBFailureSimulator doomDBFailureSimulator = new DoomDBFailureSimulator(
				this.dClient);
		
		DoomDBPlan dplan = this.dClient.getPlan();
		dplan.tracePlan();

		// show plan details
		System.out.println("--------------------");
		System.out.println("Query Info: ");
		//System.out.println("\tQuery String: " + this.dClient.getQuery());
		System.out.println("\tEstimated Runtime: " + dplan.getEstimatedTime());
		System.out.println("\tNode count: " + this.dClient.getNodeCount());
		System.out.println("");

		System.out.println("Query Deployment: ");
		for (String opId : dplan.getOperators()) {
			String nodeDesc = dplan.getNodeForOperator(opId);
			System.out.println("\t" + opId + ":" + nodeDesc);
		}
		System.out.println("");

		// execute plan w failures
		System.out.println("Query Execution: ");
		System.out.print("\tRunning ");
		this.dClient.startQuery();
		//doomDBFailureSimulator.start();

		while (!this.dClient.isQueryFinished()) {
			System.out.print(".");
			this.dClient.tracePlan();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// do nothing
			}
		}
		doomDBFailureSimulator.interrupt();

		this.dClient.tracePlan();
		
		System.out.println(" Finished!");
		System.out.println("--------------------");
	}
	
	public static void main(String[] args) {
		// input should be name of the schema, e.g. TPCH_SF10_10P
		if (args.length != 2){
			System.err.println("Too few/many input arguments!");
			System.err.println("input(s):");
			System.err.println("\t schema name (e.g. TPCH_SF10_10P)");
			System.out.println("\t query number (e.g. 5)");
			return;
		}
		String schemaName = args[0];
		int queryNumber = Integer.parseInt(args[1]);
		
		Experiment1 exp1 = new Experiment1();
	
		exp1.setUp();
		exp1.dClient.setSchema(schemaName);
		exp1.dClient.setQuery(queryNumber);
		exp1.runPlan();
		exp1.tearDown();
	}

}
