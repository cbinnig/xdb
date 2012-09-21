package org.xdb.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.execute.signals.CloseSignal;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.logging.XDBLog;
import org.xdb.server.ComputeServer;
import org.xdb.server.MasterTrackerServer;
import org.xdb.tracker.signals.RegisterSignal;
import org.xdb.utils.Identifier;

/**
 * Client to talk to Master Tracker Server.
 */
public class MasterTrackerClient extends AbstractClient{
	// constructor
	// constructor
		public MasterTrackerClient() {
			this.logger = XDBLog.getLogger(this.getClass().getName());
			this.port = Config.MASTERTRACKER_PORT;
			this.url = Config.MASTERTRACKER_URL;
		}
		
		public MasterTrackerClient(String url, int port) {
			this.logger = XDBLog.getLogger(this.getClass().getName());
			this.port = port;
			this.url = url;
		}

	
	public Error registerNode(ComputeNodeDesc desc) {
		Error err = new Error();
		RegisterSignal signal = new RegisterSignal(desc);
		
		try {
			this.server = new Socket(this.url, this.port);
			ObjectOutputStream out = new ObjectOutputStream(
					this.server.getOutputStream());

			out.writeInt(MasterTrackerServer.CMD_REGISTER_COMPUTE_NODE);
			out.flush();
			out.writeObject(signal);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(
					this.server.getInputStream());
			err = (Error) in.readObject();

			this.server.close();

		} catch (Exception e) {
			err = createClientError(e);
		}

		return err;
	}
	
	public Error executePlan(CompilePlan plan) {
		Error err = new Error();

		try {
			this.server = new Socket(url, Config.COMPUTE_PORT);
			ObjectOutputStream out = new ObjectOutputStream(
					this.server.getOutputStream());

			out.writeInt(MasterTrackerServer.CMD_EXECUTE_PLAN);
			out.flush();
			out.writeObject(plan);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(
					this.server.getInputStream());
			err = (Error) in.readObject();

			this.server.close();

		} catch (Exception e) {
			err = createClientError(e);
		}

		return err;
	}
}
