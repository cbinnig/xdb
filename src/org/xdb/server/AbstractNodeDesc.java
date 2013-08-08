package org.xdb.server;

import java.io.Serializable;

public abstract class AbstractNodeDesc implements Serializable {

	private static final long serialVersionUID = -2037296709774565591L;

	protected final String url;

	// constructor
	public AbstractNodeDesc(final String url) {
		this.url = url;
	}

	// getters and setters
	public String getUrl() {
		return url;
	}
	
	@Override
	public int hashCode() {
		return this.url.hashCode();
	}
	
	@Override
	public boolean equals(Object o){
		AbstractNodeDesc nodeDesc = (AbstractNodeDesc)o;
		return this.url.equalsIgnoreCase(nodeDesc.url);
	}
}
