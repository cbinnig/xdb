package org.xdb.funsql.statement;

import java.util.HashMap;
import java.util.Vector;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenAssignment;
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
	private HashMap<TokenVariable, SelectStmt> assignments = new HashMap<TokenVariable, SelectStmt>();// weglassen?
	private Vector<TokenAssignment> tAssignments;
	private Vector<TokenVariable> parameters;// I/O-Parameter

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
			return Catalog.createObjectAlreadyExistsErr(this.function);// update-Möglichkeit?
		}

		e = checkVariablesAndParameters();
		if(e.isError())
			return e;

		this.function = new Function(this.tFun.toString(), schema.getOid(),
				this.tFun.getLanguage(), stmtString);
		e = this.function.checkObject();
		return e;
	}

	private Error checkVariablesAndParameters() {
		Error e = new Error();
		// check Parameters
		for (TokenVariable var : this.parameters) {
			if ((this.parameters != null)
					&& (!this.assignments.containsKey(var))) {
				e = this.createOutputParameterIsNotInitialisedErr(var);
			}
		}

		// check if referenced variables are declared
		if (this.tAssignments != null) {
			for (TokenAssignment ta : this.tAssignments) {
				if (ta.isReference()) {
					int i = 0;
					for (TokenAssignment ta2 : this.tAssignments) {
						if ((ta2.getVar().getName().equals(ta.getVar()
								.getName()))) {
							if ((!ta2.isReference())) {
								i++;
							} else if ((ta2.isReference())) {
								continue;
							}
						}
					}
					if (i <= 0) {
						for (TokenVariable par : this.parameters) {
							if (ta.getVar().getName().equals(par.getName())) {
								i++;
							}
						}
					}
					if (i <= 0) {
						e = this.createVariableNotDeclared(ta.getVar());
					}
				}
			}
		}
		
		//TODO: referencing variables in select statements (attributes)
		
		return e;
	}

	private Error createOutputParameterIsNotInitialisedErr(TokenVariable tv) {
		String args[] = { tv.getName().toString() };
		Error error = new Error(
				EnumError.COMPILER_FUNCTION_OUT_NOT_INITIALISED, args);
		return error;
	}

	private Error createVariableNotDeclared(TokenVariable tv) {
		String args[] = { tv.getName().toString() };
		Error error = new Error(EnumError.COMPILER_FUNCTION_VAR_NOT_DECLARED,
				args);
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

}
