package org.xdb.tracker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.Config;
import org.xdb.client.ComputeClient;
import org.xdb.client.QueryTrackerClient;
import org.xdb.doomdb.DoomDBClusterDesc;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.doomdb.DoomDBPlanDesc;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.logging.XDBLog;
import org.xdb.server.CompileServer;
import org.xdb.server.ComputeServer;
import org.xdb.server.QueryTrackerServer;
import org.xdb.utils.Identifier;
import org.xdb.utils.Tuple;

public class MasterTrackerNode {
	/** Compute nodes information **/
	// mapping: URL -> compute node (URL, PORT)
	private final Map<String, List<ComputeNodeDesc>> url2ComputeNodes = Collections
			.synchronizedMap(new HashMap<String, List<ComputeNodeDesc>>());

	// mapping: compute node (URL, PORT) -> Availability
	private final Map<ComputeNodeDesc, Boolean> computeNode2Availability = Collections
			.synchronizedMap(new HashMap<ComputeNodeDesc, Boolean>());

	// last used compute node
	private int lastUsedComputeNode = 0;

	// list: ComputeNodeDesc for round robin assignment
	private final List<ComputeNodeDesc> computeNodes = Collections
			.synchronizedList(new LinkedList<ComputeNodeDesc>());

	// One ComputeClient
	private final ComputeClient computeClient = new ComputeClient();

	/** Query tracker information **/
	// list: QueryTrackerNodeDesc for round robin assignment
	private final List<QueryTrackerNodeDesc> queryTrackerNodes = Collections
			.synchronizedList(new LinkedList<QueryTrackerNodeDesc>());

	// last used query tracker
	private int lastUsedQueryTracker = 0;

	// map: QueryTrackerNodeDesc -> QueryTrackerClient
	private final Map<QueryTrackerNodeDesc, QueryTrackerClient> queryTrackerClients = new HashMap<QueryTrackerNodeDesc, QueryTrackerClient>();

	/** Compile plan information **/
	// map: planId -> running CompilePlan
	private final Map<Identifier, CompilePlan> runningPlans = new HashMap<Identifier, CompilePlan>();

	// map: plan ID -> URL of assigned query tracker
	private final Map<Identifier, QueryTrackerNodeDesc> planAssignment = new HashMap<Identifier, QueryTrackerNodeDesc>();

	/** Helper **/

	// logger
	private final Logger logger;


	/** Monitoring threads **/

	// monitoring thread for compute servers
	private class ComputeNodeMonitor extends Thread {
		@Override
		public void run() {
			while (true) {
				MasterTrackerNode.this.pingComputeNodes();
				try {
					Thread.sleep(Config.MASTERTRACKER_MONITOR_INTERVAL);
				} catch (Exception e) {
				}
			}
		}
	}

	// monitoring thread for compute servers
	private class QueryTrackerMonitor extends Thread {
		@Override
		public void run() {
			while (true) {
				MasterTrackerNode.this.pingQueryTracker();
				try {
					Thread.sleep(Config.MASTERTRACKER_MONITOR_INTERVAL);
				} catch (Exception e) {
				}
			}
		}
	}

	/** Master Tracker **/

	// constructor
	public MasterTrackerNode() {
		logger = XDBLog.getLogger(this.getClass().getName());
	}

	// getters and setters

	public int getRunningPlans() {
		return runningPlans.size();
	}

	public Collection<ComputeNodeDesc> getComputeNodes() {
		return this.computeNodes;
	}

	public int getNoComputeServers() {
		return this.computeNodes.size();
	}

	public Collection<QueryTrackerClient> getQueryTrackerClients() {
		return this.queryTrackerClients.values();
	}

	// methods
	/**
	 * Startup method
	 */
	public void startup() {
		if (Config.MASTERTRACKER_MONITOR_ACTIVATED) {
			ComputeNodeMonitor cMonitor = new ComputeNodeMonitor();
			cMonitor.start();

			QueryTrackerMonitor qMonitor = new QueryTrackerMonitor();
			qMonitor.start();
		}
	}

	/**
	 * Ping compute servers and changes status
	 */
	public void pingComputeNodes() {
		Error err = new Error();
		List<ComputeNodeDesc> computeNodesTmp = new ArrayList<ComputeNodeDesc>(
				this.computeNodes);
		for (ComputeNodeDesc computeNode : computeNodesTmp) {
			synchronized (this) {
				// do not ping if not available
				if (!this.computeNode2Availability.get(computeNode))
					continue;

				// ping and see if any error is returned
				err = computeClient.pingComputeServer(computeNode);
				if (err.isError()) {
					computeNode2Availability.put(computeNode, false);
				}
			}
		}
	}

	/**
	 * Ping query tracker servers and remove if not available
	 */
	public void pingQueryTracker() {
		Error err = new Error();
		List<QueryTrackerNodeDesc> tmpQTrackers = new Vector<QueryTrackerNodeDesc>(
				this.queryTrackerNodes);
		for (QueryTrackerNodeDesc qTracker : tmpQTrackers) {
			synchronized (this) {
				err = this.queryTrackerClients.get(qTracker)
						.pingQueryTrackerServer();
				if (err.isError()) {
					this.queryTrackerClients.remove(qTracker);
					this.queryTrackerNodes.remove(qTracker);
				}
			}
		}
	}

	/**
	 * Determines query tracker and hands over compile plan for execution
	 * 
	 * @param plan
	 * @return
	 */
	public Error executePlan(final CompilePlan plan) {
		// logging
		logger.log(Level.INFO,
				"MasterTracker: Received CompilePlan for execution: " + plan);

		// get query tracker
		Error err = new Error();
		final QueryTrackerNodeDesc qTracker = getAvailableQueryTracker();
		if (qTracker == null) {
			String[] args = { "MasterTracker: No query tracker available!" };
			err = new Error(EnumError.TRACKER_GENERIC, args);
			return err;
		}

		// execute plan on query tracker
		err = this.executeOnQueryTracker(qTracker, plan);
		return err;
	}
	
	/**
	 * Generate query tracker plan for DoomDB without executing it
	 * @param plan
	 * @return
	 */
	public Tuple<Error, DoomDBPlan> generateDoomDBQPlan(final CompilePlan plan) {
		// logging
		logger.log(Level.INFO,
				"MasterTracker: Received CompilePlan for generating DoomDBPlan: " + plan);

		// get query tracker
		Error err = new Error();
		final QueryTrackerNodeDesc qTracker = getAvailableQueryTracker();
		if (qTracker == null) {
			String[] args = { "MasterTracker: No query tracker available!" };
			err = new Error(EnumError.TRACKER_GENERIC, args);
			return new Tuple<Error, DoomDBPlan>(err, new DoomDBPlan());
		}

		// execute plan on query tracker
		return this.generateDoomDBQPlanOnQueryTracker(qTracker, plan);
	}

	/**
	 * Executes query tracker plan for given DoomDBPlan
	 * @param dplanDesc
	 * @return
	 */
	public Error executeDoomDBQPlan(DoomDBPlanDesc dplanDesc){
		Error err = new Error();
		
		if(!this.planAssignment.containsKey(dplanDesc.getCompilePlanId())){
			String[] args = { "MasterTracker: No query tracker available!" };
			err = new Error(EnumError.TRACKER_GENERIC, args);
			return err;
		}
		
		QueryTrackerNodeDesc qTracker = this.planAssignment.get(dplanDesc.getCompilePlanId());
		final QueryTrackerClient qClient = this.queryTrackerClients.get(qTracker);
		
		err = qClient.executeDoomDBQPlan(dplanDesc);
		
		return err;
	}
	
	
	/**
	 * Executes query tracker plan for given DoomDBPlan
	 * @param dplanDesc
	 * @return
	 */
	public Tuple<Error, Boolean> finishedDoomDBQPlan(DoomDBPlanDesc dplanDesc){
		Error err = new Error();
		
		if(!this.planAssignment.containsKey(dplanDesc.getCompilePlanId())){
			String[] args = { "MasterTracker: No query tracker available!" };
			err = new Error(EnumError.TRACKER_GENERIC, args);
			return new Tuple<Error, Boolean>(err, false);
		}
		
		QueryTrackerNodeDesc qTracker = this.planAssignment.get(dplanDesc.getCompilePlanId());
		final QueryTrackerClient qClient = this.queryTrackerClients.get(qTracker);
		
		Tuple<Error, Boolean> result = qClient.finishedDoomDBQPlan(dplanDesc);
		
		return result;
	}
	
	/**
	 * Determines next query tracker using a round-robin scheme
	 * 
	 * @return tracker URL if found - else null
	 */
	public synchronized QueryTrackerNodeDesc getAvailableQueryTracker() {
		if (this.queryTrackerNodes.size() > 0) {
			this.lastUsedQueryTracker++;
			this.lastUsedQueryTracker = (this.lastUsedQueryTracker % this.queryTrackerNodes
					.size());

			return this.queryTrackerNodes.get(this.lastUsedQueryTracker);
		}

		return null;
	}

	/**
	 * Executes compile plan on given query tracker
	 * 
	 * @param tracker
	 * @param plan
	 * @return
	 */
	public Error executeOnQueryTracker(final QueryTrackerNodeDesc tracker,
			final CompilePlan plan) {
		Error err = new Error();
		
		// add plan to monitored plans
		this.runningPlans.put(plan.getPlanId(), plan);
		this.planAssignment.put(plan.getPlanId(), tracker);

		// execute plan using client
		final QueryTrackerClient client = this.queryTrackerClients.get(tracker);
		err = client.executePlan(plan);
		return err;
	}
	
	/**
	 * Starts a doom DB cluster locally on node of master tracker
	 * @param clusterDesc
	 * @return
	 * @throws Exception 
	 */
	public Error startDoomDBCluster(DoomDBClusterDesc clusterDesc) throws Exception{
		Error err = new Error();
		
		CompileServer compilerServer = new CompileServer();
		compilerServer.startServer();
		err = compilerServer.getError();
		if(err.isError()){
			return err;
		}
		
		QueryTrackerServer qtServer = new QueryTrackerServer();
		qtServer.startServer();
		err = qtServer.getError();
		if(err.isError()){
			return err;
		}
		
		int startPort = clusterDesc.getStartPort(); 
		
		// Run doomdb cluster locally 
		if(Config.TEST_RUN_LOCAL) {
			for(int i=0; i<clusterDesc.getNumberOfNodes(); ++i){
				ComputeServer computeServer = new ComputeServer(startPort+i);
				computeServer.startServer();
				err = computeServer.getError();
				if(err.isError()){
					return err;
				}
			} 
		} else {
			// distributed run 
			System.out.print("Waiting for " + clusterDesc.getNumberOfNodes()
					+ " computer server: ");
			int noComputeServers = 0;
			while (noComputeServers < clusterDesc.getNumberOfNodes()) {
				noComputeServers = getNoComputeServers();
				System.out.print(noComputeServers);
				Thread.sleep(1000);
			}
		}
		
		return err;
	}
	
	/**
	 * Generates query tracker plan on query tracker for DoomDB
	 * @param tracker
	 * @param plan
	 * @return
	 */
	public Tuple<Error, DoomDBPlan> generateDoomDBQPlanOnQueryTracker(final QueryTrackerNodeDesc tracker,
			final CompilePlan plan) {
		Error err = new Error();
		
		// add plan to monitored plans
		this.runningPlans.put(plan.getPlanId(), plan);
		this.planAssignment.put(plan.getPlanId(), tracker);

		// execute plan using client
		final QueryTrackerClient client = this.queryTrackerClients.get(tracker);
		Tuple<Error, DoomDBPlan> result = client.generateDoomDBPlan(plan);
		err = result.getObject1();
		DoomDBPlan doomDBPlan = result.getObject2();
		return new Tuple<Error, DoomDBPlan> (err, doomDBPlan);
	}

	/**
	 * Returns a list of compute nodes for a given wish-list of compute nodes
	 * 
	 * @param requestedNodes
	 *            wish-list of compute nodes
	 * @return assigned compute nodes
	 */
	public synchronized Map<String, ComputeNodeDesc> getAvailableComputeNodes(
			final Set<String> requestedNodes) {

		final HashMap<String, ComputeNodeDesc> allocatedNodes = new HashMap<String, ComputeNodeDesc>();
		final HashSet<String> unsatisfiedNodeRequests = new HashSet<String>(
				requestedNodes);

		// assign nodes in wish-list
		for (String requestedNode : requestedNodes) {
			if (this.url2ComputeNodes.containsKey(requestedNode)) {
				for (ComputeNodeDesc computeNode : this.url2ComputeNodes
						.get(requestedNode)) {
					if (this.computeNode2Availability.get(computeNode)) {
						allocatedNodes.put(requestedNode, computeNode);
						unsatisfiedNodeRequests.remove(requestedNode);
						break;
					}
				}
			}
		}

		// assign nodes for unsatisfied requests using round robin assignment
		for (String unsatisfiedNodeRequest : unsatisfiedNodeRequests) {
			this.lastUsedComputeNode++;
			this.lastUsedComputeNode = this.lastUsedComputeNode
					% this.computeNodes.size();
			allocatedNodes.put(unsatisfiedNodeRequest,
					this.computeNodes.get(this.lastUsedComputeNode));
		}
		return allocatedNodes;
	}

	/**
	 * Register a new QueryTrackerNode
	 * 
	 * @param desc
	 * @return
	 */
	public synchronized Error registerQueryTrackerNode(
			final QueryTrackerNodeDesc desc) {
		final Error err = new Error();

		logger.log(Level.INFO, "Registered QueryTracker at MasterTracker: "
				+ desc);
		this.queryTrackerNodes.add(desc);
		this.queryTrackerClients.put(desc,
				new QueryTrackerClient(desc.getUrl()));

		return err;
	}

	/**
	 * Register new compute node for management.
	 * 
	 * @param desc
	 *            ComputeNodeDesc
	 */
	public synchronized Error registerComputeNode(final ComputeNodeDesc desc) {
		final Error err = new Error();
		logger.log(Level.INFO, "Registered ComputeNode at MasterTracker: "
				+ desc);

		if (!computeNode2Availability.containsKey(desc)) {
			List<ComputeNodeDesc> nodesPerUrl = null;
			String host = desc.getUrl();
			if (url2ComputeNodes.containsKey(host)) {
				nodesPerUrl = url2ComputeNodes.get(host);
			} else {
				nodesPerUrl = new LinkedList<ComputeNodeDesc>();
				url2ComputeNodes.put(host, nodesPerUrl);
			}
			nodesPerUrl.add(desc);
			this.computeNodes.add(desc);
		}
		computeNode2Availability.put(desc, true);

		return err;
	}
}
