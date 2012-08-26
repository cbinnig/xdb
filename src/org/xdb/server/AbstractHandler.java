package org.xdb.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.error.EnumError;
import org.xdb.error.Error;

/**
 * Handler for client requests
 * @author cbinnig
 *
 */
public abstract class AbstractHandler extends Thread {
	protected Socket client;
	protected Logger logger;

	// constructor
	public AbstractHandler(Socket client) {
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
			err = createServerError(e);
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
			err = createServerError(e);
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
			createServerError(e);
		}
	}

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
	 * Handle cmd
	 * 
	 * @return
	 * @throws IOException
	 */
	protected abstract Error handle() throws IOException;
}