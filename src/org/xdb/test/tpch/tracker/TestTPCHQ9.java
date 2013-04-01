package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q9 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ9 extends DistributedTPCHTestCase {

	// constructor
	public TestTPCHQ9() {
		super(-1);
		this.resultDDL = "(nation CHAR(25), o_year INTEGER, sum_profit DECIMAL(65,2))";
		this.subqueryDML = "select " + 
				"	nation, " + 
				"	o_year, " + 
				"	sum(amount) as sum_profit " + 
				"from " + 
				"	( " + 
				"		select " + 
				"			n_name as nation, " + 
				"			extract(year from o_orderdate) as o_year, " + 
				"			l_extendedprice * (1 - l_discount) - ps_supplycost * l_quantity as amount " + 
				"		from " + 
				"			<TPCH_DB_NAME>.part, " + 
				"			<TPCH_DB_NAME>.supplier, " + 
				"			<TPCH_DB_NAME>.lineitem, " + 
				"			<TPCH_DB_NAME>.partsupp, " + 
				"			<TPCH_DB_NAME>.orders, " + 
				"			<TPCH_DB_NAME>.nation " + 
				"		where " + 
				"			s_suppkey = l_suppkey " + 
				"			and ps_suppkey = l_suppkey " + 
				"			and ps_partkey = l_partkey " + 
				"			and p_partkey = l_partkey " + 
				"			and o_orderkey = l_orderkey " + 
				"			and s_nationkey = n_nationkey " + 
				"			and p_name like '%lawn%' " + 
				"	) as profit " + 
				"group by " + 
				"	nation, " + 
				"	o_year " + 
				"order by " + 
				"	nation, " + 
				"	o_year desc;";
		this.unionPreDML = "SELECT nation, o_year, sum(sum_profit) FROM ";
		this.unionPostDML = "group by nation,o_year order by nation, o_year desc";
	}

	// methods
	@Test
	public void testQ9() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one Q9 operator per database node
		MySQLTrackerOperator[] Q9Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, Q9Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator Q9UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, Q9UnionOp);

		// connect operators
		connectOps(qPlan, Q9Ops, Q9UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(Q9Ops,
				Q9UnionOp);
		executeQuery(qPlan, deployment, Q9UnionOp.getOperatorId(),
				getUnionOutTableName());
	}
}
