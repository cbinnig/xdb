package org.xdb.test.doomdb;

import org.xdb.Config;
import org.xdb.doomdb.DoomDBClient;
import org.xdb.doomdb.DoomDBClusterDesc;
import org.xdb.doomdb.IDoomDBClient;
import org.xdb.doomdb.IDoomDBPlan;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.server.CompileServer;
import org.xdb.server.ComputeServer;
import org.xdb.server.MasterTrackerServer;
import org.xdb.server.QueryTrackerServer;

/**
 * Test case with partitioned TPC-H schema
 * 
 * @author cbinnig
 * 
 */
public class TestDoomDB extends org.xdb.test.TestCase {
	private IDoomDBClient dClient;
	private MasterTrackerServer mTrackerServer; 
	
	protected CompileServer compileServer;
	private ComputeServer[] computeServers;
	private QueryTrackerServer qTrackerServer;
	private ComputeNodeDesc[] computeNodes;
	private int numberOfComputeServers;
	private boolean runLocal;

	@Override
	public void setUp() {
	    
		DoomDBClusterDesc clusterDesc = new DoomDBClusterDesc(1);
		this.dClient = new DoomDBClient(clusterDesc, 5, 600);
		
		if(Config.TEST_RUN_LOCAL){
			mTrackerServer = new MasterTrackerServer();
			mTrackerServer.startServer(); 
			assertNoError(mTrackerServer.getError());
		}
		
		assertTrue(this.dClient.startDB());
		
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
				+ "and s_nationkey = c_nationkey "
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
		
		// Start DoomDb failure Simulator after starting the query.
		DoomDBFailureSimulator doomDBFailureSimulator = new DoomDBFailureSimulator(this.dClient);  
		doomDBFailureSimulator.start();
		
		while (!this.dClient.isQueryFinished()) {
			System.out.print(".");
			Thread.sleep(500);
		}
		System.out.println(" Finished!");
		System.out.println("--------------------");
	}

	/**
	 * @return the computeServers
	 */
	public ComputeServer[] getComputeServers() {
		return computeServers;
	}

	/**
	 * @param computeServers the computeServers to set
	 */
	public void setComputeServers(ComputeServer[] computeServers) {
		this.computeServers = computeServers;
	}

	/**
	 * @return the qTrackerServer
	 */
	public QueryTrackerServer getqTrackerServer() {
		return qTrackerServer;
	}

	/**
	 * @param qTrackerServer the qTrackerServer to set
	 */
	public void setqTrackerServer(QueryTrackerServer qTrackerServer) {
		this.qTrackerServer = qTrackerServer;
	}

	/**
	 * @return the computeNodes
	 */
	public ComputeNodeDesc[] getComputeNodes() {
		return computeNodes;
	}

	/**
	 * @param computeNodes the computeNodes to set
	 */
	public void setComputeNodes(ComputeNodeDesc[] computeNodes) {
		this.computeNodes = computeNodes;
	}

	/**
	 * @return the numberOfComputeServers
	 */
	public int getNumberOfComputeServers() {
		return numberOfComputeServers;
	}

	/**
	 * @param numberOfComputeServers the numberOfComputeServers to set
	 */
	public void setNumberOfComputeServers(int numberOfComputeServers) {
		this.numberOfComputeServers = numberOfComputeServers;
	}

	/**
	 * @return the runLocal
	 */
	public boolean isRunLocal() {
		return runLocal;
	}

	/**
	 * @param runLocal the runLocal to set
	 */
	public void setRunLocal(boolean runLocal) {
		this.runLocal = runLocal;
	}
}
