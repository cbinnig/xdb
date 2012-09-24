package org.xdb.funsql.compile.operator;

import java.util.HashSet;
import java.util.Set;

import org.xdb.funsql.compile.tokens.TokenTable;
import org.xdb.metadata.Connection;
import org.xdb.metadata.Table;

public class TableOperator extends AbstractOperator {
	private static final long serialVersionUID = 997138204723229392L;

	//attributes
	private TokenTable tableName;
	private Connection connection = null;
	private Table table = null;
	
	//constructors
	public TableOperator(TokenTable tableName){
		super(1);
		
		this.tableName = tableName;
		this.type = EnumOperator.TABLE;
	}
	
	public Set<AbstractOperator> getSourceOperators() {
		return new HashSet<AbstractOperator>();
	}

	//getters and setters
	public TokenTable getTableName() {
		return tableName;
	}

	public void setTableName(TokenTable tableName) {
		this.tableName = tableName;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
	
	@Override
	public String toSqlString() {
		// TODO: generate sql
		return null;
	}
}
