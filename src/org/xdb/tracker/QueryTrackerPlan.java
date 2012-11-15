package org.xdb.tracker;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.Config;
import org.xdb.client.ComputeClient;
import org.xdb.error.Error;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.logging.XDBLog;
import org.xdb.tracker.operator.AbstractOperator;
import org.xdb.tracker.operator.MySQLOperator;
import org.xdb.tracker.operator.TableDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.MutableInteger;
import org.xdb.utils.StringTemplate;
import org.xdb.utils.Tuple;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphFactory;
import com.oy.shared.lm.graph.GraphNode;
import com.oy.shared.lm.out.GRAPHtoDOTtoGIF;

public class QueryTrackerPlan implements Serializable {

	private static final long serialVersionUID = -5521482252707107847L;

	// last deployment operator id
	private Integer lastDeployOperId = 1;

	// unique operator id
	private final Identifier planId;

	// assigned query tracker node and compute client
	private QueryTrackerNode tracker = null;
	private ComputeClient computeClient = null;

	// plan info
	private final HashMap<Identifier, AbstractOperator> operators = new HashMap<Identifier, AbstractOperator>();
	private final HashMap<Identifier, Set<Identifier>> consumers = new HashMap<Identifier, Set<Identifier>>();
	private final HashMap<Identifier, Set<Identifier>> sources = new HashMap<Identifier, Set<Identifier>>();
	private final HashSet<Identifier> roots = new HashSet<Identifier>();
	private final HashSet<Identifier> leaves = new HashSet<Identifier>();
	private HashMap<String, MutableInteger> slots;
	private final HashMap<Identifier, List<String>> nodeOperators = new HashMap<Identifier, List<String>>();

	// deployment info
	private final HashMap<Identifier, Set<OperatorDesc>> deployment = new HashMap<Identifier, Set<OperatorDesc>>();

	// last error
	private Error err = new Error();

	//Logger
	private final Logger logger;

	private HashMap<Identifier, OperatorDesc> currentDeployment;

	// constructor
	public QueryTrackerPlan(final Identifier planId) {
		this.planId = planId;
		logger = XDBLog.getLogger(this.getClass().getName());
	}

	public void addNodeOperator(final String node, final Identifier opId) {
		if(!nodeOperators.containsKey(opId)) {
			final List<String> ops = new LinkedList<String>();
			ops.add(node);
			nodeOperators.put(opId, ops);
		} else {
			nodeOperators.get(opId).add(node);
		}
	}

	public Map<Identifier, List<String>> getNumNodeOperators() {
		return nodeOperators;
	}

	// getter and setter
	public Identifier getPlanId() {
		return planId;
	}

	public Collection<AbstractOperator> getOperators() {
		return operators.values();
	}

	public HashSet<Identifier> getRoots() {
		return roots;
	}

	public Error getLastError() {
		return err;
	}

	public void assignTracker(final QueryTrackerNode tracker) {
		this.tracker = tracker;
		computeClient = tracker.getComputeClient();
	}

	/**
	 * Adds operator to plan and generates a plan operator id: PLAN_ID+"_"+PLAN_OPER_ID
	 * @param operator
	 * @param sources
	 * @param consumers
	 * @return
	 */
	public void addOperator(final AbstractOperator operator, final Set<Identifier> sources,
			final Set<Identifier> consumers) {
		final Identifier operId = operator.getOperatorId();
		operators.put(operId, operator);
		this.sources.put(operId, sources);
		this.consumers.put(operId, consumers);

		if ( sources.isEmpty()) {
			leaves.add(operator.getOperatorId());
		}

		if ( consumers.isEmpty()) {
			roots.add(operator.getOperatorId());
			operator.setIsRoot(true);
		}
	}

	// methods

	/**
	 * Removes result tables of root nodes
	 * @param currentDeployment
	 */
	public void cleanPlan(final Map<Identifier, OperatorDesc> currentDeployment) {
		//close operators which are not root operators
		for(final Entry<Identifier, OperatorDesc> entry : currentDeployment.entrySet()){
			final AbstractOperator planOp = operators.get(entry.getKey());
			if(planOp.isRoot()){
				final OperatorDesc operDesc = entry.getValue();
				err = computeClient.closeOperator(operDesc);

				if(err.isError()) {
					break;
				}
			}
		}
	}

	/**
	 * Executes a plan using a given deployment description
	 * @param currentDeployment
	 */
	public void executePlan(final Map<Identifier, OperatorDesc> currentDeployment) {
		if (err.isError()) {
			return;
		}


		//start execution on leave operators
		for (final Identifier leaveId : leaves) {
			final OperatorDesc leaveDesc = currentDeployment.get(leaveId);
			err = computeClient.executeOperator(leaveDesc);

			if(err.isError()) {
				break;
			}
		}


		//close operators which are not root operators
		for(final Entry<Identifier, OperatorDesc> entry : currentDeployment.entrySet()){
			final AbstractOperator planOp = operators.get(entry.getKey());
			if(!planOp.isRoot()){
				final OperatorDesc operDesc = entry.getValue();
				err = computeClient.closeOperator(operDesc);

				if(err.isError()) {
					break;
				}
			}
		}

	}

	/**
	 * Deploys the given plan and creates a deployment description
	 * @return
	 */
	public Map<Identifier, OperatorDesc> deployPlan() {
		final HashMap<Identifier, OperatorDesc> currentDeployment = new HashMap<Identifier, OperatorDesc>();

		requireSlots();

		// prepare deployment
		for (final Identifier leave : leaves) {
			prepareDeployment(leave, currentDeployment, slots);
		}

		// deploy plan
		deployPlan(currentDeployment);

		// handle error
		if (err.isError()) {
			for (final OperatorDesc deployOperDesc: currentDeployment.values()) {
				computeClient.closeOperator(deployOperDesc);
			}
			currentDeployment.clear();
		}

		this.currentDeployment = currentDeployment;
		return currentDeployment;
	}

	private void requireSlots() {
		final ResourceScheduler resourceScheduler = new ResourceScheduler(this);
		final MutableInteger numSlots = new MutableInteger(resourceScheduler.calcMaxParallelization());

		final Map<String, MutableInteger> requiredSlots = new HashMap<String, MutableInteger>();
		for (final Identifier leaf : leaves) {
			if (numSlots.intValue() == 0) {
				break;
			}
			final AbstractOperator op = operators.get(leaf);
			// Gather best Connection String 
			final String connectionString = resourceScheduler.getBestConnection(getNumNodeOperators().get(op.getOperatorId()));
			final MutableInteger numNodes = requiredSlots.get(connectionString);
			if (numNodes == null) {
				requiredSlots.put(connectionString, new MutableInteger(1));
			} else {
				numNodes.inc();
			}
			numSlots.dec();
		}
		if (numSlots.intValue() > 0) {
			requiredSlots.put(ResourceScheduler.RANDOM, numSlots);
		}
		final Tuple<Map<String, MutableInteger>, Error> tuple = tracker.requestComputeNodes(requiredSlots);
		final Map<String, MutableInteger> allocatedSlots = tuple.getObject1();
		err = tuple.getObject2();
		slots = new HashMap<String, MutableInteger>(allocatedSlots);
	}

	/**
	 * Deploys plan using a given deployment description
	 * @param currentDeployment
	 */
	private void deployPlan(final Map<Identifier, OperatorDesc> currentDeployment) {
		for (final Entry<Identifier, OperatorDesc> entry : currentDeployment
				.entrySet()) {
			final Identifier operId = entry.getKey();
			final OperatorDesc deployOperDesc = entry.getValue();
			final AbstractOperator oper = operators.get(operId);

			// create executable operator and set consumers / sources
			final org.xdb.execute.operators.AbstractOperator deployOper = oper
					.genDeployOperator(deployOperDesc, currentDeployment);

			logger.log(Level.INFO, "Deploy operator '" + deployOper.getOperatorId() + "' for plan operator '"+operId+"'");

			for (final Identifier consumerId : consumers.get(operId)) {
				final OperatorDesc consumerDesc = currentDeployment.get(consumerId);
				deployOper.addConsumer(consumerDesc);
			}

			for (final Identifier sourceId : sources.get(operId)) {
				final OperatorDesc sourceDesc = currentDeployment.get(sourceId);
				deployOper.addSource(sourceDesc);
			}

			// deploy operator
			err = computeClient.openOperator(
					deployOperDesc.getOperatorNode(), deployOper);
			if (err.isError()) {
				return;
			}

			// add info to deployments
			Set<OperatorDesc> deployementDesc = deployment.get(operId);
			if (deployementDesc == null) {
				deployementDesc = new HashSet<OperatorDesc>();
				deployment.put(operId, deployementDesc);
			}
			deployementDesc.add(deployOperDesc);
		}
	}

	/**
	 * Prepares deployment for a given operator in plan
	 * 
	 * @param operId
	 * @param currentDeployment
	 */
	private void prepareDeployment(final Identifier operId,
			final Map<Identifier, OperatorDesc> currentDeployment, final Map<String, MutableInteger> allocatedSlots) {

		// operator already deployed
		if (currentDeployment.containsKey(operId)) {
			return;
		}

		// identify best used node
		final Collection<String> bestNodes = getNumNodeOperators().get(operId);
		String usedNode = null;
		if (bestNodes != null) {
			for (final String bestNode : bestNodes) {	// Choose one of the possible connection strings
				final MutableInteger numOfFreeNodes = allocatedSlots.get(bestNode);
				if (numOfFreeNodes != null && numOfFreeNodes.intValue() > 0) {
					numOfFreeNodes.dec();
					usedNode = bestNode;
				}
			}
		} 
		if (usedNode == null) {
			// TODO: Get next best ComputeNode
			int maxSlots = 0;
			for (final Entry<String, MutableInteger> availableNode : allocatedSlots.entrySet()) {
				if (maxSlots < availableNode.getValue().intValue()) {
					usedNode = availableNode.getKey();
					maxSlots = availableNode.getValue().intValue();
				}
			}
			final MutableInteger numOfFreeNodes = allocatedSlots.get(usedNode);
			numOfFreeNodes.dec();
		}


		// generate deployment description from plan operator
		final Identifier deployOperId = operId.clone();
		deployOperId.append(lastDeployOperId++);
		final OperatorDesc deployOperDesc = new OperatorDesc(deployOperId,
				usedNode);

		// add to current deployment description
		currentDeployment.put(operId, deployOperDesc);

		// prepare deployment of consumers
		for (final Identifier consumerId : consumers.get(operId)) {
			prepareDeployment(consumerId, currentDeployment, allocatedSlots);
		}
	}

	public Set<Identifier> getLeaves() {
		return Collections.unmodifiableSet(leaves);
	}
	public Map<Identifier, AbstractOperator> getOperatorMapping() {
		return Collections.unmodifiableMap(operators);
	}

	public void execute() {
		executePlan(currentDeployment);
	}

	/**
	 * Generates a visual graph representation of the compile plan
	 */
	public Error traceGraph(String fileName){
		fileName += planId;
		final Error error =new Error();
		final Graph graph = GraphFactory.newGraph();

		final HashMap<Identifier, GraphNode> nodes = new HashMap<Identifier, GraphNode>();

		final Queue<Identifier> assemblingQueue = new LinkedList<Identifier>();
		assemblingQueue.addAll(roots);


		while(!assemblingQueue.isEmpty()) {
			final AbstractOperator rootOp = operators.get(assemblingQueue.poll());
			final GraphNode node = graph.addNode();

			for(final Identifier parent : sources.get(rootOp.getOperatorId())) {
				graph.addEdge(nodes.get(parent), node);
			}			

			final StringBuilder caption = new StringBuilder();
			for(final StringTemplate outTable : rootOp.getOutTables()) {
				caption.append(outTable.toString()+"\n");
			}
			node.getInfo().setHeader(caption.toString());

			if(rootOp instanceof MySQLOperator) {
				final MySQLOperator myOp = (MySQLOperator)rootOp;
				final StringBuilder body = new StringBuilder();
				body.append("MySQL-Operator: " + myOp.getOperatorId().toString()+"\n\n");
				for(final StringTemplate exStmt : myOp.getExecuteSQLs()) {
					body.append(exStmt.toString()+"\n");
				}
				node.getInfo().setCaption("\n\n"+body.toString()+"\n\n");


				final StringBuilder footer = new StringBuilder();

				if(myOp.getInTables().size() > 0) {
					footer.append("Input:\n");
					for(final Entry<String, StringTemplate> e : myOp.getInTables().entrySet()) {
						footer.append(e.getKey() + ":\n  DDL: "+e.getValue().toString()+"\n");
						final TableDesc desc = myOp.getInTableSource().get(e.getKey());
						if(desc != null) {
							footer.append("Operator: " + desc.getOperatorID().toString()+"\n");
						}
					}
					footer.append("\n\n");
				}

				node.getInfo().setFooter(footer.toString());
			}


			nodes.put(rootOp.getOperatorId(), node);

			assemblingQueue.addAll(consumers.get(rootOp.getOperatorId()));
		}

		for(final Identifier opId : operators.keySet()) {
			if(!nodes.containsKey(opId)) {
				logger.info("Got unreachable operator"+opId);
			}
		}

		// output graph to *.gif
		final String path = Config.DOT_TRACE_PATH;
		final String dotFileName = path + fileName + ".dot";
		final String gifFileName = path + fileName + ".gif";
		final String exeFileName = Config.DOT_EXE;
		try {
			GRAPHtoDOTtoGIF.transform( graph, dotFileName, gifFileName, exeFileName);
		} catch (final IOException e) {
			return FunSQLCompiler.createGenericCompileErr(e.getMessage());
		}
		return error;
	}

	public HashMap<String, MutableInteger> getSlots() {
		return slots;
	}

}
