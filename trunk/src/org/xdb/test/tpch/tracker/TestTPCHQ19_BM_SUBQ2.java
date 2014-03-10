package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

public class TestTPCHQ19_BM_SUBQ2 extends DistributedTPCHTestCase {
	// constructor
		public TestTPCHQ19_BM_SUBQ2() {
			super(-1);
			this.subqueryDDL = "(revenue DECIMAL(65,2))";
			this.subqueryDML = "select  " + 
					"	sum(l_extendedprice* (1 - l_discount)) as revenue  " + 
					"from  " + 
					"	<TPCH_DB_NAME>.lineitem,  " + 
					"	<TPCH_DB_NAME>.sub1_q19  " + 
					"where  " + 
					"	(  " + 
					"		p_partkey = l_partkey  " + 
					"		and l_quantity >= 3 and l_quantity <= 3 + 10  " + 
					"		and l_shipmode in ('AIR', 'AIR REG')  " + 
					"		and l_shipinstruct = 'DELIVER IN PERSON'  " + 
					"	)  " + 
					"	or  " + 
					"	(  " + 
					"		p_partkey = l_partkey  " + 
					"		and l_quantity >= 13 and l_quantity <= 13 + 10  " + 
					"		and l_shipmode in ('AIR', 'AIR REG')  " + 
					"		and l_shipinstruct = 'DELIVER IN PERSON'  " + 
					"	)  " + 
					"	or  " + 
					"	(  " + 
					"		p_partkey = l_partkey  " + 
					"		and l_quantity >= 28 and l_quantity <= 28 + 10  " + 
					"		and l_shipmode in ('AIR', 'AIR REG')  " + 
					"		and l_shipinstruct = 'DELIVER IN PERSON'  " + 
					"	);";
			
			this.unionDDL = "(revenue DECIMAL(65,2))";
			this.unionPreDML = "SELECT sum(revenue) FROM ";
			this.unionPostDML = ";";
		}

		// methods
		@Test
		public void testTPCHQ19_BM_SUBQ2() throws Exception {
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
