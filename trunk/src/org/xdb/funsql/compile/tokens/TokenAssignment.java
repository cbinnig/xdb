package org.xdb.funsql.compile.tokens;

import org.xdb.funsql.statement.SelectStmt;

// VAR v1 = <select-Stmt>;
// :v2 = <select-Stmt>;
public class TokenAssignment extends AbstractToken{
	private static final long serialVersionUID = 6953876003543489236L;
	private TokenVariable var;
	private SelectStmt selstmt;
	private boolean isReference;
	
	@Override
	public String toSqlString() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append(this.var.toSqlString());
		sqlString.append(AbstractToken.EQUAL1);
		sqlString.append(this.selstmt.getStmtString());
		sqlString.append(AbstractToken.SEMI);
		return sqlString.toString();
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

	public boolean isReference() {
		return isReference;
	}


	public void setReference(boolean isReference) {
		this.isReference = isReference;
	}
	
	public String hashKey(){
		return this.var.hashKey();
	}

}
