package org.xdb.test.doomdb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.xdb.Config;
import org.xdb.doomdb.DoomDBClient;
import org.xdb.doomdb.DoomDBClusterDesc;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.server.MasterTrackerServer;
import org.xdb.test.spotgres.NodeTrace;


/**
 * Test case of query execution with simulated failures
 * with partitioned TPC-H schema
 * 
 * @author cbinnig
 * 
 */
public class TestDoomDB extends org.xdb.test.TestCase {
	private boolean qtMonitorActivated = false;
	private boolean mtMonitorActivated = false;
	
	private DoomDBClient dClient;
	private MasterTrackerServer mTrackerServer;

	@Override
	public void setUp() {
		// Activate monitors
		this.qtMonitorActivated = Config.MASTERTRACKER_MONITOR_ACTIVATED;
		this.mtMonitorActivated = Config.QUERYTRACKER_MONITOR_ACTIVATED;
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
		this.dClient.setMTBF(Config.DOOMDB_MTBF);
	}

	@Override
	public void tearDown() {
		if (Config.TEST_RUN_LOCAL) {
			this.mTrackerServer.stopServer();
		}
		
		// set configuration flags back to initial values
		Config.MASTERTRACKER_MONITOR_ACTIVATED = this.mtMonitorActivated;
		Config.QUERYTRACKER_MONITOR_ACTIVATED = this.qtMonitorActivated;
	}

	private void runPlan() {
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
		this.dClient.startQuery();
		
		/*
		// Load the trace file to hand it to the simulator. 
		List<NodeTrace> nodesTraceList; 
		nodesTraceList = loadNodesTrace();
		SpotgresFailureSimulator spotgresFailureSimulator = new SpotgresFailureSimulator(this.dClient, nodesTraceList);
		if(Config.ACTIVATE_FAILURE_SIMULATOR){
			spotgresFailureSimulator.start();
		}
		*/
		
		// start the failure simulator
		DoomDBFailureSimulator doomDBFailureSimulator = new DoomDBFailureSimulator(this.dClient);
		if (Config.ACTIVATE_FAILURE_SIMULATOR) {
			doomDBFailureSimulator.start();
		}
		
		//int i=0;
		while (!this.dClient.isQueryFinished()) {
			System.out.print(".");
			this.dClient.tracePlan();
			
			/*for(String nodeDesc: dplan.getNodes()){
				boolean nodeAlive = this.dClient.nodeAlive(nodeDesc);
				System.out.println(nodeDesc +" alive: "+nodeAlive); 
			}*/
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// do nothing
			}
			//i++;
			
			//if(i==4)
			//	this.dClient.stopQuery();
		}
		
		// terminate the failure simulator
		if (Config.ACTIVATE_FAILURE_SIMULATOR)
			doomDBFailureSimulator.interrupt();

		this.dClient.tracePlan();
		
		System.out.println(" Finished!");
		System.out.println("--------------------");
	}

	
	public void testQ1_SF1_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF1_10P");
		this.dClient.setQuery(1);
		this.runPlan();
	}
	
	public void testQ1_SF01_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF01_10P");
		this.dClient.setQuery(1);
		this.runPlan();
	}
	
	public void testQ1_SF10_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF10_10P");
		this.dClient.setQuery(1);
		this.runPlan();
	}
	
	
	public void testQ1_SF50_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF50_10P");
		this.dClient.setQuery(1);
		this.runPlan();
	}
	
	public void testQ1_SF100_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF100_10P");
		this.dClient.setQuery(1);
		this.runPlan();
	}
	
	/**
	 * Read the trace file of the spot instances 
	 * to use later in the spotgres failure 
	 * simulator 
	 * 
	 */
	@SuppressWarnings("unused")
	private List<NodeTrace> loadNodesTrace() {
		String traceFileName = "./postgres/trace.csv";  
		String line = ""; 
		String splitBy = ","; 
		List<NodeTrace> nodeTraceList = new ArrayList<NodeTrace>();
		try{  
			int lineCounter = 0;
			BufferedReader br = new BufferedReader(new FileReader(traceFileName));  
			while ((line = br.readLine()) != null) {     
				// Read MTBF, the first line int the csv file 
				if(lineCounter == 0){
				 	Config.DOOMDB_MTBF = Integer.parseInt(line.trim()); 
				 	lineCounter++; 
				 	continue;
				} 
				NodeTrace nodeTrace = new NodeTrace();
				String[] csvFileValues  = line.split(splitBy);   
				nodeTrace.setNodeId(Integer.parseInt(csvFileValues[0].trim())); 
				nodeTrace.setFailureTime((int) Long.parseLong(csvFileValues[1].trim()));
				boolean nodeStatus = csvFileValues[2].trim().equalsIgnoreCase("S")? true:false; 
				nodeTrace.setNodeStatus(nodeStatus);
				nodeTraceList.add(nodeTrace);
			}  
			br.close();
		} catch(Exception e){ 
           
		}     
		return nodeTraceList; 
	}

	/** Query 2 **/
	public void testQ2_SF10_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF10_10P");
		this.dClient.setQuery(2);
		this.runPlan();
	}
	
	public void testQ2_SF50_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF50_10P");
		this.dClient.setQuery(2);
		this.runPlan();
	}
	
	/*
	public void testQ2_SF01_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF01_10P");
		this.dClient.setQuery(2);
		this.runPlan();
	}
	
	public void testQ2_SF100_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF100_10P");
		this.dClient.setQuery(2);
		this.runPlan();
	} 
	*/
	
	/** Query 3 **/
	public void testQ3_SF10_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF10_10P");
		this.dClient.setQuery(3);
		this.runPlan();
	}
	
	public void testQ3_SF50_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF50_10P");
		this.dClient.setQuery(3);
		this.runPlan();
	}
	
	/*
	public void testQ3_SF100_10Parts() throws Exception {
	
		this.dClient.setSchema("TPCH_SF100_10P");
		this.dClient.setQuery(3);
		this.runPlan();
	}
	 */
	
	/** Query 5 **/
	public void testQ5_SF10_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF10_10P");
		this.dClient.setQuery(5);
		this.runPlan();
	}
	
	public void testQ5_SF50_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF50_10P");
		this.dClient.setQuery(5);
		this.runPlan(); 
		
		/*
		 * java
		 *  -cp ~/XDB/XDB/lib/xdb.jar:path/to/junit-4.8.2.jar org.xdb.test.doomdb.SingleJUnitTestRunner 
		 * org.xdb.test.doomdb.TestDoomDB#testQ5_SF10_10Parts
		 */
	}
	
	/*
	public void testQ5_SF1_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF1_10P");
		this.dClient.setQuery(5);
		this.runPlan();
	}
	
	public void testQ5_SF01_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF01_10P");
		this.dClient.setQuery(5);
		this.runPlan();
	}
	
	public void testQ5_SF100_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF100_10P");
		this.dClient.setQuery(5);
		this.runPlan();
	}
	*/

	/**	Query 10 **/
	public void testQ10_SF10_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF10_10P");
		this.dClient.setQuery(10);
		this.runPlan(); 
	}
	
	public void testQ10_SF50_10Parts() throws Exception {
		this.dClient.setSchema("TPCH_SF50_10P");
		this.dClient.setQuery(10);
		this.runPlan();
	}
}
