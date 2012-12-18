package org.xdb.execute.operators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.xdb.Config;
import org.xdb.client.QueryTrackerClient;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
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
	private static final long serialVersionUID = -3874534068048724293L;

	// connection to compute DB
	protected transient Connection conn;

	// query tracker URL
	protected QueryTrackerNodeDesc queryTracker;

	// database parameters
	protected String dburl = Config.COMPUTE_DB_URL;
	protected String dbname = Config.COMPUTE_DB_NAME;
	protected String dbuser = Config.COMPUTE_DB_USER;
	protected String dbpasswd = Config.COMPUTE_DB_PASSWD;

	// unique operator id
	protected Identifier operatorId;

	// source IDs
	protected HashSet<Identifier> sourceIds = new HashSet<Identifier>();

	// Source Descriptions
	protected HashSet<OperatorDesc> sources = new HashSet<OperatorDesc>();

	// Consumer Descriptions
	protected HashSet<OperatorDesc> consumers = new HashSet<OperatorDesc>();

	// DDL statements to create input and output tables
	protected Vector<String> openSQLs = new Vector<String>();
	protected transient Vector<PreparedStatement> openStmts;

	// DDL statements to drop input and output tables
	protected Vector<String> closeSQLs = new Vector<String>();
	protected transient Vector<PreparedStatement> closeStmts;

	// helper
	protected Error err = new Error();
	private transient QueryTrackerClient queryTrackerClient;

	// constructors
	public AbstractExecuteOperator(Identifier nodeId) {
		super();
		this.operatorId = nodeId;
	}

	// getters and setters
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
		this.consumers.add(consumer);
	}

	public Set<OperatorDesc> getConsumers() {
		return this.consumers;
	}

	public void addOpenSQL(String ddl) {
		this.openSQLs.add(ddl);
	}

	public void addCloseSQL(String ddl) {
		this.closeSQLs.add(ddl);
	}

	public Set<Identifier> getSourceIds() {
		return this.sourceIds;
	}

	public Set<OperatorDesc> getSources() {
		return this.sources;
	}

	public void addSource(OperatorDesc source) {
		this.sourceIds.add(source.getOperatorID());
		this.sources.add(source);
	}

	// methods
	/**
	 * Open operator
	 */
	public Error open() {

		this.openStmts = new Vector<PreparedStatement>();
		this.closeStmts = new Vector<PreparedStatement>();

		// open connection and compile/execute statements
		try {

			Class.forName(Config.COMPUTE_DRIVER_CLASS);
			this.conn = DriverManager.getConnection(this.dburl + this.dbname,
					this.dbuser, this.dbpasswd);

			// compile open and close statements
			for (String ddl : this.openSQLs) {
				// System.out.println(ddl);
				this.openStmts.add(this.conn.prepareStatement(ddl));
			}

			for (String ddl : this.closeSQLs) {
				// System.out.println(ddl);
				this.closeStmts.add(this.conn.prepareStatement(ddl));
			}

			// execute openStmts
			for (PreparedStatement stmt : this.openStmts) {
				// System.out.println("Execute stmt "+ stmt.toString());
				stmt.execute();
			}

			// initialize client for communication with query tracker
			if (queryTracker != null) {
				this.queryTrackerClient = new QueryTrackerClient(
						this.queryTracker.getUrl());
			}

		} catch (SQLException e) {
			this.err = createMySQLError(e);
		} catch (Exception e) {
			this.err = createMySQLError(e);
		}

		if (this.err.isError())
			return this.err;

		// call operator specific open method
		this.err = openOperator();
		return this.err;
	}

	/**
	 * Operator specific implementation of open()
	 * 
	 * @return
	 */
	protected abstract Error openOperator();

	/**
	 * Execute operator
	 * 
	 * @return
	 */
	public Error execute() {
		// Call operator specific execute method
		this.err = executeOperator();

		if (this.err.isError())
			return this.err;

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
		// execute close statements
		try {
			for (PreparedStatement stmt : this.closeStmts) {
				// System.out.println("Execute stmt "+ stmt.toString());
				stmt.execute();
			}
		} catch (SQLException e) {
			this.err = createMySQLError(e);
		}

		if (this.err.isError())
			return this.err;

		// clean up statements
		this.openStmts.clear();
		this.closeStmts.clear();

		// close connection
		try {
			this.conn.close();
		} catch (Exception e) {
			this.err = createMySQLError(e);
		}

		if (this.err.isError())
			return this.err;

		// call operator specific close method
		this.err = this.closeOperator();
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
		String[] args = { e.toString() + "," + e.getCause() };
		Error err = new Error(EnumError.MYSQL_ERROR, args);
		return err;
	}
}
