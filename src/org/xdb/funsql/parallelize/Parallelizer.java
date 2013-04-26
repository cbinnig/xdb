package org.xdb.funsql.parallelize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
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
		
		System.out.println("Time for parallization" + (currentTime2 - currentTime1));
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
		HashMap<Identifier, List<PartitionInfo>> partionCandidates = this
				.extractPartitionCandiatesPerOperator();

		List<HashMap<Identifier, PartitionInfo>> partitionInfoCombinations = generatePartitionCombinations(partionCandidates);

		// generate all possible Partitioned Plans
		generatePossiblePlans(partitionInfoCombinations);
		//select cheapest plan
		CompilePlan plan = getCheapestPlan();
		//get Logger back
		plan.init();
		plan.tracePlan(this.getClass().getSimpleName());
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
		double test = 	(double) ((double) this.possibleCompilePlans.size() * (double)Math.random());
		int a = (int)test;
		System.out.println("Selected Compile Plan is " + a);
		
		return this.possibleCompilePlans.get(a);
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

		return partitionInfoCombinations;
	}

	/**
	 * This method extracts all Partition Candidates form the current Compile
	 * Plan and returns them in Map with a list of PartitionInfo as Value and
	 * the OperatorID as key
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
	 * Method generates a set of Possible paralell plans for a given compile plan
	 * @param candidates Partition Information
	 */
	private void generatePossiblePlans(
			List<HashMap<Identifier, PartitionInfo>> candidates) {
		Error error = new Error();

		// copy the compile plan n times, with n = partitionCombination size
		// build all combinations
		int currentlyBestDeCount = -1;
		int deCount= 0;
		this.newPossibleCompilePlans = new ArrayList<CompilePlan>();
		
		for (HashMap<Identifier, PartitionInfo> candidate : candidates) {
			CompilePlan plan =null ;
			plan = this.compilePlan.copy();
			plan.init();
			// set Partition Info in Plan Operators
			for (Identifier id : candidate.keySet()) {
				plan.getOperators(id).setPartitionOutputInfo(candidate.get(id));
			}
			//populate plan Information
			deCount= 0;
			PopulatePartitionInfoVisitor populateInfoVisitor = new PopulatePartitionInfoVisitor(
					null, plan, this, true);
			
			RemoveDataExchangeOpVisitor deOpRemovalVisitor = new RemoveDataExchangeOpVisitor(null, plan);
			for (AbstractCompileOperator root : plan.getRootsCollection()) {
				
				populateInfoVisitor.reset(root, plan, this,true);
				error = populateInfoVisitor.visit();
				
				deOpRemovalVisitor.reset(root, plan);
				error = deOpRemovalVisitor.visit();
				
				deCount = deCount +deOpRemovalVisitor.getDeOps();
				if (error.isError()) {
					// set this error
					return;
				}
				
			}
			
		
			// only use those with small operator Size ---> discuss this heuristik
			if(currentlyBestDeCount == -1){
				System.out.println(deCount);
				plan.tracePlan(this.getClass().getName());
				currentlyBestDeCount = deCount;
			}else{
				if(deCount <= currentlyBestDeCount){ 
					currentlyBestDeCount = deCount;
					
					if(deCount < currentlyBestDeCount){
						System.out.println(deCount);
						this.possibleCompilePlans = new ArrayList<CompilePlan>();
					}
					this.possibleCompilePlans.add(plan);
					//this.possibleCompilePlans.add(plan);
				}
			}
		}
		
		//populate Info in new variations

		for(CompilePlan plan : this.newPossibleCompilePlans){
			PopulatePartitionInfoVisitor populateInfoVisitor = new PopulatePartitionInfoVisitor(
					null,plan, this, false);
			deCount = 0;
			plan.init();
			RemoveDataExchangeOpVisitor deOpRemovalVisitor = new RemoveDataExchangeOpVisitor(null, plan);
			
			for (AbstractCompileOperator root : plan.getRootsCollection()) {

				populateInfoVisitor.reset(root, plan, this,false);
				error = populateInfoVisitor.visit();
			
				deOpRemovalVisitor.reset(root, plan);
				error = deOpRemovalVisitor.visit();
				
				deCount = deCount +populateInfoVisitor.getDeOps();
				if (error.isError()) {
					// set this error
					return;
				}
			}
	
			//plan.tracePlan("test" +i);
			/*if(deCount <= currentlyBestDeCount){ 
				currentlyBestDeCount = deCount;
				
				if(deCount < currentlyBestDeCount){
					System.out.println(deCount);
					this.possibleCompilePlans = new ArrayList<CompilePlan>();
				}
				this.possibleCompilePlans.add(plan);
			}*/
			//this.possibleCompilePlans.add(plan);
		}
		
		int n =0;
		for(CompilePlan pcp :this.possibleCompilePlans){
			n++;
			pcp.tracePlan("possiblePlan"+n);
		}
	//	System.out.println(currentlyBestDeCount);
		System.out.println(this.possibleCompilePlans.size());
		
		
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
