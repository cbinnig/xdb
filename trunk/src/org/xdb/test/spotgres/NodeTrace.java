package org.xdb.test.spotgres;

public class NodeTrace { 
	
	private int nodeId; 
	private int failureTime; 
	private boolean nodeStatus;  

	public NodeTrace() {
		// TODO Auto-generated constructor stub
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public int getFailureTime() {
		return failureTime;
	}

	public void setFailureTime(int failureTime) {
		this.failureTime = failureTime;
	}

	public boolean isNodeStatus() {
		return nodeStatus;
	}

	public void setNodeStatus(boolean nodeStatus) {
		this.nodeStatus = nodeStatus;
	}

}
