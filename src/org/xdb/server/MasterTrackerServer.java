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
		protected Error handle(final ObjectOutputStream out) throws IOException {
			Error err = new Error();

			final ObjectInputStream in = new ObjectInputStream(
					client.getInputStream());

			final int cmd = in.readInt();
			logger.log(Level.INFO, "MasterTrackerServer: Read command from client:" + cmd);
			try {

				switch (cmd) {
				case CMD_REGISTER_COMPUTE_NODE:
					final Object read = in.readObject();
					if (read instanceof RegisterSignal<?>) {
						@SuppressWarnings("unchecked")
						final RegisterSignal<ComputeNodeDesc> qtSignal = (RegisterSignal<ComputeNodeDesc>) read;
						err = tracker.registerComputeNode(qtSignal.getDescription());
					} else {
						throw new IllegalArgumentException("Next Object to Read has to be an instance of RegisterSignal<ComputeNodeDesc>");
					}
					break;
				case CMD_EXECUTE_PLAN:
					final CompilePlan plan = (CompilePlan)in.readObject();
					err = tracker.executePlan(plan);
					break;
				case CMD_REGISTER_QUERYTRACKER_NODE:
					final Object qtRead = in.readObject();
					if (qtRead instanceof RegisterSignal<?>) {
						@SuppressWarnings("unchecked")
						final RegisterSignal<QueryTrackerNodeDesc> qtSignal = (RegisterSignal<QueryTrackerNodeDesc>) qtRead;
						err = tracker.registerQueryTrackerNode(qtSignal.getDescription());
					} else {
						throw new IllegalArgumentException("Next Object to Read has to be an instance of RegisterSignal<QueryTrackerNodeDesc>");
					}
					break;
				case CMD_REQUEST_COMPUTE_NODE:
					@SuppressWarnings("unchecked")
					final
					Map<String, MutableInteger> requiredNodes = (Map<String, MutableInteger>) in.readObject();
					out.writeObject(tracker.getComputeSlots(requiredNodes));
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


	//Master tracker node which executes cmds
	private final MasterTrackerNode tracker;

	public MasterTrackerServer() {
		super();

		tracker = new MasterTrackerNode();
		port = Config.MASTERTRACKER_PORT;
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
