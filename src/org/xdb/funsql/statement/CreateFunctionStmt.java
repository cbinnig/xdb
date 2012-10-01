package org.xdb.funsql.statement;

import java.util.HashMap;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenFunction;
import org.xdb.funsql.compile.tokens.TokenVariable;

public class CreateFunctionStmt extends AbstractServerStmt{
private TokenFunction tFun;
private HashMap<TokenVariable, SelectStmt> assignments = new HashMap<TokenVariable, SelectStmt>();
private Vector<TokenVariable> parameters;

	//Constructors
	public CreateFunctionStmt() {
		this.statementType = EnumStatement.CREATE_TABLE;
	}

	@Override
	public Error compile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Error execute() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Assignments in FunctionBody
	public void addAssignment(TokenVariable var, SelectStmt selstmt) {
		this.assignments.put(var, selstmt);		
	}

	//In/Out Parameters
	public void addParam(TokenVariable var) {
		this.parameters.addElement(var);		
	}
	

	
	//getter and setter
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
