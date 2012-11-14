package org.xdb.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.execute.operators.AbstractOperator;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;

public class QueryTrackerServer extends AbstractServer {

	private final QueryTrackerNode tracker;

	public QueryTrackerServer() {
		super();

		port = Config.QUERYTRACKER_PORT;
		tracker = new QueryTrackerNode();
	}


	private class Handler extends AbstractHandler {
		// constructor
		public Handler(final Socket client) {
			super(client);
			logger = QueryTrackerServer.this.logger;
		}

		/**
		 * Handle incoming cmd
		 * 
		 * @return
		 * @throws IOException
		 */
		@Override
		protected Error handle(final ObjectOutputStream out, final ObjectInputStream in) throws IOException {
			Error err = new Error();

			final int cmd = in.readInt();
			try {
				switch (cmd) {
				case CMD_EXECUTE_PLAN:
					final QueryTrackerPlan plan = (QueryTrackerPlan) in.readObject();
					tracker.executePlan(plan);
					break;
				case CMD_OPERATOR_READY:
					final AbstractOperator op = (AbstractOperator) in.readObject();
					err = tracker.operatorReady(op);
				}
				out.writeObject(err);
				out.flush();
			} catch (final Exception e) {
				err = createServerError(e);
			}

			return err;
		}
	}

	public static final int CMD_EXECUTE_PLAN = 1;
	public static final int CMD_OPERATOR_READY = 2;

	@Override
	protected void handle(final Socket client) {
		final Handler handler = new Handler(client);
		handler.start();
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final QueryTrackerServer server = new QueryTrackerServer();
		server.startServer();
	}

}
