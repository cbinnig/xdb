package org.xdb.execute.signals;

import java.io.Serializable;

import org.xdb.execute.ComputeNodeDesc;
import org.xdb.utils.Identifier;

public class RestartSignal implements Serializable { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Identifier failedExecOpId;  
	
	private ComputeNodeDesc computeNodeDecs;
	
	private int timeToRepair; 
	

	/**
	 * @return the failedExecOpId
	 */
	public Identifier getFailedExecOpId() {
		return failedExecOpId;
	}

	/**
	 * @param failedExecOpId the failedExecOpId to set
	 */
	public void setFailedExecOpId(Identifier failedExecOpId) {
		this.failedExecOpId = failedExecOpId;
	}

	/**
	 * @return the meantimeBetweenFailure
	 */
	public int getTimeToRepair() {
		return timeToRepair;
	}

	/**
	 * @param meantimeBetweenFailure the meantimeBetweenFailure to set
	 */
	public void setTimeToRepair(int timeToRepair) {
		this.timeToRepair = timeToRepair;
	}

	/**
	 * @return the computeNodeDecs
	 */
	public ComputeNodeDesc getComputeNodeDecs() {
		return computeNodeDecs;
	}

	/**
	 * @param computeNodeDecs the computeNodeDecs to set
	 */
	public void setComputeNodeDecs(ComputeNodeDesc computeNodeDecs) {
		this.computeNodeDecs = computeNodeDecs;
	} 
	

}
