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
	// constants
	private static final String TAB1 = "TAB1";
	private static final String SQL1 = "SQL1";
	public static final String OUT_SUFFIX = "OUT";

	// compile plan
	private CompilePlan compilePlan;

	// generated query tracker plan
	private QueryTrackerPlan qtPlan = new QueryTrackerPlan();

	// sources / consumers in query tracker plan: operator Id -> operator IDs
	private Map<Identifier, Set<Identifier>> sources = new HashMap<Identifier, Set<Identifier>>();
	private Map<Identifier, Set<Identifier>> consumers = new HashMap<Identifier, Set<Identifier>>();

	// mapping: compile operator ID -> tracker operator ID
	private Map<Identifier, Identifier> compileOp2trackerOp = new HashMap<Identifier, Identifier>();

	// roots of sub-plans in compile plan: each sub-plan results in one tracker
	// operator
	private List<AbstractCompileOperator> splitCompileOps;

	// templates for SQL code generation
	private final StringTemplate sqlDMLTemplate = new StringTemplate(
			"INSERT INTO <<" + TAB1 + ">> (<" + SQL1 + ">)");
	private final StringTemplate sqlDDLTemplate = new StringTemplate("<<"
			+ TAB1 + ">> <" + SQL1 + ">");

	// last error
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
		// split compile plan into sub-plans
		this.splitCompileOps = extractSplitOps();

		// for each sub-plan (which has splitOp as root) generate a tracker
		// operator
		for (AbstractCompileOperator splitCompileOp : splitCompileOps) {
			AbstractTrackerOperator trackerOp;
			try {
				trackerOp = generateTrackerOp(splitCompileOp);
			} catch (URISyntaxException e) {
				String[] args = { e.toString() };
				this.err = new Error(EnumError.COMPILER_GENERIC, args);
				return this.err;
			}

			// add mapping: compile operator -> tracker operator
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
	 * Generates a tracker operator for each sub-plan (given as root of sub-plan
	 * in compile plan)
	 * 
	 * @param compileOp
	 * @return
	 * @throws URISyntaxException
	 */
	private AbstractTrackerOperator generateTrackerOp(
			AbstractCompileOperator compileOp) throws URISyntaxException {

		// generate a new MySQL operator
		MySQLTrackerOperator trackerOp = new MySQLTrackerOperator();
		this.qtPlan.addOperator(trackerOp);

		// generate execute DML statement
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
			TableOperator inputTableOp = null;
			String inDDL = null;
			String inTable = inputCompileOp.getOperatorId().clone().toString();

			// if leaf of sub-plan is a table: use other table name with "_" as
			// prefix
			if (inputCompileOp.getType().equals(EnumOperator.TABLE)) {
				inputTableOp = (TableOperator) inputCompileOp;
				inTable = TableOperator.TABLE_PREFIX + inTable;
				inDDL = inputTableOp.getTable().toSqlString();
			}
			// else leaf of sub-plan is an intermediate result
			else {
				inDDL = generateResultDDL(inputCompileOp);
			}

			// generate input DDL
			args.put(SQL1, inDDL);
			args.put(TAB1, inTable);
			inDDL = this.sqlDDLTemplate.toString(args);
			trackerOp.addInTable(inTable, new StringTemplate(inDDL));

			// if: leaf of sub-plan is a table: add catalog info (table name and
			// URL)
			if (inputCompileOp.getType().equals(EnumOperator.TABLE)) { // table
				URI connURI = URI.create(inputTableOp.getConnection().getUrl());
				TableDesc tableDesc = new TableDesc(inputTableOp.getTable()
						.getSourceName(), connURI);
				trackerOp.setInTableSource(inTable, tableDesc);
			}
			// else: use intermediate result as table (with _OUT suffix) and
			// register dependency
			else {
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

	/**
	 * Register dependency
	 * 
	 * @param dependencies
	 * @param fromOp
	 * @param toOp
	 */
	private void addTrackerDependency(
			Map<Identifier, Set<Identifier>> dependencies, Identifier fromOp,
			Identifier toOp) {
		Set<Identifier> opIds;
		if (!dependencies.containsKey(toOp)) {
			opIds = new HashSet<Identifier>();
			dependencies.put(toOp, opIds);
		} else {
			opIds = dependencies.get(toOp);
		}
		opIds.add(fromOp);
	}

	/**
	 * Get input operators for given split Operator (i.e., root of sub-plan)
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
	 * Get input operators for given compile operator (recursively called)
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
	 * Generate DDL statement to store output of a tracker operator from result
	 * description of compile operator (i.e., root of sub-plan)
	 * 
	 * @param compileOp
	 * @return
	 */
	private String generateResultDDL(AbstractCompileOperator compileOp) {
		return compileOp.getResult().toSqlString();
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
	 * Extract root operators of sub-plans in compile plan which are
	 * materialized (i.e., operators that have multiple consumers or that are
	 * explicitly marked as materialized)
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
