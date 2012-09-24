package org.xdb.execute.operators;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.utils.Identifier;

/**
 * MySQL operator executes SQL DML statements on computing nodes using MySQL as
 * engine
 * 
 * @author cbinnig
 * 
 */
public class MySQLOperator extends AbstractOperator {
	private static final long serialVersionUID = -6945734207336600373L;

	// DML statements for execution
	protected Vector<String> executeSQLs = new Vector<String>();
	private transient Vector<PreparedStatement> executeStmts;

	// constructor
	public MySQLOperator(final Identifier operatorId) {
		super(operatorId);
	}

	public MySQLOperator(final Identifier operatorId, final String dbname, final String dbuser,
			final String dbpasswd) {

		super(operatorId, dbname, dbuser, dbpasswd);
	}

	// getters and setters
	public void addExecuteSQL(final String dml) {
		executeSQLs.add(dml);
	}

	// methods

	@Override
	/**
	 * Execute prepared DML statements
	 */
	protected Error executeOperator() {
		try {
			for (final PreparedStatement stmt : executeStmts) {
				stmt.execute();
				// System.out.println("Execute stmt "+ stmt.toString());
			}
		} catch (final SQLException e) {
			err = createMySQLError(e);
		}
		return err;
	}

	@Override
	/**
	 * Open connection to node and prepare statements for execution
	 */
	protected Error openOperator() {

		executeStmts = new Vector<PreparedStatement>();

		// compile statements
		try {

			for (final String dml : executeSQLs) {
				executeStmts.add(conn.prepareStatement(dml));
			}
		} catch (final SQLException e) {
			err = createMySQLError(e);
		}
		return err;
	}

	@Override
	/**
	 * Close connection and remove prepared statements 
	 */
	protected Error closeOperator() {
		executeStmts.clear();

		return err;
	}
}
