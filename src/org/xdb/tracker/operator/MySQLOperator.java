package org.xdb.tracker.operator;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.xdb.execute.operators.OperatorDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

public class MySQLOperator extends AbstractOperator {

	private static final long serialVersionUID = -6394800229111645825L;
	private Vector<StringTemplate> executeSQLs = new Vector<StringTemplate>();

	// constructors
	public MySQLOperator(Identifier operatorId) {
		super(operatorId);
	}

	// getters and setters
	public void addExecuteSQL(StringTemplate dml) {
		this.executeSQLs.add(dml);
	}

	// methods

	@Override
	public org.xdb.execute.operators.AbstractOperator genDeployOperator(
			OperatorDesc operDesc, Map<Identifier, OperatorDesc> currentDeployment) {

		Identifier deployOperId = operDesc.getOperatorID();
		org.xdb.execute.operators.MySQLOperator deployOper = new org.xdb.execute.operators.MySQLOperator(
				deployOperId);

		HashMap<String, String> args = new HashMap<String, String>();

		// generate DDLs to open operator
		for (String tableName : this.inTables.keySet()) {
			
			TableDesc inTableDesc = this.inTableDesc.get(tableName);
			OperatorDesc sourceOp = currentDeployment.get(inTableDesc.getOperatorID());
			String sourceURL = sourceOp.getOperatorNode();
			String sourceTableName = inTableDesc.getTableName();
			Identifier sourceOperId = sourceOp.getOperatorID();
			
			String deployTableDDL = this.genDeployInputTableDDL(tableName,
					deployOperId, sourceTableName, sourceOperId, sourceURL);
			
			String deployTableName = genDeployTableName(tableName, deployOperId);
			deployOper.addOpenSQL(deployTableDDL);
			args.put(tableName, deployTableName);
		}

		for (String tableName : this.outTables.keySet()) {
			
			String deployTableDDL = this.genDeployOutputTableDDL(tableName,
					deployOperId);
			
			String deployTableName = genDeployTableName(tableName, deployOperId);
			deployOper.addOpenSQL(deployTableDDL);
			args.put(tableName, deployTableName);
		}

		// generate DMLs to execute operator
		for (StringTemplate executeSQL : this.executeSQLs) {
			deployOper.addExecuteSQL(executeSQL.toString(args));
		}

		// generate DDLs to close operator
		for (String tableName : this.inTables.keySet()) {
			deployOper.addCloseSQL(genDropDeployTableDDL(tableName, deployOperId));
		}
		
		for (String tableName : this.outTables.keySet()) {
			deployOper.addCloseSQL(genDropDeployTableDDL(tableName, deployOperId));
		}

		return deployOper;
	}
}
