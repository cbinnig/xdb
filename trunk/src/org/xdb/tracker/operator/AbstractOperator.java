package org.xdb.tracker.operator;

import java.io.Serializable;
import java.util.HashMap;

import org.xdb.Config;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

public abstract class AbstractOperator implements Serializable {

	private static final long serialVersionUID = 8183857279874181382L;

	// constants
	protected static final String CREATE_TABLE_DDL = "CREATE TABLE ";
	protected static final String DROP_TABLE_DDL = "DROP TABLE ";
	
	private static final StringTemplate OUTPUT_DDL = new StringTemplate(
			" ENGINE=MEMORY PARTITION BY HASH(<partAtt>) PARTITIONS <parts>");
	
	private static final String KEY_PARTATT = "partAtt";
	private static final String KEY_PARTS = "parts";

	private static final StringTemplate INPUT_DDL = new StringTemplate(
			" ENGINE=FEDERATED CONNECTION='mysql://" + Config.COMPUTE_DB_USER
					+ ":" + Config.COMPUTE_DB_PASSWD + "@<host>/"
					+ Config.COMPUTE_DB_NAME + "/<table>'");
	
	private static final String KEY_HOST = "host";
	private static final String KEY_TABLE = "table";

	// name of partition attribute
	protected HashMap<String, String> partAtt = new HashMap<String, String>();

	// info (parts, DDLs) for output tables
	protected Integer outParts;
	protected HashMap<String, StringTemplate> outTables = new HashMap<String, StringTemplate>();

	// info (parts, DDLs) for input tables
	protected HashMap<String, StringTemplate> inTables = new HashMap<String, StringTemplate>();

	// unique operator id
	protected Identifier operatorId;
	
	//Is root operator
	protected boolean isRoot = false;

	// constructors
	public AbstractOperator(Integer outParts) {
		super();
		this.operatorId = Config.COMPUTE_NOOP_ID;
		this.outParts = outParts;
	}

	// getters and setters
	public void setIsRoot(boolean isRoot){
		this.isRoot = isRoot;
	}
	
	public boolean isRoot(){
		return this.isRoot;
	}
	
	public void setOutParts(int outParts) {
		this.outParts = outParts;
	}

	public void addInTables(String tableName, StringTemplate tableDDL,
			String partAtt) {
		this.inTables.put(tableName, tableDDL);
		this.partAtt.put(tableName, partAtt);
	}

	public void addOutTables(String tableName, StringTemplate tableDDL,
			String partAtt) {
		this.outTables.put(tableName, tableDDL);
		this.partAtt.put(tableName, partAtt);
	}

	public void setOperatorId(Identifier operatorId) {
		this.operatorId = operatorId;
	}
	
	public Identifier getOperatorId() {
		return operatorId;
	}

	// methods
	public abstract org.xdb.execute.operators.AbstractOperator genDeployOperator(
			OperatorDesc operDesc);

	/**
	 * Generate SQL DDL to deploy partitioned in-memory output table
	 * 
	 * @param tableName
	 * @param operDesc
	 * @return
	 */
	protected String genDeployOutputTableDDL(String tableName, OperatorDesc operDesc) {
		StringTemplate tableTemplate = this.outTables.get(tableName);
		StringBuffer tableDDL = new StringBuffer(CREATE_TABLE_DDL);

		// replace table name
		HashMap<String, String> args = new HashMap<String, String>();
		args.put(tableName,	genDeployTableName(tableName, operDesc));
		tableDDL.append(tableTemplate.toString(args));

		// add partition specification
		args.clear();
		args.put(KEY_PARTATT, this.partAtt.get(tableName));
		args.put(KEY_PARTS, this.outParts.toString());
		tableDDL.append(OUTPUT_DDL.toString(args));

		return tableDDL.toString();
	}

	/**
	 * Generate SQL DDL to deploy federated input table
	 * 
	 * @param tableName
	 * @param operDesc
	 * @return
	 */
	protected String genDeployInputTableDDL(String tableName, OperatorDesc operDesc) {
		StringTemplate tableTemplate = this.outTables.get(tableName);
		StringBuffer tableDDL = new StringBuffer(CREATE_TABLE_DDL);

		// replace table name
		HashMap<String, String> args = new HashMap<String, String>();
		String deployTableName = genDeployTableName(tableName, operDesc);
		args.put(tableName, deployTableName);
		tableDDL.append(tableTemplate.toString(args));

		// add partition specification
		args.clear();
		args.put(KEY_HOST, operDesc.getOperatorNode());
		args.put(KEY_TABLE, deployTableName);
		tableDDL.append(INPUT_DDL.toString(args));

		return tableDDL.toString();
	}

	/**
	 * Generate DDL to drop a table
	 * @param tableName
	 * @param operDesc
	 * @return
	 */
	public static String genDropDeployTableDDL(String tableName,
			OperatorDesc operDesc) {
		StringBuffer tableDDL = new StringBuffer(DROP_TABLE_DDL);
		tableDDL.append(genDeployTableName(tableName, operDesc));
		return tableDDL.toString();
	}
	
	/**
	 * Generate table name for deploy
	 * 
	 * @param tableName
	 * @param operId
	 * @return
	 */
	public static String genDeployTableName(String tableName,
			OperatorDesc operDesc) {
		Identifier newTableName = operDesc.getOperatorID().clone();
		newTableName.append(tableName);
		return newTableName.toString();
	}

	/**
	 * Generate operator id for deployment
	 * @param operDesc
	 * @return
	 */
	public Identifier genDeployOperId(OperatorDesc operDesc) {
		Identifier newOperId = this.operatorId.clone();
		newOperId.append(operDesc.getOperatorID().toString());
		return newOperId;
	}
}
