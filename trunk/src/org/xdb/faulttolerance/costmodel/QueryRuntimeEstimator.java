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
 
	public void estimateReattemptsForMaterlialization(){
		for(int i=0; i<matPlans.size(); i++){
			MaterializedPlan matPlan =  matPlans.get(i);
			List<Level> levels = matPlan.getMateriliazedPlan(); 
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
			// success probability. 
			matPlan.setReattempts(reattempts); 
			matPlan.setSuccessProbability(querySuccessProbability); 
			matPlan.setFailureProbability(new BigDecimal(1).subtract(querySuccessProbability, MathContext.DECIMAL128));
		}
	} 
	
	// Calculate the number of re-attempts required to 
	// achieve a certain success rate. 
	// TODO using simpler way to get R
	private int calculateReattempts(BigDecimal querySuccessProbability) {
		int reattempts = 0; // initial
		BigDecimal queryFailureProbability = new BigDecimal(1).subtract(querySuccessProbability); 
		System.out.println(queryFailureProbability);
		//double successProb = querySuccessProbability*
			//	(1-Math.pow(queryFailureProbability, reattempts+1))/(1-queryFailureProbability);  

		//reattempts = (int) Math.ceil((Math.log10(1-this.successRate)/Math.log10(queryFailureProbability))-1);
		BigDecimal big1 = new BigDecimal(Math.log(1-this.successRate)); 
		//BigDecimal big2 = new BigDecimal(Math.log(queryFailureProbability.longValue()));  
		BigDecimal big2 = BigFunctions.ln(queryFailureProbability, 1000); 
		reattempts = (int) (Math.ceil(big1.divide(big2, MathContext.DECIMAL128).doubleValue()) ) -1; 
	    /*
		while(successProb < this.successRate) { 
			if(reattempts > 100000) 
				break;
			reattempts++;  
			successProb = querySuccessProbability*
				(1-Math.pow(queryFailureProbability, reattempts))/(1-queryFailureProbability);
		} */
		
		return reattempts;
	}
	
	private BigDecimal calculateSuccessProbForNode(Level level){
		return new BigDecimal(1).subtract(calculateFailureProbForNode(level));
	}
	
    private BigDecimal calculateFailureProbForNode(Level level){
    	double meatTimeBetweenFailure = level.getNodeClass().getMeanTimeBetweenFailure();
    	return new BigDecimal(1).subtract(new BigDecimal(Math.pow(2.87, -1*(level.getLevelRuntimeEstimate()+level.getMaterializationRuntimeestimate())/meatTimeBetweenFailure)));
	} 
    
    private BigDecimal calculateSuccessProbForLevel(Level level){ 
    	int numberOfNodePerLevel = level.getNodeClass().getNumberOfNodesPerClass();
		BigDecimal successProbNode = calculateSuccessProbForNode(level); 
		return successProbNode.pow(numberOfNodePerLevel);
		
	}

}
