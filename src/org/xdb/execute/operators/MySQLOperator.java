package org.xdb.execute.operators;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.utils.Identifier;

/**
 * MySQL operator executes SQL DML statements on computing nodes
 * @author cbinnig
 *
 */
public class MySQLOperator extends AbstractOperator {
	private static final long serialVersionUID = -6945734207336600373L;

	//DML statements for execution
	protected Vector<String> executeSQLs = new Vector<String>();
	private transient Vector<PreparedStatement> executeStmts;
		
	// constructor
	public MySQLOperator(Identifier operatorId) {
		super(operatorId);
	}

	public MySQLOperator(Identifier operatorId, String dbname,
			String dbuser, String dbpasswd) {
		
		super(operatorId, dbname, dbuser, dbpasswd);
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
	protected Error executeOperator() {
		try {
			for (PreparedStatement stmt : this.executeStmts) {
				stmt.execute();
				//System.out.println("Execute stmt "+ stmt.toString());
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
	protected Error openOperator() {

		this.executeStmts = new Vector<PreparedStatement>();
				
		//compile statements
		try {

			for (String dml : this.executeSQLs) {
				this.executeStmts.add(this.conn.prepareStatement(dml));
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
	protected Error closeOperator() {
		this.executeStmts.clear();
		
		return this.err;
	}
}
