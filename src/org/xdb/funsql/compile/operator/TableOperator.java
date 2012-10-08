package org.xdb.funsql.compile.operator;

import java.util.HashMap;

import org.xdb.error.Error;
import org.xdb.funsql.compile.TreeVisitor;
import org.xdb.funsql.compile.tokens.TokenTable;
import org.xdb.metadata.Connection;
import org.xdb.metadata.Table;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

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
		return "<"+connection.getTableName()+">";
	}

	/**
	 * @return false, nothing could pushed down deeper than the table
	 */
	@Override
	public boolean isPushDownAllowed(EnumPushDown pd) {
		return false;
	}

	@Override
	void accept(TreeVisitor v) {
		v.visitTableOperator(this);
	}
	
	@Override
	public Error traceGraph(Graph g, HashMap<Identifier, GraphNode> nodes){
		Error err = new Error();
		GraphNode node = nodes.get(this.operatorId);
		node.getInfo().setFooter(this.tableName.toSqlString());
		return err;
	}
}
