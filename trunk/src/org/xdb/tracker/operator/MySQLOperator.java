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

		Identifier deployOperId = this.genDeployOperId(operDesc);
		org.xdb.execute.operators.MySQLOperator deployOper = new org.xdb.execute.operators.MySQLOperator(
				deployOperId);

		HashMap<String, String> args = new HashMap<String, String>();

		// generate open SQLs
		for (String tableName : this.inTables.keySet()) {
			String deployTableName = this.genDeployInputTableDDL(tableName,
					operDesc);
			deployOper.addOpenSQL(deployTableName);
			args.put(tableName, deployTableName);
		}

		for (String tableName : this.outTables.keySet()) {
			String deployTableName = this.genDeployOutputTableDDL(tableName,
					operDesc);
			deployOper.addOpenSQL(deployTableName);
			args.put(tableName, deployTableName);
		}

		// generate execution SQLs
		for (StringTemplate executeSQL : this.executeSQLs) {
			deployOper.addExecuteSQL(executeSQL.toString(args));
		}

		// generate close SQLs
		for (String tableName : this.inTables.keySet()) {
			deployOper.addCloseSQL(genDropDeployTableDDL(tableName, operDesc));
		}

		if(!this.isRoot){
			for (String tableName : this.outTables.keySet()) {
				deployOper.addCloseSQL(genDropDeployTableDDL(tableName, operDesc));
			}
		}

		return deployOper;
	}
}
