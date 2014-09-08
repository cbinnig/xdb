package org.xdb.test.spotgres;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Abdallah
 *
 */
public class FailedNodesSet {
    
	private List<Integer> nodeIds = new ArrayList<Integer>();
	private int failureTime; 
	private int repairTime;
	
	public List<Integer> getNodeIds() {
		return nodeIds;
	}
	public void setNodeIds(List<Integer> nodeIds) {
		this.nodeIds = nodeIds;
	}
	public int getFailureTime() {
		return this.failureTime;
	}
	public void setFailureTime(int failureTime) {
		this.failureTime = failureTime;
	}
	public int getRepairTime() {
		return this.repairTime;
	}
	public void setRepairTime(int repairTime) {
		this.repairTime = repairTime;
	} 
		
}
