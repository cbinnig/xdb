package org.xdb.tracker;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.xdb.client.ComputeClient;
import org.xdb.error.Error;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.operator.AbstractOperator;
import org.xdb.utils.Identifier;

public class ExecutionPlan implements Serializable {

	private static final long serialVersionUID = -5521482252707107847L;

	// deployment operator id
	private Integer lastExecOperId = 1;

	// unique operator id
	private Identifier planId;

	// query tracker node and compute client
	private QueryTrackerNode tracker;
	private ComputeClient computeClient;

	// plan info
	private HashMap<Identifier, AbstractOperator> operators = new HashMap<Identifier, AbstractOperator>();
	private HashMap<Identifier, Set<Identifier>> consumers = new HashMap<Identifier, Set<Identifier>>();
	private HashMap<Identifier, Set<Identifier>> sources = new HashMap<Identifier, Set<Identifier>>();
	private HashSet<Identifier> roots = new HashSet<Identifier>();
	private HashSet<Identifier> leaves = new HashSet<Identifier>();

	// deployment info
	private HashMap<Identifier, Set<OperatorDesc>> deployment = new HashMap<Identifier, Set<OperatorDesc>>();

	// last error
	private Error err = Error.NO_ERROR;

	// constructor
	public ExecutionPlan(QueryTrackerNode tracker, Identifier planId) {
		this.planId = planId;
		this.tracker = tracker;
		this.computeClient = tracker.getComputeClient();
	}

	// getter and setter
	public Identifier getPlanId() {
		return this.planId;
	}

	public void addOperator(AbstractOperator operator, Set<Identifier> sources,
			Set<Identifier> consumers) {
		Identifier operId = operator.getOperatorId();

		this.operators.put(operId, operator);
		this.sources.put(operId, sources);
		this.consumers.put(operId, consumers);

		if (sources == null || sources.isEmpty()) {
			this.leaves.add(operator.getOperatorId());
		}

		if (consumers == null || consumers.isEmpty()) {
			this.roots.add(operator.getOperatorId());
		}
	}

	// methods
	public Error deployPlan() {
		HashMap<Identifier, OperatorDesc> currentDeployment = new HashMap<Identifier, OperatorDesc>();

		// prepare deployment
		for (Identifier leave : this.leaves) {
			prepareDeployment(leave, currentDeployment);
		}

		// deploy plan
		deployPlan(currentDeployment);

		// handle error
		if (err.isError()) {
			for (Entry<Identifier, OperatorDesc> entry : currentDeployment
					.entrySet()) {
				Identifier operId = entry.getKey();
				OperatorDesc deployOperDesc = entry.getValue();
				this.computeClient.closeOperator(
						deployOperDesc.getOperatorNode(), operId);
			}
		}

		return this.err;
	}

	private void deployPlan(Map<Identifier, OperatorDesc> currentDeployment) {
		// deploy current deployment
		for (Entry<Identifier, OperatorDesc> entry : currentDeployment
				.entrySet()) {
			Identifier operId = entry.getKey();
			OperatorDesc deployOperDesc = entry.getValue();
			AbstractOperator oper = this.operators.get(operId);

			// create executable operator and set consumers / sources
			org.xdb.execute.operators.AbstractOperator deployOper = oper
					.genDeployOperator(deployOperDesc);
			
			for(Identifier consumerId: this.consumers.get(operId)){
				OperatorDesc consumerDesc = currentDeployment.get(consumerId);
				deployOper.addConsumer(consumerDesc);
			}
			
			for(Identifier sourceId: this.sources.get(operId)){
				OperatorDesc sourceDesc = currentDeployment.get(sourceId);
				deployOper.addConsumer(sourceDesc);
			}
			
			// deploy operator
			this.err = computeClient.prepareOperator(
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
	 * prepares deployment for a given operator in plan
	 * 
	 * @param operId
	 * @param currentDeployment
	 */
	private void prepareDeployment(Identifier operId,
			Map<Identifier, OperatorDesc> currentDeployment) {
		// operator already deployed
		if (currentDeployment.containsKey(operId))
			return;

		// Generate deployment operator
		Identifier deployOperId = operId.clone();
		deployOperId.append(this.lastExecOperId++);
		OperatorDesc deployOperDesc = new OperatorDesc(deployOperId,
				tracker.getFreeNode());

		// add to current deployment info
		currentDeployment.put(operId, deployOperDesc);
		
		// deploy consumers
		for(Identifier consumerId: this.consumers.get(operId)){
			prepareDeployment(consumerId, currentDeployment);
		}
	}
}
