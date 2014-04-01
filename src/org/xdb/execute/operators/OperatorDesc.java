package org.xdb.execute.operators;

import java.io.Serializable;

import org.xdb.execute.ComputeNodeDesc;
import org.xdb.utils.Identifier;

/**
 * Operator specification with URL of compute node and operator ID
 * 
 * @author cbinnig
 * 
 */
public class OperatorDesc implements Serializable {

	private static final long serialVersionUID = -7230252328170776355L;

	// operator ID
	private Identifier execOpId;

	// operator URL
	private ComputeNodeDesc computeNode;  
	
	// operator status
	private EnumOperatorStatus operatorStatus; 
	
	// constructors
	public OperatorDesc(Identifier execOpId, ComputeNodeDesc computeNode) {
		super();
		this.execOpId = execOpId;
		this.computeNode = computeNode;
		this.operatorStatus = EnumOperatorStatus.INIT;
	}

	// getter and setters
	public Identifier getOperatorID() {
		return execOpId;
	}

	public ComputeNodeDesc getComputeNode() {
		return computeNode;
	}
    
	/**
	 * @return the operatorStatus
	 */
	public EnumOperatorStatus getOperatorStatus() {
		return operatorStatus;
	}

	/**
	 * @param operatorStatus the operatorStatus to set
	 */
	public void setOperatorStatus(EnumOperatorStatus operatorStatus) {
		if(operatorStatus!=null)
			this.operatorStatus = operatorStatus;
	}
	
	// methods
	@Override
	public int hashCode() {
		return this.execOpId.hashCode();
	}

	@Override
	public String toString() {
		return "(" + this.computeNode + "," + this.execOpId + "," + this.operatorStatus + ")";
	}
}
