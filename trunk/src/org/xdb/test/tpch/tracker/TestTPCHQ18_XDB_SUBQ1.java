package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

public class TestTPCHQ18_XDB_SUBQ1 extends DistributedTPCHTestCase { 
	
	// constructor
		public TestTPCHQ18_XDB_SUBQ1() {
			super(-1);
			this.subqueryDDL = "(l_orderkey INTEGER, quantity INTEGER )";
			this.subqueryDML = "SELECT l_orderkey, sum(l_quantity) as quantity from <TPCH_DB_NAME>.lineitem" +
					" group by l_orderkey; " ;					
			this.unionDDL = "(l_orderkey INTEGER, total_sum INTEGER)";
			this.unionPreDML = "SELECT l_orderkey, sum(quantity) as total_sum FROM ";
			this.unionPostDML = "GROUP BY l_orderkey having total_sum > 312  ;";
		}

		// methods
		@Test
		public void testTPCHQ18_XDB_SUBQ1() throws Exception {
			// assign plan to query tracker
			QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
			QueryTrackerPlan qPlan = new QueryTrackerPlan();
			qPlan.assignTracker(qTracker);

			// create one Q18 operator per database node
			MySQLTrackerOperator[] q18Ops = new MySQLTrackerOperator[this.numberOfSubops];
			createSubqueryOps(qPlan, q18Ops);

			// create union operator to collect results from all compute nodes
			MySQLTrackerOperator q18UnionOp = new MySQLTrackerOperator();
			createUnionOp(qPlan, q18UnionOp);

			// connect operators
			connectOps(qPlan, q18Ops, q18UnionOp);

			// create deployment and execute plan
			Map<Identifier, OperatorDesc> deployment = this.createDeployment(q18Ops,
					q18UnionOp);
			executeQuery(qPlan, deployment, q18UnionOp.getOperatorId(),
					getUnionOutTableName());
		}

}
