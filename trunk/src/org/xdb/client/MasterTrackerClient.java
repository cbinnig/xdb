package org.xdb.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.logging.XDBLog;
import org.xdb.server.MasterTrackerServer;
import org.xdb.tracker.QueryTrackerNodeDesc;
import org.xdb.tracker.signals.RegisterSignal;
import org.xdb.utils.MutableInteger;
import org.xdb.utils.Tuple;

/**
 * Client to talk to Master Tracker Server.
 */
public class MasterTrackerClient extends AbstractClient{
	// constructor
	// constructor
	public MasterTrackerClient() {
		logger = XDBLog.getLogger(this.getClass().getName());
		port = Config.MASTERTRACKER_PORT;
		url = Config.MASTERTRACKER_URL;
	}

	public MasterTrackerClient(final String url, final int port) {
		logger = XDBLog.getLogger(this.getClass().getName());
		this.port = port;
		this.url = url;
	}


	public Error registerNode(final ComputeNodeDesc desc) {
		Error err = new Error();
		final RegisterSignal<ComputeNodeDesc> signal = new RegisterSignal<ComputeNodeDesc>(desc);

		try {
			server = new Socket(url, port);
			final ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(MasterTrackerServer.CMD_REGISTER_COMPUTE_NODE);
			out.flush();
			out.writeObject(signal);
			out.flush();

			final ObjectInputStream in = new ObjectInputStream(
					server.getInputStream());
			err = (Error) in.readObject();

			server.close();

		} catch (final Exception e) {
			err = createClientError(e);
		}

		return err;
	}

	/**
	 * Method to register new QueryTrackerNodes at the MasterTrackerServer
	 * @param desc
	 * @return
	 */
	public Error registerNode(final QueryTrackerNodeDesc desc) {
		Error err = new Error();
		final RegisterSignal<QueryTrackerNodeDesc> signal = new RegisterSignal<QueryTrackerNodeDesc>(desc);

		try {
			server = new Socket(url, port);
			final ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(MasterTrackerServer.CMD_REGISTER_QUERYTRACKER_NODE);
			out.flush();
			out.writeObject(signal);
			out.flush();

			final ObjectInputStream in = new ObjectInputStream(
					server.getInputStream());
			err = (Error) in.readObject();

			server.close();

		} catch (final Exception e) {
			err = createClientError(e);
		}

		return err;
	}

	public Error executePlan(final CompilePlan plan) {
		Error err = new Error();

		try {
			server = new Socket(url, Config.COMPUTE_PORT);
			final ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(MasterTrackerServer.CMD_EXECUTE_PLAN);
			out.flush();
			out.writeObject(plan);
			out.flush();

			final ObjectInputStream in = new ObjectInputStream(
					server.getInputStream());
			err = (Error) in.readObject();

			server.close();

		} catch (final Exception e) {
			err = createClientError(e);
		}

		return err;
	}

	@SuppressWarnings("unchecked")
	public Tuple<Map<String, MutableInteger>, Error> requestComputeNodes(final Map<String, MutableInteger> requiredNodes) {
		Error err = new Error();
		Map<String, MutableInteger> computeNodes = null;
		try {
			server = new Socket(url, port);
			final ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());
			final ObjectInputStream in = new ObjectInputStream(server.getInputStream());

			out.writeInt(MasterTrackerServer.CMD_REQUEST_COMPUTE_NODE);
			out.writeObject(requiredNodes);
			out.flush();
			computeNodes = (Map<String, MutableInteger>) in.readObject();
			err = (Error) in.readObject();

			server.close();

		} catch (final Exception e) {
			err = createClientError(e);
		}
		return new Tuple<Map<String, MutableInteger>, Error>(computeNodes, err);
	}

}
