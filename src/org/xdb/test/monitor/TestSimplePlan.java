package org.xdb.test.monitor;

import org.junit.Test;
import org.xdb.client.CompileClient;
import org.xdb.test.DistributedXDBTestCase;

import org.xdb.error.Error;

public class TestSimplePlan extends DistributedXDBTestCase {
	private CompileClient compileClient;
	
	public TestSimplePlan() {
		super(2);
		this.compileClient = new CompileClient();
	}

	private void createSchema(){
		String conn1DDL = 
				"CREATE CONNECTION tpchConn1 " +
				"URL 'jdbc:mysql://127.0.0.1/tpch_s01' " +
				"USER 'xroot' " +
				"PASSWORD 'xroot' " +
				"STORE 'XDB' ";
		
		String conn2DDL = 
				"CREATE CONNECTION tpchConn2 " +
				"URL 'jdbc:mysql://127.0.0.1/tpch_s01' " +
				"USER 'xroot' " +
				"PASSWORD 'xroot' " +
				"STORE 'XDB' ";
		
		String customerDDL = 
				"CREATE TABLE  CUSTOMER ( " +
						"C_CUSTKEY     INTEGER, " +
						"C_NAME        VARCHAR, " +
						"C_ADDRESS     VARCHAR, " +
						"C_NATIONKEY   INTEGER, " +
						"C_PHONE       VARCHAR, " +
						"C_ACCTBAL     DECIMAL  , " +
						"C_MKTSEGMENT  VARCHAR, " +
						"C_COMMENT     VARCHAR" +
						") REPLICATED IN CONNECTION  " +
						"tpchConn1, tpchConn2;" ;
		
		String ordersDDL =
				"CREATE TABLE ORDERS  ( " +
						"O_ORDERKEY       INTEGER, " +
						"O_CUSTKEY        INTEGER, " +
						"O_ORDERSTATUS    VARCHAR, " +
						"O_TOTALPRICE     DECIMAL, " +
						"O_ORDERDATE      DATE, " +
						"O_ORDERPRIORITY  VARCHAR, " +
						"O_CLERK          VARCHAR,  " +
						"O_SHIPPRIORITY   INTEGER, " +
						"O_COMMENT        VARCHAR" +
						") REPLICATED IN CONNECTION  " +
						"tpchConn1, tpchConn2;";
		
		
		Error err = this.compileClient.executeStmt(conn1DDL);
		this.assertNoError(err);
		
		err =  this.compileClient.executeStmt(conn2DDL);
		this.assertNoError(err);
		
		err = this.compileClient.executeStmt(customerDDL);
		this.assertNoError(err);
		
		err = this.compileClient.executeStmt(ordersDDL);
		this.assertNoError(err);
	}
	
	@Override
	public void setUp(){
		super.setUp();
		createSchema();
	}

	@Test
	public void test1(){
		String selectDML = "" +
				"select c_custkey, " +
				"sum(o_totalprice) as revenue " +
				"from customer, " +
				"orders " +
				"where c_custkey = o_custkey " +
				"group by c_custkey";
		
		Error err = this.compileClient.executeStmt(selectDML);
		this.assertNoError(err);
	}
}
