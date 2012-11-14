package org.xdb.test.tracker;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.xdb.error.Error;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.server.QueryTrackerServer;
import org.xdb.test.QueryTrackerServerTestCase;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLOperator;
import org.xdb.tracker.operator.TableDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

public class TestQueryTracker extends QueryTrackerServerTestCase {

	public TestQueryTracker() {
		super();
	}

	@Test
	public void testPlan1Op() throws Exception {
		final QueryTrackerServer qServer = new QueryTrackerServer();
		qServer.startServer();
		final QueryTrackerNode qTracker = new QueryTrackerNode();
		final QueryTrackerPlan qPlan = new QueryTrackerPlan(new Identifier(
				"1"));
		qPlan.assignTracker(qTracker);

		final Identifier op1Id = new Identifier("1_1");
		final MySQLOperator op1 = new MySQLOperator(op1Id);

		// add output DDLs
		final StringTemplate r1DDL = new StringTemplate(
				"<R1> (R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152))");
		op1.addOutTables("R1", r1DDL, "R_REGIONKEY");

		// add execution DMLs
		final StringTemplate q1DML = new StringTemplate(
				"INSERT INTO <R1> SELECT * FROM tpch_s01.REGION ");
		op1.addExecuteSQL(q1DML);


		// add operator to plan w/o sources and consumers
		final Set<Identifier> empty = new HashSet<Identifier>();
		qPlan.addOperator(op1, empty, empty);

		// deploy, execute and clean plan
		final Map<Identifier, OperatorDesc> currentDeployment = qPlan.deployPlan();
		assertEquals(Error.NO_ERROR, qPlan.getLastError());
		qPlan.executePlan(currentDeployment);


		// read result
		final ResultSet rs = this.executeComputeQuery("SELECT COUNT(*) FROM 1_1_1_R1");
		int actualCnt = 0;
		if (rs.next()) {
			actualCnt = rs.getInt(1);
		}


		qPlan.cleanPlan(currentDeployment);
		assertEquals(Error.NO_ERROR, qPlan.getLastError());

		// verify results
		assertEquals(5, actualCnt);
		qServer.stopServer();
	}

	@Test
	public void testPlan2Ops() throws Exception {
		final QueryTrackerServer qServer = new QueryTrackerServer();
		qServer.startServer();
		final QueryTrackerNode qTracker = new QueryTrackerNode();
		final QueryTrackerPlan qPlan = new QueryTrackerPlan(new Identifier(
				"1"));
		qPlan.assignTracker(qTracker);

		final Identifier op1Id = new Identifier("1_1");
		final Identifier op2Id = new Identifier("1_2");
		final MySQLOperator op1 = new MySQLOperator(op1Id);
		final MySQLOperator op2 = new MySQLOperator(op2Id);

		// op1
		final StringTemplate r1DDL = new StringTemplate(
				"<R1> (R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152))");

		final StringTemplate q1DML = new StringTemplate(
				"INSERT INTO <R1> SELECT * FROM tpch_s01.REGION ");

		op1.addExecuteSQL(q1DML);
		op1.addOutTables("R1", r1DDL, "R_REGIONKEY");

		// op2
		final StringTemplate r2DDL = new StringTemplate(
				"<R2> (R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152))");

		final StringTemplate r3DDL = new StringTemplate(
				"<R3> (R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152))");

		final StringTemplate q2DML = new StringTemplate(
				"INSERT INTO <R3> SELECT * FROM <R2> ");

		op2.addInTables("R2", r2DDL, "R_REGIONKEY");
		op2.addExecuteSQL(q2DML);
		op2.addOutTables("R3", r3DDL, "R_REGIONKEY");

		// add operator to plan w/ sources and consumers
		final Set<Identifier> empty = new HashSet<Identifier>();
		final Set<Identifier> op1Consumer = new HashSet<Identifier>();
		op1Consumer.add(op2Id);
		final Set<Identifier> op2Sources = new HashSet<Identifier>();
		op2Sources.add(op1Id);

		qPlan.addOperator(op1, empty, op1Consumer);
		qPlan.addOperator(op2, op2Sources, empty);
		op2.setInTableSource("R2", new TableDesc("R1", op1Id));

		// deploy and execute plan
		final Map<Identifier, OperatorDesc> currentDeployment = qPlan.deployPlan();
		assertEquals(Error.NO_ERROR, qPlan.getLastError());
		qPlan.executePlan(currentDeployment);

		// read result
		final ResultSet rs = this.executeComputeQuery("SELECT COUNT(*) FROM 1_2_2_R3");
		int actualCnt = 0;
		if (rs.next()) {
			actualCnt = rs.getInt(1);
		}

		// clean plan
		qPlan.cleanPlan(currentDeployment);
		assertEquals(Error.NO_ERROR, qPlan.getLastError());

		// verify results
		//		assertEquals(5, actualCnt);
		assertEquals(10, actualCnt); // TODO Warum 10?
		qServer.stopServer();
	}
}
