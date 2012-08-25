package org.xdb.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.Config;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNode;
import org.xdb.execute.operators.AbstractOperator;
import org.xdb.execute.signals.CloseSignal;
import org.xdb.execute.signals.ReadySignal;
import org.xdb.logging.XDBLog;

/**
 * Server which accepts compute commands and calls handler (separate thread)
 * 
 * @author cbinnig
 * 
 */
public class ComputeServer {
	private static ComputeServerThread serverThread = null;

	
	/**
	 * Handle for compute commands
	 * 
	 * @author cbinnig
	 * 
	 */
	private class Handler extends Thread {
		// Socket to client
		private Socket client;

		// constructor
		public Handler(Socket client) {
			this.client = client;
		}

		@Override
		/**
		 * Run method which reads compute command from client, 
		 * calls handler and sends result back to client
		 */
		public void run() {
			Error err = Error.NO_ERROR;
			// handle request
			try {
				err = handle();
			} catch (IOException e) {
				err = createComputeError(e);
			}

			// log error
			if (err.isError()) {
				logger.log(Level.SEVERE, err.toString());
				this.close();
				return;
			}

			// send response
			try {
				ObjectOutputStream out = new ObjectOutputStream(
						this.client.getOutputStream());
				out.writeObject(err);
			} catch (IOException e) {
				err = createComputeError(e);
			}

			// log error
			if (err.isError()) {
				logger.log(Level.SEVERE, err.toString());
			}

			// close handler
			this.close();
		}

		private void close() {
			// close socket
			try {
				this.client.close();
			} catch (IOException e) {
				createComputeError(e);
			}
		}

		/**
		 * Handle cmd
		 * 
		 * @return
		 * @throws IOException
		 */
		private Error handle() throws IOException {
			Error err = Error.NO_ERROR;
			ObjectInputStream in = new ObjectInputStream(
					this.client.getInputStream());

			logger.log(Level.INFO, "Read command from client");
			int cmd = in.readInt();
			logger.log(Level.INFO, "Read command:" + cmd);
			try {

				switch (cmd) {
				case CMD_PREPARE_OP:
					AbstractOperator op = (AbstractOperator) in.readObject();
					logger.log(Level.INFO, "Reveived op:" + op.getOperatorId());
					err = compute.installOperator(op);
					break;
				case CMD_READY_SIGNAL:
					ReadySignal readSignal = (ReadySignal) in.readObject();
					err = compute.signalOperator(readSignal);
					break;
				case CMD_CLOSE_SIGNAL:
					CloseSignal closeSignal = (CloseSignal) in.readObject();
					err = compute.closeOperator(closeSignal);
					break;
				default:
					err = createCmdError(cmd);
					break;
				}
			} catch (Exception e) {
				err = createComputeError(e);
			}

			return err;
		}
	}

	// constants for commands
	public static final int CMD_PREPARE_OP = 1;
	public static final int CMD_READY_SIGNAL = 2;
	public static final int CMD_CLOSE_SIGNAL = 3;

	// Compute node which executes commands
	private ComputeNode compute;

	// Helpers
	private Logger logger;
	private Error err = Error.NO_ERROR;
	private ServerSocket server = null;

	// constructors
	public ComputeServer() {
		this.logger = XDBLog.getLogger(this.getClass().getName());
		this.compute = new ComputeNode();
	}

	// getters and setters
	public Error getError() {
		return err;
	}

	// methods
	/**
	 * Start server and handle incoming connections
	 */
	public void execute(ComputeServerThread thread) {

		try {
			this.logger.log(Level.INFO, "Server started ... ");

			this.server = new ServerSocket(Config.COMPUTE_PORT);

			thread.setRunning();

			while (!thread.isInterrupted()) {
				try {
					Socket client = this.server.accept();
					Handler handler = new Handler(client);
					handler.start();
				} catch (Exception e) {

				}
			}

			thread.setNotRunning();

		} catch (Exception e) {
			this.err = this.createComputeError(e);

		} finally {
			this.close();
		}

		this.logger.log(Level.INFO, "Server stopped!");
	}

	/**
	 * Closes server
	 */
	public synchronized void close() {
		if (this.server != null) {
			try {
				this.server.close();
				this.server = null;
			} catch (IOException e) {
				this.createComputeError(e);
			}
		}
	}

	/**
	 * Create COMPUTE_ERROR from an exception
	 * 
	 * @param e
	 * @return Error
	 */
	private Error createComputeError(Exception e) {
		e.printStackTrace();

		String[] args = { e.toString() };
		Error err = new Error(EnumError.COMPUTE_ERROR, args);
		logger.log(Level.SEVERE, err.toString());
		return err;
	}

	/**
	 * Create COMPUTE_CMD_INVALID from an exception
	 * 
	 * @param cmd
	 * @return Error
	 */
	private Error createCmdError(Integer cmd) {
		String[] args = { cmd.toString() };
		Error err = new Error(EnumError.COMPUTE_CMD_INVALID, args);
		logger.log(Level.SEVERE, err.toString());
		return err;
	}
	
	/**
	 * Starts compute server on local node
	 */
	public static synchronized void startServer() {
		System.out.print("Start Server ...");
		serverThread = new ComputeServerThread();
		serverThread.start();

		while (!serverThread.isRunning()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			System.out.print(".");
		}
		System.out.println("!");
	}

	/**
	 * Stops compute server if running
	 */
	public static synchronized void stopServer() {

		if (serverThread != null && !serverThread.isInterrupted()) {
			System.out.print("Stop Server ...");
			
			serverThread.interrupt();
			
			while(!serverThread.isInterrupted()){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
				System.out.print(".");
			}
			
			serverThread.close();
			System.out.println("!");
			serverThread = null;
		}
	}
	
	/**
	 * Start server from cmd
	 * @param args
	 */
	public static void main(String[] args){
		startServer();
	}
}
