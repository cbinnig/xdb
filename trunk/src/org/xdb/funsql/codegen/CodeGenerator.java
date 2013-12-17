package org.xdb.funsql.codegen;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.xdb.Config;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.metadata.Connection;
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

	// roots of sub-plans: each sub-plan results in one tracker operator
	private List<Identifier> splitOpIds;

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
		this.splitOpIds = extractSplitOps();

		// optimize plan for code generation
		this.optimize();
		if (this.err.isError())
			return this.err;

		// rename attributes to original names
		this.rename();
		if (this.err.isError())
			return this.err;

		// trace compile plan before generating a tracker plan
		if (Config.TRACE_CODEGEN_PLAN) {
			this.compilePlan.tracePlan(compilePlan.getClass()
					.getCanonicalName() + "_CODEGEN");
		}

		// generate a tracker operators
		this.generateTrackerPlan();
		if (this.err.isError())
			return this.err;

		return this.err;
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

		// add DML statement for execution of operator
		String outTableName = compileOp.getOperatorId().clone()
				.append(OUT_SUFFIX).toString();
		String executeDML = generateExecuteDML(compileOp);
		HashMap<String, String> args = new HashMap<String, String>();
		args.put(SQL1, executeDML);
		args.put(TAB1, outTableName);
		executeDML = this.sqlDMLTemplate.toString(args);
		trackerOp.addExecuteSQL(new StringTemplate(executeDML));

		// add DDL statement for creating output table
		String outDDL = generateResultDDL(compileOp);
		args.put(SQL1, outDDL);
		outDDL = this.sqlDDLTemplate.toString(args);
		trackerOp.addOutTable(outTableName, new StringTemplate(outDDL));

		// add DDL statements for each input table
		Set<AbstractCompileOperator> inputCompileOps = getInputOps(compileOp);
		for (AbstractCompileOperator inputCompileOp : inputCompileOps) {

			// generate input DDL
			TableOperator inputTableOp = null;
			String inDDL = null;
			String inTableName = inputCompileOp.getOperatorId().clone()
					.toString();

			// if input is a table, use other table name for input
			if (inputCompileOp.getType().equals(EnumOperator.TABLE)) {
				inputTableOp = (TableOperator) inputCompileOp;
				inTableName = TableOperator.TABLE_PREFIX + inTableName;
				inDDL = inputTableOp.getTable().toSqlString();
			}
			// else is input is a sub-plan
			else {
				inDDL = generateResultDDL(inputCompileOp);
			}

			args.put(SQL1, inDDL);
			args.put(TAB1, inTableName);
			inDDL = this.sqlDDLTemplate.toString(args);
			trackerOp.addInTable(inTableName, new StringTemplate(inDDL));

			// if input is a table: add catalog info to tracker operator
			if (inputCompileOp.getType().equals(EnumOperator.TABLE)) { // table
				
				// Add all connections to the table description
				List<Connection> tableConnections = inputTableOp
						.getConnections();
				List<URI> uris = new ArrayList<URI>();
				for (Connection connection : tableConnections) {
					uris.add(URI.create(connection.getUrl()));
				}

				TableDesc tableDesc = new TableDesc(inputTableOp.getTable()
						.getName(), uris);

				trackerOp.setInTableSource(inTableName, tableDesc);
			}
			// else: use intermediate result as table (with _OUT suffix)
			else {
				String inTableNameRemote = inputCompileOp.getOperatorId()
						.clone().append(OUT_SUFFIX).toString();
				Identifier inTrackerOpId = this.compileOp2trackerOp
						.get(inputCompileOp.getOperatorId());
				TableDesc tableDesc = new TableDesc(inTableNameRemote,
						inTrackerOpId);
				trackerOp.setInTableSource(inTableName, tableDesc);
				this.addTrackerDependency(inTrackerOpId,
						trackerOp.getOperatorId());
			}
		}

		// add connections from compile plan to tracker operator
		Set<AbstractCompileOperator> queryTrackerCompileOps = getQueryTrackerCompileOps(compileOp);
		List<Connection> trackerOpConnections = extractTrackerOpConnections(queryTrackerCompileOps);
		trackerOp.setTrackerOpConnections(trackerOpConnections);

		return trackerOp;
	}

	/**
	 * Union all the connections from a sub-plan, rank them based on the
	 * frequencies of the connections and return a list of "ranked" connections
	 * as a possible connections for a tracker operator.
	 * 
	 * @param inputCompileOps
	 *            : a set of compile operators
	 * 
	 * @rerun a set of connections
	 */
	private List<Connection> extractTrackerOpConnections(
			Set<AbstractCompileOperator> inputCompileOps) {

		// A hash map used to store the connections and their counts/repetitions
		// .
		Map<Connection, Integer> connectionsCounterMap = new HashMap<Connection, Integer>();
		// Going through every compile operator, get its wished connection and
		// store them in a hash map to count.
		for (AbstractCompileOperator abstractCompileOperator : inputCompileOps) {

			Set<Connection> compileOpConnections = abstractCompileOperator
					.getWishedConnections();

			for (Connection connection : compileOpConnections) {
				if (connectionsCounterMap.containsKey(connection)) {
					connectionsCounterMap.put(connection,
							connectionsCounterMap.get(connection) + 1);

				} else {
					connectionsCounterMap.put(connection, 1);
				}
			}

		}

		// Sort the hash map to get the ranked connections
		List<Connection> rankedConnections = sortHashMapConnections(connectionsCounterMap
				.entrySet());

		return rankedConnections;
	}

	private List<Connection> sortHashMapConnections(
			Set<Entry<Connection, Integer>> entrySet) {
		List<Entry<Connection, Integer>> connections = new ArrayList<Entry<Connection, Integer>>(
				entrySet);
		Collections.sort(connections,
				new Comparator<Entry<Connection, Integer>>() {
					@Override
					public int compare(Entry<Connection, Integer> arg0,
							Entry<Connection, Integer> arg1) {
						return (arg0.getValue() > arg1.getValue() ? -1 : (arg0
								.getValue() == arg1.getValue() ? 0 : 1));
					}
				});

		List<Connection> rankedConnections = new ArrayList<Connection>();
		for (Entry<Connection, Integer> entry : connections) {
			rankedConnections.add(entry.getKey());
		}
		return rankedConnections;
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

		if (this.splitOpIds.contains(compileOp.getOperatorId())
				|| compileOp.isLeaf()) {
			inputOps.add(compileOp);
			return;
		}

		for (AbstractCompileOperator childOp : compileOp.getChildren()) {
			getInputOps(inputOps, childOp);
		}
	}

	private Set<AbstractCompileOperator> getQueryTrackerCompileOps(
			AbstractCompileOperator compileOp) {

		Set<AbstractCompileOperator> compileOps = new HashSet<AbstractCompileOperator>();
		compileOps.add(compileOp);
		for (AbstractCompileOperator childOp : compileOp.getChildren()) {
			getQueryTrackerCompileOps(compileOps, childOp);
		}

		return compileOps;
	}

	private void getQueryTrackerCompileOps(
			Set<AbstractCompileOperator> compileOps,
			AbstractCompileOperator compileOp) {

		if (this.splitOpIds.contains(compileOp.getOperatorId())) {
			return;
		} else {
			compileOps.add(compileOp);
		}

		for (AbstractCompileOperator childOp : compileOp.getChildren()) {
			getQueryTrackerCompileOps(compileOps, childOp);
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
			if (!this.splitOpIds.contains(childOp.getOperatorId())) {
				// generate code: use no "(...)" for tables
				if (childOp.getType().equals(EnumOperator.TABLE)) {
					args.put(childOp.getOperatorId().toString(),
							this.generateExecuteDML(childOp));
				} else {
					args.put(childOp.getOperatorId().toString(),
							"(" + this.generateExecuteDML(childOp) + ")");
				}
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
	private List<Identifier> extractSplitOps() {
		SplitPlanVisitor splitVisitor = new SplitPlanVisitor(null);
		for (AbstractCompileOperator root : this.compilePlan
				.getRootsCollection()) {
			splitVisitor.reset(root);
			this.err = splitVisitor.visit();

			if (err.isError())
				return null;
		}
		return splitVisitor.getSplitOpIds();
	}

	/**
	 * Renames attributes to original names in tables
	 */
	private void rename() {
		ReRenameAttributesVisitor renameVisitor;
		for (AbstractCompileOperator root : this.compilePlan
				.getRootsCollection()) {
			renameVisitor = new ReRenameAttributesVisitor(root);
			this.err = renameVisitor.visit();

			if (err.isError())
				return;
		}
	}

	/**
	 * Optimize compile plan for MySQL code generation
	 */
	private void optimize() {
		if (!Config.CODEGEN_OPTIMIZE)
			return;

		int i = 1;
		for (Identifier splitOpId : this.splitOpIds) {
			// get splitOp from compile plan as root for optimization
			AbstractCompileOperator splitOp = this.compilePlan
					.getOperator(splitOpId);
			this.err = combineJoins(splitOp);
			if (this.err.isError())
				return;

			if (Config.TRACE_CODEGEN_PLAN)
				this.compilePlan.tracePlan(compilePlan.getClass()
						.getCanonicalName()
						+ "_CODEGEN_PHASE"
						+ i
						+ "_"
						+ splitOp.getOperatorId() + "_CombinedJoins");

			// get splitOp again since it might have be replaced
			splitOp = this.compilePlan.getOperator(splitOpId);
			this.err = combineUnaryOps(splitOp);
			if (this.err.isError())
				return;

			if (Config.TRACE_CODEGEN_PLAN)
				this.compilePlan.tracePlan(compilePlan.getClass()
						.getCanonicalName()
						+ "_CODEGEN_PHASE"
						+ i
						+ "_"
						+ splitOp.getOperatorId() + "_CombinedUnaries");

			// get splitOp again since it might have be replaced
			splitOp = this.compilePlan.getOperator(splitOpId);
			this.err = combineSQLOps(splitOp);
			if (this.err.isError())
				return;

			if (Config.TRACE_CODEGEN_PLAN)
				this.compilePlan.tracePlan(compilePlan.getClass()
						.getCanonicalName()
						+ "_CODEGEN_PHASE"
						+ i
						+ "_"
						+ splitOp.getOperatorId() + "_Combined");
			++i;
		}
	}

	private void generateTrackerPlan() {
		// for each sub-plan generate a tracker operator
		for (Identifier splitOpId : this.splitOpIds) {
			AbstractCompileOperator splitCompileOp = this.compilePlan
					.getOperator(splitOpId);
			AbstractTrackerOperator trackerOp = null;
			try {
				trackerOp = generateTrackerOp(splitCompileOp);
			} catch (URISyntaxException e) {
				String[] args = { e.toString() };
				this.err = new Error(EnumError.COMPILER_GENERIC, args);
				return;
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
	}

	/**
	 * Combine operators in plan to SQLJoinOps
	 * 
	 * @return
	 */
	private Error combineJoins(AbstractCompileOperator root) {
		Error err = new Error();
		JoinCombineVisitor combineVisitor = new JoinCombineVisitor(root,
				this.compilePlan);
		err = combineVisitor.visit();

		return err;
	}

	/**
	 * Combine operators in plan to SQLUnaryOps
	 * 
	 * @return
	 */
	private Error combineUnaryOps(AbstractCompileOperator root) {
		Error err = new Error();
		SQLUnaryCombineVisitor combineVisitor = new SQLUnaryCombineVisitor(
				root, this.compilePlan);
		err = combineVisitor.visit();

		return err;
	}

	/**
	 * Combine operators in plan to SQLOps
	 * 
	 * @return
	 */
	private Error combineSQLOps(AbstractCompileOperator root) {
		Error err = new Error();
		SQLCombineVisitor combineVisitor = new SQLCombineVisitor(root,
				this.compilePlan);
		err = combineVisitor.visit();

		return err;
	}
}
