package org.xdb.doomdb;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import org.xdb.Config;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

public enum EnumDoomDBSchema {
	TPCH_SF01_1PART("TPCH_SF01_1P", "tpch_s01"),
	TPCH_SF01_2PARTS("TPCH_SF01_2P", "tpch_s01"),
	TPCH_SF01_10PARTS("TPCH_SF01_10P", "tpch_s01"),
	TPCH_SF10_10PARTS("TPCH_SF10_10P", "tpch_s01"),	// TODO: database name should be fixed
	TPCH_SF100_10PARTS("TPCH_SF100_10P", "tpch_s01");	// TODO: database name should be fixed
	
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

	private static String tpchQ5 = "Select n_name, "
			+ "sum(l_extendedprice * (1-l_discount)) as revenue "
			+ "from customer, orders, lineitem, supplier, nation, region "
			+ "where r_regionkey = n_regionkey "
			+ "and n_nationkey = c_nationkey " + "and c_custkey = o_custkey "
			+ "and l_orderkey = o_orderkey " + "and l_suppkey = s_suppkey "
			+ "and s_nationkey = c_nationkey " + "and r_name = 'ASIA' "
			+ "and o_orderdate > date '1994-01-01' "
			+ "and o_orderdate < date '1995-01-01' "
			+ "group by n_nationkey, n_name;";

	private static String CONNURL = "CONNURL";
	private static String CONNAME = "CONNAME";
	private static String HASHCONNS = "HASHCONNS";
	private static String REPCONNS = "REPONNS";

	private static StringTemplate xdbConnDDL = new StringTemplate(
			"CREATE CONNECTION <" + CONNAME + "> " + "URL 'jdbc:mysql://<"
					+ CONNURL + ">' " + "USER '" + Config.COMPUTE_DB_USER
					+ "' " + "PASSWORD '" + Config.COMPUTE_DB_PASSWD + "' "
					+ "STORE 'XDB';");

	private static StringTemplate[] tpchHASHDDLs = { new StringTemplate(
			"CREATE TABLE LINEITEM ( " + "L_ORDERKEY    		INTEGER,"
					+ "L_PARTKEY     		INTEGER," + "L_SUPPKEY     		INTEGER,"
					+ "L_LINENUMBER  		INTEGER," + "L_QUANTITY    		DECIMAL,"
					+ "L_EXTENDEDPRICE  	DECIMAL," + "L_DISCOUNT    		DECIMAL,"
					+ "L_TAX         		DECIMAL," + "L_RETURNFLAG  		VARCHAR,"
					+ "L_LINESTATUS  		VARCHAR," + "L_SHIPDATE    		DATE,"
					+ "L_COMMITDATE  		DATE," + "L_RECEIPTDATE 		DATE,"
					+ "L_SHIPINSTRUCT 	VARCHAR," + "L_SHIPMODE     	VARCHAR,"
					+ "L_COMMENT      	VARCHAR"
					+ ") PARTIONED BY HASH ( L_ORDERKEY ) ( " + "<" + HASHCONNS
					+ ">);") };

	private static StringTemplate[] tpchREPDDLs = {
			new StringTemplate("CREATE TABLE NATION (  "
					+ "N_NATIONKEY INTEGER, " + "N_NAME VARCHAR,"
					+ "N_REGIONKEY INTEGER," + "N_COMMENT VARCHAR"
					+ ") REPLICATED IN CONNECTION " + "<" + REPCONNS + ">);"),
			new StringTemplate("CREATE TABLE REGION ( "
					+ "R_REGIONKEY INTEGER," + "R_NAME VARCHAR,"
					+ "R_COMMENT VARCHAR" + ") REPLICATED IN CONNECTION " + "<"
					+ REPCONNS + ">);") };

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
					+ ") PARTIONED BY RREF ( S_SUPPKEY REFERENCES LINEITEM.L_SUPPKEY )" };

	private static Map<Integer, String> QUERIES = new HashMap<Integer, String>();
	private static Map<String, String[]> SCHEMAS = new HashMap<String, String[]>();

	private Map<Integer, Map<Identifier, Boolean>> opMaterializabilityMap = new HashMap<Integer, Map<Identifier, Boolean>>();
	private Map<Integer, Map<Identifier, Double>> runTimeMap = new HashMap<Integer, Map<Identifier, Double>>();
	private Map<Integer, Map<Identifier, Double>> matTimeMap = new HashMap<Integer, Map<Identifier, Double>>();

	public void loadStatFromFile(int queryID) {

		String runtimeFileName = "./stat/q" + Integer.toString(queryID) + "_"
				+ this.schemaName + "_runtimes.conf";
		String mattimeFileName = "./stat/q" + Integer.toString(queryID) + "_"
				+ this.schemaName + "_mattimes.conf";
		String materializabilityFileName = "./stat/q"
				+ Integer.toString(queryID) + "_" + this.schemaName
				+ "_materializability.conf";

		Properties props;
		props = new Properties();

		Map<Identifier, Boolean> materializability = new HashMap<Identifier, Boolean>();
		Map<Identifier, Double> runtimes = new HashMap<Identifier, Double>();
		Map<Identifier, Double> mattimes = new HashMap<Identifier, Double>();

		try {
			// Reading the materializability of the operators
			props.load(new FileReader(materializabilityFileName));
			for (Object key : props.keySet()) {
				String opId = key.toString();
				materializability.put(new Identifier(Integer.parseInt(opId)),
						Boolean.parseBoolean(props.getProperty(opId).trim()));
			}

			// Reading the runtime of the operators
			props.clear();
			props.load(new FileReader(runtimeFileName));
			for (Object key : props.keySet()) {
				String opId = key.toString();
				runtimes.put(new Identifier(Integer.parseInt(opId)),
						Double.parseDouble(props.getProperty(opId).trim()));
			}

			// Reading the materialization time of the operators
			props.clear();
			props.load(new FileReader(mattimeFileName));
			for (Object key : props.keySet()) {
				String opId = key.toString();
				mattimes.put(new Identifier(Integer.parseInt(opId)),
						Double.parseDouble(props.getProperty(opId).trim()));
			}
			int queryId = 5;

			opMaterializabilityMap.put(queryId, materializability);
			runTimeMap.put(queryId, runtimes);
			matTimeMap.put(queryId, mattimes);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	private static Vector<String> createTPCHXParts(int parts,
			EnumDoomDBSchema schema) {
		String[] conns = Config.DOOMDB_COMPUTE_NODES.split(",");

		Vector<String> tpchXParts = new Vector<String>();

		String connPrefix = "TPCH";
		String partPrefix = AbstractToken.PARTNAME;
		StringBuilder repConns = new StringBuilder();
		StringBuilder hashConns = new StringBuilder();

		Map<String, String> args = new HashMap<String, String>();
		for (int i = 0; i < parts; ++i) {
			String connName = connPrefix + i;
			String partName = partPrefix + i;

			// add connection DDLs to tpchXParts
			args.put(CONNAME, connName);
			args.put(CONNURL, conns[i % Config.DOOMDB_CLUSTER_SIZE] + "/"
					+ schema.dbName);
			tpchXParts.add(xdbConnDDL.toString(args));

			// repConns and refConns
			repConns.append(connName);

			hashConns.append(partName);
			hashConns.append(AbstractToken.BLANK);
			hashConns.append(AbstractToken.IN);
			hashConns.append(AbstractToken.BLANK);
			hashConns.append(AbstractToken.CONNECTION);
			hashConns.append(AbstractToken.BLANK);
			hashConns.append(connName);

			if (i < parts - 1) {
				repConns.append(AbstractToken.COMMA);
				repConns.append(AbstractToken.BLANK);

				hashConns.append(AbstractToken.COMMA);
				hashConns.append(AbstractToken.BLANK);
			}
		}

		// add HASH tables DDLs to tpchXParts
		args.clear();
		args.put(HASHCONNS, hashConns.toString());
		for (StringTemplate tpchHASHDDL : tpchHASHDDLs) {
			tpchXParts.add(tpchHASHDDL.toString(args));
		}

		// add REP tables DDLs to tpchXParts
		args.clear();
		args.put(REPCONNS, repConns.toString());
		for (StringTemplate tpchREPDDL : tpchREPDDLs) {
			tpchXParts.add(tpchREPDDL.toString(args));
		}

		// add REF tables DDLs to tpchXParts
		for (String tpchREFDDL : tpchREFDDLs) {
			tpchXParts.add(tpchREFDDL);
		}

		return tpchXParts;
	}

	static {
		Vector<String> tpch1Parts = createTPCHXParts(1, TPCH_SF01_1PART);
		SCHEMAS.put(TPCH_SF01_1PART.schemaName,
				tpch1Parts.toArray(new String[tpch1Parts.size()]));

		Vector<String> tpch_sf01_2parts = createTPCHXParts(2, TPCH_SF01_2PARTS);
		SCHEMAS.put(TPCH_SF01_2PARTS.schemaName,
				tpch_sf01_2parts.toArray(new String[tpch_sf01_2parts.size()]));

		Vector<String> tpch_sf01_10parts = createTPCHXParts(10, TPCH_SF01_10PARTS);
		SCHEMAS.put(TPCH_SF01_10PARTS.schemaName,
				tpch_sf01_10parts.toArray(new String[tpch_sf01_10parts.size()]));
		
		Vector<String> tpch_sf10_10parts = createTPCHXParts(10, TPCH_SF10_10PARTS);
		SCHEMAS.put(TPCH_SF10_10PARTS.schemaName,
				tpch_sf10_10parts.toArray(new String[tpch_sf10_10parts.size()]));

		
		Vector<String> tpch_sf100_10parts = createTPCHXParts(10, TPCH_SF100_10PARTS);
		SCHEMAS.put(TPCH_SF100_10PARTS.schemaName,
				tpch_sf100_10parts.toArray(new String[tpch_sf100_10parts.size()]));


		QUERIES.put(1, tpchQ1);
		QUERIES.put(5, tpchQ5);
	}

	private String schemaName;
	private String dbName;

	private EnumDoomDBSchema(String schemaName, String dbName) {
		this.schemaName = schemaName;
		this.dbName = dbName;
	}

	public String[] getDDL() {
		return SCHEMAS.get(schemaName);
	}

	public int getPartitions() {
		switch (this) {
		case TPCH_SF01_1PART:
			return 1;
		case TPCH_SF01_2PARTS:
			return 2;
		case TPCH_SF01_10PARTS:
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

	public Map<Identifier, Double> getQueryRunTimesStat(int queryId) {
		return runTimeMap.get(queryId);
	}

	public Map<Identifier, Double> getQueryMatTimesStat(int queryId) {
		return matTimeMap.get(queryId);
	}

	public List<Identifier> getNonMaterializableOps(int queryNum) {
		List<Identifier> nonMatOps = new ArrayList<Identifier>();
		if (this.opMaterializabilityMap.containsKey(queryNum)) {
			for (Identifier id : this.opMaterializabilityMap.get(queryNum)
					.keySet())
				if (opMaterializabilityMap.get(queryNum).get(id).equals(false))
					nonMatOps.add(id);
		}
		return nonMatOps;
	}
}
