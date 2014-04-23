package org.xdb.execute.signals;

import java.io.Serializable;

import org.xdb.execute.ComputeNodeDesc;

/**
 * Signal  which is send to compute node in order 
 * to simulate a node failure by a restart 
 * 
 * @author cbinnig
 *
 */
public class RestartSignal implements Serializable { 
	
	private static final long serialVersionUID = -547700923322945569L;
	
	// description of compute node to restart
	private ComputeNodeDesc computeNodeDecs;
	
	// time until node restarts after repair 
	private int timeToRepair; 
	
	public RestartSignal(ComputeNodeDesc computeNodeDecs, int timeToRepair) {
		super();
		this.computeNodeDecs = computeNodeDecs;
		this.timeToRepair = timeToRepair;
	}

	/**
	 * @return the meantimeBetweenFailure
	 */
	public int getTimeToRepair() {
		return timeToRepair;
	}

	/**
	 * @return the computeNodeDecs
	 */
	public ComputeNodeDesc getComputeNodeDecs() {
		return computeNodeDecs;
	}
}
