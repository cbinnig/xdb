package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q15 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ15 extends DistributedTPCHTestCase {

	// constructor
	public TestTPCHQ15() {
		super(-1);
		this.subqueryDDL = "(s_suppkey INTEGER, s_name CHAR(25), s_address CHAR(40), s_phone CHAR(15), total_revenue DECIMAL(65,2))";
		this.subqueryDML = "SELECT s_suppkey, " + 
				"       s_name, " + 
				"       s_address, " + 
				"       s_phone, " + 
				"       total_revenue " + 
				"FROM <TPCH_DB_NAME>.supplier, <TPCH_DB_NAME>.revenue0 " + 
				"WHERE s_suppkey = supplier_no " + 
				"    AND total_revenue = " + 
				"        ( SELECT max(total_revenue) " + 
				"         FROM <TPCH_DB_NAME>.revenue0 ) " + 
				"ORDER BY s_suppkey; ";
		
		this.unionDDL = "(s_suppkey INTEGER, s_name CHAR(25), s_address CHAR(40), s_phone CHAR(15), total_revenue DECIMAL(65,2))";
		this.unionPreDML = "SELECT s_suppkey,s_name, s_address, s_phone, sum(total_revenue) as total_revenue FROM ";
		this.unionPostDML = "GROUP BY s_suppkey ORDER BY s_suppkey";
	}

	// methods
	@Test
	public void testQ15() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one Q15 operator per database node
		MySQLTrackerOperator[] q15Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, q15Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q15UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q15UnionOp);

		// connect operators
		connectOps(qPlan, q15Ops, q15UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q15Ops,
				q15UnionOp);
		executeQuery(qPlan, deployment, q15UnionOp.getOperatorId(),
				getUnionOutTableName());
	}
}
