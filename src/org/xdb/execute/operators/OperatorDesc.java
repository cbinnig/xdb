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
	private ComputeNodeDesc computeSlot;  
	
	// operator status
	private QueryOperatorStatus operatorStatus; 
	
	// constructors
	public OperatorDesc(Identifier execOpId, ComputeNodeDesc computeSlot) {
		super();
		this.execOpId = execOpId;
		this.computeSlot = computeSlot;
	}

	// getter and setters
	public Identifier getOperatorID() {
		return execOpId;
	}

	public ComputeNodeDesc getComputeSlot() {
		return computeSlot;
	}
    
	/**
	 * @return the operatorStatus
	 */
	public QueryOperatorStatus getOperatorStatus() {
		return operatorStatus;
	}

	/**
	 * @param operatorStatus the operatorStatus to set
	 */
	public void setOperatorStatus(QueryOperatorStatus operatorStatus) {
		this.operatorStatus = operatorStatus;
	}
	
	// methods
	@Override
	public int hashCode() {
		return this.execOpId.hashCode();
	}

	@Override
	public String toString() {
		return "(" + this.computeSlot + "," + this.execOpId + "," + this.operatorStatus + ")";
	}

	

}
