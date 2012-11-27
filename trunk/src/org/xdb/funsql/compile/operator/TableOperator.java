package org.xdb.funsql.compile.operator;

import java.util.HashMap;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.codegen.CodeGenerator;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.metadata.Attribute;
import org.xdb.metadata.Connection;
import org.xdb.metadata.Table;
import org.xdb.utils.Identifier;
import org.xdb.utils.SetUtils;
import org.xdb.utils.StringTemplate;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class TableOperator extends AbstractCompileOperator {
	private static final long serialVersionUID = 997138204723229392L;

	//attributes
	private TokenIdentifier tableName;
	private Connection connection = null;
	private Table table = null;
	
	private final StringTemplate sqlTemplate = 
			new StringTemplate("SELECT <RESULTS> FROM <<OP1>> AS <OP1>");
	
	//constructors
	public TableOperator(TokenIdentifier tableName){
		super(1);
		
		this.tableName = tableName;
		this.type = EnumOperator.TABLE;
	}

	//getters and setters
	public ResultDesc getResult(){
		return this.results.get(0);
	}
	
	public String getTableName() {
		return tableName.getName();
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
	
	//methods
	
	/**
	 * Replace table by other operator, e.g.
	 * sub-plan
	 * 
	 * @param newOp
	 */
	public void replace(AbstractCompileOperator newOp){
		//replace result description in newOp
		newOp.setResult(0, this.getResult());

		//add newOp to plan
		for(AbstractCompileOperator p : this.parents){
			int childIdx = p.children.indexOf(this);
			p.children.set(childIdx, newOp);
		}
	}
	
	@Override
	public boolean isLeaf(){
		return true;
	}
	
	@Override
	public String toSqlString() {
		HashMap<String, String> vars = new HashMap<String, String>();
		final String opDummy = getOperatorId().clone().append(CodeGenerator.IN_SUFFIX).toString();
		vars.put("OP1", opDummy);
		
		//get all table attributes and match them in order
		final Vector<String> attrs = new Vector<String>();
		for(Attribute attr : table.getAttributes()) {
			attrs.add(opDummy + "." + attr.getName());
		}
		vars.put("RESULTS", SetUtils.buildAliasString(attrs, getResultAttributes()));
		return sqlTemplate.toString(vars);
	}

	
	@Override
	public Error traceGraph(Graph g, HashMap<Identifier, GraphNode> nodes){
		Error err = new Error();
		GraphNode node = nodes.get(this.operatorId);
		if(this.results.size()==1)
			node.getInfo().setHeader(this.results.get(0).toString());
		node.getInfo().setCaption(this.toString());
		node.getInfo().setFooter(this.table.getName() +" AS "+ this.tableName.toSqlString());
		return err;
	}

	@Override
	public void renameAttributes(String oldId, String newId) {
		//nothing to do
	}
}
