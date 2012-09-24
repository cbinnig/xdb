package org.xdb.tracker.signals;

import java.io.Serializable;

import org.xdb.tracker.QueryTrackerNodeDesc;

/**
 * Signal sent from QueryTrackerNode to MasterTrackerServer when registering/starting
 * 
 * @author Timo Jacobs
 *
 */
public class QueryTrackerRegisterSignal implements Serializable {


	private static final long serialVersionUID = -3923172229834215945L;
	private final QueryTrackerNodeDesc querytrackerNodeDesc;

	public QueryTrackerRegisterSignal(final String url, final Integer slots) {
		querytrackerNodeDesc = new QueryTrackerNodeDesc(url, slots);
	}

	public QueryTrackerRegisterSignal(final QueryTrackerNodeDesc computeNodeDesc) {
		querytrackerNodeDesc = computeNodeDesc;
	}

	public QueryTrackerNodeDesc getDesc() {
		return querytrackerNodeDesc;
	}
}
