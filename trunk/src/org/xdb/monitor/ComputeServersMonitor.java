package org.xdb.monitor;

import java.util.HashMap;
import java.util.Map;

import org.xdb.client.ComputeClient;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.execute.operators.QueryOperatorStatus;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.utils.Identifier; 
import org.xdb.error.Error;

/**
 * Monitor component to monitor all compute servers. 
 * 
 * @author Abdallah
 *
 */
public class ComputeServersMonitor{
       
	
	// Client to ping the compute server.  
	private ComputeClient computeClient; 
	
	// Query tracker plan 
	private QueryTrackerPlan qtPlan; 
    	    
	private Map<Identifier, OperatorDesc> failedComputeNodes = new HashMap<Identifier, OperatorDesc>(); 
	
	private boolean failureDetected = false;
	
	public ComputeServersMonitor(){
		this.computeClient = new ComputeClient();
	}
	

	/**
	 * @return the computeClien
	 */
	public ComputeClient getComputeClient() {
		return computeClient;
	}

	/**
	 * @param computeClient the computeClient to set
	 */
	public void setComputeClient(ComputeClient computeClient) {
		this.computeClient = computeClient;
	}  

	
	/**
	 * @return the failedOperators
	 */
	public Map<Identifier, OperatorDesc> getFailedComputeNodes() {
		return failedComputeNodes;
	}


	/**
	 * @param failedOperators the failedOperators to set
	 */
	public void FailedComputeNodes(Map<Identifier, OperatorDesc> failedComputeNodes) {
		this.failedComputeNodes = failedComputeNodes;
	}
     
	/**
	 * @return the qtPlan
	 */
	public QueryTrackerPlan getQueryTrackerPlan() {
		return qtPlan;
	}


	/**
	 * @param qtPlan the qtPlan to set
	 */
	public void setQueryTrackerPlan(QueryTrackerPlan qtPlan) {
		this.qtPlan = qtPlan;
	}
    
	/**
	 * Start the monitor 
	 */
	public void startComputeServersMonitor(){
		// ping the compute servers 
		pingComputeServers();

	}

	/**
	 * Pinging all the compute nodes existing in the current deployment. 
	 * Giving the proper command to the query tracker server once detecting 
	 * compute node failure.  
	 *  
	 */
	private void pingComputeServers() {
		
		Error err = new Error(); 	
		for (Identifier identifier : qtPlan.getCurrentDeployment().keySet()) { 
			
			OperatorDesc opDesc = qtPlan.getCurrentDeployment().get(identifier);
			err = this.computeClient.pingComputeServer(opDesc.getComputeSlot());  
			if (err.isError()) { 
				// Update the current deployment with the failed operator 
				opDesc.setOperatorStatus(QueryOperatorStatus.ABORTED); 
				setFailureDetected(true);
				
			} else {
				System.out.println(opDesc.getComputeSlot().getUrl() + " has been checked Successfully... ");
			}
			
		} 
		
		
	}


	/**
	 * @return the failureDetected
	 */
	public boolean isFailureDetected() {
		return failureDetected;
	}


	/**
	 * @param failureDetected the failureDetected to set
	 */
	public void setFailureDetected(boolean failureDetected) {
		this.failureDetected = failureDetected;
	}
	
}
