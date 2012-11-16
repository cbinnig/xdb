package org.xdb;

import java.io.Serializable;
import java.util.logging.Level;

import org.xdb.utils.Identifier;

/**
 * XDB central configuration
 * 
 * @author cbinnig
 * 
 */
public class Config implements Serializable {

	private static final long serialVersionUID = -769530083761951611L;

	//Compute Server
	public static final String COMPUTE_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static final String COMPUTE_DB_URL = "jdbc:mysql://127.0.0.1/";
	public static final String COMPUTE_DB_NAME = "xdb_tmp";
	public static final String COMPUTE_DB_USER = "xroot";
	public static final String COMPUTE_DB_PASSWD = "xroot";
	public static final Identifier COMPUTE_NOOP_ID = new Identifier("NOOP");
	public static final int COMPUTE_PORT = 55555;
	public static final Integer COMPUTE_MAX_FETCHSIZE = Integer.MAX_VALUE;
	public static final int COMPUTE_SLOTS = 32;
	public static final String COMPUTE_URL = "127.0.0.1";


	//Compile Server
	public static final String METADATA_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static final String METADATA_DB_URL = "jdbc:mysql://127.0.0.1/xdb_schema";
	public static final String METADATA_SCHEMA = "xdb_schema";
	public static final String METADATA_USER = "xroot";
	public static final String METADATA_PASSWORD = "xroot";
	public static final String METADATA_OID_NAME = "OID";
	public static final Long METADATA_TEMP_OID = -1l;

	public static final int COMPILE_PORT = 55556;
	public static final String COMPILE_URL = "127.0.0.1";
	public static final String COMPILE_DEFAULT_SCHEMA = "PUBLIC";

	//Master Tracker Server
	public static final int MASTERTRACKER_PORT = 55557;
	public static final String MASTERTRACKER_URL = "127.0.0.1";

	//Query Tracker Server
	public static final int QUERYTRACKER_PORT = 55558;
	public static final int QUERYTRACKER_SLOTS = 32;
	public static final String QUERYTRACKER_URL = "127.0.0.1";

	public static final String LOG_FILE = "./log/xdb.log";
	public static final Level LOG_LEVEL = Level.INFO;

	//DOT
	public static final String DOT_EXE = "dot";
	public static final String DOT_TRACE_PATH = "./log/";


	// DEBUG & TESTS
	public static boolean useQueryTrackerComputeConnection = true;
}
