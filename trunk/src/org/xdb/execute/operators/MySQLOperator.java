package org.xdb.execute.operators;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import org.xdb.Config;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.utils.Identifier;

public class MySQLOperator extends AbstractOperator {
	private static final long serialVersionUID = -6945734207336600373L;

	// attributes for actual execution on node
	private transient Connection conn;
	private transient Vector<PreparedStatement> openStmts;
	private transient Vector<PreparedStatement> executeStmts;
	private transient Vector<PreparedStatement> closeStmts;

	//DML statements for execution
	protected Vector<String> executeSQLs = new Vector<String>();
		
	protected String dburl = Config.COMPUTE_DB_URL;
	protected String dbname = Config.COMPUTE_DB_NAME;
	protected String dbuser = Config.COMPUTE_DB_USER;
	protected String dbpasswd = Config.COMPUTE_DB_PASSWD;

	// constructor
	public MySQLOperator(Identifier operatorId) {
		super(operatorId);
	}


	public MySQLOperator(Identifier operatorId, String dbname,
			String dbuser, String dbpasswd) {
		this(operatorId);

		this.dbname = dbname;
		this.dbuser = dbuser;
		this.dbpasswd = dbpasswd;
	}

	// getters and setters
	
	public void addExecuteSQL(String dml){
		this.executeSQLs.add(dml);
	}
	
	// methods

	@Override
	/**
	 * Execute prepared DML statements
	 */
	public Error execute() {
		try {
			for (PreparedStatement stmt : this.executeStmts) {
				stmt.execute();
			}
		} catch (SQLException e) {
			this.err = createMySQLError(e);
		}
		return this.err;
	}

	@Override
	/**
	 * Open connection and prepare statements for execution
	 */
	public Error open() {

		this.openStmts = new Vector<PreparedStatement>();
		this.executeStmts = new Vector<PreparedStatement>();
		this.closeStmts = new Vector<PreparedStatement>();
				
		//compile statements
		try {

			Class.forName(Config.COMPUTE_DRIVER_CLASS);
			this.conn = DriverManager.getConnection(this.dburl+this.dbname, this.dbuser,
					this.dbpasswd);
			
			for (String ddl : this.openSQLs) {
				this.openStmts.add(this.conn.prepareStatement(ddl));
			}

			for (String dml : this.executeSQLs) {
				this.executeStmts.add(this.conn.prepareStatement(dml));
			}

			for (String ddl : this.closeSQLs) {
				this.closeStmts.add(this.conn.prepareStatement(ddl));
			}
			
		} catch (SQLException e) {
			this.err = createMySQLError(e);
		} catch (Exception e) {
			this.err = createMySQLError(e);
		}

		//execute openStmts 
		if(!err.isError()){
			this.err = prepare();
		}
		
		return this.err;
	}

	/**
	 * Execute open DDL statements
	 */
	private Error prepare() {
		try {
			for (PreparedStatement stmt : this.openStmts) {
				stmt.execute();
			}
		} catch (SQLException e) {
			this.err = createMySQLError(e);
		}
		return this.err;
	}
	
	@Override
	/**
	 * Close connection and remove prepared statements 
	 */
	public Error close() {
		try {
			for (PreparedStatement stmt : this.closeStmts) {
				stmt.execute();
			}
		} catch (SQLException e) {
			this.err = createMySQLError(e);
		}
		
		if(this.err.isError())
			return this.err;
		
		try {
			this.conn.close();
			this.openStmts.clear();
			this.executeStmts.clear();
			this.closeStmts.clear();
		} catch (Exception e) {
			this.err = createMySQLError(e);
		}
		return this.err;
	}

	/**
	 * Create MYSQL_ERROR from an exception
	 * 
	 * @param e
	 *            Exception
	 * @return Error
	 */
	private Error createMySQLError(Exception e) {
		String[] args = { e.toString()+","+e.getCause() };
		Error err = new Error(EnumError.MYSQL_ERROR, args);
		return err;
	}
}
