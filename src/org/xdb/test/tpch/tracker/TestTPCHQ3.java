package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q3 on multiple compute nodes
 * 
 * @author cbinnig
 * 
 */
public class TestTPCHQ3 extends DistributedTPCHTestCase {
	
	// constructor
	public TestTPCHQ3() {
		super(10 );
		this.subqueryDDL = "(l_orderkey INTEGER, revenue DECIMAL(65,2), o_orderdate DATE, o_shippriority INTEGER)";
		this.subqueryDML = "select l_orderkey, "
				+ "sum(l_extendedprice*(1-l_discount)) as revenue, "
				+ "o_orderdate, " + "o_shippriority " + "from <TPCH_DB_NAME>"
				+ ".customer, <TPCH_DB_NAME>.orders, <TPCH_DB_NAME>"
				+ ".lineitem " + "where c_mktsegment = 'BUILDING' and "
				+ "c_custkey = o_custkey and "
				+ "l_orderkey = o_orderkey and "
				+ "o_orderdate < date '1995-03-15' and "
				+ "l_shipdate > date '1995-03-15' "
				+ "group by l_orderkey, o_orderdate, o_shippriority "
				+ "order by revenue desc, o_orderdate "
				+ "limit 10;";
		
		this.unionDDL = "(l_orderkey INTEGER, revenue DECIMAL(65,2), o_orderdate DATE, o_shippriority INTEGER)";
		this.unionPreDML = "SELECT * FROM ";
		this.unionPostDML = "order by revenue desc, o_orderdate limit 10;";
	}

	// methods
	@Test
	public void testQ3() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one q3 operator per simulated database nodes
		MySQLTrackerOperator[] q3Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, q3Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q3UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q3UnionOp);

		// connect operators
		connectOps(qPlan, q3Ops, q3UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q3Ops,
				q3UnionOp);
		executeQuery(qPlan, deployment, q3UnionOp.getOperatorId(), getUnionOutTableName());
	}
}
