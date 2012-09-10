package org.xdb.funsql.compile.operator;

import java.io.Serializable;


public abstract class AbstractOperator implements Serializable {

	private static final long serialVersionUID = -5531022011681321483L;

	//attributes
	protected ResultDesc result;
	protected EnumOperator type;
	
	//getters and setters
	public ResultDesc getResult() {
		return result;
	}

	public void setResult(ResultDesc result) {
		this.result = result;
	}

	public EnumOperator getType() {
		return type;
	}

	public void setType(EnumOperator type) {
		this.type = type;
	}
}
