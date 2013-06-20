package org.xdb.test.tpch.tracker;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.AbstractTrackerOperator;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.tracker.operator.TableDesc;
import org.xdb.tracker.operator.udf.LevenshteinTrackerOp;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

/**
 * Run a basket analysis using a UDF to determine the string similarity of two
 * products in the same order.
 * 
 * Idea: Only suggest products which have a similar "type".
 * 
 * @author cbinnig
 * 
 */
public class TestTPCHBasketAnalysis extends DistributedTPCHTestCase {
	private static final String UDF_OUT_TABLE = "udf_out";
	private static final String UDF_IN_TABLE = "udf_in";

	// constructor
	public TestTPCHBasketAnalysis() {
		super(10);
		this.subqueryDDL = "(p1_key INTEGER, p2_key INTEGER, p1_type VARCHAR(25), p2_type VARCHAR(25), frequency DECIMAL(65,2))";
		this.subqueryDML = "select l1.l_partkey as p1_key, l2.l_partkey as p2_key, "
				+ "p1.p_type as p1_type, p2.p_type as p2_type, count(*) as frequency "
				+ "from "
				+ "<TPCH_DB_NAME>"
				+ ".lineitem l1, "
				+ "<TPCH_DB_NAME>"
				+ ".lineitem l2, "
				+ "<TPCH_DB_NAME>"
				+ ".part p1, "
				+ "<TPCH_DB_NAME>"
				+ ".part p2 "
				+ "where l1.l_orderkey = l2.l_orderkey "
				+ "and l1.l_partkey = p1.p_partkey "
				+ "and l2.l_partkey = p2.p_partkey "
				+ "and l1.l_partkey != l2.l_partkey "
				+ "and l1.l_shipdate > date '1998-03-15' "
				+ "and l2.l_shipdate > date '1998-03-15' "
				+ "group by l1.l_partkey, l2.l_partkey, p1.p_type, p2.p_type "
				+ "having count(*)>=2; ";

		this.unionDDL = "(p1_key INTEGER, p2_key INTEGER, p1_type VARCHAR(25), p2_type VARCHAR(25), frequency DECIMAL(65,2))";
		this.unionPreDML = "SELECT p1_key, p2_key, p1_type, p2_type, sum(frequency) as frequency FROM ";
		this.unionPostDML = "group by p1_key, p2_key, p1_type, p2_type order by frequency desc limit 1000;";
	}

	// methods
	@Test
	public void testBasketAnalysis() throws Exception {
		// assign plan to query tracker
		QueryTrackerNode qTracker = this.getQueryTrackerServer().getNode();
		QueryTrackerPlan qPlan = new QueryTrackerPlan();
		qPlan.assignTracker(qTracker);

		// create one operator per database node
		MySQLTrackerOperator[] subqueryOps = new MySQLTrackerOperator[this.numberOfSubops];
		createSubqueryOps(qPlan, subqueryOps);

		// create union operator to collect results from all compute nodes
		MySQLTrackerOperator unionOp = new MySQLTrackerOperator();
		createUnionOp(qPlan, unionOp);

		// connect operators
		connectOps(qPlan, subqueryOps, unionOp);

		// create deployment and execute plan
		Map<Identifier, OperatorDesc> deployment = this.createDeployment(
				subqueryOps, unionOp);

		// create LevenshteinTrackerOp
		LevenshteinTrackerOp udfOp = addLevenshteinOp(qPlan, deployment,
				unionOp);

		executeQuery(qPlan, deployment, udfOp.getOperatorId(), UDF_OUT_TABLE);
	}

	/**
	 * Adds UDF operator to query tracker plan
	 * 
	 * @param qPlan
	 * @param deployment
	 * @param unionOp
	 * @return
	 */
	protected LevenshteinTrackerOp addLevenshteinOp(QueryTrackerPlan qPlan,
			Map<Identifier, OperatorDesc> deployment,
			AbstractTrackerOperator unionOp) {

		LevenshteinTrackerOp udfOp = new LevenshteinTrackerOp(UDF_IN_TABLE,
				UDF_OUT_TABLE);
		qPlan.addOperator(udfOp);

		// add input and output tables
		StringTemplate udfOutDDL = new StringTemplate("<" + UDF_OUT_TABLE
				+ "> " + subqueryDDL);
		udfOp.addOutTable(UDF_OUT_TABLE, udfOutDDL);

		StringTemplate udfInDDL = new StringTemplate("<" + UDF_IN_TABLE + "> "
				+ subqueryDDL);
		udfOp.addInTable(UDF_IN_TABLE, udfInDDL);

		// connect to unionOp to udfOp
		final Set<Identifier> udfOpSources = new HashSet<Identifier>();
		udfOpSources.add(unionOp.getOperatorId());
		qPlan.setSources(udfOp.getOperatorId(), udfOpSources);
		udfOp.setInTableSource(
				UDF_IN_TABLE,
				new TableDesc(this.getUnionOutTableName(), unionOp
						.getOperatorId()));

		final Set<Identifier> unionConsumers = new HashSet<Identifier>();
		unionConsumers.add(udfOp.getOperatorId());
		qPlan.setConsumers(unionOp.getOperatorId(), unionConsumers);

		// create deployment
		Identifier trackerOpId = udfOp.getOperatorId();
		Identifier execOpId = trackerOpId.clone().append(nextExecOpId());
		OperatorDesc executeOperDesc = new OperatorDesc(execOpId,
				this.getComputeNode(0));
		deployment.put(trackerOpId, executeOperDesc);

		return udfOp;
	}
}
