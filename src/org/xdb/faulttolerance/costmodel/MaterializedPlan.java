package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.List;

/** 
 * 
 * @author Abdallah 
 *
 */
public class MaterializedPlan implements Comparable<MaterializedPlan> { 
	
	private List<Level> materiliazedPlanLevels = new ArrayList<Level>(); 
	private double runTime; // in case of failure including materialization
    private double successProbability; 
    private double failureProbability;  
    private double averageWastedTime; 
    private int reattempts;  
    private double runTimeWithoutFailure; 
    private double materializationTime;
	
    /**
	 * @return the materiliazedPlan
	 */
	public List<Level> getmateriliazedPlanLevels() {
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
	public double getSuccessProbability() {
		return successProbability;
	}

	/**
	 * @param querySuccessProbability the successProbability to set
	 */
	public void setSuccessProbability(double querySuccessProbability) {
		this.successProbability = querySuccessProbability;
	}

	/**
	 * @return the failureProbability
	 */
	public double getFailureProbability() {
		return failureProbability;
	}

	/**
	 * @param failureProbability the failureProbability to set
	 */
	public void setFailureProbability(double failureProbability) {
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
	public int compareTo(MaterializedPlan obj) {
		return (int) (this.runTime - obj.runTime);
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
