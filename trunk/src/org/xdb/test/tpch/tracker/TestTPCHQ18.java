package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q18 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ18 extends DistributedTPCHTestCase {

	// constructor
	public TestTPCHQ18() {
		super(100);
		this.subqueryDDL = "(c_name CHAR(25), c_custkey INTEGER, o_orderkey INTEGER, o_orderdate DATE, o_totalprice DECIMAL(15,2), quantity INTEGER)";
		this.subqueryDML = "SELECT c_name, " + 
				"       c_custkey, " + 
				"       o_orderkey, " + 
				"       o_orderdate, " + 
				"       o_totalprice, " + 
				"       sum(l_quantity) as quantity " + 
				"FROM <TPCH_DB_NAME>.customer, " + 
				"     <TPCH_DB_NAME>.orders, " + 
				"     <TPCH_DB_NAME>.lineitem " + 
				"WHERE o_orderkey IN " + 
				"        ( SELECT l_orderkey " + 
				"         FROM <TPCH_DB_NAME>.lineitem " + 
				"         GROUP BY l_orderkey HAVING sum(l_quantity) > 312 ) " + 
				"    AND c_custkey = o_custkey " + 
				"    AND o_orderkey = l_orderkey " + 
				"GROUP BY c_name, " + 
				"         c_custkey, " +
				
				"         o_orderkey, " + 
				"         o_orderdate, " + 
				"         o_totalprice " + 
				"ORDER BY o_totalprice DESC, o_orderdate " +
				"limit 100;";
		
		this.unionDDL = "(c_name CHAR(25), c_custkey INTEGER, o_orderkey INTEGER, o_orderdate DATE, o_totalprice DECIMAL(15,2), quantity INTEGER)";
		this.unionPreDML = "SELECT c_name, c_custkey, o_orderkey, o_orderdate, o_totalprice, sum(quantity) FROM ";
		this.unionPostDML = "GROUP BY c_name, c_custkey, o_orderkey, o_orderdate, o_totalprice ORDER BY o_totalprice DESC, o_orderdate LIMIT 100;";
	}

	// methods
	@Test
	public void testQ18() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one Q18 operator per database node
		MySQLTrackerOperator[] q18Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, q18Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q18UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q18UnionOp);

		// connect operators
		connectOps(qPlan, q18Ops, q18UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q18Ops,
				q18UnionOp);
		executeQuery(qPlan, deployment, q18UnionOp.getOperatorId(),
				getUnionOutTableName());
	}
}
