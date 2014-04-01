package org.xdb.execute.signals;

import java.io.Serializable;

import org.xdb.execute.operators.AbstractExecuteOperator;

/**
 * Signal which is send to compute node to close consumer operator (i.e., delete
 * its input/output tables)
 * 
 * @author cbinnig
 * 
 */
public class CloseSignal implements Serializable {

	private static final long serialVersionUID = 8951710674335728187L;

	// source: operator id
	private AbstractExecuteOperator exeOp;

	// constructors
	public CloseSignal(AbstractExecuteOperator exeOp) {

		this.exeOp = exeOp;
	}

	// getter and setters
	public AbstractExecuteOperator getExecuteOperator() {
		return exeOp;
	}
}
