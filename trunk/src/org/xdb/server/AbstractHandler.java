package org.xdb.server;

import java.io.IOException;
import java.io.ObjectInputStream;
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
	public AbstractHandler(final Socket client) {
		this.client = client;
	}

	@Override
	/**
	 * Run method which reads compute command from client, 
	 * calls handler and sends result back to client
	 */
	public void run() {
		Error err = new Error();
		// handle request
		try {
			final ObjectOutputStream out = new ObjectOutputStream(
					client.getOutputStream());
			final ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			err = handle(out, in);
			// send response
			out.writeObject(err);
		} catch (final Exception e) {
			err = createServerError(e);
		}

		// log error
		if (err.isError()) {
			logger.log(Level.SEVERE, err.toString());
		}

		// close handler
		close();
	}

	private void close() {
		// close socket
		try {
			client.close();
		} catch (final IOException e) {
			createServerError(e);
		}
	}

	/**
	 * Create SERVER_ERROR from an exception
	 * 
	 * @param e
	 * @return Error
	 */
	protected Error createServerError(final Exception e) {
		e.printStackTrace();

		final String[] args = { e.toString() };
		final Error err = new Error(EnumError.SERVER_ERROR, args);
		logger.log(Level.SEVERE, err.toString());
		return err;
	}

	/**
	 * Handle cmd
	 * 
	 * @return
	 * @throws IOException
	 */
	protected abstract Error handle(ObjectOutputStream out, ObjectInputStream in) throws IOException;
}