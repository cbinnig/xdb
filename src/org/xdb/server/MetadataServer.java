package org.xdb.server;

import java.net.Socket;
import java.util.logging.Level;

import org.xdb.error.Error;
import org.xdb.logging.XDBLog;
import org.xdb.metadata.Catalog;

/**
 * 
 * @author cbinnig
 */
public class MetadataServer extends AbstractServer {

	// constructors
	public MetadataServer() {
		this.logger = XDBLog.getLogger(this.getClass().getName());
		this.err = Catalog.load();
		this.logger.log(Level.INFO, "Catalog loaded ... ");
	}

	// methods
	/**
	 * Handle incoming client requests
	 */
	protected void handle(Socket client) {
		//nothing yet
	}

	/**
	 * Deleted catalog content
	 * 
	 * @return
	 */
	public static synchronized Error delete() {
		return Catalog.delete();
	}

	/**
	 * Start server from cmd
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		startServer(new MetadataServer());
	}
}
