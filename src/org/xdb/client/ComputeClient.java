package org.xdb.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.execute.operators.AbstractExecuteOperator;
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

	/**
	 * Installs operator on node
	 * 
	 * @param url
	 * @param op
	 * @return
	 */
	public Error openOperator(final String url, final AbstractExecuteOperator op) {
		Error err = new Error();

		try {
			server = new Socket(url, this.port);
			final ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(ComputeServer.CMD_OPEN_OP);
			out.flush();
			out.writeObject(op);
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
	 * Send ready signal to operator on node from source operator
	 * 
	 * @param sourceOpId
	 * @param url
	 * @param destOpId
	 * @return
	 */
	public Error executeOperator(final Identifier sourceOpId, final String url,
			final Identifier destOpId) {
		Error err = new Error();


		try {
			server = new Socket(url, this.port);
			final ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());
			final ReadySignal signal = new ReadySignal(sourceOpId, destOpId);

			out.writeInt(ComputeServer.CMD_READY_SIGNAL);
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
	 * Send ready signal to operator on node from source operator
	 * 
	 * @param sourceOp
	 * @param dest
	 * @return
	 */
	public Error executeOperator(final Identifier sourceOpId, final OperatorDesc dest) {
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
	public Error executeOperator(final OperatorDesc dest) {
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
	public Error executeOperator(final String url, final Identifier destOpId) {
		return this.executeOperator(Config.COMPUTE_NOOP_ID, url, destOpId);
	}

	/**
	 * Closes operator on node
	 * 
	 * @param url
	 * @param op
	 * @return
	 */
	public Error closeOperator(final String url, final Identifier operatorId) {
		Error err = new Error();
		final CloseSignal signal = new CloseSignal(operatorId);

		try {
			server = new Socket(url, this.port);
			final ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(ComputeServer.CMD_CLOSE_SIGNAL);
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
	 * Close operator on node
	 * 
	 * @param op
	 * @param dest
	 * @return
	 */
	public Error closeOperator(final OperatorDesc dest) {
		return this.closeOperator(dest.getOperatorNode(), dest.getOperatorID());
	}
}
