package org.xdb.tracker;

import org.xdb.Config;
import org.xdb.server.AbstractNodeDesc;

/**
 * Describes the properties of a QueryTrackerNode (i.e., URL and slots)
 * 
 * @author cbinnig
 * 
 */
public class QueryTrackerNodeDesc extends AbstractNodeDesc {

	private static final long serialVersionUID = -3489640841708710463L;
	
	// constructors
	public QueryTrackerNodeDesc(final String url) {
		this(url, Config.QUERYTRACKER_SLOTS);
	}

	public QueryTrackerNodeDesc(final String url, final int slots) {
		super(url, slots);
	}

	// methods

	@Override
	public String toString() {
		final StringBuffer value = new StringBuffer();
		value.append("(");
		value.append(url);
		value.append(",");
		value.append(slots);
		value.append(")");
		return value.toString();
	}
}