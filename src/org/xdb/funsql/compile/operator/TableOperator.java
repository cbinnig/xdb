package org.xdb.funsql.compile.operator;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.metadata.Attribute;
import org.xdb.metadata.Connection;
import org.xdb.metadata.EnumPartitionType;
import org.xdb.metadata.Partition;
import org.xdb.metadata.PartitionAttribute;
import org.xdb.metadata.Table;
import org.xdb.utils.Identifier;
import org.xdb.utils.StringTemplate;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class TableOperator extends AbstractCompileOperator {
	private static final long serialVersionUID = 997138204723229392L;
	//public static final String TABLE_PREFIX = "_";
	public static final String PART_PREFIX = "_P";
	
	private final StringTemplate sqlTemplate = new StringTemplate("<<OP1>>");
	
	// attributes
	private TokenIdentifier tableAlias;
	
	// meta data attributes
	private Table table = new Table();
	
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
		this.table = new Table(toCopy.table);
	}

	// getters and setters
	public ResultDesc getResult() {
		return this.results.get(0);
	}

	public String getTableAlias() {
		return tableAlias.getValue();
	} 
	
	public void setTableAlias(String tableName ){
		this.tableAlias.setValue(tableName);
	}

	public TokenIdentifier getTableAliasToken() {
		return tableAlias;
	}
	
	public List<Connection> getConnections(int partNum) {
		if(this.table.isPartioned()){
			return this.table.getPartition(partNum).getConnections();
		}
		else{
			return this.table.getConnections();
		}
	}
	
	public  List<URI> getURIs(int partNum){
		List<URI> uris = new ArrayList<URI>();
		for (Connection connection : this.getConnections(partNum)) {
			uris.add(URI.create(connection.getUrl()));
		}
		return uris;
	}
	
	public String getAttsDDL(){
		return this.table.attsToDDL();
	}
	
	public String getTableName(){
		return this.table.getName();
	}

	public String getTableName(int partNum){
		return this.table.getName()+PART_PREFIX+partNum;
	}
	
	public void setTable(Table table) {
		this.table = table;
	}

	public Collection<Attribute> getAttributes(){
		return this.table.getAttributes();
	}
	
	public Collection<Partition> getPartitions(){
		return this.table.getPartitions();
	}
	
	public Collection<PartitionAttribute> getPartitionAttributes(){
		if(this.table.isPartioned())
			return this.table.getPartitionAttributes();
		else 
			return null;
	}
	
	public boolean isPartitioned(){
		return this.table.isPartioned();
	}
	
	public EnumPartitionType getPartitionType(){
		return this.table.getPartitionType();
	}
	
	public int getPartitionCount(){
		return (int)this.table.getPartitionCount();
	}
	
	public String getRefTableName(){
		return this.table.getRefTable().getName();
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
		//final String opDummy = TABLE_PREFIX + getOperatorId().toString();
		final String opDummy = getOperatorId().toString();
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
	
	@Override
	public String toString() {
		StringBuffer value = new StringBuffer();
		value.append("(");
		value.append(this.operatorId);
		value.append(":");
		value.append(this.type);
		value.append(" ");
		value.append(this.tableAlias);
		value.append(")");
		return value.toString();
	}
}