package org.xdb.funsql.compile.tokens;

import java.util.Vector;

//Call f1(:i1, VAR o1);
public class TokenFunctionCall  extends AbstractTokenFunctionPart{

	public TokenFunctionCall() {
		super(EnumFunctionPartType.FUNCTION_CALL);
	}

	private static final long serialVersionUID = 4600903022238163726L;
	private TokenFunction fun;
	private Vector<TokenVariable> inVars;
	private Vector<TokenVariable> outVars;

	@Override
	public String toSqlString() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append(AbstractToken.CALL);
		sqlString.append(this.fun.toSqlString());
		sqlString.append(AbstractToken.LBRACE);
		for(TokenVariable var: this.inVars){
			sqlString.append(var.toString());
			sqlString.append(AbstractToken.COMMA);
		}
		for(int i=0; i<this.outVars.size()-1;i++){
			sqlString.append(AbstractToken.VAR);
			sqlString.append(this.outVars.get(i).toString());
			sqlString.append(AbstractToken.COMMA);
		}
		sqlString.append(AbstractToken.VAR);
		sqlString.append(this.outVars.get(this.outVars.size()-1).toString());
		sqlString.append(AbstractToken.RBRACE);
		sqlString.append(AbstractToken.SEMI);
		return sqlString.toString();
	}
	
	public void addInVar(TokenVariable var) {
		this.inVars.add(var);	
	}
	public void addOutVar(TokenVariable var) {
		this.outVars.add(var);	
	}

	
	//getter and setter
	public TokenFunction getFun() {
		return fun;
	}

	public void setFun(TokenFunction fun) {
		this.fun = fun;
	}

	public Vector<TokenVariable> getInVars() {
		return inVars;
	}
	
	public void setInVars(Vector<TokenVariable> inVars) {
		this.inVars = inVars;
	}

	public Vector<TokenVariable> getOutVars() {
		return outVars;
	}

	public void setOutVars(Vector<TokenVariable> outVars) {
		this.outVars = outVars;
	}

}
