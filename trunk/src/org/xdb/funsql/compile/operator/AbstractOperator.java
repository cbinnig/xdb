package org.xdb.funsql.compile.operator;

import java.io.Serializable;
import java.util.Set;
import java.util.Vector;

import org.xdb.utils.Identifier;


public abstract class AbstractOperator implements Serializable {

	private static final long serialVersionUID = -5531022011681321483L;

	//attributes
	protected Vector<ResultDesc> results;
	protected EnumOperator type;
	
	// unique operator id
	protected Identifier operatorId;
	
	/**
	 * Get all source operators.
	 * @return set of all dependency operators, empty set if no given
	 */
	public abstract Set<AbstractOperator> getSourceOperators(); 
		
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
	
	// methods
	
	/**
	 * Generate SQL representation of this operator
	 * @return
	 */
	public abstract String toSqlString();
	
	
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
