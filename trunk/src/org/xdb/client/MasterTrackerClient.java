package org.xdb.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

import org.xdb.Config;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.logging.XDBLog;
import org.xdb.server.MasterTrackerServer;
import org.xdb.tracker.QueryTrackerNodeDesc;
import org.xdb.tracker.signals.RegisterSignal;
import org.xdb.utils.Tuple;

/**
 * Client to talk to Master Tracker Server.
 */
public class MasterTrackerClient extends AbstractClient {
	// constructor
	public MasterTrackerClient() {
		logger = XDBLog.getLogger(this.getClass().getName());
		port = Config.MASTERTRACKER_PORT;
		url = Config.MASTERTRACKER_URL;
	}

	/**
	 * Register a compute node at the master tracker server
	 * 
	 * @param desc
	 * @return
	 */
	public Error registerNode(final ComputeNodeDesc desc) {
		final RegisterSignal<ComputeNodeDesc> signal = new RegisterSignal<ComputeNodeDesc>(
				desc);
		Object[] args = { signal };
		return this.executeCmd(this.url, this.port,
				MasterTrackerServer.CMD_REGISTER_COMPUTE_NODE, args);
	}

	/**
	 * Register new query tracker node at the master tracker server
	 * 
	 * @param desc
	 * @return
	 */
	public Error registerNode(final QueryTrackerNodeDesc desc) {
		final RegisterSignal<QueryTrackerNodeDesc> signal = new RegisterSignal<QueryTrackerNodeDesc>(
				desc);
		Object[] args = { signal };
		return this.executeCmd(this.url, this.port,
				MasterTrackerServer.CMD_REGISTER_QUERYTRACKER_NODE, args);
	}

	/**
	 * Request compute nodes from master tracker for execution
	 * 
	 * @param requiredNodes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Tuple<Map<String, ComputeNodeDesc>, Error> requestComputeNodes(
			final Set<String> wishList) {
		Error err = new Error();
		Map<String, ComputeNodeDesc> computeNodes = null;
		try {
			Socket server = new Socket(url, port);
			final ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());
			final ObjectInputStream in = new ObjectInputStream(
					server.getInputStream());

			out.writeInt(MasterTrackerServer.CMD_REQUEST_COMPUTE_NODE);
			out.writeObject(wishList);
			out.flush();
			computeNodes = (Map<String, ComputeNodeDesc>) in.readObject();
			err = (Error) in.readObject();

			server.close();

		} catch (final Exception e) {
			err = createClientError(url, e);
		}
		return new Tuple<Map<String, ComputeNodeDesc>, Error>(computeNodes, err);
	}

	/**
	 * Execute given compile plan using master tracker
	 * 
	 * @param plan
	 * @return
	 */
	public Error executePlan(final CompilePlan plan) {
		Object[] args = { plan };
		return this.executeCmd(this.url, this.port,
				MasterTrackerServer.CMD_EXECUTE_PLAN, args);
	}

	/**
	 * Generate tracker plan for given compile plan and return DoomDBPlan with
	 * operator IDs
	 */
	public Tuple<Error, DoomDBPlan> generateDoomDBPlan(final CompilePlan plan) {
		Object[] args = { plan };
		Tuple<Error, Object> result = this.executeCmdWithResult(this.url,
				this.port, MasterTrackerServer.CMD_DOOMDB_GENERATE_PLAN, args);

		Tuple<Error, DoomDBPlan> resultDoomDBPlan = new Tuple<Error, DoomDBPlan>(
				result.getObject1(), (DoomDBPlan) result.getObject2());
		return resultDoomDBPlan;
	}
}
