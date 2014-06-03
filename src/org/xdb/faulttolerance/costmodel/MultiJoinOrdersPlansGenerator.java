package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector; 
import org.apache.commons.lang3.StringUtils;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;

public class MultiJoinOrdersPlansGenerator { 
	
	private CompilePlan compilePlan;
	private List<AbstractCompileOperator> joinOps = new ArrayList<AbstractCompileOperator>(); 
	
	
	public MultiJoinOrdersPlansGenerator(CompilePlan compilePlan){
		this.setCompilePlan(compilePlan);
	} 
	
	public void initializeTheWholeOperator(){
		
	}
	
	public void enerateDifferentTrees(){
		
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
    	int joinOpsNumber = this.compilePlan.getOperators().size();
    	for(int i=0; i<2*joinOpsNumber+1; i++){
    		if(i >= joinOpsNumber)
    		   sequence += "0"; 
    		else 
    	       sequence += "1"; 
    	} 
    	
    	Set<String> allPossibleJoinsPermutation = generatePermutation(sequence);  
    	for (String string : allPossibleJoinsPermutation) {
			System.out.println(string);
		}
    	
    	return allPossibleJoinsPermutation;
    } 
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public Set<String> generatePermutation(String input) {
		Set<String> set = new HashSet<String>();
		if (input == "")
			return set;

		Character a = input.charAt(0);

		if (input.length() > 1){
			input = input.substring(1);
			Set<String> permSet = generatePermutation(input);
			for (String x : permSet) {
				for (int i = 0; i <= x.length(); i++){ 
					String in = x.substring(0, i) + a + x.substring(i);
					if(in.length() == (2*this.compilePlan.getOperators().size() + 1)){ 
						if(!checkSequenceValidity(in)) 
							continue;
					}
					set.add(in);
				}
			}
		}
		else{ 
			set.add(a + "");
		}
		return set;
	} 
	
	 /**
     * Check the preorder sequence to check if it is valid to construct a 
     * join order, the conditin is: at every index (i, i!= 2n) in the sequence 
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
	 * @return the joinOps
	 */
	public List<AbstractCompileOperator> getJoinOps() {
		return joinOps;
	}

	/**
	 * @param joinOps the joinOps to set
	 */
	public void setJoinOps(List<AbstractCompileOperator> joinOps) {
		this.joinOps = joinOps;
	} 
	
	public static void main(String [] args){
		MultiJoinOrdersPlansGenerator obj = new MultiJoinOrdersPlansGenerator(null);
		
		Set<String> allTraversals = obj.generatePermutation("1110000"); 
		for (String sequence : allTraversals) {
			System.out.println(sequence);
		}
	}
}
