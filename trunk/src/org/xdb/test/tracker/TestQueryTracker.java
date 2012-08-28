package org.xdb.test.tracker;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.test.QueryTrackerServerTestCase;
import org.xdb.tracker.ExecutionPlan;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.operator.MySQLOperator;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

public class TestQueryTracker extends QueryTrackerServerTestCase {

	public TestQueryTracker() {
		super();
	}
	
	@Test
	public void testPlan1(){
		QueryTrackerNode qTracker = new QueryTrackerNode();
		ExecutionPlan execPlan = new ExecutionPlan(qTracker,
				new Identifier("1"));

		MySQLOperator op1 = new MySQLOperator(1);

		StringTemplate r1DDL = new StringTemplate(
				"<R1> (R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152))");
		op1.addOutTables("R1", r1DDL, "R_REGIONKEY");

		StringTemplate q1DML = new StringTemplate(
				"INSERT INTO <R1> SELECT * FROM tpch_s01.REGION ");
		op1.addExecuteSQL(q1DML);

		Set<Identifier> empty = new HashSet<Identifier>();
		execPlan.addOperator(op1, empty, empty);
		Map<Identifier, OperatorDesc> currentDeployment = execPlan.deployPlan();
		execPlan.executePlan(currentDeployment);
	}
}
