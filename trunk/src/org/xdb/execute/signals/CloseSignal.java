package org.xdb.execute.signals;

import java.io.Serializable;

import org.xdb.utils.Identifier;

/**
 * Signal which is send by operator (source) to consumer
 * @author cbinnig
 *
 */
public class CloseSignal implements Serializable {

	private static final long serialVersionUID = 8951710674335728187L;
	
	//source: operator id
	private Identifier source;
	
	//constructors
	public CloseSignal(Identifier source) {
		
		this.source = source;
	}

	//getter and setters
	public Identifier getSource() {
		return source;
	}
}
