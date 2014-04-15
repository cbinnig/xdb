package org.xdb.doomdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.xdb.Config;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

public enum EnumDoomDBSchema {
	TPCH_1PART("TPCH (1 Part)"),
	TPCH_2PARTS("TPCH (2 Parts)"),
	TPCH_10PARTS("TPCH (10 Parts)");

	private static String tpchQ1 = "select	l_returnflag,	"
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
	
	private static String tpchQ5 = "Select n_name, " +
			"sum(l_extendedprice * (1-l_discount)) as revenue " +
			"from customer, orders, lineitem, supplier, nation, region " +
			"where r_regionkey = n_regionkey " +
			"and n_nationkey = c_nationkey " +
			"and c_custkey = o_custkey " +
			"and l_orderkey = o_orderkey " +
			"and l_suppkey = s_suppkey " +
			"and s_nationkey = c_nationkey " +
			"and r_name = 'ASIA' " +
			"and o_orderdate > date '1994-01-01' "+
			"and o_orderdate < date '1995-01-01' "+
			"group by n_nationkey, n_name;";

	private static String CONNURL = "CONNURL";
	private static String CONNAME = "CONNAME";
	private static String HASHCONNS = "HASHCONNS";
	private static String REPCONNS = "REPONNS";
	
	private static StringTemplate xdbConnDDL = new StringTemplate(
			"CREATE CONNECTION <" + CONNAME + "> "
					+ "URL 'jdbc:mysql://<"+CONNURL+">/"+Config.TEST_DB_NAME+"' "
					+ "USER '" + Config.COMPUTE_DB_USER + "' " + "PASSWORD '"
					+ Config.COMPUTE_DB_PASSWD + "' " + "STORE 'XDB';");

	private static StringTemplate[] tpchHASHDDLs = {
		new StringTemplate(
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
					+ "<"+HASHCONNS+">);")
	};
	
	private static StringTemplate[] tpchREPDDLs = {
		new StringTemplate(
			"CREATE TABLE NATION (  " + "N_NATIONKEY INTEGER, "
					+ "N_NAME VARCHAR," + "N_REGIONKEY INTEGER,"
					+ "N_COMMENT VARCHAR"
					+ ") REPLICATED IN CONNECTION "
					+ "<"+REPCONNS+">);"),
		new StringTemplate("CREATE TABLE REGION ( " + "R_REGIONKEY INTEGER,"
					+ "R_NAME VARCHAR," + "R_COMMENT VARCHAR"
					+ ") REPLICATED IN CONNECTION "
					+ "<"+REPCONNS+">);")
	};
	
	private static String[] tpchREFDDLs = {

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
				+ ") PARTIONED BY RREF ( S_SUPPKEY REFERENCES LINEITEM.L_SUPPKEY )"
	};
	
	private static Map<Integer, String> QUERIES = new HashMap<Integer, String>();
	private static Map<String, String[]> SCHEMAS = new HashMap<String, String[]>(); 
	
	private Map<Identifier, Double> q5RuntimesStat = new HashMap<Identifier, Double>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L; 
		{  put(new Identifier("13"),0.0); put(new Identifier("3"),0.0); 
		   put(new Identifier("5"),0.18); put(new Identifier("7"), 0.68); 
		   put(new Identifier("9"),0.98); put(new Identifier("17"), 0.69); 
				
	}};
	
	private Map<Identifier, Double> q5MattimesStat = new HashMap<Identifier, Double>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L; 
		{  put(new Identifier("13"),0.0); put(new Identifier("3"),0.0); 
		   put(new Identifier("5"),0.27); put(new Identifier("7"), 0.42); 
		   put(new Identifier("9"),1.67); put(new Identifier("17"), 0.0); 
	}}; 
	
	private List<Identifier> q5NonMaterializableOps = new ArrayList<Identifier>(){

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L; 
		{
			add(new Identifier("14")); add(new Identifier("11"));   
			add(new Identifier("12")); add(new Identifier("18")); 
			add(new Identifier("16")); 
		}
		
	}; 
	
	private Map<Integer, Map<Identifier, Double>> statsRunTimeMap = new HashMap<Integer, Map<Identifier, Double>>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L; 
		{  
			put(5, q5RuntimesStat);
	    }
	};  
	
	private Map<Integer, Map<Identifier, Double>> statsMatTimeMap = new HashMap<Integer, Map<Identifier, Double>>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L; 
		{  
			put(5, q5MattimesStat);
	    }
	};  
	
	private Map<Integer, List<Identifier>> nonMatOpsMap = new HashMap<Integer, List<Identifier>>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L; 
		{  
			put(5, q5NonMaterializableOps);
	    }
	}; 
	
	private static Vector<String> createTPCHXParts(int parts){
		String[] conns = Config.DOOMDB_COMPUTE_NODES.split(",");
		
		Vector<String> tpchXParts = new Vector<String>();
		
		String connPrefix = "TPCH";
		String partPrefix = AbstractToken.PARTNAME;
		StringBuilder repConns = new StringBuilder();
		StringBuilder hashConns = new StringBuilder();
		
		Map<String, String> args = new HashMap<String, String>();
		for(int i=0; i<parts; ++i){
			String connName = connPrefix+i;
			String partName = partPrefix+i;
			
			//add connection DDLs to tpchXParts
			args.put(CONNAME, connName);
			args.put(CONNURL, conns[i%Config.DOOMDB_CLUSTER_SIZE]);
			tpchXParts.add(xdbConnDDL.toString(args));
			
			//repConns and refConns
			repConns.append(connName);
		
			hashConns.append(partName);
			hashConns.append(AbstractToken.BLANK);
			hashConns.append(AbstractToken.IN);
			hashConns.append(AbstractToken.BLANK);
			hashConns.append(AbstractToken.CONNECTION);
			hashConns.append(AbstractToken.BLANK);
			hashConns.append(connName);
			
			if(i<parts-1){
				repConns.append(AbstractToken.COMMA);
				repConns.append(AbstractToken.BLANK);
				
				hashConns.append(AbstractToken.COMMA);
				hashConns.append(AbstractToken.BLANK);
			}
		}
		
		//add HASH tables DDLs to tpchXParts
		args.clear();
		args.put(HASHCONNS, hashConns.toString());
		for(StringTemplate tpchHASHDDL: tpchHASHDDLs){
			tpchXParts.add(tpchHASHDDL.toString(args));
		}
		
		//add REP tables DDLs to tpchXParts
		args.clear();
		args.put(REPCONNS, repConns.toString());
		for(StringTemplate tpchREPDDL: tpchREPDDLs){
			tpchXParts.add(tpchREPDDL.toString(args));
		}
		
		//add REF tables DDLs to tpchXParts
		for(String tpchREFDDL: tpchREFDDLs){
			tpchXParts.add(tpchREFDDL);
		}
		
		return tpchXParts;
	}
	
	static {
		Vector<String> tpch1Parts = createTPCHXParts(1);
		SCHEMAS.put(TPCH_1PART.schemaName, tpch1Parts.toArray(new String[tpch1Parts.size()]));
		
		Vector<String> tpch2Parts = createTPCHXParts(2);
		SCHEMAS.put(TPCH_2PARTS.schemaName, tpch2Parts.toArray(new String[tpch2Parts.size()]));
		
		Vector<String> tpch10Parts = createTPCHXParts(10);
		SCHEMAS.put(TPCH_10PARTS.schemaName, tpch10Parts.toArray(new String[tpch10Parts.size()]));
		
		QUERIES.put(1, tpchQ1);
		QUERIES.put(5, tpchQ5);
	}

	private String schemaName;

	private EnumDoomDBSchema(String schemaName) {
		this.schemaName = schemaName;

	}

	public String[] getDDL() {
		return SCHEMAS.get(schemaName);
	}

	public int getPartitions() {
		switch (this) {
		case TPCH_1PART:
			return 1;
		case TPCH_2PARTS:
			return 2;
		case TPCH_10PARTS:
			return 10;
		default:
			return 0;
		}
	}

	public String getQuery(int i) {
		if (QUERIES.containsKey(i))
			return QUERIES.get(i);

		throw new RuntimeException("Query '" + i + "' not supported!");
	}

	public static EnumDoomDBSchema enumOf(String schemaName) {
		for (EnumDoomDBSchema schema : EnumDoomDBSchema.values()) {
			if (schema.schemaName.equals(schemaName))
				return schema;
		}
		throw new RuntimeException("Schema '" + schemaName + "' not supported!");
	}

	/**
	 * @return the q5RuntimesStat
	 */
	public Map<Identifier, Double> getQ5RuntimesStat() {
		return q5RuntimesStat;
	}

	/**
	 * @param q5RuntimesStat the q5RuntimesStat to set
	 */
	public void setQ5RuntimesStat(Map<Identifier, Double> q5RuntimesStat) {
		this.q5RuntimesStat = q5RuntimesStat;
	}

	/**
	 * @return the q5MattimesStat
	 */
	public Map<Identifier, Double> getQ5MattimesStat() {
		return q5MattimesStat;
	}

	/**
	 * @param q5MattimesStat the q5MattimesStat to set
	 */
	public void setQ5MattimesStat(Map<Identifier, Double> q5MattimesStat) {
		this.q5MattimesStat = q5MattimesStat;
	}

	/**
	 * @return the q5NonMaterializableOps
	 */
	public List<Identifier> getQ5NonMaterializableOps() {
		return q5NonMaterializableOps;
	}

	/**
	 * @param q5NonMaterializableOps the q5NonMaterializableOps to set
	 */
	public void setQ5NonMaterializableOps(List<Identifier> q5NonMaterializableOps) {
		this.q5NonMaterializableOps = q5NonMaterializableOps;
	}

	/**
	 * @return the statsRunTimeMap
	 */
	public Map<Integer, Map<Identifier, Double>> getStatsRunTimeMap() {
		return statsRunTimeMap;
	}

	/**
	 * @param statsRunTimeMap the statsRunTimeMap to set
	 */
	public void setStatsRunTimeMap(Map<Integer, Map<Identifier, Double>> statsRunTimeMap) {
		this.statsRunTimeMap = statsRunTimeMap;
	}

	/**
	 * @return the statsMatTimeMap
	 */
	public Map<Integer, Map<Identifier, Double>> getStatsMatTimeMap() {
		return statsMatTimeMap;
	}

	/**
	 * @param statsMatTimeMap the statsMatTimeMap to set
	 */
	public void setStatsMatTimeMap(Map<Integer, Map<Identifier, Double>> statsMatTimeMap) {
		this.statsMatTimeMap = statsMatTimeMap;
	}

	/**
	 * @return the nonMatOpsMap
	 */
	public Map<Integer, List<Identifier>> getNonMatOpsMap() {
		return nonMatOpsMap;
	}

	/**
	 * @param nonMatOpsMap the nonMatOpsMap to set
	 */
	public void setNonMatOpsMap(Map<Integer, List<Identifier>> nonMatOpsMap) {
		this.nonMatOpsMap = nonMatOpsMap;
	}
}
