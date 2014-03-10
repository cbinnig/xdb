package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

public class TestTPCHQ20_BM_SUBQ2 extends DistributedTPCHTestCase {


	// constructor
	public TestTPCHQ20_BM_SUBQ2() {
		super(-1);
		this.subqueryDDL = "(s_name CHAR(25), s_address CHAR(40))";
		this.subqueryDML = "select  s_name,	s_address " +
				"from 	<TPCH_DB_NAME>.supplier, <TPCH_DB_NAME>.nation " +
				"where 	s_suppkey in ( " +
				" 		    select ps_suppkey " +
				" 		    from <TPCH_DB_NAME>.partsupp, <TPCH_DB_NAME>.sub1_q20" +
				"		    where ps_partkey in " +
				"			(" +
				" 				       	select p_partkey " +
				"						from <TPCH_DB_NAME>.part " +
				" 				       	where p_name like 'sienna%' " +
				"		    )" +
				"		    and ps_availqty > totalqty " +
				"		    and l_partkey = ps_partkey " +
				"	 	    and l_suppkey = ps_suppkey) " +
				"and s_nationkey = n_nationkey 	" +
				"and n_name = 'ETHIOPIA'";
		
		this.unionDDL = "(s_name CHAR(25), s_address CHAR(40))";
		this.unionPreDML = "SELECT distinct s_name,	s_address FROM ";
		this.unionPostDML = "order by s_name;";
	}

	// methods
	@Test
	public void testTPCHQ20_BM_SUBQ2() throws Exception {
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
