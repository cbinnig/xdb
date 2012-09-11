package org.xdb.funsql.compile.operator;

import java.io.Serializable;
import java.util.Vector;


public abstract class AbstractOperator implements Serializable {

	private static final long serialVersionUID = -5531022011681321483L;

	//attributes
	protected Vector<ResultDesc> results;
	protected EnumOperator type;
	
	//constructors
	public AbstractOperator(int resultNumber){
		this.results = new Vector<ResultDesc>(resultNumber);
	}
	
	//getters and setters
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
}
