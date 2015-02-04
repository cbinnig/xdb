package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.utils.Identifier;

/**
 * 
 * @author Abdallah
 *
 */
public class HeurisicMaterialization {
    
	// Compile Plan
	private CompilePlan compilePlan = new CompilePlan();   
	
	private Map<Identifier, Double> opsEstimatedRuntime = new HashMap<Identifier, Double>(); 
	
	private Map<Identifier, Double> intermediadeResultsMatTime = new HashMap<Identifier, Double>();    
	
	private List<Identifier> nonMaterializableOps = new ArrayList<Identifier>();

	private List<Identifier> recommendedMatOpsIds = new ArrayList<Identifier>();
	
	public HeurisicMaterialization() {
		// TODO Auto-generated constructor stub
	}

	public CompilePlan getCompilePlan() {
		return compilePlan;
	}

	public void setCompilePlan(CompilePlan compilePlan) {
		this.compilePlan = compilePlan;
	} 
	
	public Error materializePlan(){
		Error err = new Error(); 
		double queryRuntime = calculateQueryRuntime();
		Collection<AbstractCompileOperator> ops = this.compilePlan.getOperators();  
		for (AbstractCompileOperator abstractCompileOperator : ops) {
			if(abstractCompileOperator.getType() == EnumOperator.TABLE
					|| nonMaterializableOps.contains(abstractCompileOperator.getOperatorId().getChildId())) 
				continue; 
			// Heuristic Rule: if the mat time is less than certain threshold of the 
			// total runtime of the query, then materialize it.  
			double opMatTime = intermediadeResultsMatTime.get(abstractCompileOperator.getOperatorId().getChildId());  
			if(opMatTime <= 0.1*queryRuntime){
			     abstractCompileOperator.getResult().materialize(true);    
			     this.recommendedMatOpsIds.add(abstractCompileOperator.getOperatorId().getChildId()); 
			     System.out.println("Materialize op (extra Mat): "+ abstractCompileOperator.getOperatorId().getChildId());
			}
		}
		
		this.compilePlan.setMatOps(recommendedMatOpsIds);
		this.compilePlan.tracePlan("Materialized");
		return err;
	}
    
	/**
	 * 
	 * @return
	 */
	private double calculateQueryRuntime() {
		double queryRuntime = 0.0;
		Collection<Double> opRuntimes = this.opsEstimatedRuntime.values(); 
		for (Double opRuntime : opRuntimes) {
			queryRuntime += opRuntime;
		}
		return queryRuntime;
	}

	public Map<Identifier, Double> getOpsEstimatedRuntime() {
		return opsEstimatedRuntime;
	}

	public void setOpsEstimatedRuntime(Map<Identifier, Double> opsEstimatedRuntime) {
		this.opsEstimatedRuntime = opsEstimatedRuntime;
	}

	public Map<Identifier, Double> getIntermediadeResultsMatTime() {
		return intermediadeResultsMatTime;
	}

	public void setIntermediadeResultsMatTime(
			Map<Identifier, Double> intermediadeResultsMatTime) {
		this.intermediadeResultsMatTime = intermediadeResultsMatTime;
	}

	public List<Identifier> getNonMaterializableOps() {
		return nonMaterializableOps;
	}

	public void setNonMaterializableOps(List<Identifier> nonMaterializableOps) {
		this.nonMaterializableOps = nonMaterializableOps;
	}

}
