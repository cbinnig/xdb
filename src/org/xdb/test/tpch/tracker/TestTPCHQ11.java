package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q11 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ11 extends DistributedTPCHTestCase {

	// constructor
	public TestTPCHQ11() {
		super(-1);
		this.resultDDL = "(ps_partkey INTEGER, value DECIMAL(65,2))";
		this.subqueryDML = "select " + 
				"	ps_partkey, " + 
				"	sum(ps_supplycost * ps_availqty) as value " + 
				"from " + 
				"	<TPCH_DB_NAME>.partsupp, " + 
				"	<TPCH_DB_NAME>.supplier, " + 
				"	<TPCH_DB_NAME>.nation " + 
				"where " + 
				"	ps_suppkey = s_suppkey " + 
				"	and s_nationkey = n_nationkey " + 
				"	and n_name = 'UNITED STATES' " + 
				"group by " + 
				"	ps_partkey having " + 
				"		sum(ps_supplycost * ps_availqty) > ( " + 
				"			select " + 
				"				sum(ps_supplycost * ps_availqty) * 0.0001000000 " + 
				"			from " + 
				"				<TPCH_DB_NAME>.partsupp, " + 
				"				<TPCH_DB_NAME>.supplier, " + 
				"				<TPCH_DB_NAME>.nation " + 
				"			where " + 
				"				ps_suppkey = s_suppkey " + 
				"				and s_nationkey = n_nationkey " + 
				"				and n_name = 'UNITED STATES' " + 
				"		) " + 
				"order by " + 
				"	value desc;";
		this.unionPreDML = "SELECT ps_partkey, sum(value) FROM ";
		this.unionPostDML = "group by ps_partkey order by value desc";
	}

	// methods
	@Test
	public void testQ11() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one Q11 operator per database node
		MySQLTrackerOperator[] Q11Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, Q11Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator Q11UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, Q11UnionOp);

		// connect operators
		connectOps(qPlan, Q11Ops, Q11UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(Q11Ops,
				Q11UnionOp);
		executeQuery(qPlan, deployment, Q11UnionOp.getOperatorId(),
				getUnionOutTableName());
	}
}
