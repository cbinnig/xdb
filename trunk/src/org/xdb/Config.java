package org.xdb;

import java.io.Serializable;
import java.util.logging.Level;

/**
 * StratusDB central configuration
 * @author cbinnig
 *
 */
public class Config implements Serializable{
	
	private static final long serialVersionUID = -769530083761951611L;
	
	public static final String COMPUTE_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static final String COMPUTE_DB_URL = "jdbc:mysql://127.0.0.1/";
	public static final String COMPUTE_DB_NAME = "stratusdb";
	public static final String COMPUTE_DB_USER = "stratusroot";
	public static final String COMPUTE_DB_PASSWD = "stratusroot";
	public static final Integer COMPUTE_NOOP_ID = 0;
	public static final int COMPUTE_PORT = 55555;
	public static final String COMPUTE_URL = "127.0.0.1";
	public static final Integer COMPUTE_MAX_FETCHSIZE = Integer.MAX_VALUE;
	
	public static final String DEFAULT_SCHEMA = "PUBLIC";
	public static final String METADATA_SCHEMA = "stratusdb";
	public static final String METADATA_OID_NAME = "OID";
	public static final String METADATA_USER = "stratusroot";
	public static final String METADATA_PASSWORD = "stratusroot";
	public static final String METADATA_DATABASE_URL = "jdbc:mysql://127.0.0.1/stratusdb";
	public static final String METADATA_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	public static final String LOG_FILE = "./log/xdb.log";
	public static final Level LOG_LEVEL = Level.INFO;
}
