package org.xdb.tracker.operator;

import java.util.HashMap;
import java.util.Vector;

import org.xdb.execute.operators.OperatorDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

public class MySQLOperator extends AbstractOperator {

	private static final long serialVersionUID = -6394800229111645825L;
	private Vector<StringTemplate> executeSQLs = new Vector<StringTemplate>();

	// constructors
	public MySQLOperator(Integer outParts) {
		super(outParts);
	}

	// getters and setters
	public void addExecuteSQL(StringTemplate dml) {
		this.executeSQLs.add(dml);
	}

	// methods

	@Override
	public org.xdb.execute.operators.AbstractOperator genDeployOperator(
			OperatorDesc operDesc) {

		Identifier deployOperId = operDesc.getOperatorID();
		org.xdb.execute.operators.MySQLOperator deployOper = new org.xdb.execute.operators.MySQLOperator(
				deployOperId);

		HashMap<String, String> args = new HashMap<String, String>();

		// generate open SQLs
		for (String tableName : this.inTables.keySet()) {
			String deployTableDDL = this.genDeployInputTableDDL(tableName,
					operDesc);
			String deployTableName = genDeployTableName(tableName, operDesc);
			deployOper.addOpenSQL(deployTableDDL);
			args.put(tableName, deployTableName);
		}

		for (String tableName : this.outTables.keySet()) {
			String deployTableDDL = this.genDeployOutputTableDDL(tableName,
					operDesc);
			String deployTableName = genDeployTableName(tableName, operDesc);
			deployOper.addOpenSQL(deployTableDDL);
			args.put(tableName, deployTableName);
		}

		// generate execution SQLs
		for (StringTemplate executeSQL : this.executeSQLs) {
			deployOper.addExecuteSQL(executeSQL.toString(args));
		}

		// generate close SQLs
		for (String tableName : this.outTables.keySet()) {
			deployOper.addCloseSQL(genDropDeployTableDDL(tableName, operDesc));
		}

		return deployOper;
	}
}
