package org.xdb.execute.signals;

import java.io.Serializable;

/**
 * Signal which is send by operator (source) to consumer
 * @author cbinnig
 *
 */
public class ReadySignal implements Serializable {

	private static final long serialVersionUID = 6242268268961828041L;

	//source: operator id
	private Integer source;
	
	//consumer: operator id
	private Integer consumer;
	
	//constructors
	public ReadySignal(Integer source, Integer consumer) {
		
		this.source = source;
		this.consumer = consumer;
	}

	//getter and setters
	public Integer getSource() {
		return source;
	}

	public Integer getConsumer() {
		return consumer;
	}
}
