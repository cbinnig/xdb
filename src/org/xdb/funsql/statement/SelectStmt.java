package org.xdb.funsql.statement;

import java.util.Collection;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenTable;

public class SelectStmt extends AbstractStatement {
	private Vector<TokenAttribute> tAttributes = new Vector<TokenAttribute>();
	private Vector<TokenTable> tTables = new Vector<TokenTable>();
	private AbstractPredicate tPredicate;
	
	//constructors
	public SelectStmt() {
		this.statementType = EnumStatement.SELECT;
	}
	
	//getters and setters
	public void addAttribute(String att){
		this.tAttributes.add(new TokenAttribute(att));
	}
	
	public void addAttribute(TokenAttribute att){
		this.tAttributes.add(att);
	}
	
	public Collection<TokenAttribute> getAttributes() {
		return tAttributes;
	}

	public void addTable(String table){
		this.tTables.add(new TokenTable(table));
	}
	
	public void addTable(TokenTable table){
		this.tTables.add(table);
	}
	
	public Collection<TokenTable> getTables() {
		return tTables;
	}
	
	public AbstractPredicate getPredicate() {
		return tPredicate;
	}

	public void setPredicate(AbstractPredicate tPredicate) {
		this.tPredicate = tPredicate;
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
}
