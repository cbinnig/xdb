package org.xdb.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.execute.operators.AbstractOperator;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.execute.signals.CloseSignal;
import org.xdb.execute.signals.ReadySignal;
import org.xdb.logging.XDBLog;
import org.xdb.server.ComputeServer;
import org.xdb.utils.Identifier;

/**
 * Client to talk to Compute Server.
 */
public class ComputeClient extends AbstractClient{
	// constructor
	public ComputeClient() {
		this.logger = XDBLog.getLogger(this.getClass().getName());
		this.port = Config.COMPUTE_PORT;
		this.url = Config.COMPUTE_URL;
	}
	
	public ComputeClient(String url, int port) {
		this.logger = XDBLog.getLogger(this.getClass().getName());
		this.port = port;
		this.url = url;
	}

	/**
	 * Installs operator on node
	 * 
	 * @param url
	 * @param op
	 * @return
	 */
	public Error openOperator(String url, AbstractOperator op) {
		Error err = new Error();
		
		try {
			this.server = new Socket(url, this.port);
			ObjectOutputStream out = new ObjectOutputStream(
					this.server.getOutputStream());

			out.writeInt(ComputeServer.CMD_OPEN_OP);
			out.flush();
			out.writeObject(op);
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

	/**
	 * Send ready signal to operator on node from source operator
	 * 
	 * @param sourceOpId
	 * @param url
	 * @param destOpId
	 * @return
	 */
	public Error executeOperator(Identifier sourceOpId, String url,
			Identifier destOpId) {
		Error err = new Error();
		
		try {
			this.server = new Socket(url, Config.COMPUTE_PORT);
			ObjectOutputStream out = new ObjectOutputStream(
					this.server.getOutputStream());
			ReadySignal signal = new ReadySignal(sourceOpId, destOpId);

			out.writeInt(ComputeServer.CMD_READY_SIGNAL);
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

	/**
	 * Send ready signal to operator on node from source operator
	 * 
	 * @param sourceOp
	 * @param dest
	 * @return
	 */
	public Error executeOperator(Identifier sourceOpId, OperatorDesc dest) {
		return this.executeOperator(sourceOpId, dest.getOperatorNode(),
				dest.getOperatorID());
	}

	/**
	 * Send ready signal to operator on node w/o a source operator
	 * 
	 * @param node
	 * @param op
	 * @return
	 */
	public Error executeOperator(OperatorDesc dest) {
		return this.executeOperator(Config.COMPUTE_NOOP_ID,
				dest.getOperatorNode(), dest.getOperatorID());
	}

	/**
	 * Send ready signal to operator on node w/o a source operator
	 * 
	 * @param url
	 * @param destOpId
	 * @return
	 */
	public Error executeOperator(String url, Identifier destOpId) {
		return this.executeOperator(Config.COMPUTE_NOOP_ID, url, destOpId);
	}

	/**
	 * Closes operator on node
	 * 
	 * @param url
	 * @param op
	 * @return
	 */
	public Error closeOperator(String url, Identifier operatorId) {
		Error err = new Error();
		CloseSignal signal = new CloseSignal(operatorId);

		try {
			this.server = new Socket(url, Config.COMPUTE_PORT);
			ObjectOutputStream out = new ObjectOutputStream(
					this.server.getOutputStream());

			out.writeInt(ComputeServer.CMD_CLOSE_SIGNAL);
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

	/**
	 * Close operator on node
	 * 
	 * @param op
	 * @param dest
	 * @return
	 */
	public Error closeOperator(OperatorDesc dest) {
		return this.closeOperator(dest.getOperatorNode(), dest.getOperatorID());
	}
}
