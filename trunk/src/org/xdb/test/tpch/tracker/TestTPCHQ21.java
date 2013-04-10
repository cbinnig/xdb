package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q21 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ21 extends DistributedTPCHTestCase {

	// constructor
	public TestTPCHQ21() {
		super(100);
		this.resultDDL = "(s_name CHAR(25), numwait INTEGER)";
		this.subqueryDML = "select s_name, count(*) as numwait " + 
				"from " + 
				"	<TPCH_DB_NAME>.supplier, " + 
				"	<TPCH_DB_NAME>.lineitem l1, " + 
				"	<TPCH_DB_NAME>.orders, " + 
				"	<TPCH_DB_NAME>.nation " + 
				"where " + 
				"	s_suppkey = l1.l_suppkey " + 
				"	and o_orderkey = l1.l_orderkey " + 
				"	and o_orderstatus = 'F' " + 
				"	and l1.l_receiptdate > l1.l_commitdate " + 
				"	and exists ( " + 
				"		select * from " + 
				"			<TPCH_DB_NAME>.lineitem l2 " + 
				"		where l2.l_orderkey = l1.l_orderkey " + 
				"		  and l2.l_suppkey <> l1.l_suppkey " + 
				"	) " + 
				"	and not exists ( " + 
				"		select * from " + 
				"			<TPCH_DB_NAME>.lineitem l3 " + 
				"		where l3.l_orderkey = l1.l_orderkey " + 
				"		  and l3.l_suppkey <> l1.l_suppkey " + 
				"		  and l3.l_receiptdate > l3.l_commitdate " + 
				"	) " + 
				"	and s_nationkey = n_nationkey " + 
				"	and n_name = 'CANADA' " + 
				"group by s_name " + 
				"order by numwait desc, s_name LIMIT 100;";
		this.unionPreDML = "SELECT s_name, count(*) as numwait FROM ";
		this.unionPostDML = "GROUP BY s_name ORDER BY numwait desc, s_name LIMIT 100;";
	}

	// methods
	@Test
	public void testQ21() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one Q21 operator per database node
		MySQLTrackerOperator[] q21Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, q21Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q21UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q21UnionOp);

		// connect operators
		connectOps(qPlan, q21Ops, q21UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q21Ops,
				q21UnionOp);
		executeQuery(qPlan, deployment, q21UnionOp.getOperatorId(),
				getUnionOutTableName());
	}
}
