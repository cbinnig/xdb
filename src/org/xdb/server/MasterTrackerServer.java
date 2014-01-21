package org.xdb.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Set;
import java.util.logging.Level;

import org.xdb.Config;
import org.xdb.client.ComputeClient;
import org.xdb.client.QueryTrackerClient;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.tracker.MasterTrackerNode;
import org.xdb.tracker.QueryTrackerNodeDesc;
import org.xdb.tracker.signals.RegisterSignal;
import org.xdb.utils.Tuple;

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
				case CMD_REGISTER_QUERYTRACKER_NODE:
					@SuppressWarnings("unchecked")
					final RegisterSignal<QueryTrackerNodeDesc> registerQTSignal = (RegisterSignal<QueryTrackerNodeDesc>) in
							.readObject();
					err = tracker.registerQueryTrackerNode(registerQTSignal
							.getDescription());
					break;
				case CMD_REQUEST_COMPUTE_NODE:
					@SuppressWarnings("unchecked")
					final Set<String> requiredNodes = (Set<String>) in
							.readObject();
					out.writeObject(tracker.getAvailableComputeNodes(requiredNodes));
					break;
				case CMD_EXECUTE_PLAN:
					final CompilePlan cplan1 = (CompilePlan) in.readObject();
					err = tracker.executePlan(cplan1);
					break;
				case CMD_DOOMDB_GENERATE_PLAN:
					final CompilePlan cplan2 = (CompilePlan) in.readObject();
					Tuple<Error, DoomDBPlan> result = tracker.generateDoomDBQPlan(cplan2);
					out.writeObject(result.getObject2());
					err = result.getObject1();
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

	public static final int CMD_DOOMDB_GENERATE_PLAN = 100;
	
	
	// Master tracker node which executes cmds
	private final MasterTrackerNode tracker;

	public MasterTrackerServer() {
		super();
		this.port = Config.MASTERTRACKER_PORT;
		
		tracker = new MasterTrackerNode();
		tracker.startup();
	}

	@Override
	protected void handle(final Socket client) {
		final Handler handler = new Handler(client);
		handler.start();
	}
	
	public Collection<ComputeNodeDesc> getComputeNodes() {
		return this.tracker.getComputeNodes();
	}
	
	public int getNoComputeServers(){
		return this.tracker.getNoComputeServers();
	}

	//methods
	public synchronized void stopServer() {
		super.stopServer();
		
		//stop all compute servers
		ComputeClient computeClient = new ComputeClient();
		for(ComputeNodeDesc computeNode: this.tracker.getComputeNodes()){
			computeClient.stopComputeServer(computeNode);
		}
		
		//stop all query tracker servers
		for(QueryTrackerClient qTClient: this.tracker.getQueryTrackerClients()){
			qTClient.stopQueryTrackerServer();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final MasterTrackerServer server = new MasterTrackerServer();
		server.startServer();
		
		if(server.getError().isError()){
			server.stopServer();
			System.out.println("Compute server error ("+server.getError()+")");
		}
	}

}
