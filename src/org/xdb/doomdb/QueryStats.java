package org.xdb.doomdb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xdb.utils.Identifier;

public class QueryStats implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;  
	
	private Map<Identifier, Double> queryRuntimesStat = new HashMap<Identifier, Double>(); 
	
	private Map<Identifier, Double> queryMattimesStat = new HashMap<Identifier, Double>(); 
	
	private List<Identifier> nonMatOps = new ArrayList<Identifier>(); 
	
    public QueryStats(Map<Identifier, Double> queryRuntimesStat, 
    		Map<Identifier, Double> queryMattimesStat, List<Identifier> nonMatOps){
    	this.setQueryRuntimesStat(queryRuntimesStat); 
    	this.setQueryMattimesStat(queryMattimesStat); 
    	this.setNonMatOps(nonMatOps); 
    }

	/**
	 * @return the queryRuntimesStat
	 */
	public Map<Identifier, Double> getQueryRuntimesStat() {
		return queryRuntimesStat;
	}

	/**
	 * @param queryRuntimesStat the queryRuntimesStat to set
	 */
	public void setQueryRuntimesStat(Map<Identifier, Double> queryRuntimesStat) {
		this.queryRuntimesStat = queryRuntimesStat;
	}

	/**
	 * @return the queryMattimesStat
	 */
	public Map<Identifier, Double> getQueryMattimesStat() {
		return queryMattimesStat;
	}

	/**
	 * @param queryMattimesStat the queryMattimesStat to set
	 */
	public void setQueryMattimesStat(Map<Identifier, Double> queryMattimesStat) {
		this.queryMattimesStat = queryMattimesStat;
	}

	/**
	 * @return the nonMatOps
	 */
	public List<Identifier> getNonMatOps() {
		return nonMatOps;
	}

	/**
	 * @param nonMatOps the nonMatOps to set
	 */
	public void setNonMatOps(List<Identifier> nonMatOps) {
		this.nonMatOps = nonMatOps;
	}
	

}
