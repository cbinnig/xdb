package org.xdb.tracker.operator;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xdb.Config;
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;
import org.xdb.error.Error;
import org.xdb.metadata.Connection;

public abstract class AbstractTrackerOperator implements Serializable {

	private static final long serialVersionUID = 8183857279874181382L;

	// constants
	protected static final String CREATE_TABLE_DDL = "CREATE TABLE IF NOT EXISTS ";
	protected static final String DROP_TABLE_DDL = "DROP TABLE IF EXISTS ";

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

	
	// map: output table name -> PartitionInfo String
	protected HashMap<String, String> outTablesPartition = new HashMap<String, String>();

	// map: input table name -> TableDesc
	protected HashMap<String, TableDesc> inTableDesc = new HashMap<String, TableDesc>(); 
	
	// list: possible connections "ranked based on the frequencies of the connections" 
	protected List<Connection> trackerOpConnections = new ArrayList<Connection>(); 

	// unique operator id
	protected Identifier operatorId;

	// flag for root operator
	protected boolean isRoot = false;

	// flag if operator was executed
	private boolean isExecuted = false;
	
	// Error handling
	protected Error err = new Error();
	

	// constructors
	public AbstractTrackerOperator() {
		super();
	}

	// getters and setters
	public boolean isExecuted() {
		return isExecuted;
	}

	public void setExecuted(boolean isExecuted) {
		this.isExecuted = isExecuted;
	}
	
	public void setIsRoot(boolean isRoot){
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
	

	public void addOutTable(final String tableName, final StringTemplate tableDDL, final String partition) {
		outTables.put(tableName, tableDDL);
		outTablesPartition.put(tableName, partition);
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
	
	public List<Connection> getTrackerOpConnections(){
		return this.trackerOpConnections;
	} 
	
	public void setTrackerOpConnections(List<Connection> trackerOpConnections){
		this.trackerOpConnections = trackerOpConnections;
	}

	// methods
	public abstract AbstractExecuteOperator genDeployOperator(
			OperatorDesc operDesc, Map<Identifier, OperatorDesc> currentDeployment);

	/**
	 * Creates input and output tables for a given execute operator
	 * and returns table names of deployment
	 * 
	 * @param execOp
	 * @param operDesc
	 * @param currentDeployment
	 * @return
	 */
	protected Map<String, String> genInputAndOutput(AbstractExecuteOperator execOp,
			OperatorDesc operDesc, Map<Identifier, OperatorDesc> currentDeployment) {

		Identifier deployOperId = operDesc.getOperatorID();
		String deployURL = operDesc.getComputeNode().getUrl();
		
		HashMap<String, String> args = new HashMap<String, String>();

		// generate DDLs to open operator: input tables
		for (String tableName : this.inTables.keySet()) {
			TableDesc inTableDesc = this.inTableDesc.get(tableName);
			String deployTableName = genDeployTableName(tableName, deployOperId);

			
			if (inTableDesc.isTemp()) { // input table is a intermediate result  
				
				OperatorDesc sourceOp = currentDeployment.get(inTableDesc
						.getOperatorID());
				String sourceURL = sourceOp.getComputeNode().getUrl();
				String sourceTableName = inTableDesc.getTableName();
		
				Identifier sourceOperId = sourceOp.getOperatorID();

				String deployTableDDL = this.genDeployInputTableDDL(tableName,
						deployOperId, sourceTableName, sourceOperId, sourceURL);
				execOp.addOpenSQL(deployTableDDL);

				//if URL of source operator is local then use directly its output  
				if(isLocalInput(sourceURL, deployURL)){
					//get description of source Table
					Identifier sourceDeployOperId = sourceOp.getOperatorID();
					//set different deployment name
					deployTableName = genDeployTableName(sourceTableName, sourceDeployOperId);
				}

				
			
				//check wether partitioned?
				if(inTableDesc.isPartioned()){
					args.put(tableName, "SELECT * FROM " + deployTableName + " PARTITION (p"+inTableDesc.getPartition()+")");
		
				}else {
					args.put(tableName, "SELECT * FROM " + deployTableName);

				}
			} else { // input table is stored in an XDB instance
				URI connURI = inTableDesc.getURI();
				String sourceTableName = inTableDesc.getTableName();
				String sourceURL = connURI.getHost();
				String sourceDB = connURI.getPath().substring(1);

				String deployTableDDL = this.genDeployInputTableDDL(
							tableName, deployOperId, sourceTableName, sourceDB,
							sourceURL);
				execOp.addOpenSQL(deployTableDDL);

				//if URL of table is local then use directly its output  
				if(isLocalInput(sourceURL, deployURL)){
					deployTableName = sourceDB + "." + sourceTableName;
				}else if (sourceURL.equals(operDesc.getComputeNode())){
					deployTableName = sourceDB + "." + sourceTableName;
				}
				
				//TODO add partitioned to non temp
				args.put(tableName, deployTableName);
			}
		}

		// generate DDLs to open operator: output tables
		for (String tableName : this.outTables.keySet()) {

			String deployTableDDL = this.genDeployOutputTableDDL(tableName,
					deployOperId);

			String deployTableName = genDeployTableName(tableName, deployOperId);
			execOp.addOpenSQL(deployTableDDL);
			args.put(tableName, deployTableName);
		}

		// generate DDLs to close operator
		for (String tableName : this.inTables.keySet()) {
			execOp.addCloseSQL(genDropDeployTableDDL(tableName,
					deployOperId));
		}

		for (String tableName : this.outTables.keySet()) {
			execOp.addCloseSQL(genDropDeployTableDDL(tableName,
					deployOperId));
		}

		return args;
	}
	
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

	
		if(this.outTablesPartition.get(tableName) !=null){
			tableDDL.append(" "+this.outTablesPartition.get(tableName) );
		}
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
	 * Checks if sourceURL and deployURL are the same 
	 * or if sourceURL is loop back
	 * @param sourceURL
	 * @param deployURL
	 * @return
	 */
	protected boolean isLocalInput(String sourceURL, String deployURL){
		InetAddress sourceAddress = null;
		InetAddress deployAddress = null;
		try {
			sourceAddress = InetAddress.getByName(sourceURL);
			deployAddress = InetAddress.getByName(deployURL);
		} catch (UnknownHostException e) {
			return false;
		}

		if(sourceAddress.equals(deployAddress)){
			return true;
		}
		else if(sourceAddress.isLoopbackAddress()){
			return true;
		}
		return false;
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
}
