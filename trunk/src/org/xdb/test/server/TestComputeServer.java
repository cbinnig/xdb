package org.xdb.test.server;

import java.sql.ResultSet;

import org.junit.Test;
import org.xdb.Config;
import org.xdb.client.ComputeClient;
import org.xdb.execute.operators.MySQLExecuteOperator;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.test.ComputeServerTestCase;
import org.xdb.utils.Identifier;

public class TestComputeServer extends ComputeServerTestCase {
	//private String[] nodes = {"192.168.178.42", "192.168.178.20"};
	private final String[] nodes = {"127.0.0.1", "127.0.0.1"};

	public TestComputeServer() {
		super();
	}

	@Test
	public void test1Node() throws Exception {
		Config.COMPUTE_SIGNAL2QUERY_TRACKER = false;

		// operator
		final MySQLExecuteOperator op = new MySQLExecuteOperator(new Identifier("1"));
		op.addOpenSQL("CREATE TEMPORARY TABLE REGION ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) "
				+ "ENGINE=FEDERATED CONNECTION='mysql://"+Config.COMPUTE_DB_USER+":"+Config.COMPUTE_DB_PASSWD+"@"+nodes[0]+"/tpch_s01/REGION';");

		op.addOpenSQL("CREATE TABLE R1 ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) ENGINE=MEMORY;");
		op.addExecuteSQL("INSERT INTO R1 SELECT * FROM REGION WHERE R_NAME LIKE 'A%';");
		op.addCloseSQL("DROP TABLE R1");

		// run plan with client
		final ComputeClient client = new ComputeClient();

		assertNoError(client.openOperator(nodes[0], op));
		assertNoError(client.executeOperator(nodes[0], op.getOperatorId()));

		// check results
		final ResultSet rs = this.executeComputeQuery("SELECT COUNT(*) AS CNT FROM R1;");
		assertResultSize(rs, 3);

		// clean up
		assertNoError(client.closeOperator(nodes[0], op.getOperatorId()));

		Config.COMPUTE_SIGNAL2QUERY_TRACKER = true;
	}


	@Test
	public void test2Nodes() throws Exception {
		Config.COMPUTE_SIGNAL2QUERY_TRACKER = false;

		// first op
		final MySQLExecuteOperator op1 = new MySQLExecuteOperator(new Identifier("1"));

		op1.addOpenSQL("CREATE TEMPORARY TABLE REGION ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) "
				+ "ENGINE=FEDERATED CONNECTION='mysql://"+Config.COMPUTE_DB_USER+":"+Config.COMPUTE_DB_PASSWD+"@"+nodes[0]+"/tpch_s01/REGION';");
		op1.addOpenSQL("CREATE TABLE R1 ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) ENGINE=MEMORY;");
		op1.addExecuteSQL("INSERT INTO R1 SELECT * FROM REGION WHERE R_NAME LIKE 'A%';");
		op1.addCloseSQL("DROP TABLE R1");


		// second op
		final MySQLExecuteOperator op2 = new MySQLExecuteOperator(new Identifier("2"));

		op2.addOpenSQL("CREATE TEMPORARY TABLE R2 ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) "
				+ "ENGINE=FEDERATED CONNECTION='mysql://"+Config.COMPUTE_DB_USER+":"+Config.COMPUTE_DB_PASSWD+"@"+nodes[0]+"/"+Config.COMPUTE_DB_NAME+"/R1';");

		op2.addOpenSQL("CREATE TABLE R3 ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) ENGINE=MEMORY;");

		op2.addExecuteSQL("INSERT INTO R3 SELECT * FROM R2;");

		op2.addCloseSQL("DROP TABLE R3");

		final OperatorDesc source = new OperatorDesc(op1.getOperatorId(), nodes[0]);
		op2.addSource(source);
		final OperatorDesc consumer = new OperatorDesc(op2.getOperatorId(), nodes[1]);
		op1.addConsumer(consumer);

		// run plan with client
		final ComputeClient client = new ComputeClient();

		assertNoError(client.openOperator(nodes[0], op1));
		assertNoError(client.openOperator(nodes[1], op2));

		assertNoError(client.executeOperator(nodes[0], op1.getOperatorId()));

		// clean up
		assertNoError(client.closeOperator(nodes[0], op1.getOperatorId()));
		assertNoError(client.closeOperator(nodes[1], op2.getOperatorId()));

		Config.COMPUTE_SIGNAL2QUERY_TRACKER = true;
	}
}
