package org.xdb.test.tpch.tracker;

import java.util.Map;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Tests Q22 on multiple compute nodes
 * 
 * @author slisting
 * 
 */
public class TestTPCHQ22 extends DistributedTPCHTestCase {

	// constructor
	public TestTPCHQ22() {
		super(-1);
		this.subqueryDDL = "(cntrycode CHAR(2), numcust INTEGER, totacctbal DECIMAL(65,2))";
		this.subqueryDML = "select " + 
				"	cntrycode, count(*) as numcust,	sum(c_acctbal) as totacctbal " + 
				"from ( select substring(c_phone from 1 for 2) as cntrycode, c_acctbal " + 
				"		from <TPCH_DB_NAME>.customer " + 
				"		where substring(c_phone from 1 for 2) in " + 
				"				('41', '26', '31', '34', '43', '32', '24') " + 
				"			and c_acctbal > ( " + 
				"				select avg(c_acctbal) from <TPCH_DB_NAME>.customer " + 
				"				where c_acctbal > 0.00 " + 
				"					and substring(c_phone from 1 for 2) in " + 
				"						('41', '26', '31', '34', '43', '32', '24') " + 
				"			) and not exists ( " + 
				"				select * from <TPCH_DB_NAME>.orders " + 
				"				where o_custkey = c_custkey " + 
				"			) " + 
				"	) as custsale " + 
				"group by cntrycode order by cntrycode;";
		
		this.unionDDL = "(cntrycode CHAR(2), numcust INTEGER, totacctbal DECIMAL(65,2))";
		this.unionPreDML = "SELECT cntrycode, sum(numcust) as numcust, sum(totacctbal) as totacctbal FROM ";
		this.unionPostDML = "GROUP BY cntrycode ORDER BY cntrycode;";
	}

	// methods
	@Test
	public void testQ22() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one Q22 operator per database node
		MySQLTrackerOperator[] q22Ops = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, q22Ops);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator q22UnionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, q22UnionOp);

		// connect operators
		connectOps(qPlan, q22Ops, q22UnionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(q22Ops,
				q22UnionOp);
		executeQuery(qPlan, deployment, q22UnionOp.getOperatorId(),
				getUnionOutTableName());
	}
}
