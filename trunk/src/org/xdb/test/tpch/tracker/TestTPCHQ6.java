package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q6 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ6 extends DistributedTPCHTestCase {

	// constructor
	public TestTPCHQ6() {
		super(1);
		this.subqueryDDL = "(revenue DECIMAL(65,2))";
		this.subqueryDML = "select " + 
				"	sum(l_extendedprice * l_discount) as revenue " + 
				"from " + 
				"	<TPCH_DB_NAME>.lineitem " + 
				"where " + 
				"	l_shipdate >= date '1996-01-01' " + 
				"	and l_shipdate < date '1996-01-01' + interval '1' year " + 
				"	and l_discount between 0.08 - 0.01 and 0.08 + 0.01 " + 
				"	and l_quantity < 24;";
		
		this.unionDDL = "(revenue DECIMAL(65,2))";
		this.unionPreDML = "SELECT SUM(revenue) FROM ";
		this.unionPostDML = "";
	}

	// methods
	@Test
	public void testQ6() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one Q6 operator per database node
		MySQLTrackerOperator[] Q6Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, Q6Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator Q6UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, Q6UnionOp);

		// connect operators
		connectOps(qPlan, Q6Ops, Q6UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(Q6Ops,
				Q6UnionOp);
		executeQuery(qPlan, deployment, Q6UnionOp.getOperatorId(),
				getUnionOutTableName());
	}
}
