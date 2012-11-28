package org.xdb.funsql.codegen;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.AbstractTrackerOperator;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.tracker.operator.TableDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;
import org.xdb.error.EnumError;
import org.xdb.error.Error;

/**
 * Generates a query tracker plan for a given compile plan
 * 
 * @author cbinnig
 * 
 */
public class CodeGenerator {
	private static final String TAB1 = "TAB1";
	private static final String SQL1 = "SQL1";
	public static final String OUT_SUFFIX = "OUT";

	private CompilePlan compilePlan;
	private QueryTrackerPlan qtPlan = new QueryTrackerPlan();

	private Map<Identifier, Set<Identifier>> sources = new HashMap<Identifier, Set<Identifier>>();
	private Map<Identifier, Set<Identifier>> consumers = new HashMap<Identifier, Set<Identifier>>();

	private Map<Identifier, Identifier> compileOp2trackerOp = new HashMap<Identifier, Identifier>();
	private List<AbstractCompileOperator> splitCompileOps;

	private final StringTemplate sqlDMLTemplate = new StringTemplate(
			"INSERT INTO <<" + TAB1 + ">> (<" + SQL1 + ">)");
	private final StringTemplate sqlDDLTemplate = new StringTemplate("<<"
			+ TAB1 + ">> <" + SQL1 + ">");

	private Error err;

	// constructor
	public CodeGenerator(CompilePlan compilePlan) {
		super();
		this.compilePlan = compilePlan;
		this.err = new Error();
	}

	// getter and setter
	public QueryTrackerPlan getQueryTrackerPlan() {
		return this.qtPlan;
	}

	// methods
	/**
	 * Generate QueryTrackerPlan from CompilePlan
	 * 
	 * @return
	 */
	public Error generate() {
		// split compile plan and generate tracker operators
		this.splitCompileOps = extractSplitOps();
		for (AbstractCompileOperator splitCompileOp : splitCompileOps) {
			AbstractTrackerOperator trackerOp;
			try {
				trackerOp = generateTrackerOp(splitCompileOp);
			} catch (URISyntaxException e) {
				String[] args = {e.toString()};
				this.err = new Error(EnumError.COMPILER_GENERIC, args);
				return this.err;
			}

			this.compileOp2trackerOp.put(splitCompileOp.getOperatorId(),
					trackerOp.getOperatorId());
		}

		// add sources and consumers to plan
		for (Map.Entry<Identifier, Set<Identifier>> entry : this.sources
				.entrySet()) {
			this.qtPlan.setSources(entry.getKey(), entry.getValue());
		}

		for (Map.Entry<Identifier, Set<Identifier>> entry : this.consumers
				.entrySet()) {
			this.qtPlan.setConsumers(entry.getKey(), entry.getValue());
		}

		return err;
	}

	/**
	 * Generates a tracker operator for each materialized sub-plan
	 * 
	 * @param compileOp
	 * @return
	 * @throws URISyntaxException 
	 */
	private AbstractTrackerOperator generateTrackerOp(
			AbstractCompileOperator compileOp) throws URISyntaxException {
		MySQLTrackerOperator trackerOp = new MySQLTrackerOperator();
		this.qtPlan.addOperator(trackerOp);

		// add execute DML statement
		String outTable = compileOp.getOperatorId().clone().append(OUT_SUFFIX)
				.toString();
		String executeDML = generateExecuteDML(compileOp);
		HashMap<String, String> args = new HashMap<String, String>();
		args.put(SQL1, executeDML);
		args.put(TAB1, outTable);
		executeDML = this.sqlDMLTemplate.toString(args);
		trackerOp.addExecuteSQL(new StringTemplate(executeDML));

		// add out table DDL statement
		String outDDL = generateResultDDL(compileOp);
		args.put(SQL1, outDDL);
		outDDL = this.sqlDDLTemplate.toString(args);
		trackerOp.addOutTable(outTable, new StringTemplate(outDDL));

		// add input table DDL statements and input table descriptions
		Set<AbstractCompileOperator> inputCompileOps = getInputOps(compileOp);
		for (AbstractCompileOperator inputCompileOp : inputCompileOps) {
			String inDDL = generateResultDDL(inputCompileOp);
			String inTable = inputCompileOp.getOperatorId().clone().toString();
			if (inputCompileOp.getType().equals(EnumOperator.TABLE)) {
				inTable = TableOperator.TABLE_PREFIX + inTable;
			}
			
			args.put(SQL1, inDDL);
			args.put(TAB1, inTable);

			inDDL = this.sqlDDLTemplate.toString(args);	
			trackerOp.addInTable(inTable, new StringTemplate(inDDL));

			if (inputCompileOp.getType().equals(EnumOperator.TABLE)) { // table
				TableOperator inputTableOp = (TableOperator) inputCompileOp;
				URI connURI = URI.create(inputTableOp.getConnection().getUrl());
				TableDesc tableDesc = new TableDesc(inputTableOp.getTable()
						.getSourceName(), connURI);
				trackerOp.setInTableSource(inTable, tableDesc);
			} else { // other operators
				String inTableRemote = inputCompileOp.getOperatorId().clone()
						.append(OUT_SUFFIX).toString();
				Identifier inTrackerOpId = this.compileOp2trackerOp
						.get(inputCompileOp.getOperatorId());
				TableDesc tableDesc = new TableDesc(inTableRemote,
						inTrackerOpId);
				trackerOp.setInTableSource(inTable, tableDesc);
				this.addTrackerDependency(inTrackerOpId,
						trackerOp.getOperatorId());
			}
		}

		return trackerOp;
	}

	/**
	 * Register dependency
	 * 
	 * @param fromOp
	 * @param toOp
	 */
	private void addTrackerDependency(Identifier fromOp, Identifier toOp) {
		addTrackerDependency(this.sources, fromOp, toOp);
		addTrackerDependency(this.consumers, toOp, fromOp);
	}

	private void addTrackerDependency(Map<Identifier, Set<Identifier>> sources,
			Identifier fromOp, Identifier toOp) {
		Set<Identifier> sourceIds;
		if (!sources.containsKey(toOp)) {
			sourceIds = new HashSet<Identifier>();
			sources.put(toOp, sourceIds);
		} else {
			sourceIds = sources.get(toOp);
		}
		sourceIds.add(fromOp);
	}

	/**
	 * Get input operators for given split Operator
	 * 
	 * @param compileOp
	 * @return
	 */
	private Set<AbstractCompileOperator> getInputOps(
			AbstractCompileOperator compileOp) {

		Set<AbstractCompileOperator> inputOps = new HashSet<AbstractCompileOperator>();
		for (AbstractCompileOperator childOp : compileOp.getChildren()) {
			getInputOps(inputOps, childOp);
		}
		return inputOps;
	}

	/**
	 * Get input operators for given split Operator
	 * 
	 * @param inputOps
	 * @param compileOp
	 */
	private void getInputOps(Set<AbstractCompileOperator> inputOps,
			AbstractCompileOperator compileOp) {

		if (this.splitCompileOps.contains(compileOp) || compileOp.isLeaf()) {
			inputOps.add(compileOp);
			return;
		}

		for (AbstractCompileOperator childOp : compileOp.getChildren()) {
			getInputOps(inputOps, childOp);
		}
	}

	/**
	 * Generate DDL statement to store result of operator
	 * 
	 * @param compileOp
	 * @return
	 */
	private String generateResultDDL(AbstractCompileOperator compileOp) {
		return compileOp.getResult(0).toSqlString();
	}

	/**
	 * Generate DML statement for execution
	 * 
	 * @param compileOp
	 * @return
	 */
	private String generateExecuteDML(AbstractCompileOperator compileOp) {
		HashMap<String, String> args = new HashMap<String, String>();
		StringTemplate sqlTemplate = new StringTemplate(compileOp.toSqlString());
		for (AbstractCompileOperator childOp : compileOp.getChildren()) {
			if (!this.splitCompileOps.contains(childOp)) {
				args.put(childOp.getOperatorId().toString(),
						this.generateExecuteDML(childOp));
			}
		}
		return sqlTemplate.toString(args);
	}

	/**
	 * Extract operators which are materialized from compile plan
	 * 
	 * @return
	 */
	private List<AbstractCompileOperator> extractSplitOps() {
		SplitPlanVisitor splitVisitor = new SplitPlanVisitor(null);
		for (AbstractCompileOperator root : this.compilePlan.getRoots()) {
			splitVisitor.reset(root);
			splitVisitor.visit();
		}

		return splitVisitor.getSplitOps();
	}
}
