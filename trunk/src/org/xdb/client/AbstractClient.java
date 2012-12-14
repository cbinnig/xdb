package org.xdb.client;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.error.EnumError;
import org.xdb.error.Error;

/**
 * Abstract client implementation to talk to server components via sockets
 * 
 * @author cbinnig
 * 
 */
public abstract class AbstractClient {
	// Server Socket
	protected Socket server;

	// Port for Socket
	protected int port;

	// URL of server
	protected String url;

	// Helpers
	protected Logger logger;

	// getter and setter
	public String getUrl() {
		return url;
	}

	/**
	 * Create error
	 * 
	 * @param e
	 * @return
	 */
	protected Error createClientError(Exception e) {
		e.printStackTrace();

		String[] args = { e.toString() };
		Error err = new Error(EnumError.CLIENT_ERROR, args);
		logger.log(Level.SEVERE, err.toString());
		return err;
	}
}
