package org.xdb.test.parallelize;


import org.xdb.client.CompileClient;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.operator.DataExchangeOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.predicate.EnumCompOperator;
import org.xdb.funsql.compile.predicate.SimplePredicate;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.funsql.parallelize.EnumPartitionType;
import org.xdb.funsql.parallelize.Parallelizer;
import org.xdb.funsql.parallelize.PartitionInfo;
import org.xdb.funsql.statement.SelectStmt;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.Table;
import org.xdb.test.XDBTestCase;

public class TestPlanParallelizer extends XDBTestCase {
	private CompileClient client = new CompileClient();
	
	/*
	private String[] schemaDDLs = {
			"CREATE CONNECTION TPCH1 " +
			"URL 'jdbc:mysql://127.0.0.1/tpch_s01' " + 
			"USER 'xroot' " +
			"PASSWORD 'xroot' " +
			"STORE 'XDB';",
			
			"CREATE CONNECTION TPCH2 " +
					"URL 'jdbc:mysql://127.0.0.1/tpch_s01' " + 
					"USER 'xroot' " +
					"PASSWORD 'xroot' " +
					"STORE 'XDB';",
					
					
			"CREATE CONNECTION TPCH3 " +
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
			") PARTIONED BY HASH(L_ORDERKEY) (" +
			"\"P1\" IN CONNECTION  \"TPCH1\"," +
			"\"P2\" IN CONNECTION  \"TPCH2\" )",
			
			"CREATE TABLE  CUSTOMER ( " +
			"C_CUSTKEY     INTEGER, " +
			"C_NAME        VARCHAR, " +
			"C_ADDRESS     VARCHAR, " +
			"C_NATIONKEY   INTEGER, " +
			"C_PHONE       VARCHAR, " +
			"C_ACCTBAL     DECIMAL  , " +
			"C_MKTSEGMENT  VARCHAR, " +
			"C_COMMENT     VARCHAR" +
			") PARTIONED BY HASH(C_CUSTKEY) (" +
			"\"P1\" IN CONNECTION  \"TPCH1\"," +
			"\"P2\" IN CONNECTION  \"TPCH2\"," +
			"\"P3\" IN CONNECTION  \"TPCH3\" )",
			
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
			")  PARTIONED BY HASH(O_ORDERKEY) (" +
			"\"P1\" IN CONNECTION  \"TPCH1\"," +
			"\"P2\" IN CONNECTION  \"TPCH2\" )",
			
			"CREATE TABLE SUPPLIER ( " +
			"S_SUPPKEY INTEGER, " +
			"S_NAME VARCHAR, " +
			"S_ADDRESS VARCHAR, " +
			"S_NATIONKEY INTEGER, " +
			"S_PHONE VARCHAR, " +
			"S_ACCTBAL DECIMAL, " +
			"S_COMMENT VARCHAR" +
			") IN CONNECTION TPCH1;",
			
			"CREATE TABLE NATION (  " +
			"N_NATIONKEY INTEGER, " +
			"N_NAME VARCHAR," +
			"N_REGIONKEY INTEGER," +
			"N_COMMENT VARCHAR" +
			") IN CONNECTION TPCH1;",
			
			"CREATE TABLE REGION ( " +
			"R_REGIONKEY INTEGER," +
			"R_NAME VARCHAR," +
			"R_COMMENT VARCHAR" +
			") IN CONNECTION TPCH1;"
			
	};*/
	
	private String[] schemaDDLs = {
			"CREATE CONNECTION TPCH1 " +
			"URL 'jdbc:mysql://127.0.0.1/tpch_s01' " + 
			"USER 'xroot' " +
			"PASSWORD 'xroot' " +
			"STORE 'XDB';",
			
			"CREATE CONNECTION TPCH2 " +
					"URL 'jdbc:mysql://127.0.0.1/tpch_s01' " + 
					"USER 'xroot' " +
					"PASSWORD 'xroot' " +
					"STORE 'XDB';",
					
					
			"CREATE CONNECTION TPCH3 " +
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
			") PARTIONED BY HASH(L_ORDERKEY) (" +
			"\"P1\" IN CONNECTION  \"TPCH1\"," +
			"\"P2\" IN CONNECTION  \"TPCH2\" )",
			
			"CREATE TABLE  CUSTOMER ( " +
			"C_CUSTKEY     INTEGER, " +
			"C_NAME        VARCHAR, " +
			"C_ADDRESS     VARCHAR, " +
			"C_NATIONKEY   INTEGER, " +
			"C_PHONE       VARCHAR, " +
			"C_ACCTBAL     DECIMAL  , " +
			"C_MKTSEGMENT  VARCHAR, " +
			"C_COMMENT     VARCHAR" +
			") IN CONNECTION TPCH1;",
			
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
			")  PARTIONED BY HASH(O_ORDERKEY) (" +
			"\"P1\" IN CONNECTION  \"TPCH1\"," +
			"\"P2\" IN CONNECTION  \"TPCH2\" )",
			
			"CREATE TABLE SUPPLIER ( " +
			"S_SUPPKEY INTEGER, " +
			"S_NAME VARCHAR, " +
			"S_ADDRESS VARCHAR, " +
			"S_NATIONKEY INTEGER, " +
			"S_PHONE VARCHAR, " +
			"S_ACCTBAL DECIMAL, " +
			"S_COMMENT VARCHAR" +
			") IN CONNECTION TPCH1;",
			
			"CREATE TABLE NATION (  " +
			"N_NATIONKEY INTEGER, " +
			"N_NAME VARCHAR," +
			"N_REGIONKEY INTEGER," +
			"N_COMMENT VARCHAR" +
			") IN CONNECTION TPCH1;",
			
			"CREATE TABLE REGION ( " +
			"R_REGIONKEY INTEGER," +
			"R_NAME VARCHAR," +
			"R_COMMENT VARCHAR" +
			") IN CONNECTION TPCH1;"
			
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
	
	private void compileStatement(String stmt){
		FunSQLCompiler compiler = new FunSQLCompiler();
		SelectStmt sstmt = (SelectStmt) compiler.compile(stmt);

		sstmt.getPlan().tracePlan(this.getClass().getName() + "_COMPILED");
		Parallelizer p = new Parallelizer(sstmt.getPlan());
		p.parallelize().tracePlan(this.getClass().getName() + "_PARALLELIZED");
		this.assertNoError(compiler.getLastError());
	}
	
	private void executeStmt(String stmt){
		Error error = client.executeStmt(stmt);
		this.assertNoError(error);
	}
	
	
	public void testJoinAndAggregation(){
		String q3 = "" +
				"select l_orderkey, " +
				"sum(l_extendedprice*(1-l_discount)) as revenue, " +
				"o_orderdate, " +
				"o_shippriority " +
				"from customer, " +
				"orders, " +
				"lineitem " +
				"where c_mktsegment = 'BUILDING' and " +
				"c_custkey = o_custkey and " +
				"l_orderkey = o_orderkey and " +
				"o_orderdate < date '1995-03-15' and " +
				"l_shipdate > date '1995-03-15' " +
				"group by l_orderkey, o_orderdate, o_shippriority";
		
		this.compileStatement(q3);
	}

	public void testPlanCopying(){
		CompilePlan plan = new CompilePlan();
		Table tc1 = new Table("t1");
		Catalog.addTable(tc1);
		Table tc2 = new Table("t2");
		Catalog.addTable(tc2);
		Table tc3 = new Table("t3");
		Catalog.addTable(tc3);

		TableOperator t1 = new TableOperator(new TokenIdentifier("Table1"));
		t1.setTable(tc1);
		plan.addOperator(t1, false);

		TableOperator t2 = new TableOperator(new TokenIdentifier("Table2"));
		t2.setTable(tc2);
		plan.addOperator(t2, false);
		
		TableOperator t3 = new TableOperator(new TokenIdentifier("Table3"));
		t3.setTable(tc3);
		plan.addOperator(t3, false);
		
	
		DataExchangeOperator deOp1 = new DataExchangeOperator(t1,null,null);
		PartitionInfo p1 = new PartitionInfo(EnumPartitionType.HASH, 2);
		deOp1.setInputPartitioning(p1);
		plan.addOperator(deOp1, false);
		
		TokenAttribute ta1 = new TokenAttribute("a");
		TokenAttribute ta2 = new TokenAttribute("a");
		EquiJoin ej1 = new EquiJoin(t2, t3, ta1, ta2);
		plan.addOperator(ej1, false);
		
		DataExchangeOperator deOp2 = new DataExchangeOperator(ej1,null,null);
		deOp2.setInputPartitioning(p1);
		plan.addOperator(deOp2, false);
		
		EquiJoin ej2 = new EquiJoin(deOp1, deOp2, ta1, ta2);
		plan.addOperator(ej2, false);
		
		DataExchangeOperator deOp3 = new DataExchangeOperator(ej2,null,null);
		deOp3.setInputPartitioning(p1);
		plan.addOperator(deOp3, false);
		
		GenericSelection gs = new GenericSelection(deOp3);
		SimplePredicate sp = new SimplePredicate();
		SimpleExpression se = new SimpleExpression();
		se.setOper(new TokenAttribute("A"));
		sp.setExpr1(se);
		sp.setExpr2(se);
		sp.setComp(EnumCompOperator.SQL_EQUAL);
		gs.setPredicate(sp);
		plan.addOperator(gs, true);

		
		plan.tracePlan(this.toString());
		
		Parallelizer p = new Parallelizer(plan);
		p.copyParalellPart(plan);
		

		plan.tracePlan(this.toString() + "PARALLEL");
		
	}
	@Override
	public void tearDown(){
		super.tearDown();
	}
}
