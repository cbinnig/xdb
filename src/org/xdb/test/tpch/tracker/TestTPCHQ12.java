package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q12 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ12 extends DistributedTPCHTestCase {

	// constructor
	public TestTPCHQ12() {
		super(-1);
		this.resultDDL = "(l_shipmode CHAR(10), high_line_count DECIMAL(65,2), low_line_count DECIMAL(65,2))";
		this.subqueryDML = "SELECT l_shipmode, " + 
				"       sum(CASE WHEN o_orderpriority = '1-URGENT' " + 
				"           OR o_orderpriority = '2-HIGH' THEN 1 ELSE 0 END) AS high_line_count, " + 
				"       sum(CASE WHEN o_orderpriority <> '1-URGENT' " + 
				"           AND o_orderpriority <> '2-HIGH' THEN 1 ELSE 0 END) AS low_line_count " + 
				"FROM <TPCH_DB_NAME>.orders, " + 
				"     <TPCH_DB_NAME>.lineitem " + 
				"WHERE o_orderkey = l_orderkey " + 
				"    AND l_shipmode IN ('AIR', " + 
				"                       'FOB') " + 
				"    AND l_commitdate < l_receiptdate " + 
				"    AND l_shipdate < l_commitdate " + 
				"    AND l_receiptdate >= '1994-01-01' " + 
				"    AND l_receiptdate < '1995-01-01' " + 
				"GROUP BY l_shipmode " + 
				"ORDER BY l_shipmode;";
		this.unionPreDML = "SELECT l_shipmode, sum(high_line_count), sum(low_line_count) FROM ";
		this.unionPostDML = "group by l_shipmode order by l_shipmode";
	}

	// methods
	@Test
	public void testQ12() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one Q12 operator per database node
		MySQLTrackerOperator[] Q12Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, Q12Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator Q12UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, Q12UnionOp);

		// connect operators
		connectOps(qPlan, Q12Ops, Q12UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(Q12Ops,
				Q12UnionOp);
		executeQuery(qPlan, deployment, Q12UnionOp.getOperatorId(),
				getUnionOutTableName());
	}
}
