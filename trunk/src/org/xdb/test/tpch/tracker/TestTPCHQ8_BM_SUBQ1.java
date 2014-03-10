package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

public class TestTPCHQ8_BM_SUBQ1 extends DistributedTPCHTestCase {

	public TestTPCHQ8_BM_SUBQ1() {
		super(-1);  
		this.subqueryDDL = "(p_partkey INTEGER)";
		this.subqueryDML = "select p_partkey from <TPCH_DB_NAME>.part where p_type = " +
				           " 'STANDARD BRUSHED BRASS';";
	    this.unionDDL = "(p_partkey INTEGER)"; 
	    this.unionPreDML = "SELECT distinct p_partkey From ";
	} 
	
	// methods
		@Test
		public void testQ8_SUBQ1() throws Exception {
			// assign plan to query tracker
			QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
			QueryTrackerPlan qPlan = new QueryTrackerPlan();
			qPlan.assignTracker(qTracker);

			// create one Q8 operator per database node
			MySQLTrackerOperator[] Q8Ops = new MySQLTrackerOperator[this.numberOfSubops];
			createSubqueryOps(qPlan, Q8Ops);

			// create union operator to collect results from all compute nodes
			MySQLTrackerOperator Q8UnionOp = new MySQLTrackerOperator();
			createUnionOp(qPlan, Q8UnionOp);

			// connect operators
			connectOps(qPlan, Q8Ops, Q8UnionOp);

			// create deployment and execute plan
			Map<Identifier, OperatorDesc> deployment = this.createDeployment(Q8Ops,
					Q8UnionOp);
			executeQuery(qPlan, deployment, Q8UnionOp.getOperatorId(),
					getUnionOutTableName());
		}

	
}
