package org.xdb.tracker;

import java.io.Serializable;

import org.xdb.Config;

public class QueryTrackerNodeDesc implements Serializable {

	private static final long serialVersionUID = -3489640841708710463L;
	private final String url;
	private final int slots;

	public QueryTrackerNodeDesc(final String url) {
		this(url, Config.QUERYTRACKER_SLOTS);
	}

	public QueryTrackerNodeDesc(final String url, final int slots) {
		this.url = url;
		this.slots = slots;
	}


	public String getUrl() {
		return url;
	}

	public int getSlots() {
		return slots;
	}

	@Override 
	public String toString(){
		final StringBuffer value = new StringBuffer();
		value.append("(");
		value.append(url);
		value.append(",");
		value.append(slots);
		value.append(")");
		return value.toString();
	}
}
