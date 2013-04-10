package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q19 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ19 extends DistributedTPCHTestCase {

	// constructor
	public TestTPCHQ19() {
		super(-1);
		this.resultDDL = "(revenue DECIMAL(65,2))";
		this.subqueryDML = "select  " + 
				"	sum(l_extendedprice* (1 - l_discount)) as revenue  " + 
				"from  " + 
				"	<TPCH_DB_NAME>.lineitem,  " + 
				"	<TPCH_DB_NAME>.part  " + 
				"where  " + 
				"	(  " + 
				"		p_partkey = l_partkey  " + 
				"		and p_brand = 'Brand#33'  " + 
				"		and p_container in ('SM CASE', 'SM BOX', 'SM PACK', 'SM PKG')  " + 
				"		and l_quantity >= 3 and l_quantity <= 3 + 10  " + 
				"		and p_size between 1 and 5  " + 
				"		and l_shipmode in ('AIR', 'AIR REG')  " + 
				"		and l_shipinstruct = 'DELIVER IN PERSON'  " + 
				"	)  " + 
				"	or  " + 
				"	(  " + 
				"		p_partkey = l_partkey  " + 
				"		and p_brand = 'Brand#51'  " + 
				"		and p_container in ('MED BAG', 'MED BOX', 'MED PKG', 'MED PACK')  " + 
				"		and l_quantity >= 13 and l_quantity <= 13 + 10  " + 
				"		and p_size between 1 and 10  " + 
				"		and l_shipmode in ('AIR', 'AIR REG')  " + 
				"		and l_shipinstruct = 'DELIVER IN PERSON'  " + 
				"	)  " + 
				"	or  " + 
				"	(  " + 
				"		p_partkey = l_partkey  " + 
				"		and p_brand = 'Brand#12'  " + 
				"		and p_container in ('LG CASE', 'LG BOX', 'LG PACK', 'LG PKG')  " + 
				"		and l_quantity >= 28 and l_quantity <= 28 + 10  " + 
				"		and p_size between 1 and 15  " + 
				"		and l_shipmode in ('AIR', 'AIR REG')  " + 
				"		and l_shipinstruct = 'DELIVER IN PERSON'  " + 
				"	);";
		this.unionPreDML = "SELECT sum(revenue) FROM ";
		this.unionPostDML = ";";
	}

	// methods
	@Test
	public void testQ19() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one Q19 operator per database node
		MySQLTrackerOperator[] q19Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, q19Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q19UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q19UnionOp);

		// connect operators
		connectOps(qPlan, q19Ops, q19UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q19Ops,
				q19UnionOp);
		executeQuery(qPlan, deployment, q19UnionOp.getOperatorId(),
				getUnionOutTableName());
	}
}
