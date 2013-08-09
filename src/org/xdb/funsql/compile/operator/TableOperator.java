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
import org.xdb.metadata.Partition;
import org.xdb.metadata.Table;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class TableOperator extends AbstractCompileOperator {
	private static final long serialVersionUID = 997138204723229392L;
	public static final String TABLE_PREFIX = "_";
	// attributes
	private TokenIdentifier tableName;
	private Connection connection = null; 
	private List<Connection> connections = new ArrayList<Connection>();
	
	private Partition partition = null;
	private Table table = null;

	private int part = -1;

	private final StringTemplate sqlTemplate = new StringTemplate("<<OP1>>");

	// constructors
	public TableOperator(TokenIdentifier tableName) {
		super(1);

		this.tableName = tableName;
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
		this.tableName = toCopy.tableName.clone();
		if (toCopy.connection != null) {
			this.setConnection(new Connection(toCopy.getConnection()));
		}
		if (toCopy.partition != null) {
			this.setPartition(new Partition(toCopy.getPartition()));
		}

		this.part = toCopy.part;

		this.table = new Table(toCopy.table);
	}

	// getters and setters
	public ResultDesc getResult() {
		return this.results.get(0);
	}

	public String getTableName() {
		return tableName.getValue();
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

	public boolean hasPartition() {
		return this.partition != null;
	}



	public Partition getPartition() {
		return partition;
	}

	public void setPartition(Partition partition) {
		this.partition = partition;
	}

	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}
 
	public List<Connection> getConnections() {
		return connections;
	}

	public void addConnection(Connection connection) {
		this.connections.add(connection);
	}
	
	// methods

	/**
	 * Replace table by other operator, e.g. sub-plan
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

		HashMap<String, String> vars = new HashMap<String, String>();
		final String opDummy = TABLE_PREFIX + getOperatorId().toString();
		vars.put("OP1", opDummy);

		// get all table attributes and match them in order
		/*
		 * final Vector<String> attrs = new Vector<String>(); for(Attribute attr
		 * : table.getAttributes()) { attrs.add(opDummy + "." + attr.getName());
		 * }
		 */
		// vars.put("RESULTS", SetUtils.buildAliasString(attrs,
		// getResultAttributes()));
		// System.out.println(sqlTemplate.toString(vars));
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
			footer.append(this.tableName.toSqlString());
			footer.append(AbstractToken.NEWLINE);

			if (Config.TRACE_COMPILE_PLAN_PARTITIONING && this.hasPartition()) {
				footer.append("Partition: ");
				footer.append(partition.toString());
				footer.append(AbstractToken.NEWLINE);
				footer.append("Part : " + this.part);
				footer.append(AbstractToken.NEWLINE);
			}
			
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

	}
}