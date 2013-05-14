package org.xdb.client;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
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
public class ComputeClient extends AbstractClient {

	// constructor
	public ComputeClient() {
		this(Config.LOCALHOST, Config.COMPUTE_PORT);
	}

	public ComputeClient(final ComputeNodeDesc slot) {
		this(slot.getUrl(), slot.getPort());
	}

	public ComputeClient(String url, int port) {
		this.logger = XDBLog.getLogger(this.getClass().getName());
		this.port = port;
		this.url = url;
	}

	/**
	 * Installs operator on given node (URL)
	 * 
	 * @param url
	 * @param op
	 * @return
	 */
	public Error openOperator(final ComputeNodeDesc url,
			final AbstractExecuteOperator op) {
		Object[] args = { op };
		return this.executeCmd(url.getUrl(), url.getPort(),
				ComputeServer.CMD_OPEN_OP, args);
	}

	/**
	 * Send ready signal to operator on node from source operator
	 * 
	 * @param sourceOpId
	 * @param url
	 * @param destOpId
	 * @return
	 */
	public Error executeOperator(final Identifier sourceOpId,
			final ComputeNodeDesc url, final Identifier destOpId) {
		final ReadySignal signal = new ReadySignal(sourceOpId, destOpId);
		Object[] args = { signal };
		return this.executeCmd(url.getUrl(), url.getPort(),
				ComputeServer.CMD_READY_SIGNAL, args);
	}

	/**
	 * Send ready signal to operator on node from source operator
	 * 
	 * @param sourceOp
	 * @param dest
	 * @return
	 */
	public Error executeOperator(final Identifier sourceOpId,
			final OperatorDesc dest) {
		return this.executeOperator(sourceOpId, dest.getComputeSlot(),
				dest.getOperatorID());
	}

	/**
	 * Send ready signal to a leaf operator on node w/o a source operator
	 * 
	 * @param node
	 * @param op
	 * @return
	 */
	public Error executeOperator(final OperatorDesc dest) {
		return this.executeOperator(Config.COMPUTE_NOOP_ID,
				dest.getComputeSlot(), dest.getOperatorID());
	}

	/**
	 * Send ready signal to a leaf operator on node w/o a source operator
	 * 
	 * @param url
	 * @param destOpId
	 * @return
	 */
	public Error executeOperator(final ComputeNodeDesc url,
			final Identifier destOpId) {
		return this.executeOperator(Config.COMPUTE_NOOP_ID, url, destOpId);
	}

	/**
	 * Closes operator on node
	 * 
	 * @param url
	 * @param op
	 * @return
	 */
	public Error closeOperator(final ComputeNodeDesc url,
			final Identifier operatorId) {
		final CloseSignal signal = new CloseSignal(operatorId);
		Object[] args = { signal };
		return this.executeCmd(url.getUrl(), url.getPort(),
				ComputeServer.CMD_CLOSE_SIGNAL, args);
	}

	/**
	 * Close operator on node
	 * 
	 * @param op
	 * @param dest
	 * @return
	 */
	public Error closeOperator(final OperatorDesc dest) {
		return this.closeOperator(dest.getComputeSlot(), dest.getOperatorID());
	}
	
	/**
	 * Stop compute server
	 * 
	 * @param url
	 * @param operatorId
	 * @return
	 */
	public Error stopComputeServer(final ComputeNodeDesc url) {
		Object[] args = {};
		return this.executeCmd(url.getUrl(), url.getPort(),
				ComputeServer.CMD_STOP_SERVER, args);
	}
	
	/**
	 * Ping compute server
	 * @param url
	 * @return
	 */
	public Error pingComputeServer(final ComputeNodeDesc url) {
		Object[] args = {};
		return this.executeCmd(url.getUrl(), url.getPort(),
				ComputeServer.CMD_PING_SERVER, args);
	}
}
