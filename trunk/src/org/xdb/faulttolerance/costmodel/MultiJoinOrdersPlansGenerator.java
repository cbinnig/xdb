package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.xdb.Config;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.utils.Identifier;

/**
 * 
 * @author Abdallah
 *
 */
public class MultiJoinOrdersPlansGenerator { 
	// 
	private static int NUMBER_OF_JOINS = 5; 
	// the join order stored as an array of character 
	private char[] preOrderJoin;
	// index 
	private int index; 
	// table names for enumeration process 
	private static List<String> TABLE_NAMES = new ArrayList<String>(); 
	private int table_idx = 0; 
	private static List<String> TABLE_CONNECTIONS = new ArrayList<String>();  
	private static Map<String, Integer> TABLE_CARDINALITIES = new HashMap<String, Integer>();  
	private List<String> TABLE_FOREIGNKEYS = new ArrayList<String>(); 

	
	public List<CompilePlan> compilePalns = new ArrayList<CompilePlan>(); 
	private Set<String> allTablesOrder = new HashSet<String>();  
			
	public int numberOfPlans = 0;  
	public int numOfSkippedJoinOrders = 0;  
	
	private Map<Identifier, Double> opsRunTimes = new HashMap<Identifier, Double>();  
	private Map<Identifier, Double> opsMatTimes = new HashMap<Identifier, Double>(); 
	
	public BushyCPlanMatEnumerator bushyCPlanMatEnumerator = new BushyCPlanMatEnumerator(); 
	public static MultiJoinOrdersPlansGenerator obj = new MultiJoinOrdersPlansGenerator(); 
	public List<Stat> statObjList = new ArrayList<Stat>();


	public MultiJoinOrdersPlansGenerator(){
		// Set Table Names 
		TABLE_NAMES.add("R");  
		TABLE_NAMES.add("N"); 
		TABLE_NAMES.add("C"); 
		TABLE_NAMES.add("O"); 
		TABLE_NAMES.add("L"); 
		TABLE_NAMES.add("S"); 
		// Set Table Connections based on Q2 
		TABLE_CONNECTIONS.add("RN"); 
		TABLE_CONNECTIONS.add("NC"); 
		TABLE_CONNECTIONS.add("CO"); 
		TABLE_CONNECTIONS.add("OL"); 
		TABLE_CONNECTIONS.add("LS"); 	 
		TABLE_CONNECTIONS.add("NR"); 
		TABLE_CONNECTIONS.add("CN"); 
		TABLE_CONNECTIONS.add("OC"); 
		TABLE_CONNECTIONS.add("LO"); 
		TABLE_CONNECTIONS.add("SL");  
		// Set table cardinalities 
		TABLE_CARDINALITIES.put("R", 1);
		TABLE_CARDINALITIES.put("N", 5);
		TABLE_CARDINALITIES.put("C", 208000);
		TABLE_CARDINALITIES.put("O", 182063);
		TABLE_CARDINALITIES.put("L", 60000000);
		TABLE_CARDINALITIES.put("S", 100000);
		// Set table foreign keys 
		TABLE_FOREIGNKEYS.add("RN");  
		TABLE_FOREIGNKEYS.add("NC");
		TABLE_FOREIGNKEYS.add("CO");
		TABLE_FOREIGNKEYS.add("OL");
		TABLE_FOREIGNKEYS.add("SL");
        

	} 
	
	/**
	 * Generate sequence of binary number that represents nodes visit (Preorder)
	 * Ex:1011000 it is a sequence for a certain join order with three join 
	 * operators (0s represents the null nodes) 
	 * Every Preorder traversal (satisfies a certain condition) represents 
	 * a certain join order 
	 * @return
	 */
    public Set<String> generateNodesPreorderTraversalSequence(){
    	
    	// The sequence length = 2n + 1 where 
    	// n is the number of join ops, n = number 
    	// of join ops  as 1's and n+1 = number of null 
    	// ops as 0's 
    	
    	// The first sequence is 111000 (for 3 join ops) which represents the 
    	// the left deep order and the one we will start with  
    	String sequence = "";  
    	for(int i=0; i<2*NUMBER_OF_JOINS+1; i++){
    		if(i >= NUMBER_OF_JOINS)
    		   sequence += "0"; 
    		else 
    	       sequence += "1"; 
    	} 
    	Set<String> allPossibleJoinsPermutation = generatePermutation(sequence);  
    	return allPossibleJoinsPermutation;
    } 
	
	/**
	 * String permutation, used to permutate different 
	 * Join orders and different table orders.
	 * @param input
	 * @return
	 */
	public Set<String> generatePermutation(String input) {
		Set<String> set = new HashSet<String>();
		if (input == "")
			return set;
		Character c = input.charAt(0); 
		if (input.length() > 1){
			input = input.substring(1);
			Set<String> permSet = generatePermutation(input);
			for (String part : permSet) {
				for (int i = 0; i <= part.length(); i++){ 
					String in = part.substring(0, i) + c + part.substring(i);
					if(in.length() == (2*NUMBER_OF_JOINS + 1)){ 
						if(!checkSequenceValidity(in)) 
							continue;
					}
					set.add(in);
				}
			}
		}
		else{ 
			set.add(c + "");
		}
		return set;
	} 
	
	 /**
     * Check the pre-order sequence to check if it is valid to construct a 
     * join order, the condition is: at every index (i, i!= 2n) in the sequence 
     * the number of 1s must be no fewer than number of 0s  
     * @param in 
     * @return
     */
    public boolean checkSequenceValidity(String in){
    	boolean validSequence = true;
    	for(int i=0; i< in.length() -1; i++){
    		if(StringUtils.countMatches(in.subSequence(0, i), "1") < StringUtils.countMatches(in.subSequence(0, i), "0")) { 
    			validSequence = false; 
    			break;
    		}		
    	}
    	return validSequence;
    }
   
	/**
	 * 
	 * @param preOrderJoin
	 * @return
	 */
	public CompilePlan generateCompilePlan(String preOrderJoin) {
		CompilePlan compilePlan = new CompilePlan(); 
		this.preOrderJoin = preOrderJoin.toCharArray();
		index = 0; 
		table_idx = 0;
		generatePlan(compilePlan, new EquiJoin(), null, false);
		return compilePlan;
	}
	
	/**
	 * Generate all compile plans with all join orders potentials 
	 * @param compilePlan
	 * @param rootJoin
	 * @param parent
	 * @param isLeft
	 */
	private void generatePlan(CompilePlan compilePlan, EquiJoin rootJoin, EquiJoin parent, boolean isLeft) {
		
		if(preOrderJoin[index] == '1') {
			rootJoin = new EquiJoin(); 
			index++; 
			compilePlan.addOperator(rootJoin, false); 
			ResultDesc joinResultDesc = new ResultDesc(1); 
			joinResultDesc.setPartitionCount(Config.DOOMDB_CLUSTER_SIZE);   
			rootJoin.setResult(joinResultDesc);
			if(parent != null) {
				if(isLeft) {
					parent.setLeftChildOp(rootJoin); 
					parent.addLeftChildren(parent.leftChildOp); 
					parent.setLeftChild(parent.leftChildOp);
					parent.leftChildOp.addParent(parent);
				} else {
					parent.setRightChildOp(rootJoin);
					parent.addChild(parent.rightChildOp); 
					parent.setRightChild(parent.rightChildOp);
					parent.rightChildOp.addParent(parent);
				}
			} else {
				compilePlan.addRootId(rootJoin.getOperatorId());
			}
			generatePlan(compilePlan, rootJoin.leftChildOp, rootJoin, true); 
			generatePlan(compilePlan, rootJoin.rightChildOp, rootJoin, false);
		} else {
			TableOperator tableOp = new TableOperator(new TokenIdentifier(TABLE_NAMES.get(table_idx)));  
			table_idx++;
			compilePlan.addOperator(tableOp, false);   
			if(isLeft) {
				parent.leftTableOp = tableOp; 
				parent.addLeftChildren(parent.leftTableOp); 
				parent.setLeftChild(parent.leftTableOp);
				parent.leftTableOp.addParent(parent);
			} else {
				parent.rightTableOp = tableOp; 
				parent.addChild(parent.rightTableOp); 
				parent.setRightChild(parent.rightTableOp);
				parent.rightTableOp.addParent(parent);
			}
			index++; 
		}	
	}
    	
	public Set<String> getAllTablesOrder() {
		return allTablesOrder;
	}

	public void setAllTablesOrder(Set<String> allTablesOrder) {
		this.allTablesOrder = allTablesOrder;
	}

	public static void main(String [] args){
		obj = new MultiJoinOrdersPlansGenerator();
		Set<String> allTraversals = obj.generatePermutation("11111000000"); 
		Set<String> allTablesOrder = obj.generatePermutation("RNCOLS"); 
		obj.setAllTablesOrder(allTablesOrder);

		System.out.println("Generating Plans with differen join orders in progress...");
	    for(int i=0; i<allTraversals.size(); i++){
	    	System.out.print("...");
	    	CompilePlan cPlan = new CompilePlan();  
	    	cPlan = obj.generateCompilePlan(allTraversals.toArray()[i].toString());  
		    // enumerate table orders 
		    obj.enumerateTableOrders(cPlan);
	    	obj.compilePalns.add(cPlan);
	    }  
	    
	    for(int i=0; i<obj.statObjList.size(); i++){
	    	 System.out.println();
	         System.out.println(obj.statObjList.get(i).getNumberOfEnumeratedPlan() +" plan have been generated!"); 
	         System.out.println("Total Confs Number: "+obj.statObjList.get(i).getNumberOfMatConf());
	         System.out.println("Number of neglect conf from step 1: "+obj.statObjList.get(i).getRule1());
	         System.out.println("Number of neglect conf from step 2: "+obj.statObjList.get(i).getRule2());
	         System.out.println("Number of neglect conf from step 3: "+obj.statObjList.get(i).getRule3());
	    }
	    System.out.println();
        System.out.println((obj.numberOfPlans - obj.numOfSkippedJoinOrders) +" plan have been generated!"); 
        System.out.println("Total Confs Number: "+obj.bushyCPlanMatEnumerator.TOTAL_CONFS_NUMBER);
        System.out.println("Number of neglect conf from step 1: "+obj.bushyCPlanMatEnumerator.NEGLECTED_PLAN_COUNTER_FIRST_RULE);
        System.out.println("Number of neglect conf from step 2: "+obj.bushyCPlanMatEnumerator.NEGLECTED_PLAN_COUNTER_SECOND_RULE);
        System.out.println("Number of neglect conf from step 3: "+obj.bushyCPlanMatEnumerator.NEGLECTED_PLAN_COUNTER_THIRD_RULE);
	}
    
	/**
	 * 
	 * @param cPlan
	 * @throws Exception 
	 */
	private void enumerateTableOrders(CompilePlan cPlan){
		Set<Identifier> leaves = cPlan.getLeaves(); 
		Iterator<String> iterator = allTablesOrder.iterator();  
		// Connects small ops on top of join ops to complete the plan 
		connectsSmallOps(cPlan); 
		while(iterator.hasNext()){
			numberOfPlans++;
			String tablesOrder = iterator.next(); 
			char[] tablesOrderAsArray = tablesOrder.toCharArray();  
			int i=0;
			for (Identifier identifier : leaves) {
				TableOperator tableOp = (TableOperator) cPlan.getOperator(identifier); 
				tableOp.setTableAlias(tablesOrderAsArray[i] +"");  
				i++;
			} 
			// detect valid join order 
			try { 
				this.opsMatTimes.clear();
				this.opsRunTimes.clear();
				detectCartesianJoinOrder(cPlan); 
				
				List<Identifier> nonMaterializableOps; 
				nonMaterializableOps = new ArrayList<Identifier>(); 
				
				nonMaterializableOps.add(new Identifier("12")); 
				nonMaterializableOps.add(new Identifier("14"));
				
				this.opsRunTimes.put(new Identifier("13"), 0.03);
				this.opsRunTimes.put(new Identifier("15"), 0.03);  
				this.opsMatTimes.put(new Identifier("13"), 0.02);
				this.opsMatTimes.put(new Identifier("15"), 0.005); 
				
				bushyCPlanMatEnumerator.setNonMatOps(nonMaterializableOps); 
				bushyCPlanMatEnumerator.setOpsEstimatedRuntime(this.opsRunTimes);
				bushyCPlanMatEnumerator.setIntermediadeResultsMatTime(this.opsMatTimes);
				
				bushyCPlanMatEnumerator.setCompilePlan(cPlan); 
				bushyCPlanMatEnumerator.enumerateCompilePlan();    
				
				if((obj.numberOfPlans - obj.numOfSkippedJoinOrders)%200 == 0){ 
					Stat stat = new Stat(); 
					stat.setNumberOfEnumeratedPlan(obj.numberOfPlans - obj.numOfSkippedJoinOrders); 
					stat.setNumberOfMatConf(obj.bushyCPlanMatEnumerator.TOTAL_CONFS_NUMBER);
					stat.setRule1(obj.bushyCPlanMatEnumerator.NEGLECTED_PLAN_COUNTER_FIRST_RULE);  
					stat.setRule2(obj.bushyCPlanMatEnumerator.NEGLECTED_PLAN_COUNTER_SECOND_RULE); 
					stat.setRule3(obj.bushyCPlanMatEnumerator.NEGLECTED_PLAN_COUNTER_THIRD_RULE);  
					statObjList.add(stat);
				}
			
			} catch (Exception e) {
				numOfSkippedJoinOrders++;
			} 
		}
		 
	}
    
	/**
	 * Connect the remaining operators (not joins) to the join enumerated plans 
	 * @param cPlan
	 */
	private void connectsSmallOps(CompilePlan cPlan) {
		
		Vector<Identifier> roots = cPlan.getRoots(); 
		Collection<AbstractCompileOperator> rootOps = cPlan.getRootOps();
		
		// Assuming we have one root in the plan
		AbstractCompileOperator rootOp = rootOps.iterator().next(); 
		
		GenericSelection selectOp = new GenericSelection(rootOp);  
		cPlan.addOperator(selectOp, false); 
		ResultDesc selectOpResultDesc = new ResultDesc(1); 
		selectOpResultDesc.setPartitionCount(Config.DOOMDB_CLUSTER_SIZE);  
		//proj1ResultDesc.materialize(true);
		selectOp.setResult(selectOpResultDesc);
		
		GenericAggregation agg1 = new GenericAggregation(selectOp); 
		cPlan.addOperator(agg1, false);
		ResultDesc agg1ResultDesc = new ResultDesc(1); 
		agg1ResultDesc.setPartitionCount(Config.DOOMDB_CLUSTER_SIZE);   
		agg1ResultDesc.materialize(true);
		agg1.setResult(agg1ResultDesc);
		
		GenericSelection selectOp2 = new GenericSelection(agg1); 
		cPlan.addOperator(selectOp2, false);
		ResultDesc proj2ResultDesc = new ResultDesc(1); 
		proj2ResultDesc.setPartitionCount(Config.DOOMDB_CLUSTER_SIZE);   
		selectOp2.setResult(proj2ResultDesc);
		
		GenericAggregation proj3 = new GenericAggregation(selectOp2); 
		cPlan.addOperator(proj3, true); 
		ResultDesc proj3ResultDesc = new ResultDesc(1); 
		proj3ResultDesc.setPartitionCount(Config.DOOMDB_CLUSTER_SIZE);  
		proj3ResultDesc.materialize(true);
		proj3.setResult(proj3ResultDesc); 
		
		for (Identifier identifier : roots) {
			cPlan.removeRootId(identifier);
		}
		
	}

	/**
	 * 
	 * @param cPlan
	 * @throws Exception 
	 */
	private void detectCartesianJoinOrder(CompilePlan cPlan) throws Exception {
		Vector<Identifier> roots = cPlan.getRoots(); 
		for (Identifier rootId : roots) {
			AbstractCompileOperator op = cPlan.getOperator(rootId);
			visitPlanBottomUp(op);
		} 
	}

	/**
	 * 
	 * @param rootId
	 * @throws Exception 
	 */
	private void visitPlanBottomUp(AbstractCompileOperator op) throws Exception {
		// Get the operator children 
		Vector<AbstractCompileOperator> children = op.getChildren();
		for (AbstractCompileOperator childOperator : children) {
			visitPlanBottomUp(childOperator);
		}  
		
		if(op.getType() == EnumOperator.EQUI_JOIN){
			Vector<AbstractCompileOperator> equiJoinChildren = op.getChildren(); 
			String leftChildTableName; 
			String rightChildTableName;
			AbstractCompileOperator leftOp = equiJoinChildren.get(0); 
			AbstractCompileOperator rightOp = equiJoinChildren.get(1); 
			int leftChildCardinality; 
			int rightChildCardinality; 
            
			if(leftOp.getType() == EnumOperator.TABLE){ 
				leftChildTableName = ((TableOperator) leftOp).getTableAlias();  
				leftChildCardinality = TABLE_CARDINALITIES.get(leftChildTableName);
			} else {
				leftChildTableName = ((EquiJoin) leftOp).getTableNamesFromJoinOperation(); 
				leftChildCardinality =  ((EquiJoin) leftOp).getIntermediateResultCardinality();
			}

			if(rightOp.getType() == EnumOperator.TABLE){
				rightChildTableName = ((TableOperator) rightOp).getTableAlias(); 
				rightChildCardinality = TABLE_CARDINALITIES.get(rightChildTableName);
			} else {
				rightChildTableName = ((EquiJoin) rightOp).getTableNamesFromJoinOperation(); 
				rightChildCardinality = ((EquiJoin) rightOp).getIntermediateResultCardinality();
			}
			boolean isValid = checkJoinOrder(leftChildTableName, rightChildTableName);   
			if(isValid) {
				((EquiJoin) op).setTableNamesFromJoinOperation(leftChildTableName + rightChildTableName); 
				calculateRuntimeMatTime(op, leftChildCardinality, rightChildCardinality, leftChildTableName, rightChildTableName);
			} else {
				throw new Exception();
			}	
		} 
	}
    
	/**
	 * 
	 * @param op
	 * @param leftChildCardinality
	 * @param rightChildCardinality
	 * @param rightChildTableName 
	 * @param leftChildTableName 
	 */
	private void calculateRuntimeMatTime(AbstractCompileOperator op,
			int leftChildCardinality, int rightChildCardinality, String leftChildTableName, String rightChildTableName) {
		double opRuntime = calculateOpRunTime(leftChildCardinality, rightChildCardinality); 
		double opMattime = calculateOpMatitme(leftChildCardinality, rightChildCardinality, leftChildTableName, rightChildTableName);  
		this.setOpsRunTimes(op.getOperatorId().getChildId(), opRuntime); 
		this.setOpsMatTimes(op.getOperatorId().getChildId(), opMattime); 
		((EquiJoin)op).setIntermediateResultCardinality(calculateIntermediateResultCardinality
				(leftChildCardinality, rightChildCardinality, leftChildTableName, rightChildTableName));
	}
    /**
     * 
     * @param leftChildCardinality
     * @param rightChildCardinality
     * @param rightChildTableName 
     * @param leftChildTableName 
     * @return
     */
	private int calculateIntermediateResultCardinality(
			int leftChildCardinality, int rightChildCardinality, String leftChildTableName, String rightChildTableName) { 
		char[] leftTables = leftChildTableName.toCharArray(); 
		char[] rightTable = rightChildTableName.toCharArray(); 

		for(int i=0; i<leftTables.length; i++) {
			String leftTableName = leftTables[i] +"";
			for(int j=0; j<rightTable.length; j++){
				String rightTableName = rightTable[j]+"";
				if(TABLE_CONNECTIONS.contains(leftTableName+rightTableName) || TABLE_CONNECTIONS.contains(rightTableName+leftTableName)){ 
					if(TABLE_FOREIGNKEYS.contains(leftTableName+rightTableName)) 
						return rightChildCardinality; 
					else 
						return leftChildCardinality;
				} 
			}
		} 
		if(leftChildCardinality > rightChildCardinality)
			return leftChildCardinality;
		else 
			return rightChildCardinality;
	}

	/**
	 * 
	 * @param leftChildCardinality
	 * @param rightChildCardinality
	 * @param rightChildTableName 
	 * @param leftChildTableName 
	 * @return
	 */
	private double calculateOpMatitme(int leftChildCardinality,
			int rightChildCardinality, String leftChildTableName, String rightChildTableName) { 
		double matTime = calculateIntermediateResultCardinality(leftChildCardinality, rightChildCardinality
				, leftChildTableName, rightChildTableName)/(Config.COMPILE_FT_MAT_SPEED_CONST*
				Config.COMPILE_FT_MAT_TO_PROCESSING_SPEED_RATIO*1000); 
		return matTime;
	}
    /**
     * 
     * @param leftChildCardinality
     * @param rightChildCardinality
     * @return
     */
	private double calculateOpRunTime(int leftChildCardinality,
			int rightChildCardinality) {
		double opRunTime = (1.2*leftChildCardinality + rightChildCardinality)/(Config.COMPILE_FT_MAT_SPEED_CONST*1000); 
		return opRunTime;
	}

	/**
	 * 
	 * @param leftChildTableName
	 * @param rightChildTableName
	 * @return
	 */
	private boolean checkJoinOrder(String leftChildTableName, String rightChildTableName) {
		boolean isValidOrder = false; 
		char[] leftTables = leftChildTableName.toCharArray(); 
		char[] rightTable = rightChildTableName.toCharArray(); 
		
		for(int i=0; i<leftTables.length; i++) {
			String leftTableName = leftTables[i] +"";
			for(int j=0; j<rightTable.length; j++){
				String rightTableName = rightTable[j]+"";
				if(TABLE_CONNECTIONS.contains(leftTableName+rightTableName) || TABLE_CONNECTIONS.contains(rightTableName+leftTableName)){ 
					isValidOrder = true; 
				    break;
				} 
			} 
			if(isValidOrder)
				break;
		}
		return isValidOrder;
	}

	public Map<Identifier, Double> getOpsRunTimes() {
		return opsRunTimes;
	}

	public void setOpsRunTimes(Identifier opId, double runTime) {
		this.opsRunTimes.put(opId, runTime);
	}

	public Map<Identifier, Double> getOpsMatTimes() {
		return opsMatTimes;
	}

	public void setOpsMatTimes(Identifier opId, double matTime) {
		this.opsMatTimes.put(opId, matTime);
	}	
}
