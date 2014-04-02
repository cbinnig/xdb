package org.xdb.client;

import java.util.logging.Level;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.execute.operators.EnumOperatorStatus;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.execute.signals.CloseSignal;
import org.xdb.execute.signals.KillSignal;
import org.xdb.execute.signals.ReadySignal;
import org.xdb.execute.signals.RestartSignal;
import org.xdb.logging.EnumXDBComponents;
import org.xdb.server.ComputeServer;
import org.xdb.utils.Identifier;
import org.xdb.utils.Tuple;

/**
 * Client to talk to Compute Server.
 */
public class ComputeClient extends AbstractClient {

	// constructor
	public ComputeClient() {
		this(Config.LOCALHOST, Config.COMPUTE_PORT);
	}

	public ComputeClient(final ComputeNodeDesc node) {
		this(node.getUrl(), node.getPort());
	}

	public ComputeClient(String url, int port) {
		super(url, port, EnumXDBComponents.COMPUTE_SERVER);
	}

	/**
	 * Installs operator on given node (URL)
	 * 
	 * @param url
	 * @param op
	 * @return
	 */
	public Tuple<Error, EnumOperatorStatus> openOperator(final ComputeNodeDesc url,
			final AbstractExecuteOperator op) {
		Object[] args = { op };
		Tuple<Error, Object> result =  this.executeCmdWithResultIgnoreCommErr(url.getUrl(), url.getPort(),
				ComputeServer.CMD_OPEN_OP, args);
		
		Tuple<Error, EnumOperatorStatus> resultStatus = new Tuple<Error, EnumOperatorStatus>(result.getObject1(),
				(EnumOperatorStatus)result.getObject2());
		
		//set status due to communication error
		if(resultStatus.getObject2() == null)
			resultStatus.setObject2(EnumOperatorStatus.ABORTED);
		
		return resultStatus;
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
		
		this.logger.log(Level.INFO, "Signalling "+destOpId+" from "+ sourceOpId);
		
		final ReadySignal signal = new ReadySignal(sourceOpId, destOpId);
		Object[] args = { signal };
		return this.executeCmdIgnoreCommErr(url.getUrl(), url.getPort(),
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
		return this.executeOperator(sourceOpId, dest.getComputeNode(),
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
				dest.getComputeNode(), dest.getOperatorID());
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
			final AbstractExecuteOperator execOp) {
		final CloseSignal signal = new CloseSignal(execOp);
		Object[] args = { signal };
		return this.executeCmdIgnoreCommErr(url.getUrl(), url.getPort(),
				ComputeServer.CMD_CLOSE_SIGNAL, args);
	}

	/**
	 * Close operator on node
	 * 
	 * @param op
	 * @param dest
	 * @return
	 */
	public Error closeOperator(final OperatorDesc dest, final AbstractExecuteOperator execOp) {
		return this.closeOperator(dest.getComputeNode(), execOp);
	}

	/**
	 * Stop compute server
	 * 
	 * @param url
	 * @param operatorId
	 * @return
	 */
	public Error stopComputeServer(final ComputeNodeDesc compNode) {
		Object[] args = {};
		return this.executeCmdIgnoreCommErr(compNode.getUrl(), compNode.getPort(),
				ComputeServer.CMD_STOP_SERVER, args);
	}

	/**
	 * Ping compute server
	 * 
	 * @param url
	 * @return
	 */
	public Error pingComputeServer(final ComputeNodeDesc url) {
		Object[] args = {};
		return this.executeCmd(url.getUrl(), url.getPort(),
				ComputeServer.CMD_PING_SERVER, args);
	}

	/**
	 * Kill failed operator.
	 * 
	 * @param url
	 * @param failedExecOpId
	 * @return
	 */
	public Error killFailedOperator(ComputeNodeDesc url,
			Identifier failedExecOpId) {
		final KillSignal killSignal = new KillSignal(failedExecOpId);
		Object[] args = { killSignal };
		return this.executeCmdIgnoreCommErr(url.getUrl(), url.getPort(),
				ComputeServer.CMD_KILL_SIGNAL, args);
	} 
	
	/**
	 * Restart Compute Node  
	 * 
	 * @param url
	 * @param mttr: mean time to repair 
	 * @return
	 */
	public Error restartComputeNode(ComputeNodeDesc url, int mttr) {
		final RestartSignal restartSignal = new RestartSignal(); 
		restartSignal.setTimeToRepair(mttr);
        Object[] args = { restartSignal };
		return this.executeCmdIgnoreCommErr(url.getUrl(), url.getPort(),
				ComputeServer.CMD_RESTART_SERVER, args);
	}
}
