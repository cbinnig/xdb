package org.xdb.test.tracker;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.xdb.error.Error;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.test.QueryTrackerServerTestCase;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLOperator;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

public class TestQueryTracker extends QueryTrackerServerTestCase {

	public TestQueryTracker() {
		super();
	}

	@Test
	public void testPlan1() {
		QueryTrackerNode qTracker = new QueryTrackerNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan(qTracker,
				new Identifier("1"));

		MySQLOperator op1 = new MySQLOperator(1);

		//add output DDLs
		StringTemplate r1DDL = new StringTemplate(
				"<R1> (R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152))");
		op1.addOutTables("R1", r1DDL, "R_REGIONKEY");

		//add execution DMLs
		StringTemplate q1DML = new StringTemplate(
				"INSERT INTO <R1> SELECT * FROM tpch_s01.REGION ");
		op1.addExecuteSQL(q1DML);

		//add operator to plan w/o sources and consumers
		Set<Identifier> empty = new HashSet<Identifier>();
		qPlan.addOperator(op1, empty, empty);
		
		//deploy, execute and clean plan
		Map<Identifier, OperatorDesc> currentDeployment = qPlan.deployPlan();
		assertEquals(Error.NO_ERROR, qPlan.getLastError());
		
		qPlan.executePlan(currentDeployment);
		qPlan.cleanPlan(currentDeployment);
		assertEquals(Error.NO_ERROR, qPlan.getLastError());
	}
}
