package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;


import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.utils.Dotty;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphFactory;
import com.oy.shared.lm.graph.GraphNode;

/**
 * 
 * @author Abdallah
 *
 */
public class CostModelQueryPlan { 

	public Identifier planId; 
	private CompilePlan cplan;
	private Vector<Identifier> roots = new Vector<Identifier>();
	private HashSet<Identifier> leaves = new HashSet<Identifier>();
	private Map<Identifier, CostModelOperator> operators = new HashMap<Identifier, CostModelOperator>();

	private List<CostModelOperator> allOperators = new ArrayList<CostModelOperator>(); 
	private List<Integer> forcedMaterializedOpsIndexes = new ArrayList<Integer>(); 
	private Map<Identifier, Identifier> costModelOpToCompileOp = new HashMap<Identifier, Identifier>(); 
	private List<Identifier> nonMatOps = new ArrayList<Identifier>();  

	private List<CostModelOperator> smallMergedOp = new ArrayList<CostModelOperator>(); 
	private double mergedOpSuccessProb; 

	private double dominantPathRuntime; 
	private int dominatPathIndex;  

	public static double BEST_PLAN_RUNTIME; 
	public static int BEST_PLAN_NUMBER; 
	// Store the dominant best n-level plan 
	public static List<CostModelOperator> BEST_N_LEVEL_PLAN = new ArrayList<CostModelOperator>(); 
	private List<CostModelOperator> dominantPath = new ArrayList<CostModelOperator>(); 

	private int matConfNumber;


	private List<BitSet> allMaterializationConf = new ArrayList<BitSet>(); 

	private List<List<Identifier>> paths = new ArrayList<List<Identifier>>();


	// Constructor
	public CostModelQueryPlan(List<CostModelOperator> allOperators,
			List<Integer> forcedMaterializedOpsIndexes, Map<Identifier, Identifier> costModelOpToCompileOp, List<Identifier> nonMatOps) {
		this.allOperators = allOperators; 
		this.forcedMaterializedOpsIndexes = forcedMaterializedOpsIndexes; 
		this.costModelOpToCompileOp = costModelOpToCompileOp;
	}  

	public CostModelQueryPlan copyPlan(){

		CostModelQueryPlan costModelPlan = new CostModelQueryPlan();
		costModelPlan.planId  = this.planId; 
		CompilePlan compilePlan = new CompilePlan();
		costModelPlan.setCplan(compilePlan);
		// Copy roots 
		Vector<Identifier> copiedRoots = new Vector<Identifier>(this.roots);  
		// Copy leaves 
		HashSet<Identifier> copyLeaves = new HashSet<Identifier>(this.leaves);  
		// Copy the hashmap of ops 
		Map<Identifier, CostModelOperator> copiedOps = new HashMap<Identifier, CostModelOperator>(); 

		// Copy the list of ops 
		List<CostModelOperator> copiedOpsList = new ArrayList<CostModelOperator>(); 
		for (CostModelOperator costModelOperator : this.allOperators) {
			CostModelOperator op = new CostModelOperator();		
			Identifier id = new Identifier(costModelOperator.getId().toString()); 
			int partitions = costModelOperator.getDegreeOfPartitioning();
			String type = costModelOperator.getType();
			double opRunTime = costModelOperator.getOpRunTimeEstimate();
			double opMatTime = costModelOperator.getOpMaterializationTimeEstimate();
			//op.setChildren(children); 
			//op.setParents(parents); 
			op.setOpRunTimeEstimate(opRunTime);
			op.setOpMaterializationTimeEstimate(opMatTime);
			op.setId(id);
			op.setDegreeOfPartitioning(partitions);
			op.setType(type);
			copiedOps.put(id, op);
			copiedOpsList.add(op);
		} 

		// Copy Parents and child of every operator
		for (CostModelOperator costModelOp : copiedOpsList) {
			Vector<CostModelOperator> opriginalParents = this.operators.get(costModelOp.getId()).getParents(); 
			for (CostModelOperator costModelOperator : opriginalParents) {
				costModelOp.addParent(copiedOps.get(costModelOperator.getId()));
			}

		} 

		for (CostModelOperator costModelOp : copiedOpsList) {
			Vector<CostModelOperator> opriginalChildren = this.operators.get(costModelOp.getId()).getChildren(); 
			for (CostModelOperator costModelOperator : opriginalChildren) {
				costModelOp.addChild(copiedOps.get(costModelOperator.getId()));
			}

		}

		costModelPlan.setRoots(copiedRoots);
		costModelPlan.setLeaves(copyLeaves);   
		costModelPlan.setOperators(copiedOps);
		costModelPlan.setAllOperators(copiedOpsList); 
		return costModelPlan;

	}

	public CostModelQueryPlan(){

	}

	public void setNonMatOps(List<Identifier> nonMatOps){
		this.nonMatOps = nonMatOps;
	}

	/**
	 * @return the roots
	 */
	public Vector<Identifier> getRoots() {
		return roots;
	}

	/**
	 * @param roots the roots to set
	 */
	public void setRoots(Vector<Identifier> roots) {
		this.roots = roots;
	}


	/**
	 * @return the leaves
	 */
	public HashSet<Identifier> getLeaves() {
		return leaves;
	}

	/**
	 * @param leaves the leaves to set
	 */
	public void setLeaves(HashSet<Identifier>  leaves) {
		this.leaves = leaves;
	}

	/**
	 * @return the allOperators
	 */
	public List<CostModelOperator> getAllOperators() {
		return allOperators;
	}

	/**
	 * @param allOperators the allOperators to set
	 */
	public void setAllOperators(List<CostModelOperator> allOperators) {
		this.allOperators = allOperators;
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

	/**
	 * @return the costModelOpToCompileOp
	 */
	public Map<Identifier, Identifier> getCostModelOpToCompileOp() {
		return costModelOpToCompileOp;
	}

	/**
	 * @param costModelOpToCompileOp the costModelOpToCompileOp to set
	 */
	public void setCostModelOpToCompileOp(Identifier costModelOpId, Identifier compileOpId) {
		this.costModelOpToCompileOp.put(costModelOpId, compileOpId);
	}

	/**
	 * @return the operators
	 */
	public Map<Identifier, CostModelOperator> getOperators() {
		return operators;
	}

	/**
	 * @param operators the operators to set
	 */
	public void setOperators(Map<Identifier, CostModelOperator> operators) {
		this.operators = operators;
	}   

	public List<Identifier> getNonMatOps(){
		return this.nonMatOps;
	}

	public void removeOperator(Identifier opId){
		//System.out.println("Remove Op: "+opId);
		this.allOperators.remove(this.operators.get(opId));
		this.operators.remove(opId);
		this.leaves.remove(opId);
		this.roots.remove(opId);		
	}

	public CostModelOperator getOperator(Identifier opId){
		return this.getOperators().get(opId);
	} 


	/**
	 * 
	 * 
	 */
	public void mergeOpsForRunTimes(){
		// clear the nonMatOps 
		this.nonMatOps.clear();
		for (Identifier rootId : this.roots) {
			CostModelOperator op = this.operators.get(rootId);
			visitOperatorBottomUp(op);
		}
	}
	/**
	 * bottom up visitor 
	 * @param rootId
	 */
	private void visitOperatorBottomUp(CostModelOperator op) {
		// Get the operator children 
		Vector<CostModelOperator> children = op.getChildren();
		for (CostModelOperator childOperator : children) {
			// calculate the total runtime for a PATH connects 
			// a parent with a child 
			//storePathRuntime(op, childOperator);
			visitOperatorBottomUp(childOperator);
		}
		if(op.getChildren().size() > 0){
			if(checkMergeConditions(op)){
				Vector<CostModelOperator> ops = op.getChildren(); 
				for (CostModelOperator child : ops) {
					this.nonMatOps.add(child.getId());
				} 
			}
		}

	}

	/**
	 * 
	 * @param op (the parent operator)
	 * @return
	 */
	private boolean checkMergeConditions(CostModelOperator op) {
		boolean isMergingSatisfied = true; 
		Vector<CostModelOperator> opChilds = op.getChildren(); 
		double mergedOpsRunTime = 0.0; 
		double mergedOpTotalTime = 0.0;
		for (CostModelOperator child : opChilds) {
			mergedOpsRunTime += child.getOpRunTimeEstimate();
		} 
		mergedOpsRunTime = (mergedOpsRunTime + op.getOpRunTimeEstimate()) *Config.COMPILE_FT_PIPELINE_CNST;
		mergedOpTotalTime = mergedOpsRunTime + op.getOpMaterializationTimeEstimate(); 
		// compare the merged op run time with all individual paths 
		Vector<CostModelOperator> children = op.getChildren(); 
		for (CostModelOperator child : children) {
			if(mergedOpTotalTime > (child.getOpMaterializationTimeEstimate() + child.getOpRunTimeEstimate())){
				isMergingSatisfied = false; 
				break;
			}

		} 
		// if the merging condition is satisfied, then update the 
		// the parent op with the new runtime (merging with parent)
		if(isMergingSatisfied) {
			op.setOpRunTimeEstimate(mergedOpsRunTime);
		}
		return isMergingSatisfied; 
	} 

	/**
	 * 
	 */
	public void mergeForSmallOperator(){
		this.nonMatOps.clear();
		for (Identifier rootId : this.roots) {
			CostModelOperator op = this.operators.get(rootId); 
			Level level = new Level(); 
			List<CostModelOperator> mergedOp = new ArrayList<CostModelOperator>(); 
			mergedOp.add(op); 
			level.setSubQquery(mergedOp); 
			// TODO read the partitions number from the op
			level.setNumberOfPartitions(op.getDegreeOfPartitioning());
			visitOperatorTopDown(op, level);
		}
	}

	/**
	 * 
	 * @param op
	 */
	private void visitOperatorTopDown(CostModelOperator op, Level mergeOps ) {
		boolean clearFlag;
		mergedOpSuccessProb = calculateMergedOpSuccessProb(mergeOps);
		if(mergedOpSuccessProb < Config.COMPILE_FT_MERGING_SMALLOPS_THRESHOLD 
				|| op.getChildren().size() > 1){
			clearFlag = true;              			
		} else { 
			// set the new merged small op to the non materialized list 
			mergeSmallOps(mergeOps);	
			clearFlag = false;
		}

		Vector<CostModelOperator> children = op.getChildren(); 
		for (CostModelOperator child : children) {
			Level level = new Level();
			if(clearFlag) { 
				smallMergedOp.clear();
			}
			smallMergedOp.add(child);  
			level.setSubQquery(smallMergedOp); 
			// TODO read the partitions number from the op
			level.setNumberOfPartitions(op.getDegreeOfPartitioning());
			visitOperatorTopDown(child, level);
		}		
	}

	/**
	 * 
	 * @param mergeOps
	 */
	private void mergeSmallOps(Level mergeOps) {
		if(mergeOps.getSubQquery().size() >1) { 
			CostModelOperator smallOp = mergeOps.getSubQquery().get(mergeOps.getSubQquery().size()-1);
			Identifier smallOpId = smallOp.getId(); 
			double smallOpRT = smallOp.getOpRunTimeEstimate(); 
			CostModelOperator mergedOp = mergeOps.getSubQquery().get(0); // the first op in the level 
			double mergedOpRunTime = mergeOps.getSubQquery().get(0).getOpRunTimeEstimate() + smallOpRT; 
			// update nonMatOp list 
			this.nonMatOps.add(smallOpId); 
			this.getOperator(mergedOp.getId()).setOpRunTimeEstimate(mergedOpRunTime);
		}
	}

	/**
	 * 
	 * @param op
	 * @param mergeCandidateOp
	 */
	private double calculateMergedOpSuccessProb(Level mergedOps) {
		// An instance from this class to calculate the success probability of the merged op
		QueryRuntimeEstimator queryRuntimeEstimator = new QueryRuntimeEstimator();  
		double mergedSuccessProbability = queryRuntimeEstimator.calculateSuccessProbForLevel(mergedOps); 
		return mergedSuccessProbability;

	}


	/**
	 * @return the smallMergedOp
	 */
	public List<CostModelOperator> getSmallMergedOp() {
		return smallMergedOp;
	}

	/**
	 * @param smallMergedOp the smallMergedOp to set
	 */
	public void setSmallMergedOp(List<CostModelOperator> smallMergedOp) {
		this.smallMergedOp = smallMergedOp;
	}

	/**
	 * 
	 */
	public void enumerate() { 
		List<Integer> forcedMaterializedOps = new ArrayList<Integer>(); 
		forcedMaterializedOps.add(3);
		forcedMaterializedOps.add(4); 
		MaterlizationStrategyEnumerator enumerator = new MaterlizationStrategyEnumerator(this, forcedMaterializedOps);
		this.allMaterializationConf = enumerator.enumerateBushyTree(); 
		System.out.println("The number of Possible Configuration: "+allMaterializationConf.size());		
	}

	/**
	 * 
	 */
	public void findBestMatConf() {
		// label the ops with the materialization flag 
		List<BitSet> matConfs = this.allMaterializationConf;  
		int matConfNum=0;
		for (BitSet bitSet : matConfs) { 
			// Get a Copy from the orignal plan 
			CostModelQueryPlan copyPlan = this.copyPlan();
			this.nonMatOps.clear();
			for(int i=0; i<this.allOperators.size(); i++){
				if(bitSet.get(i)){
					copyPlan.allOperators.get(i).setMaterilaized(true);  
					copyPlan.operators.get(copyPlan.allOperators.get(i).getId()).setMaterilaized(true);
				} else {
					this.nonMatOps.add(copyPlan.allOperators.get(i).getId());
				}
			} 
			System.out.println("Mat Conf "+matConfNum);
			// merge nonmat ops to their parents without removal 
			mergeNonMatOps(copyPlan); 
			// remove the ops that will not be materialized
			removeNonMatOps(copyPlan);
			copyPlan.tracePlan("Mat Conf_"+matConfNum+"_");
			// Analyze Each Mat Conf 
			analyzeSingleMatConf(copyPlan, matConfNum);
			matConfNum++;
		}  

		System.out.println("Best Plan: "+BEST_PLAN_NUMBER + " with Runtime: "+BEST_PLAN_RUNTIME);

	} 

	private void mergeNonMatOps(CostModelQueryPlan copyPlan) {
		BitSet checkedNonMatOps= new BitSet(this.nonMatOps.size());
		for(int i=0; i<this.nonMatOps.size(); i++){
			if(checkedNonMatOps.get(i))
				continue;
			checkedNonMatOps.set(i);
			CostModelOperator op = copyPlan.operators.get(this.nonMatOps.get(i));
			if(copyPlan.roots.contains(op.getId()))
				continue;
			CostModelOperator opParent = op.getParents().get(0); 
			double runTime = op.getOpRunTimeEstimate();
			// Check for other non mat ops having the same parents 
			for(int j=0; j<this.nonMatOps.size(); j++){
				if(checkedNonMatOps.get(j)){
					continue;
				}
				else { 
					CostModelOperator siblingOp = copyPlan.operators.get(this.nonMatOps.get(j));
					CostModelOperator siblingParent = siblingOp.getParents().get(0); 
					if(opParent == siblingParent){
						checkedNonMatOps.set(j);
						runTime += siblingOp.getOpRunTimeEstimate();
					}
				}
			} 
			// Set the parent the merged runtime 
			opParent.setOpRunTimeEstimate((opParent.getOpRunTimeEstimate() + runTime)*Config.COMPILE_FT_PIPELINE_CNST);	
		}

	}

	/**
	 * 
	 */
	public Error removeNonMatOps(CostModelQueryPlan copyPlan){
		Error err = new Error();
		BushyPlanBottomUpTreeVisitor visitor = new BushyPlanBottomUpTreeVisitor();    
		visitor.setNonMatsOps(this.nonMatOps);
		visitor.setCostModelQueryPlan(copyPlan);
		// applying the visitor will remove the non mat ops 
		err = this.cplan.applyVisitor(visitor);  
		return err;
	}

	private void analyzeSingleMatConf(CostModelQueryPlan copyPlan, int matConfNum) {
		// Visit the paths 
		boolean negelctPlan = false;
		paths.clear();
		for (Identifier rootId : copyPlan.roots) {
			findPaths(copyPlan, rootId, new ArrayList<Identifier>());
		} 
		for(int i=0; i<paths.size();i++){
			System.out.println(paths.get(i));
			// Apply our cost model to each path to get the total runtime 
			MaterlizationStrategyEnumerator costModelApplier = new MaterlizationStrategyEnumerator(Config.DOOMDB_MTBF);
			// Build the list of ops from the list of id 
			List<Identifier> pathOpsIds = paths.get(i);
			List<CostModelOperator> pathOps = new ArrayList<CostModelOperator>();
			for(int j=pathOpsIds.size() -1 ; j>=0; j--){
				CostModelOperator op = copyPlan.operators.get(pathOpsIds.get(j)); 
				op.setMaterilaized(true);
				pathOps.add(op);
			}
			Collections.sort(pathOps);
			MaterializedPlan matPlan = costModelApplier.buildMaterliazedPlan(pathOps); 
			List<MaterializedPlan> materializedPlansList = new ArrayList<MaterializedPlan>();
			materializedPlansList.add(matPlan); 
			// Apply a prunning rule for paths 
			// If the runtime for a certain path in a certain conf 
			// is greater than the runtime of the dominant path (for)
			// the best plan found at this point. Then neglect the whole 
			// conf. 
			if(matPlan.getRunTimeWithoutFailure() >= BEST_PLAN_RUNTIME && matConfNum != 0) {
				negelctPlan = true;
				break;
			} 
			// Applying the seconf path pruning rule (collapsed Ops Comparsion)
			if(compareCollapsedOps(pathOps) && matConfNum != 0){
				negelctPlan = true; 
				System.out.println("Mat Conf: "+matConfNum +" is neglected!");
				break;
			}

			// Calculate the number of re-attempts required to achieve the given success rate c
			// For each materialization configuration. 
			QueryRuntimeEstimator queryRuntimeEstimator =  new QueryRuntimeEstimator(materializedPlansList, 0.99);
			queryRuntimeEstimator.estimateReattemptsForMaterlialization();  
			queryRuntimeEstimator.getReattemptsForDifferentMaterializations();

			// Enumerate (Generator Different failure Scenarion) 
			// Each materialization configuration produces number of 
			// failure scenarions depends on how many level the mat 
			// configuration has. More levels more failure scenarions! 
			TotalRuntimeEstimator totalRuntimeEstimator = new TotalRuntimeEstimator(queryRuntimeEstimator.getMatPlans());
			totalRuntimeEstimator.calculateAverageWastedTimePerMatConf();
			totalRuntimeEstimator.calculateRuntTimeForMatConfs();  
			if(matPlan.getRunTime() > copyPlan.dominantPathRuntime) {
				copyPlan.dominantPathRuntime = matPlan.getRunTime(); 
				copyPlan.setDominantPath(pathOps);
				copyPlan.setMatConfNumber(matConfNum);
			}

		}  

		if(matConfNum == 0 && !negelctPlan) {
			BEST_PLAN_RUNTIME = copyPlan.dominantPathRuntime;   
			BEST_PLAN_NUMBER = copyPlan.matConfNumber; 
			BEST_N_LEVEL_PLAN = copyPlan.dominantPath;
		}
		else if(!negelctPlan) {
			if(BEST_PLAN_RUNTIME > copyPlan.dominantPathRuntime) {
				BEST_PLAN_RUNTIME = copyPlan.dominantPathRuntime;
				BEST_PLAN_NUMBER = copyPlan.matConfNumber; 
				BEST_N_LEVEL_PLAN = copyPlan.dominantPath;
			}
		}

	}

	private boolean compareCollapsedOps(List<CostModelOperator> pathOps) {

		boolean neglectPlan = true;
		if(pathOps.size() < BEST_N_LEVEL_PLAN.size()) 
			return !neglectPlan;
		else {
			for(int i=0; i<BEST_N_LEVEL_PLAN.size(); i++){
				if((pathOps.get(i).getOpMaterializationTimeEstimate() + pathOps.get(i).getOpRunTimeEstimate()) <= 
						BEST_N_LEVEL_PLAN.get(i).getOpMaterializationTimeEstimate() + BEST_N_LEVEL_PLAN.get(i).getOpRunTimeEstimate()) { 
					neglectPlan = false;
				}
			}
		}

		return neglectPlan; 
	}

	private void findPaths(CostModelQueryPlan copyPlan, Identifier rootId, ArrayList<Identifier> path) {

		path.add(rootId);
		CostModelOperator op = copyPlan.operators.get(rootId);
		Vector<CostModelOperator> children = op.getChildren();
		if(children.size() > 0) {
			for(int i=0; i< children.size(); i++){
				findPaths(copyPlan, children.get(i).getId(), path);
			} 
		}
		else if(op.getChildren().size() == 0){
			List<Identifier> pathIds = new ArrayList<Identifier>(); 
			pathIds.addAll(path);
			paths.add(pathIds);
		} 
		path.remove(rootId);

	}

	/**
	 * @return the cplan
	 */
	public CompilePlan getCplan() {
		return cplan;
	}

	/**
	 * @param cplan the cplan to set
	 */
	public void setCplan(CompilePlan cplan) {
		this.cplan = cplan;
	} 

	/**
	 * Generates a visual graph representation of the compile plan
	 */
	public String tracePlan(String fileName) {
		fileName += this.planId;

		final Graph graph = GraphFactory.newGraph();

		final Map<Identifier, GraphNode> dottyNodes = new HashMap<Identifier, GraphNode>();

		// add nodes to plan
		for (Identifier opId : this.operators.keySet()) {
			GraphNode node = graph.addNode();

			CostModelOperator costModelOp = this.operators.get(opId);
			node.getInfo().setCaption(costModelOp.toString());
			if(this.nonMatOps.contains(opId))
				node.getInfo().setFillColor("red");
			dottyNodes.put(opId, node); 
		}

		for (Identifier opId : this.operators.keySet()) {  
			CostModelOperator costModelOp = this.operators.get(opId); 
			GraphNode from = dottyNodes.get(costModelOp.getId());
			Vector<CostModelOperator> children = costModelOp.getChildren(); 
			for (CostModelOperator costModelOperator : children) {
				GraphNode to = dottyNodes.get(costModelOperator.getId());
				graph.addEdge(from, to);
			}
		}
		return Dotty.dot2Img(graph, fileName);
	}

	/**
	 * @return the dominantPathRuntime
	 */
	public double getDominantPathRuntime() {
		return dominantPathRuntime;
	}

	/**
	 * @param dominantPathRuntime the dominantPathRuntime to set
	 */
	public void setDominantPathRuntime(double dominantPathRuntime) {
		this.dominantPathRuntime = dominantPathRuntime;
	}

	/**
	 * @return the dominatPathIndex
	 */
	public int getDominatPathIndex() {
		return dominatPathIndex;
	}

	/**
	 * @param dominatPathIndex the dominatPathIndex to set
	 */
	public void setDominatPathIndex(int dominatPathIndex) {
		this.dominatPathIndex = dominatPathIndex;
	}

	/**
	 * @return the matConfNumber
	 */
	public int getMatConfNumber() {
		return matConfNumber;
	}

	/**
	 * @param matConfNumber the matConfNumber to set
	 */
	public void setMatConfNumber(int matConfNumber) {
		this.matConfNumber = matConfNumber;
	}

	/**
	 * @return the dominantPath
	 */
	public List<CostModelOperator> getDominantPath() {
		return dominantPath;
	}

	/**
	 * @param dominantPath the dominantPath to set
	 */
	public void setDominantPath(List<CostModelOperator> dominantPath) {
		this.dominantPath = dominantPath;
	}

}
