package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q14 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ14 extends DistributedTPCHTestCase {

	// constructor
	public TestTPCHQ14() {
		super(1);
		this.subqueryDDL = "(promo_revenue DECIMAL(20,16))";
		this.subqueryDML = "SELECT sum(CASE WHEN p_type LIKE 'PROMO%' THEN l_extendedprice * (1 - l_discount) ELSE 0 END) / sum(l_extendedprice * (1 - l_discount)) AS promo_revenue " + 
				"FROM <TPCH_DB_NAME>.lineitem, " + 
				"     <TPCH_DB_NAME>.part " + 
				"WHERE l_partkey = p_partkey " + 
				"    AND l_shipdate >= date '1995-09-01' " + 
				"    AND l_shipdate < date '1995-10-01';";
		
		this.unionDDL = "(promo_revenue DECIMAL(20,16))";
		this.unionPreDML = "SELECT 100.00 * sum(promo_revenue) FROM ";
		this.unionPostDML = "";
	}

	// methods
	@Test
	public void testQ14() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one Q14 operator per database node
		MySQLTrackerOperator[] q14Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, q14Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q14UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q14UnionOp);

		// connect operators
		connectOps(qPlan, q14Ops, q14UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q14Ops,
				q14UnionOp);
		executeQuery(qPlan, deployment, q14UnionOp.getOperatorId(),
				getUnionOutTableName());
	}
}
