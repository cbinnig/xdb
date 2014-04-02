package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import java.math.*;

public class QueryRuntimeEstimator { 
	
	private Map<MaterializedPlan, Integer> reattemptsForDifferentMaterializations
	= new HashMap<MaterializedPlan, Integer>(); 
	
	private Map<MaterializedPlan, Double> runtimesForDifferentMaterializations
	= new HashMap<MaterializedPlan, Double>(); 
	
	private List<MaterializedPlan> matPlans = new ArrayList<MaterializedPlan>(); 
	
	private double successRate;
	
	QueryRuntimeEstimator(List<MaterializedPlan> matPlans, double successRate){
		this.setMatPlans(matPlans);
		this.setSuccessRate(successRate);
	}
	/**
	 * @return the runtimesForDifferentMaterializations
	 */
	public Map<MaterializedPlan, Double> getRuntimesForDifferentMaterializations() {
		return runtimesForDifferentMaterializations;
	}

	/**
	 * @param runtimesForDifferentMaterializations the runtimesForDifferentMaterializations to set
	 */
	public void setRuntimesForDifferentMaterializations(
			Map<MaterializedPlan, Double> runtimesForDifferentMaterializations) {
		this.runtimesForDifferentMaterializations = runtimesForDifferentMaterializations;
	}

	/**
	 * @return the reattemptsForDifferentMaterializations
	 */
	public Map<MaterializedPlan, Integer> getReattemptsForDifferentMaterializations() {
		return reattemptsForDifferentMaterializations;
	}

	/**
	 * @param reattemptsForDifferentMaterializations the reattemptsForDifferentMaterializations to set
	 */
	public void setReattemptsForDifferentMaterializations(MaterializedPlan matPlan, Integer reattempt){
		this.reattemptsForDifferentMaterializations.put(matPlan, reattempt);
	}

	/**
	 * @return the matPlans
	 */
	public List<MaterializedPlan> getMatPlans() {
		return matPlans;
	}

	/**
	 * @param matPlans the matPlans to set
	 */
	public void setMatPlans(List<MaterializedPlan> matPlans) {
		this.matPlans = matPlans;
	}

	/**
	 * @return the successRate
	 */
	public double getSuccessRate() {
		return successRate;
	}

	/**
	 * @param successRate the successRate to set
	 */
	public void setSuccessRate(double successRate) {
		this.successRate = successRate;
	}  
     
	/**
	 * Calculate the number of re-attempts required for 
	 * each materialization configuration  
	 */
	public void estimateReattemptsForMaterlialization(){
		
		for(int i=0; i<matPlans.size(); i++){
			MaterializedPlan matPlan =  matPlans.get(i);
			List<Level> levels = matPlan.getmateriliazedPlanLevels(); 
			BigDecimal querySuccessProbability = new BigDecimal(1.0); 
			for (Level level : levels) {
				BigDecimal levelSuccess =  calculateSuccessProbForLevel(level); 
				level.setLevelSuccessProbability(levelSuccess);
				level.setLevelFailureProbability(new BigDecimal(1).subtract(levelSuccess));
				querySuccessProbability = querySuccessProbability.multiply(levelSuccess, MathContext.DECIMAL128); 
			}  
			int reattempts = calculateReattempts(querySuccessProbability);
			this.setReattemptsForDifferentMaterializations(matPlan, reattempts); 
			// set the number of reattempts, failure probability, and 
			// success probability for each materialization configuration  
			matPlan.setReattempts(reattempts); 
			matPlan.setSuccessProbability(querySuccessProbability); 
			matPlan.setFailureProbability(new BigDecimal(1).subtract(querySuccessProbability, MathContext.DECIMAL128));
		}
	} 
	
	/**
	 * Calculate the number of re-attempts required to 
	 * achieve a certain success rate.  
	 * @param querySuccessProbability
	 * @return
	 */
	private int calculateReattempts(BigDecimal querySuccessProbability) {
		int reattempts = 0; // initial
		BigDecimal queryFailureProbability = new BigDecimal(1).subtract(querySuccessProbability); 
		BigDecimal big1 = new BigDecimal(Math.log(1-this.successRate)); 
		BigDecimal big2 = BigFunctions.ln(queryFailureProbability, 1000); 
		reattempts = (int) (Math.ceil(big1.divide(big2, MathContext.DECIMAL128).doubleValue()) ) -1;	
		return reattempts;
	}
	
	private BigDecimal calculateSuccessProbForNode(Level level){
		return new BigDecimal(1).subtract(calculateFailureProbForNode(level));
	}
	
    private BigDecimal calculateFailureProbForNode(Level level){
    	double meatTimeBetweenFailure = level.getMTBF();
    	return new BigDecimal(1).subtract(new BigDecimal(Math.pow(2.87, -1*(level.getLevelRuntimeEstimate()+level.getMaterializationRuntimeestimate())/meatTimeBetweenFailure)));
	} 
    
    @SuppressWarnings("unused")
	private BigDecimal calculateFailureProbForLevel(Level level){
		return new BigDecimal(1).subtract(calculateSuccessProbForLevel(level));
	} 

    private BigDecimal calculateSuccessProbForLevel(Level level){ 
    	int numberOfNodePerLevel = level.getNumberOfPartitions();
		BigDecimal successProbNode = calculateSuccessProbForNode(level); 
		return successProbNode.pow(numberOfNodePerLevel);
		
	}

}