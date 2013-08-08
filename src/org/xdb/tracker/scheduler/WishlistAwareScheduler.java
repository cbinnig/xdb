package org.xdb.tracker.scheduler;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xdb.execute.ComputeNodeDesc;
import org.xdb.metadata.Connection;
import org.xdb.tracker.QueryTrackerPlan;
import org.xdb.tracker.operator.AbstractTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * Resource Scheduler which uses wish-list of operators to schedule operators
 * 
 * @author cbinnig
 * 
 */
public class WishlistAwareScheduler extends AbstractResourceScheduler {
	// compute node URLs per operator that have been requested
	private Map<Identifier, List<String>> wishLocations = new HashMap<Identifier, List<String>>();

	// constructor
	public WishlistAwareScheduler(QueryTrackerPlan plan) {
		super(plan);

		this.type = EnumResourceScheduler.WISHLIST_AWARE;
	}

	@Override
	public int getNumberOfConnections(Identifier opId) {
		if (!this.wishLocations.containsKey(opId))
			return 0;

		return this.wishLocations.get(opId).size();
	}

	@Override
	public Set<String> createComputeNodesWishList() {
		Set<String> wishedConnections = new HashSet<String>();
		for (AbstractTrackerOperator op : this.plan.getTrackerOperators()) {
			List<String> connUrls = new ArrayList<String>();
			for (Connection conn : op.getTrackerOpConnections()) {
				URI uri = conn.getURI();
				connUrls.add(uri.getHost());
			}

			if (connUrls.size() == 0)
				connUrls.add(RANDOM_COMPUTE_NODE);

			this.wishLocations.put(op.getOperatorId(), connUrls);
			wishedConnections.addAll(connUrls);
		}
		return wishedConnections;
	}

	@Override
	public ComputeNodeDesc getComputeNode(Identifier opId) {
		return this.getComputeNode(opId, 0);
	}

	@Override
	public ComputeNodeDesc getComputeNode(final Identifier opId,
			int nodeNumber) {
		// no wished location for operator
		if (!this.wishLocations.containsKey(opId))
			return null;

		List<String> connUrls = this.wishLocations.get(opId);
		if (nodeNumber >= connUrls.size())
			return null;

		// get wished URL
		String wishUrl = this.wishLocations.get(opId).get(nodeNumber);

		// get assigned compute node for url
		return this.assignedComputeNodes.get(wishUrl);
	}
}
