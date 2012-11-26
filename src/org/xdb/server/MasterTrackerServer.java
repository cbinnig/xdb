package org.xdb.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Level;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.tracker.MasterTrackerNode;
import org.xdb.tracker.QueryTrackerNodeDesc;
import org.xdb.tracker.signals.RegisterSignal;
import org.xdb.utils.MutableInteger;

public class MasterTrackerServer extends AbstractServer {

	private class Handler extends AbstractHandler {
		// constructor
		public Handler(final Socket client) {
			super(client);
			logger = MasterTrackerServer.this.logger;
		}

		/**
		 * Handle incoming cmd
		 * 
		 * @return
		 * @throws IOException
		 */
		@Override
		protected Error handle(final ObjectOutputStream out,
				final ObjectInputStream in) throws IOException {
			Error err = new Error();

			final int cmd = in.readInt();
			logger.log(Level.INFO,
					"MasterTrackerServer: Read command from client:" + cmd);
			try {

				switch (cmd) {
				case CMD_REGISTER_COMPUTE_NODE:
					@SuppressWarnings("unchecked")
					final RegisterSignal<ComputeNodeDesc> registerCNSignal = (RegisterSignal<ComputeNodeDesc>) in
							.readObject();
					err = tracker.registerComputeNode(registerCNSignal
							.getDescription());

					break;
				case CMD_EXECUTE_PLAN:
					final CompilePlan plan = (CompilePlan) in.readObject();
					err = tracker.executePlan(plan);
					break;
				case CMD_REGISTER_QUERYTRACKER_NODE:
					@SuppressWarnings("unchecked")
					final RegisterSignal<QueryTrackerNodeDesc> registerQTSignal = (RegisterSignal<QueryTrackerNodeDesc>) in
							.readObject();
					err = tracker.registerQueryTrackerNode(registerQTSignal
							.getDescription());

					break;
				case CMD_REQUEST_COMPUTE_NODE:
					@SuppressWarnings("unchecked")
					final Map<String, MutableInteger> requiredNodes = (Map<String, MutableInteger>) in
							.readObject();
					out.writeObject(tracker.getComputeSlots(requiredNodes));
					break;
				case CMD_NOTICE_FREE_COMPUTE_NODES:
					@SuppressWarnings("unchecked")
					final Map<String, MutableInteger> freeNodes = (Map<String, MutableInteger>) in
							.readObject();
					tracker.addFreeNodes(freeNodes);
					break;
				}
			} catch (final Exception e) {
				err = createServerError(e);
			}

			return err;
		}
	}

	// constants for commands
	public static final int CMD_REGISTER_COMPUTE_NODE = 1;
	public static final int CMD_EXECUTE_PLAN = 2;
	public static final int CMD_REGISTER_QUERYTRACKER_NODE = 3;
	public static final int CMD_REQUEST_COMPUTE_NODE = 4;
	public static final int CMD_NOTICE_FREE_COMPUTE_NODES = 5;

	// Master tracker node which executes cmds
	private final MasterTrackerNode tracker;

	public MasterTrackerServer() {
		super();

		tracker = new MasterTrackerNode();
		this.port = Config.MASTERTRACKER_PORT;
	}

	@Override
	protected void handle(final Socket client) {
		final Handler handler = new Handler(client);
		handler.start();
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final MasterTrackerServer server = new MasterTrackerServer();
		server.startServer();
	}

}
