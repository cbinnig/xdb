package org.xdb.execute.operators;

import java.sql.PreparedStatement;
import java.sql.SQLSyntaxErrorException;
import java.util.Vector;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.utils.Identifier;


/**
 * MySQL operator executes SQL DML statements on computing nodes using MySQL as
 * engine
 * 
 * @author cbinnig
 * 
 */
public class MySQLExecuteOperator extends AbstractExecuteOperator {
	private static final long serialVersionUID = -6945734207336600373L;

	// DML statements for execution
	protected Vector<String> executeSQLs = new Vector<String>();
	private transient Vector<PreparedStatement> executeStmts;

	// constructor
	public MySQLExecuteOperator(final Identifier operatorId) {
		super(operatorId);
	}

	// getters and setters
	public void addExecuteSQL(final String dml) {
		executeSQLs.add(dml);
	}

	// methods
	@Override
	/**
	 * Prepare statements for execution
	 */
	protected Error openOperator() {

		this.executeStmts = new Vector<PreparedStatement>();

		// compile statements
		try {
			for (final String dml : executeSQLs) {
				//System.err.println(this.getOperatorId()+">"+ dml+";");
				this.executeStmts.add(conn.prepareStatement(dml));
			}
		} 
		catch (final SQLSyntaxErrorException e) {
			this.err = createMySQLError(e);
			this.status = EnumOperatorStatus.FAILED;
		}
		catch (final Exception e) {
			this.err = createMySQLError(e);
			if(Config.QUERYTRACKER_MONITOR_ACTIVATED)
				this.status = EnumOperatorStatus.ABORTED;
			else 
				this.status = EnumOperatorStatus.FAILED;
		}
		
		return this.err;
	}

	@Override
	/**
	 * Execute prepared DML statements
	 */
	protected Error executeOperator() {
		
		try {
			for (final PreparedStatement stmt : executeStmts) {
				stmt.execute();
			}
		} 
		catch (final SQLSyntaxErrorException e) {
			this.err = createMySQLError(e);
			this.status = EnumOperatorStatus.FAILED;
		}
		catch (final Exception e) {
			err = createMySQLError(e);
			if(Config.QUERYTRACKER_MONITOR_ACTIVATED)
				this.status = EnumOperatorStatus.ABORTED;
			else 
				this.status = EnumOperatorStatus.FAILED;
		}

		return err;
	}

	@Override
	/**
	 * Clear prepared statements 
	 */
	protected Error closeOperator() {
		this.executeStmts = null;
		return this.err;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append(super.toString());
		for (String exeSQL : this.executeSQLs) {
			builder.append(exeSQL.toString());
			builder.append(AbstractToken.NEWLINE);
		}

		return builder.toString();
	}
}
