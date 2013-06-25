package org.xdb.test.tpch.tracker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.xdb.Config;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.AbstractTrackerOperator;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.tracker.operator.TableDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

/**
 * Tests Q1 on multiple compute nodes
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
	
	private class Q2Lower extends DistributedTPCHTestCase {
		
        private List<Identifier> opIds = new ArrayList<Identifier>(); 
		
		private Identifier unionOp; 
		
		// constructor
		public Q2Lower() {
			super(-1);

			this.subqueryDDL = "(min_supplycost DECIMAL(10,3), ps_partkey INTEGER)";
			this.subqueryDML = "select"
					+ "			min(ps_supplycost) as min_supplycost, "
					+ "			ps_partkey " + "		from "
					+ "			<TPCH_DB_NAME>.partsupp,"
					+ "			<TPCH_DB_NAME>.supplier,"
					+ "			<TPCH_DB_NAME>.nation," + "			<TPCH_DB_NAME>.region"
					+ "		where " + "			s_suppkey = ps_suppkey"
					+ "			and s_nationkey = n_nationkey"
					+ "			and n_regionkey = r_regionkey"
					+ "			and r_name = 'EUROPE'" + "		group by"
					+ "			ps_partkey;";

			this.unionDDL = "(min_supplycost DECIMAL(10,3), ps_partkey INTEGER)";
			this.unionPreDML = "SELECT MIN(min_supplycost), ps_partkey FROM ";
			this.unionPostDML = "group by ps_partkey;"; 
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

			this.subqueryDDL = "(s_acctbal DECIMAL(65,2), s_name CHAR(25), n_name CHAR(25), p_partkey INTEGER, p_mfgr CHAR(25), s_address VARCHAR(40), " +
	                 "s_phone CHAR(15), s_comment VARCHAR(101))";
			this.subqueryDML = "select" + 
					"			s_acctbal," + 
					"			s_name," + 
					"			n_name," + 
					"			p_partkey," + 
					"			p_mfgr," + 
					"			s_address," + 
					"			s_phone," + 
					"			s_comment " + 
					"		from " + 
					"			<TPCH_DB_NAME>.part," + 
					"			<TPCH_DB_NAME>.supplier," + 
					"			<TPCH_DB_NAME>.partsupp as ps," + 
					"			<TPCH_DB_NAME>.nation," + 
					"			<TPCH_DB_NAME>.region," +
					"			(" + t1 + ") as temp1 " + 
					"		where" + 
					"			p_partkey = ps.ps_partkey" + 
					"			and s_suppkey = ps.ps_suppkey" +
					"			and temp1.ps_partkey = ps.ps_partkey" +
					"			and temp1.min_supplycost = ps.ps_supplycost" + 
					"			and p_size = 15" + 
					"			and p_type like '%BRASS'" + 
					"			and s_nationkey = n_nationkey" + 
					"			and n_regionkey = r_regionkey" + 
					"			and r_name = 'EUROPE';";

			  this.unionDDL = "(s_acctbal DECIMAL(65,2), s_name CHAR(25), n_name CHAR(25), p_partkey INTEGER, p_mfgr CHAR(25), s_address VARCHAR(40), " +
	                  "s_phone CHAR(15), s_comment VARCHAR(101))";
	          this.unionPreDML = "SELECT DISTINCT * FROM ";
	          this.unionPostDML = "order by s_acctbal desc, n_name, s_name, p_partkey limit 100;";
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
		
		for(int i=0; i < Config.RUN_TIMES ; ++i){
			params.add(new Object[]{new Boolean(true),new Integer(Config.NUMBER_OF_FAILURES)});
			
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
        
		QueryTrackerNode queryTrackerNode = this.getQueryTrackerServer().getNode(); 
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
			StringTemplate unionDDL = new StringTemplate("<UNION_IN> (min_supplycost DECIMAL(10,3), ps_partkey INTEGER)");
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
		setConnectionList(qPlan.getOperatorMapping(), q2Lower.getOpIds(), q2Lower.getUnionOp()); 
		
		// set the connection list for the upper tracker operators  
	    setConnectionList(qPlan.getOperatorMapping(), q2Upper.getOpIds(), q2Upper.getUnionOp());  
	    
	    //this.assertError(this.executeQuery(qPlan, true));    

		// Execute it the number of runs  
	    this.assertError(this.executeQuery(qPlan, this.withFailure, this.numOfFailures, TestTPCHQ2FT.EXECUTION_TIME)); 
	    TestTPCHQ2FT.RUN_COUNTER++;
	    if(!TestTPCHQ2FT.IS_EXECUTION_TIME_SET && TestTPCHQ2FT.RUN_COUNTER == 2){  
	    	TestTPCHQ2FT.EXECUTION_TIME = qPlan.getQueryExecutionTime(); 
	    	TestTPCHQ2FT.IS_EXECUTION_TIME_SET = true; 
	    }
	   
	}
	
	@Before
	public void setUp(){
		super.setUp();
	}
	
	@After
	public void tearDown(){
		super.tearDown();
	}
} 


