package org.xdb.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.logging.XDBLog;
import org.xdb.logging.EnumXDBComponents;

;

/**
 * Abstract server
 * 
 * @author cbinnig
 * 
 */
public abstract class AbstractServer {

	// CMDs
	public static final int CMD_RESTART_SERVER = -3;
	public static final int CMD_STOP_SERVER = -2;
	public static final int CMD_PING_SERVER = -1;

	// thread
	protected ServerThread serverThread = null;

	// network
	protected ServerSocket serverSocket = null;
	protected int port = -1;

	// helper
	protected Logger logger;
	protected Error err = new Error();

	// Constructors
	public AbstractServer(EnumXDBComponents comp) {
		this.logger = XDBLog.getLogger(comp);
	}

	// getters and setters
	public Error getError() {
		return err;
	}

	public boolean isRunning() {
		if (this.serverThread != null)
			return this.serverThread.isRunning();
		return false;
	}

	/**
	 * Starts server thread on local node
	 */
	public synchronized Error startServer() {
		if (this.err.isError())
			return this.err;

		// add shutdown hook
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				stopServer();
			}
		});

		System.out.print(this.getClass().getSimpleName() + " ...");

		serverThread = new ServerThread(this);
		serverThread.start();

		// wait until thread is started
		while (!serverThread.isRunning()) {
			System.out.print(".");
			
			try {
				if (this.err.isError())
					return this.err;
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
		
		String msg = this.getClass().getSimpleName() + " ... started";
		this.logger.log(Level.INFO, msg);
		
		System.out.println(" started!");

		return this.err;
	}

	/**
	 * Stops server thread if running
	 */
	public synchronized void stopServer() {
		if (serverThread != null && serverThread.isRunning()) {
			
			serverThread.interrupt();

			System.out.print(this.getClass().getSimpleName() + " ...");
			while(!serverThread.isInterrupted()){
				System.out.print(".");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
			
			// close socket
			this.closeSocket();

			// set status
			serverThread.setNotRunning();

			//output message
			String msg = this.getClass().getSimpleName() + " stopped!";
			this.logger.log(Level.INFO, msg);
			System.out.println(" stopped!");
		}
	}

	/**
	 * Execute server and handle incoming connections
	 */
	protected void executeServer() {

		try {
			this.serverSocket = new ServerSocket();
			this.serverSocket.setReuseAddress(true);
			this.serverSocket.bind(new InetSocketAddress(this.port));

			serverThread.setRunning();

			while (!serverThread.isInterrupted()) {
				try {
					Socket clientSocket = this.serverSocket.accept();
					this.handle(clientSocket);
				} catch (Exception e) {
					// Nothing to do
				}
			}

		} catch (IOException e) {
			this.err = this.createServerError(e);
		}
	}

	/**
	 * Closes server socket
	 */
	protected void closeSocket() {
		if (this.serverSocket != null) {
			try {
				this.serverSocket.close();
				this.serverSocket = null;
			} catch (IOException e) {
				this.createServerError(e);
			}
		}
	}

	/**
	 * Handle client requests
	 * 
	 * @param client
	 */
	protected abstract void handle(Socket client);

	/**
	 * Create SERVER_ERROR from an exception
	 * 
	 * @param e
	 * @return Error
	 */
	protected Error createServerError(Exception e) {
		String[] args = { e.toString() };
		Error err = new Error(EnumError.SERVER_ERROR, args);
		logger.log(Level.SEVERE, err.toString());
		return err;
	}

	/**
	 * Create COMPUTE_CMD_INVALID from an exception
	 * 
	 * @param cmd
	 * @return Error
	 */
	protected Error createCmdError(Integer cmd) {
		String[] args = { cmd.toString() };
		Error err = new Error(EnumError.COMPUTE_CMD_INVALID, args);
		logger.log(Level.SEVERE, err.toString());
		return err;
	}
}
