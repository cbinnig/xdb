package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q4 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ4 extends DistributedTPCHTestCase {
	
	// constructor
	public TestTPCHQ4() {
		super(-1);
		this.resultDDL = "(o_orderpriority CHAR(15), order_count INTEGER)";
		this.subqueryDML = "select o_orderpriority,	count(*) as order_count from <TPCH_DB_NAME>.orders " + 
				"where o_orderdate >= date '1996-01-01' and o_orderdate < date '1996-01-01' + interval '3' month " + 
				"	and exists (select * from <TPCH_DB_NAME>.lineitem	where l_orderkey = o_orderkey and l_commitdate < l_receiptdate) " + 
				"group by o_orderpriority " + 
				"order by o_orderpriority;";
		
		this.unionPreDML = "SELECT * FROM ";
		this.unionPostDML = "order by o_orderpriority;";
	}

	// methods
	@Test
	public void testQ4() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one q3 operator per simulated database nodes
		MySQLTrackerOperator[] q4Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, q4Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q4UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q4UnionOp);

		// connect operators
		connectOps(qPlan, q4Ops, q4UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q4Ops,
				q4UnionOp);
		executeQuery(qPlan, deployment, q4UnionOp.getOperatorId(), getUnionOutTableName());
	}
}
