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
	}

	/**
	 * Installs operator on node
	 * 
	 * @param node
	 * @param op
	 * @return
	 */
	public Error openOperator(String node, AbstractOperator op) {
		Error err = Error.NO_ERROR;

		try {
			this.server = new Socket(node, Config.COMPUTE_PORT);
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
	 * @param destNode
	 * @param destOpId
	 * @return
	 */
	public Error executeOperator(Identifier sourceOpId, String destNode,
			Identifier destOpId) {
		Error err = Error.NO_ERROR;

		try {
			this.server = new Socket(destNode, Config.COMPUTE_PORT);
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
			this.server = new Socket(node, Config.COMPUTE_PORT);
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
