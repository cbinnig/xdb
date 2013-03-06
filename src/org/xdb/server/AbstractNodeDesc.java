package org.xdb.server;

import java.io.Serializable;

public abstract class AbstractNodeDesc implements Serializable {

	private static final long serialVersionUID = -2037296709774565591L;

	protected final String url;
	protected final int slots;

	// constructor
	public AbstractNodeDesc(final String url, final int slots) {
		this.url = url;
		this.slots = slots;
	}

	// getters and setters
	public String getUrl() {
		return url;
	}

	public int getSlots() {
		return slots;
	}
}
