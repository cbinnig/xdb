package org.xdb.funsql.compile.operator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.metadata.Connection;
import org.xdb.utils.Identifier;
import org.xdb.utils.SetUtils;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public abstract class AbstractCompileOperator implements Serializable {

	private static final long serialVersionUID = -5531022011681321483L;

	// attributes
	protected Vector<ResultDesc> results;
	protected EnumOperator type;
	protected Vector<AbstractCompileOperator> children = new Vector<AbstractCompileOperator>();
	protected Vector<AbstractCompileOperator> parents = new Vector<AbstractCompileOperator>(); 
	protected Set<Connection> wishedConnections = new HashSet<Connection>();  
	
    // unique operator id
	protected Identifier operatorId;

	// constructors
	/**
	 * Copy Constructor
	 * 
	 * @param toCopy
	 *            Element to copy
	 */
	public AbstractCompileOperator(AbstractCompileOperator toCopy) {
		this.children = new Vector<AbstractCompileOperator>(toCopy.children);
		this.parents = new Vector<AbstractCompileOperator>(toCopy.parents);
		this.type = toCopy.type;
		this.wishedConnections = new HashSet<Connection>(toCopy.wishedConnections);
		
		this.results = new Vector<ResultDesc>();		
		for (ResultDesc rd : toCopy.results) {
			if (rd != null) {
				this.results.add(rd.clone());
			}
		}
	}

	public AbstractCompileOperator(int resultNumber) {
		this.results = new Vector<ResultDesc>(resultNumber);
	}

	// getters and setters
	/**
	 * Get all source operators.
	 * 
	 * @return set of all dependency operators, empty set if no given
	 */
	public Vector<AbstractCompileOperator> getChildren() {
		return this.children;
	}

	/**
	 * Get all destination operators.
	 * 
	 * @return set of all dependency operators, empty set if no given
	 */
	public Vector<AbstractCompileOperator> getParents() {
		return this.parents;
	}

	/**
	 * Get wished connection.
	 * 
	 * @return wished connection, null if not set
	 */ 
	public Connection getWishedConnection(){
		if(this.wishedConnections.isEmpty())
			return null;
		
		return this.wishedConnections.iterator().next();
	}
	
	public Set<Connection> getWishedConnections() {
		return wishedConnections;
	}
	
	public void addWishedConnection(final Connection conn) {
		this.wishedConnections.add(conn);
	}

	public void addWishedConnections(Set<Connection> wishedConnections) {
		this.wishedConnections.addAll(wishedConnections);
	}

	public Identifier getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(Identifier operatorId) {
		this.operatorId = operatorId;
	}

	public boolean hasResult() {
		return this.results.size() >= 1;
	}

	public ResultDesc getResult() {
		return results.get(0);
	}

	public void replaceResult(ResultDesc result) {
		this.results.set(0, result);
	}

	public void setResult(ResultDesc result) {
		if (this.results.size() == 1)
			this.setResult(result);
		else
			this.results.add(result);
	}

	public EnumOperator getType() {
		return type;
	}

	public void setType(EnumOperator type) {
		this.type = type;
	}

	public void setChildren(Vector<AbstractCompileOperator> sources) {
		this.children = sources;
	}

	public void resetChildren() {
		this.children = new Vector<AbstractCompileOperator>();
	}

	public void setChild(int idx, AbstractCompileOperator child) {
		this.children.set(idx, child);
	}

	public void setChild(AbstractCompileOperator oldChild,
			AbstractCompileOperator newchild) {
		int oldIndex = this.children.indexOf(oldChild);
		this.children.set(oldIndex, newchild);
	}

	public void removeParent(int idx) {
		this.parents.remove(idx);
	}

	public void setParent(int idx, AbstractCompileOperator parent) {
		this.parents.set(idx, parent);
	}

	public void setParent(AbstractCompileOperator oldparent,
			AbstractCompileOperator newparent) {
		int oldIndex = parents.indexOf(oldparent);
		this.parents.set(oldIndex, newparent);
	}

	public void setParents(Vector<AbstractCompileOperator> parents) {
		this.parents = parents;
	}

	public void addParent(AbstractCompileOperator parent) {
		this.parents.add(parent);
	}

	public void addChild(AbstractCompileOperator child) {
		this.children.add(child);
	}

	public boolean removeParent(AbstractCompileOperator parent) {
		return this.parents.remove(parent);
	}

	public void addParents(Vector<AbstractCompileOperator> parents) {
		this.parents.addAll(parents);
	}
	
	public void replaceChild(int childIdx, SQLUnary sqlUnaryOp) {
		this.children.set(childIdx, sqlUnaryOp);
	}

	public void replaceParent(int parentIdx, SQLUnary sqlUnaryOp) {
		this.parents.set(parentIdx, sqlUnaryOp);
	}

	public boolean isTable(){
		return this.type.equals(EnumOperator.TABLE);
	}
	
	// methods

	/**
	 * Generate SQL representation of this operator
	 * 
	 * @return
	 */
	public abstract String toSqlString();

	/**
	 * Renames attributes names according to new operator-id of child
	 * 
	 * @param oldId
	 * @param newId
	 */
	public abstract void renameTableOfAttributes(String oldChildId, String newChildId);

	/**
	 * Checks if operator is leave
	 * 
	 * @return
	 */
	public abstract boolean isLeaf();

	/**
	 * Returns index of child in list of children
	 * 
	 * @param child
	 * @return
	 */
	public int findChild(AbstractCompileOperator child) {
		for (int i = 0; i < this.children.size(); ++i) {
			if (this.children.get(i).equals(child)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns index of parent in list of parents
	 * 
	 * @param parent
	 * @return
	 */
	public int findParent(AbstractCompileOperator parent) {
		for (int i = 0; i < this.parents.size(); ++i) {
			if (this.parents.get(i).equals(parent)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Rename all necessary attributes using a map (old->new)
	 * 
	 * @param renamedAttributes
	 * @param renamedOps
	 * @return boolean to verify if some elements of this operator were renamed
	 */
	public boolean renameAttributes(HashMap<String, String> renamedAttributes,
			Vector<String> renamedOps) {
		
		boolean renamed = false;

		// rename result attributes
		for (TokenAttribute tA : getResult().getAttributes()) {
			if (renamedAttributes.containsKey(tA.getName().getValue())) {
				if (tA.renameAttribute(renamedAttributes))
					renamed = true;
			}
		}

		return renamed;
	}
	
	
	
	/**
	 * Creates a SQL string representing the result attributes (without table
	 * name) e.g., [a, b]
	 * 
	 * @return
	 */
	protected List<String> resultAttributesWothTableToSQL() {
		return SetUtils.attributesWithTableToSQLString(getResult()
				.getAttributes());
	}

	/**
	 * Creates a SQL string representing the result attributes (with table name)
	 * e.g., [R.a, R.b]
	 * 
	 * @return
	 */
	protected List<String> resultAttributesToSQL() {
		return SetUtils.attributesToSQLString(getResult().getAttributes());
	}

	/**
	 * Generates a visual graph representation of the operator
	 * 
	 * @param g
	 * @return
	 */
	public Error traceOperator(Graph g, Map<Identifier, GraphNode> nodes) {
		Error err = new Error();
		GraphNode node = nodes.get(this.operatorId);

		// header
		if (Config.TRACE_COMPILE_PLAN_HEADER) {
			StringBuffer header = new StringBuffer();
			if (Config.TRACE_COMPILE_PLAN_HEADER_PARENTCHILD) {
				header.append("Parents: ");
				header.append(this.parents.toString());
				header.append(AbstractToken.NEWLINE);
				header.append("Children: ");
				header.append(this.children.toString());
				header.append(AbstractToken.NEWLINE);
			}
			if (Config.TRACE_COMPILE_PLAN_HEADER_RESULT && this.hasResult()) {
				header.append("Result:");
				header.append(AbstractToken.NEWLINE);
				if (this.getResult() != null) {
					header.append(this.getResult().toString());
				}
				header.append(AbstractToken.NEWLINE);
			}
			
			node.getInfo().setHeader(header.toString());
		}

		// body
		node.getInfo().setCaption(this.toString());

		// footer
		if (Config.TRACE_COMPILE_PLAN_FOOTER) {
			StringBuffer footer = new StringBuffer();
			node.getInfo().setFooter(footer.toString());
		}

		return err;
	}

	@Override
	public boolean equals(Object o) {
		AbstractCompileOperator op = (AbstractCompileOperator) o;
		if (op.operatorId.equals(this.operatorId))
			return true;

		return false;
	}

	@Override
	public String toString() {
		StringBuffer value = new StringBuffer();
		value.append("(");
		value.append(this.operatorId);
		value.append(":");
		value.append(this.type);
		value.append(")");
		return value.toString();
	}

}