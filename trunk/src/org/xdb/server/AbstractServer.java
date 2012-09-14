package org.xdb.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.logging.XDBLog;

/**
 * Abstract server 
 * @author cbinnig
 *
 */
public abstract class AbstractServer {
	
	protected ServerThread serverThread = null;
	protected int port = -1;
	
	protected ServerSocket serverSocket = null;
	protected Logger logger;
	protected Error err = Error.NO_ERROR;

	//Constructors
	public AbstractServer(){
		this.logger = XDBLog.getLogger(this.getClass().getName());
	}
	
	// getters and setters
	public Error getError() {
		return err;
	}
	
	/**
	 * Starts server thread on local node
	 */
	public synchronized void startServer() {
		serverThread = new ServerThread(this);
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
	public synchronized void stopServer() {
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

			this.serverSocket = new ServerSocket(this.port);

			thread.setRunning();

			while (!thread.isInterrupted()) {
				try {
					Socket client = this.serverSocket.accept();
					handle(client);
				} catch (Exception e) {
					//Nothing to do!
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
