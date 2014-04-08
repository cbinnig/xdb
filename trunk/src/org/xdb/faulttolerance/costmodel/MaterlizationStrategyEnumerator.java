package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Abdallah
 *
 */
public class MaterlizationStrategyEnumerator { 

	private CostModelQueryPlan costModelQueryPlan;   

	private List<MaterializedPlan> materilaizedPlansList = new ArrayList<MaterializedPlan>();   

	private List<Integer> forcedMaterializedOpsIndexes = new ArrayList<Integer>();

	private int MTBF;

	MaterlizationStrategyEnumerator(CostModelQueryPlan costModelQueryPlan, List<Integer> forcedMaterializedOpsIndexes, int MTBF) {
		this.costModelQueryPlan = costModelQueryPlan;
		this.setForcedMaterializedOpsIndexes(forcedMaterializedOpsIndexes);
		this.setMTBF(MTBF);
	}

	/**
	 * @return the queryPlan
	 */
	public CostModelQueryPlan getQueryPlan() {
		return costModelQueryPlan;
	}

	/**
	 * @param costModelQueryPlan the queryPlan to set
	 */
	public void setQueryPlan(CostModelQueryPlan costModelQueryPlan) {
		this.costModelQueryPlan = costModelQueryPlan;
	} 

	/**
	 * @return the MTBF
	 */
	public int getMTBF() {
		return MTBF;
	}

	/**
	 * @param MTBF the MTBF to set
	 */
	public void setMTBF(int MTBF) {
		this.MTBF = MTBF;
	}

	/**
	 * enumerate materialization using simple binary enumerator  
	 * @return 
	 */
	public List<MaterializedPlan> enumerateQueryPlan(){
		List<CostModelOperator> allOperator = this.costModelQueryPlan.getAllOperators();    
		int numberOfPossibleLevel = allOperator.size(); 
		int numberOfMaterializationScenarios = (int) Math.pow(2, numberOfPossibleLevel); 
        boolean forcedMaterializedDetected = false;
		for(int i=0; i<numberOfMaterializationScenarios; i++){  
			for(int j=0; j<forcedMaterializedOpsIndexes.size(); j++){
				Double level = Math.pow(2.0, forcedMaterializedOpsIndexes.get(j));
				if((level.intValue()&i) == 0){
					forcedMaterializedDetected = true;
					break;
				}
			} 
			if(forcedMaterializedDetected) {
				forcedMaterializedDetected = false; 
				continue;
			}
			char[] binaryRep = String.format(Integer.toBinaryString(i)).toCharArray();  
			for (int j = 0; j<binaryRep.length; j++){
				if(binaryRep[j] == '1') {
					CostModelOperator op = allOperator.get(allOperator.size() -1 - j);
					op.setMaterilaized(true);   
				}
			}

			materilaizedPlansList.add(buildMaterliazedPlan(allOperator)); 	
			resetOperators(allOperator);
		}  
		return materilaizedPlansList;
	}

	/**
	 * 
	 * @param allOperator
	 */
	private void resetOperators(List<CostModelOperator> allOperator) {
		for (CostModelOperator operator : allOperator) {
			if(!operator.isForcedMaterlialized())
				operator.setMaterilaized(false);
		}		
	}

	/**
	 * Build the materialized plan (Levels) from a list of 
	 * operators after enumerating them
	 * 
	 * @param allOperator
	 * @return
	 */
	private MaterializedPlan buildMaterliazedPlan(List<CostModelOperator> allOperator) {
		List<Integer> materialzationIndecis = new ArrayList<Integer>();  
		SubQueryEstimator levelEstimator = new SubQueryEstimator(); 
		int lastMaterializationIndex = 0; 
		MaterializedPlan matPlan = new MaterializedPlan();
		for (int i=0; i<allOperator.size(); i++) { 
			CostModelOperator op = allOperator.get(i); 
			if(op.isMaterilaized() || i == allOperator.size() -1){ 
				materialzationIndecis.add(i); 
				Level level = new Level(); 
				level.setSubQquery(allOperator.
						subList(lastMaterializationIndex, i+1)); 
				// set the runtime including the materialization cost.
				level.setLevelRuntimeEstimate(levelEstimator.getLevelRunTime(level)); 
				level.setMaterializationRuntimeestimate(levelEstimator.getMaterializationTime(level));
				lastMaterializationIndex = i+1;
				matPlan.setMateriliazedPlanLevels(level);
			} 
		} 
		// deploy the materialized plan  
		deployMaterializedPlan(matPlan); 
		return matPlan;
	}

	private void deployMaterializedPlan(MaterializedPlan matPlan) { 
		// initially make an initial deployment plan
		// such that every two consecutive levels 
		// are deployed on two different class nodes 
		List<Level> matPlanLevels = matPlan.getmateriliazedPlanLevels(); 
		for (int i=0; i<matPlanLevels.size(); i++) { 
			Level l = matPlanLevels.get(i); 
			// Getting the number of partitions from the last operator 
			l.setNumberOfPartitions(l.getSubQquery().get(l.getSubQquery().size() -1).getDegreeOfPartitioning());  
			l.setMTBF(this.MTBF);

		}		
	}

	/**
	 * @return the forcedMaterializedOpsIndexes
	 */
	public List<Integer> getForcedMaterializedOpsIndexes() {
		return forcedMaterializedOpsIndexes;
	}

	/**
	 * @param forcedMaterializedOpsIndexes the forcedMaterializedOpsIndexes to set
	 */
	public void setForcedMaterializedOpsIndexes(
			List<Integer> forcedMaterializedOpsIndexes) {
		this.forcedMaterializedOpsIndexes = forcedMaterializedOpsIndexes;
	}

}
