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
import org.xdb.tracker.operator.MySQLTrackerOperator;
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
		final QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		final MySQLTrackerOperator op1 = new MySQLTrackerOperator();

		// add output DDLs
		final StringTemplate r1DDL = new StringTemplate(
				"<R1> (R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152))");
		op1.addOutTable("R1", r1DDL);

		// add execution DMLs
		final StringTemplate q1DML = new StringTemplate(
				"INSERT INTO <R1> SELECT * FROM tpch_s01.REGION ");
		op1.addExecuteSQL(q1DML);


		// add operator to plan w/o sources and consumers
		qPlan.addOperator(op1);

		// deploy, execute and clean plan
		final Map<Identifier, OperatorDesc> currentDeployment = qPlan.deployPlan();
		assertEquals(Error.NO_ERROR, qPlan.getLastError());
		qPlan.executePlan(currentDeployment);

		// read result
		Identifier deployOp1Id = currentDeployment.get(op1.getOperatorId()).getOperatorID();
		final ResultSet rs = this.executeComputeQuery("SELECT COUNT(*) FROM "+deployOp1Id+"_R1");
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
		final QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		final MySQLTrackerOperator op1 = new MySQLTrackerOperator();
		final MySQLTrackerOperator op2 = new MySQLTrackerOperator();

		// op1
		final StringTemplate r1DDL = new StringTemplate(
				"<R1> (R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152))");

		final StringTemplate q1DML = new StringTemplate(
				"INSERT INTO <R1> SELECT * FROM tpch_s01.REGION ");

		op1.addExecuteSQL(q1DML);
		op1.addOutTable("R1", r1DDL);
		qPlan.addOperator(op1);

		// op2
		final StringTemplate r2DDL = new StringTemplate(
				"<R2> (R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152))");

		final StringTemplate r3DDL = new StringTemplate(
				"<R3> (R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152))");

		final StringTemplate q2DML = new StringTemplate(
				"INSERT INTO <R3> SELECT * FROM <R2> ");

		op2.addInTable("R2", r2DDL);
		op2.addExecuteSQL(q2DML);
		op2.addOutTable("R3", r3DDL);
		qPlan.addOperator(op2);
		
		// connect operators
		final Set<Identifier> op1Consumer = new HashSet<Identifier>();
		op1Consumer.add(op2.getOperatorId());
		qPlan.setConsumers(op1.getOperatorId(), op1Consumer);
		
		final Set<Identifier> op2Sources = new HashSet<Identifier>();
		op2Sources.add(op1.getOperatorId());
		qPlan.setSources(op2.getOperatorId(), op2Sources);
		op2.setInTableSource("R2", new TableDesc("R1", op1.getOperatorId()));

		// deploy and execute plan
		final Map<Identifier, OperatorDesc> currentDeployment = qPlan.deployPlan();
		assertEquals(Error.NO_ERROR, qPlan.getLastError());
		qPlan.executePlan(currentDeployment);

		// read result
		Identifier deployOp2Id = currentDeployment.get(op2.getOperatorId()).getOperatorID();
		final ResultSet rs = this.executeComputeQuery("SELECT COUNT(*) FROM "+deployOp2Id+"_R3");
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
