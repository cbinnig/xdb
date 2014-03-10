package org.xdb.faulttolerance.costmodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MaterializedPlan implements Comparable<MaterializedPlan> { 
	
	private List<Level> materiliazedPlanLevels = new ArrayList<Level>(); 
	private double runTime; // in case of failure including materialization
    private BigDecimal successProbability; 
    private BigDecimal failureProbability;  
    private double averageWastedTime; 
    private int reattempts;  
    private double runTimeWithoutFailure; 
    private double materializationTime;
	/**
	 * @return the materiliazedPlan
	 */
	public List<Level> getMateriliazedPlan() {
		return materiliazedPlanLevels;
	}

	/**
	 * @param materiliazedPlan the materiliazedPlan to set
	 */
	public void setMateriliazedPlanLevels(Level level) {
		this.materiliazedPlanLevels.add(level); 
	}

	/**
	 * @return the successProbability
	 */
	public BigDecimal getSuccessProbability() {
		return successProbability;
	}

	/**
	 * @param querySuccessProbability the successProbability to set
	 */
	public void setSuccessProbability(BigDecimal querySuccessProbability) {
		this.successProbability = querySuccessProbability;
	}

	/**
	 * @return the failureProbability
	 */
	public BigDecimal getFailureProbability() {
		return failureProbability;
	}

	/**
	 * @param failureProbability the failureProbability to set
	 */
	public void setFailureProbability(BigDecimal failureProbability) {
		this.failureProbability = failureProbability;
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

	/**
	 * @return the reattempts
	 */
	public int getReattempts() {
		return reattempts;
	}

	/**
	 * @param reattempts the reattempts to set
	 */
	public void setReattempts(int reattempts) {
		this.reattempts = reattempts;
	}

	/**
	 * @return the runTimeWithoutFailure
	 */
	public double getRunTimeWithoutFailure() {
		return runTimeWithoutFailure;
	}

	/**
	 * @param runTimeWithoutFailure the runTimeWithoutFailure to set
	 */
	public void setRunTimeWithoutFailure(double runTimeWithoutFailure) {
		this.runTimeWithoutFailure = runTimeWithoutFailure;
	}

	/**
	 * @return the runTime
	 */
	public double getRunTime() {
		return runTime;
	}

	/**
	 * @param runTime the runTime to set
	 */
	public void setRunTime(double runTime) {
		this.runTime = runTime;
	}

	@Override
	public int compareTo(MaterializedPlan o) {
		return (int) (this.getMateriliazedPlan().size() - o.getMateriliazedPlan().size());
	}

	/**
	 * @return the materializationTime
	 */
	public double getMaterializationTime() {
		return materializationTime;
	}

	/**
	 * @param materializationTime the materializationTime to set
	 */
	public void setMaterializationTime(double materializationTime) {
		this.materializationTime = materializationTime;
	} 

}
