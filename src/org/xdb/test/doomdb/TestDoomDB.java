package org.xdb.test.doomdb;

import org.xdb.Config;
import org.xdb.doomdb.DoomDBClient;
import org.xdb.doomdb.DoomDBClusterDesc;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.server.MasterTrackerServer;

/**
 * Test case with partitioned TPC-H schema
 * 
 * @author cbinnig
 * 
 */
public class TestDoomDB extends org.xdb.test.TestCase {
	private DoomDBClient dClient;
	private MasterTrackerServer mTrackerServer;

	@Override
	public void setUp() {
		// Activate monitors
		Config.MASTERTRACKER_MONITOR_ACTIVATED = true;
		Config.QUERYTRACKER_MONITOR_ACTIVATED = true;

		// start master tracker if test is executed locally
		if (Config.TEST_RUN_LOCAL) {
			this.mTrackerServer = new MasterTrackerServer();
			this.mTrackerServer.startServer();
			assertNoError(mTrackerServer.getError());
		}
		// wait for compute servers given in cluster specification
		DoomDBClusterDesc clusterDesc = new DoomDBClusterDesc(
				Config.DOOMDB_CLUSTER_SIZE);
		this.dClient = new DoomDBClient(clusterDesc);
		this.dClient.startDB();
	}

	@Override
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
		System.out.println("\tQuery String: " + this.dClient.getQuery());
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
		doomDBFailureSimulator.start();

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

	public void testQ1With10Parts() throws Exception {
		this.dClient.setSchema("TPCH (10 Parts)");
		this.dClient.setQuery(1);
		this.runPlan();
	}
	
	public void testQ5With10Parts() throws Exception {
		this.dClient.setSchema("TPCH (10 Parts)");
		this.dClient.setQuery(5);
		this.runPlan();
	}
	
	public void testQ5With2Parts() throws Exception {
		this.dClient.setSchema("TPCH (2 Parts)");
		this.dClient.setQuery(5);
		this.runPlan();
	}
	
	public void testQ5With1Parts() throws Exception {
		this.dClient.setSchema("TPCH (1 Part)");
		this.dClient.setQuery(5);
		this.runPlan();
	}
}
