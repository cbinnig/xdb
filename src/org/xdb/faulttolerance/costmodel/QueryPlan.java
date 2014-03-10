package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryPlan { 
	
	private List<Operator> roots = new ArrayList<Operator>();
	private List<Operator> leaves = new ArrayList<Operator>();
	private Map<Operator, List<Operator>> sources = new HashMap<Operator, List<Operator>>();  
	private Map<Operator, List<Operator>> consumers = new HashMap<Operator, List<Operator>>(); 
    private List<Operator> allOperators = new ArrayList<Operator>();

	public QueryPlan(List<Operator> allOperators) {
        setAllOperators(allOperators);
	}

	/**
	 * @return the roots
	 */
	public List<Operator> getRoots() {
		return roots;
	}

	/**
	 * @param roots the roots to set
	 */
	public void setRoots(List<Operator> roots) {
		this.roots = roots;
	}

	/**
	 * @return the consumers
	 */
	public Map<Operator, List<Operator>> getConsumers() {
		return consumers;
	}

	/**
	 * @param consumers the consumers to set
	 */
	public void setConsumers(Map<Operator, List<Operator>> consumers) {
		this.consumers = consumers;
	}

	/**
	 * @return the leaves
	 */
	public List<Operator> getLeaves() {
		return leaves;
	}

	/**
	 * @param leaves the leaves to set
	 */
	public void setLeaves(List<Operator> leaves) {
		this.leaves = leaves;
	}

	/**
	 * @return the allOperators
	 */
	public List<Operator> getAllOperators() {
		return allOperators;
	}

	/**
	 * @param allOperators the allOperators to set
	 */
	public void setAllOperators(List<Operator> allOperators) {
		this.allOperators = allOperators;
	}

	/**
	 * @return the sources
	 */
	public Map<Operator, List<Operator>> getSources() {
		return sources;
	}

	/**
	 * @param sources the sources to set
	 */
	public void setSources(Map<Operator, List<Operator>> sources) {
		this.sources = sources;
	}

}
