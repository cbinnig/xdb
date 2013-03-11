package org.xdb.execute.operators;

import java.io.Serializable;

import org.xdb.execute.ComputeNodeSlot;
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
	private ComputeNodeSlot computeSlot;

	// constructors
	public OperatorDesc(Identifier execOpId, ComputeNodeSlot computeSlot) {
		super();
		this.execOpId = execOpId;
		this.computeSlot = computeSlot;
	}

	// getter and setters
	public Identifier getOperatorID() {
		return execOpId;
	}

	public ComputeNodeSlot getComputeSlot() {
		return computeSlot;
	}

	// methods
	@Override
	public int hashCode() {
		return this.execOpId.hashCode();
	}

	@Override
	public String toString() {
		return "(" + this.computeSlot + "," + this.execOpId + ")";
	}

}
