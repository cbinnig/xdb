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
		super(1);
		this.resultDDL = "(s_suppkey INTEGER, s_name CHAR(25), s_address CHAR(40), s_phone CHAR(15), total_revenue DECIMAL(65,2))";
		this.subqueryDML = "CREATE VIEW <TPCH_DB_NAME>.revenue0(supplier_no, total_revenue) AS " + 
				"SELECT l_suppkey, " + 
				"       sum(l_extendedprice * (1 - l_discount)) " + 
				"FROM <TPCH_DB_NAME>.lineitem " + 
				"WHERE l_shipdate >= '1996-10-01' " + 
				"    AND l_shipdate < '1997-01-01' " + 
				"GROUP BY l_suppkey; " + 
				" " + 
				"SELECT s_suppkey, " + 
				"       s_name, " + 
				"       s_address, " + 
				"       s_phone, " + 
				"       total_revenue " + 
				"FROM <TPCH_DB_NAME>.supplier, <TPCH_DB_NAME>.revenue0 " + 
				"WHERE s_suppkey = supplier_no " + 
				"    AND total_revenue = " + 
				"        ( SELECT max(total_revenue) " + 
				"         FROM <TPCH_DB_NAME>.revenue0 ) " + 
				"ORDER BY s_suppkey; " + 
				" " + 
				"DROP VIEW revenue0;";
		this.unionPreDML = "SELECT * FROM ";
		this.unionPostDML = "ORDER BY s_suppkey";
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
