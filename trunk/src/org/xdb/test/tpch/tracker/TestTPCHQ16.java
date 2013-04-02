package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q16 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ16 extends DistributedTPCHTestCase {

	// constructor
	public TestTPCHQ16() {
		super(-1);
		this.resultDDL = "(p_brand CHAR(10), p_type CHAR(25), p_size INTEGER, supplier_cnt INTEGER)";
		this.subqueryDML = "SELECT p_brand, p_type, p_size, " + 
				"       count(DISTINCT ps_suppkey) AS supplier_cnt " + 
				"FROM <TPCH_DB_NAME>.partsupp, " + 
				"     <TPCH_DB_NAME>.part " + 
				"WHERE p_partkey = ps_partkey " + 
				"    AND p_brand <> 'Brand#32' " + 
				"    AND p_type NOT LIKE 'LARGE PLATED%' " + 
				"    AND p_size IN (20, 32, 31, 13, 15, 30, 10, 5) " + 
				"    AND ps_suppkey NOT IN " + 
				"        ( SELECT s_suppkey " + 
				"         FROM <TPCH_DB_NAME>.supplier " + 
				"         WHERE s_comment LIKE '%Customer%Complaints%' ) " + 
				"GROUP BY p_brand, p_type, p_size " + 
				"ORDER BY supplier_cnt DESC, p_brand, p_type, p_size;";
		this.unionPreDML = "SELECT p_brand, p_type, p_size, sum(supplier_cnt) FROM ";
		this.unionPostDML = "GROUP BY p_brand, p_type, p_size ORDER BY supplier_cnt DESC, p_brand, p_type, p_size";
	}

	// methods
	@Test
	public void testQ16() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one Q16 operator per database node
		MySQLTrackerOperator[] q16Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, q16Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q16UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q16UnionOp);

		// connect operators
		connectOps(qPlan, q16Ops, q16UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q16Ops,
				q16UnionOp);
		executeQuery(qPlan, deployment, q16UnionOp.getOperatorId(),
				getUnionOutTableName());
	}
}
