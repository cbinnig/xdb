package org.xdb.execute.signals;

import java.io.Serializable;

import org.xdb.utils.Identifier;

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
	private Identifier consumer;

	// constructors
	public CloseSignal(Identifier consumer) {

		this.consumer = consumer;
	}

	// getter and setters
	public Identifier getConsumer() {
		return consumer;
	}
}
