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
	private double mergedOpRuntime;
	private double mergedOpSuccessProb;

	private double dominantPathRuntime;
	private int dominatPathIndex;

	public double BEST_PLAN_RUNTIME;
	public int BEST_PLAN_NUMBER;
	// Store the dominant best n-level plan
	public List<CostModelOperator> BEST_N_LEVEL_PLAN = new ArrayList<CostModelOperator>();
	private List<CostModelOperator> dominantPath = new ArrayList<CostModelOperator>();

	private int matConfNumber;
	private List<BitSet> allMaterializationConf = new ArrayList<BitSet>();
	private List<List<Identifier>> paths = new ArrayList<List<Identifier>>();

	public int NEGLECTED_CONFS_NUMBER;
	private int mtbf = 0;
	private int mttr = 0;

	// Constructor
	public CostModelQueryPlan(List<CostModelOperator> allOperators,
			List<Integer> forcedMaterializedOpsIndexes,
			Map<Identifier, Identifier> costModelOpToCompileOp,
			List<Identifier> nonMatOps, int mtbf, int mttr) {
		this.allOperators = allOperators;
		this.forcedMaterializedOpsIndexes = forcedMaterializedOpsIndexes;
		this.costModelOpToCompileOp = costModelOpToCompileOp;
		this.mtbf = mtbf;
		this.mttr = mttr;
	}

	public CostModelQueryPlan copyPlan() {

		CostModelQueryPlan costModelPlan = new CostModelQueryPlan(this.mtbf);
		costModelPlan.mtbf = this.mtbf;
		costModelPlan.mttr = this.mttr;

		costModelPlan.planId = this.planId;
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
			double opMatTime = costModelOperator
					.getOpMaterializationTimeEstimate();
			// op.setChildren(children);
			// op.setParents(parents);
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
			Vector<CostModelOperator> opriginalParents = this.operators.get(
					costModelOp.getId()).getParents();
			for (CostModelOperator costModelOperator : opriginalParents) {
				costModelOp.addParent(copiedOps.get(costModelOperator.getId()));
			}

		}

		for (CostModelOperator costModelOp : copiedOpsList) {
			Vector<CostModelOperator> opriginalChildren = this.operators.get(
					costModelOp.getId()).getChildren();
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

	public CostModelQueryPlan(int mtbf) {
		this.mtbf = mtbf;
	}

	public void setNonMatOps(List<Identifier> nonMatOps) {
		this.nonMatOps = nonMatOps;
	}

	/**
	 * @return the roots
	 */
	public Vector<Identifier> getRoots() {
		return roots;
	}

	/**
	 * @param roots
	 *            the roots to set
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
	 * @param leaves
	 *            the leaves to set
	 */
	public void setLeaves(HashSet<Identifier> leaves) {
		this.leaves = leaves;
	}

	/**
	 * @return the allOperators
	 */
	public List<CostModelOperator> getAllOperators() {
		return allOperators;
	}

	/**
	 * @param allOperators
	 *            the allOperators to set
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
	 * @param forcedMaterializedOpsIndexes
	 *            the forcedMaterializedOpsIndexes to set
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
	 * @param costModelOpToCompileOp
	 *            the costModelOpToCompileOp to set
	 */
	public void setCostModelOpToCompileOp(
			Map<Identifier, Identifier> costModelOpToCompileOp) {
		this.costModelOpToCompileOp = costModelOpToCompileOp;
	}

	/**
	 * @return the operators
	 */
	public Map<Identifier, CostModelOperator> getOperators() {
		return operators;
	}

	/**
	 * @param operators
	 *            the operators to set
	 */
	public void setOperators(Map<Identifier, CostModelOperator> operators) {
		this.operators = operators;
	}

	public List<Identifier> getNonMatOps() {
		return this.nonMatOps;
	}

	public void removeOperator(Identifier opId) {
		// System.out.println("Remove Op: "+opId);
		this.allOperators.remove(this.operators.get(opId));
		this.operators.remove(opId);
		this.leaves.remove(opId);
		this.roots.remove(opId);
	}

	public CostModelOperator getOperator(Identifier opId) {
		return this.getOperators().get(opId);
	}

	/**
	 * This function used in the pruning rule 2: removing the ops has high
	 * materialization time.
	 * 
	 */
	public void mergeOpsForRunTimes() {
		// clear the nonMatOps
		this.nonMatOps.clear();
		for (Identifier rootId : this.roots) {
			CostModelOperator op = this.operators.get(rootId);
			visitOperatorBottomUp(op);
		}
	}

	/**
	 * bottom up visitor Visit the nodes bottom up, check the merge condition at
	 * every node
	 * 
	 * @param rootId
	 */
	private void visitOperatorBottomUp(CostModelOperator op) {
		// Get the operator children
		Vector<CostModelOperator> children = op.getChildren();
		for (CostModelOperator childOperator : children) {
			// calculate the total runtime for a PATH connects
			// a parent with a child
			// storePathRuntime(op, childOperator);
			visitOperatorBottomUp(childOperator);
		}
		if (op.getChildren().size() > 0) {
			if (checkMergeConditions(op)) {
				Vector<CostModelOperator> ops = op.getChildren();
				for (CostModelOperator child : ops) {
					if (!child.isForcedMaterlialized())
						this.nonMatOps.add(child.getId());
				}
			}
		}

	}

	/**
	 * At every node check the merge condition for pruning the ops have very
	 * high materialization time.
	 * 
	 * @param op
	 *            (the parent operator)
	 * @return
	 */
	private boolean checkMergeConditions(CostModelOperator op) {
		boolean isMergingSatisfied = true;
		Vector<CostModelOperator> opChilds = op.getChildren();
		double mergedOpsRunTime = 0.0;
		double mergedOpTotalTime = 0.0;
		for (CostModelOperator child : opChilds) {
			if (child.isForcedMaterlialized())
				continue;
			mergedOpsRunTime += child.getOpRunTimeEstimate();
		}
		mergedOpsRunTime = (mergedOpsRunTime + op.getOpRunTimeEstimate())
				* Config.COMPILE_FT_PIPELINE_CNST;
		mergedOpTotalTime = mergedOpsRunTime
				+ op.getOpMaterializationTimeEstimate();
		// compare the merged op run time with all individual paths
		Vector<CostModelOperator> children = op.getChildren();
		for (CostModelOperator child : children) {
			if (mergedOpTotalTime > (child.getOpMaterializationTimeEstimate() + child
					.getOpRunTimeEstimate()) || child.isForcedMaterlialized()) {
				isMergingSatisfied = false;
				break;
			}

		}
		// if the merging condition is satisfied, then update the
		// the parent op with the new runtime (merging with parent)
		if (isMergingSatisfied) {
			op.setOpRunTimeEstimate(mergedOpsRunTime);
		}
		return isMergingSatisfied;
	}

	/**
	 * Applying the third rule, merge the small ops which have very high success
	 * rate.
	 */
	public void mergeForSmallOperator() {
		this.nonMatOps.clear();
		for (Identifier rootId : this.roots) {
			CostModelOperator op = this.operators.get(rootId);
			Level level = new Level();
			List<CostModelOperator> mergedOp = new ArrayList<CostModelOperator>();
			mergedOp.add(op);
			level.setSubQquery(mergedOp);
			level.setLevelRuntimeEstimate(op.getOpRunTimeEstimate());
			level.setMaterializationRuntimeestimate(op
					.getOpMaterializationTimeEstimate());
			// TODO read the partitions number from the op
			level.setNumberOfPartitions(op.getDegreeOfPartitioning());
			visitOperatorTopDown(op, level);
		}
	}

	/**
	 * Top Down Visitior: used for merging small ops, we start from the root
	 * down until we find "fork" or another forced materialized op, and then we
	 * start the merging process again.
	 * 
	 * @param op
	 */
	private void visitOperatorTopDown(CostModelOperator op, Level mergeOps) {
		// it is set to true when the current merging operation finishes
		// or when we want to start new merging process.
		boolean clearFlag = true;
		mergedOpSuccessProb = calculateMergedOpSuccessProb(mergeOps);
		if (mergedOpSuccessProb < Config.COMPILE_FT_MERGING_SMALLOPS_THRESHOLD
				|| op.getChildren().size() > 1 || op.isForcedMaterlialized()) {
			clearFlag = true;
		} else if (mergedOpSuccessProb >= Config.COMPILE_FT_MERGING_SMALLOPS_THRESHOLD) {
			// add the small ops (that will be merged in on op) to the non
			// materialized list
			// in order to remove them from the plan, after updating
			// the runtime of the merged op
			mergeSmallOps(mergeOps);
			clearFlag = false;
		}

		Vector<CostModelOperator> children = op.getChildren();
		for (CostModelOperator child : children) {
			Level level = new Level();
			if (clearFlag) {
				smallMergedOp.clear();
				mergedOpRuntime = 0;
			}
			smallMergedOp.add(child);
			level.setSubQquery(smallMergedOp);
			mergedOpRuntime += child.getOpRunTimeEstimate();
			level.setMaterializationRuntimeestimate(child
					.getOpMaterializationTimeEstimate());
			level.setLevelRuntimeEstimate(mergedOpRuntime);
			level.setNumberOfPartitions(op.getDegreeOfPartitioning());
			visitOperatorTopDown(child, level);
		}
	}

	/**
	 * Merged the small ops in one op, it is the first in the list (the top op
	 * in the level)
	 * 
	 * @param mergeOps
	 */
	private void mergeSmallOps(Level mergeOps) {
		if (mergeOps.getSubQquery().size() > 1) {
			CostModelOperator smallOp = mergeOps.getSubQquery().get(
					mergeOps.getSubQquery().size() - 1);
			Identifier smallOpId = smallOp.getId();
			double smallOpRT = smallOp.getOpRunTimeEstimate();
			CostModelOperator mergedOp = mergeOps.getSubQquery().get(0); // the
																			// first
																			// op
																			// in
																			// the
																			// level
			double mergedOpRunTime;
			if (!mergedOp.isMerged()) {
				mergedOpRunTime = (mergedOp.getOpRunTimeEstimate() + smallOpRT)
						* Config.COMPILE_FT_PIPELINE_CNST;
				mergedOp.setMerged(true);
			} else {
				mergedOpRunTime = mergedOp.getOpRunTimeEstimate() + smallOpRT
						* Config.COMPILE_FT_PIPELINE_CNST;
			}
			// update nonMatOp list
			this.nonMatOps.add(smallOpId);
			this.getOperator(mergedOp.getId()).setOpRunTimeEstimate(
					mergedOpRunTime);
		}
	}

	/**
	 * Calculate the success probability for a set of small merged ops to check
	 * in the merging process if they exceed a given threshold
	 * 
	 * @param op
	 * @param mergeCandidateOp
	 */
	private double calculateMergedOpSuccessProb(Level mergedOps) {
		// An instance from this class to calculate the success probability of
		// the merged op
		QueryRuntimeEstimator queryRuntimeEstimator = new QueryRuntimeEstimator(this.mtbf);
		double mergedSuccessProbability = queryRuntimeEstimator
				.calculateSuccessProbForLevel(mergedOps);
		return mergedSuccessProbability;

	}

	/**
	 * @return the smallMergedOp
	 */
	public List<CostModelOperator> getSmallMergedOp() {
		return smallMergedOp;
	}

	/**
	 * @param smallMergedOp
	 *            the smallMergedOp to set
	 */
	public void setSmallMergedOp(List<CostModelOperator> smallMergedOp) {
		this.smallMergedOp = smallMergedOp;
	}

	/**
	 * 
	 */
	public void enumerate() {
		List<Integer> forcedMaterializedIndexes = new ArrayList<Integer>();
		for (int i = 0; i < this.allOperators.size(); i++) {
			if (this.allOperators.get(i).isForcedMaterlialized())
				forcedMaterializedIndexes.add(i);
		}
		MaterlizationStrategyEnumerator enumerator = new MaterlizationStrategyEnumerator(
				this, forcedMaterializedIndexes);
		this.allMaterializationConf = enumerator.enumerateBushyTree();
		System.out.println("The number of Possible Configuration: "
				+ allMaterializationConf.size());
	}

	/**
	 * 
	 */
	public List<Identifier> findBestMatConf() {
		// label the ops with the materialization flag
		List<BitSet> matConfs = this.allMaterializationConf;
		int matConfNum = 0;
		for (BitSet bitSet : matConfs) {
			// Get a Copy from the orignal plan
			CostModelQueryPlan copyPlan = this.copyPlan();
			this.nonMatOps.clear();
			for (int i = 0; i < this.allOperators.size(); i++) {
				if (bitSet.get(i)) {
					copyPlan.allOperators.get(i).setMaterilaized(true);
					copyPlan.operators
							.get(copyPlan.allOperators.get(i).getId())
							.setMaterilaized(true);
				} else {
					this.nonMatOps.add(copyPlan.allOperators.get(i).getId());
				}
			}
			System.out.println("Mat Conf " + matConfNum);
			// merge nonmat ops to their parents without removal
			mergeNonMatOpsForEnumeratedPlan(copyPlan);
			// remove the ops that will not be materialized
			removeNonMatOps(copyPlan);
			// copyPlan.tracePlan("Mat Conf_"+matConfNum+"_");
			// Analyze Each Mat Conf
			analyzeSingleMatConf(copyPlan, matConfNum);
			// NEGLECTED_CONFS_NUMBER = 0;
			matConfNum++;
		}

		// System.out.println("Best Plan: "+BEST_PLAN_NUMBER +
		// " with Runtime: "+BEST_PLAN_RUNTIME);

		//
		List<Identifier> recommendedMatOpIds = new ArrayList<Identifier>();
		BitSet bestPlanAsBitSet = matConfs.get(BEST_PLAN_NUMBER);
		for (int i = 0; i < this.allOperators.size(); i++) {
			if (bestPlanAsBitSet.get(i)) {
				recommendedMatOpIds.add(this.allOperators.get(i).getId());
			}
		}

		return recommendedMatOpIds;
	}

	/**
	 * merg nonmat ops, comes from the eumeration of different mat cond
	 * 
	 * @param copyPlan
	 */
	private void mergeNonMatOpsForEnumeratedPlan(CostModelQueryPlan copyPlan) {
		for (Identifier rootId : copyPlan.roots) {
			CostModelOperator op = copyPlan.operators.get(rootId);
			visitOperatorBottomUpForEnumeratedPlan(op);
		}
	}

	/**
	 * bottom up visitor Visit the nodes bottom up, check the merge condition at
	 * every node
	 * 
	 * @param rootId
	 */
	private void visitOperatorBottomUpForEnumeratedPlan(CostModelOperator op) {
		// Get the operator children
		Vector<CostModelOperator> children = op.getChildren();
		for (CostModelOperator childOperator : children) {
			// calculate the total runtime for a PATH connects
			// a parent with a child
			// storePathRuntime(op, childOperator);
			visitOperatorBottomUpForEnumeratedPlan(childOperator);
		}

		if (this.nonMatOps.contains(op.getId())) {
			// update the parent run time such that it collapse
			// in one op
			List<CostModelOperator> parents = op.getParents();
			for (CostModelOperator parent : parents) {
				// if the parent is also include another op (one sibling) then
				// we
				// add the new op runtime multiplied by the constant to the
				// current
				// merged runtime, otherwise we add them together and then we
				// multiplied them by that constant.
				if (parent.isMerged()) {
					parent.setOpRunTimeEstimate(parent.getOpRunTimeEstimate()
							+ op.getOpRunTimeEstimate()
							* Config.COMPILE_FT_PIPELINE_CNST);
				} else {
					parent.setMerged(true);
					parent.setOpRunTimeEstimate((parent.getOpRunTimeEstimate() + op
							.getOpRunTimeEstimate())
							* Config.COMPILE_FT_PIPELINE_CNST);
				}
			}
		}

	}

	/**
	 * remove ops from a plan due to merging process
	 */
	public Error removeNonMatOps(CostModelQueryPlan copyPlan) {
		Error err = new Error();
		BushyPlanBottomUpTreeVisitor visitor = new BushyPlanBottomUpTreeVisitor();
		visitor.setNonMatsOps(this.nonMatOps);
		visitor.setCostModelQueryPlan(copyPlan);
		// applying the visitor will remove the non mat ops
		err = this.cplan.applyVisitor(visitor);
		return err;
	}

	/**
	 * 
	 * @param copyPlan
	 * @param matConfNum
	 */
	private void analyzeSingleMatConf(CostModelQueryPlan copyPlan,
			int matConfNum) {
		// Visit the paths
		boolean negelctPlan = false;
		paths.clear();
		for (Identifier rootId : copyPlan.roots) {
			findPaths(copyPlan, rootId, new ArrayList<Identifier>());
		}
		for (int i = 0; i < paths.size(); i++) {
			System.out.println(paths.get(i));
			// Apply our cost model to each path to get the total runtime
			MaterlizationStrategyEnumerator costModelApplier = new MaterlizationStrategyEnumerator(
					this.mtbf);
			// Build the list of ops from the list of id
			List<Identifier> pathOpsIds = paths.get(i);
			List<CostModelOperator> pathOps = new ArrayList<CostModelOperator>();
			for (int j = pathOpsIds.size() - 1; j >= 0; j--) {
				CostModelOperator op = copyPlan.operators
						.get(pathOpsIds.get(j));
				op.setMaterilaized(true);
				pathOps.add(op);
			}
			Collections.sort(pathOps);

			MaterializedPlan matPlan = costModelApplier
					.buildMaterliazedPlan(pathOps);
			List<MaterializedPlan> materializedPlansList = new ArrayList<MaterializedPlan>();
			materializedPlansList.add(matPlan);
			// Apply a prunning rule for paths
			// If the runtime for a certain path in a certain conf
			// is greater than the runtime of the dominant path (for)
			// the best plan found at this point. Then neglect the whole
			// conf.
			if (Config.COMPILE_FT_PRUNING) {
				if (matPlan.getRunTimeWithoutFailure() >= BEST_PLAN_RUNTIME
						&& BEST_N_LEVEL_PLAN.size() > 0) {
					System.out.println("Mat Conf: " + matConfNum
							+ " is neglected! from runtime comparsion");
					NEGLECTED_CONFS_NUMBER++;
					negelctPlan = true;
					break;
				}
				// Applying the seconf path pruning rule (collapsed Ops
				// Comparsion)
				if (compareCollapsedOps(pathOps)
						&& BEST_N_LEVEL_PLAN.size() > 0) {
					negelctPlan = true;
					System.out.println("Mat Conf: " + matConfNum
							+ " is neglected! from paths comparison");
					NEGLECTED_CONFS_NUMBER++;
					break;
				}
			}
			// Calculate the number of re-attempts required to achieve the given
			// success rate c
			// For each materialization configuration.
			QueryRuntimeEstimator queryRuntimeEstimator = new QueryRuntimeEstimator(
					materializedPlansList, 0.99, this.mtbf);
			queryRuntimeEstimator.estimateReattemptsForMaterlialization();
			queryRuntimeEstimator.getReattemptsForDifferentMaterializations();

			// Enumerate (Generator Different failure scenarios)
			// Each materialization configuration produces number of
			// failure scenarios depends on how many level the mat
			// configuration has. More levels more failure scenarios!
			TotalRuntimeEstimator totalRuntimeEstimator = new TotalRuntimeEstimator(
					queryRuntimeEstimator.getMatPlans(), this.mttr);
			totalRuntimeEstimator.calculateAverageWastedTimePerMatConf();
			totalRuntimeEstimator.calculateRuntTimeForMatConfs();
			if (matPlan.getRunTime() > copyPlan.dominantPathRuntime) {
				copyPlan.dominantPathRuntime = matPlan.getRunTime();
				copyPlan.setDominantPath(pathOps);
				copyPlan.setMatConfNumber(matConfNum);
			}
		}
		if (BEST_N_LEVEL_PLAN.size() == 0 && !negelctPlan) {
			BEST_PLAN_RUNTIME = copyPlan.dominantPathRuntime;
			BEST_PLAN_NUMBER = copyPlan.matConfNumber;
			BEST_N_LEVEL_PLAN = copyPlan.dominantPath;
		} else if (!negelctPlan) {
			if (BEST_PLAN_RUNTIME > copyPlan.dominantPathRuntime) {
				BEST_PLAN_RUNTIME = copyPlan.dominantPathRuntime;
				BEST_PLAN_NUMBER = copyPlan.matConfNumber;
				BEST_N_LEVEL_PLAN = copyPlan.dominantPath;
			}
		}
	}

	/**
	 * 
	 * @param pathOps
	 * @return
	 */
	private boolean compareCollapsedOps(List<CostModelOperator> pathOps) {

		boolean neglectPlan = false;
		if (pathOps.size() < BEST_N_LEVEL_PLAN.size())
			return neglectPlan;
		else {
			for (int i = 0; i < BEST_N_LEVEL_PLAN.size(); i++) {
				if ((pathOps.get(i).getOpMaterializationTimeEstimate() + pathOps
						.get(i).getOpRunTimeEstimate()) >= BEST_N_LEVEL_PLAN
						.get(i).getOpMaterializationTimeEstimate()
						+ BEST_N_LEVEL_PLAN.get(i).getOpRunTimeEstimate()) {
					neglectPlan = true;
				} else {
					neglectPlan = false;
					break;
				}
			}
		}
		return neglectPlan;
	}

	/**
	 * Find all paths from roots to leaves in a bushy tree, and store them in a
	 * list
	 * 
	 * @param copyPlan
	 * @param rootId
	 * @param path
	 */
	private void findPaths(CostModelQueryPlan copyPlan, Identifier rootId,
			ArrayList<Identifier> path) {

		path.add(rootId);
		CostModelOperator op = copyPlan.operators.get(rootId);
		Vector<CostModelOperator> children = op.getChildren();
		if (children.size() > 0) {
			for (int i = 0; i < children.size(); i++) {
				findPaths(copyPlan, children.get(i).getId(), path);
			}
		} else if (op.getChildren().size() == 0) {
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
	 * @param cplan
	 *            the cplan to set
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
			if (this.nonMatOps.contains(opId))
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
	 * @param dominantPathRuntime
	 *            the dominantPathRuntime to set
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
	 * @param dominatPathIndex
	 *            the dominatPathIndex to set
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
	 * @param matConfNumber
	 *            the matConfNumber to set
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
	 * @param dominantPath
	 *            the dominantPath to set
	 */
	public void setDominantPath(List<CostModelOperator> dominantPath) {
		this.dominantPath = dominantPath;
	}

}
