package org.xdb.doomdb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xdb.utils.Identifier;

public class QueryStats implements Serializable {

	
	private static final long serialVersionUID = -1889447598280403462L;
	
	private Map<Identifier, Double> queryRuntimesStat = new HashMap<Identifier, Double>(); 
	private Map<Identifier, Double> queryMattimesStat = new HashMap<Identifier, Double>(); 
	private List<Identifier> nonMatOps = new ArrayList<Identifier>();  
	private List<Identifier> forcedMatirializedOps = new ArrayList<Identifier>();
	private int mtbf = 0;
	private int mttr = 0;
	
	public QueryStats(){
		
	}
	
    public QueryStats(Map<Identifier, Double> queryRuntimesStat, 
    		Map<Identifier, Double> queryMattimesStat, List<Identifier> nonMatOps,  int mtbf, int mttr){
    	this.setQueryRuntimesStat(queryRuntimesStat); 
    	this.setQueryMattimesStat(queryMattimesStat); 
    	this.setNonMatOps(nonMatOps);  
    	this.mtbf = mtbf;
    	this.mttr = mttr;
    }

    public int getMTBF(){
    	return this.mtbf;
    }
    
    public int getMTTR(){
    	return this.mttr;
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

	public List<Identifier> getForcedMatirializedOps() {
		return forcedMatirializedOps;
	}

	public void setForcedMatirializedOps(List<Identifier> forcedMatirializedOps) {
		this.forcedMatirializedOps = forcedMatirializedOps;
	}
	

}
