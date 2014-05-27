package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.List;

/** 
 * Level class
 * @author Abdallah
 *
 */
public class Level { 
	
	private List<CostModelOperator> subQquery = new ArrayList<CostModelOperator>(); 
	
	private double levelSuccessProbability;   
	
	private double levelFailureProbability;  
	
	private int numberOfPartitions;  
	
	private NodeClass nodeClass;  
	
	private double levelRuntimeEstimate; 
	
	private double materializationRuntimeestimate;   
	
	private int MTBF; 
	
	private long numberOfAttemptsPerLevel; 
	
	private double averageWastedTime;
	

	/**
	 * @return the subQquery
	 */
	public List<CostModelOperator> getSubQquery() {
		return subQquery;
	}

	/**
	 * @param subQquery the subQquery to set
	 */
	public void setSubQquery(List<CostModelOperator> subQquery) {
		this.subQquery = subQquery;
	}

	/**
	 * @return the levelSuccessProbability
	 */
	public double getLevelSuccessProbability() {
		return levelSuccessProbability;
	}

	/**
	 * @param levelSuccess the levelSuccessProbability to set
	 */
	public void setLevelSuccessProbability(double levelSuccess) {
		this.levelSuccessProbability = levelSuccess;
	}

	/**
	 * @return the levelFailureProbability
	 */
	public double getLevelFailureProbability() {
		return levelFailureProbability;
	}

	/**
	 * @param levelFailureProbability the levelFailureProbability to set
	 */
	public void setLevelFailureProbability(double levelFailureProbability) {
		this.levelFailureProbability = levelFailureProbability;
	}

	/**
	 * @return the numberOfPartitions
	 */
	public int getNumberOfPartitions() {
		return numberOfPartitions;
	}

	/**
	 * @param numberOfPartitions the numberOfPartitions to set
	 */
	public void setNumberOfPartitions(int numberOfPartitions) {
		this.numberOfPartitions = numberOfPartitions;
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

	/**
	 * @return the levelRuntimeEstimate
	 */
	public double getLevelRuntimeEstimate() {
		return levelRuntimeEstimate;
	}

	/**
	 * @param levelRuntimeEstimate the levelRuntimeEstimate to set
	 */
	public void setLevelRuntimeEstimate(double levelRuntimeEstimate) {
		this.levelRuntimeEstimate = levelRuntimeEstimate;
	}

	/**
	 * @return the materializationRuntimeestimate
	 */
	public double getMaterializationRuntimeestimate() {
		return materializationRuntimeestimate;
	}

	/**
	 * @param materializationRuntimeestimate the materializationRuntimeestimate to set
	 */
	public void setMaterializationRuntimeestimate(
			double materializationRuntimeestimate) {
		this.materializationRuntimeestimate = materializationRuntimeestimate;
	}

	/**
	 * @return the MTBF
	 */
	public int getMTBF() {
		return MTBF;
	}

	/**
	 * @param mTTR the mTTR to set
	 */
	public void setMTBF(int MTBF) {
		this.MTBF = MTBF;
	}

	/**
	 * @return the numberOfAttemptsPerLevel
	 */
	public long getNumberOfAttemptsPerLevel() {
		return numberOfAttemptsPerLevel;
	}

	/**
	 * @param numberOfAttemptsPerLevel the numberOfAttemptsPerLevel to set
	 */
	public void setNumberOfAttemptsPerLevel(long numberOfAttemptsPerLevel) {
		this.numberOfAttemptsPerLevel = numberOfAttemptsPerLevel;
	}

	/**
	 * @return the averageWastedTime
	 */
	public double getAverageWastedTime() {
		return averageWastedTime;
	}

	/**
	 * @param averageWastedTime the averageWastedTime to set
	 */
	public void setAverageWastedTime(double averageWastedTime) {
		this.averageWastedTime = averageWastedTime;
	} 

}
