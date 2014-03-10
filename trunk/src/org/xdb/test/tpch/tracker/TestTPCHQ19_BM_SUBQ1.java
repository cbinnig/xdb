package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

public class TestTPCHQ19_BM_SUBQ1 extends DistributedTPCHTestCase { 
	
	// constructor
		public TestTPCHQ19_BM_SUBQ1() {
			super(-1);
			this.subqueryDDL = "(p_partkey INTEGER)";
			this.subqueryDML = "select p_partkey from <TPCH_DB_NAME>.part where p_brand = 'Brand#33'  " + 
					"		and p_container in ('SM CASE', 'SM BOX', 'SM PACK', 'SM PKG')  " +
					"  and p_size between 1 and 5 union  " + 
					" select p_partkey from <TPCH_DB_NAME>.part where p_brand = 'Brand#51'  " + 
					"		and p_container in ('MED BAG', 'MED BOX', 'MED PKG', 'MED PACK')  " + 
					"  and p_size between 1 and 10 union select p_partkey from <TPCH_DB_NAME>.part where p_brand = 'Brand#12'  " + 
					"		and p_container in ('LG CASE', 'LG BOX', 'LG PACK', 'LG PKG')  " + 
					"and p_size between 1 and 15; "; 
					
			this.unionDDL = "(p_partkey INTEGER)";
			this.unionPreDML = "SELECT distinct p_partkey FROM ";
			this.unionPostDML = ";";
		}

		// methods
		@Test
		public void testTPCHQ19_BM_SUBQ1() throws Exception {
			// assign plan to query tracker
			QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
			QueryTrackerPlan qPlan = new QueryTrackerPlan();
			qPlan.assignTracker(qTracker);

			// create one Q19 operator per database node
			MySQLTrackerOperator[] q19Ops = new MySQLTrackerOperator[this.numberOfSubops];
			createSubqueryOps(qPlan, q19Ops);

			// create union operator to collect results from all compute nodes
			MySQLTrackerOperator q19UnionOp = new MySQLTrackerOperator();
			createUnionOp(qPlan, q19UnionOp);

			// connect operators
			connectOps(qPlan, q19Ops, q19UnionOp);

			// create deployment and execute plan
			Map<Identifier, OperatorDesc> deployment = this.createDeployment(q19Ops,
					q19UnionOp);
			executeQuery(qPlan, deployment, q19UnionOp.getOperatorId(),
					getUnionOutTableName());
		}

}
