package org.xdb.monitor;

import org.xdb.client.ComputeClient;
import org.xdb.error.Error;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.execute.operators.EnumOperatorStatus;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.utils.Identifier;

/**
 * Monitor component to monitor all compute servers.
 * 
 * @author Abdallah
 * 
 */
public class ComputeServersMonitor {

	// Client to ping the compute server.
	private ComputeClient computeClient;

	// Query tracker plan
	private QueryTrackerPlan qtPlan;

	private boolean failureDetected = false;

	public ComputeServersMonitor(QueryTrackerPlan qtPlan) {
		this.computeClient = new ComputeClient();
		this.qtPlan = qtPlan;
	}

	/**
	 * @return the computeClien
	 */
	public ComputeClient getComputeClient() {
		return computeClient;
	}

	/**
	 * @param computeClient
	 *            the computeClient to set
	 */
	public void setComputeClient(ComputeClient computeClient) {
		this.computeClient = computeClient;
	}

	/**
	 * @return the failureDetected
	 */
	public boolean hasDetectedFailure() {
		return failureDetected;
	}

	/**
	 * @param failureDetected
	 *            the failureDetected to set
	 */
	public void setFailureDetected(boolean failureDetected) {
		this.failureDetected = failureDetected;
	}

	/**
	 * Pinging all the compute nodes existing in the current deployment. Giving
	 * the proper command to the query tracker server once detecting compute
	 * node failure.
	 * 
	 */
	public void monitorAllOperators() {

		Error err = new Error();
		for (Identifier identifier : this.qtPlan.getCurrentDeployment()
				.keySet()) {

			OperatorDesc opDesc = this.qtPlan.getCurrentDeployment().get(
					identifier);

			// do not ping in this cases
			switch (opDesc.getOperatorStatus()){
			case FAILED:
			case FINISHED:
			case NEGLECTED:
				continue;
			case ABORTED:
				System.err.println("Aborted Operator " + identifier
						+ " has been detected");
				setFailureDetected(true);
				continue;
			default:
				break;	
			}
			
			// ping compute server to see if it is alive
			err = this.computeClient.pingComputeServer(opDesc.getComputeNode());
			if (err.isError()) {
				System.err.println("Operator " + identifier
						+ " has been detected on killed compute node: "
						+ opDesc.getComputeNode());
				// Update the current deployment with the failed operator
				opDesc.setOperatorStatus(EnumOperatorStatus.ABORTED);
				setFailureDetected(true);
			}

		}

	}
}
