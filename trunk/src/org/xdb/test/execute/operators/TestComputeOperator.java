package org.xdb.test.execute.operators;

import java.sql.ResultSet;

import org.junit.Test;
import org.xdb.execute.operators.AbstractOperator;
import org.xdb.execute.operators.MySQLOperator;
import org.xdb.test.TestCase;
import org.xdb.utils.Identifier;

public class TestComputeOperator extends TestCase {
	@Test
	public void test1Op() throws Exception {
		//operator
		MySQLOperator op = new MySQLOperator(new Identifier("1"));
		op.addOpenSQL("CREATE TEMPORARY TABLE REGION ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) " +
				"ENGINE=FEDERATED CONNECTION='mysql://root@127.0.0.1/tpch_s01/region';");
		
		op.addOpenSQL("CREATE TABLE R1 ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) ENGINE=MEMORY;");
		
		op.addExecuteSQL("INSERT INTO R1 SELECT * FROM REGION WHERE R_NAME LIKE 'A%';");
		
		op.addCloseSQL("DROP TABLE R1");
		
		//execute and clean up
		this.executeOperator(op);
		
		//check results
		ResultSet rs = this.execute("SELECT COUNT(*) AS CNT FROM R1;");
		this.assertResultSize(rs, 3);
		
		//close op
		this.closeOperator(op);
	}
	
	@Test
	public void test2Ops() throws Exception {
		//first op
		MySQLOperator op1 = new MySQLOperator(new Identifier("1"));
		
		op1.addOpenSQL("CREATE TEMPORARY TABLE REGION ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) " +
				"ENGINE=FEDERATED CONNECTION='mysql://root@127.0.0.1/tpch_s01/region';");
		
		op1.addOpenSQL("CREATE TABLE R1 ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) ENGINE=MEMORY;");
		
		op1.addExecuteSQL("INSERT INTO R1 SELECT * FROM REGION WHERE R_NAME LIKE 'A%';");
		
		op1.addCloseSQL("DROP TABLE R1");
		
		this.executeOperator(op1);
		ResultSet rs = this.execute("SELECT COUNT(*) AS CNT FROM R1;");
		this.assertResultSize(rs, 3);
		
		//second op
		MySQLOperator op2 = new MySQLOperator(new Identifier("2"));
		
		op2.addOpenSQL("CREATE TEMPORARY TABLE R2 ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) " +
				"ENGINE=FEDERATED CONNECTION='mysql://root@127.0.0.1/stratusdb/r1';");
		
		op2.addOpenSQL("CREATE TABLE R3 ( R_REGIONKEY INTEGER NOT NULL, R_NAME CHAR(25) NOT NULL, R_COMMENT VARCHAR(152)) ENGINE=MEMORY;");
		
		op2.addExecuteSQL("INSERT INTO R3 SELECT * FROM R2;");
		
		op2.addCloseSQL("DROP TABLE R3");
		
		this.executeOperator(op2);
		rs = this.execute("SELECT COUNT(*) AS CNT FROM R3;");
		this.assertResultSize(rs, 3);
		
		//clean up
		this.closeOperator(op1);
		this.closeOperator(op2);
	}
	
	protected void executeOperator(AbstractOperator op){
		this.assertNoError(op.open());
		this.assertNoError(op.execute());
	}
	
	protected void closeOperator(AbstractOperator op){
		this.assertNoError(op.close());
	}
}
