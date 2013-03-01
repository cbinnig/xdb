package org.xdb.funsql.compile.analyze;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.tokens.TokenVariable;

public final class FunctionCache {
	//Singleton
	private static FunctionCache cache;
	
	//cached CompilePlans
	private Map<String, CompilePlan> plans;
	private Map<String, Vector<TokenVariable>> inVars;
	
	public synchronized static FunctionCache getCache(){
		if(cache == null){
			cache = new FunctionCache();
		}
		return cache;
	}
	
	//constructor
	private FunctionCache(){
		this.plans = Collections.synchronizedMap(new HashMap<String, CompilePlan>());
	}
		
	//methods	
	/**
	 * adds a CompilePlan to the Cache; if a plan exists for this key, it is updated
	 * @param cp CompilePlan to be added
	 */
	public void addPlan(String functionKey, CompilePlan cp){
		plans.put(functionKey, cp);		
	}
	
	/**
	 * Fetches the Plan from the Cache
	 * @param i
	 */
	public CompilePlan getPlan(String functionKey){
		return this.plans.get(functionKey);
	}
	
	/**
	 * Checks if function is in cache
	 * @param functionKey
	 * @return
	 */
	public boolean hasPlan(String functionKey){
		return this.plans.containsKey(functionKey);
	}
	
	public Vector<TokenVariable> getInVars(String functionkey) {
		return this.inVars.get(functionkey);
	}

	public void addInVars(String functionkey, Vector<TokenVariable> vars) {
		this.inVars.put(functionkey, vars);
	}

	/**
	 * Clear Cache
	 */
	public void clear(){
		this.plans.clear();
	}
}
