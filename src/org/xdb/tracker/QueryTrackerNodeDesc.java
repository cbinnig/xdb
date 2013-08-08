package org.xdb.tracker;

import org.xdb.server.AbstractNodeDesc;

/**
 * Describes the properties of a QueryTrackerNode (i.e., URL)
 * 
 * @author cbinnig
 * 
 */
public class QueryTrackerNodeDesc extends AbstractNodeDesc {

	private static final long serialVersionUID = -3489640841708710463L;
	
	// constructors
	public QueryTrackerNodeDesc(final String url) {
		super(url);
	}

	// methods

	@Override
	public String toString() {
		final StringBuffer value = new StringBuffer();
		value.append("(");
		value.append(url);
		value.append(")");
		return value.toString();
	}
}