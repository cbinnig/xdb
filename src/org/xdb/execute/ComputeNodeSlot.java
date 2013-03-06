package org.xdb.execute;

import java.io.Serializable;

/**
 * Describes the properties of a ComputeNodeSlot (i.e., URL and port) One
 * ComputeNode can have multiple ComputeNodeSlots
 * 
 * @author Timo Jacobs
 * 
 */
public class ComputeNodeSlot implements Serializable {
	private static final long serialVersionUID = -6943589620125008473L;

	private final String host;
	private final int port;

	// constructors

	public ComputeNodeSlot(final String host, final int port) {
		this.host = host;
		this.port = port;
	}

	// getters and setters

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	// methods

	@Override
	public String toString() {
		return host + ":" + port;
	}

	@Override
	public int hashCode() {
		return this.host.hashCode() + this.port;
	}

	@Override
	public boolean equals(Object o) {
		ComputeNodeSlot slot = (ComputeNodeSlot) o;
		if (slot.host.equals(this.host))
			return true;
		return false;
	}

}
