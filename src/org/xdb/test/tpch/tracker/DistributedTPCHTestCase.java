package org.xdb.test.tpch.tracker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.test.DistributedQueryTrackerTestCase;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.tracker.operator.TableDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

public abstract class DistributedTPCHTestCase extends
		DistributedQueryTrackerTestCase {

	// static fields
	private static final int NUMBER_COMPUTE_DBS = Config.TEST_NODE_COUNT
			* Config.TEST_SLOTS_PER_NODE; 
	private static final String TPCH_DB_NAME = Config.TEST_DB_NAME;
	private static Integer LAST_EXEC_OP_ID = 1;

	// test into
	protected int numberOfSubops = NUMBER_COMPUTE_DBS;
	
	// need to be set by child class
	protected int expectedCnt = 0;
	protected String resultDDL = "";
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
				+ "> " + resultDDL);
		unionOp.addOutTable(unionOutTableName, unionOutDDL);

		// DDL for all inputs of union
		for (int i = 0; i < NUMBER_COMPUTE_DBS; ++i) {
			String unionInTableName = getUnionInTableName(i);
			StringTemplate unionInDDL = new StringTemplate("<"
					+ unionInTableName + "> " + resultDDL);

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
					+ "> " + resultDDL);
			subOps[i].addOutTable(subOpOutTableName, subOpOutDDL);

			// DML for sub-query operators
			StringTemplate subqueryDMLTempl = new StringTemplate(this.subqueryDML);
			HashMap<String, String> args = new HashMap<String, String>();
			
			// name of database must be different if we have multiple slots (i.e., databases) per node
			// however if we run local, we just use the normal database name of the configuration
			String dbName = TPCH_DB_NAME;
			if(!this.isRunLocal() && Config.TEST_SLOTS_PER_NODE>1){
				dbName = TPCH_DB_NAME+((i%Config.TEST_SLOTS_PER_NODE)+1);
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
					this.getComputeSlot(i/Config.TEST_SLOTS_PER_NODE));
			currentDeployment.put(trackerOpId, executeOperDesc);
		}

		// create deployment for union operator
		Identifier trackerOpId = unionOp.getOperatorId();
		Identifier execOpId = trackerOpId.clone().append(nextExecOpId());
		OperatorDesc executeOperDesc = new OperatorDesc(execOpId,
				this.getComputeSlot(0));
		currentDeployment.put(trackerOpId, executeOperDesc);

		return currentDeployment;
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

		// read result (if run local, else just clean up)
		if (this.isRunLocal()) {
			final Map<Identifier, OperatorDesc> currentDeployment = qPlan
					.getCurrentDeployment();
			Identifier resultTable = currentDeployment.get(resultOpId)
					.getOperatorID();
			final ResultSet rs = this
					.executeComputeQuery("SELECT COUNT(*) FROM " + resultTable
							+ "_" + resultTableName);
			int actualCnt = 0;
			if (rs.next()) {
				actualCnt = rs.getInt(1);
			}

			// clean plan
			this.assertNoError(qPlan.cleanPlan());

			if (expectedCnt > 0) {
				// verify results
				assertEquals(expectedCnt, actualCnt);
			}
		} else {
			// clean plan
			this.assertNoError(qPlan.cleanPlan());
		}
	}
}
