package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.List;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 // Building a query  Manually (dummy)  
		
		Operator op1 = new Operator();
		op1.setType("select"); 
		op1.setMaterilaized(false); 
		op1.setId(1);   
		op1.setOpRunTimeEstimate(2.7); 
		op1.setOpMaterializationTimeEstimate(0.003);
		
		
		Operator op2 = new Operator();
		op2.setType("join"); 
		op2.setMaterilaized(false);
		op2.setId(2); 
		op2.setOpRunTimeEstimate(59453.26); 
		op2.setOpMaterializationTimeEstimate(233.94);
		
		Operator op3 = new Operator();
		op3.setType("join"); 
		op3.setMaterilaized(false);
		op3.setId(3);  
		op3.setOpRunTimeEstimate(555848.78); 
		op3.setOpMaterializationTimeEstimate(707);
		
		Operator op4 = new Operator();
		op4.setType("join"); 
		op4.setMaterilaized(false);
		op4.setId(4);  
		op4.setOpRunTimeEstimate(2714083.49); 
		op4.setOpMaterializationTimeEstimate(3721);
		
		Operator op5 = new Operator();
		op5.setType("aggregation"); 
		op5.setMaterilaized(false);
		op5.setForcedMaterlialized(false);
		op5.setId(5);
		op5.setOpRunTimeEstimate(150319); 
		op5.setOpMaterializationTimeEstimate(113);
		
		Operator op6 = new Operator();
		op6.setType("aggregation"); 
		op6.setMaterilaized(false);
		op6.setId(6); 
		op6.setOpRunTimeEstimate(1451); 
		op6.setOpMaterializationTimeEstimate(0.19); 
		
		Operator op7 = new Operator();
		op7.setType("aggregation"); 
		op7.setMaterilaized(false);
		op7.setId(7); 
		op7.setOpRunTimeEstimate(2); 
		op7.setOpMaterializationTimeEstimate(0.1); 
		
		Operator op8 = new Operator();
		op8.setType("aggregation"); 
		op8.setMaterilaized(false);
		op8.setId(8); 
		op8.setOpRunTimeEstimate(4); 
		op8.setOpMaterializationTimeEstimate(1);
		
		List<Operator> allOperators = new ArrayList<Operator>();  
		
		allOperators.add(op1); 
		allOperators.add(op2); 
		allOperators.add(op3); 
		allOperators.add(op4); 
		allOperators.add(op5);  
		allOperators.add(op6); 
		//allOperators.add(op7); 
		//allOperators.add(op8); 

			
		QueryPlan queryPlan = new QueryPlan(allOperators); 
		queryPlan.setAllOperators(allOperators);
		MaterlizationStrategyEnumerator matEnumerator = new MaterlizationStrategyEnumerator(queryPlan);
		matEnumerator.enumerateQueryPlan();  
		QueryRuntimeEstimator queryRuntimeEstimator = 
				new QueryRuntimeEstimator(matEnumerator.materilaizedPlansList, 0.9);
		queryRuntimeEstimator.estimateReattemptsForMaterlialization(); 
		FailureScenariosGen failureScenariosGen = new FailureScenariosGen(queryRuntimeEstimator.getMatPlans());
		failureScenariosGen.calculateAverageWastedTimePerMatConf();
		failureScenariosGen.calculateRuntTimeForMatConfs();
		// attach it to the materialaization strategy enumerator. 
		// calculate the success rate and the failure rate for the query 
		// with the current materilazation strategy 
	}

}
