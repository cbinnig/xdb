package org.xdb.test.tpch;

import org.junit.Test;
import org.xdb.client.CompileClient;
import org.xdb.test.XDBTestCase;
import org.xdb.error.Error;

public class TestTPCH extends XDBTestCase {
	private CompileClient client = new CompileClient();;
	private String[] schemaDDLs = {
			"CREATE CONNECTION TPCH " +
			"URL 'jdbc:mysql://127.0.0.1/tpch_s01' " + 
			"USER 'xroot' " +
			"PASSWORD 'xroot' " +
			"STORE 'XDB';",
			
			"CREATE TABLE LINEITEM ( " +
			"L_ORDERKEY    		INTEGER," +
			"L_PARTKEY     		INTEGER," +
			"L_SUPPKEY     		INTEGER," +
			"L_LINENUMBER  		INTEGER," +
			"L_QUANTITY    		DECIMAL," +
			"L_EXTENDEDPRICE  	DECIMAL," +
			"L_DISCOUNT    		DECIMAL," +
			"L_TAX         		DECIMAL," +
			"L_RETURNFLAG  		VARCHAR," +
			"L_LINESTATUS  		VARCHAR," +
			"L_SHIPDATE    		DATE," +
			"L_COMMITDATE  		DATE," +
			"L_RECEIPTDATE 		DATE," +
			"L_SHIPINSTRUCT 	VARCHAR," +
			"L_SHIPMODE     	VARCHAR," +
			"L_COMMENT      	VARCHAR" +
			") IN CONNECTION TPCH;"
	};
	
	@Override
	public void setUp(){
		super.setUp();
		this.createSchema();
	}
	
	private void createSchema(){
		for(String schemaDDL: this.schemaDDLs){
			executeStmt(schemaDDL);
		}
	}
	
	private void executeStmt(String stmt){
		Error error = client.executeStmt(stmt);
		this.assertNoError(error);
	}
	
	@Test
	public void testQ1(){
		String q1 = 
				"select	l_returnflag,	" +
				"l_linestatus,	" +
				"sum(l_quantity) as sum_qty,	" +
				"sum(l_extendedprice) as sum_base_price,	" +
				"sum(l_extendedprice * (1 - l_discount)) as sum_disc_price,	" +
				"sum(l_extendedprice * (1 - l_discount) * (1 + l_tax)) as sum_charge,	" +
				"avg(l_quantity) as avg_qty,	" +
				"avg(l_extendedprice) as avg_price,	" +
				"avg(l_discount) as avg_disc,	" +
				"count(l_orderkey) as count_order " +
				"from	lineitem " +
				"where	l_shipdate <= '1998-12-01' " +
				"group by l_returnflag,	l_linestatus";
		
		executeStmt(q1);
	}
	
	@Override
	public void tearDown(){
		super.tearDown();
	}
}
