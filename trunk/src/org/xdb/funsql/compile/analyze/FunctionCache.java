package org.xdb.funsql.compile.analyze;

import java.util.HashMap;
import java.util.Vector;

import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.tokens.*;
import org.xdb.metadata.Attribute;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.Schema;
import org.xdb.metadata.Table;

public final class FunctionCache {
	//Singleton
	private static FunctionCache cache;
	//constructor
	private FunctionCache(){}
	
	//Cache-variables
	private static Vector<TokenVariable> vars;
	private static HashMap<String, TokenVariable> namedvars;
	private static HashMap<String, Table> tables;	//representation of variable is a table
	//cached CompilePlans
	private static HashMap<String, CompilePlan> plans;
	
	public synchronized static FunctionCache getCache(){
		if(cache == null){
			cache = new FunctionCache();
			plans = new HashMap<String, CompilePlan>();
			tables = new HashMap<String, Table>();
			namedvars = new HashMap<String, TokenVariable>();
			vars= new Vector<TokenVariable>();
		}
		return cache;
	}
	
	//methods
	
	/**
	 * @param tv TokenVariable to add to the Cache
	 */
	private void addVariable(TokenVariable tv){
		 namedvars.put(tv.getName(), tv);
		 vars.addElement(tv);
		 if(Catalog.getSchema("VARIABLE").equals(null)){
			 Catalog.createSchema(new Schema("VARIABLE"));
		 }
		 Schema schema = Catalog.getSchema("VARIABLE");
		 Table table = new Table("VAR_"+ tv.getName(), tv.getName(), schema.getName(), schema.getOid(),null);
		 tables.put(table.getName(), table);
	}	
	
	public void addVariable(TokenVariable tv, ResultDesc rd) {
		Table t;
		if (!this.isVarInCache(tv.getName())) {
			this.addVariable(tv);
		}
		t = tables.get(tv.getName());
		tables.remove(tv.getName());
		for(int i = 0; i <  rd.getAttributes().size(); i++){
			TokenAttribute ta = rd.getAttribute(i);
			t.addAttribute(new Attribute(ta.getName().toString(),rd.getType(i), t.getOid()));
		}
		tables.put(t.getName(), t);
	}
	
	/**
	 * Checks if variable is in Cache
	 * @param VarName
	 * @return true = variable is in Cache, false = variable is not in Cache
	 */
	public boolean isVarInCache(String VarName){
		TokenVariable tv = namedvars.get(VarName);
		if (tv.equals(null))
			return false;
		else return true;
	}
	
	/**
	 * Checks if variable is in Cache
	 * @param VarName
	 * @return true = variable is in Cache, false = variable is not in Cache
	 */
	public boolean isVarInCache(TokenTable ttable){
		Table t = tables.get(ttable.toString());
		if (t.equals(null))
			return false;
		else return true;
	}
	
	/**
	 * Gets a Variable from Cache
	 * @param VarName
	 * @return TokenVariable
	 */
	public TokenVariable getVar(String VarName){
		TokenVariable tv = namedvars.get(VarName);
		return tv;
	}
	
	/**
	 * Gets a Variable from Cache
	 * @param VarName
	 * @return TokenVariable
	 */
	public Table getTable(String VarName){
		Table t = tables.get(VarName);
		return t;
	}
	
	/**
	 * adds a CompilePlan to the Cache; if a plan exists for this key, it is updated
	 * @param cp CompilePlan to be added
	 */
	public void addPlan(CompilePlan cp){
		plans.put(cp.getPlanId().toString(),cp);		
	}
	
	/**
	 * Fetches the Plan from the Cache
	 * @param i
	 */
	public void getPlan(int i){
		plans.get(i);
	}

	public void addVariables(Vector<TokenVariable> variables) {
		for(TokenVariable tv: variables){
			this.addVariable(tv);
		}
		
	}

}
