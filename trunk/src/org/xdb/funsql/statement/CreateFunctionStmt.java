package org.xdb.funsql.statement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.FunctionCache;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.tokens.TokenAssignment;
import org.xdb.funsql.compile.tokens.TokenFunction;
import org.xdb.funsql.compile.tokens.TokenSchema;
import org.xdb.funsql.compile.tokens.TokenVariable;
import org.xdb.funsql.optimize.Optimizer;
import org.xdb.funsql.types.EnumSimpleType;
import org.xdb.metadata.Attribute;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.EnumDatabaseObject;
import org.xdb.metadata.Function;
import org.xdb.metadata.Schema;
import org.xdb.metadata.Table;

public class CreateFunctionStmt extends AbstractServerStmt {

	// function
	private TokenFunction tFun;
	private Function function;
	private FunctionCache fCache = FunctionCache.getCache();

	// output parameters
	private Vector<TokenVariable> outParameters = new Vector<TokenVariable>();
	private HashSet<String> outParamKeys = new HashSet<String>();

	// assignments in function body
	private HashMap<TokenVariable, SelectStmt> assignments = new HashMap<TokenVariable, SelectStmt>();
	private Vector<TokenAssignment> tAssignments = new Vector<TokenAssignment>();
	private HashMap<String, CompilePlan> compilePlans = new HashMap<String, CompilePlan>();
	private HashMap<String, Table> varSymbols = new HashMap<String, Table>();

	private CompilePlan functionPlan = new CompilePlan();

	// Constructors
	public CreateFunctionStmt() {
		this.statementType = EnumStatement.CREATE_FUNCTION;
	}

	public CreateFunctionStmt(String name) {
		this();
		this.tFun = new TokenFunction(name);
	}

	// getter and setter

	// Assignments in FunctionBody
	public void addAssignment(TokenVariable var, SelectStmt selstmt) {
		var.setName(var.getName().toUpperCase());
		this.assignments.put(var, selstmt);
	}

	// In/Out Parameters
	public void addOutParam(TokenVariable var) {
		this.outParameters.addElement(var);
		this.outParamKeys.add(var.hashKey());
	}

	// getter and setter
	public void setFunction(TokenFunction tFun) {
		this.tFun = tFun;
	}

	public TokenFunction gettFun() {
		return tFun;
	}

	public void settFun(TokenFunction tFun) {
		this.tFun = tFun;
	}

	public HashMap<TokenVariable, SelectStmt> getAssignments() {
		return assignments;
	}

	public void setAssignments(HashMap<TokenVariable, SelectStmt> assignments) {
		this.assignments = assignments;
	}

	public void addAssignment(TokenAssignment ta) {
		ta.getVar().setName(ta.getVar().getName().toUpperCase());
		this.tAssignments.add(ta);
	}

	public Vector<TokenVariable> getParameters() {
		return outParameters;
	}

	public void setParameters(Vector<TokenVariable> parameters) {
		this.outParameters = parameters;
	}

	/**
	 * @return the plan
	 */
	public CompilePlan getPlan() {
		return this.functionPlan;
	}

	/**
	 * @param plan
	 *            : the plan to set
	 */
	public void setPlan(CompilePlan plan) {
		this.functionPlan = plan;
	}

	// methods
	@Override
	public Error compile() {
		Error e = new Error();

		// step 1: checks
		// check for non existing schema names
		TokenSchema tSchema = this.tFun.getSchema();
		Schema schema = Catalog.getSchema(tSchema.hashKey());
		if (schema == null) {
			return Catalog.createObjectNotExistsErr(tSchema.toSqlString(),
					EnumDatabaseObject.SCHEMA);
		}

		// check if function with same name already exists
		this.function = Catalog.getFunction(this.tFun.hashKey(schema.getOid()));
		if (this.function != null) {
			return Catalog.createObjectAlreadyExistsErr(this.function);// update?
		}

		// check parameters and add them to cache
		e = checkParameters();
		if (e.isError())
			return e;

		// step 2: compile assignments
		for (TokenAssignment ta : this.tAssignments) {
			SelectStmt stmt = ta.getSelStmt();
			stmt.addVarSymbols(this.varSymbols);
			e = stmt.compile();
			if (e.isError())
				return e;

			// add plan to compiled plans and build table from result
			this.compilePlans.put(ta.getVar().hashKey(), stmt.getPlan());
			Table tableType = this.buildTableType(ta.getVar(), stmt.getPlan()
					.getRoot(0).getResult(0));
			varSymbols.put(ta.hashKey(), tableType);
		}

		// step 3: build compile plan from select plans
		// TODO: not yet correct
		for (TokenAssignment ta : this.tAssignments) {
			SelectStmt stmt = ta.getSelStmt();
			CompilePlan stmtPlan = stmt.getPlan();

			// replace variables in stmtPlan
			for (String varKey : stmt.getUsedVariables()) {
				CompilePlan varPlan = this.compilePlans.get(varKey);
				stmtPlan.replaceVariable(varKey, varPlan.getRoot(0));
			}

			// add stmtPlan to functionPlan
			this.functionPlan.addSubPlan(stmtPlan);
			if (this.outParamKeys.contains(ta.getVar())) {
				this.functionPlan.addRootId(stmtPlan.getRootId(0));
			}
		}

		// step 4: add CompilePlan to cache and catalog
		this.function = new Function(this.tFun.toString(), schema.getOid(),
				this.tFun.getLanguage(), stmtString);

		fCache.addPlan(this.function.hashKey(), this.functionPlan);
		e = this.function.checkObject();

		return e;
	}

	private Table buildTableType(TokenVariable var, ResultDesc resultDesc) {
		Table table = new Table(var.getName());
		for (int i = 0; i < resultDesc.size(); ++i) {
			String attName = resultDesc.getAttribute(i).getName().toSqlString();
			EnumSimpleType attType = resultDesc.getType(i);
			Attribute att = new Attribute(attName, attType);
			table.addAttribute(att);
		}
		return table;
	}

	private Error checkParameters() {
		Error e = new Error();
		// check Parameters
		if (this.outParameters.size() > 0) {
			for (TokenVariable var : this.outParameters) {
				if (!this.assignments.containsKey(var)) {
					e = this.createOutputParameterIsNotInitialisedErr(var);
				}
			}
			return e;
		} else {
			return this.createNoOutputParameterErr();
		}
	}

	private Error createOutputParameterIsNotInitialisedErr(TokenVariable tv) {
		String args[] = { tv.getName().toString() };
		Error error = new Error(
				EnumError.COMPILER_FUNCTION_OUT_NOT_INITIALISED, args);
		return error;
	}

	private Error createNoOutputParameterErr() {
		String args[] = { "" };
		Error error = new Error(EnumError.COMPILER_FUNCTION_NO_OUT_PARAM, args);
		return error;
	}

	@Override
	public Error execute() {
		return Catalog.createFunction(this.function);
	}

	@Override
	public Error optimize() {
		Error err = new Error();
		Optimizer opti = new Optimizer(this.functionPlan);
		opti.optimize();
		return err;
	}
}
