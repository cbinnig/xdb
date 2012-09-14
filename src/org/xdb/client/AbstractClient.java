package org.xdb.client;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.error.EnumError;
import org.xdb.error.Error;

public abstract class AbstractClient {
	protected Socket server;
	protected int port;
	protected String url;
	
	// Helpers
	protected Logger logger;

		
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
