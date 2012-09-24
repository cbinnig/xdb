package org.xdb.tracker;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Set;

import org.xdb.client.ComputeClient;
import org.xdb.error.Error;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.logging.XDBLog;
import org.xdb.tracker.operator.AbstractOperator;
import org.xdb.utils.Identifier;

public class QueryTrackerPlan implements Serializable {

	private static final long serialVersionUID = -5521482252707107847L;

	// last deployment operator id
	private Integer lastDeployOperId = 1;

	// unique operator id
	private Identifier planId;

	// assigned query tracker node and compute client
	private QueryTrackerNode tracker = null;
	private ComputeClient computeClient = null;

	// plan info
	private HashMap<Identifier, AbstractOperator> operators = new HashMap<Identifier, AbstractOperator>();
	private HashMap<Identifier, Set<Identifier>> consumers = new HashMap<Identifier, Set<Identifier>>();
	private HashMap<Identifier, Set<Identifier>> sources = new HashMap<Identifier, Set<Identifier>>();
	private HashSet<Identifier> roots = new HashSet<Identifier>();
	private HashSet<Identifier> leaves = new HashSet<Identifier>();

	// deployment info
	private HashMap<Identifier, Set<OperatorDesc>> deployment = new HashMap<Identifier, Set<OperatorDesc>>();

	// last error
	private Error err = new Error();
	
	//Logger
	private Logger logger;

	// constructor
	public QueryTrackerPlan(Identifier planId) {
		this.planId = planId;
		this.logger = XDBLog.getLogger(this.getClass().getName());
	}

	// getter and setter
	public Identifier getPlanId() {
		return this.planId;
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
	
	public void assignTracker(QueryTrackerNode tracker) {
		this.tracker = tracker;
		this.computeClient = tracker.getComputeClient();
	}

	/**
	 * Adds operator to plan and generates a plan operator id: PLAN_ID+"_"+PLAN_OPER_ID
	 * @param operator
	 * @param sources
	 * @param consumers
	 * @return
	 */
	public void addOperator(AbstractOperator operator, Set<Identifier> sources,
			Set<Identifier> consumers) {
		Identifier operId = operator.getOperatorId();
		this.operators.put(operId, operator);
		this.sources.put(operId, sources);
		this.consumers.put(operId, consumers);

		if ( sources.isEmpty()) {
			this.leaves.add(operator.getOperatorId());
		}

		if ( consumers.isEmpty()) {
			this.roots.add(operator.getOperatorId());
			operator.setIsRoot(true);
		}
	}

	// methods
	
	/**
	 * Removes result tables of root nodes
	 * @param currentDeployment
	 */
	public void cleanPlan(Map<Identifier, OperatorDesc> currentDeployment) {
		//close operators which are not root operators
		for(Entry<Identifier, OperatorDesc> entry : currentDeployment.entrySet()){
			AbstractOperator planOp = this.operators.get(entry.getKey());
			if(planOp.isRoot()){
				OperatorDesc operDesc = entry.getValue();
				this.err = this.computeClient.closeOperator(operDesc);
				
				if(this.err.isError())
					break;
			}
		}
	}
	
	/**
	 * Executes a plan using a given deployment description
	 * @param currentDeployment
	 */
	public void executePlan(Map<Identifier, OperatorDesc> currentDeployment) {
		if (this.err.isError())
			return;

		//start execution on leave operators
		for (Identifier leaveId : this.leaves) {
			OperatorDesc leaveDesc = currentDeployment.get(leaveId);
			this.err = this.computeClient.executeOperator(leaveDesc);
			
			if(this.err.isError())
				break;
		}
		
		
		//close operators which are not root operators
		for(Entry<Identifier, OperatorDesc> entry : currentDeployment.entrySet()){
			AbstractOperator planOp = this.operators.get(entry.getKey());
			if(!planOp.isRoot()){
				OperatorDesc operDesc = entry.getValue();
				this.err = this.computeClient.closeOperator(operDesc);
				
				if(this.err.isError())
					break;
			}
		}
	}

	/**
	 * Deploys the given plan and creates a deployment description
	 * @return
	 */
	public Map<Identifier, OperatorDesc> deployPlan() {
		HashMap<Identifier, OperatorDesc> currentDeployment = new HashMap<Identifier, OperatorDesc>();

		// prepare deployment
		for (Identifier leave : this.leaves) {
			prepareDeployment(leave, currentDeployment);
		}

		// deploy plan
		deployPlan(currentDeployment);

		// handle error
		if (err.isError()) {
			for (OperatorDesc deployOperDesc: currentDeployment.values()) {
				this.computeClient.closeOperator(deployOperDesc);
			}
			currentDeployment.clear();
		}

		return currentDeployment;
	}

	/**
	 * Deploys plan using a given deployment description
	 * @param currentDeployment
	 */
	private void deployPlan(Map<Identifier, OperatorDesc> currentDeployment) {
		for (Entry<Identifier, OperatorDesc> entry : currentDeployment
				.entrySet()) {
			Identifier operId = entry.getKey();
			OperatorDesc deployOperDesc = entry.getValue();
			AbstractOperator oper = this.operators.get(operId);

			// create executable operator and set consumers / sources
			org.xdb.execute.operators.AbstractOperator deployOper = oper
					.genDeployOperator(deployOperDesc, currentDeployment);

			logger.log(Level.INFO, "Deploy operator '" + deployOper.getOperatorId() + "' for plan operator '"+operId+"'");
			
			for (Identifier consumerId : this.consumers.get(operId)) {
				OperatorDesc consumerDesc = currentDeployment.get(consumerId);
				deployOper.addConsumer(consumerDesc);
			}

			for (Identifier sourceId : this.sources.get(operId)) {
				OperatorDesc sourceDesc = currentDeployment.get(sourceId);
				deployOper.addSource(sourceDesc);
			}

			// deploy operator
			this.err = computeClient.openOperator(
					deployOperDesc.getOperatorNode(), deployOper);
			if (this.err.isError())
				return;

			// add info to deployments
			Set<OperatorDesc> deployementDesc = this.deployment.get(operId);
			if (deployementDesc == null) {
				deployementDesc = new HashSet<OperatorDesc>();
				this.deployment.put(operId, deployementDesc);
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
	private void prepareDeployment(Identifier operId,
			Map<Identifier, OperatorDesc> currentDeployment) {
		
		// operator already deployed
		if (currentDeployment.containsKey(operId))
			return;

		// generate deployment description from plan operator
		Identifier deployOperId = operId.clone();
		deployOperId.append(this.lastDeployOperId++);
		OperatorDesc deployOperDesc = new OperatorDesc(deployOperId,
				tracker.getFreeNode());

		// add to current deployment description
		currentDeployment.put(operId, deployOperDesc);

		// prepare deployment of consumers
		for (Identifier consumerId : this.consumers.get(operId)) {
			prepareDeployment(consumerId, currentDeployment);
		}
	}
}
