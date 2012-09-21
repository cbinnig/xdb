package org.xdb.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.tracker.MasterTrackerNode;
import org.xdb.tracker.signals.RegisterSignal;

public class MasterTrackerServer extends AbstractServer {

	private class Handler extends AbstractHandler {
		// constructor
		public Handler(Socket client) {
			super(client);
			this.logger = MasterTrackerServer.this.logger;
		}

		/**
		 * Handle incoming cmd
		 * 
		 * @return
		 * @throws IOException
		 */
		protected Error handle() throws IOException {
			Error err = new Error();
			
			ObjectInputStream in = new ObjectInputStream(
					this.client.getInputStream());

			int cmd = in.readInt();
			logger.log(Level.INFO, "MasterTrackerServer: Read command from client:" + cmd);
			try {

				switch (cmd) {
				case CMD_REGISTER_COMPUTE_NODE:
					RegisterSignal signal = (RegisterSignal)in.readObject();
					err = tracker.registerComputeNode(signal.getComputeNodeDesc());
					break;
				case CMD_EXECUTE_PLAN:
					CompilePlan plan = (CompilePlan)in.readObject();
					err = tracker.executePlan(plan);
					break;
				}
			} catch (Exception e) {
				err = createServerError(e);
			}

			return err;
		}
	}
	
	// constants for commands
	public static final int CMD_REGISTER_COMPUTE_NODE = 1;
	public static final int CMD_EXECUTE_PLAN = 2;
		
		
	//Master tracker node which executes cmds
	private MasterTrackerNode tracker;
	
	public MasterTrackerServer() {
		super();
		
		this.tracker = new MasterTrackerNode();
		this.port = Config.MASTERTRACKER_PORT;
	}

	@Override
	protected void handle(Socket client) {
		Handler handler = new Handler(client);
		handler.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MasterTrackerServer server = new MasterTrackerServer();
		server.startServer();
	}

}
