package org.xdb.funsql.statement;

import java.util.Collection;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.funsql.compile.tokens.TokenTable;

public class SelectStmt extends AbstractServerStmt {
	private Vector<AbstractExpression> tExpressions = new Vector<AbstractExpression>();
	private Vector<TokenIdentifier> tExprAliases = new Vector<TokenIdentifier>();
	
	private Vector<TokenTable> tTables = new Vector<TokenTable>();
	private Vector<TokenIdentifier> tTableAliases = new Vector<TokenIdentifier>();
	
	private AbstractPredicate tPredicate;
	
	//constructors
	public SelectStmt() {
		this.statementType = EnumStatement.SELECT;
	}
	
	//getters and setters
	public void addExpression(AbstractExpression expr){
		this.tExpressions.add(expr);
	}
	
	public Collection<AbstractExpression> getExpressions() {
		return this.tExpressions;
	}
	
	public void addExpAlias(TokenIdentifier alias){
		this.tExprAliases.add(alias);
	}
	
	public Collection<TokenIdentifier> getExprAliases() {
		return this.tExprAliases;
	}

	public void addTable(TokenTable table){
		this.tTables.add(table);
	}
	
	public Collection<TokenTable> getTables() {
		return tTables;
	}
	
	public void addTableAlias(TokenIdentifier alias){
		this.tTableAliases.add(alias);
	}
	
	public Collection<TokenIdentifier> getTableAliases() {
		return this.tTableAliases;
	}
	
	public AbstractPredicate getPredicate() {
		return tPredicate;
	}

	public void setPredicate(AbstractPredicate tPredicate) {
		this.tPredicate = tPredicate;
	}

	@Override
	public Error compile() {
		Error err = new Error();
		
		return err;
	}

	@Override
	public Error execute() {
		Error err = new Error();
		
		return err;
	}
	
	@Override
	public String toString(){
		StringBuffer sqlValue = new StringBuffer();
		
		sqlValue.append(AbstractToken.SELECT);
		sqlValue.append(AbstractToken.BLANK);
		
		boolean isFirst = true;
		for(AbstractExpression tExpr: this.tExpressions){
			if(!isFirst)
				sqlValue.append(AbstractToken.COMMA);
			
			sqlValue.append(tExpr);
			sqlValue.append(AbstractToken.BLANK);
			isFirst = false;
		}
		
		sqlValue.append(AbstractToken.FROM);
		sqlValue.append(AbstractToken.BLANK);
		
		isFirst = true;
		for(TokenTable tTable: this.tTables){
			if(!isFirst)
				sqlValue.append(AbstractToken.COMMA);
			
			sqlValue.append(tTable);
			sqlValue.append(AbstractToken.BLANK);
			isFirst = false;
		}
		
		sqlValue.append(AbstractToken.WHERE);
		sqlValue.append(AbstractToken.BLANK);
		sqlValue.append(this.tPredicate.toString());
		
		return sqlValue.toString();
	}
}
