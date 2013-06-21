package org.xdb.test.tpch.tracker;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.metadata.Connection;
import org.xdb.store.EnumStore;
import org.xdb.test.DistributedXDBTestCase;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.AbstractTrackerOperator;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.tracker.operator.TableDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

public abstract class DistributedTPCHTestCase extends
		DistributedXDBTestCase {

	// static fields
	protected static final int NUMBER_COMPUTE_DBS = Config.TEST_NODE_COUNT
			* Config.TEST_PARTS_PER_NODE; 
	private static final String TPCH_DB_NAME = Config.TEST_DB_NAME;
	private static Integer LAST_EXEC_OP_ID = 1; 
	
	private List<Connection> allConnections = new ArrayList<Connection>();


	// test into
	protected int numberOfSubops = NUMBER_COMPUTE_DBS;
	
	// need to be set by child class
	protected int expectedCnt = 0;
	protected String subqueryDDL = "";
	protected String unionDDL = "";
	protected String subqueryDML = "";
	protected String unionPreDML = "SELECT * FROM ";
	protected String unionPostDML = ";";

	// constructor
	public DistributedTPCHTestCase(int expectedCnt) {
		super(Config.TEST_NODE_COUNT);
		this.expectedCnt = expectedCnt;
	}

	// getters and setters
	protected int nextExecOpId() {
		return LAST_EXEC_OP_ID++;
	}

	protected String getSubqueryOutTableName(int i) {
		return "SUBQ_OUT" + i;
	}

	protected String getUnionInTableName(int i) {
		return "UNION_IN" + i;
	}

	protected String getUnionInTableName() {
		return "UNION_IN";
	}
	
	protected String getUnionOutTableName() {
		return "UNION_OUT";
	}

	// methods
	/**
	 * Connects MySQLTrackerOperators in QueryTrackerPlan: All sub-queries are
	 * consumed by union query
	 * 
	 * @param qPlan
	 * @param subqueryOps
	 * @param unionOp
	 */
	protected void connectOps(QueryTrackerPlan qPlan,
			MySQLTrackerOperator[] subqueryOps, MySQLTrackerOperator unionOp) {
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			final Set<Identifier> subqueryConsumer = new HashSet<Identifier>();
			subqueryConsumer.add(unionOp.getOperatorId());
			qPlan.setConsumers(subqueryOps[i].getOperatorId(), subqueryConsumer);
		}

		final Set<Identifier> unionOpSources = new HashSet<Identifier>();
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			unionOpSources.add(subqueryOps[i].getOperatorId());
			qPlan.setSources(unionOp.getOperatorId(), unionOpSources);

			String unionOutTableName = getSubqueryOutTableName(i);
			String unionInTableName = getUnionInTableName(i);
			unionOp.setInTableSource(unionInTableName, new TableDesc(
					unionOutTableName, subqueryOps[i].getOperatorId()));
		}
	}

	/**
	 * Creates a MySQLTrackerOperator as a union over all sub-queries and adds
	 * it to the Query Tracker Plan
	 * 
	 * @param qPlan
	 * @param unionOp
	 */
	protected void createUnionOp(QueryTrackerPlan qPlan,
			MySQLTrackerOperator unionOp) {
		// DDL for output of union
		String unionOutTableName = getUnionOutTableName();
		StringTemplate unionOutDDL = new StringTemplate("<" + unionOutTableName
				+ "> " + unionDDL);
		unionOp.addOutTable(unionOutTableName, unionOutDDL);

		// DDL for all inputs of union
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			String unionInTableName = getUnionInTableName(i);
			StringTemplate unionInDDL = new StringTemplate("<"
					+ unionInTableName + "> " + subqueryDDL);

			unionOp.addInTable(unionInTableName, unionInDDL);
		}

		// DML for union query
		StringBuffer unionTMPDDL = new StringBuffer("INSERT INTO <");
		unionTMPDDL.append(unionOutTableName);
		unionTMPDDL.append("> ");
		unionTMPDDL.append(this.unionPreDML); 
		unionTMPDDL.append(" ( ");
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			String unionInTableName = getUnionInTableName(i);
 
			unionTMPDDL.append("(<");
			unionTMPDDL.append(unionInTableName);
			unionTMPDDL.append(">)");

			if (i < NUMBER_COMPUTE_DBS - 1) {
				unionTMPDDL.append(" UNION ALL ");
			}
		}
		unionTMPDDL.append(") as uniontmp ");
		unionTMPDDL.append(this.unionPostDML);
		StringTemplate unionDML = new StringTemplate(unionTMPDDL.toString());
		unionOp.addExecuteSQL(unionDML);

		// add operator to plan
		qPlan.addOperator(unionOp);
	}

	/**
	 * Creates MySQLTrackerOperators for all sub-queries that can be executed on
	 * one database instance and adds them to the Query Tracker Plan
	 * 
	 * @param qPlan
	 * @param subOps
	 */
	protected void createSubqueryOps(QueryTrackerPlan qPlan,
			MySQLTrackerOperator[] subOps) {
		
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			subOps[i] = new MySQLTrackerOperator();

			// DDL for output of sub-query operator
			String subOpOutTableName = getSubqueryOutTableName(i);
			StringTemplate subOpOutDDL = new StringTemplate("<" + subOpOutTableName
					+ "> " + subqueryDDL);
			subOps[i].addOutTable(subOpOutTableName, subOpOutDDL);

			// DML for sub-query operators
			StringTemplate subqueryDMLTempl = new StringTemplate(this.subqueryDML);
			HashMap<String, String> args = new HashMap<String, String>();
			
			// name of database must be different if we have multiple slots (i.e., databases) per node
			// however if we run local, we just use the normal database name of the configuration
			String dbName = TPCH_DB_NAME;
			if(!this.isRunLocal()){
				dbName+=(i+1);
			}
			
			args.put("TPCH_DB_NAME", dbName);
			
			StringTemplate subqueryDML = new StringTemplate("insert into <"
					+ subOpOutTableName + "> " + subqueryDMLTempl.toString(args));

			subOps[i].addExecuteSQL(subqueryDML);

			qPlan.addOperator(subOps[i]);
		}
	}

	/**
	 * Creates static deployment for operators in order to execute sub-queries
	 * locally and union on first compute node
	 * 
	 * @param currentDeployment
	 */
	protected Map<Identifier, OperatorDesc> createDeployment(
			MySQLTrackerOperator[] subqueryOps, MySQLTrackerOperator unionOp) {
		Map<Identifier, OperatorDesc> currentDeployment = new HashMap<Identifier, OperatorDesc>();

		// create deployment for sub-queries operator
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			MySQLTrackerOperator subqueryOp = subqueryOps[i];
			Identifier trackerOpId = subqueryOp.getOperatorId();
			Identifier execOpId = trackerOpId.clone().append(nextExecOpId());

			OperatorDesc executeOperDesc = new OperatorDesc(execOpId,
					this.getComputeNode(i/Config.TEST_PARTS_PER_NODE));
			currentDeployment.put(trackerOpId, executeOperDesc);
		}

		// create deployment for union operator
		Identifier trackerOpId = unionOp.getOperatorId();
		Identifier execOpId = trackerOpId.clone().append(nextExecOpId());
		OperatorDesc executeOperDesc = new OperatorDesc(execOpId,
				this.getComputeNode(0));
		currentDeployment.put(trackerOpId, executeOperDesc);

		return currentDeployment;
	} 
	
	protected void setConnectionList(Map<Identifier, AbstractTrackerOperator> trackerOps, List<Identifier> opIds, Identifier unionOpId) {
	      
		
		for(int i=0; i < NUMBER_COMPUTE_DBS; i++){
	    	  
			  AbstractTrackerOperator trackerOp = trackerOps.get(opIds.get(i)); 
			  // Set the connection list 
			  List<Connection> opConnections = new ArrayList<Connection>();  
			  ComputeNodeDesc computeNodeDesc = this.getComputeNode(i); 
			  // Create the first connection 
			  Connection conn = new Connection("Connection_"+i,computeNodeDesc.getUrl(),
					  Config.METADATA_USER, Config.METADATA_PASSWORD, EnumStore.MYSQL); 
			  
			  opConnections.add(conn);
			  opConnections.add(conn);
              this.allConnections.add(conn);
			  trackerOp.setTrackerOpConnections(opConnections);	  
	      }   
		  
		  // Set the connection of union operator  
		  ComputeNodeDesc computeNodeDesc = this.getComputeNode(0); 
		  Connection conn = new Connection("Connection_0",computeNodeDesc.getUrl(),
				  Config.METADATA_USER, Config.METADATA_PASSWORD, EnumStore.MYSQL);  
		  AbstractTrackerOperator unionTrackerOp = trackerOps.get(unionOpId);   
		  List<Connection> opConnections = new ArrayList<Connection>();  
		  opConnections.add(conn); 
		  opConnections.add(conn); 
          this.allConnections.add(conn);
		  unionTrackerOp.setTrackerOpConnections(opConnections); 
		  	  
	}
	
	private static String prettyPrintResultSet(ResultSet resultSet, int rowCount) throws SQLException{
		StringBuilder returnValue = new StringBuilder();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnCount = rsmd.getColumnCount();
		for (int j = 1; j <= columnCount; j++) {
			returnValue.append(rsmd.getColumnName(j));
			if (j < columnCount) {
				returnValue.append('|');
			}
		}
		returnValue.append("\n");
		
		int i = 0;
		while (resultSet.next() && i < rowCount){
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				returnValue.append(resultSet.getString(j));
				if (j < columnCount) {
					returnValue.append('|');
				}
			}
			returnValue.append("\n");
			i++;
		}
		return returnValue.toString();
	}

	/**
	 * Executes the Query Tracker Plan and checks if results size is correct
	 * 
	 * @param qPlan
	 * @param unionOp
	 * @throws SQLException
	 */
	protected void executeQuery(QueryTrackerPlan qPlan,
			Map<Identifier, OperatorDesc> deployment, Identifier resultOpId,
			String resultTableName) throws SQLException {

		// deploy plan to compute nodes
		Error err = qPlan.deployPlan(deployment);
		if (err.isError())
			qPlan.cleanPlanOnError();
		this.assertNoError(err);

		// execute plan using query tracker
		err = qPlan.executePlan();
		if (err.isError())
			qPlan.cleanPlanOnError();
		this.assertNoError(err);

		final Map<Identifier, OperatorDesc> currentDeployment = qPlan
				.getCurrentDeployment();
		OperatorDesc rootDesc = currentDeployment.get(resultOpId);
		Identifier resultTable = rootDesc.getOperatorID();
		String rootUrl = "jdbc:mysql://"+rootDesc.getComputeSlot().getUrl()+"/"+Config.COMPUTE_DB_NAME;
		final ResultSet rs = this
				.executeComputeQuery(rootUrl, "SELECT COUNT(*) FROM " + resultTable
						+ "_" + resultTableName);
		int actualCnt = 0;
		if (rs.next()) {
			actualCnt = rs.getInt(1);
		}

		if (expectedCnt > 0) {
			// verify results
			assertEquals(expectedCnt, actualCnt);
		}
		if (actualCnt>0){
			final ResultSet results = this.executeComputeQuery(rootUrl,"SELECT * FROM " + resultTable + "_" + resultTableName);
			System.out.println(prettyPrintResultSet(results, 10));
		}

		// clean plan
		this.assertNoError(qPlan.cleanPlan());
	} 
	

	protected Error executePlanTestQ2FT (QueryTrackerPlan qplan){

		Error err = new Error();

		// 2. Deploy query tracker plan
		err = qplan.deployPlan();
		if (err.isError()) {
			qplan.cleanPlanOnError();
			return err;
		}
        
		// Run the failure simulator 
		
		//FailureSimulatorFT failureSimulatorFT = new FailureSimulatorFT(1, qplan, this.allConnections, 1000); 
		//failureSimulatorFT.start();
		
		// 3. Execute query tracker plan
		err = qplan.executePlan();
		if (err.isError()) {
			qplan.cleanPlanOnError();
			return err;
		}
        
		// 4. Clean query tracker plan
		err = qplan.cleanPlan();
		if (err.isError()) {
			qplan.cleanPlanOnError();
			return err;
		}

		return err;

	}
}
