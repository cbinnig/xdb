package org.xdb.funsql.compile.operator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.operator.ITreeVisitor;
import org.xdb.utils.Identifier;
import org.xdb.utils.SetUtils;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;


public abstract class AbstractOperator implements Serializable {

	private static final long serialVersionUID = -5531022011681321483L;

	//attributes
	protected Vector<ResultDesc> results;
	protected EnumOperator type;
	protected Vector<AbstractOperator> children = new Vector<AbstractOperator>();
	protected Vector<AbstractOperator> parents = new Vector<AbstractOperator>();
	
	// unique operator id
	protected Identifier operatorId;
	
	public AbstractOperator(){
		this.children = new Vector<AbstractOperator>();
		this.parents = new Vector<AbstractOperator>();
	}
	
	public AbstractOperator(AbstractOperator toCopy){
		this.children = toCopy.children;
		this.parents = toCopy.parents;
		this.type = toCopy.type;
		this.results = toCopy.results;
	}
	
	/**
	 * Get all source operators.
	 * @return set of all dependency operators, empty set if no given
	 */
	public Vector<AbstractOperator> getSourceOperators(){
		return this.children;
	}

	/**
	 * Get all destination operators.
	 * @return set of all dependency operators, empty set if no given
	 */
	public Vector<AbstractOperator> getDestinationOperators(){
		return this.parents;
	}

	//constructors
	public AbstractOperator(int resultNumber){
		this.results = new Vector<ResultDesc>(resultNumber);
	}
	
	//getters and setters
	public Identifier getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(Identifier operatorId) {
		this.operatorId = operatorId;
	}
	
	public ResultDesc getResult(int i) {
		return results.get(i);
	}

	public void setResult(int i, ResultDesc result) {
		this.results.add(i,  result);
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
	
	public int getResultNumber(){
		return this.results.size();
	}
	
	public void setSourceOperators(Vector<AbstractOperator> sources) {
		this.children = sources;
	}
	
	public void setDestinationOperators(Vector<AbstractOperator> destinations) {
		this.parents = destinations;
	}
	
	public void addDestinationOperators(AbstractOperator destination) {
		this.parents.add(destination);
	}
	
	// methods
	public boolean replaceChild(AbstractOperator oldChild, AbstractOperator newChild){
		boolean replaced = false;
		
		for(int i=0; i<this.children.size(); ++i){
			AbstractOperator child = this.children.get(i);
			if(child.equals(oldChild)){
				this.children.set(i, newChild);
				replaced = true;
			}
		}
		
		return replaced;
	}
	
	/**
	 * @param v TreeVisitor (visitor pattern)
	 * @return checked: okay?
	 */
	public abstract void accept(ITreeVisitor v);
		
	
	/**
	 * Checks if pushdown is allowed
	 * @param pd
	 * @return
	 */
	public abstract boolean isPushDownAllowed(EnumPushDown pd);
	
	/**
	 * Generate SQL representation of this operator
	 * @return
	 */
	public abstract String toSqlString();
	
	/**
	 * Get list of all result TokenAttributes.
	 */
	protected List<String> getResultAttributes() {
		return SetUtils.attributesToString(getResult(0).getAttributes());
	}
	
	/**
	 * Checks if operator is leave
	 * @return
	 */
	public abstract boolean isLeaf();
	
	/**
	 * Generates a visual graph representation of the operator
	 * @param g
	 * @return
	 */
	public abstract Error traceGraph(Graph g, HashMap<Identifier, GraphNode> nodes);
	
	@Override
	public boolean equals(Object o){
		AbstractOperator op  = (AbstractOperator)o;
		if(op.operatorId.equals(this.operatorId))
			return true;
		
		return false;
	}
	
	@Override
	public String toString(){
		StringBuffer value = new StringBuffer();
		value.append("(");
		value.append(this.operatorId);
		value.append(":");
		value.append(this.type);
		value.append(")");
		return value.toString();
	}
}
