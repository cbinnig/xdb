package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q7 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ7 extends DistributedTPCHTestCase {

	// constructor
	public TestTPCHQ7() {
		super(-1);
		this.resultDDL = "(supp_nation CHAR(25), cust_nation CHAR(25), l_year INTEGER, revenue DECIMAL(65,2))";
		this.subqueryDML = "select " + 
				"	supp_nation, " + 
				"	cust_nation, " + 
				"	l_year, " + 
				"	sum(volume) as revenue " + 
				"from " + 
				"	( " + 
				"		select " + 
				"			n1.n_name as supp_nation, " + 
				"			n2.n_name as cust_nation, " + 
				"			extract(year from l_shipdate) as l_year, " + 
				"			l_extendedprice * (1 - l_discount) as volume " + 
				"		from " + 
				"			<TPCH_DB_NAME>.supplier, " + 
				"			<TPCH_DB_NAME>.lineitem, " + 
				"			<TPCH_DB_NAME>.orders, " + 
				"			<TPCH_DB_NAME>.customer, " + 
				"			<TPCH_DB_NAME>.nation n1, " + 
				"			<TPCH_DB_NAME>.nation n2 " + 
				"		where " + 
				"			s_suppkey = l_suppkey " + 
				"			and o_orderkey = l_orderkey " + 
				"			and c_custkey = o_custkey " + 
				"			and s_nationkey = n1.n_nationkey " + 
				"			and c_nationkey = n2.n_nationkey " + 
				"			and ( " + 
				"				(n1.n_name = 'MOZAMBIQUE' and n2.n_name = 'CANADA') " + 
				"				or (n1.n_name = 'CANADA' and n2.n_name = 'MOZAMBIQUE') " + 
				"			) " + 
				"			and l_shipdate between date '1995-01-01' and date '1996-12-31' " + 
				"	) as shipping " + 
				"group by " + 
				"	supp_nation, " + 
				"	cust_nation, " + 
				"	l_year " + 
				"order by " + 
				"	supp_nation, " + 
				"	cust_nation, " + 
				"	l_year;";
		this.unionPreDML = "SELECT supp_nation, cust_nation, l_year, SUM(revenue) FROM ";
		this.unionPostDML = "group by supp_nation, cust_nation, l_year order by supp_nation, cust_nation, l_year";
	}

	// methods
	@Test
	public void testQ7() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one Q7 operator per database node
		MySQLTrackerOperator[] Q7Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, Q7Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator Q7UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, Q7UnionOp);

		// connect operators
		connectOps(qPlan, Q7Ops, Q7UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(Q7Ops,
				Q7UnionOp);
		executeQuery(qPlan, deployment, Q7UnionOp.getOperatorId(),
				getUnionOutTableName());
	}
}
