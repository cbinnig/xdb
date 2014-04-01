package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.utils.Identifier; 
import org.xdb.error.Error;


public class MaterializationOpsSuggester { 
	
	// CompilePlan to annotate extra materialization point
	private CompilePlan compilePlan; 
	// Hashmap to store the runtime for every operator. 
	private Map<Identifier, Double> opsEstimatedRuntime = new HashMap<Identifier, Double>(); 
	
	private Map<Identifier, Double> intermediadeResultsMatTime = new HashMap<Identifier, Double>();  
	
	private int MTBF; 
	
	public MaterializationOpsSuggester(CompilePlan compilePlan, Map<Identifier, Double> opsEstimatedRuntime, 
			Map<Identifier, Double> intermediadeResultsMatTime, int MTBF ){ 
		this.compilePlan = compilePlan;
		this.opsEstimatedRuntime = opsEstimatedRuntime; 
		this.intermediadeResultsMatTime = intermediadeResultsMatTime;  
		this.MTBF = MTBF;	
	}

	/**
	 * @return the compilePlan
	 */
	public CompilePlan getCompilePlan() {
		return compilePlan;
	}

	/**
	 * @param compilePlan the compilePlan to set
	 */
	public void setCompilePlan(CompilePlan compilePlan) {
		this.compilePlan = compilePlan;
	}

	/**
	 * @return the opsEstimatedRuntime
	 */
	public Map<Identifier, Double> getOpsEstimatedRuntime() {
		return opsEstimatedRuntime;
	}

	/**
	 * @param opsEstimatedRuntime the opsEstimatedRuntime to set
	 */
	public void setOpsEstimatedRuntime(Map<Identifier, Double> opsEstimatedRuntime) {
		this.opsEstimatedRuntime = opsEstimatedRuntime;
	}

	/**
	 * @return the intermediadeResultsCardinality
	 */
	public Map<Identifier, Double> getIntermediadeResultsCardinality() {
		return intermediadeResultsMatTime;
	}

	/**
	 * @param intermediadeResultsCardinality the intermediadeResultsCardinality to set
	 */
	public void setIntermediadeResultsCardinality(
			Map<Identifier, Double> intermediadeResultsMatTime) {
		this.intermediadeResultsMatTime = intermediadeResultsMatTime;
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
	 * 
	 * @return
	 */
	public Error startSmartMaterilizationFinder(){
		Error err = new Error();
		// Traverse the tree from top to bottom, in the way 
		// adding the left child operators so will 
		CostModelTreePlanVisitor visitor = new CostModelTreePlanVisitor();   
		err = this.compilePlan.applyVisitor(visitor); 
		
		List<AbstractCompileOperator> sortedCompileOps = new ArrayList<AbstractCompileOperator>();  
		mapCompileOpsToCostModelOps(sortedCompileOps);
		visitor.getOps();
		
		return err; 
	} 
	
	/**
	 * 
	 * @return
	 */
	public Error startNaiveMaterilizationFinder() {
		Error err = new Error();
		Collection<AbstractCompileOperator> ops = this.compilePlan.getOperators();  
		for (AbstractCompileOperator abstractCompileOperator : ops) {
			if(abstractCompileOperator.getType() == EnumOperator.TABLE) 
				continue;
			abstractCompileOperator.getResult().materialize(true);   
			System.out.println(abstractCompileOperator.getOperatorId());
		}
		
		return err;
	}
	/**
	 * Convert the AbstractComileOperator (XDB Format) to CostModel 
	 * Operator (XDB cost model Format) 
	 * @param sortedCompileOps
	 */
	private void mapCompileOpsToCostModelOps(
			List<AbstractCompileOperator> sortedCompileOps) {
		List<CostModelOperator> costModelOps = new ArrayList<CostModelOperator>();  
		for (int i= sortedCompileOps.size() -1; i>=0; i--) {
			AbstractCompileOperator compileOp = sortedCompileOps.get(i); 
			CostModelOperator costModelOp = new CostModelOperator();
			costModelOp.setForcedMaterlialized(compileOp.getResult().materialize());
			costModelOp.setOpMaterializationTimeEstimate(intermediadeResultsMatTime.get(compileOp.getOperatorId())); 
			costModelOp.setOpRunTimeEstimate(opsEstimatedRuntime.get(compileOp.getOperatorId()));
			// Number of nodes the operator executes on 
			costModelOp.setDegreeOfPartitioning(compileOp.getResult().getPartitionCount()); 
			costModelOps.add(costModelOp);
		} 
		// Apply our Cost Model 
		applyCostModel(costModelOps); 
		
	}

	private void applyCostModel(List<CostModelOperator> costModelOps) {
		
		// Initiate a query plan 
		CostModelQueryPlan costModelQueryPlan = new CostModelQueryPlan(costModelOps); 
		costModelQueryPlan.setAllOperators(costModelOps); 
		// Enumerate Different Materialization Strategy 
		MaterlizationStrategyEnumerator matEnumerator = new MaterlizationStrategyEnumerator(costModelQueryPlan, this.MTBF);
		List<MaterializedPlan> materializedPlansList = matEnumerator.enumerateQueryPlan(); 
		
		// Calculate the number of re-attempts required to achieve the given success rate c
		// For each materialization configuration. 
		QueryRuntimeEstimator queryRuntimeEstimator =  new QueryRuntimeEstimator(materializedPlansList, 0.99);
		queryRuntimeEstimator.estimateReattemptsForMaterlialization();  
		// Map<MaterializedPlan, Integer> reattemptsMatConfMap = queryRuntimeEstimator.getReattemptsForDifferentMaterializations();
		
		// Enumerate (Generator Different failure Scenarion) 
		// Each materialization configuration produces number of 
		// failure scenarions depends on how many level the mat 
		// configuration has. More levels more failure scenarions! 
		TotalRuntimeEstimator totalRuntimeEstimator = new TotalRuntimeEstimator(queryRuntimeEstimator.getMatPlans());
		totalRuntimeEstimator.calculateAverageWastedTimePerMatConf();
		totalRuntimeEstimator.calculateRuntTimeForMatConfs(); 
		
		List<Identifier> recommendedMatOpsIds = totalRuntimeEstimator.getTheRecommendedMaterializationOpsId(); 
		
		// uodate the compile plan with new mat ops resulted from the cost model 
		updateCompilePlanWithNewMat(recommendedMatOpsIds);
	}

	private void updateCompilePlanWithNewMat(
			List<Identifier> recommendedMatOpsIds) { 
		
		for (Identifier identifier : recommendedMatOpsIds) {
			this.compilePlan.getOperator(identifier).getResult().materialize(true); 
		}		
	}	
}
