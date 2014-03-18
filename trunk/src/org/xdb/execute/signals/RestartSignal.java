package org.xdb.execute.signals;

import java.io.Serializable;

import org.xdb.utils.Identifier;

public class RestartSignal implements Serializable { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Identifier failedExecOpId;  
	
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
	

}
