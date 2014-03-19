package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.List;

public class MaterlizationStrategyEnumerator { 

	private QueryPlan queryPlan;  
	
	List<MaterializedPlan> materilaizedPlansList = new ArrayList<MaterializedPlan>();

	MaterlizationStrategyEnumerator(QueryPlan queryPlan) {
		setQueryPlan(queryPlan);
	}
	
	/**
	 * @return the queryPlan
	 */
	public QueryPlan getQueryPlan() {
		return queryPlan;
	}

	/**
	 * @param queryPlan the queryPlan to set
	 */
	public void setQueryPlan(QueryPlan queryPlan) {
		this.queryPlan = queryPlan;
	} 
    

	/**
	 * enumerate materialization using simple binary enumerator  
	 */
	public void enumerateQueryPlan(){
        List<Operator> allOperator = this.queryPlan.getAllOperators();  
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
        		Operator op = allOperator.get(k);   
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
	}

	private void resetOperators(List<Operator> allOperator) {
		for (Operator operator : allOperator) {
			if(!operator.isForcedMaterlialized())
			    operator.setMaterilaized(false);
		}		
	}
    
	// build the materialized plan. Build the levels 
	private MaterializedPlan buildMaterliazedPlan(List<Operator> allOperator) {
        List<Integer> materialzationIndecis = new ArrayList<Integer>();  
        SubQueryEstimator levelEstimator = new SubQueryEstimator(); 
        int lastMaterializationIndex = 0; 
        MaterializedPlan matPlan = new MaterializedPlan();
        for (int i=0; i<allOperator.size(); i++) { 
  			Operator op = allOperator.get(i); 
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
		List<Level> levels = matPlan.getMateriliazedPlan(); 
		for (int i=0; i<levels.size(); i++) { 
			if(i%2 == 0 ) {
			     levels.get(i).setNodeClass(NodeClass.values()[0]);
			} else {
			     levels.get(i).setNodeClass(NodeClass.values()[1]);
			}
		}		
	}
}
