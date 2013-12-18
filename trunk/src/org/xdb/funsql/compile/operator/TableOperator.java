package org.xdb.funsql.compile.operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.metadata.Connection;
import org.xdb.metadata.Table;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class TableOperator extends AbstractCompileOperator {
	private static final long serialVersionUID = 997138204723229392L;
	public static final String TABLE_PREFIX = "_";
	
	private final StringTemplate sqlTemplate = new StringTemplate("<<OP1>>");
	
	// attributes
	private TokenIdentifier tableAlias;
	private String tableName;
	private String attsDDL;
	
	
	// meta data attributes
	private Table table = null;
	private List<Connection> connections = new ArrayList<Connection>();
	
	// constructors
	public TableOperator(TokenIdentifier tableAlias) {
		super(1);

		this.tableAlias = tableAlias;
		this.type = EnumOperator.TABLE;
	}

	/**
	 * Copy Constructor
	 * 
	 * @param toCopy
	 *            Element to copy
	 */
	public TableOperator(TableOperator toCopy) {
		super(toCopy);
		
		this.tableAlias = toCopy.tableAlias.clone();
		this.connections = new ArrayList<Connection>(toCopy.connections);
		this.table = new Table(toCopy.table);
	}

	// getters and setters
	public ResultDesc getResult() {
		return this.results.get(0);
	}

	public String getTableAlias() {
		return tableAlias.getValue();
	}

	public TokenIdentifier getTableAliasToken() {
		return tableAlias;
	}
	
	public Connection getConnection() {
		return (this.connections.size()>=1)? connections.get(0) : null;
	}

	public void setConnection(Connection connection) {
		this.connections.add(connection);
	}
	
	public String getAttsDDL(){
		return this.attsDDL;
	}
	
	public String getTableName(){
		return this.tableName;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
		this.tableName = table.getName();
		this.attsDDL = table.attsToDDL();
	}

	public List<Connection> getConnections() {
		return connections;
	}

	public void addConnection(Connection connection) {
		this.connections.add(connection);
	}
	
	// methods
	/**
	 * Replace table by sub-plan
	 * 
	 * @param newOp
	 */
	public void replace(Rename newOp) {
		// replace result description in newOp
		newOp.setResult(this.getResult());

		// add newOp to plan
		newOp.addParents(this.getParents());

		for (AbstractCompileOperator p : this.parents) {
			int childIdx = p.children.indexOf(this);
			p.children.set(childIdx, newOp);
		}
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public String toSqlString() {
		Map<String, String> vars = new HashMap<String, String>();
		final String opDummy = TABLE_PREFIX + getOperatorId().toString();
		vars.put("OP1", opDummy);
		return sqlTemplate.toString(vars);
	}

	@Override
	public Error traceOperator(Graph g, Map<Identifier, GraphNode> nodes) {
		Error err = super.traceOperator(g, nodes);

		GraphNode node = nodes.get(this.operatorId);
		if (Config.TRACE_COMPILE_PLAN_FOOTER) {
			StringBuffer footer = new StringBuffer();

			footer.append(this.table.getName());
			footer.append(" AS ");
			footer.append(this.tableAlias.toSqlString());
			footer.append(AbstractToken.NEWLINE);
			
			if (node.getInfo().getFooter() != null) {
				footer.append(node.getInfo().getFooter());
				footer.append(AbstractToken.NEWLINE);
			}
			node.getInfo().setFooter(footer.toString());
		}

		return err;
	}

	@Override
	public void renameTableOfAttributes(String oldId, String newId) {
		//nothing to do :)
	}
}