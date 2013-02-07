package org.xdb;

import java.io.FileReader;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Properties;
import java.util.logging.Level;

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

	// Compute Server
	public static String COMPUTE_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static String COMPUTE_DB_URL = "jdbc:mysql://127.0.0.1/";
	public static String COMPUTE_DB_NAME = "xdb_tmp";
	public static String COMPUTE_DB_USER = "xroot";
	public static String COMPUTE_DB_PASSWD = "xroot";
	public static Identifier COMPUTE_NOOP_ID = new Identifier("NOOP");
	public static int COMPUTE_PORT = 55555;
	public static int COMPUTE_MAX_FETCHSIZE = Integer.MAX_VALUE;
	public static int COMPUTE_SLOTS = 32;
	public static String COMPUTE_URL = "127.0.0.1";
	public static boolean COMPUTE_SIGNAL2QUERY_TRACKER = true;
	public static boolean COMPUTE_CLEAN_RESULTS = true;

	// Compile Server
	public static String METADATA_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static String METADATA_DB_URL = "jdbc:mysql://127.0.0.1/xdb_schema";
	public static String METADATA_SCHEMA = "xdb_schema";
	public static String METADATA_USER = "xroot";
	public static String METADATA_PASSWORD = "xroot";
	public static String METADATA_OID_NAME = "OID";
	public static int METADATA_TEMP_OID = -1;

	public static int COMPILE_PORT = 55556;
	public static String COMPILE_URL = "127.0.0.1";
	public static String COMPILE_DEFAULT_SCHEMA = "PUBLIC";
	public static BitSet OPTIMIZER_ACTIVE_RULES_FUNCTION = new BitSet();
	public static BitSet OPTIMIZER_ACTIVE_RULES_SELECT = new BitSet();

	static {
		OPTIMIZER_ACTIVE_RULES_FUNCTION.set(0, true);
		OPTIMIZER_ACTIVE_RULES_FUNCTION.set(1, true);
		OPTIMIZER_ACTIVE_RULES_FUNCTION.set(2, true);
		OPTIMIZER_ACTIVE_RULES_FUNCTION.set(3, true);
		OPTIMIZER_ACTIVE_RULES_FUNCTION.set(4, true);
		OPTIMIZER_ACTIVE_RULES_SELECT.set(0, false);
		OPTIMIZER_ACTIVE_RULES_SELECT.set(1, true);
		OPTIMIZER_ACTIVE_RULES_SELECT.set(2, true);
		OPTIMIZER_ACTIVE_RULES_SELECT.set(3, true);
		OPTIMIZER_ACTIVE_RULES_SELECT.set(4, true);
	}

	// Master Tracker Server
	public static int MASTERTRACKER_PORT = 55557;
	public static String MASTERTRACKER_URL = "127.0.0.1";

	// Query Tracker Server
	public static int QUERYTRACKER_PORT = 55558;
	public static int QUERYTRACKER_SLOTS = 32;
	public static String QUERYTRACKER_URL = "127.0.0.1";
	public static EnumResourceScheduler RESOURCE_SCHEDULER = EnumResourceScheduler.LOCALITY_AWARE_SCHEDULER;

	public static String LOG_FILE = "./log/xdb.log";
	public static Level LOG_LEVEL = Level.SEVERE;

	// Tracing
	public static String DOT_EXE = "dot";
	public static String DOT_TRACE_PATH = "./log/";
	public static boolean TRACE_COMPILE_PLAN = false;
	public static boolean TRACE_COMPILE_PLAN_HEADER = false;
	public static boolean TRACE_OPTIMIZED_PLAN = false;
	public static boolean TRACE_TRACKER_PLAN = false;
	public static boolean TRACE_EXECUTE_PLAN = false;
	
	//Measurements
	public static boolean MEASURE_QUERY_EXECUTION_TIME = false;

	// load properties from file
	static {
		load();
	}

	/**
	 * Load user configuration from file and override default values
	 */
	private static void load() {
		String[] intProperties = { "COMPUTE_MAX_FETCHSIZE", "COMPUTE_PORT",
				"COMPUTE_SLOTS", "COMPILE_URL", "COMPILE_PORT",
				"MASTERTRACKER_URL", "MASTERTRACKER_PORT", "QUERYTRACKER_URL",
				"QUERYTRACKER_PORT", "QUERYTRACKER_SLOTS" };

		String[] boolProperties = { "COMPUTE_CLEAN_RESULTS",
				"TRACE_COMPILE_PLAN", "TRACE_COMPILE_PLAN_HEADER",
				"TRACE_OPTIMIZED_PLAN", "TRACE_TRACKER_PLAN", 
				"TRACE_EXECUTE_PLAN", "MEASURE_QUERY_EXECUTION_TIME" };

		String CONFIG_FILE = "./config/xdb.conf";
		Properties props;
		props = new Properties();
		try {
			props.load(new FileReader(CONFIG_FILE));
			for (String intProperty : intProperties) {
				if (props.containsKey(intProperty)) {
					Config.class.getField(intProperty)
							.setInt(null,
									Integer.parseInt(props.get(intProperty)
											.toString()));
				}
			}

			for (String boolProperty : boolProperties) {
				if (props.containsKey(boolProperty)) {
					Config.class.getField(boolProperty).setBoolean(
							null,
							Boolean.parseBoolean(props.get(boolProperty)
									.toString()));
					;
				}
			}

			if (props.containsKey("LOG_LEVEL")) {
				LOG_LEVEL = Level.parse(props.getProperty("LOG_LEVEL"));
			}

			if (props.containsKey("OPTIMIZER_ACTIVE_RULES_FUNCTION")) {
				String ruleBitSet = props.getProperty("OPTIMIZER_ACTIVE_RULES_FUNCTION");
				int i = 0;
				for (char bit : ruleBitSet.toCharArray()) {
					OPTIMIZER_ACTIVE_RULES_FUNCTION
							.set(i++, (bit == '1') ? true : false);
				}
			}
			if (props.containsKey("OPTIMIZER_ACTIVE_RULES_SELECT")) {
				String ruleBitSet = props.getProperty("OPTIMIZER_ACTIVE_RULES_SELECT");
				int i = 0;
				for (char bit : ruleBitSet.toCharArray()) {
					OPTIMIZER_ACTIVE_RULES_SELECT
							.set(i++, (bit == '1') ? true : false);
				}
			}

		} catch (Exception e) {
			// do nothing
			e.printStackTrace();
		}
	}
}
