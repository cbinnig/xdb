package org.xdb.test.costmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.faulttolerance.costmodel.BushyCPlanMatEnumerator;
import org.xdb.faulttolerance.costmodel.MaterializationOpsSuggester;
import org.xdb.faulttolerance.costmodel.MultiJoinOrdersPlansGenerator;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.tokens.TokenAttribute;
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
	
	public void testBushyTreePlan(){  
		
		CompilePlan bushyPlan = new CompilePlan();   
		Identifier id8 = new Identifier("8"); 
		Identifier id9 = new Identifier("9"); 
		Identifier id10 = new Identifier("10"); 
		Identifier id11 = new Identifier("11");  
		Identifier id12 = new Identifier("12"); 
		Identifier id13 = new Identifier("13"); 
		Identifier id14 = new Identifier("14"); 
		Identifier id15 = new Identifier("15");  
		Identifier id16 = new Identifier("16"); 
		Identifier id17 = new Identifier("17"); 
		Identifier id18 = new Identifier("18"); 
		Identifier id19 = new Identifier("19");  
		Identifier id20 = new Identifier("20");
		Identifier id21 = new Identifier("21"); 
		Identifier id22 = new Identifier("22"); 
		Identifier id23 = new Identifier("23");
		Identifier id24 = new Identifier("24"); 

		
		this.opsEstimatedRuntime.put(id8, 2.0); 
		this.opsEstimatedRuntime.put(id9, 1.0);
		this.opsEstimatedRuntime.put(id10, 2.0);
		this.opsEstimatedRuntime.put(id11, 1.0); 
		this.opsEstimatedRuntime.put(id12, 0.5); 
		this.opsEstimatedRuntime.put(id13, 2.0);
		this.opsEstimatedRuntime.put(id14, 10.5 );
		this.opsEstimatedRuntime.put(id15, 12.3);
		this.opsEstimatedRuntime.put(id16, 4.0); 
		this.opsEstimatedRuntime.put(id17, 1.0);
		this.opsEstimatedRuntime.put(id18, 3.0 );
		this.opsEstimatedRuntime.put(id19, 4.0);
		this.opsEstimatedRuntime.put(id20, 1.0); 
		this.opsEstimatedRuntime.put(id21, 0.1); 
		this.opsEstimatedRuntime.put(id22,0.1); 
		this.opsEstimatedRuntime.put(id23, 0.1); 
		this.opsEstimatedRuntime.put(id24, 0.1); 
			
		this.intermediadeResultsMatTime.put(id8, 5.0); 
		this.intermediadeResultsMatTime.put(id9, 10.0);
		this.intermediadeResultsMatTime.put(id10, 5.0);
		this.intermediadeResultsMatTime.put(id11, 20.1);  
		this.intermediadeResultsMatTime.put(id12, 1.0); 
		this.intermediadeResultsMatTime.put(id13, 0.9);
		this.intermediadeResultsMatTime.put(id14, 2.0);
		this.intermediadeResultsMatTime.put(id15, 0.1); 
		this.intermediadeResultsMatTime.put(id16, 2.3); 
		this.intermediadeResultsMatTime.put(id17, 3.1);
		this.intermediadeResultsMatTime.put(id18, 4.0);
		this.intermediadeResultsMatTime.put(id19, 1.0); 
		this.intermediadeResultsMatTime.put(id20, 0.1); 
		this.intermediadeResultsMatTime.put(id21, 0.1);
		this.intermediadeResultsMatTime.put(id22, 0.1);
		this.intermediadeResultsMatTime.put(id23, 0.1);
		this.intermediadeResultsMatTime.put(id24, 0.1);
	
		// Build Compile Plan  
		
		//table op
		TableOperator tableOp1 = new TableOperator(new TokenIdentifier("L"));  
		bushyPlan.addOperator(tableOp1, false); 
		ResultDesc tableOp1ResultDesc = new ResultDesc(1); 
		tableOp1ResultDesc.setPartitionCount(1);   
		tableOp1.setResult(tableOp1ResultDesc);

		//table op 
		TableOperator tableOp2 = new TableOperator(new TokenIdentifier("O"));  
		bushyPlan.addOperator(tableOp2, false);   
		ResultDesc tableOp2ResultDesc = new ResultDesc(1); 
		tableOp2ResultDesc.setPartitionCount(1);   
		tableOp2.setResult(tableOp2ResultDesc);

		//table op 
		TableOperator tableOp3 = new TableOperator(new TokenIdentifier("N"));  
		bushyPlan.addOperator(tableOp3, false);  
		ResultDesc tableOp3ResultDesc = new ResultDesc(1); 
		tableOp3ResultDesc.setPartitionCount(1);   
		tableOp3.setResult(tableOp3ResultDesc);

		//table op 
		TableOperator tableOp4 = new TableOperator(new TokenIdentifier("R"));  
		bushyPlan.addOperator(tableOp4, false);  
		ResultDesc tableOp4ResultDesc = new ResultDesc(1); 
		tableOp4ResultDesc.setPartitionCount(1);   
		tableOp4.setResult(tableOp4ResultDesc);

		//table op 
		TableOperator tableOp5 = new TableOperator(new TokenIdentifier("C"));  
		bushyPlan.addOperator(tableOp5, false);   
		ResultDesc tableOp5ResultDesc = new ResultDesc(1); 
		tableOp5ResultDesc.setPartitionCount(1);   
		tableOp5.setResult(tableOp5ResultDesc);

		//table op 
		TableOperator tableOp6 = new TableOperator(new TokenIdentifier("S"));  
		bushyPlan.addOperator(tableOp6, false);
		ResultDesc tableOp6ResultDesc = new ResultDesc(1); 
		tableOp6ResultDesc.setPartitionCount(1);   
		tableOp6.setResult(tableOp6ResultDesc);

		//table op 
		TableOperator tableOp7 = new TableOperator(new TokenIdentifier("P"));  
		bushyPlan.addOperator(tableOp7, false);  
		ResultDesc tableOp7ResultDesc = new ResultDesc(1); 
		tableOp7ResultDesc.setPartitionCount(1);   
		tableOp7.setResult(tableOp7ResultDesc);
		
		// selection op
		GenericSelection selectOp1 = new GenericSelection(tableOp1);  
		bushyPlan.addOperator(selectOp1, false);  
		ResultDesc selectOp1ResultDesc = new ResultDesc(1); 
		selectOp1ResultDesc.setPartitionCount(1);   
		selectOp1.setResult(selectOp1ResultDesc);
		
		// selection op
		GenericSelection selectOp2 = new GenericSelection(tableOp2);  
		bushyPlan.addOperator(selectOp2, false);  
		ResultDesc selectOp2ResultDesc = new ResultDesc(1); 
		selectOp2ResultDesc.setPartitionCount(1);   
		selectOp2.setResult(selectOp2ResultDesc);
		
		// join operator 
		TokenAttribute attributeRA = new TokenAttribute(selectOp1.getOperatorId().toString(), "a");
		TokenAttribute attributeRB = new TokenAttribute(selectOp2.getOperatorId().toString(), "b");				
		EquiJoin joinOp1 = new EquiJoin(selectOp1, selectOp2, attributeRA, attributeRB);
		bushyPlan.addOperator(joinOp1, false);
		ResultDesc joinOp1ResultDesc = new ResultDesc(1); 
		joinOp1ResultDesc.setPartitionCount(1);   
		joinOp1.setResult(joinOp1ResultDesc);
		
		// selection op 
		GenericSelection selectOp3 = new GenericSelection(tableOp3);
		bushyPlan.addOperator(selectOp3, false);
		ResultDesc selectOp3ResultDesc = new ResultDesc(1); 
		selectOp3ResultDesc.setPartitionCount(1);   
		selectOp3.setResult(selectOp3ResultDesc);
		
		// join operator  
		attributeRA = new TokenAttribute(joinOp1.getOperatorId().toString(), "a");
		attributeRB = new TokenAttribute(selectOp3.getOperatorId().toString(), "b");				
		EquiJoin joinOp2 = new EquiJoin(selectOp3, joinOp1, attributeRA, attributeRB);
		bushyPlan.addOperator(joinOp2, false);
		ResultDesc joinOp2ResultDesc = new ResultDesc(1); 
		joinOp2ResultDesc.setPartitionCount(1);   
		joinOp2.setResult(joinOp2ResultDesc);
		
		// selection op 
		GenericSelection selectOp4 = new GenericSelection(tableOp4);
		bushyPlan.addOperator(selectOp4, false); 
		ResultDesc selectOp4ResultDesc = new ResultDesc(1); 
		selectOp4ResultDesc.setPartitionCount(1);   
		selectOp4.setResult(selectOp4ResultDesc);
		
		// join operator  
		attributeRA = new TokenAttribute(joinOp2.getOperatorId().toString(), "a");
		attributeRB = new TokenAttribute(selectOp4.getOperatorId().toString(), "b");				
		EquiJoin joinOp3 = new EquiJoin(joinOp2, selectOp4, attributeRA, attributeRB);
		bushyPlan.addOperator(joinOp3, false); 
		ResultDesc joinOp3ResultDesc = new ResultDesc(1); 
		joinOp3ResultDesc.setPartitionCount(1);   
		joinOp3.setResult(joinOp3ResultDesc);

		// selection op 
		GenericSelection selectOp5 = new GenericSelection(tableOp5);
		bushyPlan.addOperator(selectOp5, false);
		ResultDesc selectOp5ResultDesc = new ResultDesc(1); 
		selectOp5ResultDesc.setPartitionCount(1);   
		selectOp5.setResult(selectOp5ResultDesc);

		// selection op 
		GenericSelection selectOp6 = new GenericSelection(tableOp6);
		bushyPlan.addOperator(selectOp6, false);   
		ResultDesc selectOp6ResultDesc = new ResultDesc(1); 
		selectOp6ResultDesc.setPartitionCount(1);   
		selectOp6.setResult(selectOp6ResultDesc);
		
		// join operator  
		attributeRA = new TokenAttribute(selectOp5.getOperatorId().toString(), "a");
		attributeRB = new TokenAttribute(selectOp6.getOperatorId().toString(), "b");				
		EquiJoin joinOp4 = new EquiJoin(selectOp5, selectOp6, attributeRA, attributeRB);
		bushyPlan.addOperator(joinOp4, false);  
		ResultDesc joinOp4ResultDesc = new ResultDesc(1); 
		joinOp4ResultDesc.setPartitionCount(1);   
		joinOp4.setResult(joinOp4ResultDesc);
		
		// join operator  
		attributeRA = new TokenAttribute(joinOp4.getOperatorId().toString(), "a");
		attributeRB = new TokenAttribute(joinOp3.getOperatorId().toString(), "b");				
		EquiJoin joinOp5 = new EquiJoin(joinOp4, joinOp3, attributeRA, attributeRB);
		bushyPlan.addOperator(joinOp5, false); 
		ResultDesc joinOp5ResultDesc = new ResultDesc(1); 
		joinOp5ResultDesc.setPartitionCount(1);   
		joinOp5.setResult(joinOp5ResultDesc);
		
		// selection op 
		GenericSelection selectOp7 = new GenericSelection(tableOp7);
		bushyPlan.addOperator(selectOp7, false);
		ResultDesc selectOp7ResultDesc = new ResultDesc(1); 
		selectOp7ResultDesc.setPartitionCount(1);   
		selectOp7.setResult(selectOp7ResultDesc);
        
		// join operator  
		attributeRA = new TokenAttribute(joinOp5.getOperatorId().toString(), "a");
		attributeRB = new TokenAttribute(selectOp7.getOperatorId().toString(), "b");				
		EquiJoin joinOp6 = new EquiJoin(joinOp5, selectOp7, attributeRA, attributeRB);
		bushyPlan.addOperator(joinOp6, false); 
		ResultDesc joinOp6ResultDesc = new ResultDesc(1); 
		joinOp6ResultDesc.setPartitionCount(1);
		//joinOp6ResultDesc.materialize(true);
		joinOp6.setResult(joinOp6ResultDesc);
		
		GenericProjection proj1 = new GenericProjection(joinOp6); 
		bushyPlan.addOperator(proj1, false); 
		ResultDesc proj1ResultDesc = new ResultDesc(1); 
		proj1ResultDesc.setPartitionCount(1);  
		//proj1ResultDesc.materialize(true);
		proj1.setResult(proj1ResultDesc);
		
		GenericAggregation agg1 = new GenericAggregation(proj1); 
		bushyPlan.addOperator(agg1, false);
		ResultDesc agg1ResultDesc = new ResultDesc(1); 
		agg1ResultDesc.setPartitionCount(1);   
		//agg1ResultDesc.materialize(true);
		agg1.setResult(agg1ResultDesc);
		
		GenericProjection proj2 = new GenericProjection(agg1); 
		bushyPlan.addOperator(proj2, false);
		ResultDesc proj2ResultDesc = new ResultDesc(1); 
		proj2ResultDesc.setPartitionCount(1);   
		proj2ResultDesc.materialize(false);
		proj2.setResult(proj2ResultDesc);
		
		GenericAggregation proj3 = new GenericAggregation(proj2); 
		bushyPlan.addOperator(proj3, true); 
		ResultDesc proj3ResultDesc = new ResultDesc(1); 
		proj3ResultDesc.setPartitionCount(2);   
		proj3.setResult(proj3ResultDesc);
		
		bushyPlan.tracePlan("Bushy_Tree_Plan_");
		
		BushyCPlanMatEnumerator bushyTreeEnumerator = new BushyCPlanMatEnumerator(Config.DOOMDB_MTBF, Config.DOOMDB_MTTR);
		List<Identifier> nonMaterializableOps; 
		nonMaterializableOps = new ArrayList<Identifier>(); 
		
		nonMaterializableOps.add(new Identifier("10")); 
		nonMaterializableOps.add(new Identifier("14"));
		nonMaterializableOps.add(new Identifier("17"));
		nonMaterializableOps.add(new Identifier("18"));

		bushyTreeEnumerator.setNonMatOps(nonMaterializableOps); 
		bushyTreeEnumerator.setOpsEstimatedRuntime(opsEstimatedRuntime);
		bushyTreeEnumerator.setIntermediadeResultsMatTime(intermediadeResultsMatTime);
		bushyTreeEnumerator.setCompilePlan(bushyPlan); 
		
		bushyTreeEnumerator.enumerateCompilePlan(); 
	
	} 
	
	public void testMultiJoinOrders(){
		
		CompilePlan bushyPlan = new CompilePlan();   
		
		//TokenAttribute attributeRA = new TokenAttribute("a");
		//TokenAttribute attributeRB = new TokenAttribute("b");				
		
		EquiJoin joinOp1 = new EquiJoin();
		bushyPlan.addOperator(joinOp1, false);  
		//ResultDesc joinOp1ResultDesc = new ResultDesc(1); 
		//joinOp1ResultDesc.setPartitionCount(1);   
		//joinOp1.setResult(joinOp1ResultDesc); 
		
		EquiJoin joinOp2 = new EquiJoin();
		bushyPlan.addOperator(joinOp2, false);  
		//joinOp2ResultDesc.setPartitionCount(1);   
		//joinOp2.setResult(joinOp2ResultDesc);
		
		EquiJoin joinOp3 = new EquiJoin();
		bushyPlan.addOperator(joinOp3, false);  
		//ResultDesc joinOp3ResultDesc = new ResultDesc(1); 
		//joinOp3ResultDesc.setPartitionCount(1);   
		//joinOp3.setResult(joinOp3ResultDesc);
		
		EquiJoin joinOp4 = new EquiJoin();
		bushyPlan.addOperator(joinOp4, false);  
		//ResultDesc joinOp4ResultDesc = new ResultDesc(1); 
		//joinOp4ResultDesc.setPartitionCount(1);   
		//joinOp4.setResult(joinOp4ResultDesc);
		
		//EquiJoin joinOp5 = new EquiJoin();
		//bushyPlan.addOperator(joinOp5, false);  
		//ResultDesc joinOp5ResultDesc = new ResultDesc(1); 
		//joinOp5ResultDesc.setPartitionCount(1);   
		//joinOp5.setResult(joinOp4ResultDesc); 
		
		MultiJoinOrdersPlansGenerator obj = new MultiJoinOrdersPlansGenerator();
		obj.generateNodesPreorderTraversalSequence();
		
	}
	
}
