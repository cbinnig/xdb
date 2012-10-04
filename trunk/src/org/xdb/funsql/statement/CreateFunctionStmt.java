package org.xdb.funsql.statement;

import java.util.HashMap;
import java.util.Vector;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenFunction;
import org.xdb.funsql.compile.tokens.TokenSchema;
import org.xdb.funsql.compile.tokens.TokenVariable;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.EnumDatabaseObject;
import org.xdb.metadata.Function;
import org.xdb.metadata.Schema;

public class CreateFunctionStmt extends AbstractServerStmt {
	private TokenFunction tFun;
	private Function function;
	private HashMap<TokenVariable, SelectStmt> assignments = new HashMap<TokenVariable, SelectStmt>();
	private Vector<TokenVariable> parameters;

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
		// check Parameters
		for (TokenVariable var : this.parameters) {
			if ((this.parameters != null)
					&& (!this.assignments.containsKey(var))) {
				return this.createOutputParameterIsNotInitialisedErr(var);
			}
		}
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
			return Catalog.createObjectAlreadyExistsErr(this.function);
		}
		
		//TODO

		this.function = new Function(this.tFun.toString(), schema.getOid(), EnumLanguage.FUNSQL, stmtString);
		return this.function.checkObject();
	}

	private Error createOutputParameterIsNotInitialisedErr(TokenVariable tv) {
		String args[] = { tv.getName().toString() };
		Error error = new Error(
				EnumError.COMPILER_FUNCTION_OUT_NOT_INITIALISED, args);
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

}
