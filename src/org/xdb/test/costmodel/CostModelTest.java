package org.xdb.test.costmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.faulttolerance.costmodel.MaterializationOpsSuggester;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.test.TestCase;
import org.xdb.utils.Identifier;

/**
 * 
 * @author Abdallah
 *
 */
public class CostModelTest extends TestCase{   
    
	// Compile Plan
	private CompilePlan cplan = new CompilePlan();  
	// Fault Tolerance variables 
	private Map<Identifier, Double> opsEstimatedRuntime = new HashMap<Identifier, Double>(); 
	private Map<Identifier, Double> intermediadeResultsMatTime = new HashMap<Identifier, Double>();    

	public void setUp(){
		
		// Build Compile Plan 
		TableOperator tableOp = new TableOperator(new TokenIdentifier("L"));  
		cplan.addOperator(tableOp, false);  
		
		GenericSelection selectOp = new GenericSelection(tableOp);  
		cplan.addOperator(selectOp, false);  
				
		GenericAggregation aggOp1 = new GenericAggregation(selectOp); 
		cplan.addOperator(aggOp1, false);
		
		GenericSelection selectOp2 = new GenericSelection(aggOp1);
		cplan.addOperator(selectOp2, false);
		
		GenericProjection projOp = new GenericProjection(selectOp2); 
		cplan.addOperator(projOp, true);     
		
		
		ResultDesc tableOpResultDesc = new ResultDesc(1); 
		tableOpResultDesc.setPartitionCount(2);   
		tableOp.setResult(tableOpResultDesc);
		
		ResultDesc selectOpResultDesc = new ResultDesc(1); 
		selectOpResultDesc.setPartitionCount(2);  
		selectOp.setResult(selectOpResultDesc);
		
		ResultDesc aggOpResultDesc1 = new ResultDesc(1); 
		aggOpResultDesc1.setPartitionCount(2);  
		aggOpResultDesc1.materialize(true);
		aggOp1.setResult(aggOpResultDesc1);
		
		ResultDesc slecetOpResultDesc2 = new ResultDesc(1); 
		slecetOpResultDesc2.setPartitionCount(1); 
		selectOp2.setResult(slecetOpResultDesc2);
		
		ResultDesc projOpResultDesc = new ResultDesc(1); 
		projOpResultDesc.setPartitionCount(1);  
		projOp.setResult(projOpResultDesc); 
		
		//this.cplan.tracePlan(this.getClass().getName()+"_Compiled");
			 
	}

	public void testOptimalMatPlan() { 
		
		Error err = new Error(); 
		Identifier id2 = new Identifier("2"); 
		Identifier id3 = new Identifier("3"); 
		Identifier id4 = new Identifier("4"); 
		Identifier id5 = new Identifier("5");  
		
		this.opsEstimatedRuntime.put(id2, 44.0); 
		this.opsEstimatedRuntime.put(id3, 20.889);
		this.opsEstimatedRuntime.put(id4, 0.119 );
		this.opsEstimatedRuntime.put(id5, 0.002);
		
		this.intermediadeResultsMatTime.put(id2, 77.0); 
		this.intermediadeResultsMatTime.put(id3, 0.009);
		this.intermediadeResultsMatTime.put(id4, 0.009);
		this.intermediadeResultsMatTime.put(id5, 0.009);  
		 
		// Adding one non materializable operator.
		List<Identifier> nonMatops = new ArrayList<Identifier>(); 
		nonMatops.add(id4);
	
		MaterializationOpsSuggester matSuggester = new MaterializationOpsSuggester
				(cplan, this.opsEstimatedRuntime, this.intermediadeResultsMatTime, nonMatops, 20, 2);  
	    
		List<Identifier> expected = new ArrayList<Identifier>();
		List<Identifier> result = new ArrayList<Identifier>();

		if(Config.COMPILE_FT_MODE.equalsIgnoreCase("naive")){
			expected.add(new Identifier("2"));
			expected.add(new Identifier("3"));  
			expected.add(new Identifier("5")); 
		} else if(Config.COMPILE_FT_MODE.equalsIgnoreCase("smart")) {
			expected.add(new Identifier("5"));  
			expected.add(new Identifier("3"));  
		} else { 
			assertFalse("Unknown materialization mode: "+Config.COMPILE_FT_MODE, true);
		}
	
		err = matSuggester.startCostModel();   
		if(!err.isError()){
			result = matSuggester.getRecommendedMatOpsIds();
			assertEquals(new HashSet<Identifier>(expected), new HashSet<Identifier>(result));
		}		
	}  
}
