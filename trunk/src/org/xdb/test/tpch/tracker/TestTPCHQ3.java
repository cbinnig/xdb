package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q3 on NUMBER_COMPUTE_DBS TPC-H database instances using
 * NUMBER_COMPUTE_DBS+1 compute nodes
 * 
 * - NUMBER_COMPUTE_DBS compute nodes are executing the local sub-queries - 1
 * query is computing the union over all intermediate results
 * 
 * if RUN_LOCAL==true all compute nodes are started on local machine else no
 * compute nodes are started automatically (i.e., must be done manually)
 * 
 * @author cbinnig
 * 
 */
public class TestTPCHQ3 extends DistributedTPCHTestCase {
	
	// constructor
	public TestTPCHQ3() {
		super(10 );
		this.resultDDL = "(l_orderkey INTEGER, revenue DECIMAL(65,2), o_orderdate DATE, o_shippriority INTEGER)";
		this.subqueryDML = "select l_orderkey, "
				+ "sum(l_extendedprice*(1-l_discount)) as revenue, "
				+ "o_orderdate, " + "o_shippriority " + "from " + dbName
				+ ".customer, " + dbName + ".orders, " + dbName
				+ ".lineitem " + "where c_mktsegment = 'BUILDING' and "
				+ "c_custkey = o_custkey and "
				+ "l_orderkey = o_orderkey and "
				+ "o_orderdate < date '1995-03-15' and "
				+ "l_shipdate > date '1995-03-15' "
				+ "group by l_orderkey, o_orderdate, o_shippriority "
				+ "order by revenue desc, o_orderdate "
				+ "limit 10;";
		
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
		MySQLTrackerOperator[] q3Ops = new MySQLTrackerOperator[NUMBER_COMPUTE_DBS];
		createSubqueryOps(qPlan, q3Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q3UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q3UnionOp);

		// connect operators
		connectOps(qPlan, q3Ops, q3UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q3Ops,
				q3UnionOp);
		executeQuery(qPlan, deployment, q3UnionOp.getOperatorId());
	}
}
