package org.xdb.tracker.signals;

import java.io.Serializable;

import org.xdb.execute.ComputeNodeDesc;

public class RegisterSignal implements Serializable {

	private static final long serialVersionUID = -4487814618914464842L;
	
	private ComputeNodeDesc computeNodeDesc;

	// constructors
	public RegisterSignal(String url, Integer slots) {
		super();
		this.computeNodeDesc = new ComputeNodeDesc(url, slots);
	}
	
	public RegisterSignal(ComputeNodeDesc computeNodeDesc) {
		super();
		this.computeNodeDesc = computeNodeDesc;
	}
	
	// getters and setters
	public ComputeNodeDesc getComputeNodeDesc() {
		return computeNodeDesc;
	}
}
