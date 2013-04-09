package org.xdb.funsql.compile.operator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.parallelize.PartitionInfo;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.Connection;
import org.xdb.utils.Identifier;
import org.xdb.utils.SetUtils;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public abstract class AbstractCompileOperator implements Serializable {

	private static final long serialVersionUID = -5531022011681321483L;

	//partionInformation
	protected PartitionInfo partitionOutputInfo;
	protected Set<PartitionInfo> partitionCandiates = new HashSet<PartitionInfo>();
	
	// attributes
	protected Vector<ResultDesc> results;
	protected EnumOperator type;
	protected Vector<AbstractCompileOperator> children = new Vector<AbstractCompileOperator>();
	protected Vector<AbstractCompileOperator> parents = new Vector<AbstractCompileOperator>();
	protected Connection wishedConnection = null;

	// unique operator id
	protected Identifier operatorId;

	// constructors
	/**
	 * Copy Constructor
	 * @param toCopy Element to copy
	 */
	@SuppressWarnings("unchecked")
	public AbstractCompileOperator(AbstractCompileOperator toCopy) {
		this.children = (Vector<AbstractCompileOperator>) toCopy.children.clone();
		this.parents = (Vector<AbstractCompileOperator>) toCopy.parents.clone();
		this.type = toCopy.type;
		this.results = toCopy.results;
		
		Vector<ResultDesc> cloneresults = new Vector<ResultDesc>();
	
			for(ResultDesc rd :toCopy.results){
				if(rd !=null){
					cloneresults.add(rd.clone());
				}
			
			}
		
		this.results = cloneresults;
		if(toCopy.getWishedConnection()!= null){
			this.wishedConnection = Catalog.getConnection(toCopy.getWishedConnection().getOid());
		}
	
		if(toCopy.partitionOutputInfo!= null){
			this.partitionOutputInfo = new PartitionInfo(toCopy.partitionOutputInfo);
		}

	}

	
	public AbstractCompileOperator(int resultNumber) {
		this.results = new Vector<ResultDesc>(resultNumber);
	}

	
	// getters and setters
	
	
	/** Gets the partition Info of the Operator, so how many parts the output has and on what column it is partioned
	 * 
	 * @return
	 */
	public PartitionInfo getPartitionOutputInfo() {
		return partitionOutputInfo;
	}

	/** Set the partionInfo for this operator
	 * @param partitionOutputInfo
	 */
	public void setPartitionOutputInfo(PartitionInfo partitionOutputInfo) {
		this.partitionOutputInfo = partitionOutputInfo;
	}
	
	public void addPartitionCandiate(PartitionInfo pi){
		this.partitionCandiates.add(pi);
	}
	
	
	public Set<PartitionInfo> getPartitionCandiates() {
		return partitionCandiates;
	}

	public void setPartitionCandiates(Set<PartitionInfo> partitionCandiates) {
		this.partitionCandiates = partitionCandiates;
	}

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
	 * @return wished connection, null if not set
	 */
	public Connection getWishedConnection() {
		return this.wishedConnection;
	}

	public Identifier getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(Identifier operatorId) {
		this.operatorId = operatorId;
	}

	public ResultDesc getResult() {
		return results.get(0);
	}

	public ResultDesc getResult(int i) {
		return results.get(i);
	}

	public void setResult(int i, ResultDesc result) {
		this.results.set(i, result);
	}

	public void setResult(ResultDesc result) {
		this.results.set(0, result);
	}

	public void addResult(ResultDesc result) {
		this.results.add(result);
	}

	public EnumOperator getType() {
		return type;
	}

	public void setType(EnumOperator type) {
		this.type = type;
	}

	public int getResultNumber() {
		return this.results.size();
	}

	public void setChildren(Vector<AbstractCompileOperator> sources) {
		this.children = sources;
	}
	
	public void resetChildren(){
		this.children = new Vector<AbstractCompileOperator>();
	}

	public void setChild(int idx, AbstractCompileOperator child) {
		this.children.set(idx, child);
	}
	
	public void setChild(AbstractCompileOperator oldChild, AbstractCompileOperator newchild) {
		int oldIndex = this.children.indexOf(oldChild);
		this.children.set(oldIndex,newchild);
	}

	public void removeParent(int idx) {
		this.parents.remove(idx);
	}

	public void setParent(int idx, AbstractCompileOperator parent) {
		this.parents.set(idx, parent);
	}
	

	public void setParent(AbstractCompileOperator oldparent, AbstractCompileOperator newparent) {
		int oldIndex= parents.indexOf(oldparent);
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
	
	
	public void setWishedConnection(final Connection conn) {
		this.wishedConnection = conn;
	}

	// methods
	public int findChild(AbstractCompileOperator child) {
		for (int i = 0; i < this.children.size(); ++i) {
			if (this.children.get(i).equals(child)) {
				return i;
			}
		}
		return -1;
	}

	public int findParent(AbstractCompileOperator parent) {
		for (int i = 0; i < this.parents.size(); ++i) {
			if (this.parents.get(i).equals(parent)) {
				return i;
			}
		}
		return -1;
	}

	public boolean removeParent(AbstractCompileOperator parent) {
		return this.parents.remove(parent);
	}

	public void addParents(Vector<AbstractCompileOperator> parents) {
		this.parents.addAll(parents);
	}

	/**
	 * Get list of all result TokenAttributes.
	 */
	protected List<String> getResultTableAttributes() {
		return SetUtils.attributesToTableString(getResult().getAttributes());
	}

	protected List<String> getResultAttributes() {
		return SetUtils.attributesToString(getResult().getAttributes());
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

	/**
	 * Generate SQL representation of this operator
	 * 
	 * @return
	 */
	public abstract String toSqlString();

	/**
	 * Renames attributes names according to new id of child
	 * 
	 * @param oldId
	 * @param newId
	 */
	public abstract void renameAttributes(String oldChildId, String newChildId);

	/**
	 * Checks if operator is leave
	 * 
	 * @return
	 */
	public abstract boolean isLeaf();

	/**
	 * Generates a visual graph representation of the operator
	 * 
	 * @param g
	 * @return
	 */
	public Error traceOperator(Graph g, HashMap<Identifier, GraphNode> nodes) {
		Error err = new Error();
		GraphNode node = nodes.get(this.operatorId);
		StringBuffer header = new StringBuffer();
		// header
		if (Config.TRACE_COMPILE_PLAN_HEADER) {
		}
			header.append("Partition candidates:");
			header.append(AbstractToken.NEWLINE);
			for (PartitionInfo pi : this.getPartitionCandiates()) {
				header.append(pi.toString());
				header.append(AbstractToken.NEWLINE);
			}
			
			header.append("Parents: ");
			header.append(this.parents.toString());
			header.append(AbstractToken.NEWLINE);
			header.append("Children: ");
			header.append(this.children.toString());
			if (this.results.size() == 1) {
				header.append(AbstractToken.NEWLINE);
				if(this.getResult()!=null){
					header.append(this.getResult().toString());
				}
			
			}
			header.append(AbstractToken.NEWLINE);
			

		
		node.getInfo().setHeader(header.toString());
		// body
		node.getInfo().setCaption(this.toString());
		
		if(this.getPartitionOutputInfo()!= null){
			node.getInfo().setFooter(this.getPartitionOutputInfo().toString());
		}
		
		return err;
	}

	/**
	 * Rename all necessary field of given Operator is used for rerenaming of
	 * operators attributes who acess table to avoid intermediary result
	 * materialization
	 * 
	 * @param renamedAttributes
	 * @param renamedOps
	 * @return boolean to verify if some elements of this operator were renamed
	 */
	public boolean renameOperator(HashMap<String, String> renamedAttributes,
			Vector<String> renamedOps) {
		String newName;
		boolean renamed = false;
		for (TokenAttribute tA : getResult().getAttributes()) {
			newName = renamedAttributes.get(tA.getName().getName());
			// if no new name was found there is no need to rename, because not
			// accessd
			// table op
			if (newName == null)
				continue;
			renamed = true;
			tA.setName(newName);
		}
		return renamed;
	}
}