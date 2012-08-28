package org.xdb.execute.operators;

import java.io.Serializable;

import org.xdb.utils.Identifier;

/**
 * Consumer specification with URL for consumer node and operator ID
 * @author cbinnig
 *
 */
public class OperatorDesc implements Serializable {
	
	private static final long serialVersionUID = -7230252328170776355L;
	
	//operator ID
	private Identifier operatorID;
	
	//operator URL
	private String operatorNode;
	
	//constructors
	public OperatorDesc(Identifier operatorID, String operatorNode) {
		super();
		this.operatorID = operatorID;
		this.operatorNode = operatorNode;
	}

	//getter and setters
	public Identifier getOperatorID() {
		return operatorID;
	}

	public String getOperatorNode() {
		return operatorNode;
	}
	
	//methods
	@Override
	public int hashCode(){
		return this.operatorID.hashCode();
	}
	
	@Override
	public String toString(){
		return "("+this.operatorNode+","+this.operatorID+")";
	}
	
}
