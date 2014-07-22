package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xdb.Config;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.utils.Identifier; 
import org.xdb.error.Error;


public class MaterializationOpsSuggester { 
	
	private CostModelQueryPlan costModelQueryPlan;
	
	// CompilePlan to annotate extra materialization point
	private CompilePlan compilePlan; 
	// Hashmap to store the runtime for every operator. 
	private Map<Identifier, Double> opsEstimatedRuntime = new HashMap<Identifier, Double>(); 
	
	private Map<Identifier, Double> intermediadeResultsMatTime = new HashMap<Identifier, Double>();    
	
	private List<Identifier> nonMaterializableOps = new ArrayList<Identifier>();
	
	private List<Identifier> recommendedMatOpsIds = new ArrayList<Identifier>(); 
	
	
	private int mtbf;
	private int mttr; 
	
	public MaterializationOpsSuggester(CompilePlan compilePlan, Map<Identifier, Double> opsEstimatedRuntime, 
			Map<Identifier, Double> intermediadeResultsMatTime, List<Identifier> nonMaterializableOs, int MTBF, int MTTR){ 
		this.compilePlan = compilePlan;
		this.opsEstimatedRuntime = opsEstimatedRuntime; 
		this.intermediadeResultsMatTime = intermediadeResultsMatTime;   
		this.setNonMaterializableOps(nonMaterializableOs);
		this.mtbf = MTBF; 
		this.mttr = MTTR; 	
	} 
	
	/**
	 * 
	 */
	public Error startCostModel(){
		
		Error err = new Error();
		System.out.println("MTBF: "+this.mtbf);
		System.out.println("MTTR: "+this.mttr);
		
		if(Config.COMPILE_FT_MODE.equalsIgnoreCase("smart")){
			err = startSmartMaterilizationFinder();
		} else if(Config.COMPILE_FT_MODE.equalsIgnoreCase("naive")) {
			err = startNaiveMaterilizationFinder();
		} else if(Config.COMPILE_FT_MODE.equalsIgnoreCase("bushy")){
			err = startBushyMaterializationFinder();
		}
	    
		return err;
	} 
	
	private Error startBushyMaterializationFinder() {
		Error err = new Error(); 
		BushyCPlanMatEnumerator bushyTreeEnumerator = new BushyCPlanMatEnumerator(this.mtbf);
		bushyTreeEnumerator.setNonMatOps(this.nonMaterializableOps); 
		bushyTreeEnumerator.setOpsEstimatedRuntime(opsEstimatedRuntime);
		bushyTreeEnumerator.setIntermediadeResultsMatTime(intermediadeResultsMatTime);
		bushyTreeEnumerator.setCompilePlan(this.compilePlan); 
		bushyTreeEnumerator.enumerateCompilePlan();   
		this.costModelQueryPlan = bushyTreeEnumerator.getCostModelQueryPlan();
		this.recommendedMatOpsIds = bushyTreeEnumerator.getRecommendedMatOpIds();  
		updateCompilePlanWithNewMat();
		return err;
	}

	/**
	 * 
	 * @return
	 */
	public List<Identifier> getRecommendedMatOpsIds(){
		return this.recommendedMatOpsIds;
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
	 * 
	 * @return
	 */
	private Error startSmartMaterilizationFinder(){
		Error err = new Error();
		// Traverse the tree from top to bottom, in the way 
		// adding the left child operators so will 
		CostModelTreePlanVisitor visitor = new CostModelTreePlanVisitor();    
		visitor.setNonMatsOps(this.nonMaterializableOps);
		err = this.compilePlan.applyVisitor(visitor); 
		
		List<AbstractCompileOperator> sortedCompileOps = new ArrayList<AbstractCompileOperator>();
		sortedCompileOps = visitor.getOps();
		mapCompileOpsToCostModelOps(sortedCompileOps);
		
		return err; 
	} 
	
	/**
	 * 
	 * @return
	 */
	private Error startNaiveMaterilizationFinder() {
		Error err = new Error();
		Collection<AbstractCompileOperator> ops = this.compilePlan.getOperators();  
		for (AbstractCompileOperator abstractCompileOperator : ops) {
			if(abstractCompileOperator.getType() == EnumOperator.TABLE
					|| nonMaterializableOps.contains(abstractCompileOperator.getOperatorId().getChildId())) 
				continue;
			abstractCompileOperator.getResult().materialize(true);    
			this.recommendedMatOpsIds.add(abstractCompileOperator.getOperatorId().getChildId());
		}
		
		this.compilePlan.setMatOps(recommendedMatOpsIds);
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
		List<Integer> forcedMaterializedOpsIndexes = new ArrayList<Integer>();  
		int compileOpsNumber = sortedCompileOps.size();  
		Map<Identifier, Identifier> mapCostModelOpToCompileOp = new 
				HashMap<Identifier, Identifier>(); 
		for (int i= compileOpsNumber -1; i>=0; i--) {
			AbstractCompileOperator compileOp = sortedCompileOps.get(i); 
			CostModelOperator costModelOp = new CostModelOperator();
			Identifier costModelOpId = compileOp.getOperatorId().getChildId(); 
			costModelOp.setId(costModelOpId); 
			mapCostModelOpToCompileOp.put(costModelOpId, compileOp.getOperatorId());
			// if there are roots or set in advance to be materialized 
			// then set them as forced materialized 
			if(compileOp.getResult().materialize() || compileOp.isRoot()){
				costModelOp.setForcedMaterlialized(true); 
				costModelOp.setMaterilaized(true);
				forcedMaterializedOpsIndexes.add(compileOpsNumber -i -1); 
				System.out.println("Forced Materialized Op:"+ (compileOp.getOperatorId()));
			} else {
				costModelOp.setMaterilaized(false);
			}
		    
			costModelOp.setOpMaterializationTimeEstimate(intermediadeResultsMatTime.get(costModelOpId)); 
			costModelOp.setOpRunTimeEstimate(opsEstimatedRuntime.get(costModelOpId));
			// Number of nodes the operator executes on 
			costModelOp.setDegreeOfPartitioning(compileOp.getResult().getPartitionCount()); 
			costModelOps.add(costModelOp);
		} 

		// Apply our Cost Model 
		applyCostModel(costModelOps, forcedMaterializedOpsIndexes, mapCostModelOpToCompileOp); 
		
	}
    
	/**
	 * 
	 * @param costModelOps
	 * @param forcedMaterializedOpsIndexes 
	 * @param mapCostModelOpToCompileOp 
	 */
	private void applyCostModel(List<CostModelOperator> costModelOps,
			List<Integer> forcedMaterializedOpsIndexes, Map<Identifier, Identifier> mapCostModelOpToCompileOp) {
		
		// Initiate a query plan 
		this.costModelQueryPlan = new CostModelQueryPlan(costModelOps, 
				forcedMaterializedOpsIndexes, mapCostModelOpToCompileOp, null, this.mtbf, this.mttr); 
		// Enumerate Different Materialization Strategy 
		MaterlizationStrategyEnumerator matEnumerator = new MaterlizationStrategyEnumerator(costModelQueryPlan,forcedMaterializedOpsIndexes, this.mtbf);
		List<MaterializedPlan> materializedPlansList = matEnumerator.enumerateQueryPlan(); 
		
		// Calculate the number of re-attempts required to achieve the given success rate c
		// For each materialization configuration. 
		QueryRuntimeEstimator queryRuntimeEstimator =  new QueryRuntimeEstimator(materializedPlansList, 0.99, this.mtbf);
		queryRuntimeEstimator.estimateReattemptsForMaterlialization();  
		// Map<MaterializedPlan, Integer> reattemptsMatConfMap = queryRuntimeEstimator.getReattemptsForDifferentMaterializations();
		
		// Enumerate (Generator Different failure Scenarion) 
		// Each materialization configuration produces number of 
		// failure scenarions depends on how many level the mat 
		// configuration has. More levels more failure scenarions! 
		TotalRuntimeEstimator totalRuntimeEstimator = new TotalRuntimeEstimator(queryRuntimeEstimator.getMatPlans(), this.mttr);
		totalRuntimeEstimator.calculateAverageWastedTimePerMatConf();
		totalRuntimeEstimator.calculateRuntTimeForMatConfs(); 
		
		recommendedMatOpsIds = totalRuntimeEstimator.getTheRecommendedMaterializationOpsId(); 
		
		// uodate the compile plan with new mat ops resulted from the cost model 
		updateCompilePlanWithNewMat();
	}
    
	/**
	 * 
	 * @param recommendedMatOpsIds
	 */
	private void updateCompilePlanWithNewMat() { 
		this.compilePlan.setMatOps(recommendedMatOpsIds);
		for (Identifier identifier : recommendedMatOpsIds) {
			Identifier compileOpId = this.costModelQueryPlan.getCostModelOpToCompileOp().get(identifier);
			this.compilePlan.getOperator(compileOpId).getResult().materialize(true); 
		}		
	}

	/**
	 * @return the nonMaterializableOps
	 */
	public List<Identifier> getNonMaterializableOps() {
		return nonMaterializableOps;
	}

	/**
	 * @param nonMaterializableOps the nonMaterializableOps to set
	 */
	public void setNonMaterializableOps(List<Identifier> nonMaterializableOps) {
		this.nonMaterializableOps = nonMaterializableOps;
	}

	
}
