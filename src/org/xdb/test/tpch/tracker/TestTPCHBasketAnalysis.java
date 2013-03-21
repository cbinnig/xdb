package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Run a basket analysis
 * 
 * @author cbinnig
 * 
 */
public class TestTPCHBasketAnalysis extends DistributedTPCHTestCase {
	
	// constructor
	public TestTPCHBasketAnalysis() {
		super(10);
		this.resultDDL = "(p1_key INTEGER, p2_key INTEGER, p1_type VARCHAR(25), p2_type VARCHAR(25), frequency DECIMAL(65,2))";
		this.subqueryDML = "select l1.l_partkey as p1_key, l2.l_partkey as p2_key, " +
				"p1.p_type as p1_type, p2.p_type as p2_type, count(*) as frequency " +
				"from " +
				dbName + ".lineitem l1, " +
				dbName + ".lineitem l2, " +
				dbName + ".part p1, " +
				dbName + ".part p2 " +
				"where l1.l_orderkey = l2.l_orderkey " +
				"and l1.l_partkey = p1.p_partkey " +
				"and l2.l_partkey = p2.p_partkey " +
				"and l1.l_partkey != l2.l_partkey " +
				"group by l1.l_partkey, l2.l_partkey, p1.p_type, p2.p_type " +
				"having count(*)>2; ";
		
		this.unionPreDML = "SELECT p1_key, p2_key, p1_type, p2_type, count(*) as frequency FROM ";
		this.unionPostDML = "group by p1_key, p2_key, p1_type, p2_type order by frequency desc limit 10;";
	}

	// methods
	@Test
	public void testBasketAnalysis() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one operator per database node
		MySQLTrackerOperator[] subqueryOps = new MySQLTrackerOperator[NUMBER_COMPUTE_DBS];
		createSubqueryOps(qPlan, subqueryOps);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator unionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, unionOp);

		// connect operators
		connectOps(qPlan, subqueryOps, unionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(subqueryOps,
				unionOp);
		executeQuery(qPlan, deployment, unionOp.getOperatorId());
	}
}
