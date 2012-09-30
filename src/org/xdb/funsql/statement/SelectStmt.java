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
		this.tExprAliases.add(null);
	}
	
	public Collection<AbstractExpression> getExpressions() {
		return this.tExpressions;
	}
	
	public void setExpressionAlias(int i, TokenIdentifier alias){
		this.tExprAliases.set(i, alias);
	}
	
	public Collection<TokenIdentifier> getExpressionAliases() {
		return this.tExprAliases;
	}

	public void addTable(TokenTable table){
		this.tTables.add(table);
		this.tTableAliases.add(null);
	}
	
	public Collection<TokenTable> getTables() {
		return tTables;
	}
	
	public void setTableAlias(int i, TokenIdentifier alias){
		this.tTableAliases.set(i, alias);
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
		
		int i=0;
		for(AbstractExpression tExpr: this.tExpressions){
			if(i!=0)
				sqlValue.append(AbstractToken.COMMA);
			
			sqlValue.append(AbstractToken.BLANK);	
			sqlValue.append(tExpr);
			TokenIdentifier tExprAlias = this.tExprAliases.get(i);
			if(tExprAlias!=null){
				sqlValue.append(AbstractToken.BLANK);
				sqlValue.append(AbstractToken.AS);
				sqlValue.append(AbstractToken.BLANK);
				sqlValue.append(tExprAlias);
				sqlValue.append(AbstractToken.BLANK);
			}
			
			++i;
		}
		
		sqlValue.append(AbstractToken.BLANK);
		sqlValue.append(AbstractToken.FROM);
		sqlValue.append(AbstractToken.BLANK);
		
		i = 0;
		for(TokenTable tTable: this.tTables){
			if(i!=0)
				sqlValue.append(AbstractToken.COMMA);
			
			sqlValue.append(AbstractToken.BLANK);
			sqlValue.append(tTable);
			
			TokenIdentifier tTableAlias = this.tTableAliases.get(i);
			if(tTableAlias!=null){
				sqlValue.append(AbstractToken.BLANK);
				sqlValue.append(AbstractToken.AS);
				sqlValue.append(AbstractToken.BLANK);
				sqlValue.append(tTableAlias);
			}
		
			++i;
		}
		
		sqlValue.append(AbstractToken.BLANK);
		sqlValue.append(AbstractToken.WHERE);
		sqlValue.append(AbstractToken.BLANK);
		sqlValue.append(this.tPredicate.toString());
		
		return sqlValue.toString();
	}
}
