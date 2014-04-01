package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CostModelQueryPlan { 
	
	private List<CostModelOperator> roots = new ArrayList<CostModelOperator>();
	private List<CostModelOperator> leaves = new ArrayList<CostModelOperator>();
	private Map<CostModelOperator, List<CostModelOperator>> sources = new HashMap<CostModelOperator, List<CostModelOperator>>();  
	private Map<CostModelOperator, List<CostModelOperator>> consumers = new HashMap<CostModelOperator, List<CostModelOperator>>(); 
    private List<CostModelOperator> allOperators = new ArrayList<CostModelOperator>();

	public CostModelQueryPlan(List<CostModelOperator> allOperators) {
        setAllOperators(allOperators);
	}

	/**
	 * @return the roots
	 */
	public List<CostModelOperator> getRoots() {
		return roots;
	}

	/**
	 * @param roots the roots to set
	 */
	public void setRoots(List<CostModelOperator> roots) {
		this.roots = roots;
	}

	/**
	 * @return the consumers
	 */
	public Map<CostModelOperator, List<CostModelOperator>> getConsumers() {
		return consumers;
	}

	/**
	 * @param consumers the consumers to set
	 */
	public void setConsumers(Map<CostModelOperator, List<CostModelOperator>> consumers) {
		this.consumers = consumers;
	}

	/**
	 * @return the leaves
	 */
	public List<CostModelOperator> getLeaves() {
		return leaves;
	}

	/**
	 * @param leaves the leaves to set
	 */
	public void setLeaves(List<CostModelOperator> leaves) {
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
	 * @return the sources
	 */
	public Map<CostModelOperator, List<CostModelOperator>> getSources() {
		return sources;
	}

	/**
	 * @param sources the sources to set
	 */
	public void setSources(Map<CostModelOperator, List<CostModelOperator>> sources) {
		this.sources = sources;
	}

}
