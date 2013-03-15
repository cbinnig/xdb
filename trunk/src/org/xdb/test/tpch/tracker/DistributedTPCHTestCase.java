package org.xdb.test.tpch.tracker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.xdb.error.Error;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.test.DistributedQueryTrackerTestCase;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

public abstract class DistributedTPCHTestCase extends
		DistributedQueryTrackerTestCase {
	private int expectedCnt;

	// constructor
	public DistributedTPCHTestCase(int numberOfComputeServer, boolean runLocal,
			int expectedCnt) {
		super(numberOfComputeServer, runLocal);

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
	 * Creates static deployment for operators in order to execute query
	 * optimally
	 * 
	 * @param currentDeployment
	 */
	protected abstract Map<Identifier, OperatorDesc> createDeployment(
			MySQLTrackerOperator[] q3Ops, MySQLTrackerOperator q3UnionOp);

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
