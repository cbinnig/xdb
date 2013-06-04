package org.xdb.funsql.parallelize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.codegen.ReRenameAttributesVisitor;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.metadata.Partition;
import org.xdb.utils.Identifier;

public class Parallelizer {

	private CompilePlan compilePlan;

	private List<CompilePlan> possibleCompilePlans;
	private List<CompilePlan> newPossibleCompilePlans;

	//Standard Error
	private Error error;
	
	public Parallelizer(CompilePlan compilePlan) {
		super();
		this.compilePlan = compilePlan;
		this.error = new Error();
	}

	public CompilePlan parallelize() {

		// initialize List each Time the Plan is parallelized
		possibleCompilePlans = new ArrayList<CompilePlan>();
		
		//first of all rename attributes
		this.rename();
		
		
		//insert necessary repartitioning Operators
		error = this.insertDataExchangeOps();
		
		//make the plan parallel	
		long currentTime1 = System.currentTimeMillis();
	  
		CompilePlan cp = this.getParalellPlan();
		long currentTime2 = System.currentTimeMillis();
		
		//duplicate parallel parts of the plan
		cp = this.copyParalellPart(cp);

		//build realPartition on table objects
		annotateFinalPartition(cp);
		
		System.out.println("Time for parallization " + (currentTime2 - currentTime1) + "ms");
		this.compilePlan = cp;
		//return plan 
		return this.compilePlan;
	}

	private void annotateFinalPartition(CompilePlan cp) {
		HashMap<Long, List<Partition>> mapPartitions = new HashMap<Long, List<Partition>>();
		
		for(AbstractCompileOperator absOp :cp.getOperators()){
			if(absOp.getType().equals(EnumOperator.TABLE)){
				TableOperator to = (TableOperator) absOp;
				if(to.getTable().isPartioned()){
					if(! mapPartitions.containsKey(to.getTable().getOid())){
						List<Partition> partitions = new ArrayList<Partition>();
						partitions.addAll(to.getTable().getPartitions());
						mapPartitions.put(to.getTable().getOid(), partitions);
					}
				}
			}
		}
		
		
		for(AbstractCompileOperator absOp :cp.getOperators()){
			if(absOp.getType().equals(EnumOperator.TABLE)){
				TableOperator to = (TableOperator) absOp;
				if(to.getTable().isPartioned()){
					Partition p = mapPartitions.get(to.getTable().getOid()).get(to.getPart());
					to.setPartition(p);
				}
			}
		}
	}


	private Error insertDataExchangeOps() {
		Error error = new Error();
		for (AbstractCompileOperator root : this.compilePlan
				.getRootsCollection()) {
			InsertDataExchangeOpVisitor exchangeOpVisitor = new InsertDataExchangeOpVisitor(
					root, this.compilePlan);
			error = exchangeOpVisitor.visit();

			if (error.isError())
				return error;
		}
		return error;
	}

	private  CompilePlan getParalellPlan() {
		Error error = new Error();
		
		HashMap<Identifier, List<PartitionInfo>> partitionCandidates = this.extractPartitionCandiatesPerOperator();

		List<HashMap<Identifier, PartitionInfo>> partitionInfoCombinations = generatePartitionCombinations(partitionCandidates);

		// generate all possible Partitioned Plans
		generatePossiblePlans(partitionInfoCombinations);
		//select cheapest plan
		for(CompilePlan cps: this.possibleCompilePlans){
			cps.tracePlan(this.getClass().getName() + "_CANDID_PARALLELIZED");
		}

		
		CompilePlan plan = getCheapestPlan();
		//get Logger back
		plan.init();
		this.error = error;
		
		return plan;
	}

	/**
	 * Method that selects the best Plan out of a given Set of plans, currently with a naive Metric can be enhanced
	 * @param partitionInfoCombinations
	 * @return
	 */
	private CompilePlan getCheapestPlan() {

		
		//make the life easy and simply give the first on back
		//add statistics-based Heuristik	

		double bestHeuristic = 999999.00;
		CompilePlan cheapestPlan = null;
		for(CompilePlan cps: this.possibleCompilePlans){
			if(cps.getEfficiencyHeuristic() < bestHeuristic){
				cheapestPlan = cps;
				bestHeuristic = cps.getEfficiencyHeuristic();
			}
		}
		
		System.out.println("Best Measure is:" + bestHeuristic + " has compilePlan with " + cheapestPlan.getPlanId() );

		//return this.possibleCompilePlans.get(a);
		
		return cheapestPlan;
	}
	


	/**
	 * This method should be used to generate PartitionCombinations from a Map 
	 * of PartitionCandiates per operator, it calls the internal recursive Method 
	 * generatePartition Combinations
	 * @param partionCandidates
	 * @return
	 */
	private List<HashMap<Identifier, PartitionInfo>> generatePartitionCombinations(
			HashMap<Identifier, List<PartitionInfo>> partionCandidates) {
		
		//prepare Input
		List<Identifier> identifiers = new ArrayList<Identifier>();
		List<List<PartitionInfo>> elements = new ArrayList<List<PartitionInfo>>();
		for (Identifier id : partionCandidates.keySet()) {
			identifiers.add(id);
			elements.add(partionCandidates.get(id));
		}
		//call internal Method
		List<HashMap<Identifier, PartitionInfo>> partitionInfoCombinations = generatePartitionCombinations(0, identifiers, elements, null);

	//	int biggercount=0;
		//prune output because is much too big TODO
		return partitionInfoCombinations;
	}

	/**
	 * This method extracts all Partition Candidates from the current Compile
	 * Plan and returns them in a Map with the OperatorID as the key and a list
	 * of PartitionInfo as Value
	 * 
	 * @return
	 */
	private HashMap<Identifier, List<PartitionInfo>> extractPartitionCandiatesPerOperator() {
		HashMap<Identifier, List<PartitionInfo>> partitions = new HashMap<Identifier, List<PartitionInfo>>();

		for (AbstractCompileOperator cOp : this.compilePlan.getOperators()) {
			List<PartitionInfo> list = new ArrayList<PartitionInfo>();
			if (cOp.getPartitionCandiates().size() > 0) {
				list.addAll(cOp.getPartitionCandiates());
				partitions.put(cOp.getOperatorId(), list);
			}
		}

		return partitions;
	}

	/**
	 * Given the set of possible combinations of PartitioninInfo of operators,
	 * this method generates a set of possible parallel plans for a given compile plan
	 * @param candidates Partition Information
	 */
	private void generatePossiblePlans(
			List<HashMap<Identifier, PartitionInfo>> candidates) {
		Error error = new Error();

		// copy the compile plan n times, with n = partitionCombination size
		// This is necessary as we want to build a separate plan for each candidate,
		// meaning that each candidate would have different partitioning information for the operators 
		
		this.newPossibleCompilePlans = new ArrayList<CompilePlan>();
		ArrayList<String> consideredCombinations = new ArrayList<String>();
		for (HashMap<Identifier, PartitionInfo> candidate : candidates) {
			CompilePlan plan =null ;
			for (Identifier id : candidate.keySet()) {
				// id represents an operator, the HashMap gives the list of PartitioningInfo of this operator
				this.compilePlan.getOperators(id).setOutputPartitionInfo(candidate.get(id));
			}
			plan = new CompilePlan(this.compilePlan);
			plan.init();
			
			plan.tracePlan("Very beginning");
			// set Partition Info in Plan Operators
		
			//populate plan Information
			
		
			PopulatePartitionInfoVisitor populateInfoVisitor = new PopulatePartitionInfoVisitor(
					null, plan, this, true, consideredCombinations);
			
			RemoveDataExchangeOpVisitor deOpRemovalVisitor = new RemoveDataExchangeOpVisitor(null, plan);
			for (AbstractCompileOperator root : plan.getRootsCollection()) {
				
				populateInfoVisitor.reset(root, plan, this,true,consideredCombinations);
				error = populateInfoVisitor.visit();
				plan.tracePlan("Erfan");
				
				deOpRemovalVisitor.reset(root, plan);
				error = deOpRemovalVisitor.visit();
				plan.setEfficiencyHeuristic(deOpRemovalVisitor.getEfficiencyHeuristic());
				if (error.isError()) {
					// set this error
					return;
				}
			}			
			this.possibleCompilePlans.add(plan);
			// only use those with small operator Size ---> discuss this heuristik
		}

		
		//populate Info in new variations and add new variations until no new are added
		for(int i = 0; i < this.newPossibleCompilePlans.size(); i++){
			CompilePlan plan = this.newPossibleCompilePlans.get(i);
			
			PopulatePartitionInfoVisitor populateInfoVisitor = new PopulatePartitionInfoVisitor(
					null,plan, this, false, consideredCombinations);
			plan.init();
			for (AbstractCompileOperator root : plan.getCopyRootsCollection()) {

				populateInfoVisitor.reset(root, plan, this,true, consideredCombinations);
				error = populateInfoVisitor.visit();
				
				if (error.isError()) {
					// set this error
					return;
				}
			}
		}
		
		//final population of Partition Info and remove not necessary data exchange operators
		for(CompilePlan plan : this.newPossibleCompilePlans){				
			PopulatePartitionInfoVisitor populateInfoVisitor = new PopulatePartitionInfoVisitor(
					null,plan, this, false, consideredCombinations);
			plan.init();
			RemoveDataExchangeOpVisitor deOpRemovalVisitor = new RemoveDataExchangeOpVisitor(null, plan);
			
			for (AbstractCompileOperator root : plan.getRootsCollection()) {

				populateInfoVisitor.reset(root, plan, this,false, consideredCombinations);
				error = populateInfoVisitor.visit();
			
				deOpRemovalVisitor.reset(root, plan);
				error = deOpRemovalVisitor.visit();
				if (error.isError()) {
					// set this error
					return;
				}
			}
		}
		
		this.possibleCompilePlans.addAll(this.newPossibleCompilePlans);
		
		
		System.out.println("Created: " + this.possibleCompilePlans.size() +" Compile Plans");
	}

	public Error addNewPossibleCompilePlan(CompilePlan p) {
		
		Error e = new Error();
		if (this.possibleCompilePlans == null) {
			return new Error(EnumError.PARALELLIZER_NO_COMPILEPLANS, null);
		}
		this.newPossibleCompilePlans.add(p);

		return e;

	}


	/**
	 * Recursive Method to generate Partition Combinations
	 * @param currindex
	 * @param identifiers
	 * @param elements
	 * @param combination
	 * @return
	 */
	private List<HashMap<Identifier, PartitionInfo>> generatePartitionCombinations(
			int currindex, List<Identifier> identifiers,
			List<List<PartitionInfo>> elements,
			HashMap<Identifier, PartitionInfo> combination) {

		// get first of the remainingElements
		Identifier currElem = null;
		List<PartitionInfo> partElems = null;

		//check ending condition
		if (currindex == (identifiers.size())) {
			//return current combination wrapped in a list
			ArrayList<HashMap<Identifier, PartitionInfo>> returnlist = new ArrayList<HashMap<Identifier, PartitionInfo>>();
			returnlist.add(combination);

			return returnlist;
		}
		//get next element
		currElem = identifiers.get(currindex);
		partElems = elements.get(currindex);

		currindex = currindex + 1;
		ArrayList<HashMap<Identifier, PartitionInfo>> combinations = new ArrayList<HashMap<Identifier, PartitionInfo>>();

		//loop over all candidates of this element to generate all combinations
		for (PartitionInfo info : partElems) {

			if (combination == null) {
				combination = new HashMap<Identifier, PartitionInfo>();
			}

			//build new combination
			@SuppressWarnings("unchecked")
			HashMap<Identifier, PartitionInfo> combinationNew = (HashMap<Identifier, PartitionInfo>) combination.clone();
			combinationNew.put(currElem, info);

			//recursion and add result of recursion to result
			combinations.addAll(this.generatePartitionCombinations(currindex,
					identifiers, elements, combinationNew));
		}

		//return result
		return combinations;

	}

	/**
	 * Renames the attributes and predicates of all operators to match the orginal ones
	 * 
	 */
	private void rename() {
		ReRenameAttributesVisitor renameVisitor;
		for (AbstractCompileOperator root : this.compilePlan.getRootsCollection()) {
			renameVisitor = new ReRenameAttributesVisitor(root);
			this.error = renameVisitor.visit();

			if (error.isError())
				return;
		}
	}
	
	
	/**
	 * @param cp
	 * @return
	 */
	public CompilePlan copyParalellPart(CompilePlan cp) {
		for(AbstractCompileOperator absOp : cp.getRootsCollection()){
			CopyParallelPartsVisitor cppv = new CopyParallelPartsVisitor(absOp, cp);
			cppv.visit();
		}
	
		return cp;
	}
	
	public Error getError(){
		return error;
	}
	
}
