package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

public class TestTPCH_LEFTJOIN_WO extends DistributedTPCHTestCase  {

	public TestTPCH_LEFTJOIN_WO() {
		super(-1);
		this.subqueryDDL = "(c_custkey INTEGER)";
		this.subqueryDML = " select distinct(c_custkey) from <TPCH_DB_NAME>.customer " +
				"left outer join <TPCH_DB_NAME>.orders on c_custkey = o_custkey where o_orderkey is null; " ;
		
		this.unionDDL = "(cust_total INTEGER)";
		this.unionPreDML = "SELECT count(distinct c_custkey) as cust_total FROM ";
		this.unionPostDML = "";
	} 

	// methods
	@Test
	public void testQ() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one q1 operator per simulated database nodes
		MySQLTrackerOperator[] qOps = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, qOps);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q1UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q1UnionOp);

		// connect operators
		connectOps(qPlan, qOps, q1UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(qOps,
				q1UnionOp);
		executeQuery(qPlan, deployment, q1UnionOp.getOperatorId(), getUnionOutTableName());
	}

}
