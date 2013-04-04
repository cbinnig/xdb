package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q17 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ17 extends DistributedTPCHTestCase {

	// constructor
	public TestTPCHQ17() {
		super(-1);
		this.resultDDL = "(avg_yearly DECIMAL(65,2))";
		this.subqueryDML = "select " + 
				"	sum (l_extendedprice) / 7.0 as avg_yearly " + 
				"from " + 
				"	<TPCH_DB_NAME>.lineitem, " + 
				"	<TPCH_DB_NAME>.part " + 
				"where " + 
				"	p_partkey = l_partkey " + 
				"	and p_brand = 'Brand#43' " + 
				"	and p_container = 'MED CASE' " + 
				"	and l_quantity < ( " + 
				"		select 0.2 * avg(l_quantity) " + 
				"		from <TPCH_DB_NAME>.lineitem " + 
				"		where l_partkey = p_partkey " + 
				"	);";
		this.unionPreDML = "SELECT sum(avg_yearly) FROM ";
		this.unionPostDML = "";
	}

	// methods
	@Test
	public void testQ17() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one Q17 operator per database node
		MySQLTrackerOperator[] q17Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, q17Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q17UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q17UnionOp);

		// connect operators
		connectOps(qPlan, q17Ops, q17UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q17Ops,
				q17UnionOp);
		executeQuery(qPlan, deployment, q17UnionOp.getOperatorId(),
				getUnionOutTableName());
	}
}
