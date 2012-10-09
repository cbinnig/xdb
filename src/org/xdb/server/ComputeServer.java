package org.xdb.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNode;
import org.xdb.execute.operators.AbstractOperator;
import org.xdb.execute.signals.CloseSignal;
import org.xdb.execute.signals.ReadySignal;

/**
 * Server which accepts compute commands and calls handler (separate thread)
 * 
 * @author cbinnig
 * 
 */
public class ComputeServer extends AbstractServer {

	/**
	 * Handle for compute commands
	 * 
	 * @author cbinnig
	 * 
	 */
	private class Handler extends AbstractHandler {
		// constructor
		public Handler(final Socket client) {
			super(client);
			logger = ComputeServer.this.logger;
		}

		/**
		 * Handle incoming cmd
		 * 
		 * @return
		 * @throws IOException
		 */
		protected Error handle(final ObjectOutputStream out) throws IOException {
			Error err = new Error();

			final ObjectInputStream in = new ObjectInputStream(
					client.getInputStream());

			final int cmd = in.readInt();
			logger.log(Level.INFO, "ComputeServer: Read command from client:" + cmd);
			try {

				switch (cmd) {
				case CMD_OPEN_OP:
					final AbstractOperator op = (AbstractOperator) in.readObject();
					logger.log(Level.INFO, "Received operator:" + op.getOperatorId());
					err = compute.openOperator(op);
					break;
				case CMD_READY_SIGNAL:
					final ReadySignal readSignal = (ReadySignal) in.readObject();
					logger.log(Level.INFO, "Received ready signal for operator:" + readSignal.getConsumer());
					err = compute.signalOperator(readSignal);
					break;
				case CMD_CLOSE_SIGNAL:
					final CloseSignal closeSignal = (CloseSignal) in.readObject();
					logger.log(Level.INFO, "Received close signal for operator:" + closeSignal.getConsumer());
					err = compute.closeOperator(closeSignal);
					break;
				default:
					err = createCmdError(cmd);
					break;
				}
			} catch (final Exception e) {
				err = createServerError(e);
			}

			return err;
		}
	}

	// constants for commands
	public static final int CMD_OPEN_OP = 1;
	public static final int CMD_READY_SIGNAL = 2;
	public static final int CMD_CLOSE_SIGNAL = 3;

	// Compute node which executes commands
	private final ComputeNode compute;

	// constructors
	public ComputeServer() throws Exception {
		super();

		port = Config.COMPUTE_PORT;
		compute = new ComputeNode();
	}

	// methods
	/**
	 * Handle incoming client requests
	 */
	@Override
	protected void handle(final Socket client) {
		final Handler handler = new Handler(client);
		handler.start();
	}

	/**
	 * Start server from cmd
	 * 
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(final String[] args) throws Exception {
		final ComputeServer server = new ComputeServer();
		server.startServer();
	}
}
