package org.xdb.funsql.statement;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.FunctionCache;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.tokens.TokenAssignment;
import org.xdb.funsql.compile.tokens.TokenFunction;
import org.xdb.funsql.compile.tokens.TokenSchema;
import org.xdb.funsql.compile.tokens.TokenVariable;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.EnumDatabaseObject;
import org.xdb.metadata.Function;
import org.xdb.metadata.Schema;
import org.xdb.utils.Identifier;

public class CreateFunctionStmt extends AbstractServerStmt {
	
	//function
	private TokenFunction tFun;
	private Function function;
	private FunctionCache cache = FunctionCache.getCache();
	// I/O-Parameter
	private Vector<TokenVariable> parameters;
	
	//assignments in function body
	private HashMap<TokenVariable, SelectStmt> assignments = new HashMap<TokenVariable, SelectStmt>();// weglassen?
	private Vector<TokenAssignment> tAssignments;

	private CompilePlan plan = new CompilePlan();
	
	// Constructors
	public CreateFunctionStmt() {
		this.statementType = EnumStatement.CREATE_FUNCTION;
	}

	public CreateFunctionStmt(String name) {
		this();
		this.tFun = new TokenFunction(name);
	}

	@Override
	public Error compile() {
		Error e = new Error();
		
		//step 1: checks
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
		
		//check parameters and add them to cache
		e = checkParameters();
		if(e.isError())
			return e;
		cache.addVariables(this.parameters);
		
		//step 2: assignments
		for(TokenAssignment ta : this.tAssignments){
			 e = ta.getSelStmt().compile();
			 if(e.isError())
				 return e;
			 
			 if(ta.getVar().isReferenced()&&(!cache.isVarInCache(ta.getVar().getName()))){
				 String[] s = {ta.getVar().getName()};
				 e = new Error(EnumError.COMPILER_FUNCTION_VAR_NOT_DECLARED, s);
			 }else{
				 for (Identifier rootId : ta.getSelStmt().getPlan().getRoots()) {				 
					 AbstractOperator root = ta.getSelStmt().getPlan().getOperators(rootId);
					 cache.addVariable(ta.getVar(),root.getResult(0));//TODO: FunctionCall: more than 1 RD
					 this.plan.addOperator(root, false);//TODO: is root???
					 //TODO: links between assignments
				 }
				 
			 }
		}
		
		//step 3: create plan
		

		this.function = new Function(this.tFun.toString(), schema.getOid(),
				this.tFun.getLanguage(), stmtString);
		e = this.function.checkObject();
		return e;
		

	}

	private Error checkParameters() {
		Error e = new Error();
		// check Parameters
		for (TokenVariable var : this.parameters) {
			if ((this.parameters != null)
					&& (!this.assignments.containsKey(var))) {
				e = this.createOutputParameterIsNotInitialisedErr(var);
			}
			else if (this.parameters == null)
				e = this.createNoOutputParameterErr();
		}
		return e;
	}

	private Error createOutputParameterIsNotInitialisedErr(TokenVariable tv) {
		String args[] = { tv.getName().toString() };
		Error error = new Error(
				EnumError.COMPILER_FUNCTION_OUT_NOT_INITIALISED, args);
		return error;
	}
	
	private Error createNoOutputParameterErr() {
		String args[] = { "" };
		Error error = new Error(
				EnumError.COMPILER_FUNCTION_NO_OUT_PARAM, args);
		return error;
	}



	@Override
	public Error execute() {
		return Catalog.createFunction(this.function);
	}

	// Assignments in FunctionBody
	public void addAssignment(TokenVariable var, SelectStmt selstmt) {
		this.assignments.put(var, selstmt);
	}

	// In/Out Parameters
	public void addParam(TokenVariable var) {
		this.parameters.addElement(var);
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

	public Vector<TokenVariable> getParameters() {
		return parameters;
	}

	public void setParameters(Vector<TokenVariable> parameters) {
		this.parameters = parameters;
	}

	public Vector<TokenAssignment> gettAssignments() {
		return tAssignments;
	}

	public void settAssignments(Vector<TokenAssignment> tAssignments) {
		this.tAssignments = tAssignments;
		for (TokenAssignment ta : this.tAssignments) {
			this.assignments.put(ta.getVar(), ta.getSelStmt());
		}
	}

	/**
	 * @return the plan
	 */
	public CompilePlan getPlan() {
		return plan;
	}

	/**
	 * @param plan: the plan to set
	 */
	public void setPlan(CompilePlan plan) {
		this.plan = plan;
	}

}
