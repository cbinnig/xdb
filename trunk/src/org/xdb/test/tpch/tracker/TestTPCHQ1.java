package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q1 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ1 extends DistributedTPCHTestCase {
	
	// constructor
	public TestTPCHQ1() {
		super(-1);

		this.resultDDL = "(l_returnflag CHAR, l_linestatus CHAR, sum_qty DECIMAL(65,2), sum_base_price DECIMAL(65,2), sum_disc_price DECIMAL(65,2), sum_charge DECIMAL(65,2), " +
		 "avg_qty DECIMAL(65,2), avg_price DECIMAL(65,2), avg_disc DECIMAL(65,2), count_order INTEGER)";
		this.subqueryDML = "select " + 
				"	l_returnflag, " + 
				"	l_linestatus, " + 
				"	sum(l_quantity) as sum_qty, " + 
				"	sum(l_extendedprice) as sum_base_price," + 
				"	sum(l_extendedprice * (1 - l_discount)) as sum_disc_price, " + 
				"	sum(l_extendedprice * (1 - l_discount) * (1 + l_tax)) as sum_charge, " + 
				"	avg(l_quantity) as avg_qty, " + 
				"	avg(l_extendedprice) as avg_price, " + 
				"	avg(l_discount) as avg_disc, " + 
				"	count(*) as count_order " + 
				"from "  + dbName + 
				".lineitem " + 
				"where " + 
				"	l_shipdate <= '1998-12-01' - interval 119 day " + 
				"group by " + 
				"	l_returnflag, " + 
				"	l_linestatus ";
		
		this.unionPreDML = "SELECT l_returnflag, l_linestatus, sum(sum_qty), sum(sum_base_price), sum(sum_disc_price), sum(sum_charge), avg(avg_qty), avg(avg_price), avg(avg_disc), sum(count_order) FROM ";
		this.unionPostDML = "group by l_returnflag, l_linestatus order by l_returnflag, l_linestatus;";
	}

	// methods
	@Test
	public void testQ1() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one q1 operator per simulated database nodes
		MySQLTrackerOperator[] q1Ops = new MySQLTrackerOperator[NUMBER_COMPUTE_DBS];
		createSubqueryOps(qPlan, q1Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q1UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q1UnionOp);

		// connect operators
		connectOps(qPlan, q1Ops, q1UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q1Ops,
				q1UnionOp);
		executeQuery(qPlan, deployment, q1UnionOp.getOperatorId(), getUnionOutTableName());
	}
}
