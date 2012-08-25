package org.xdb.execute.signals;

import java.io.Serializable;

/**
 * Signal which is send by operator (source) to consumer
 * @author cbinnig
 *
 */
public class CloseSignal implements Serializable {

	private static final long serialVersionUID = 8951710674335728187L;
	
	//source: operator id
	private Integer source;
	
	//constructors
	public CloseSignal(Integer source) {
		
		this.source = source;
	}

	//getter and setters
	public Integer getSource() {
		return source;
	}
}
