package org.xdb.test.tpch;

import org.xdb.client.CompileClient;
import org.xdb.error.Error;
import org.xdb.test.DistributedXDBTestCase;

/**
 * Test case with partitioned TPC-H schema
 * @author cbinnig
 *
 */
public class TestTPCHParallel extends DistributedXDBTestCase {
	
	public TestTPCHParallel() {
		super(2);
	}


	private CompileClient client = new CompileClient();
	
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
			") PARTIONED BY HASH ( L_ORDERKEY ) ( " +
			" P0 IN CONNECTION TPCH1," +
			" P1 IN CONNECTION TPCH2 )",
			
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
			") PARTIONED BY RREF ( O_ORDERKEY REFERENCES LINEITEM.L_ORDERKEY ) ",
			
			
			"CREATE TABLE  CUSTOMER ( " +
			"C_CUSTKEY     INTEGER, " +
			"C_NAME        VARCHAR, " +
			"C_ADDRESS     VARCHAR, " +
			"C_NATIONKEY   INTEGER, " +
			"C_PHONE       VARCHAR, " +
			"C_ACCTBAL     DECIMAL  , " +
			"C_MKTSEGMENT  VARCHAR, " +
			"C_COMMENT     VARCHAR" +
			") PARTIONED BY RREF ( C_CUSTKEY REFERENCES ORDERS.O_CUSTKEY )",
			

			"CREATE TABLE SUPPLIER ( " +
			"S_SUPPKEY INTEGER, " +
			"S_NAME VARCHAR, " +
			"S_ADDRESS VARCHAR, " +
			"S_NATIONKEY INTEGER, " +
			"S_PHONE VARCHAR, " +
			"S_ACCTBAL DECIMAL, " +
			"S_COMMENT VARCHAR" +
			") PARTIONED BY RREF ( S_SUPPKEY REFERENCES LINEITEM.L_SUPPKEY )",
			
			"CREATE TABLE PARTSUPP ( " +
			"	PS_PARTKEY     INTEGER, " +
			"	PS_SUPPKEY     INTEGER, " +
			"	PS_AVAILQTY    INTEGER, " +
			"	PS_SUPPLYCOST  DECIMAL, " +
			"	PS_COMMENT     VARCHAR " +
			") PARTIONED BY RREF ( PS_SUPPKEY REFERENCES SUPPLIER.S_SUPPKEY );",
			
			"CREATE TABLE PART  ( " +
			"P_PARTKEY     INTEGER," +
			"P_NAME        VARCHAR, " +
			"P_MFGR        VARCHAR, " +
			"P_BRAND       VARCHAR, " +
			"P_TYPE        VARCHAR, " +
			"P_SIZE        INTEGER, " +
			"P_CONTAINER   VARCHAR, " +
			"P_RETAILPRICE DECIMAL, " +
			"P_COMMENT     VARCHAR" +
			") PARTIONED BY RREF ( P_PARTKEY REFERENCES PARTSUPP.PS_PARTKEY );",
					
			"CREATE TABLE NATION (  " +
			"N_NATIONKEY INTEGER, " +
			"N_NAME VARCHAR," +
			"N_REGIONKEY INTEGER," +
			"N_COMMENT VARCHAR" +
			") REPLICATED IN CONNECTION TPCH1, TPCH2;",
			
			"CREATE TABLE REGION ( " +
			"R_REGIONKEY INTEGER," +
			"R_NAME VARCHAR," +
			"R_COMMENT VARCHAR" +
			") REPLICATED IN CONNECTION TPCH1, TPCH2;"
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
	
	/*
	select
		l_returnflag,
		l_linestatus,
		sum(l_quantity) as sum_qty,
		sum(l_extendedprice) as sum_base_price,
		sum(l_extendedprice * (1 - l_discount)) as sum_disc_price,
		sum(l_extendedprice * (1 - l_discount) * (1 + l_tax)) as sum_charge,
		avg(l_quantity) as avg_qty,
		avg(l_extendedprice) as avg_price,
		avg(l_discount) as avg_disc,
		count(*) as count_order
	from
		lineitem
	where
		l_shipdate <= date '1998-12-01' - interval ':1' day (3)
	group by
		l_returnflag,
		l_linestatus
	order by
		l_returnflag,
		l_linestatus;
		 */
	public void testQ01() {
		String q1 = "select	l_returnflag,	"
				+ "l_linestatus,	"
				+ "sum(l_quantity) as sum_qty,	"
				+ "sum(l_extendedprice) as sum_base_price,	"
				+ "sum(l_extendedprice * (1 - l_discount)) as sum_disc_price,	"
				+ "sum(l_extendedprice * (1 - l_discount) * (1 + l_tax)) as sum_charge,	"
				+ "avg(l_quantity) as avg_qty,	"
				+ "avg(l_extendedprice) as avg_price,	"
				+ "avg(l_discount) as avg_disc,	"
				+ "count(l_orderkey) as count_order " + "from	lineitem "
				+ "where l_shipdate <= date '1998-12-01' "
				+ "group by l_returnflag, l_linestatus";

		executeStmt(q1);
	}

	//TODO: Check Parallel Q2 since it gives no results
	public void testQ02(){
		/*
		select
			s_acctbal,
			s_name,
			n_name,
			p_partkey,
			p_mfgr,
			s_address,
			s_phone,
			s_comment
		from
			part,
			supplier,
			partsupp,
			nation,
			region
		where
			p_partkey = ps_partkey
			and s_suppkey = ps_suppkey
			and p_size = :1
			and p_type like '%:2'
			and s_nationkey = n_nationkey
			and n_regionkey = r_regionkey
			and r_name = ':3'
			and ps_supplycost = (
				select
					min(ps_supplycost)
				from
					partsupp,
					supplier,
					nation,
					region
				where
					p_partkey = ps_partkey
					and s_suppkey = ps_suppkey
					and s_nationkey = n_nationkey
					and n_regionkey = r_regionkey
					and r_name = ':3'
			)
		order by
			s_acctbal desc,
			n_name,
			s_name,
			p_partkey;
		 */
				String createQ2 = "CREATE FUNCTION q2( OUT o1 TABLE) \n" +
						"BEGIN \n" +
						"  :t1 = " +
						"		select" + 
						"			min(ps_supplycost) as min_supplycost, " +
						"			ps_partkey"+ 
						"		from " + 
						"			nation," + 
						"			region," + 
						"			supplier," + 
						"			partsupp" + 
						"		where " + 
						"			r_regionkey = n_regionkey" + 
						"			and s_nationkey = n_nationkey" + 
						"			and s_suppkey = ps_suppkey" + 
						"			and r_name = 'EUROPE'" +
						"		group by" +
						"			ps_partkey;" +
						"\n"+
						"	:o1 = select" + 
						"			s_acctbal," + 
						"			s_name," + 
						"			n_name," + 
						"			p_partkey," + 
						"			p_mfgr," + 
						"			s_address," + 
						"			s_phone," + 
						"			s_comment " + 
						"		from " + 
						"			region," +
						"			nation," + 
						"			supplier," + 
						"			partsupp as ps," + 
						"			part," + 
						"			:t1 as temp1 " + 
						"		where" + 
						"			r_regionkey = n_regionkey" + 
						"			and n_nationkey = s_nationkey" + 
						"			and s_suppkey = ps.ps_suppkey" + //TODO: Problem when removing ps
						"			and ps.ps_partkey = p_partkey" + 
						"			and ps.ps_partkey=temp1.ps_partkey" +
						"			and temp1.min_supplycost = ps.ps_supplycost" + 
						"			and p_size = 15" + 
						"			and p_type like '%BRASS'" + 
						"			and r_name = 'EUROPE';"+ 
						"END;";
				
		String callQ2 = "CALL FUNCTION q2;";

		executeStmt(createQ2);
		executeStmt(callQ2);
	}

	public void testQ03(){
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
	
	public void testQ05WithAvg(){
		
		/*
		 * 
		 * Select 
n_name,
sum(l_extendedprice * (1-l_discount)) as revenue,
avg(l_extendedprice * (1-l_discount)) as avgrevenue
from 
customer,
orders,
lineitem,
supplier,
nation,
region
where c_custkey = o_custkey
and l_orderkey = o_orderkey
and l_suppkey = s_suppkey
and c_nationkey = s_nationkey
and s_nationkey = n_nationkey
and r_regionkey = n_regionkey
and r_name = 'ASIA'
and o_orderdate > DATE('1994-01-01 00:00:00')
and o_orderdate < DATE('1995-01-01 00:00:00')
group by n_nationkey, n_name;
		 */
		String q5 = "Select n_name, " +
					"sum(l_extendedprice * (1-l_discount)) as revenue, " +
					"avg(l_extendedprice * (1-l_discount)) as avgrevenue " +
					"from customer, orders, lineitem, supplier, nation, region " +
					"where c_custkey = o_custkey " +
					"and l_orderkey = o_orderkey " +
					"and l_suppkey = s_suppkey  " +
					"and n_nationkey = s_nationkey " +
					"and r_regionkey = n_regionkey " +
					"and s_nationkey = c_nationkey " +
					"and r_name = 'ASIA' " +
					"and o_orderdate > date '1994-01-01' "+
					"and o_orderdate < date '1995-01-01' "+
					"group by n_nationkey, n_name;";
		this.executeStmt(q5);
	}
	
	public void testQ06(){
		/*select
	sum(l_extendedprice * l_discount) as revenue
from
	lineitem
where
	l_shipdate >= date '1994-01-01'
	and l_shipdate < date '1994-01-01' + interval '1' year
	and l_discount between 0.0 and 0.7
	and l_quantity < 24;
	*/
		
		String q6 = "select sum(l_extendedprice * l_discount) as revenue " +
				"from lineitem " +
				"where l_shipdate >= date '1994-01-01' " +
				"and l_shipdate < date '1995-01-01' " +
				"and l_discount >= 0.0 " +
				"and l_discount < 0.9 " +
				"and l_quantity < 24; ";
		this.executeStmt(q6);	
		
	}
	
	public void testQ10(){/*
	select
	c_custkey,
	c_name,
	sum(l_extendedprice * (1 - l_discount)) as revenue,
	c_acctbal,
	n_name,
	c_address,
	c_phone,
	c_comment
from
	customer,
	orders,
	lineitem,
	nation
where
	c_custkey = o_custkey
	and l_orderkey = o_orderkey
	and o_orderdate >= date '1994-01-01'
	and o_orderdate < date '1994-04-01'
	and l_returnflag = 'R'
	and c_nationkey = n_nationkey
group by
	c_custkey,
	c_name,
	c_acctbal,
	c_phone,
	n_name,
	c_address,
	c_comment;*/
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
	
	
	@Override
	public void tearDown(){
		super.tearDown();
	}
}
