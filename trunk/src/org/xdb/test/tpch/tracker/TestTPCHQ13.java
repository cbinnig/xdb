package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q13 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ13 extends DistributedTPCHTestCase {

	// constructor
	public TestTPCHQ13() {
		super(-1);
		this.resultDDL = "(c_count INTEGER, custdist INTEGER)";
		this.subqueryDML = "SELECT c_count, " + 
				"       count(*) AS custdist " + 
				"FROM " + 
				"    ( SELECT c_custkey as custdist, " + 
				"             count(o_orderkey) as c_count" + 
				"     FROM <TPCH_DB_NAME>.customer " + 
				"     LEFT OUTER JOIN <TPCH_DB_NAME>.orders ON c_custkey = o_custkey " + 
				"     AND o_comment NOT LIKE '%unusual%accounts%' " + 
				"     GROUP BY c_custkey ) AS c_orders " + 
				"GROUP BY c_count;";
		this.unionPreDML = "SELECT c_count, sum(custdist) FROM ";
		this.unionPostDML = "group by c_count order by custdist DESC, c_count DESC";
	}

	// methods
	@Test
	public void testQ13() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one Q13 operator per database node
		MySQLTrackerOperator[] q13Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, q13Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q13UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q13UnionOp);

		// connect operators
		connectOps(qPlan, q13Ops, q13UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q13Ops,
				q13UnionOp);
		executeQuery(qPlan, deployment, q13UnionOp.getOperatorId(),
				getUnionOutTableName());
	}
}
