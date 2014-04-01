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
	
	private int MTBF;
	
	MaterlizationStrategyEnumerator(CostModelQueryPlan costModelQueryPlan, int MTBF) {
		this.costModelQueryPlan = costModelQueryPlan;
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
        boolean isForcedMaterialized = false; 
        char[] copyArray = new char[numberOfPossibleLevel];
        for(int i=0; i<copyArray.length; i++){
        	copyArray[i] = '0';
        } 
        for(int i=0; i<numberOfMaterializationScenarios; i++){
        	char[] binaryRep = String.format(Integer.toBinaryString(i)).toCharArray();  
        	System.arraycopy(binaryRep, 0, copyArray, copyArray.length -binaryRep.length , binaryRep.length);
        	for (int j = copyArray.length -1, k=allOperator.size()-1; j>=0; j--, k--){
        		CostModelOperator op = allOperator.get(k);   
				String isMaterilaized = String.valueOf(copyArray[j]);  
        		if(op.isForcedMaterlialized()) {
        			op.setMaterilaized(true);  
        			if(!isMaterilaized.equals("1")){ 
    					isForcedMaterialized = true;
    					break;
    				}
        		} else { 
        			isForcedMaterialized = false;
        			op.setMaterilaized(isMaterilaized.equals("1"));
        		}
        	}
        	if(!isForcedMaterialized){
        		materilaizedPlansList.add(buildMaterliazedPlan(allOperator)); 
        	}
        	resetOperators(allOperator);
        }  
        return materilaizedPlansList;
	}

	private void resetOperators(List<CostModelOperator> allOperator) {
		for (CostModelOperator operator : allOperator) {
			if(!operator.isForcedMaterlialized())
			    operator.setMaterilaized(false);
		}		
	}
    
	// build the materialized plan. Build the levels 
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

}
