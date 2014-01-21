package org.xdb.execute;

import org.xdb.server.AbstractNodeDesc;

/**
 * Describes the properties of a ComputeNode (i.e., URL, port). 
 * 
 * @author Timo Jacobs
 * 
 */
public class ComputeNodeDesc extends AbstractNodeDesc {
	private static final long serialVersionUID = -6943589620125008473L;

	private final int port;

	// constructors
	public ComputeNodeDesc(final String host, final int port) {
		super(host);
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
	
	public boolean equalsWPort(ComputeNodeDesc compNode){
		if(!compNode.url.equals(this.url))
			return false;
		
		if(compNode.port!=this.port)
			return false;
		
		return true;
	}
}
