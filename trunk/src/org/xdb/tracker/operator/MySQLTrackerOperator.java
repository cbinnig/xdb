package org.xdb.tracker.operator;

import java.util.Collection;
import java.util.Map;
import java.util.Vector;

import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.execute.operators.MySQLExecuteOperator;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

public class MySQLTrackerOperator extends AbstractTrackerOperator {

	private static final long serialVersionUID = -6394800229111645825L;
	private Vector<StringTemplate> executeSQLs = new Vector<StringTemplate>();

	// constructors
	public MySQLTrackerOperator() {
		super();
	}

	// getters and setters
	public void addExecuteSQL(StringTemplate dml) {
		this.executeSQLs.add(dml);
	}

	public Collection<StringTemplate> getExecuteSQLs() {
		return executeSQLs;
	}

	// methods
	@Override
	public AbstractExecuteOperator genDeployOperator(OperatorDesc operDesc,
			Map<Identifier, OperatorDesc> currentDeployment) {

		// create a MYSQL operator
		Identifier execOpId = operDesc.getOperatorID();
		MySQLExecuteOperator execOp = new MySQLExecuteOperator(execOpId);

		// generate DDLs for input and output tables and return deployment table names
		Map<String, String> args = this.genInputAndOutput(execOp, operDesc, currentDeployment);

		// generate DMLs to execute operator
		for (StringTemplate executeSQL : this.executeSQLs) {
			execOp.addExecuteSQL(executeSQL.toString(args));
		}

		return execOp;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append(this.getOperatorId());
		builder.append(":");
		builder.append(AbstractToken.NEWLINE);
		for (StringTemplate exeSQL : executeSQLs) {
			builder.append(exeSQL.toString());
			builder.append(AbstractToken.NEWLINE);
		}

		return builder.toString();
	}
}
