package org.xdb.test.parallelize;

import org.xdb.client.CompileClient;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.parallelize.Parallelizer;
import org.xdb.funsql.statement.SelectStmt;
import org.xdb.metadata.Connection;
import org.xdb.store.EnumStore;
import org.xdb.test.XDBTestCase;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.utils.Tuple;

public class TestParallelPlanCodeGen extends XDBTestCase  {
	private CompileClient client = new CompileClient();
	private QueryTrackerNode qTracker;
	
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
	public void setUp() {
		super.setUp();
		
		this.qTracker = this.qTrackerServer.getNode();
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

	private void compileAndGenPlan(String stmt){
		FunSQLCompiler compiler = new FunSQLCompiler();
		SelectStmt sstmt = (SelectStmt) compiler.compile(stmt);
		CompilePlan serialPlan = sstmt.getPlan();
		this.assertNoError(compiler.getLastError());

		for(AbstractCompileOperator op : serialPlan.getOperators()) {
			if(op instanceof TableOperator) {
				((TableOperator)op).setConnection(new Connection("INVALID_CONNECTION", "mysql://127.0.0.1/xdbR", "user", "password", EnumStore.MYSQL));
			}
		}
		
		//copy somehow doesn't work
		CompilePlan parallelPlan = ((SelectStmt)compiler.compile(stmt)).getPlan();
		for(AbstractCompileOperator op : parallelPlan.getOperators()) {
			if(op instanceof TableOperator) {
				((TableOperator)op).setConnection(new Connection("INVALID_CONNECTION", "mysql://127.0.0.1/xdbR", "user", "password", EnumStore.MYSQL));
			}
		}
		
		Parallelizer p = new Parallelizer(parallelPlan);
		parallelPlan = p.parallelize();		
		
		serialPlan.tracePlan(this.getClass().getName() + "_COMPILED");
		parallelPlan.tracePlan(this.getClass().getName() + "_PARALLELCOMP");
		
		Error annotation = QueryTrackerNode.annotateCompilePlan(serialPlan);
		assertNoError(annotation);
		
		Tuple<QueryTrackerPlan, Error> serialTrackerPlan = qTracker.generateQueryTrackerPlan(serialPlan);
		assertNoError(serialTrackerPlan.getObject2());
		
		annotation = QueryTrackerNode.annotateCompilePlan(parallelPlan);
		assertNoError(annotation);
		
		Tuple<QueryTrackerPlan, Error> parallelTrackerPlan = qTracker.generateQueryTrackerPlan(parallelPlan);
		assertNoError(parallelTrackerPlan.getObject2());

		serialTrackerPlan.getObject1().tracePlan(this.getClass().getName() + "_GEN");
		parallelTrackerPlan.getObject1().tracePlan(this.getClass().getName() + "_PARALLELGEN");

		//serialplan should be collapsed
		assertEquals(1, serialTrackerPlan.getObject1().getTrackerOperators().size());
		assertNotSame(1, parallelTrackerPlan.getObject1().getTrackerOperators().size());
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
		
		this.compileAndGenPlan(q3);
	}

}
