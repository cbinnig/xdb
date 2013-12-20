package org.xdb;

import java.io.FileReader;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Properties;
import java.util.logging.Level;

import org.xdb.tracker.EnumQueryTrackerStrategy;
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
	public static String LOCALHOST = "127.0.0.1";
	public static String CONFIG_FILE = "./config/xdb.conf";

	// Monitoring
	public static int MASTERTRACKER_MONITOR_INTERVAL = 2000;  
	public static int QUERYTRACKER_MONITOR_INTERVAL = 100; 
	public static boolean MASTERTRACKER_MONITOR_ACTIVATED = false;
	public static boolean QUERYTRACKER_MONITOR_ACTIVATED = false;  
	

	// Compute Server
	public static String COMPUTE_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static String COMPUTE_DB_URL = "jdbc:mysql://127.0.0.1/";
	public static String COMPUTE_DB_NAME = "xdb_tmp";
	public static String COMPUTE_DB_USER = "xroot";
	public static String COMPUTE_DB_PASSWD = "xroot";
	public static final Identifier COMPUTE_NOOP_ID = new Identifier("NOOP");
	public static int COMPUTE_PORT = 55700;
	public static int COMPUTE_MAX_FETCHSIZE = Integer.MAX_VALUE;
	public static boolean COMPUTE_CLEAN_RESULTS = true;
	public static boolean COMPUTE_CLEAN_PLAN = true;

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

	// Compile Server: Optimizer
	public static BitSet OPTIMIZER_ACTIVE_RULES_FUNCTION = new BitSet();
	public static BitSet OPTIMIZER_ACTIVE_RULES_SELECT = new BitSet();

	static {
		OPTIMIZER_ACTIVE_RULES_FUNCTION.set(0, false); //push selections
		OPTIMIZER_ACTIVE_RULES_FUNCTION.set(1, true); //combine selections
		OPTIMIZER_ACTIVE_RULES_SELECT.set(0, false); //push selections
		OPTIMIZER_ACTIVE_RULES_SELECT.set(1, true); //combine selections
	}

	// Master Tracker Server
	public static int MASTERTRACKER_PORT = 55501;
	public static String MASTERTRACKER_URL = "127.0.0.1";

	// Query Tracker Server
	public static int QUERYTRACKER_PORT = 55600;
	public static EnumResourceScheduler QUERYTRACKER_SCHEDULER = EnumResourceScheduler.SIMPLE;
	public static EnumQueryTrackerStrategy QUERYTRACKER_STRATEGY = EnumQueryTrackerStrategy.PERFORMANCE;

	// Query Tracker Server: Code generation
	public static boolean CODEGEN_OPTIMIZE = true;

	// Logging
	public static String LOG_FILE = "./log/xdb.log";
	public static Level LOG_LEVEL = Level.SEVERE;
	public static boolean LOG_EXECUTION_TIME = false;

	// Tracing
	public static String DOT_EXE = "dot";
	public static String DOT_TRACE_PATH = "./log/";
	public static boolean TRACE_PARALLEL_PLAN = false;
	public static boolean TRACE_COMPILE_PLAN = false;
	public static boolean TRACE_COMPILE_PLAN_HEADER = true;
	public static boolean TRACE_COMPILE_PLAN_FOOTER = true;
	public static boolean TRACE_COMPILE_PLAN_PARENTCHILD = false;
	public static boolean TRACE_COMPILE_PLAN_PARTITIONING = false;
	public static boolean TRACE_COMPILE_PLAN_RESULTS = true;
	public static boolean TRACE_OPTIMIZED_PLAN = false;
	public static boolean TRACE_CODEGEN_PLAN = false;
	public static boolean TRACE_TRACKER_PLAN = false;
	public static boolean TRACE_EXECUTE_PLAN = false;

	// General Testing
	public static boolean TEST_RUN_LOCAL = true;
	public static String TEST_DB_NAME = "tpch_s01";
	public static int TEST_NODE_COUNT = 2;
	public static int TEST_PARTS_PER_NODE = 1;

	// Fault-tolerance testing
	public static int TEST_FT_NUMBER_OF_RUNS = 100;
	public static int TEST_FT_NUMBER_OF_FAILURES = 1; 
	public static boolean TEST_FT_CHECKPOINTING = false; 
	public static int TEST_FT_RECORDS_LIMIT = 10;
	
	// Load xdb.conf
	static {
		load();
	}

	/**
	 * Load user configuration from file and override default values
	 */
	private static void load() {
		String[] intProperties = { "COMPUTE_MAX_FETCHSIZE", "COMPUTE_PORT",
				"COMPILE_PORT", "MASTERTRACKER_PORT",
				"QUERYTRACKER_PORT", "TEST_NODE_COUNT",
				"TEST_FT_NUMBER_OF_FAILURES", "TEST_FT_NUMBER_OF_RUNS", "TEST_PARTS_PER_NODE", 
				"TEST_FT_RECORDS_LIMIT" };

		String[] stringProperties = { "COMPILE_URL", "MASTERTRACKER_URL",
				"TEST_DB_NAME" };

		String[] boolProperties = { "COMPUTE_CLEAN_RESULTS", "COMPUTE_CLEAN_PLAN",
				"TRACE_PARALLEL_PLAN",
				"TRACE_COMPILE_PLAN", "TRACE_COMPILE_PLAN_HEADER",
				"TRACE_COMPILE_PLAN_PARTITIONING", "TRACE_COMPILE_PLAN_RESULTS", 
				"TRACE_COMPILE_PLAN_RESULTS", "TRACE_COMPILE_PLAN_FOOTER",
				"TRACE_OPTIMIZED_PLAN", "TRACE_TRACKER_PLAN",
				"TRACE_EXECUTE_PLAN", "TRACE_CODEGEN_PLAN",
				"LOG_EXECUTION_TIME", "CODEGEN_OPTIMIZE", "TEST_RUN_LOCAL",
				"QUERYTRACKER_MONITOR_ACTIVATED", "MASTERTRACKER_MONITOR_ACTIVATED",
				"TEST_FT_CHECKPOINTING"};

		Properties props;
		props = new Properties();
		try {
			// Integer
			props.load(new FileReader(CONFIG_FILE));
			for (String intProperty : intProperties) {
				if (props.containsKey(intProperty)) {
					Config.class.getField(intProperty)
							.setInt(null,
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
				String ruleBitSet = props
						.getProperty("OPTIMIZER_ACTIVE_RULES_FUNCTION").trim();
				int i = 0;
				for (char bit : ruleBitSet.toCharArray()) {
					OPTIMIZER_ACTIVE_RULES_FUNCTION.set(i++,
							(bit == '1') ? true : false);
				}
			}
			if (props.containsKey("OPTIMIZER_ACTIVE_RULES_SELECT")) {
				String ruleBitSet = props
						.getProperty("OPTIMIZER_ACTIVE_RULES_SELECT").trim();
				int i = 0;
				for (char bit : ruleBitSet.toCharArray()) {
					OPTIMIZER_ACTIVE_RULES_SELECT.set(i++, (bit == '1') ? true
							: false);
				}
			}
			if (props.containsKey("QUERYTRACKER_STRATEGY")) {
				String qtStrategy = props.getProperty("QUERYTRACKER_STRATEGY").trim();
				EnumQueryTrackerStrategy tempQtStrat = EnumQueryTrackerStrategy
						.valueOf(qtStrategy);
				if (tempQtStrat != null)
					QUERYTRACKER_STRATEGY = tempQtStrat;
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
	}
}
