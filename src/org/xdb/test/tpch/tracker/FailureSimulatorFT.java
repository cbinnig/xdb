/**
 * 
 */
package org.xdb.test.tpch.tracker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.xdb.Config;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.execute.operators.EnumOperatorStatus;
import org.xdb.metadata.Connection;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.AbstractTrackerOperator;
import org.xdb.utils.Identifier;


/** 
 * Failure simulator class for fault tolerance test. 
 * 
 * @author Abdallah
 *
 */
public class FailureSimulatorFT extends Thread{ 

	private QueryTrackerPlan qPlan;  

	private int numberOfFailures; 

	private List<Connection> connections = new ArrayList<Connection>(); 

	private long queryRunTime;   

	private Map<Identifier, AbstractTrackerOperator> trackerOps;

	public FailureSimulatorFT (int numberOfFailures, QueryTrackerPlan qPlan, List<Connection> connections, long queryRunTime) { 

		this.setNumberOfFailures(numberOfFailures);  
		this.setqPlan(qPlan); 
		this.setConnections(connections); 
		this.setQueryRunTime(queryRunTime); 
		this.setTrackerOps(this.qPlan.getOperatorMapping());	
	}

	@Override
	public void run() { 

		Random randomGenerator = new Random(); 
		try { 
			long failureTime = (long) (randomGenerator.nextDouble()*queryRunTime); 
			System.out.println("Failure occured after: "+failureTime+" from the execution");
			Thread.sleep(failureTime); 
			simulateFailure();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}

	private void simulateFailure() {

		List<Integer> pickedConnections = new ArrayList<Integer>();
		for(int i=0; i < this.numberOfFailures; i++){
			Integer index = pickConnectionIndex(pickedConnections); 
			if(!pickedConnections.contains(index))
				pickedConnections.add(index); 
			else 
				i--;
		}

		killCorrespondingOps(pickedConnections);
	} 

	private void killCorrespondingOps(List<Integer> pickedConnections) {

		Set<Identifier> failedOpsIds = new HashSet<Identifier>(); 
		Set<String> shootedConnections = new HashSet<String>();
		for (Integer index : pickedConnections) {
			Connection conn = this.connections.get(index);  
			String connName = conn.getName(); 
			shootedConnections.add(connName);
		}
		failedOpsIds = getFailedOpsIds(shootedConnections); 
		System.out.println("failed Ops: "+failedOpsIds); 
		setAbortedOperators(failedOpsIds);
	}

	private void setAbortedOperators(Set<Identifier> failedOpsIds) {
		Map<Identifier, OperatorDesc> currentDeployment = this.qPlan.getCurrentDeployment();  

		for (Identifier identifier : failedOpsIds) {
			OperatorDesc opDesc = currentDeployment.get(identifier); 
			opDesc.setOperatorStatus(EnumOperatorStatus.ABORTED);
		}

	}

	private Set<Identifier> getFailedOpsIds(Set<String> shootedConnections) {

		Set<Identifier> trackerOpIds = trackerOps.keySet();  

		Set<Identifier> failedOpsIds = new HashSet<Identifier>(); 

		for (Identifier identifier : trackerOpIds) {
			AbstractTrackerOperator trackerOps = this.trackerOps.get(identifier); 
			if(shootedConnections.contains(trackerOps.getTrackerOpConnections().get(0).getName())) 
				failedOpsIds.add(identifier);

		}
		return failedOpsIds;
	}

	private Integer pickConnectionIndex(List<Integer> pickedConnections) {

		Integer index; 
		// if we have only one connection 
		// choose it regardless the checkpointing 
		// condition. 
		if(this.connections.size() == 1) {
			return 0;
		}
		
		Random randomGenarator = new Random(); 
		// if checkpointing is enabled, then exclude the 
		// first connection from shooting 

		if(Config.TEST_FT_CHECKPOINTING) {
			index = randomGenarator.nextInt(this.connections.size()-1)+1; 

		}
		else {
			index = randomGenarator.nextInt(this.connections.size());  
		}
		return index;
	}

	/**
	 * @return the qPlan
	 */
	public QueryTrackerPlan getqPlan() {
		return qPlan;
	}

	/**
	 * @param qPlan the qPlan to set
	 */
	public void setqPlan(QueryTrackerPlan qPlan) {
		this.qPlan = qPlan;
	}

	/**
	 * @return the numberOfFailures
	 */
	public int getNumberOfFailures() {
		return numberOfFailures;
	}

	/**
	 * @param numberOfFailures the numberOfFailures to set
	 */
	public void setNumberOfFailures(int numberOfFailures) {
		this.numberOfFailures = numberOfFailures;
	}

	/**
	 * @return the connections
	 */
	public List<Connection> getConnections() {
		return connections;
	}

	/**
	 * @param connections the connections to set
	 */
	public void setConnections(List<Connection> connections) {
		this.connections = connections;
	}

	/**
	 * @return the queryRunTime
	 */
	public long getQueryRunTime() {
		return queryRunTime;
	}

	/**
	 * @param queryRunTime the queryRunTime to set
	 */
	public void setQueryRunTime(long queryRunTime) {
		this.queryRunTime = queryRunTime;
	}

	/**
	 * @return the trackerOps
	 */
	public Map<Identifier, AbstractTrackerOperator> getTrackerOps() {
		return trackerOps;
	}

	/**
	 * @param trackerOps the trackerOps to set
	 */
	public void setTrackerOps(Map<Identifier, AbstractTrackerOperator> trackerOps) {
		this.trackerOps = trackerOps;
	} 

}
