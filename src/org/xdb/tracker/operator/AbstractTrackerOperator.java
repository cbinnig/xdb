package org.xdb.tracker.operator;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.xdb.Config;
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;
import org.xdb.error.Error;

public abstract class AbstractTrackerOperator implements Serializable {

	private static final long serialVersionUID = 8183857279874181382L;

	// constants
	protected static final String CREATE_TABLE_DDL = "CREATE TABLE ";
	protected static final String DROP_TABLE_DDL = "DROP TABLE ";

	private static final StringTemplate OUTPUT_DDL = new StringTemplate(
			" ENGINE=MEMORY");

	private static final StringTemplate INPUT_DDL = new StringTemplate(
			" ENGINE=FEDERATED CONNECTION='mysql://" + Config.COMPUTE_DB_USER
			+ ":" + Config.COMPUTE_DB_PASSWD + "@<HOST>/"
			+ Config.COMPUTE_DB_NAME + "/<TABLE>'");

	private static final StringTemplate INPUT2_DDL = new StringTemplate(
			" ENGINE=FEDERATED CONNECTION='mysql://" + Config.COMPUTE_DB_USER
			+ ":" + Config.COMPUTE_DB_PASSWD + "@<HOST>/"
			+ "<DB>/<TABLE>'");

	private static final String KEY_HOST = "HOST";
	private static final String KEY_TABLE = "TABLE";
	private static final String KEY_DB = "DB";
	
	// map: output table name -> DDLs
	protected HashMap<String, StringTemplate> outTables = new HashMap<String, StringTemplate>();

	// map: input table name -> DDLs
	protected HashMap<String, StringTemplate> inTables = new HashMap<String, StringTemplate>();

	// map: input table name -> TableDesc
	protected HashMap<String, TableDesc> inTableDesc = new HashMap<String, TableDesc>();

	// unique operator id
	protected Identifier operatorId;

	// flag for root operator
	protected boolean isRoot = false;

	// Error handling
	protected Error err = new Error();
	

	// constructors
	public AbstractTrackerOperator() {
		super();
	}

	// getters and setters
	public void setIsRoot(final boolean isRoot){
		this.isRoot = isRoot;
	}

	public Error getError() {
		return err;
	}

	public boolean isRoot(){
		return isRoot;
	}

	public void setInTableSource(final String tableName, final TableDesc tableDesc){
		inTableDesc.put(tableName, tableDesc);
	}

	public Collection<TableDesc> getInTableSources(){
		return this.inTableDesc.values();
	}
	
	public void addInTable(final String tableName, final StringTemplate tableDDL) {
		inTables.put(tableName, tableDDL);
	}
	
	public Collection<StringTemplate> getInTables() {
		return inTables.values();
	}
	
	public void addOutTable(final String tableName, final StringTemplate tableDDL) {
		outTables.put(tableName, tableDDL);
	}
	
	public Collection<StringTemplate> getOutTables() {
		return outTables.values();
	}

	public void setOperatorId(final Identifier operatorId) {
		this.operatorId = operatorId;
	}

	public Identifier getOperatorId() {
		return operatorId;
	}

	// methods
	public abstract AbstractExecuteOperator genDeployOperator(
			OperatorDesc operDesc, Map<Identifier, OperatorDesc> currentDeployment);

	/**
	 * Generate SQL DDL to deploy partitioned in-memory output table
	 * 
	 * @param tableName
	 * @param operDesc
	 * @return
	 */
	protected String genDeployOutputTableDDL(final String tableName, final Identifier operatorID) {
		final StringTemplate tableTemplate = outTables.get(tableName);
		final StringBuffer tableDDL = new StringBuffer(CREATE_TABLE_DDL);

		// replace table name
		final HashMap<String, String> args = new HashMap<String, String>();
		args.put(tableName,	genDeployTableName(tableName, operatorID));
		tableDDL.append(tableTemplate.toString(args));

		// add partition specification
		args.clear();
		tableDDL.append(OUTPUT_DDL.toString(args));

		return tableDDL.toString();
	}

	/**
	 * Generate SQL DDL to deploy input table
	 * 
	 * @param tableName
	 * @param operDesc
	 * @return
	 */
	protected String genDeployInputTableDDL(final String tableName, final Identifier opID, final String sourceTableName, final String sourceDB, final String sourceURL) {
		final StringTemplate tableTemplate = inTables.get(tableName);
		final StringBuffer tableDDL = new StringBuffer(CREATE_TABLE_DDL);

		// create table DDL
		final HashMap<String, String> args = new HashMap<String, String>();
		final String deployTableName = genDeployTableName(tableName, opID);
		args.put(tableName, deployTableName);
		tableDDL.append(tableTemplate.toString(args));

		// add federation specification
		args.clear();
		args.put(KEY_HOST, sourceURL);
		args.put(KEY_DB, sourceDB);
		args.put(KEY_TABLE, sourceTableName);
		tableDDL.append(INPUT2_DDL.toString(args));

		return tableDDL.toString();
	}
	
	/**
	 * Generate SQL DDL to deploy input table
	 * 
	 * @param tableName
	 * @param operDesc
	 * @return
	 */
	protected String genDeployInputTableDDL(final String tableName, final Identifier opID, final String sourceTableName, final Identifier sourceOpID, final String sourceNode) {
		final StringTemplate tableTemplate = inTables.get(tableName);
		final StringBuffer tableDDL = new StringBuffer(CREATE_TABLE_DDL);

		// create table DDL
		final HashMap<String, String> args = new HashMap<String, String>();
		final String deployTableName = genDeployTableName(tableName, opID);
		args.put(tableName, deployTableName);
		tableDDL.append(tableTemplate.toString(args));

		// add federation specification
		args.clear();
		final String deploySourceTableName = genDeployTableName(sourceTableName, sourceOpID);
		args.put(KEY_HOST, sourceNode);
		args.put(KEY_TABLE, deploySourceTableName);
		tableDDL.append(INPUT_DDL.toString(args));

		return tableDDL.toString();
	}

	/**
	 * Generate DDL to drop a table
	 * @param tableName
	 * @param operDesc
	 * @return
	 */
	public static String genDropDeployTableDDL(final String tableName,
			final Identifier operatorID) {
		final StringBuffer tableDDL = new StringBuffer(DROP_TABLE_DDL);
		tableDDL.append(genDeployTableName(tableName, operatorID));
		return tableDDL.toString();
	}

	/**
	 * Generate table name for deploy
	 * 
	 * @param tableName
	 * @param operId
	 * @return
	 */
	public static String genDeployTableName(final String tableName,
			final Identifier operatorID) {
		final Identifier newTableName = operatorID.clone();
		newTableName.append(tableName);
		return newTableName.toString();
	}

	/**
	 * Generate operator id for deployment
	 * @param operDesc
	 * @return
	 */
	public Identifier genDeployOperId(final OperatorDesc operDesc) {
		final Identifier newOperId = operatorId.clone();
		newOperId.append(operDesc.getOperatorID().toString());
		return newOperId;
	}
}
