package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q10 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ10 extends DistributedTPCHTestCase {

	// constructor
	public TestTPCHQ10() {
		super(-1);
		this.resultDDL = "(c_custkey INTEGER, c_name CHAR(25), revenue DECIMAL(65,2), c_acctbal DECIMAL(65,2), n_name CHAR(25), c_address CHAR(40), c_phone CHAR(15), c_comment CHAR(117))";
		this.subqueryDML = "select" + 
				"	c_custkey," + 
				"	c_name," + 
				"	sum(l_extendedprice * (1 - l_discount)) as revenue," + 
				"	c_acctbal," + 
				"	n_name," + 
				"	c_address," + 
				"	c_phone," + 
				"	c_comment " + 
				"from" + 
				"	<TPCH_DB_NAME>.customer," + 
				"	<TPCH_DB_NAME>.orders," + 
				"	<TPCH_DB_NAME>.lineitem," + 
				"	<TPCH_DB_NAME>.nation " + 
				"where" + 
				"	c_custkey = o_custkey" + 
				"	and l_orderkey = o_orderkey" + 
				"	and o_orderdate >= date '1993-04-01'" + 
				"	and o_orderdate < date '1993-04-01' + interval '3' month" + 
				"	and l_returnflag = 'R'" + 
				"	and c_nationkey = n_nationkey " + 
				"group by" + 
				"	c_custkey," + 
				"	c_name," + 
				"	c_acctbal," + 
				"	c_phone," + 
				"	n_name," + 
				"	c_address," + 
				"	c_comment " + 
				"order by" + 
				"	revenue desc " +
				"limit 20;";
		this.unionPreDML = "SELECT c_custkey, c_name, sum(revenue), c_acctbal, n_name, c_address, c_phone, c_comment FROM ";
		this.unionPostDML = "group by c_custkey, c_name, c_acctbal, c_phone, n_name, c_address, c_comment limit 20";
	}

	// methods
	@Test
	public void testQ10() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one Q10 operator per database node
		MySQLTrackerOperator[] Q10Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, Q10Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator Q10UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, Q10UnionOp);

		// connect operators
		connectOps(qPlan, Q10Ops, Q10UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(Q10Ops,
				Q10UnionOp);
		executeQuery(qPlan, deployment, Q10UnionOp.getOperatorId(),
				getUnionOutTableName());
	}
}
