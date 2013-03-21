package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q5 on multiple compute nodes
 * 
 * @author cbinnig
 * 
 */
public class TestTPCHQ5 extends DistributedTPCHTestCase {
	
	// constructor
	public TestTPCHQ5() {
		super(5);
		this.resultDDL = "(n_name CHAR(25), revenue DECIMAL(65,2))";
		this.subqueryDML = "select " + "n_name, "
				+ "sum(l_extendedprice * (1-l_discount)) as revenue "
				+ "from  " + dbName + ".customer, " + dbName + ".orders, "
				+ dbName + ".lineitem, " + dbName + ".supplier, " + dbName
				+ ".nation, " + dbName + ".region "
				+ "where c_custkey = o_custkey "
				+ "and l_orderkey = o_orderkey "
				+ "and l_suppkey = s_suppkey "
				+ "and c_nationkey = s_nationkey "
				+ "and s_nationkey = n_nationkey "
				+ "and r_regionkey = n_regionkey " + "and r_name = 'ASIA' "
				+ "and o_orderdate > DATE('1994-01-01')  "
				+ "and o_orderdate < DATE('1995-01-01') "
				+ "group by n_name;";
		this.unionPreDML = "SELECT n_name, SUM(revenue) FROM ";
		this.unionPostDML = "group by n_name;";
	}

	// methods
	@Test
	public void testQ5() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one q5 operator per database node
		MySQLTrackerOperator[] q5Ops = new MySQLTrackerOperator[NUMBER_COMPUTE_DBS];
		createSubqueryOps(qPlan, q5Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q5UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q5UnionOp);

		// connect operators
		connectOps(qPlan, q5Ops, q5UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q5Ops,
				q5UnionOp);
		executeQuery(qPlan, deployment, q5UnionOp.getOperatorId());
	}
}
