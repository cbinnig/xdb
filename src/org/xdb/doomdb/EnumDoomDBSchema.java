package org.xdb.doomdb;

import java.util.HashMap;
import java.util.Map;

public enum EnumDoomDBSchema {
	TPCH_2PARTS("TPCH (2 Parts)");

	String tpchQ5 = "Select n_nationkey, "
			+ "sum(l_extendedprice * (1-l_discount)) as revenue, "
			+ "avg(l_extendedprice * (1-l_discount)) as avgrevenue "
			+ "from customer, orders, lineitem, supplier, nation, region "
			+ "where c_custkey = o_custkey " + "and l_orderkey = o_orderkey "
			+ "and l_suppkey = s_suppkey  " + "and n_nationkey = s_nationkey "
			+ "and r_regionkey = n_regionkey "
			+ "and s_nationkey = c_nationkey " + "group by n_nationkey;";

	private String[] tpchCreateDDLs = {
			"CREATE CONNECTION TPCH1 "
					+ "URL 'jdbc:mysql://127.0.0.1/tpch_s01' "
					+ "USER 'xroot' " + "PASSWORD 'xroot' " + "STORE 'XDB';",

			"CREATE CONNECTION TPCH2 "
					+ "URL 'jdbc:mysql://127.0.0.1/tpch_s01' "
					+ "USER 'xroot' " + "PASSWORD 'xroot' " + "STORE 'XDB';",

			"CREATE TABLE LINEITEM ( " + "L_ORDERKEY    		INTEGER,"
					+ "L_PARTKEY     		INTEGER," + "L_SUPPKEY     		INTEGER,"
					+ "L_LINENUMBER  		INTEGER," + "L_QUANTITY    		DECIMAL,"
					+ "L_EXTENDEDPRICE  	DECIMAL," + "L_DISCOUNT    		DECIMAL,"
					+ "L_TAX         		DECIMAL," + "L_RETURNFLAG  		VARCHAR,"
					+ "L_LINESTATUS  		VARCHAR," + "L_SHIPDATE    		DATE,"
					+ "L_COMMITDATE  		DATE," + "L_RECEIPTDATE 		DATE,"
					+ "L_SHIPINSTRUCT 	VARCHAR," + "L_SHIPMODE     	VARCHAR,"
					+ "L_COMMENT      	VARCHAR"
					+ ") PARTIONED BY HASH ( L_ORDERKEY ) ( "
					+ " P0 IN CONNECTION TPCH1," + " P1 IN CONNECTION TPCH2 )",

			"CREATE TABLE ORDERS  ( "
					+ "O_ORDERKEY       INTEGER, "
					+ "O_CUSTKEY        INTEGER, "
					+ "O_ORDERSTATUS    VARCHAR, "
					+ "O_TOTALPRICE     DECIMAL, "
					+ "O_ORDERDATE      DATE, "
					+ "O_ORDERPRIORITY  VARCHAR, "
					+ "O_CLERK          VARCHAR,  "
					+ "O_SHIPPRIORITY   INTEGER, "
					+ "O_COMMENT        VARCHAR"
					+ ") PARTIONED BY RREF ( O_ORDERKEY REFERENCES LINEITEM.L_ORDERKEY ) ",

			"CREATE TABLE  CUSTOMER ( "
					+ "C_CUSTKEY     INTEGER, "
					+ "C_NAME        VARCHAR, "
					+ "C_ADDRESS     VARCHAR, "
					+ "C_NATIONKEY   INTEGER, "
					+ "C_PHONE       VARCHAR, "
					+ "C_ACCTBAL     DECIMAL  , "
					+ "C_MKTSEGMENT  VARCHAR, "
					+ "C_COMMENT     VARCHAR"
					+ ") PARTIONED BY RREF ( C_CUSTKEY REFERENCES ORDERS.O_CUSTKEY )",

			"CREATE TABLE SUPPLIER ( "
					+ "S_SUPPKEY INTEGER, "
					+ "S_NAME VARCHAR, "
					+ "S_ADDRESS VARCHAR, "
					+ "S_NATIONKEY INTEGER, "
					+ "S_PHONE VARCHAR, "
					+ "S_ACCTBAL DECIMAL, "
					+ "S_COMMENT VARCHAR"
					+ ") PARTIONED BY RREF ( S_SUPPKEY REFERENCES LINEITEM.L_SUPPKEY )",

			"CREATE TABLE NATION (  " + "N_NATIONKEY INTEGER, "
					+ "N_NAME VARCHAR," + "N_REGIONKEY INTEGER,"
					+ "N_COMMENT VARCHAR"
					+ ") REPLICATED IN CONNECTION TPCH1, TPCH2;",

			"CREATE TABLE REGION ( " + "R_REGIONKEY INTEGER,"
					+ "R_NAME VARCHAR," + "R_COMMENT VARCHAR"
					+ ") REPLICATED IN CONNECTION TPCH1, TPCH2;" };

	private String schemaName;
	private Map<Integer, String> queries = new HashMap<Integer, String>();

	private EnumDoomDBSchema(String schemaName) {
		this.schemaName = schemaName;
		this.queries.put(5, this.tpchQ5);
	}

	public String[] getCreateDDL() {
		switch (this) {
		case TPCH_2PARTS:
			return this.tpchCreateDDLs;
		default:
			return null;
		}
	}

	public int getPartitions() {
		switch (this) {
		case TPCH_2PARTS:
			return 2;
		default:
			return 0;
		}
	}

	public String getQuery(int i){
		if(this.queries.containsKey(i))
			return this.queries.get(i);
		
		throw new RuntimeException("Query '"+i+"' not supported!");
	}
	
	public static EnumDoomDBSchema enumOf(String schemaName) {
		for (EnumDoomDBSchema schema : EnumDoomDBSchema.values()) {
			if (schema.schemaName.equals(schemaName))
				return schema;
		}
		throw new RuntimeException("Schema '"+schemaName+"' not supported!");
	}
}
