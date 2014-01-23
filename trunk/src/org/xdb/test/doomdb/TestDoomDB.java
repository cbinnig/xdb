package org.xdb.test.doomdb;

import org.xdb.Config;
import org.xdb.doomdb.DoomDBClient;
import org.xdb.doomdb.DoomDBClusterDesc;
import org.xdb.doomdb.IDoomDBClient;
import org.xdb.doomdb.IDoomDBPlan;
import org.xdb.server.MasterTrackerServer;

/**
 * Test case with partitioned TPC-H schema
 * 
 * @author cbinnig
 * 
 */
public class TestDoomDB extends org.xdb.test.TestCase {
	private IDoomDBClient dClient;
	private MasterTrackerServer mTrackerServer;

	@Override
	public void setUp() {
		DoomDBClusterDesc clusterDesc = new DoomDBClusterDesc(2);
		this.dClient = new DoomDBClient(clusterDesc);
		this.dClient.setMTTR(100);
		
		if(Config.TEST_RUN_LOCAL){
			mTrackerServer = new MasterTrackerServer();
			mTrackerServer.startServer();
			assertNoError(mTrackerServer.getError());
			assertTrue(this.dClient.startDB());
		}
		else{
			System.out.print("Waiting for master tracker to start up!");
			while(!this.dClient.startDB()){
				System.out.print(".");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					//Do nothing
				}
			}
			System.out.println("");
		}
	}
	
	@Override
	public void tearDown() {
		if(Config.TEST_RUN_LOCAL)
			mTrackerServer.stopServer();
	} 

	public void testWith2Levels() throws Exception {
		this.dClient.setSchema("TPCH (2 Parts)");

		String q = "Select n_nationkey, "
				+ "sum(l_extendedprice * (1-l_discount)) as revenue, "
				+ "avg(l_extendedprice * (1-l_discount)) as avgrevenue "
				+ "from customer, orders, lineitem, supplier, nation, region "
				+ "where c_custkey = o_custkey "
				+ "and l_orderkey = o_orderkey "
				+ "and l_suppkey = s_suppkey  "
				+ "and n_nationkey = s_nationkey "
				+ "and r_regionkey = n_regionkey "
				+ "and s_nationkey = c_nationkey " + "and r_name = 'ASIA' "
				+ "and o_orderdate > date '1994-01-01' "
				+ "and o_orderdate < date '1995-01-01' "
				+ "group by n_nationkey;";

		this.dClient.setQuery(q);
		IDoomDBPlan dplan = this.dClient.getPlan();
		dplan.tracePlan();

		System.out.println("--------------------");
		System.out.println("Query Info: ");
		System.out.println("\tQuery String: " + q);
		System.out.println("\tEstimated Runtime: "
				+ this.dClient.getEstimatedTime());
		System.out.println("\tNode count: " + this.dClient.getNodeCount());
		System.out.println("");

		System.out.println("Query Deployment: ");
		for (String opId : dplan.getOperators()) {
			String nodeDesc = this.dClient.getNode(opId);
			System.out.println("\t" + opId + ":" + nodeDesc);
		}
		System.out.println("");

		System.out.println("Query Execution: ");
		System.out.print("\tRunning ");
		this.dClient.startQuery();
		while (!this.dClient.isQueryFinished()) {
			System.out.print(".");
			Thread.sleep(500);
		}
		System.out.println(" Finished!");
		System.out.println("--------------------");
	}
}
