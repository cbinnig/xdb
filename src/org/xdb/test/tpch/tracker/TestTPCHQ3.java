package org.xdb.test.tpch.tracker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.tracker.operator.TableDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

/**
 * Tests Q3 on NUMBER_COMPUTE_DBS TPC-H database instances using
 * NUMBER_COMPUTE_DBS+1 compute nodes
 * 
 * - NUMBER_COMPUTE_DBS compute nodes are executing the local sub-queries - 1
 * query is computing the union over all intermediate results
 * 
 * if RUN_LOCAL==true all compute nodes are started on local machine else no
 * compute nodes are started automatically (i.e., must be done manually)
 * 
 * @author cbinnig
 * 
 */
public class TestTPCHQ3 extends DistributedTPCHTestCase {
	private static boolean RUN_LOCAL = true;
	private static int NUMBER_COMPUTE_DBS = 10;
	private static final String RESULT_DDL = "(l_orderkey INTEGER, revenue DECIMAL(65,2), o_orderdate DATE, o_shippriority INTEGER)";
	private static Integer LAST_EXEC_OP_ID = 1;
	private static final String DB_NAME = "tpch_s01";

	// constructor
	public TestTPCHQ3() {
		super(NUMBER_COMPUTE_DBS, RUN_LOCAL, 1216 * NUMBER_COMPUTE_DBS);
	}

	// methods
	/**
	 * Creates static deployment for operators in order to execute sub-queries
	 * locally and union on last node
	 * 
	 * @param currentDeployment
	 */
	protected Map<Identifier, OperatorDesc> createDeployment(
			MySQLTrackerOperator[] q3Ops, MySQLTrackerOperator q3UnionOp) {
		Map<Identifier, OperatorDesc> currentDeployment = new HashMap<Identifier, OperatorDesc>();

		// create deployment for sub-queries operator
		for (int i = 0; i < q3Ops.length; ++i) {
			MySQLTrackerOperator q3Op = q3Ops[i];
			Identifier trackerOpId = q3Op.getOperatorId();
			Identifier execOpId = trackerOpId.clone().append(LAST_EXEC_OP_ID++);

			OperatorDesc executeOperDesc = new OperatorDesc(execOpId,
					this.getComputeSlot(i));
			currentDeployment.put(trackerOpId, executeOperDesc);
		}

		// create deployment for union operator
		Identifier trackerOpId = q3UnionOp.getOperatorId();
		Identifier execOpId = trackerOpId.clone().append(LAST_EXEC_OP_ID++);
		OperatorDesc executeOperDesc = new OperatorDesc(execOpId,
				this.getComputeSlot(0));
		currentDeployment.put(trackerOpId, executeOperDesc);

		return currentDeployment;
	}

	/**
	 * Creates MySQLTrackerOperators for all Q3 sub-queries that can be executed
	 * on one database instance and adds them to the Query Tracker Plan
	 * 
	 * @param qPlan
	 * @param q3Ops
	 */
	protected void createSubqueryOps(QueryTrackerPlan qPlan,
			MySQLTrackerOperator[] q3Ops) {
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			q3Ops[i] = new MySQLTrackerOperator();

			// DDL for output of sub-query q3
			String q3OutTableName = getSubqueryOutTableName(i);
			StringTemplate q3OutDDL = new StringTemplate("<" + q3OutTableName
					+ "> " + RESULT_DDL);
			q3Ops[i].addOutTable(q3OutTableName, q3OutDDL);

			// DML for sub-query q3
			String dbName = DB_NAME;
			StringTemplate q3DML = new StringTemplate("insert into <"
					+ q3OutTableName + "> select l_orderkey, "
					+ "sum(l_extendedprice*(1-l_discount)) as revenue, "
					+ "o_orderdate, " + "o_shippriority " + "from " + dbName
					+ ".customer, " + dbName + ".orders, " + dbName
					+ ".lineitem " + "where c_mktsegment = 'BUILDING' and "
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
	 * Creates an instance for MySQLTrackerOperator for the Q3 union query and
	 * adds this operator to Query Tracker Plan
	 * 
	 * @param qPlan
	 * @param q3UnionOp
	 */
	protected void createUnionOp(QueryTrackerPlan qPlan,
			MySQLTrackerOperator q3UnionOp) {
		// DDL for output of union
		String q3UnionOutTableName = getUnionOutTableName();
		StringTemplate q3UnionOutDDL = new StringTemplate("<"
				+ q3UnionOutTableName + "> " + RESULT_DDL);
		q3UnionOp.addOutTable(q3UnionOutTableName, q3UnionOutDDL);

		// DDL for all inputs of union
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			String q3UnionInTableName = getUnionInTableName(i);
			StringTemplate q3UnionInDDL = new StringTemplate("<"
					+ q3UnionInTableName + "> " + RESULT_DDL);

			q3UnionOp.addInTable(q3UnionInTableName, q3UnionInDDL);
		}

		// DML for union query
		StringBuffer q3UnionTMPDDL = new StringBuffer("INSERT INTO <");
		q3UnionTMPDDL.append(q3UnionOutTableName);
		q3UnionTMPDDL.append("> ");
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			String q3UnionInTableName = getUnionInTableName(i);

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
	 * Connects MySQLTrackerOperators in QueryTrackerPlan: All Q3 sub-queries
	 * are consumed by Q3 union query
	 * 
	 * @param qPlan
	 * @param q3Ops
	 * @param q3UnionOp
	 */
	protected void connectOps(QueryTrackerPlan qPlan,
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

			String q3OutTableName = getSubqueryOutTableName(i);
			String q3UnionInTableName = getUnionInTableName(i);
			q3UnionOp.setInTableSource(q3UnionInTableName, new TableDesc(
					q3OutTableName, q3Ops[i].getOperatorId()));
		}
	}

	@Test
	public void testQ3() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one q3 operator per simulated database nodes
		MySQLTrackerOperator[] q3Ops = new MySQLTrackerOperator[NUMBER_COMPUTE_DBS];
		createSubqueryOps(qPlan, q3Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q3UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q3UnionOp);

		// connect operators
		connectOps(qPlan, q3Ops, q3UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q3Ops,
				q3UnionOp);
		executeQuery(qPlan, deployment, q3UnionOp.getOperatorId());
	}
}
