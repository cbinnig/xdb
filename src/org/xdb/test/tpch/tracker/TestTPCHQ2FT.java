package org.xdb.test.tpch.tracker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xdb.Config;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.AbstractTrackerOperator;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.tracker.operator.TableDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

/**
 * Tests Q2 on multiple compute nodes
 * 
 * @author slisting
 * 
 */

@RunWith(Parameterized.class)
public class TestTPCHQ2FT extends DistributedTPCHTestCase {  
	
	private Boolean withFailure = false;
	private Integer numOfFailures = 0;  
	private static long EXECUTION_TIME = 1000; 
	private static Boolean IS_EXECUTION_TIME_SET = false; 
	private static int RUN_COUNTER = 0; 
	
	private static QueryTrackerNode queryTrackerNode;  
    private static ComputeNodeDesc[] computeNodes; 
	
	private class Q2Lower extends DistributedTPCHTestCase {
		
        private List<Identifier> opIds = new ArrayList<Identifier>(); 
		
		private Identifier unionOp; 
		
		// constructor
		public Q2Lower() {
			super(-1);

			this.subqueryDDL = "(o_total DECIMAL(15,2), c_custkey INTEGER )";
			this.subqueryDML = "select "
					+ "			sum(o_totalprice) as o_total, "
					+ "			c_custkey "
					+ "		from "
					+ "			<TPCH_DB_NAME>.customer,"
					+ "			<TPCH_DB_NAME>.orders "
					+ "		where " 
					+ "			c_custkey = o_custkey "
					+ "		    and o_orderdate between date '1995-01-01' and date '1996-12-31' " 
					+ "		group by c_custkey; ";

			this.unionDDL = "(o_total DECIMAL(15,2), c_custkey INTEGER )";
			this.unionPreDML = "SELECT sum(o_total) as o_total, c_custkey FROM "; 
			this.unionPostDML = " group by c_custkey order by o_total desc limit "+Config.JOIN_RECORDS_LIMIT+";";  

		}

		public QueryTrackerPlan createPlan() throws Exception {
			// assign plan to query tracker
			QueryTrackerPlan qPlan = new QueryTrackerPlan();
			
			// create one q1 operator per simulated database nodes
			MySQLTrackerOperator[] q2Ops = new MySQLTrackerOperator[this.numberOfSubops];
			createSubqueryOps(qPlan, q2Ops); 
			
			for(int i=0; i<q2Ops.length; i++){
				setOpIds(q2Ops[i].getOperatorId());
			}

			// create union operator to collect results from all compute nodes
			MySQLTrackerOperator q2UnionOp = new MySQLTrackerOperator();
			createUnionOp(qPlan, q2UnionOp); 
			
			setUnionOp(q2UnionOp.getOperatorId());

			// connect operators
			connectOps(qPlan, q2Ops, q2UnionOp); 
			return qPlan;
		} 
		
		/**
		 * @return the opIds
		 */
		public List<Identifier> getOpIds() {
			return opIds;
		}

		/**
		 * @param opIds the opIds to set
		 */
		public void setOpIds(Identifier opIds) {
			this.opIds.add(opIds);
		}

		/**
		 * @return the unionOp
		 */
		public Identifier getUnionOp() {
			return unionOp;
		}

		/**
		 * @param unionOp the unionOp to set
		 */
		public void setUnionOp(Identifier unionOp) {
			this.unionOp = unionOp;
		}
	} 
	
	private class Q2Upper extends DistributedTPCHTestCase {  
		
		private List<Identifier> opIds = new ArrayList<Identifier>(); 
		
		private Identifier unionOp; 

		// constructor
		public Q2Upper() {
			super(-1);
			String t1 = "<" + getUnionInTableName() + ">";

			this.subqueryDDL = "(l_partkey INTEGER, l_frequency INTEGER)";
			this.subqueryDML = "select" + 
					"			l_partkey," + 
					"			COUNT(*) as l_frequency " + 
					"		from " + 
					"			<TPCH_DB_NAME>.lineitem, " +
					"			<TPCH_DB_NAME>.orders, " +
					"			(" + t1 + ") as temp1 " + 
					"		where" + 
					"			o_custkey = temp1.c_custkey " + 
					"			and l_orderkey = o_orderkey " + 
					"		    and o_orderdate between date '1995-01-01' and date '1996-12-31' " +
					"		    and l_shipdate between date '1995-01-01' and date '1996-12-31' " +
					"		group by l_partkey;";

			  this.unionDDL = "(l_partkey INTEGER, l_frequency INTEGER)";
	          this.unionPreDML = "SELECT l_partkey, SUM(l_frequency) as l_frequency FROM ";
	          this.unionPostDML = "group by l_partkey order by l_frequency desc limit 10;";
		}

		public QueryTrackerPlan createPlan() throws Exception {
			// assign plan to query tracker
			QueryTrackerPlan qPlan = new QueryTrackerPlan();
			
			// create one q1 operator per simulated database nodes
			MySQLTrackerOperator[] q2Ops = new MySQLTrackerOperator[this.numberOfSubops];
			createSubqueryOps(qPlan, q2Ops); 
			
			for(int i=0; i<q2Ops.length; i++){
				setOpIds(q2Ops[i].getOperatorId());
			}
			
			// create union operator to collect results from all compute nodes
			MySQLTrackerOperator q2UnionOp = new MySQLTrackerOperator();
			createUnionOp(qPlan, q2UnionOp);  
			
			setUnionOp(q2UnionOp.getOperatorId());

			// connect operators
			connectOps(qPlan, q2Ops, q2UnionOp);
			return qPlan;
		}

		/**
		 * @return the opIds
		 */
		public List<Identifier> getOpIds() {
			return opIds;
		}

		/**
		 * @param opIds the opIds to set
		 */
		public void setOpIds(Identifier opIds) {
			this.opIds.add(opIds);
		}

		/**
		 * @return the unionOp
		 */
		public Identifier getUnionOp() {
			return unionOp;
		}

		/**
		 * @param unionOp the unionOp to set
		 */
		public void setUnionOp(Identifier unionOp) {
			this.unionOp = unionOp;
		}
    
	}
	
	public TestTPCHQ2FT(Boolean withFailure, Integer numOfFaliures) throws Exception {
		super(5); 
		
		this.withFailure =withFailure;
		this.numOfFailures = numOfFaliures;
    }  
	
	@Parameters
	public static Collection<Object[]> generateData(){
		Collection<Object[]> params = new ArrayList<Object[]>(2);
		params.add(new Object[]{new Boolean(false),new Integer(0)});
		params.add(new Object[]{new Boolean(false),new Integer(0)});
		
		for(int i=0; i < Config.TEST_NUMBER_OF_RUNS ; ++i){
			params.add(new Object[]{new Boolean(true),new Integer(Config.TEST_NUMBER_OF_FAILURES)});
			
		}
		return params;
	}
	
	@Test
	public void testQ2() throws Exception {
		Q2Upper q2Upper = new Q2Upper(); 
		Q2Lower q2Lower = new Q2Lower();
		
		QueryTrackerPlan qPlanUpper = q2Upper.createPlan();
		QueryTrackerPlan qPlanLower = q2Lower.createPlan(); 
		
		// The merged plan 
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
        
		QueryTrackerNode queryTrackerNode = TestTPCHQ2FT.queryTrackerNode; 
		qPlan.assignTracker(queryTrackerNode);
		queryTrackerNode.addPlan(qPlanUpper.getPlanId(), qPlan);
		queryTrackerNode.addPlan(qPlanLower.getPlanId(), qPlan);
		
		Map<Identifier, AbstractTrackerOperator> lowerTrackerOps = qPlanLower.getOperatorMapping();  
		Map<Identifier, AbstractTrackerOperator> higherTrackerOps = qPlanUpper.getOperatorMapping();  
		
		//qPlanLower.tracePlan("LowerPlan");
		qPlan.setTrackerOperators(lowerTrackerOps); 
		qPlan.setTrackerOperators(higherTrackerOps);
		
		// Add the roots of the merged qplan (the root of the upper plan) 
		// Add the leaves of the merged qplan (the leaves of the lower plan) 
		Set<Identifier> upperRoots = qPlanUpper.getRoots();  
		Set<Identifier> lowerLeaves = qPlanLower.getLeaves();   
		
		qPlan.setLeaves(lowerLeaves);  
		qPlan.setRoots(upperRoots);  
		
		// The leaves of the upper plan are consumers for roots of the lower plan.  
		// The roots of the lower plan are sources for the leaves of the upper plan. 
		Set<Identifier> upperLeaves = qPlanUpper.getLeaves();  
		Set<Identifier> lowerRoots = qPlanLower.getRoots();   
	    
		for (Identifier lowerRoot : lowerRoots) { 
			qPlan.setConsumers(lowerRoot, upperLeaves);  
			
		} 
		
		for (Identifier upperLeaf : upperLeaves) { 
			StringTemplate unionDDL = new StringTemplate("<UNION_IN> (o_total DECIMAL(15,2), c_custkey INTEGER )");
			// Added input table to the leaves of the upper query one by one. 
			AbstractTrackerOperator opUpperLeaf = qPlan.getTrackerOperator(upperLeaf); 
			opUpperLeaf.addInTable("UNION_IN", unionDDL);  
			for (Identifier identifier : lowerRoots) {
				AbstractTrackerOperator opLowerRoot = qPlan.getTrackerOperator(identifier);  
				opUpperLeaf.setInTableSource("UNION_IN", new TableDesc("UNION_OUT", opLowerRoot.getOperatorId()));
                
			}
			qPlan.setSources(upperLeaf, lowerRoots);
		}  
		
		Map<Identifier, Set<Identifier>> lowerSources = qPlanLower.getAllSourcesMap();   
		Map<Identifier, Set<Identifier>> lowerConsumers = qPlanLower.getAllConsumersMap();   
		
		// set the consumers and sources from the lower query plan 
		// with excluding the roots of the lower query plan when 
		// setting the consumers (since the consumer of the roots) 
		// has been set to the leaves of the upper query plan.
		
		Set<Entry<Identifier, Set<Identifier>>> lowerConsumersSet = lowerConsumers.entrySet();
		for (Entry<Identifier, Set<Identifier>> entry : lowerConsumersSet) {
			if(lowerRoots.contains(entry.getKey())) 
				continue; 
			else 
				qPlan.setConsumers(entry.getKey(), entry.getValue());
				
		} 
		
		Set<Entry<Identifier, Set<Identifier>>> lowerSourcesSet = lowerSources.entrySet();
		for (Entry<Identifier, Set<Identifier>> entry : lowerSourcesSet) {
		      qPlan.setSources(entry.getKey(), entry.getValue());
				
		}
		
		// Do the same for the upper part 
		Map<Identifier, Set<Identifier>> upperSources = qPlanUpper.getAllSourcesMap();   
		Map<Identifier, Set<Identifier>> upperConsumers = qPlanUpper.getAllConsumersMap();    
		
		Set<Entry<Identifier, Set<Identifier>>> upperConsumersSet = upperConsumers.entrySet(); 
		
		for (Entry<Identifier, Set<Identifier>> entry : upperConsumersSet) {
			qPlan.setConsumers(entry.getKey(), entry.getValue());
		}
		
		Set<Entry<Identifier, Set<Identifier>>> upperSourcesSet = upperSources.entrySet();  
		
		for (Entry<Identifier, Set<Identifier>> entry : upperSourcesSet) {
		    if(upperLeaves.contains(entry.getKey()))	
			    continue; 
		    else 
		    	qPlan.setSources(entry.getKey(), entry.getValue());
		    
		}
		
		qPlan.tracePlan("MergedPlan"); 
		
		// set the connection list foe the lower traker operators  
		setConnectionList(qPlan.getOperatorMapping(), q2Lower.getOpIds(), q2Lower.getUnionOp(), TestTPCHQ2FT.computeNodes); 
		
		// set the connection list for the upper tracker operators  
	    setConnectionList(qPlan.getOperatorMapping(), q2Upper.getOpIds(), q2Upper.getUnionOp(), TestTPCHQ2FT.computeNodes);  
	    
	    //this.assertError(this.executeQuery(qPlan, true));    

		// Execute it the number of runs  
	    this.assertError(this.executeQuery(qPlan, this.withFailure, this.numOfFailures, TestTPCHQ2FT.EXECUTION_TIME));  
	    if(!TestTPCHQ2FT.IS_EXECUTION_TIME_SET && TestTPCHQ2FT.RUN_COUNTER == 2){  
	    	TestTPCHQ2FT.EXECUTION_TIME = qPlan.getQueryExecutionTime();  
	    	TestTPCHQ2FT.IS_EXECUTION_TIME_SET = true; 
	    }   
	}
	
	@Before
	public void setUp(){
		if(RUN_COUNTER == 0) { 
		   super.setUp();  
		   TestTPCHQ2FT.queryTrackerNode = this.getQueryTrackerServer().getNode(); 
		   TestTPCHQ2FT.computeNodes = this.getComputeNodes();
		} 
		RUN_COUNTER++;
	}

} 


