package org.xdb.test.tpch.tracker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.xdb.error.Error;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.test.DistributedQueryTrackerTestCase;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.tracker.operator.TableDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

public abstract class DistributedTPCHTestCase extends
		DistributedQueryTrackerTestCase {
	
	protected static int NUMBER_COMPUTE_DBS = 2;
	protected static Integer LAST_EXEC_OP_ID = 1;
	protected static final String dbName = "tpch_s01";

	protected int expectedCnt = 0;
	protected String resultDDL = "";
	protected String subqueryDML = "";
	protected String unionPreDML = "SELECT * FROM ";
	protected String unionPostDML = ";";
	
	// constructor
	public DistributedTPCHTestCase(boolean runLocal,
			int expectedCnt) {
		super(NUMBER_COMPUTE_DBS, runLocal);
		this.expectedCnt = expectedCnt;
	}

	// getters and setters
	protected String getSubqueryOutTableName(int i) {
		return "SUBQ_OUT" + i;
	}

	protected String getUnionInTableName(int i) {
		return "UNION_IN" + i;
	}

	protected String getUnionOutTableName() {
		return "UNION_OUT";
	}

	// methods
	/**
	 * Connects MySQLTrackerOperators in QueryTrackerPlan: All Q3 sub-queries
	 * are consumed by Q3 union query
	 * 
	 * @param qPlan
	 * @param subqueryOps
	 * @param unionOp
	 */
	protected void connectOps(QueryTrackerPlan qPlan,
			MySQLTrackerOperator[] subqueryOps, MySQLTrackerOperator unionOp) {
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			final Set<Identifier> q3OpConsumer = new HashSet<Identifier>();
			q3OpConsumer.add(unionOp.getOperatorId());
			qPlan.setConsumers(subqueryOps[i].getOperatorId(), q3OpConsumer);
		}

		final Set<Identifier> q3UnionOpSources = new HashSet<Identifier>();
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			q3UnionOpSources.add(subqueryOps[i].getOperatorId());
			qPlan.setSources(unionOp.getOperatorId(), q3UnionOpSources);

			String q3OutTableName = getSubqueryOutTableName(i);
			String q3UnionInTableName = getUnionInTableName(i);
			unionOp.setInTableSource(q3UnionInTableName, new TableDesc(
					q3OutTableName, subqueryOps[i].getOperatorId()));
		}
	}
	
	protected void createUnionOp(QueryTrackerPlan qPlan,
			MySQLTrackerOperator unionOp) {
		// DDL for output of union
		String unionOutTableName = getUnionOutTableName();
		StringTemplate q3UnionOutDDL = new StringTemplate("<"
				+ unionOutTableName + "> " + resultDDL);
		unionOp.addOutTable(unionOutTableName, q3UnionOutDDL);

		// DDL for all inputs of union
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			String q3UnionInTableName = getUnionInTableName(i);
			StringTemplate q3UnionInDDL = new StringTemplate("<"
					+ q3UnionInTableName + "> " + resultDDL);

			unionOp.addInTable(q3UnionInTableName, q3UnionInDDL);
		}

		// DML for union query
		StringBuffer unionTMPDDL = new StringBuffer("INSERT INTO <");
		unionTMPDDL.append(unionOutTableName);
		unionTMPDDL.append("> ");
		unionTMPDDL.append(this.unionPreDML);
		unionTMPDDL.append(" ( ");
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			String q3UnionInTableName = getUnionInTableName(i);

			unionTMPDDL.append("(<");
			unionTMPDDL.append(q3UnionInTableName);
			unionTMPDDL.append(">)");

			if (i < NUMBER_COMPUTE_DBS - 1) {
				unionTMPDDL.append(" UNION ALL ");
			} 
		}
		unionTMPDDL.append(") as uniontmp ");
		unionTMPDDL.append(this.unionPostDML);
		StringTemplate q3UnionDML = new StringTemplate(unionTMPDDL.toString());
		unionOp.addExecuteSQL(q3UnionDML);

		// add operator to plan
		qPlan.addOperator(unionOp);
	}
	
	/**
	 * Creates MySQLTrackerOperators for all sub-queries that can be executed
	 * on one database instance and adds them to the Query Tracker Plan
	 * 
	 * @param qPlan
	 * @param subqueryOps
	 */
	protected void createSubqueryOps(QueryTrackerPlan qPlan,
			MySQLTrackerOperator[] subqueryOps) {
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			subqueryOps[i] = new MySQLTrackerOperator();

			// DDL for output of sub-query q5
			String q5OutTableName = getSubqueryOutTableName(i);
			StringTemplate q5OutDDL = new StringTemplate("<" + q5OutTableName
					+ "> " + resultDDL);
			subqueryOps[i].addOutTable(q5OutTableName, q5OutDDL);

			// DML for sub-query q5
			StringTemplate q5DML = new StringTemplate("insert into <"
					+ q5OutTableName + "> "+this.subqueryDML);

			subqueryOps[i].addExecuteSQL(q5DML);

			qPlan.addOperator(subqueryOps[i]);
		}
	}
	
	/**
	 * Creates static deployment for operators in order to execute sub-queries
	 * locally and union on last node
	 * 
	 * @param currentDeployment
	 */
	protected Map<Identifier, OperatorDesc> createDeployment(
			MySQLTrackerOperator[] subqueryOps, MySQLTrackerOperator unionOp) {
		Map<Identifier, OperatorDesc> currentDeployment = new HashMap<Identifier, OperatorDesc>();

		// create deployment for sub-queries operator
		for (int i = 0; i < subqueryOps.length; ++i) {
			MySQLTrackerOperator q3Op = subqueryOps[i];
			Identifier trackerOpId = q3Op.getOperatorId();
			Identifier execOpId = trackerOpId.clone().append(LAST_EXEC_OP_ID++);

			OperatorDesc executeOperDesc = new OperatorDesc(execOpId,
					this.getComputeSlot(i));
			currentDeployment.put(trackerOpId, executeOperDesc);
		}

		// create deployment for union operator
		Identifier trackerOpId = unionOp.getOperatorId();
		Identifier execOpId = trackerOpId.clone().append(LAST_EXEC_OP_ID++);
		OperatorDesc executeOperDesc = new OperatorDesc(execOpId,
				this.getComputeSlot(0));
		currentDeployment.put(trackerOpId, executeOperDesc);

		return currentDeployment;
	}
	
	/**
	 * Executes the Query Tracker Plan and Checks if results size is correct
	 * 
	 * @param qPlan
	 * @param unionOp
	 * @throws SQLException
	 */
	protected void executeQuery(QueryTrackerPlan qPlan,
			Map<Identifier, OperatorDesc> deployment, Identifier unionOpId)
			throws SQLException {

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

		// read result
		String q3UnionOutTableName = getUnionOutTableName();
		final Map<Identifier, OperatorDesc> currentDeployment = qPlan
				.getCurrentDeployment();
		Identifier resultTable = currentDeployment.get(unionOpId)
				.getOperatorID();
		final ResultSet rs = this.executeComputeQuery("SELECT COUNT(*) FROM "
				+ resultTable + "_" + q3UnionOutTableName);
		int actualCnt = 0;
		if (rs.next()) {
			actualCnt = rs.getInt(1);
		}

		// clean plan
		this.assertNoError(qPlan.cleanPlan());

		// verify results
		assertEquals(expectedCnt, actualCnt);
	}
}
