package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

public class TestTPCHQ17_BM_SUBQ2 extends DistributedTPCHTestCase {  
	
	// constructor
		public TestTPCHQ17_BM_SUBQ2() {
			super(-1);
			this.subqueryDDL = "(sum_qty  DECIMAL(65,2))";
			this.subqueryDML = "select sum(l_extendedprice) as sum_qty " + 
					"from " + 
					"	<TPCH_DB_NAME>.lineitem, " + 
					"	<TPCH_DB_NAME>.sub1_q17 " +
					"where " + 
					"	p_partkey = l_partkey " + 
					"	and l_quantity < avg_qty ";
			
			this.unionDDL = "(avg_yearly  DECIMAL(65,2))";
			this.unionPreDML = "SELECT sum(sum_qty)/7 FROM ";
			this.unionPostDML = "";
		}

		// methods
		@Test
		public void testTPCHQ17_BM_SUBQ2() throws Exception {
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
