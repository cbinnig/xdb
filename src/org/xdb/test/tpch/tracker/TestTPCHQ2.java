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
public class TestTPCHQ2 extends DistributedTPCHTestCase {
	
	// constructor
	public TestTPCHQ2() {
		super(-1);

		this.resultDDL = "(s_acctbal DECIMAL(65,2), s_name CHAR(25), n_name CHAR(25), p_partkey INTEGER, p_mfgr CHAR(25), s_address VARCHAR(40), " +
		 "s_phone CHAR(15), s_comment VARCHAR(101))";
		this.subqueryDML = "select" + 
				"	s_acctbal," + 
				"	s_name," + 
				"	n_name," + 
				"	p_partkey," + 
				"	p_mfgr," + 
				"	s_address," + 
				"	s_phone," + 
				"	s_comment " + 
				"from " + 
				dbName +".part," + 
				dbName +".supplier," + 
				dbName +".partsupp," + 
				dbName +".nation," + 
				dbName +".region " + 
				"where" + 
				"	p_partkey = ps_partkey" + 
				"	and s_suppkey = ps_suppkey" + 
				"	and p_size = 27" + 
				"	and p_type like '%TIN'" + 
				"	and s_nationkey = n_nationkey" + 
				"	and n_regionkey = r_regionkey" + 
				"	and r_name = 'AFRICA'" + 
				"	and ps_supplycost = (" + 
				"		select" + 
				"			min(ps_supplycost)" + 
				"		from " + 
				dbName +".partsupp," + 
				dbName +".supplier," + 
				dbName +".nation," + 
				dbName +".region" + 
				"		where " + 
				"			p_partkey = ps_partkey" + 
				"			and s_suppkey = ps_suppkey" + 
				"			and s_nationkey = n_nationkey" + 
				"			and n_regionkey = r_regionkey" + 
				"			and r_name = 'AFRICA'" + 
				"	) limit 100" ;
		
		this.unionPreDML = "SELECT DISTINCT * FROM ";
		this.unionPostDML = "order by s_acctbal desc, n_name, s_name, p_partkey limit 100;";
	}

	// methods
	@Test
	public void testQ2() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one q1 operator per simulated database nodes
		MySQLTrackerOperator[] q2Ops = new MySQLTrackerOperator[NUMBER_COMPUTE_DBS];
		createSubqueryOps(qPlan, q2Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q2UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q2UnionOp);

		// connect operators
		connectOps(qPlan, q2Ops, q2UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q2Ops,
				q2UnionOp);
		executeQuery(qPlan, deployment, q2UnionOp.getOperatorId(), getUnionOutTableName());
	}
}
