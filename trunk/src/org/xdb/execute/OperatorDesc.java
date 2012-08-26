package org.xdb.execute;

import java.io.Serializable;

/**
 * Consumer specification with URL for consumer node and operator ID
 * @author cbinnig
 *
 */
public class OperatorDesc implements Serializable {
	
	private static final long serialVersionUID = -7230252328170776355L;
	
	//operator ID
	private Integer operatorID;
	
	//operator URL
	private String operatorNode;
	
	//constructors
	public OperatorDesc(Integer operatorID, String operatorNode) {
		super();
		this.operatorID = operatorID;
		this.operatorNode = operatorNode;
	}

	//getter and setters
	public Integer getOperatorID() {
		return operatorID;
	}

	public String getOperatorNode() {
		return operatorNode;
	}
	
	//methods
	@Override
	public int hashCode(){
		return this.operatorID;
	}
	
	@Override
	public String toString(){
		return "("+this.operatorNode+","+this.operatorID+")";
	}
	
}
