package org.xdb.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.Config;
import org.xdb.error.EnumError;
import org.xdb.error.Error;

/**
 * Abstract server 
 * @author cbinnig
 *
 */
public abstract class AbstractServer {
	
	protected static ServerThread serverThread = null;
	
	protected ServerSocket serverSocket = null;
	protected Logger logger;
	protected Error err = Error.NO_ERROR;

	// getters and setters
	public Error getError() {
		return err;
	}
	
	/**
	 * Starts server thread on local node
	 */
	public static synchronized void startServer(AbstractServer server) {
		serverThread = new ServerThread(server);
		serverThread.start();

		//wait until thread is started
		while (!serverThread.isRunning()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * Stops server thread if running
	 */
	public static synchronized void stopServer() {
		if (serverThread != null && !serverThread.isInterrupted()) {
			
			serverThread.interrupt();
			
			//wait until thread is stopped
			while(!serverThread.isInterrupted()){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
			
			//Close socket
			serverThread.closeSocket();
			
			//Set server = null
			serverThread = null;
		}
	}
	
	/**
	 * Execute server and handle incoming connections
	 */
	protected void executeServer(ServerThread thread) {

		try {
			this.logger.log(Level.INFO, "Server ("+this.getClass().getSimpleName()+") started ... ");

			this.serverSocket = new ServerSocket(Config.COMPUTE_PORT);

			thread.setRunning();

			while (!thread.isInterrupted()) {
				try {
					Socket client = this.serverSocket.accept();
					handle(client);
				} catch (Exception e) {

				}
			}

			thread.setNotRunning();

		} catch (Exception e) {
			this.err = this.createServerError(e);

		} finally {
			this.closeSocket();
		}

		this.logger.log(Level.INFO, "Server ("+this.getClass().getSimpleName()+") stopped!");
	}
	
	/**
	 * Closes server socket
	 */
	protected synchronized void closeSocket() {
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
		e.printStackTrace();

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
