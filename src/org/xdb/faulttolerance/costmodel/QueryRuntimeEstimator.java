package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import org.xdb.Config;

public class QueryRuntimeEstimator { 
	
	private Map<MaterializedPlan, Long> reattemptsForDifferentMaterializations
	= new HashMap<MaterializedPlan, Long>(); 
	
	private Map<MaterializedPlan, Double> runtimesForDifferentMaterializations
	= new HashMap<MaterializedPlan, Double>(); 
	
	private List<MaterializedPlan> matPlans = new ArrayList<MaterializedPlan>(); 
	
	private double successRate; 
	
	private long reattempts;
	
	QueryRuntimeEstimator(List<MaterializedPlan> matPlans, double successRate){
		this.setMatPlans(matPlans);
		this.setSuccessRate(successRate);
	} 
	
	QueryRuntimeEstimator(){
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
	public Map<MaterializedPlan, Long> getReattemptsForDifferentMaterializations() {
		return reattemptsForDifferentMaterializations;
	}

	/**
	 * @param reattemptsForDifferentMaterializations the reattemptsForDifferentMaterializations to set
	 */
	public void setReattemptsForDifferentMaterializations(MaterializedPlan matPlan, Long reattempt){
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
		
		List<Integer> uselessMatConfList = new ArrayList<Integer>();
		for(int i=0; i<matPlans.size(); i++){
			MaterializedPlan matPlan =  matPlans.get(i);
			List<Level> levels = matPlan.getmateriliazedPlanLevels(); 
			double querySuccessProbability = 1;
			for (Level level : levels) {
				double levelSuccess =  calculateSuccessProbForLevel(level);  
				level.setLevelSuccessProbability(levelSuccess); 
				level.setLevelFailureProbability(1 - levelSuccess); 
				// calculate the the number of attempts for a level 
				//long levelAttempts = calculateReattempts(levelSuccess, levels.size()); 
				this.reattempts =0; 
				long levelAtteptsRecursively = calculateReattemptsRecursively(levelSuccess, 
					Math.pow(Math.E,Math.log(this.successRate)/levels.size()), level, level.getNumberOfPartitions());  

				level.setNumberOfAttemptsPerLevel(levelAtteptsRecursively);
				querySuccessProbability = querySuccessProbability*levelSuccess;  
			} 
			// if the success rate of the query almost zero 
			// then neglect the corresponding Mat Cong 
			if(querySuccessProbability == 0){
				System.out.println("A conf is skipped has zero success rate for at least one level!");
				uselessMatConfList.add(i);
				continue;
			}
			// Can be neglected later!! (Calculate the attempts for levels individually)
			long reattempts = calculateReattempts(querySuccessProbability, levels.size());
			this.setReattemptsForDifferentMaterializations(matPlan, reattempts); 
			// set the number of reattempts, failure probability, and 
			// success probability for each materialization configuration  
			matPlan.setReattempts(reattempts); 
			matPlan.setSuccessProbability(querySuccessProbability); 
			matPlan.setFailureProbability(1 - querySuccessProbability);
		} 
		
		// delete all useless mat conf 
		for (int j=0; j<uselessMatConfList.size(); j++){
			matPlans.remove(uselessMatConfList.get(j));
		}
	} 
	
	/**
	 * Calculate the number of re-attempts required to 
	 * achieve a certain success rate.  
	 * @param querySuccessProbability
	 * @return
	 */
	private long calculateReattempts(double querySuccessProbability, int levels) {
		long reattempts = 0; // initial
		double queryFailureProbability = 1 - querySuccessProbability; 
		double big1 = Math.log10(1-Math.pow(Math.E, Math.log(this.successRate)/levels)); 
		double big2 = Math.log10(queryFailureProbability);  
		//reattempts = calculateReattemptsRecursively(querySuccessProbability, Math.pow(Math.E, Math.log(this.successRate)/levels));
		reattempts = (long) (Math.ceil((big1/big2) -1));	
		return reattempts;
	}  
	
	/**
	 * Calculate the number of reattempts per level 
	 * recursively based on the increasing success rate 
	 * after each attempt 
	 * 
	 * @param querySuccessProbability
	 * @param levelSuccessProbability
	 * @param level 
	 * @return
	 */
	private long calculateReattemptsRecursively(double querySuccessProbability, 
			double levelSuccessProbability, Level level, double nodeNumber) {	
		if(querySuccessProbability < levelSuccessProbability && this.reattempts < 5000) {
			this.reattempts++; 
			nodeNumber = nodeNumber - querySuccessProbability*nodeNumber; 
			QueryRuntimeEstimator obj = new QueryRuntimeEstimator();  
			querySuccessProbability = obj.calculateSuccessProbForLevelNnodes(level,nodeNumber);  
			calculateReattemptsRecursively(querySuccessProbability, levelSuccessProbability, level, nodeNumber);
		} 
		return this.reattempts;
	}

	/**
	 * 
	 * @param level
	 * @return
	 */
	public double calculateSuccessProbForNode(Level level){
		return 1 - calculateFailureProbForNode(level);
	}
	
	/**
	 * 
	 * @param level
	 * @return
	 */
	public double calculateFailureProbForNode(Level level){
    	double meatTimeBetweenFailure = level.getMTBF(); 
    	meatTimeBetweenFailure = Config.DOOMDB_MTBF;
    	return 1 - Math.pow(Math.E, -1*(level.getLevelRuntimeEstimate()+level.getMaterializationRuntimeestimate())/meatTimeBetweenFailure);
	} 
    
    @SuppressWarnings("unused")
    private double calculateFailureProbForLevel(Level level){
		return 1 - calculateSuccessProbForLevel(level);
	} 
    
    /**
     * 
     * @param level
     * @return
     */
    public double calculateSuccessProbForLevel(Level level){ 
    	int numberOfNodePerLevel = level.getNumberOfPartitions(); 
    	double successProbNode = calculateSuccessProbForNode(level);   
		return Math.pow(successProbNode, numberOfNodePerLevel);	
	} 
    
    /**
     * 
     * @param level
     * @return
     */
    public double calculateSuccessProbForLevelNnodes(Level level, double succeedNodesNumber){ 
    	double successProbNode = calculateSuccessProbForNode(level);  
		return Math.pow(successProbNode, succeedNodesNumber);	
	}


}
