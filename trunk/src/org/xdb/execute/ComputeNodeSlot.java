package org.xdb.execute;

import java.io.Serializable;

/**
 * Data-Class, which contains Host and Port of a ComputeNode-Slot
 * @author Timo Jacobs
 *
 */
public class ComputeNodeSlot implements Serializable {
	private static final long serialVersionUID = -6943589620125008473L;
	
	private final String host;
	private final int port;
	
	public ComputeNodeSlot(final String host, final int port) {
		this.host = host;
		this.port = port;
	}
	
	public String getHost() {
		return host;
	}
	
	public int getPort() {
		return port;
	}
	
	@Override
	public String toString() {
		return host;
	}
	
}
