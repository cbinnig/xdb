package org.xdb.funsql.statement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import org.xdb.Config;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.FunctionCache;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.tokens.AbstractTokenFunctionPart;
import org.xdb.funsql.compile.tokens.EnumFunctionPartType;
import org.xdb.funsql.compile.tokens.TokenAssignment;
import org.xdb.funsql.compile.tokens.TokenFunction;
import org.xdb.funsql.compile.tokens.TokenFunctionCall;
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
	private Vector<TokenVariable> inParameters = new Vector<TokenVariable>();
	private HashSet<String> inParamKeys = new HashSet<String>();
	
	//FunctionPart
	private Vector<AbstractTokenFunctionPart> parts = new Vector<AbstractTokenFunctionPart>();
	
	// assignments in function body
	private HashMap<TokenVariable, SelectStmt> assignments = new HashMap<TokenVariable, SelectStmt>();
	private Vector<TokenAssignment> tAssignments = new Vector<TokenAssignment>();
	private HashMap<String, CompilePlan> compilePlans = new HashMap<String, CompilePlan>();
	private HashMap<String, Table> varSymbols = new HashMap<String, Table>();
	
	//function calls in function body
	private Vector <TokenFunctionCall> tcalls = new Vector <TokenFunctionCall>();
	private HashMap <TokenFunction, Function> calls = new HashMap <TokenFunction, Function>();
	private HashMap <String, FunctionCall> callSymbols = new HashMap<String, FunctionCall>();
	
	private CompilePlan functionPlan = new CompilePlan();

	// Constructors
	public CreateFunctionStmt() {
		this.statementType = EnumStatement.CREATE_FUNCTION;
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
	
	public void addInParam(TokenVariable var) {
		this.inParameters.addElement(var);
		this.inParamKeys.add(var.hashKey());
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
		this.parts.add(ta);
	}

	public Vector<TokenVariable> getOutParameters() {
		return outParameters;
	}
	public Vector<TokenVariable> getInParameters() {
		return inParameters;
	}

	public void setParameters(Vector<TokenVariable> outParams, Vector<TokenVariable> inParams) {
		this.outParameters = outParams;
		this.inParameters = inParams;
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

	/**
	 * @return the calls
	 */
	public Vector <TokenFunctionCall> gettCalls() {
		return tcalls;
	}

	/**
	 * @param calls: the calls to set
	 */
	public void settCalls(Vector <TokenFunctionCall> tcalls) {
		this.tcalls = tcalls;
	}

	public HashMap <TokenFunction, Function> getCalls() {
		return calls;
	}

	public void setCalls(HashMap <TokenFunction, Function> calls) {
		this.calls = calls;
	}

	public Vector<AbstractTokenFunctionPart> getParts() {
		return parts;
	}

	public void setParts(Vector<AbstractTokenFunctionPart> parts) {
		this.parts = parts;
	}

	// methods
	@Override
	public Error compile() {
		Error e = new Error();
		TokenSchema tSchema = this.tFun.getSchema();
		Schema schema = Catalog.getSchema(tSchema.hashKey());

		// step 1: semantic checks
		if (this.doSemanticAnalysis) {
			// check for non existing schema names
			if (schema == null) {
				return Catalog.createObjectNotExistsErr(tSchema.toSqlString(),
						EnumDatabaseObject.SCHEMA);
			}

			// check if function with same name already exists
			this.function = Catalog.getFunction(this.tFun.hashKey(schema
					.getOid()));
			if (this.function != null) {
				return Catalog.createObjectAlreadyExistsErr(this.function);//TODO:update?
			}
			
			//check if functions which are called exist
			for(TokenFunctionCall tc: this.tcalls){
				Function tempfunction = Catalog.getFunction(tc.getFun().hashKey(schema
						.getOid()));
				if(tempfunction !=null){
					this.calls.put(tc.getFun(), tempfunction);
				}else{
					e = this.createCalledFunctionDoesNotExist(tc.getFun());
				}
			}
			
			// check parameters and add them to cache
			e = checkParameters();
			
			if (e.isError())
				return e;
		}

		// step 2: compile assignments/function calls
		for(AbstractTokenFunctionPart tfp : this.parts){
			
			//assignment
			if(tfp.getType().equals(EnumFunctionPartType.ASSIGNMENT)){
				TokenAssignment ta = (TokenAssignment) tfp;
				SelectStmt stmt = ta.getSelStmt();
				stmt.addVarSymbols(this.varSymbols);
				e = stmt.compile();
				if (e.isError())
					return e;
	
				// add plan to compiled plans and build table from result
				this.compilePlans.put(ta.getVar().hashKey(), stmt.getPlan());
				Table tableType = this.buildTableType(ta.getVar(), stmt.getPlan()
						.getRoot(0).getResult());
				this.varSymbols.put(ta.hashKey(), tableType);
				
			//function call
			}else if(tfp.getType().equals(EnumFunctionPartType.FUNCTION_CALL)){
				TokenFunctionCall tfc = (TokenFunctionCall) tfp;
				//check FunctionCalls
				e = checkCallParameters(tfc);
				if (e.isError())
					return e;
				CompilePlan plan = this.fCache.getPlan(this.calls.get(tfc.getFun()).hashKey());
				if(plan == null){
					CallFunctionStmt stmt = new CallFunctionStmt(tfc.getFun().getName().toString());
					stmt.compile();
					plan = this.fCache.getPlan(this.calls.get(tfc.getFun()).hashKey());	
					
				}	
				
				//add FunctionCall Operator to functioncall
				FunctionCall fc = new FunctionCall(tfc.getFun(), tfc.getOutVars().size());
				for(int i=0; i < plan.getRoots().size(); i++){
					AbstractCompileOperator op = plan.getRoot(i);
					for(int j=0; j<op.getResultNumber(); j++){
						fc.addResult(op.getResult(j));
					}
				}
				
				//adds plans of called functions
				int o=0;
				for(TokenVariable otv: tfc.getOutVars()){
					this.compilePlans.put(otv.hashKey(), plan);	
					Table tableType = this.buildTableType(otv, plan.getRoot(o).getResult(0));
					o++;
					this.varSymbols.put(otv.hashKey(), tableType);
					this.callSymbols.put(otv.hashKey(), fc);
				}
				
			}
		}
		
		//step 3: build compile plan
		
		// step 3a: build compile plan from select plans
		for (TokenAssignment ta : this.tAssignments) {
			SelectStmt stmt = ta.getSelStmt();
			CompilePlan stmtPlan = stmt.getPlan();

			// replace variables in stmtPlan
			for (String varKey : stmt.getUsedVariables()) {
				CompilePlan varPlan = this.compilePlans.get(varKey);
				if(this.callSymbols.containsKey(varKey)){
					FunctionCall fc = callSymbols.get(varKey);
					fc.addParent(varPlan.getRoot(0));
					varPlan.addOperator(fc, false);//ID of fc = ID of original plan
					stmtPlan.replaceVariable(varKey,fc);
					//adds FunctionCall to tree
				}
				else
					stmtPlan.replaceVariable(varKey, varPlan.getRoot(0));
			}

			// add stmtPlan to functionPlan			
			this.functionPlan.addSubPlan(stmtPlan);
			if (this.outParamKeys.contains(ta.getVar())) {
				this.functionPlan.addRootId(stmtPlan.getRootId(0));
			}
		}
		
		//step 3b	
		for(TokenFunctionCall tfc: this.tcalls){
			CompilePlan callPlan = this.fCache.getPlan(this.calls.get(tfc.getFun()).hashKey());//plan of called function
			//replace in parameters of called function with subplans of the variables in present function
			if(!tfc.getInVars().isEmpty()){//TODO
				int i = 0;
				for(TokenVariable cVar: this.fCache.getInVars(this.calls.get(tfc.getFun()).hashKey())){//Signatur der Funktion
					TokenVariable uVar = tfc.getInVars().get(i);//Parameter beim Aufruf
					CompilePlan varPlan = this.compilePlans.get(uVar.hashKey());
					callPlan.replaceVariable(cVar.hashKey(), varPlan.getRoot(0));
					i++;
				}
			}	
			
		}
		
		// step 4: check catalog object
		this.function = new Function(this.tFun.getName().toString(),
				schema.getOid(), this.tFun.getLanguage(), stmtString);
		e = this.function.checkObject();
		if (e.isError())
			return e;
				
		this.fCache.addPlan(this.function.hashKey(), this.functionPlan);
		if(!this.inParameters.isEmpty())
			this.fCache.addInVars(this.function.hashKey(), this.inParameters);

		return e;
	}

	private Error checkCallParameters(TokenFunctionCall call) {
		Error e = new Error();
		// check Input Parameters of a FunctionCall
		for (TokenVariable ivar: call.getInVars())
		if (!this.assignments.containsKey(ivar)) {
			e = this.createInputFunctionCallParameterIsNotInitialisedErr(ivar, call.getFun());
		}
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

	/**
	 * checks if the outgoing parameters are filled in the function
	 * @return Error if they are not filled
	 */
	private Error checkParameters() {
		Error e = new Error();
		// check Parameters
		if(this.inParameters.size() > 0){
			for (@SuppressWarnings("unused") TokenVariable var : this.inParameters){				
				//Table tableType = this.buildTableType(var, .getRoot(0).getResult());
				//this.varSymbols.put(var.hashKey(), tableType);
			}
		}
		if (this.outParameters.size() > 0) {
			for (TokenVariable var : this.outParameters) {
				if (!this.assignments.containsKey(var)) {
					for(TokenFunctionCall call: this.tcalls){
						if(!call.getOutVars().contains(var))
							e = this.createOutputParameterIsNotInitialisedErr(var);
					}
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
	
	private Error createCalledFunctionDoesNotExist(TokenFunction tf) {
		String args[] = { tf.getName().toString() };
		Error error = new Error(
				EnumError.COMPILER_FUNCTION_CALLED_FUNCTION_DOES_NOT_EXIST, args);
		return error;
	}
	
	private Error createInputFunctionCallParameterIsNotInitialisedErr(TokenVariable tv, TokenFunction tf) {
		String args[] = { tv.getName().toString(), tf.getName().toString() };
		Error error = new Error(
				EnumError.COMPILER_FUNCTION_CALL_IN_NOT_INITIALISED, args);
		return error;
	}

	private Error createNoOutputParameterErr() {
		String args[] = { "" };
		Error error = new Error(EnumError.COMPILER_FUNCTION_NO_OUT_PARAM, args);
		return error;
	}

	@Override
	public Error execute() {
		// add function to catalog!
		Error err = Catalog.createFunction(this.function);
		return err;
	}

	@Override
	public Error optimize() {
		Error err = new Error();
		Optimizer opti = new Optimizer(this.functionPlan);
		err = opti.optimize(Config.OPTIMIZER_ACTIVE_RULES_FUNCTION);
		return err;
	}

	public void addFunctionCall(TokenFunctionCall tfc) {
		this.tcalls.add(tfc);
		this.parts.add(tfc);
	}
}
