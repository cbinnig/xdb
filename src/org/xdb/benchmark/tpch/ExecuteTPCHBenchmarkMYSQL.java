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
