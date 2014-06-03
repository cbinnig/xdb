package org.xdb;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.logging.EnumXDBComponents;
import org.xdb.logging.XDBLog;
import org.xdb.tracker.scheduler.EnumResourceScheduler;
import org.xdb.utils.Identifier;

/**
 * XDB central configuration
 * 
 * @author cbinnig
 * 
 */
public class Config implements Serializable {

	private static final long serialVersionUID = -3628108255115350359L;

	// General
	public static String PLATTFORM = "MAC";
	public static String LOCALHOST = "127.0.0.1";
	public static String CONFIG_FILE = "./config/xdb.conf";

	// Monitoring
	public static int MASTERTRACKER_MONITOR_INTERVAL = 2000;
	public static int QUERYTRACKER_MONITOR_INTERVAL = 2000;
	public static boolean MASTERTRACKER_MONITOR_ACTIVATED = false;
	public static boolean QUERYTRACKER_MONITOR_ACTIVATED = false;
	public static int QUERYTRACKER_MONITOR_ATTEMPTS = 10;

	// Compute Server
	public static String COMPUTE_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static String COMPUTE_DB_URL = "jdbc:mysql://127.0.0.1/";
	public static String COMPUTE_DB_NAME = "xdb_tmp";
	public static String COMPUTE_DB_USER = "xroot";
	public static String COMPUTE_DB_PASSWD = "xroot";
	public static final Identifier COMPUTE_NOOP_ID = new Identifier("NOOP");

	public static int COMPUTE_PORT = 55700;
	public static int COMPUTE_MAX_FETCHSIZE = Integer.MAX_VALUE;
	public static boolean COMPUTE_CLEAN_PLAN = true;
	public static boolean COMPUTE_INTERMEDIATE_KEYS = true;
	public static int COMPUTE_THINKTIME = 1000;
	public static String COMPUTE_ENGINE = "MEMORY";

	// Compile Server
	public static String METADATA_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static String METADATA_DB_URL = "jdbc:mysql://127.0.0.1/xdb_schema";
	public static String METADATA_SCHEMA = "xdb_schema";
	public static String METADATA_USER = "xroot";
	public static String METADATA_PASSWORD = "xroot";
	public static String METADATA_OID_NAME = "OID";
	public static int METADATA_TEMP_OID = -1;

	public static int COMPILE_PORT = 55500;
	public static String COMPILE_URL = "127.0.0.1";
	public static String COMPILE_DEFAULT_SCHEMA = "PUBLIC";

	public static boolean COMPILE_FT_ACTIVE = true;
	public static String COMPILE_FT_MODE = "naive";
	public static int COMPILE_FT_BENCHMARK_ROWS_NUMBER = 10;
	public static int COMPILE_FT_BENCHMARK_COLUMNS_NUMBER = 2;
	public static double COMPILE_FT_PIPELINE_CNST = 0.95; 
	public static double COMPILE_FT_MERGING_SMALLOPS_THRESHOLD = 0.98;

	// Optimizer
	public static BitSet OPTIMIZER_ACTIVE_RULES_FUNCTION = new BitSet();
	public static BitSet OPTIMIZER_ACTIVE_RULES_SELECT = new BitSet();
	static {
		OPTIMIZER_ACTIVE_RULES_FUNCTION.set(0, false); // push selections
		OPTIMIZER_ACTIVE_RULES_FUNCTION.set(1, true); // combine selections
		OPTIMIZER_ACTIVE_RULES_SELECT.set(0, false); // push selections
		OPTIMIZER_ACTIVE_RULES_SELECT.set(1, true); // combine selections
	}

	// Master Tracker Server
	public static int MASTERTRACKER_PORT = 55501;
	public static String MASTERTRACKER_URL = "127.0.0.1";

	// Query Tracker Server
	public static int QUERYTRACKER_PORT = 55600;
	public static EnumResourceScheduler QUERYTRACKER_SCHEDULER = EnumResourceScheduler.WISHLIST_AWARE;

	// Query Tracker Server: Code generation
	public static boolean CODEGEN_OPTIMIZE = true;

	// Logging
	public static Boolean LOGGING_ENABLED = true;
	public static String LOG_FILE = "./log/xdb_";
	public static Level LOG_LEVEL = Level.SEVERE;
	public static boolean LOG_EXECUTION_TIME = false;

	// Tracing
	public static String DOT_EXE = "dot";
	public static String DOT_TRACE_PATH = "./log/";
	public static boolean TRACE_PARALLEL_PLAN = false;
	public static boolean TRACE_COMPILE_PLAN = false;
	public static boolean TRACE_COMPILE_PLAN_HEADER = true;
	public static boolean TRACE_COMPILE_PLAN_FOOTER = true;
	public static boolean TRACE_COMPILE_PLAN_HEADER_PARENTCHILD = false;
	public static boolean TRACE_COMPILE_PLAN_HEADER_RESULT = true;
	public static boolean TRACE_COMPILE_PLAN_HEADER_RESULT_SCHEMA = false;
	public static boolean TRACE_COMPILE_PLAN_HEADER_RESULT_PARTITIONING = true;

	public static boolean TRACE_TRACKER_PLAN_HEADER = false;
	public static boolean TRACE_TRACKER_PLAN_FOOTER = false;
	public static boolean TRACE_TRACKER_SHORT_CAPTIONS = true;
	public static int TRACE_TRACKER_SHORT_CAPTIONS_CHARS = 50;

	public static boolean TRACE_OPTIMIZED_PLAN = false;
	public static boolean TRACE_CODEGEN_PLAN = false;
	public static boolean TRACE_TRACKER_PLAN = false;
	public static boolean TRACE_EXECUTE_PLAN = false;

	// General Testing
	public static boolean TEST_RUN_LOCAL = true;
	public static String TEST_DB_NAME = "tpch_s01";
	public static int TEST_NODE_COUNT = 2;
	public static int TEST_PARTS_PER_NODE = 1;

	// DoomDB
	public static boolean ACTIVATE_FAILURE_SIMULATOR = false;
	public static String DOOMDB_CONFIG_FILE = "./config/doomdb.conf";
	public static boolean DOOMDB_MTBF_UPDATE = false; // in s
	public static int DOOMDB_MTBF = 20; // in s
	public static double DOOMDB_MTBF_STDEV = 2; // in s
	public static int DOOMDB_MTTR = 5; // in s
	public static int DOOMDB_NUM_FAILUERS = 100; // num of failures
	public static int DOOMDB_CLUSTER_SIZE = 2; // in s
	public static String DOOMDB_COMPUTE_NODES = "127.0.0.1,locahost";
	public static String DOOMDB_TPCH_S01 = "tpch_s01";
	public static String DOOMDB_TPCH_S1 = "tpch_s1";
	public static String DOOMDB_TPCH_S10 = "tpch_s10";
	public static String DOOMDB_TPCH_S100 = "tpch_s100";
	
	// Logging
	private static Logger logger = XDBLog.getLogger(EnumXDBComponents.CONFIG);

	// Load xdb.conf
	static {
		loadXDB();
		loadDoom();
	}

	/**
	 * Change a property in file
	 * 
	 * @param key
	 * @param value
	 */
	private static synchronized void write(String key, String value, String file) {
		Properties props;
		props = new Properties();
		try {
			// Integer
			props.load(new FileReader(file));
			props.setProperty(key, value);
			props.store(new FileWriter(file), "");

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static synchronized void write(String key, String value) {
		write(key, value, CONFIG_FILE);
		loadXDB();
	}

	public static synchronized void writeDoom(String key, String value) {
		write(key, value, DOOMDB_CONFIG_FILE);
		loadDoom();
	}

	/**
	 * Load user configuration from file and override default values
	 */
	private static void loadDoom() {
		String[] intProperties = { "DOOMDB_MTBF",
				"DOOMDB_MTTR", "DOOMDB_CLUSTER_SIZE", "DOOMDB_NUM_FAILUERS" };

		String[] stringProperties = { "DOOMDB_COMPUTE_NODES", "DOOMDB_TPCH_S01",
				"DOOMDB_TPCH_S1","DOOMDB_TPCH_S10","DOOMDB_TPCH_S100"};

		String[] boolProperties = { "DOOMDB_MTBF_LOAD" , "ACTIVATE_FAILURE_SIMULATOR"};
		
		String[] doubleProperties = {"DOOMDB_MTBF_STDEV"};

		Properties props;
		props = new Properties();
		try {
			// Integer
			props.load(new FileReader(DOOMDB_CONFIG_FILE));
			for (String intProperty : intProperties) {
				if (props.containsKey(intProperty)) {
					Config.class.getField(intProperty).setInt(
							null,
							Integer.parseInt(props.get(intProperty.trim())
									.toString().trim()));
				}
			}

			// Boolean
			for (String boolProperty : boolProperties) {
				if (props.containsKey(boolProperty)) {
					Config.class.getField(boolProperty).setBoolean(
							null,
							Boolean.parseBoolean(props.get(boolProperty.trim())
									.toString()));
				}
			}

			// String
			for (String stringProperty : stringProperties) {
				if (props.containsKey(stringProperty)) {
					Config.class.getField(stringProperty).set(null,
							props.getProperty(stringProperty.trim()));
				}
			}
			
			// Double
			props.load(new FileReader(DOOMDB_CONFIG_FILE));
			for (String doubleProperty : doubleProperties) {
				if (props.containsKey(doubleProperty)) {
						Config.class.getField(doubleProperty).setDouble(
							null,
							Double.parseDouble(props.get(doubleProperty.trim())
								.toString().trim()));
				}
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		logger.log(Level.INFO, ":----------------------------------------:");
		logger.log(Level.INFO, "  Loading DoomDB configuration:");
		logger.log(Level.INFO, "  " + props.toString());
		logger.log(Level.INFO, ":----------------------------------------:");
		logger.log(Level.INFO, "");
	}

	/**
	 * Load user configuration from file and override default values
	 */
	private static void loadXDB() {
		String[] intProperties = { "COMPUTE_MAX_FETCHSIZE", "COMPUTE_PORT",
				"COMPILE_PORT", "MASTERTRACKER_PORT", "QUERYTRACKER_PORT",
				"QUERYTRACKER_MONITOR_ATTEMPTS",
				"QUERYTRACKER_MONITOR_INTERVAL", "TEST_NODE_COUNT",
				"TEST_FT_NUMBER_OF_FAILURES", "TEST_FT_NUMBER_OF_RUNS",
				"TEST_PARTS_PER_NODE", "TEST_FT_RECORDS_LIMIT",
				"COMPILE_FT_BENCHMARK_ROWS_NUMBER",
				"COMPILE_FT_BENCHMARK_COLUMNS_NUMBER" };

		String[] stringProperties = { "PLATTFORM", "COMPILE_URL", "MASTERTRACKER_URL",
				"TEST_DB_NAME", "COMPUTE_ENGINE", "SHOOTED_COMPUTE_NODES",
				"COMPILE_FT_MODE", "DOT_EXE" };

		String[] boolProperties = { "LOGGING_ENABLED", "COMPUTE_CLEAN_PLAN",
				"TRACE_PARALLEL_PLAN", "TRACE_COMPILE_PLAN",
				"TRACE_COMPILE_PLAN_HEADER",
				"TRACE_COMPILE_PLAN_HEADER_RESULT",
				"TRACE_COMPILE_PLAN_HEADER_RESULT_PARTITIONING",
				"TRACE_COMPILE_PLAN_HEADER_RESULT_SCHEMA",
				"TRACE_COMPILE_PLAN_FOOTER", "TRACE_TRACKER_PLAN_HEADER",
				"TRACE_TRACKER_PLAN_FOOTER", "TRACE_TRACKER_PLAN_CAPTIONS",
				"TRACE_OPTIMIZED_PLAN", "TRACE_TRACKER_PLAN",
				"TRACE_EXECUTE_PLAN", "TRACE_CODEGEN_PLAN",
				"LOG_EXECUTION_TIME", "CODEGEN_OPTIMIZE", "TEST_RUN_LOCAL",
				"QUERYTRACKER_MONITOR_ACTIVATED",
				"MASTERTRACKER_MONITOR_ACTIVATED", "TEST_FT_CHECKPOINTING",
				"COMPILE_FT_ACTIVE", "COMPUTE_INTERMEDIATE_KEYS"};

		Properties props;
		props = new Properties();
		try {
			// Integer
			props.load(new FileReader(CONFIG_FILE));
			for (String intProperty : intProperties) {
				if (props.containsKey(intProperty)) {
					Config.class.getField(intProperty).setInt(
							null,
							Integer.parseInt(props.get(intProperty.trim())
									.toString().trim()));
				}
			}

			// Boolean
			for (String boolProperty : boolProperties) {
				if (props.containsKey(boolProperty)) {
					Config.class.getField(boolProperty).setBoolean(
							null,
							Boolean.parseBoolean(props.get(boolProperty.trim())
									.toString()));
				}
			}

			// String
			for (String stringProperty : stringProperties) {
				if (props.containsKey(stringProperty)) {
					Config.class.getField(stringProperty).set(null,
							props.getProperty(stringProperty.trim()));
				}
			}

			// Others (LogLevel, Bitmaps, Enums)
			if (props.containsKey("LOG_LEVEL")) {
				LOG_LEVEL = Level.parse(props.getProperty("LOG_LEVEL").trim());
			}

			if (props.containsKey("OPTIMIZER_ACTIVE_RULES_FUNCTION")) {
				String ruleBitSet = props.getProperty(
						"OPTIMIZER_ACTIVE_RULES_FUNCTION").trim();
				int i = 0;
				for (char bit : ruleBitSet.toCharArray()) {
					OPTIMIZER_ACTIVE_RULES_FUNCTION.set(i++,
							(bit == '1') ? true : false);
				}
			}
			if (props.containsKey("OPTIMIZER_ACTIVE_RULES_SELECT")) {
				String ruleBitSet = props.getProperty(
						"OPTIMIZER_ACTIVE_RULES_SELECT").trim();
				int i = 0;
				for (char bit : ruleBitSet.toCharArray()) {
					OPTIMIZER_ACTIVE_RULES_SELECT.set(i++, (bit == '1') ? true
							: false);
				}
			}
			if (props.containsKey("QUERYTRACKER_SCHEDULER")) {
				String qtScheduler = props
						.getProperty("QUERYTRACKER_SCHEDULER").trim();
				EnumResourceScheduler tempQtScheduler = EnumResourceScheduler
						.valueOf(qtScheduler);
				if (tempQtScheduler != null)
					QUERYTRACKER_SCHEDULER = tempQtScheduler;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		logger.log(Level.INFO, ":----------------------------------------:");
		logger.log(Level.INFO, "   Loading XDB configuration:");
		logger.log(Level.INFO, "  " + props.toString());
		logger.log(Level.INFO, ":----------------------------------------:");
		logger.log(Level.INFO, "");
	}
}
