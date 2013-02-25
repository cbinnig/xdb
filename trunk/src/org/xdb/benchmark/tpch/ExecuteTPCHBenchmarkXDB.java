package org.xdb.benchmark.tpch;

import org.xdb.Config;
import org.xdb.client.CompileClient;
import org.xdb.error.Error;
import org.xdb.server.CompileServer;
import org.xdb.server.ComputeServer;
import org.xdb.server.MasterTrackerServer;
import org.xdb.server.QueryTrackerServer;

public class ExecuteTPCHBenchmarkXDB extends ExecuteTPCHBenchmark{
	private CompileClient client = new CompileClient();
	private CompileServer compileServer;
	private MasterTrackerServer mTrackerServer;
	private QueryTrackerServer qTrackerServer;
	private ComputeServer computeServer;
	
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
			") IN CONNECTION TPCH;",
			
			"CREATE TABLE  CUSTOMER ( " +
			"C_CUSTKEY     INTEGER, " +
			"C_NAME        VARCHAR, " +
			"C_ADDRESS     VARCHAR, " +
			"C_NATIONKEY   INTEGER, " +
			"C_PHONE       VARCHAR, " +
			"C_ACCTBAL     DECIMAL  , " +
			"C_MKTSEGMENT  VARCHAR, " +
			"C_COMMENT     VARCHAR" +
			") IN CONNECTION TPCH;",
			
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
			") IN CONNECTION TPCH;",
			
			"CREATE TABLE SUPPLIER ( " +
			"S_SUPPKEY INTEGER, " +
			"S_NAME VARCHAR, " +
			"S_ADDRESS VARCHAR, " +
			"S_NATIONKEY INTEGER, " +
			"S_PHONE VARCHAR, " +
			"S_ACCTBAL DECIMAL, " +
			"S_COMMENT VARCHAR" +
			") IN CONNECTION TPCH;",
			
			"CREATE TABLE NATION (  " +
			"N_NATIONKEY INTEGER, " +
			"N_NAME VARCHAR," +
			"N_REGIONKEY INTEGER," +
			"N_COMMENT VARCHAR" +
			") IN CONNECTION TPCH;",
			
			"CREATE TABLE REGION ( " +
			"R_REGIONKEY INTEGER," +
			"R_NAME VARCHAR," +
			"R_COMMENT VARCHAR" +
			") IN CONNECTION TPCH;"
			
	};
	
	public static void main(String args[]){
		int numberoftimes = 1;
		if(args.length!=0){
			numberoftimes = Integer.parseInt(args[0]);
		}
		ExecuteTPCHBenchmarkXDB bench = new ExecuteTPCHBenchmarkXDB(numberoftimes);
		bench.run();
	}
	
	public ExecuteTPCHBenchmarkXDB(int numberoftimes) {
		super(numberoftimes);
	}

	public void execute(int numberoftimes) {
		for(int i = 0; i < numberoftimes; i++){
			try{
				this.executeQ1();
				this.executeQ3();
				this.executeQ5();
				this.executeQ6();
				this.executeQ10();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	protected void prepare() {
		try {
			CompileServer.deleteCatalog();
			
			compileServer = new CompileServer();
			compileServer.startServer();

			mTrackerServer = new MasterTrackerServer();
			mTrackerServer.startServer();

			qTrackerServer = new QueryTrackerServer();
			qTrackerServer.startServer();

			computeServer = new ComputeServer(Config.COMPUTE_URL, Config.COMPUTE_PORT);
			computeServer.startServer();


			createSchema();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void cleanup() {
		compileServer.stopServer();
		mTrackerServer.stopServer();
		qTrackerServer.stopServer();
		computeServer.stopServer();
	}

	private void createSchema() {
		for (String schemaDDL : this.schemaDDLs) {
			try {
				executeStmt(schemaDDL);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void executeStmt(String stmt) throws Exception {
		Error error = client.executeStmt(stmt);
		if (error.isError())
			throw new Exception();
	}
	
	public void executeQ1() throws Exception{
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
				"where	l_shipdate <= date '1998-12-01' " +
				"group by l_returnflag,	l_linestatus";
		
		executeStmt(q1);
	}
	
	public void executeQ3() throws Exception{
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
		
		this.executeStmt(q3);
	}
	
	public void executeQ5() throws Exception{
		
		String q5 = "Select n_name, " +
					"sum(l_extendedprice * (1-l_discount)) as revenue " +
					"from customer, orders, lineitem, supplier, nation, region " +
					"where c_custkey = o_custkey " +
					"and l_orderkey = o_orderkey " +
					"and l_suppkey = s_suppkey  " +
					"and c_nationkey = s_nationkey " +
					"and s_nationkey = n_nationkey " +
					"and n_regionkey = r_regionkey " +
					"and r_name = 'ASIA' " +
					"and o_orderdate > date '1994-01-01' "+
					"and o_orderdate < date '1995-01-01' "+
					"group by n_name;";
		this.executeStmt(q5);
	}
	
	
	public void executeQ6() throws Exception{
		
		String q6 = "select sum(l_extendedprice * l_discount) as revenue " +
				"from lineitem " +
				"where l_shipdate >= date '1994-01-01' " +
				"and l_shipdate < date '1995-01-01' " +
				"and l_discount >= 0.0 " +
				"and l_discount < 0.9 " +
				"and l_quantity < 24; ";
		this.executeStmt(q6);			
	}
	
	public void executeQ10() throws Exception{
		String q10 = "select "+
						"c_custkey, "+
						"c_name, "+
						"sum(l_extendedprice * (1 - l_discount)) as revenue, "+
						"c_acctbal, "+
						"n_name, "+
						"c_address, "+
						"c_phone, "+
						"c_comment " +
					"from "+
						"customer, "+
						"orders, "+
						"lineitem, "+
						"nation " +
					"where "+
						"c_custkey = o_custkey "+
						"and l_orderkey = o_orderkey "+
						"and o_orderdate >= date '1994-01-01' "+
						"and o_orderdate < date '1994-04-01' "+
						"and l_returnflag = 'R' "+
						"and c_nationkey = n_nationkey " +
					"group by "+
						"c_custkey, " +
						"c_name, "+
						"c_acctbal, "+
						"c_phone, "+
						"n_name, "+
						"c_address, "+
						"c_comment;" ;
		this.executeStmt(q10);
	}

}