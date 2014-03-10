package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

public class TestTPCHQ8_BM_SUBQ2 extends DistributedTPCHTestCase { 
	

	// constructor
	public TestTPCHQ8_BM_SUBQ2() {
		super(-1);
		this.subqueryDDL = "(o_year INTEGER, mkt_share DECIMAL(65,14), volume DECIMAL(65,6))";
		this.subqueryDML = "select " + 
				"	o_year, " + 
				"	sum(case " + 
				"		when nation = 'IRAN' then volume " + 
				"		else 0 " + 
				"	end) as mkt_share, sum(volume) as volume " + 
				"from " + 
				"	( " + 
				"		select " + 
				"			extract(year from o_orderdate) as o_year, " + 
				"			l_extendedprice * (1 - l_discount) as volume, " + 
				"			n2.n_name as nation " + 
				"		from " + 
				"			<TPCH_DB_NAME>.sub1_q8, " + 
				"			<TPCH_DB_NAME>.supplier, " + 
				"			<TPCH_DB_NAME>.lineitem, " + 
				"			<TPCH_DB_NAME>.orders, " + 
				"			<TPCH_DB_NAME>.customer, " + 
				"			<TPCH_DB_NAME>.nation n1, " + 
				"			<TPCH_DB_NAME>.nation n2, " + 
				"			<TPCH_DB_NAME>.region " + 
				"		where " + 
				"			p_partkey = l_partkey " + 
				"			and s_suppkey = l_suppkey " + 
				"			and l_orderkey = o_orderkey " + 
				"			and o_custkey = c_custkey " + 
				"			and c_nationkey = n1.n_nationkey " + 
				"			and n1.n_regionkey = r_regionkey " + 
				"			and r_name = 'MIDDLE EAST' " + 
				"			and s_nationkey = n2.n_nationkey " + 
				"			and o_orderdate between date '1995-01-01' and date '1996-12-31' " + 
				"	) as all_nations " + 
				"group by o_year " + 
				"order by o_year;";
		
		this.unionDDL = "(o_year INTEGER, mkt_share DECIMAL(65,14))";
		this.unionPreDML = "SELECT o_year, sum(mkt_share) / sum(volume) as mkt_share FROM ";
		this.unionPostDML = "group by o_year order by o_year";
	}

	// methods
	@Test
	public void testQ8() throws Exception {
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
