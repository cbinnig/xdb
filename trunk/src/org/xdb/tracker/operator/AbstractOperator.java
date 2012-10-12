package org.xdb.tracker.operator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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

	// map: table name -> partition attribute
	protected HashMap<String, String> partAtt = new HashMap<String, String>();

	// map: output table name -> DDLs
	protected HashMap<String, StringTemplate> outTables = new HashMap<String, StringTemplate>();

	// map: input table name -> DDLs
	protected HashMap<String, StringTemplate> inTables = new HashMap<String, StringTemplate>();
	
	// map: input table name -> TableDesc
	protected HashMap<String, TableDesc> inTableDesc = new HashMap<String, TableDesc>();

	// map: input table name -> Connection string
	protected HashMap<String, String> inTableConnection = new HashMap<String, String>();
	
	// unique operator id
	protected Identifier operatorId;
	
	// flag for root operator
	protected boolean isRoot = false;

	// constructors
	public AbstractOperator(Identifier operatorId) {
		super();
		this.operatorId = operatorId;
	}

	// getters and setters
	public void setIsRoot(boolean isRoot){
		this.isRoot = isRoot;
	}
	
	public boolean isRoot(){
		return this.isRoot;
	}
	
	public void addInTableConnection(final String tableName, final String connectionURL) {
		inTableConnection.put(tableName, connectionURL);
	}

	public void setInTableSource(String tableName, TableDesc tableDesc){
		inTableDesc.put(tableName, tableDesc);
	}
	
	public void addInTables(String tableName, StringTemplate tableDDL,
			String partAtt ) {
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
			OperatorDesc operDesc, Map<Identifier, OperatorDesc> currentDeployment);

	/**
	 * Generate SQL DDL to deploy partitioned in-memory output table
	 * 
	 * @param tableName
	 * @param operDesc
	 * @return
	 */
	protected String genDeployOutputTableDDL(String tableName, Identifier operatorID) {
		StringTemplate tableTemplate = this.outTables.get(tableName);
		StringBuffer tableDDL = new StringBuffer(CREATE_TABLE_DDL);

		// replace table name
		HashMap<String, String> args = new HashMap<String, String>();
		args.put(tableName,	genDeployTableName(tableName, operatorID));
		tableDDL.append(tableTemplate.toString(args));

		// add partition specification
		args.clear();
		args.put(KEY_PARTATT, this.partAtt.get(tableName));
		args.put(KEY_PARTS, "1");
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
	protected String genDeployInputTableDDL(String tableName, Identifier opID, String sourceTableName, Identifier sourceOpID, String sourceNode) {
		StringTemplate tableTemplate = this.inTables.get(tableName);
		StringBuffer tableDDL = new StringBuffer(CREATE_TABLE_DDL);

		// create table DDL
		HashMap<String, String> args = new HashMap<String, String>();
		String deployTableName = genDeployTableName(tableName, opID);
		args.put(tableName, deployTableName);
		tableDDL.append(tableTemplate.toString(args));

		// add federation specification
		args.clear();
		String deploySourceTableName = genDeployTableName(sourceTableName, sourceOpID);
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
	public static String genDropDeployTableDDL(String tableName,
			Identifier operatorID) {
		StringBuffer tableDDL = new StringBuffer(DROP_TABLE_DDL);
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
	public static String genDeployTableName(String tableName,
			Identifier operatorID) {
		Identifier newTableName = operatorID.clone();
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
