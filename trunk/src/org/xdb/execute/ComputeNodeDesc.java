package org.xdb.execute;

import org.xdb.Config;
import org.xdb.server.AbstractNodeDesc;

/**
 * Describes the properties of a ComputeNode (i.e., URL, port and slots). 
 * 
 * @author Timo Jacobs
 * 
 */
public class ComputeNodeDesc extends AbstractNodeDesc {
	private static final long serialVersionUID = -6943589620125008473L;

	private final int port;

	// constructors

	public ComputeNodeDesc(final String host, final int port) {
		this(host, port, Config.COMPUTE_SLOTS);
	}
	
	public ComputeNodeDesc(final String host, final int port, final int slots) {
		super(host, slots);
		this.port = port;
	}

	// getters and setters
	public int getPort() {
		return port;
	}

	// methods
	@Override
	public String toString() {
		return "("+url + ":" + port+")";
	}

	@Override
	public int hashCode() {
		return this.url.hashCode() + this.port;
	}
}
