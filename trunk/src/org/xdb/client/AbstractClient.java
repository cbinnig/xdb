package org.xdb.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.utils.Tuple;

/**
 * Abstract client implementation to talk to server components via sockets
 * 
 * @author cbinnig
 * 
 */
public abstract class AbstractClient {

	// Port for Socket
	private int port;

	// URL of server
	private String url;

	// Helpers
	protected Logger logger;

	public AbstractClient() {
		super();
	}

	public AbstractClient(String url, int port) {
		super();
		this.port = port;
		this.url = url;
	}

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
	protected Error createClientError(String url, Exception e) {
		String[] args = { "Could not connect to server " + url + ": "
				+ e.toString() };
		Error err = new Error(EnumError.CLIENT_ERROR, args);
		logger.log(Level.SEVERE, err.toString());
		return err;
	}

	/**
	 * Execute a given command on server 
	 * 
	 * @param cmd
	 * @param args
	 * @return
	 */
	public Error executeCmd(int cmd, Object[] args) {
		return this.executeCmd(this.url, this.port, cmd, args);
	}

	/**
	 * Execute given command with arguments on server (with given URL and port)
	 * 
	 * @param url
	 * @param port
	 * @param cmd
	 * @param args
	 * @return
	 */
	protected Error executeCmd(final String url, final int port, int cmd,
			Object[] args) {
		Error err = new Error();

		try {
			Socket server = new Socket(url, port);
			final ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(cmd);
			out.flush();
			for (Object arg : args) {
				out.writeObject(arg);
				out.flush();
			}

			final ObjectInputStream in = new ObjectInputStream(
					server.getInputStream());
			err = (Error) in.readObject();

			server.close();

		} catch (final Exception e) {
			err = createClientError(url, e);
		}

		return err;
	}

	/**
	 * Execute given command with arguments on server and return result
	 * 
	 * @param cmd
	 * @param args
	 * @return
	 */
	public Tuple<Error, Object> executeCmdWithResult(int cmd, Object[] args) {
		return this.executeCmdWithResult(this.url, this.port, cmd, args);
	}

	/**
	 * Execute given command with arguments on server (with given URL and port)
	 * and return result
	 * 
	 * @param url
	 * @param port
	 * @param cmd
	 * @param args
	 * @return
	 */
	protected Tuple<Error, Object> executeCmdWithResult(final String url,
			final int port, int cmd, Object[] args) {
		Error err = new Error();
		Object obj = null;

		try {
			Socket server = new Socket(url, port);
			final ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(cmd);
			out.flush();
			for (Object arg : args) {
				out.writeObject(arg);
				out.flush();
			}

			final ObjectInputStream in = new ObjectInputStream(
					server.getInputStream());
			obj = in.readObject();
			err = (Error) in.readObject();

			server.close();

		} catch (final Exception e) {
			err = createClientError(url, e);
		}

		return new Tuple<Error, Object>(err, obj);
	}
	
	/**
	 * Execute given command with arguments on server (with given URL and port)
	 * 
	 * @param url
	 * @param port
	 * @param cmd
	 * @param args
	 * @return
	 */
	protected Error executeCmdIgnoreCommErr(final String url, final int port, int cmd,
			Object[] args) {
		Error err = new Error();

		try {
			Socket server = new Socket(url, port);
			final ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(cmd);
			out.flush();
			for (Object arg : args) {
				out.writeObject(arg);
				out.flush();
			}

			final ObjectInputStream in = new ObjectInputStream(
					server.getInputStream());
			err = (Error) in.readObject();

			server.close();

		} catch (final Exception e) {
			//Ignore communication errors
		}

		return err;
	}
	
	/**
	 * Execute given command with arguments on server (with given URL and port)
	 */
	protected Tuple<Error, Object> executeCmdWithResultIgnoreCommErr(final String url,
			final int port, int cmd, Object[] args) {
		Error err = new Error();
		Object obj = null;

		try {
			Socket server = new Socket(url, port);
			final ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(cmd);
			out.flush();
			for (Object arg : args) {
				out.writeObject(arg);
				out.flush();
			}

			final ObjectInputStream in = new ObjectInputStream(
					server.getInputStream());
			obj = in.readObject();
			err = (Error) in.readObject();

			server.close();

		} catch (final Exception e) {
			
		}

		return new Tuple<Error, Object>(err, obj);
	}
}
