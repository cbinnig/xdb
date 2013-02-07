package org.xdb.benchmark.tpch;

import java.sql.SQLException;

import org.xdb.logging.XDBExecuteTimeMeasurement;

public class ExecuteTPCHBenchmarkMYSQL extends ExecuteTPCHBenchmark {
	private SimpleMYSQLConnection conn;
	private XDBExecuteTimeMeasurement measurement;
	
	public static void main(String args[]){
		int numberoftimes = 1;
		if(args.length!=0){
			numberoftimes = Integer.parseInt(args[0]);
		}
		ExecuteTPCHBenchmarkMYSQL bench = new ExecuteTPCHBenchmarkMYSQL(numberoftimes);
		bench.run();
	}
	
	public ExecuteTPCHBenchmarkMYSQL(int numberoftimes) {
		super(numberoftimes);
	}

	@Override
	protected void prepare() {
		this.measurement = XDBExecuteTimeMeasurement.getXDBExecuteTimeMeasurement("mysqltimes");
		this.conn = new SimpleMYSQLConnection("jdbc:mysql://127.0.0.1:3306/tpch_s01");
		this.conn.openConnection("root", "");
	}

	@Override
	protected void execute(int numberoftimes) {
		for(int i = 0; i < numberoftimes; i++){
			try{
				this.executeQ1();
				this.executeQ3();
				this.executeQ5();
				this.executeQ6();
				this.executeQ10();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}

	@Override
	protected void cleanup() {
		this.conn.close();
	}
	
	private void executeStatement(String sqlstatement) throws SQLException{
		boolean value = this.conn.executeQuery(sqlstatement);
		if (!value) throw new SQLException("Bad query");
	}
	
	private void executeQ1() throws SQLException{
		String 	q1 = "select "+
				    "l_returnflag, "+
				    "l_linestatus, "+
				    "sum(l_quantity) as sum_qty, "+
				    "sum(l_extendedprice) as sum_base_price, "+
				    "sum(l_extendedprice * (1 - l_discount)) as sum_disc_price, "+
				    "sum(l_extendedprice * (1 - l_discount) * (1 + l_tax)) as sum_charge, "+
				    "avg(l_quantity) as avg_qty, "+
				    "avg(l_extendedprice) as avg_price, "+
				    "avg(l_discount) as avg_disc, "+
				    "count(*) as count_order "+
				    "from "+
				    "lineitem "+
				    "where "+
				    	"l_shipdate <= date '1998-12-01' "+
				    "group by "+
				    "l_returnflag, "+
				    "l_linestatus "+
				    "order by "+
				    "l_returnflag, "+
				    "l_linestatus;";
		this.measurement.start("q1");
		this.executeStatement(q1);
		
		this.measurement.stop("q1");
	}
	
	private void executeQ3() throws SQLException{
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
		
		this.measurement.start("q3");
		this.executeStatement(q3);
		
		this.measurement.stop("q3");
	}
	
	
	private void executeQ5() throws SQLException{
		
		String 	q5 = "Select "+
		"n_name, "+
		"sum(l_extendedprice * (1-l_discount)) as revenue "+
		"from "+
		"customer, "+
		"orders, "+
		"lineitem, "+
		"supplier, "+
		"nation, "+
		"region "+
		"where c_custkey = o_custkey "+
		"and l_orderkey = o_orderkey "+
		"and l_suppkey = s_suppkey "+
		"and c_nationkey = s_nationkey "+
		"and s_nationkey = n_nationkey "+
		"and n_regionkey = r_regionkey "+
		"and r_name = 'ASIA' "+
		"and o_orderdate > DATE('1994-01-01 00:00:00') "+
		"and o_orderdate < DATE('1995-01-01 00:00:00') "+
		"group by n_name; ";
		
		this.measurement.start("q5");
		this.executeStatement(q5);
		
		this.measurement.stop("q5");
	}
	
	private void executeQ6() throws SQLException{
		String 	q6 = "select "+
					 "sum(l_extendedprice * l_discount) as revenue "+
					 "from "+
					 "lineitem "+
					 "where "+
					 "l_shipdate >= date '1994-01-01' "+
					 "and l_shipdate < date '1994-01-01' + interval '1' year "+
					 "and l_discount between 0.0 and 0.7 "+
					 "and l_quantity < 24;";
		this.measurement.start("q6");
		this.executeStatement(q6);
		
		this.measurement.stop("q6");
	}
	
	
	private void executeQ10() throws SQLException{
		
		String q10 = "Select "+
		"n_name, " +
		"sum(l_extendedprice * (1-l_discount)) as revenue " +
		"from  "+
		"customer, "+
		"orders, "+
		"lineitem, "+
		"supplier, "+
		"nation, "+
		"region "+
		"where c_custkey = o_custkey "+
		"and l_orderkey = o_orderkey "+
		"and l_suppkey = s_suppkey "+
		"and c_nationkey = s_nationkey "+
		"and s_nationkey = n_nationkey "+
		"and n_regionkey = r_regionkey "+
		"and r_name = 'ASIA' "+
		"and o_orderdate > DATE('1994-01-01 00:00:00') "+
		"and o_orderdate < DATE('1995-01-01 00:00:00') "+
		"group by n_name; ";
		this.measurement.start("q10");
		this.executeStatement(q10);
		
		this.measurement.stop("q10");
	} 
}