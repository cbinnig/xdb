package org.xdb.test.tpch.tracker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.AbstractTrackerOperator;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.tracker.operator.TableDesc;
import org.xdb.tracker.operator.udf.LevenshteinTrackerOp;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

/**
 * Run a basket analysis using a UDF to determine the string similarity of two
 * products in the same order.
 * 
 * Idea: Only suggest products which have a similar "type".
 * 
 * @author cbinnig
 * 
 */


@RunWith(Parameterized.class)
public class TestTPCHBasketAnalysisParallel extends DistributedTPCHTestCase {
	private static final String UDF_OUT_TABLE = "udf_out";
	private static final String UDF_IN_TABLE = "udf_in";
	private int numberOfPartitions = 2;
	
	private static boolean setupcalled=false;
	private static QueryTrackerNode qtn=null;
	private static ComputeNodeDesc[] computenodes;

	// constructor
	public TestTPCHBasketAnalysisParallel (int numberOfPartitions) {
		super(10);
		
		this.numberOfPartitions = numberOfPartitions;
		this.subqueryDDL = "(p1_key INTEGER, p2_key INTEGER, p1_type VARCHAR(25), p2_type VARCHAR(25), frequency DECIMAL(65,2))";
		this.subqueryDML = "select l1.l_partkey as p1_key, l2.l_partkey as p2_key, "
				+ "p1.p_type as p1_type, p2.p_type as p2_type, count(*) as frequency "
				+ "from "
				+ "<TPCH_DB_NAME>"
				+ ".lineitem l1, "
				+ "<TPCH_DB_NAME>"
				+ ".lineitem l2, "
				+ "<TPCH_DB_NAME>"
				+ ".part p1, "
				+ "<TPCH_DB_NAME>"
				+ ".part p2 "
				+ "where l1.l_orderkey = l2.l_orderkey "
				+ "and l1.l_partkey = p1.p_partkey "
				+ "and l2.l_partkey = p2.p_partkey "
				+ "and l1.l_partkey != l2.l_partkey "
				+ "and l1.l_shipdate > date '1998-03-15' "
				+ "and l1.l_shipdate < date '1998-04-08' "
				+ "and l2.l_shipdate > date '1998-03-15' "
				+ "and l2.l_shipdate < date '1998-04-08' "
				+ "group by l1.l_partkey, l2.l_partkey, p1.p_type, p2.p_type"
				+ "; ";

		this.unionDDL = "(p1_key INTEGER, p2_key INTEGER, p1_type VARCHAR(25), p2_type VARCHAR(25), frequency DECIMAL(65,2)) ";
		this.unionPartitionDDL = "PARTITION BY HASH(p1_key) PARTITIONS "+this.numberOfPartitions;
		this.unionPreDML = "SELECT p1_key, p2_key, p1_type, p2_type, sum(frequency) as frequency FROM ";
		this.unionPostDML = "group by p1_key, p2_key, p1_type, p2_type order by frequency desc;";
	
	}
	
	@Parameters
	public static Collection<Object[]> generateData(){
		
		
		Collection<Object[]> params = new ArrayList<Object[]>(2);
		params.add(new Object[]{new Integer(2)});
		params.add(new Object[]{new Integer(4)});
		params.add(new Object[]{new Integer(8)});
		//params.add(new Object[]{new Integer(16)});
		//params.add(new Object[]{new Integer(32)});
		return params;
	}

	// methods
	@Test
	public void testBasketAnalysis() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = qtn;
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one operator per database node
		MySQLTrackerOperator[] subqueryOps = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, subqueryOps);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator unionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, unionOp);

		// connect operators
		connectOps(qPlan, subqueryOps, unionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(
				subqueryOps, unionOp);

		// create LevenshteinTrackerOp
		Map<Identifier,String> idToTableName = new HashMap<Identifier, String>();
		for(int i=0; i < numberOfPartitions; i++){
			LevenshteinTrackerOp udfOp = addLevenshteinOp(qPlan, deployment,
					unionOp,i );
			idToTableName.put( udfOp.getOperatorId(), UDF_OUT_TABLE);
	
		}
		executeQuery(qPlan, deployment, idToTableName) ;

	}

	
	@Override
	protected void executeQuery(QueryTrackerPlan qPlan,
			Map<Identifier, OperatorDesc> deployment,
			Map<Identifier, String> resultOpIds) throws SQLException {
		// deploy plan to compute nodes
				Error err = qPlan.deployPlan(deployment);

				
				if (err.isError())
					qPlan.cleanPlanOnError();
				this.assertNoError(err);

				// execute plan using query tracker
				err = qPlan.executePlan();
				if (err.isError())
					qPlan.cleanPlanOnError();
				this.assertNoError(err);

				final Map<Identifier, OperatorDesc> currentDeployment = qPlan
						.getCurrentDeployment();
				int actualCnt = 0;
				for (Identifier resultOpId : resultOpIds.keySet()) {
					OperatorDesc rootDesc = currentDeployment.get(resultOpId);
					String resultTableName = resultOpIds.get(resultOpId);
					Identifier resultTable = rootDesc.getOperatorID();
					String rootUrl = "jdbc:mysql://"+rootDesc.getComputeSlot().getUrl()+"/"+Config.COMPUTE_DB_NAME;
					final ResultSet rs = this
						.executeComputeQuery(rootUrl, "SELECT COUNT(*) FROM " + resultTable
								+ "_" + resultTableName);
				
					if (rs.next()) {
					actualCnt += rs.getInt(1);
					}
					/*
					if (actualCnt>0){
						final ResultSet results = this.executeComputeQuery(rootUrl,"SELECT * FROM " + resultTable + "_" + resultTableName);
						//System.out.println(prettyPrintResultSet(results, 10));
					}*/

				}
				
				System.out.println("#################QueryExecuted with Count:"+actualCnt+"#################");
				
				assertTrue(actualCnt >0);
			

				// clean plan
				this.assertNoError(qPlan.cleanPlan());
	}

	
	protected Map<Identifier, OperatorDesc> createDeployment(
			MySQLTrackerOperator[] subqueryOps, MySQLTrackerOperator unionOp) {
		Map<Identifier, OperatorDesc> currentDeployment = new HashMap<Identifier, OperatorDesc>();

		// create deployment for sub-queries operator
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			MySQLTrackerOperator subqueryOp = subqueryOps[i];
			Identifier trackerOpId = subqueryOp.getOperatorId();
			Identifier execOpId = trackerOpId.clone().append(nextExecOpId());

			OperatorDesc executeOperDesc = new OperatorDesc(execOpId,
					computenodes[i/Config.TEST_PARTS_PER_NODE]);
			currentDeployment.put(trackerOpId, executeOperDesc);
		}

		// create deployment for union operator
		Identifier trackerOpId = unionOp.getOperatorId();
		Identifier execOpId = trackerOpId.clone().append(nextExecOpId());
		OperatorDesc executeOperDesc = new OperatorDesc(execOpId,
				computenodes[0]);
		currentDeployment.put(trackerOpId, executeOperDesc);

		return currentDeployment;
	} 
	
	
	/**
	 * Adds UDF operator to query tracker plan
	 * 
	 * @param qPlan
	 * @param deployment
	 * @param unionOp
	 * @param partition
	 * @return
	 */
	protected LevenshteinTrackerOp addLevenshteinOp(QueryTrackerPlan qPlan,
			Map<Identifier, OperatorDesc> deployment,
			AbstractTrackerOperator unionOp, int partition) {

		LevenshteinTrackerOp udfOp = new LevenshteinTrackerOp(UDF_IN_TABLE,
				UDF_OUT_TABLE);
		qPlan.addOperator(udfOp);

		// add input and output tables
		StringTemplate udfOutDDL = new StringTemplate("<" + UDF_OUT_TABLE
				+ "> " + subqueryDDL);
		udfOp.addOutTable(UDF_OUT_TABLE, udfOutDDL);

		StringTemplate udfInDDL = new StringTemplate("<" + UDF_IN_TABLE + "> "
				+ subqueryDDL);
		udfOp.addInTable(UDF_IN_TABLE, udfInDDL);

		// connect to unionOp to udfOp
		final Set<Identifier> udfOpSources = new HashSet<Identifier>();
		udfOpSources.add(unionOp.getOperatorId());
		qPlan.setSources(udfOp.getOperatorId(), udfOpSources);
	
		udfOp.setInTableSource(
				UDF_IN_TABLE,
				new TableDesc(this.getUnionOutTableName(), unionOp
						.getOperatorId(),partition));

		/*final Set<Identifier> unionConsumers = new HashSet<Identifier>();
		unionConsumers.add(u);*/
		qPlan.addConsumer(unionOp.getOperatorId(), udfOp.getOperatorId());

		// create deployment
		Identifier trackerOpId = udfOp.getOperatorId();
		
		
		//System.out.println(partition%this.getNumberOfComputeNodes()+"");
		
		Identifier execOpId = trackerOpId.clone().append(nextExecOpId());
		
		
		//deploy Operator in a round robin way to number of 
		OperatorDesc executeOperDesc = new OperatorDesc(execOpId,
				computenodes[partition%computenodes.length]);
		deployment.put(trackerOpId, executeOperDesc);

		return udfOp;
	}

	public void setNumberOfPartitions(int numberOfPartitions) {
		this.numberOfPartitions = numberOfPartitions;
	}
	
	
	@Before
	public void setUp(){
		
		if(!setupcalled){
			super.setUp();
			qtn=  this.getQueryTrackerServer().getNode();
			computenodes = this.getComputeNodes();
			setupcalled = true;
		}
	
	}
	
	@After
	public void tearDown(){
		//super.tearDown();
	}
}
