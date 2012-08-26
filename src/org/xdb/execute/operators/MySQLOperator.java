package org.xdb.execute.operators;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import org.xdb.Config;
import org.xdb.error.EnumError;
import org.xdb.error.Error;

public class MySQLOperator extends AbstractOperator {
	private static final long serialVersionUID = -6945734207336600373L;

	// attributes for actual execution on node
	private transient Connection conn;
	private transient Vector<PreparedStatement> prepareStmts;
	private transient Vector<PreparedStatement> executeStmts;
	private transient Vector<PreparedStatement> closeStmts;

	protected String dburl = Config.COMPUTE_DB_URL;
	protected String dbname = Config.COMPUTE_DB_NAME;
	protected String dbuser = Config.COMPUTE_DB_USER;
	protected String dbpasswd = Config.COMPUTE_DB_PASSWD;

	// constructor
	public MySQLOperator(Integer operatorId) {
		super(operatorId);
	}


	public MySQLOperator(Integer operatorId, String dbname,
			String dbuser, String dbpasswd) {
		this(operatorId);

		this.dbname = dbname;
		this.dbuser = dbuser;
		this.dbpasswd = dbpasswd;
	}

	// methods
	@Override
	/**
	 * Execute prepared DDL statements
	 */
	public Error prepare() {
		try {
			for (PreparedStatement stmt : this.prepareStmts) {
				stmt.execute();
			}
		} catch (SQLException e) {
			this.err = createMySQLError(e);
		}
		return this.err;
	}

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

		this.prepareStmts = new Vector<PreparedStatement>();
		this.executeStmts = new Vector<PreparedStatement>();
		this.closeStmts = new Vector<PreparedStatement>();
				
		try {

			Class.forName(Config.COMPUTE_DRIVER_CLASS);
			this.conn = DriverManager.getConnection(this.dburl+this.dbname, this.dbuser,
					this.dbpasswd);
			
			for (String ddl : this.prepareSQLs) {
				this.prepareStmts.add(this.conn.prepareStatement(ddl));
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
			this.prepareStmts.clear();
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
