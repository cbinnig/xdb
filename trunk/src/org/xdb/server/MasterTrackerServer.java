package org.xdb.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.tracker.MasterTrackerNode;
import org.xdb.tracker.signals.QueryTrackerRegisterSignal;
import org.xdb.tracker.signals.RegisterSignal;

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
		protected Error handle() throws IOException {
			Error err = new Error();

			final ObjectInputStream in = new ObjectInputStream(
					client.getInputStream());

			final int cmd = in.readInt();
			logger.log(Level.INFO, "MasterTrackerServer: Read command from client:" + cmd);
			try {

				switch (cmd) {
				case CMD_REGISTER_COMPUTE_NODE:
					final RegisterSignal signal = (RegisterSignal)in.readObject();
					err = tracker.registerComputeNode(signal.getComputeNodeDesc());
					break;
				case CMD_EXECUTE_PLAN:
					final CompilePlan plan = (CompilePlan)in.readObject();
					err = tracker.executePlan(plan);
					break;
				case CMD_REGISTER_QUERYTRACKER_NODE:
					final QueryTrackerRegisterSignal qtSignal = (QueryTrackerRegisterSignal) in.readObject();
					err = tracker.registerQueryTrackerNode(qtSignal.getDesc());
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
