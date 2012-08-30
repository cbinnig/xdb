package org.xdb.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.Config;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.execute.operators.AbstractOperator;
import org.xdb.execute.signals.CloseSignal;
import org.xdb.execute.signals.ReadySignal;
import org.xdb.logging.XDBLog;
import org.xdb.server.ComputeServer;
import org.xdb.utils.Identifier;

/**
 * Client to talk to Compute Server.
 */
public class ComputeClient {
	// Helpers
	private Logger logger;

	// constructor
	public ComputeClient() {
		this.logger = XDBLog.getLogger(this.getClass().getName());
	}

	/**
	 * Installs operator on node
	 * 
	 * @param node
	 * @param op
	 * @return
	 */
	public Error prepareOperator(String node, AbstractOperator op) {
		Error err = Error.NO_ERROR;

		try {
			Socket server = new Socket(node, Config.COMPUTE_PORT);
			ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(ComputeServer.CMD_PREPARE_OP);
			out.flush();
			out.writeObject(op);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(
					server.getInputStream());
			err = (Error) in.readObject();

			server.close();

		} catch (Exception e) {
			err = createClientError(e);
		}

		return err;
	}

	/**
	 * Send ready signal to operator on node from source operator
	 * 
	 * @param sourceOpId
	 * @param destNode
	 * @param destOpId
	 * @return
	 */
	public Error executeOperator(Identifier sourceOpId, String destNode,
			Identifier destOpId) {
		Error err = Error.NO_ERROR;

		try {
			Socket server = new Socket(destNode, Config.COMPUTE_PORT);
			ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());
			ReadySignal signal = new ReadySignal(sourceOpId, destOpId);

			out.writeInt(ComputeServer.CMD_READY_SIGNAL);
			out.flush();
			out.writeObject(signal);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(
					server.getInputStream());
			err = (Error) in.readObject();

			server.close();
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
	 * @param destNode
	 * @param destOpId
	 * @return
	 */
	public Error executeOperator(String destNode, Identifier destOpId) {
		return this.executeOperator(Config.COMPUTE_NOOP_ID, destNode, destOpId);
	}

	/**
	 * Closes operator on node
	 * 
	 * @param node
	 * @param op
	 * @return
	 */
	public Error closeOperator(String node, Identifier operatorId) {
		Error err = Error.NO_ERROR;
		CloseSignal signal = new CloseSignal(operatorId);

		try {
			Socket server = new Socket(node, Config.COMPUTE_PORT);
			ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(ComputeServer.CMD_CLOSE_SIGNAL);
			out.flush();
			out.writeObject(signal);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(
					server.getInputStream());
			err = (Error) in.readObject();

			server.close();

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

	/**
	 * Create error
	 * 
	 * @param e
	 * @return
	 */
	private Error createClientError(Exception e) {
		e.printStackTrace();

		String[] args = { e.toString() };
		Error err = new Error(EnumError.CLIENT_ERROR, args);
		logger.log(Level.SEVERE, err.toString());
		return err;
	}
}
