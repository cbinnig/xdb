package org.xdb.test.tpch.tracker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.xdb.error.Error;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.test.DistributedQueryTrackerTestCase;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.tracker.operator.TableDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

/**
 * Tests Q3 on 10 s01 TPC-H database instances using 10 compute nodes
 * 
 * if RUN_LOCAL==true all compute nodes are started on local machine
 * else no compute nodes are started automatically (i.e., must be done manually)
 * 
 * @author cbinnig
 *
 */
public class TestTPCHQ3 extends DistributedQueryTrackerTestCase {
	private static boolean RUN_LOCAL = true;
	private static int NUMBER_COMPUTE_DBS = 10;
	private static final String RESULT_DDL = "(l_orderkey INTEGER, revenue DECIMAL(65,2), o_orderdate DATE, o_shippriority INTEGER)";
	
	// constructor
	public TestTPCHQ3() {
		super(NUMBER_COMPUTE_DBS + 1, RUN_LOCAL);
	}

	// getter and setter
	private String getQ3OutTableName(int i) {
		return "Q3_OUT" + i;
	}

	private String getQ3UnionInTableName(int i) {
		return "Q3_UNION_IN" + i;
	}

	private String getQ3UnionOutTableName() {
		return "Q3_UNION_OUT";
	}

	// methods
	
	/**
	 * Creates MySQLTrackerOperators for all Q3 sub-queries
	 * that can be executed on one database instance
	 * and adds them to the Query Tracker Plan
	 * 
	 * @param qPlan
	 * @param q3Ops
	 */
	private void createQ3Subqueries(QueryTrackerPlan qPlan,
			MySQLTrackerOperator[] q3Ops) {
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			q3Ops[i] = new MySQLTrackerOperator();

			// DDL for output of sub-query q3
			String q3OutTableName = getQ3OutTableName(i);
			StringTemplate q3OutDDL = new StringTemplate("<" + q3OutTableName
					+ "> " + RESULT_DDL);
			q3Ops[i].addOutTable(q3OutTableName, q3OutDDL);

			// DML for sub-query q3
			//String dbName = "tpch"+(i%4)+"_s01";
			String dbName = "tpch_s01";
			StringTemplate q3DML = new StringTemplate("insert into <"
					+ q3OutTableName + "> select l_orderkey, "
					+ "sum(l_extendedprice*(1-l_discount)) as revenue, "
					+ "o_orderdate, " + "o_shippriority "
					+ "from "+dbName+".customer, " 
					+ dbName+".orders, "
					+ dbName+".lineitem "
					+ "where c_mktsegment = 'BUILDING' and "
					+ "c_custkey = o_custkey and "
					+ "l_orderkey = o_orderkey and "
					+ "o_orderdate < date '1995-03-15' and "
					+ "l_shipdate > date '1995-03-15' "
					+ "group by l_orderkey, o_orderdate, o_shippriority");
			q3Ops[i].addExecuteSQL(q3DML);

			qPlan.addOperator(q3Ops[i]);
		}
	}

	/**
	 * Creates an instance for MySQLTrackerOperator 
	 * for the Q3 union query and adds this 
	 * operator to Query Tracker Plan
	 * 
	 * @param qPlan
	 * @param q3UnionOp
	 */
	private void createQ3Union(QueryTrackerPlan qPlan,
			MySQLTrackerOperator q3UnionOp) {
		// DDL for output of union
		String q3UnionOutTableName = getQ3UnionOutTableName();
		StringTemplate q3UnionOutDDL = new StringTemplate("<"
				+ q3UnionOutTableName + "> " + RESULT_DDL);
		q3UnionOp.addOutTable(q3UnionOutTableName, q3UnionOutDDL);

		// DDL for all inputs of union
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			String q3UnionInTableName = getQ3UnionInTableName(i);
			StringTemplate q3UnionInDDL = new StringTemplate("<"
					+ q3UnionInTableName + "> " + RESULT_DDL);

			q3UnionOp.addInTable(q3UnionInTableName, q3UnionInDDL);
		}

		// DML for union query
		StringBuffer q3UnionTMPDDL = new StringBuffer("INSERT INTO <");
		q3UnionTMPDDL.append(q3UnionOutTableName);
		q3UnionTMPDDL.append("> ");
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			String q3UnionInTableName = getQ3UnionInTableName(i);

			q3UnionTMPDDL.append("(<");
			q3UnionTMPDDL.append(q3UnionInTableName);
			q3UnionTMPDDL.append(">)");

			if (i < NUMBER_COMPUTE_DBS - 1) {
				q3UnionTMPDDL.append(" UNION ALL ");
			} else {
				q3UnionTMPDDL.append(";");
			}
		}
		StringTemplate q3UnionDML = new StringTemplate(q3UnionTMPDDL.toString());
		q3UnionOp.addExecuteSQL(q3UnionDML);

		// add operator to plan
		qPlan.addOperator(q3UnionOp);
	}

	/**
	 * Connects MySQLTrackerOperators in QueryTrackerPlan:
	 * All Q3 sub-queries are consumed by Q3 union query
	 * 
	 * @param qPlan
	 * @param q3Ops
	 * @param q3UnionOp
	 */
	private void connectQ3Ops(QueryTrackerPlan qPlan,
			MySQLTrackerOperator[] q3Ops, MySQLTrackerOperator q3UnionOp) {
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			final Set<Identifier> q3OpConsumer = new HashSet<Identifier>();
			q3OpConsumer.add(q3UnionOp.getOperatorId());
			qPlan.setConsumers(q3Ops[i].getOperatorId(), q3OpConsumer);
		}

		final Set<Identifier> q3UnionOpSources = new HashSet<Identifier>();
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			q3UnionOpSources.add(q3Ops[i].getOperatorId());
			qPlan.setSources(q3UnionOp.getOperatorId(), q3UnionOpSources);

			String q3OutTableName = getQ3OutTableName(i);
			String q3UnionInTableName = getQ3UnionInTableName(i);
			q3UnionOp.setInTableSource(q3UnionInTableName, new TableDesc(
					q3OutTableName, q3Ops[i].getOperatorId()));
		}
	}

	/**
	 * Executes the Query Tracker Plan of Q3 
	 * and Checks if results size is correct
	 * @param qPlan
	 * @param q3UnionOp
	 * @throws SQLException
	 */
	private void executeQ3(QueryTrackerPlan qPlan,
			MySQLTrackerOperator q3UnionOp) throws SQLException {
		Error err = qPlan.deployPlan();
		if (err.isError())
			qPlan.cleanPlanOnError();
		this.assertNoError(err);

		err = qPlan.executePlan();
		if (err.isError())
			qPlan.cleanPlanOnError();
		this.assertNoError(err);

		// read result
		String q3UnionOutTableName = getQ3UnionOutTableName();
		final Map<Identifier, OperatorDesc> currentDeployment = qPlan
				.getCurrentDeployment();
		Identifier resultTable = currentDeployment.get(
				q3UnionOp.getOperatorId()).getOperatorID();
		final ResultSet rs = this.executeComputeQuery("SELECT COUNT(*) FROM "
				+ resultTable + "_" + q3UnionOutTableName);
		int actualCnt = 0;
		if (rs.next()) {
			actualCnt = rs.getInt(1);
		}

		// clean plan
		this.assertNoError(qPlan.cleanPlan());

		// verify results
		assertEquals(1216*NUMBER_COMPUTE_DBS, actualCnt);
	}

	@Test
	public void testQ3() throws Exception {
		QueryTrackerNode qTracker = this.qServer.getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one q3 operator per simulated database nodes
		MySQLTrackerOperator[] q3Ops = new MySQLTrackerOperator[NUMBER_COMPUTE_DBS];
		createQ3Subqueries(qPlan, q3Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q3UnionOp = new MySQLTrackerOperator();
		createQ3Union(qPlan, q3UnionOp);

		// connect operators
		connectQ3Ops(qPlan, q3Ops, q3UnionOp);

		// execute plan
		executeQ3(qPlan, q3UnionOp);
	}

}
