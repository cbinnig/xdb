package org.xdb.server;

import java.io.ObjectInputStream;
import java.net.Socket;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;

public class QueryTrackerServer extends AbstractServer {

	private final QueryTrackerNode tracker;

	public QueryTrackerServer() {
		super();

		port = Config.QUERYTRACKER_PORT;
		tracker = new QueryTrackerNode();
	}

	public static final int CMD_EXECUTE_PLAN = 1;

	@Override
	protected void handle(final Socket client) {
		try {
			err = new Error();

			final ObjectInputStream in = new ObjectInputStream(
					client.getInputStream());

			final int cmd = in.readInt();
			switch (cmd) {
			case CMD_EXECUTE_PLAN:
				final QueryTrackerPlan plan = (QueryTrackerPlan) in.readObject();
				tracker.executePlan(plan);
				break;
			}
		} catch (final Exception e) {
			err = createServerError(e);
		}

	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final QueryTrackerServer server = new QueryTrackerServer();
		server.startServer();
	}

}
