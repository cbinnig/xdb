package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector; 


import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.utils.Identifier;


public class BushyCPlanMatEnumerator { 
	
	private CostModelQueryPlan costModelQPlan;
	// Compile Plan
	private CompilePlan compilePlan; 
	// 
	private Map<Identifier, Double> opsEstimatedRuntime = new HashMap<Identifier, Double>(); 
	//
	private Map<Identifier, Double> intermediadeResultsMatTime = new HashMap<Identifier, Double>(); 	
	// 
	private List<Identifier> nonMaterializableOps = new ArrayList<Identifier>();
	//
	private List<Identifier> forcedMat = new ArrayList<Identifier>(); 
    // 
	private List<Identifier> recommendedMatOpIds = new ArrayList<Identifier>();
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
	
	public CostModelQueryPlan getCostModelQueryPlan(){
		return this.costModelQPlan;
	}

	/**
	 * @return the nonMatOps
	 */
	public List<Identifier> getNonMatOps() {
		return nonMaterializableOps;
	}

	/**
	 * @param nonMatOps the nonMatOps to set
	 */
	public void setNonMatOps(List<Identifier> nonMaterializableOps) {
		this.nonMaterializableOps = nonMaterializableOps;
	}

	/**
	 * @return the forcedMat
	 */
	public List<Identifier> getForcedMat() {
		return forcedMat;
	}

	/**
	 * @param forcedMat the forcedMat to set
	 */
	public void setForcedMat(List<Identifier> forcedMat) {
		this.forcedMat = forcedMat;
	}  
	
	/**
	 * 
	 * Enumerate CompilePlan with different materialization strategies 
	 */
	public Error enumerateCompilePlan(){ 

		Error err = new Error();
		costModelQPlan = constructCModelQPlan(); 
		costModelQPlan.tracePlan("Cost_Model_Query_Plan_Original_");  
		System.out.println("1- Remove non mat ops from the statistics file!");
		// 1- First pruning rule: removing the non mat ops (those resulted from comparing the pysical vs compile plans) 
		err = removeNonMatOps();
        // trace plan 
		costModelQPlan.tracePlan("Cost_Model_Query_Plan_Prunned_FirstRule_");  
		System.out.println("2- Remove non mat from the bottom up merging");
		// 2- Second Pruning Rule: removing the non mat ops (those resulted from the comparing run-times of paths) 
		costModelQPlan.mergeOpsForRunTimes();  
		this.nonMaterializableOps = costModelQPlan.getNonMatOps();
		if(!this.nonMaterializableOps.isEmpty())
		   err = removeNonMatOps();
		costModelQPlan.tracePlan("Cost_Model_Query_Plan_Prunned_SecondRule_");  
		System.out.println("3- Remove Small Ops");
		// 3- Third Pruning Rule: removing small operators 
		costModelQPlan.mergeForSmallOperator(); 
		this.nonMaterializableOps = costModelQPlan.getNonMatOps();
		if(!this.nonMaterializableOps.isEmpty())
		   err = removeNonMatOps(); 
		costModelQPlan.tracePlan("Cost_Model_Query_Plan_Prunned_ThirdRule_");  
		// 4- Forth Pruning rule: path Comparisons 
		costModelQPlan.enumerate(); 
		//costModelQPlan.findBestMatConf();
		setRecommendedMatOpIds(costModelQPlan.findBestMatConf());
		return err;
	} 
	
	/**
	 * 
	 * @return
	 */
	public Error removeNonMatOps(){
		Error err = new Error();
		BushyPlanBottomUpTreeVisitor visitor = new BushyPlanBottomUpTreeVisitor();    
		System.out.println(this.nonMaterializableOps);
		visitor.setNonMatsOps(this.nonMaterializableOps);
		visitor.setCostModelQueryPlan(costModelQPlan);
		// applying the visitor will remove the non mat ops 
		err = this.compilePlan.applyVisitor(visitor);  
		return err;
	}
	
	/**
	 * Construct Cost Model Query Plan from the 
	 * original compile plan 
	 * 
	 */
	private CostModelQueryPlan constructCModelQPlan() {
		
		CostModelQueryPlan cModelQPlan = new CostModelQueryPlan();  
		
		cModelQPlan.setCplan(this.compilePlan);
		cModelQPlan.planId = this.compilePlan.getPlanId(); 
		cModelQPlan.setNonMatOps(this.nonMaterializableOps);
		// Copy roots 
		Vector<Identifier> roots = new Vector<Identifier>();  
		Vector<Identifier> compilePlanRoots = this.compilePlan.getRoots(); 
		for (Identifier identifier : compilePlanRoots) {
			roots.add(identifier.getChildId());
		}
		cModelQPlan.setRoots(roots);  
		
		// Copy leaves  
		Iterator<Identifier> it = this.compilePlan.getLeaves().iterator();  
		HashSet<Identifier> leaves = new HashSet<Identifier>(); 
		while(it.hasNext()){
			Identifier leafId = it.next();
			if(this.compilePlan.getOperator(leafId).getType() != EnumOperator.TABLE) {
			    leaves.add(leafId.getChildId()); 
			}
			else {
				// the leaves parents of the compile plan become 
				// the leaves of the cost model plan 
				Vector<AbstractCompileOperator> leafParents = this.compilePlan.getOperator(leafId).getParents(); 
				for (AbstractCompileOperator abstractCompileOperator : leafParents) {
					leaves.add(abstractCompileOperator.getOperatorId().getChildId());
				}
			}
		}
		cModelQPlan.setLeaves(leaves);  
		
		// Convert all Abstract Compile Ops to Cost Model Ops. 
		HashMap<Identifier, CostModelOperator> allCostModelOps = new HashMap<Identifier, CostModelOperator>();   
		List<CostModelOperator> allOpsAsList = new ArrayList<CostModelOperator>();
		Collection<AbstractCompileOperator> allOps = this.compilePlan.getOperators();  
		Map<Identifier, Identifier> costModelOpToCompileOp = new HashMap<Identifier, Identifier>(); 
		List<Integer> forcedMaterializedOpsIndexes = new ArrayList<Integer>();
		int compileOpIndex = 0;
		for (AbstractCompileOperator op : allOps) {
			if(op.getType() != EnumOperator.TABLE){
				CostModelOperator costModelOperator = new CostModelOperator();  
				costModelOperator.setId(op.getOperatorId().getChildId()); 
				costModelOperator.setType(op.getType().toString()); 
				if(this.opsEstimatedRuntime.get(costModelOperator.getId()) == null){
					costModelOperator.setOpRunTimeEstimate(0);
					costModelOperator.setOpMaterializationTimeEstimate(0);
				} else {
					costModelOperator.setOpRunTimeEstimate(this.opsEstimatedRuntime.get(costModelOperator.getId())); 
					costModelOperator.setOpMaterializationTimeEstimate(this.intermediadeResultsMatTime.get(costModelOperator.getId()));
				}
				costModelOperator.setDegreeOfPartitioning(op.getResult().getPartitionCount()); 
				costModelOpToCompileOp.put(op.getOperatorId().getChildId(), op.getOperatorId()); 
				if(op.getResult().materialize() || op.isRoot()){
					costModelOperator.setForcedMaterlialized(true); 
					costModelOperator.setMaterilaized(true);
					forcedMaterializedOpsIndexes.add(compileOpIndex); 
					System.out.println("Forced Materialized Op:"+ (op.getOperatorId()));
				} else {
					costModelOperator.setMaterilaized(false);
				}
				allCostModelOps.put(op.getOperatorId().getChildId(), costModelOperator);
				allOpsAsList.add(costModelOperator); 
			} 
			compileOpIndex++;
		}   
		cModelQPlan.setOperators(allCostModelOps);
		cModelQPlan.setAllOperators(allOpsAsList);
		cModelQPlan.setCostModelOpToCompileOp(costModelOpToCompileOp);
		cModelQPlan.setForcedMaterializedOpsIndexes(forcedMaterializedOpsIndexes);
		
		// Add Children to each operator  
		for (AbstractCompileOperator op : allOps) { 
			if(op.getType() != EnumOperator.TABLE){
				Vector<CostModelOperator> cModelOpChildren = new Vector<CostModelOperator>();
				Vector<AbstractCompileOperator> children = op.getChildren();  
				for (AbstractCompileOperator child : children) {
					if(allCostModelOps.get(child.getOperatorId().getChildId()) != null)
					   cModelOpChildren.add(allCostModelOps.get(child.getOperatorId().getChildId()));
				}  
				
				allCostModelOps.get(op.getOperatorId().getChildId()).setChildren(cModelOpChildren); 
			} 
		}
		
		// Add Parents 
		for (AbstractCompileOperator op : allOps) {  
			if(op.getType() != EnumOperator.TABLE){
				Vector<CostModelOperator> cModelOpParents = new Vector<CostModelOperator>();
				Vector<AbstractCompileOperator> parents = op.getParents();  
				for (AbstractCompileOperator parent : parents) {  
					if(allCostModelOps.get(parent.getOperatorId().getChildId()) != null)
					    cModelOpParents.add(allCostModelOps.get(parent.getOperatorId().getChildId()));
				} 
				allCostModelOps.get(op.getOperatorId().getChildId()).setParents(cModelOpParents);
			}
		} 

		return cModelQPlan;
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
	 * @return the intermediadeResultsMatTime
	 */
	public Map<Identifier, Double> getIntermediadeResultsMatTime() {
		return intermediadeResultsMatTime;
	}

	/**
	 * @param intermediadeResultsMatTime the intermediadeResultsMatTime to set
	 */
	public void setIntermediadeResultsMatTime(
			Map<Identifier, Double> intermediadeResultsMatTime) {
		this.intermediadeResultsMatTime = intermediadeResultsMatTime;
	}

	/**
	 * @return the recommendedMatOpIds
	 */
	public List<Identifier> getRecommendedMatOpIds() {
		return recommendedMatOpIds;
	}

	/**
	 * @param recommendedMatOpIds the recommendedMatOpIds to set
	 */
	public void setRecommendedMatOpIds(List<Identifier> recommendedMatOpIds) {
		this.recommendedMatOpIds = recommendedMatOpIds;
	} 
	
}
