package org.xdb.funsql.compile.operator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.ITreeVisitor;
import org.xdb.utils.Identifier;

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

	
	/**
	 * @param v TreeVisitor (visitor pattern)
	 * @return checked: okay?
	 */
	abstract void accept(ITreeVisitor v);
		
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
		this.results.set(i,  result);
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
	
	public abstract boolean isPushDownAllowed(EnumPushDown pd);
	
	// methods
	
	/**
	 * Generate SQL representation of this operator
	 * @return
	 */
	public abstract String toSqlString();
	
	
	/**
	 * Generates a visual graph representation of the operator
	 * @param g
	 * @return
	 */
	public abstract Error traceGraph(Graph g, HashMap<Identifier, GraphNode> nodes);
	
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
