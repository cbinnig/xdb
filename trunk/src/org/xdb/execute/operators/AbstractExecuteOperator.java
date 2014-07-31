package org.xdb.execute.operators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.xdb.Config;
import org.xdb.client.QueryTrackerClient;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.tracker.QueryTrackerNodeDesc;
import org.xdb.utils.Identifier;

/**
 * Abstract executable operator implementation with an iterator interface -
 * open: prepare input and output tables - execute: execute code - close: drop
 * input and output tables
 * 
 * @author cbinnig
 */
public abstract class AbstractExecuteOperator implements Serializable {
	
	private static final long serialVersionUID = -3896677758698111894L;

	// connection to compute DB
	protected transient Connection conn;

	// query tracker 
	protected QueryTrackerNodeDesc queryTracker;
	private transient QueryTrackerClient queryTrackerClient;

	// database parameters
	protected String dburl = Config.COMPUTE_DB_URL;
	protected String dbname = Config.COMPUTE_DB_NAME;
	protected String dbuser = Config.COMPUTE_DB_USER;
	protected String dbpasswd = Config.COMPUTE_DB_PASSWD;

	// unique operator id
	protected Identifier operatorId;

	// source IDs
	protected HashSet<Identifier> sourceTrackerIds = new HashSet<Identifier>();

	// Consumer Descriptions
	protected HashSet<Identifier> consumersTrackerIds = new HashSet<Identifier>();

	// DDL statements to create input and output tables
	protected Vector<String> openSQLs = new Vector<String>();

	// DDL statements to drop input and output tables
	protected Vector<String> closeSQLs = new Vector<String>();

	// status
	protected EnumOperatorStatus status = EnumOperatorStatus.INIT;

	// helper
	protected Error err = new Error(); 
	
	protected double runtime; 
	
	protected double mattime; 
	
	// constructors
	public AbstractExecuteOperator(Identifier nodeId) {
		super();
		this.operatorId = nodeId;
	}

	// getters and setters
	public void setStatus(EnumOperatorStatus status){
		this.status = status;
	}
	
	public EnumOperatorStatus getStatus() {
		return this.status;
	}
	
	
	public Error getLastError() {
		return this.err;
	}

	public void setQueryTracker(QueryTrackerNodeDesc queryTracker) {
		this.queryTracker = queryTracker;
	}

	public QueryTrackerClient getQueryTrackerClient() {
		return this.queryTrackerClient;
	}

	public Identifier getOperatorId() {
		return operatorId;
	}

	public void addConsumer(OperatorDesc consumer) {
		Identifier trackerConsumerId = consumer.getOperatorID().getParentId(1);
		if(trackerConsumerId!=null)
			this.consumersTrackerIds.add(trackerConsumerId);
	}

	public Set<Identifier> getConsumerTrackerIds() {
		return this.consumersTrackerIds;
	}

	public void addOpenSQL(String ddl) {
		this.openSQLs.add(ddl);
	}

	public void addCloseSQL(String ddl) {
		this.closeSQLs.add(ddl);
	}

	public Set<Identifier> getSourceTrackerIds() {
		return this.sourceTrackerIds;
	}

	public void addSource(OperatorDesc source) {
		this.sourceTrackerIds.add(source.getOperatorID().getParentId(1));
	}

	// methods
	/**
	 * Open operator
	 */
	public Error open() {
		// initialize client for communication with query tracker
		if (queryTracker != null) {
			this.queryTrackerClient = new QueryTrackerClient(
					this.queryTracker.getUrl());
		}

		// open connection and execute open statements
		try {
			Class.forName(Config.COMPUTE_DRIVER_CLASS);
			this.conn = DriverManager.getConnection(this.dburl + this.dbname,
					this.dbuser, this.dbpasswd);

			// compile open and close statements
			Statement openStmt = conn.createStatement();
			for (String ddl : this.openSQLs) {
				//System.out.println(this.getOperatorId()+">"+ddl+";");
				openStmt.execute(ddl);
			}

		} catch (final Exception e) {
			this.err = createMySQLError(e);
			this.status = EnumOperatorStatus.getRuntimeFailure();
			return this.err;
		}

		// call operator specific open method
		this.err = openOperator();
		if (this.err.isError())
			return this.err;

		// set status to deployed
		this.status = EnumOperatorStatus.DEPLOYED;
		return this.err;
	}

	/**
	 * Operator specific implementation of open()
	 * 
	 * @return
	 */
	protected abstract Error openOperator();
    
	public void setMattime(double mattime){
		this.mattime = mattime;
	}
    
	public void setRunime(double runtime){
		this.runtime = runtime;
	} 
	
	public double getMattime(){
		return this.mattime;
	} 
	
	public double getRuntime(){
		return this.runtime;
	}
	
	/**
	 * Execute operator
	 * 
	 * @return
	 */
	public Error execute() {
		this.status = EnumOperatorStatus.RUNNING;
		
		// execute operator 
		this.err = executeOperator();
		if (!err.isError())
			this.status = EnumOperatorStatus.FINISHED;

		// close connection
		try {
			this.conn.close();
		} catch (Exception e) {
			this.err = createMySQLError(e);
		}

		return this.err;
	}

	/**
	 * Operator specific implementation of execute()
	 * 
	 * @return
	 */
	protected abstract Error executeOperator();

	/**
	 * Closes operator
	 * 
	 * @return
	 */
	/**
	 * Close connection and remove prepared statements
	 */
	public Error close() {
		// open connection and execute close statements
		try {
			Class.forName(Config.COMPUTE_DRIVER_CLASS);
			this.conn = DriverManager.getConnection(this.dburl + this.dbname,
					this.dbuser, this.dbpasswd);

			Statement closeStmt = this.conn.createStatement();
			for (String ddl : this.closeSQLs) {
				//System.err.println(this.operatorId+">"+ddl);
				closeStmt.execute(ddl);
			}

		} catch (final Exception e) {
			this.err = createMySQLError(e);
		}

		// call operator specific close method
		this.closeOperator();

		// close connection
		try {
			this.conn.close();
		} catch (Exception e) {
			this.err = createMySQLError(e);
		}

		return this.err;
	}

	/**
	 * Operator specific implementation of close()
	 * 
	 * @return
	 */
	protected abstract Error closeOperator();

	/**
	 * Create MYSQL_ERROR from an exception
	 * 
	 * @param e
	 *            Exception
	 * @return Error
	 */
	protected Error createMySQLError(Exception e) {
		//e.printStackTrace();
		String[] args = { this.getOperatorId() + " > " + e.toString() };
		Error err = new Error(EnumError.MYSQL_ERROR, args);
		return err;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getCanonicalName());
		builder.append(AbstractToken.NEWLINE);

		for (String openSQL : this.openSQLs) {
			builder.append(openSQL.toString());
			builder.append(AbstractToken.NEWLINE);
		}

		for (String closeSQL : this.closeSQLs) {
			builder.append(closeSQL.toString());
			builder.append(AbstractToken.NEWLINE);
		}

		return builder.toString();
	}
}
