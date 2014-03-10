package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

public class TestTPCHQ14_BM_SUBQ1 extends DistributedTPCHTestCase { 
	
	// constructor
		public TestTPCHQ14_BM_SUBQ1() {
			super(-1);
			this.subqueryDDL = "(p_partkey INTEGER, p_type varchar(25))";
			this.subqueryDML = "select p_partkey,p_type  from <TPCH_DB_NAME>.part ;";
			
			this.unionDDL = "(p_partkey INTEGER, p_type varchar(25))";
			this.unionPreDML = "SELECT p_partkey, p_type FROM ";
		}

		// methods
		@Test
		public void test14_BM_SUBQ1() throws Exception {
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
