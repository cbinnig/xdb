package org.xdb.funsql.codegen;

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
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.metadata.Connection;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.AbstractTrackerOperator;
import org.xdb.tracker.operator.MySQLTrackerOperator;
import org.xdb.tracker.operator.TableDesc;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

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
	private static final String PART1 = "PART1";
	public static final String OUT_PREFIX = "OUT";
	public static final String PART_PREFIX = "P";
	public static final String TABLE_PREFIX = "_";
	
	// compile plan
	private CompilePlan compilePlan;

	// generated query tracker plan
	private QueryTrackerPlan qtPlan = new QueryTrackerPlan();

	// sources / consumers in query tracker plan: operator Id -> operator IDs
	private Map<Identifier, Set<Identifier>> sources = new HashMap<Identifier, Set<Identifier>>();
	private Map<Identifier, Set<Identifier>> consumers = new HashMap<Identifier, Set<Identifier>>();

	// mapping: compile operator ID -> tracker operator ID
	private Map<Identifier, List<Identifier>> compileOp2trackerOp = new HashMap<Identifier, List<Identifier>>();

	// roots of sub-plans: each sub-plan results in one tracker operator
	private List<Identifier> splitOpIds;

	// templates for SQL code generation
	private final StringTemplate sqlExecuteDMLTemplate = new StringTemplate(
			"INSERT INTO <<" + TAB1 + ">> (<" + SQL1 + ">)");

	private final StringTemplate sqlInOutDDLTemplate = new StringTemplate("<<"
			+ TAB1 + ">> <" + SQL1 + ">");

	private final StringTemplate sqlSelectPartDMLTemplate = new StringTemplate(
			"SELECT * FROM <<" + TAB1 + ">>  PARTITION (<" + PART1 + ">)");

	private final StringTemplate sqlUnionPartDMLTemplate = new StringTemplate(
			"SELECT * FROM <<" + TAB1 + ">>");
	
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

	private void addCompileOp2TrackerOp(Identifier compileId,
			Identifier trackerId) {
		List<Identifier> trackerIds;

		if (!this.compileOp2trackerOp.containsKey(compileId)) {
			trackerIds = new ArrayList<Identifier>();
			this.compileOp2trackerOp.put(compileId, trackerIds);
		} else {
			trackerIds = this.compileOp2trackerOp.get(compileId);
		}
		trackerIds.add(trackerId);
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
		this.genTrackerPlan();
		if (this.err.isError())
			return this.err;

		return this.err;
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

	/**
	 * Renames attributes to original names in tables
	 * e.g., from LINEITEM_L_ORDERKEY to L_ORDERKEY
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
	 * Generates (parallelized) QueryTrackerPlan 
	 * (which includes repartitioning) 
	 */
	private void genTrackerPlan() {

		// for each sub-plan generate a tracker operator
		for (Identifier splitOpId : this.splitOpIds) {
			AbstractCompileOperator splitCompileOp = this.compilePlan
					.getOperator(splitOpId);
			AbstractTrackerOperator trackerOp = null;
			try {
				ResultDesc splitResult = splitCompileOp.getResult();

				// generate one tracker operator for each partition
				for (int i = 0; i < splitResult.getPartitionCount(); ++i) {
					trackerOp = this.genTrackerOp(splitCompileOp, i);
				}

			} catch (URISyntaxException e) {
				String[] args = { e.toString() };
				this.err = new Error(EnumError.COMPILER_GENERIC, args);
				return;
			}

			// add mapping: compile operator -> tracker operator
			this.addCompileOp2TrackerOp(splitCompileOp.getOperatorId(),
					trackerOp.getOperatorId());
		}

		// connect tracker operator in QueryTrackerPlan
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
	 * Adds execute statements to new tracker operator
	 * @param trackerOp
	 * @param compileOp
	 */
	private void addTrackerExecuteDML(MySQLTrackerOperator trackerOp,
			AbstractCompileOperator compileOp) {
		// add DML statement for execution
		Identifier outTableId = this.genOutputTableName(compileOp);
		String outTableName = outTableId.toString();
		String executeDML = genExecuteDML(compileOp);
		Map<String, String> args = new HashMap<String, String>();
		args.put(SQL1, executeDML);
		args.put(TAB1, outTableName);
		executeDML = this.sqlExecuteDMLTemplate.toString(args);
		trackerOp.addExecuteSQL(new StringTemplate(executeDML));
	}

	/**
	 * Adds DDL statements for output tables/views to new tracker operator
	 * @param trackerOp
	 * @param compileOp
	 */
	private void addTrackerOutputDDL(MySQLTrackerOperator trackerOp,
			AbstractCompileOperator compileOp) {
		Identifier outTableId = this.genOutputTableName(compileOp);
		String outTableName = outTableId.toString();

		Map<String, String> args = new HashMap<String, String>();
		ResultDesc outputResult = compileOp.getResult();
		String outAttsDDL = outputResult.getAttsDDL();
		args.put(SQL1, outAttsDDL);
		args.put(TAB1, outTableName);
		outAttsDDL = this.sqlInOutDDLTemplate.toString(args);

		// add output table w repartition specification
		if (outputResult.repartition()) {
			String repartitionDDL = outputResult.getRepartDDL();
			trackerOp.addOutTable(outTableName, outAttsDDL, repartitionDDL);

			// add one output view for each partition
			for (Integer i = 0; i < outputResult.getPartitionCount(); ++i) {
				Identifier outViewId = this.genOutputTableName(compileOp, i);
				args.put(TAB1, outTableName);
				args.put(PART1, i.toString());

				String outputViewName = outViewId.toString();
				String selectPartDML = sqlSelectPartDMLTemplate.toString(args);

				trackerOp.addOutView(outputViewName, selectPartDML);
			}
		}
		// add output table w/o repartition specification
		else {
			trackerOp.addOutTable(outTableName, outAttsDDL);
		}
	}

	/**
	 * Adds DDL statements for input tables/views to new tracker operator
	 * @param trackerOp
	 * @param compileOp
	 * @param partNum
	 */
	private void addTrackerInputDDL(MySQLTrackerOperator trackerOp,
			AbstractCompileOperator compileOp, int partNum) {
		
		Map<String, String> args = new HashMap<String, String>();
		Set<AbstractCompileOperator> inputCompileOps = this
				.getInputOps(compileOp);
		
		//for each input operator create input DDL
		for (AbstractCompileOperator inputCompileOp : inputCompileOps) {
			// generate input DDL
			ResultDesc inputResult = inputCompileOp.getResult();

			/** Create input table DDL **/
			Identifier inTableId = this.genInputTableName(inputCompileOp);
			String inTableName = inTableId.toString();
			String inAttsDDL = inputResult.getAttsDDL();
			args.put(SQL1, inAttsDDL);
			
			// if input is a table -> use a different name
			if (inputCompileOp.isTable()) {
				inTableName = TABLE_PREFIX + inTableName;
			}
			
			if (inputResult.repartition()) {
				StringBuffer sqlUnionDML = new StringBuffer();
				for(int i=0; i<partNum;++i){
					Identifier inPartId = this.genOutputTableName(
							inputCompileOp, partNum);
					String inPartName = inPartId.toString();
					args.put(TAB1, inPartName);
					inAttsDDL = this.sqlInOutDDLTemplate.toString(args);
					
					trackerOp
					.addInTable(inPartName, new StringTemplate(inAttsDDL));
					
					if(i>0){
						sqlUnionDML.append(AbstractToken.BLANK);
						sqlUnionDML.append(AbstractToken.UNION);
						sqlUnionDML.append(AbstractToken.BLANK);
					}
					
					sqlUnionDML.append(AbstractToken.LBRACE);
					sqlUnionDML.append(this.sqlUnionPartDMLTemplate.toString(args));
					sqlUnionDML.append(AbstractToken.RBRACE);
				}
				trackerOp.addInView(inTableName, sqlUnionDML.toString());
			} else {
				
				args.put(TAB1, inTableName);
				inAttsDDL = this.sqlInOutDDLTemplate.toString(args);
				trackerOp
						.addInTable(inTableName, new StringTemplate(inAttsDDL));
			}

			/** Create input table description **/
			// if input is a table: add catalog info to tracker operator
			if (inputCompileOp.isTable()) { // table
				TableOperator inputTableOp = (TableOperator) inputCompileOp;

				if (inputTableOp.isPartitioned()) {
					TableDesc tableDesc = new TableDesc(
							inputTableOp.getTableName(partNum), inputTableOp.getURIs(partNum));
					trackerOp.setInTableSource(inTableName, tableDesc);
				} else {
					TableDesc tableDesc = new TableDesc(
							inputTableOp.getTableName(), inputTableOp.getURIs());
					trackerOp.setInTableSource(inTableName, tableDesc);
				}
			}
			// else: use intermediate result as table (with _OUT suffix)
			else {
				// if input is repartitioned
				if (inputResult.repartition()) {
					// create one input per partition
					int remotePart = 0;
					for (Identifier inTrackerOpId : this.compileOp2trackerOp
							.get(inputCompileOp.getOperatorId())) {

						Identifier inPartRemoteId = this.genOutputTableName(
								inputCompileOp, remotePart);
						Identifier inPartId = this.genInputTableName(inputCompileOp);
						String inPartName = inPartId.toString();

						TableDesc tableDesc = new TableDesc(
								inPartRemoteId.toString(), inTrackerOpId);
						trackerOp.setInTableSource(inPartName, tableDesc);
						this.addTrackerDependency(inTrackerOpId,
								trackerOp.getOperatorId());
					}
				} else {
					// create one input table
					Identifier inTableRemoteId = this
							.genOutputTableName(inputCompileOp);
					Identifier inTrackerOpId = this.compileOp2trackerOp.get(
							inputCompileOp.getOperatorId()).get(partNum);
					TableDesc tableDesc = new TableDesc(
							inTableRemoteId.toString(), inTrackerOpId);
					trackerOp.setInTableSource(inTableName, tableDesc);
					this.addTrackerDependency(inTrackerOpId,
							trackerOp.getOperatorId());
				}
			}
		}
	}

	/**
	 * Generates a tracker operator for each sub-plan (given as root of sub-plan
	 * in compile plan)
	 * 
	 * @param compileOp
	 * @return
	 * @throws URISyntaxException
	 */
	private AbstractTrackerOperator genTrackerOp(
			AbstractCompileOperator compileOp, int partNum)
			throws URISyntaxException {
		// generate a new MySQL operator
		MySQLTrackerOperator trackerOp = new MySQLTrackerOperator();
		this.qtPlan.addOperator(trackerOp);

		// add DML statement for execution
		this.addTrackerExecuteDML(trackerOp, compileOp);

		// add DDL statements for output tables
		this.addTrackerOutputDDL(trackerOp, compileOp);

		// add DDL statements for input tables
		this.addTrackerInputDDL(trackerOp, compileOp, partNum);

		// add connections to tracker operator
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
	 * Generate DML statement for execution
	 * 
	 * @param compileOp
	 * @return
	 */
	private String genExecuteDML(AbstractCompileOperator compileOp) {
		Map<String, String> args = new HashMap<String, String>();
		String executeDML = compileOp.toSqlString();

		StringTemplate sqlTemplate = new StringTemplate(executeDML);
		for (AbstractCompileOperator childOp : compileOp.getChildren()) {
			if (!this.splitOpIds.contains(childOp.getOperatorId())) {
				String childExecuteDML = this.genExecuteDML(childOp);

				// generate code: use "(...)" for sub-queries
				if (!childOp.isTable()) {
					childExecuteDML = AbstractToken.LBRACE + childExecuteDML
							+ AbstractToken.RBRACE;
				}
				args.put(childOp.getOperatorId().toString(), childExecuteDML);
			}
		}

		return sqlTemplate.toString(args);
	}

	private Identifier genOutputTableName(
			final AbstractCompileOperator compileOp) {
		return compileOp.getOperatorId().clone().append(OUT_PREFIX);
	}

	private Identifier genOutputTableName(
			final AbstractCompileOperator compileOp, final int partNum) {
		return compileOp.getOperatorId().clone().append(PART_PREFIX + partNum)
				.append(OUT_PREFIX);
	}

	private Identifier genInputTableName(final AbstractCompileOperator compileOp) {
		return compileOp.getOperatorId().clone();
	}

	@SuppressWarnings("unused")
	private Identifier genInputTableName(
			final AbstractCompileOperator compileOp, final int partNum) {
		return compileOp.getOperatorId().clone().append(PART_PREFIX + partNum);
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
