package org.xdb.faulttolerance.costmodel;

public class Operator { 
	
	private int id; 
	private String type;   
	private boolean isMaterilaized;  
	private boolean isForcedMaterlialized; 
	
	private double opRunTimeEstimate; 
	private double opMaterializationTimeEstimate; 

	public Operator() {
		// TODO Auto-generated constructor stub
	}


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
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the isMaterilaized
	 */
	public boolean isMaterilaized() {
		return isMaterilaized;
	}


	/**
	 * @param isMaterilaized the isMaterilaized to set
	 */
	public void setMaterilaized(boolean isMaterilaized) {
		this.isMaterilaized = isMaterilaized;
	}


	/**
	 * @return the isForcedMaterlialized
	 */
	public boolean isForcedMaterlialized() {
		return isForcedMaterlialized;
	}


	/**
	 * @param isForcedMaterlialized the isForcedMaterlialized to set
	 */
	public void setForcedMaterlialized(boolean isForcedMaterlialized) {
		this.isForcedMaterlialized = isForcedMaterlialized;
	}


	/**
	 * @return the opRunTimeEstimate
	 */
	public double getOpRunTimeEstimate() {
		return opRunTimeEstimate;
	}


	/**
	 * @param opRunTimeEstimate the opRunTimeEstimate to set
	 */
	public void setOpRunTimeEstimate(double opRunTimeEstimate) {
		this.opRunTimeEstimate = opRunTimeEstimate;
	}


	/**
	 * @return the opMaterializationTimeEstimate
	 */
	public double getOpMaterializationTimeEstimate() {
		return opMaterializationTimeEstimate;
	}


	/**
	 * @param opMaterializationTimeEstimate the opMaterializationTimeEstimate to set
	 */
	public void setOpMaterializationTimeEstimate(
			double opMaterializationTimeEstimate) {
		this.opMaterializationTimeEstimate = opMaterializationTimeEstimate;
	}

}
