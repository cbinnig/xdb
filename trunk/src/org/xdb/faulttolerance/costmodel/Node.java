package org.xdb.faulttolerance.costmodel;

public class Node { 
	
	private int id; 
	private double meanTimeBetweenFailure;  
	private NodeClass nodeClass; 
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the meanTimeBetweenFailure
	 */
	public double getMeanTimeBetweenFailure() {
		return meanTimeBetweenFailure;
	}
	/**
	 * @param meanTimeBetweenFailure the meanTimeBetweenFailure to set
	 */
	public void setMeanTimeBetweenFailure(double meanTimeBetweenFailure) {
		this.meanTimeBetweenFailure = meanTimeBetweenFailure;
	}
	/**
	 * @return the nodeClass
	 */
	public NodeClass getNodeClass() {
		return nodeClass;
	}
	/**
	 * @param nodeClass the nodeClass to set
	 */
	public void setNodeClass(NodeClass nodeClass) {
		this.nodeClass = nodeClass;
	} 

}
