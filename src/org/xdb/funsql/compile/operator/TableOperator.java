package org.xdb.funsql.compile.operator;

import java.util.HashMap;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.operator.ITreeVisitor;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.metadata.Attribute;
import org.xdb.metadata.Connection;
import org.xdb.metadata.Table;
import org.xdb.utils.Identifier;
import org.xdb.utils.SetUtils;
import org.xdb.utils.StringTemplate;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class TableOperator extends AbstractOperator {
	private static final long serialVersionUID = 997138204723229392L;

	//attributes
	private TokenIdentifier tableName;
	private Connection connection = null;
	private Table table = null;
	private boolean isVar;
	
	private final StringTemplate sqlTemplate = 
			new StringTemplate("SELECT <RESULTS> FROM <<OP1>> AS <OP1>");
	
	//constructors
	public TableOperator(TokenIdentifier tableName){
		super(1);
		
		this.tableName = tableName;
		this.type = EnumOperator.TABLE;
		this.isVar = false;
	}

	//getters and setters
	public String getTableName() {
		return tableName.getName();
	}
	
	public boolean isVar() {
		return isVar;
	}

	public void setVar(boolean isVar) {
		this.isVar = isVar;
	}

	public TokenIdentifier getTokenTable() {
		return tableName;
	}

	public void setTableName(TokenIdentifier tableName) {
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
		HashMap<String, String> vars = new HashMap<String, String>();
		final String opDummy = getOperatorId().toString()+"_TABLE";
		vars.put("OP1", opDummy);
		
		//get alle table attributes and match them in order
		final Vector<String> attrs = new Vector<String>();
		for(Attribute attr : table.getAttributes()) {
			attrs.add(attr.getName());
		}
		vars.put("RESULTS", SetUtils.buildAliasString(opDummy, attrs, getResultAttributes()));
		return sqlTemplate.toString(vars);
	}

	/**
	 * @return false, nothing could pushed down deeper than the table
	 */
	@Override
	public boolean isPushDownAllowed(EnumPushDown pd) {
		return false;
	}

	@Override
	public void accept(ITreeVisitor v) {
		v.visitTableOperator(this);
	}
	
	@Override
	public Error traceGraph(Graph g, HashMap<Identifier, GraphNode> nodes){
		Error err = new Error();
		GraphNode node = nodes.get(this.operatorId);
		if(this.results.size()==1)
			node.getInfo().setHeader(this.results.get(0).toString());
		node.getInfo().setCaption(this.toString());
		node.getInfo().setFooter(this.tableName.toSqlString());
		return err;
	}
	
	public void replace(AbstractOperator newOp){
		for(AbstractOperator p : this.parents){
			p.children.add(newOp);
			p.children.remove(this);
		}
	}
}
