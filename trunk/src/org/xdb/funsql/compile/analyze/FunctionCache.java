package org.xdb.funsql.compile.analyze;

import java.util.HashMap;

import org.xdb.funsql.compile.CompilePlan;

public final class FunctionCache {
	//Singleton
	private static FunctionCache cache;
	
	//cached CompilePlans
	private static HashMap<String, CompilePlan> plans;
	
	public synchronized static FunctionCache getCache(){
		if(cache == null){
			cache = new FunctionCache();
			plans = new HashMap<String, CompilePlan>();
		}
		return cache;
	}
	
	//constructor
	private FunctionCache(){}
		
	//methods	
	/**
	 * adds a CompilePlan to the Cache; if a plan exists for this key, it is updated
	 * @param cp CompilePlan to be added
	 */
	public void addPlan(String functioName, CompilePlan cp){
		plans.put(functioName, cp);		
	}
	
	/**
	 * Fetches the Plan from the Cache
	 * @param i
	 */
	public void getPlan(String id){
		plans.get(id);
	}
}
