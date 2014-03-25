package org.xdb.execute.signals;

import java.io.Serializable;

import org.xdb.utils.Identifier;

/**
 * Signal which is send to compute node from a source operator to signal its
 * consumers that the input from source is ready
 * 
 * @author cbinnig
 * 
 */
public class ReadySignal implements Serializable {

	private static final long serialVersionUID = 6242268268961828041L;

	// source: operator id
	private Identifier source;

	// consumer: operator id
	private Identifier consumer;

	// constructors
	public ReadySignal(Identifier source, Identifier consumer) {
		this.source = source;
		this.consumer = consumer;
	}

	// getter and setters
	public Identifier getSource() {
		return source;
	}

	public Identifier getConsumer() {
		return consumer;
	}
}
