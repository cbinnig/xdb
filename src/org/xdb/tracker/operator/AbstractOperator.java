package org.xdb.tracker.operator;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.xdb.Config;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

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
	public AbstractOperator(final Identifier operatorId) {
		super();
		this.operatorId = operatorId;
	}

	// getters and setters
	public void setIsRoot(final boolean isRoot){
		this.isRoot = isRoot;
	}

	public boolean isRoot(){
		return isRoot;
	}

	public void addInTableConnection(final String tableName, final String connectionURL) {
		inTableConnection.put(tableName, connectionURL);
	}

	public void setInTableSource(final String tableName, final TableDesc tableDesc){
		inTableDesc.put(tableName, tableDesc);
	}

	public void addInTables(final String tableName, final StringTemplate tableDDL,
			final String partAtt ) {
		inTables.put(tableName, tableDDL);
		this.partAtt.put(tableName, partAtt);
	}

	public void addOutTables(final String tableName, final StringTemplate tableDDL,
			final String partAtt) {
		outTables.put(tableName, tableDDL);
		this.partAtt.put(tableName, partAtt);
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
	public abstract org.xdb.execute.operators.AbstractOperator genDeployOperator(
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
		args.put(KEY_PARTATT, partAtt.get(tableName));
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
	
	public Map<String, StringTemplate> getInTables() {
		return Collections.unmodifiableMap(inTables);
	}
	
	public Map<String, TableDesc> getInTableSource() {
		return Collections.unmodifiableMap(inTableDesc);
	}

	public Map<String, String> getInTableConnection() {
		return Collections.unmodifiableMap(inTableConnection);
	}
}
