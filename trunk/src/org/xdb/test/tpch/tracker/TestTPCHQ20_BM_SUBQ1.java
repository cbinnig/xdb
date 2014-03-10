package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

public class TestTPCHQ20_BM_SUBQ1 extends DistributedTPCHTestCase {

	// constructor
	public TestTPCHQ20_BM_SUBQ1() {
		super(-1);
		this.subqueryDDL = "(l_suppkey INTEGER, l_partkey INTEGER, totalqty DECIMAL(65,2))";
		this.subqueryDML = "select l_suppkey, l_partkey, 0.5 * sum(l_quantity) as totalqty " +
				"						from <TPCH_DB_NAME>.lineitem " +
				"				 		where l_shipdate >= date '1994-01-01' " +
				"						and l_shipdate < date '1995-01-01' " +
				"				       	group by l_suppkey, l_partkey";
			
		
		this.unionDDL = "(l_suppkey INTEGER, l_partkey INTEGER, totalqty DECIMAL(65,2))";
		this.unionPreDML = "SELECT l_suppkey, l_partkey, 0.5*sum(totalqty) FROM ";
		this.unionPostDML = "group by l_suppkey, l_partkey";
	}

	// methods
	@Test
	public void testQ20() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one Q20 operator per database node
		MySQLTrackerOperator[] q20Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, q20Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q20UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q20UnionOp);

		// connect operators
		connectOps(qPlan, q20Ops, q20UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q20Ops,
				q20UnionOp);
		executeQuery(qPlan, deployment, q20UnionOp.getOperatorId(),
				getUnionOutTableName());
	}

}
