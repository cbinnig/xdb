package org.xdb;

import java.io.FileReader;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;

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

	// Master Tracker Server
	public static int MASTERTRACKER_PORT = 55557;
	public static String MASTERTRACKER_URL = "127.0.0.1";

	// Query Tracker Server
	public static int QUERYTRACKER_PORT = 55558;
	public static int QUERYTRACKER_SLOTS = 32;
	public static String QUERYTRACKER_URL = "127.0.0.1";

	public static String LOG_FILE = "./log/xdb.log";
	public static Level LOG_LEVEL = Level.SEVERE;

	// Dotty
	public static String DOT_EXE = "dot";
	public static String DOT_TRACE_PATH = "./log/";

	// Debugging and Testing
	public static boolean useQueryTrackerComputeConnection = true;

	// load properties from file
	static {
		load();
	}

	private static void load() {
		String[] intProperties = {"COMPUTE_MAX_FETCHSIZE", "COMPUTE_SLOTS", "COMPILE_PORT", "MASTERTRACKER_PORT", "QUERYTRACKER_PORT", "QUERYTRACKER_SLOTS"};
		
		String CONFIG_FILE = "./config/xdb.conf";
		Properties props;
		props = new Properties();
		try {
			props.load(new FileReader(CONFIG_FILE));
			for(String intProperty: intProperties){
				if(props.containsKey(intProperty))
					Config.class.getField(intProperty).setInt(null, Integer.parseInt(props.get(intProperty).toString()));
			}
			
			if(props.containsKey("LOG_LEVEL"))
				LOG_LEVEL = Level.parse(props.getProperty("LOG_LEVEL"));
			
		} catch (Exception e) {
			// do nothing
			e.printStackTrace();
		}
	}
}
