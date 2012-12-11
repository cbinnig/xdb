package org.xdb.test.execute.operators;

import java.sql.ResultSet;

import org.junit.Test;
import org.xdb.Config;
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.execute.operators.MySQLExecuteOperator;
import org.xdb.test.TestCase;
import org.xdb.utils.Identifier;

public class TestComputeOperator extends TestCase {
	@Test
	public void test1Op() throws Exception {

		Config.COMPUTE_SIGNAL2QUERY_TRACKER = false;
		//operator
		final MySQLExecuteOperator op = new MySQLExecuteOperator(new Identifier("1"));
		op.addOpenSQL("CREATE TEMPORARY TABLE REGION ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) " +
				"ENGINE=FEDERATED CONNECTION='mysql://xroot:xroot@127.0.0.1/tpch_s01/REGION';");

		op.addOpenSQL("CREATE TABLE R1 ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) ENGINE=MEMORY;");

		op.addExecuteSQL("INSERT INTO R1 SELECT * FROM REGION WHERE R_NAME LIKE 'A%';");

		op.addCloseSQL("DROP TABLE R1");

		//execute and clean up
		executeOperator(op);

		//check results
		final ResultSet rs = this.executeComputeQuery("SELECT COUNT(*) AS CNT FROM R1;");
		assertResultSize(rs, 3);

		//close op
		closeOperator(op);

		Config.COMPUTE_SIGNAL2QUERY_TRACKER = true;
	}

	@Test
	public void test2Ops() throws Exception {
		Config.COMPUTE_SIGNAL2QUERY_TRACKER = false; 
		//first op
		final MySQLExecuteOperator op1 = new MySQLExecuteOperator(new Identifier("1"));

		op1.addOpenSQL("CREATE TEMPORARY TABLE REGION ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) " +
				"ENGINE=FEDERATED CONNECTION='mysql://xroot:xroot@127.0.0.1/tpch_s01/REGION';");

		op1.addOpenSQL("CREATE TABLE R1 ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) ENGINE=MEMORY;");

		op1.addExecuteSQL("INSERT INTO R1 SELECT * FROM REGION WHERE R_NAME LIKE 'A%';");

		op1.addCloseSQL("DROP TABLE R1");

		executeOperator(op1);
		ResultSet rs = this.executeComputeQuery("SELECT COUNT(*) AS CNT FROM R1;");
		assertResultSize(rs, 3);

		//second op
		final MySQLExecuteOperator op2 = new MySQLExecuteOperator(new Identifier("2"));

		op2.addOpenSQL("CREATE TEMPORARY TABLE R2 ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) " +
				"ENGINE=FEDERATED CONNECTION='mysql://xroot:xroot@127.0.0.1/"+Config.COMPUTE_DB_NAME+"/R1';");

		op2.addOpenSQL("CREATE TABLE R3 ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) ENGINE=MEMORY;");

		op2.addExecuteSQL("INSERT INTO R3 SELECT * FROM R2;");

		op2.addCloseSQL("DROP TABLE R3");

		executeOperator(op2);
		rs = this.executeComputeQuery("SELECT COUNT(*) AS CNT FROM R3;");
		assertResultSize(rs, 3);

		//clean up
		closeOperator(op1);
		closeOperator(op2);

		Config.COMPUTE_SIGNAL2QUERY_TRACKER = true;
	}

	protected void executeOperator(final AbstractExecuteOperator op){
		assertNoError(op.open());
		assertNoError(op.execute());
	}

	protected void closeOperator(final AbstractExecuteOperator op){
		assertNoError(op.close());
	}
}
