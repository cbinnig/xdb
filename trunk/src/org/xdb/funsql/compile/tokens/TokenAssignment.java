package org.xdb.funsql.compile.tokens;

import org.xdb.funsql.statement.SelectStmt;

public class TokenAssignment extends AbstractToken{
	private static final long serialVersionUID = 6953876003543489236L;
	private TokenVariable var;
	private SelectStmt selstmt;

	@Override
	public String toSqlString() {
		// TODO Auto-generated method stub
		return null;
	}

	//getter and setter
	public void setVar(TokenVariable var) {
		this.var = var;
	}
	
	public TokenVariable getVar(){
		return this.var;
	}

	public SelectStmt getSelStmt() {
		return selstmt;
	}

	public void setSelStmt(SelectStmt selstmt) {
		this.selstmt = selstmt;
	}

}
