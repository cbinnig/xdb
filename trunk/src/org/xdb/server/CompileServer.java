package org.xdb.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;

import org.xdb.Config;
import org.xdb.client.statement.Statement;
import org.xdb.error.Error;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.statement.AbstractStatement;
import org.xdb.metadata.Catalog;

/**
 * 
 * @author cbinnig
 */
public class CompileServer extends AbstractServer {

	private class Handler extends AbstractHandler {
		// constructor
		public Handler(Socket client) {
			super(client);
			this.logger = CompileServer.this.logger;
		}

		/**
		 * Handle incoming cmd
		 * 
		 * @return
		 * @throws IOException
		 */
		protected Error handle() throws IOException {
			Error err = Error.NO_ERROR;
			ObjectInputStream in = new ObjectInputStream(
					this.client.getInputStream());

			int cmd = in.readInt();
			logger.log(Level.INFO, "CompileServer: Read command from client:" + cmd);
			try {

				switch (cmd) {
				case CMD_EXECUTE:
					Statement clientStmt = (Statement)in.readObject();
					logger.log(Level.INFO, "CompileServer: Received stmt:" + clientStmt.getStmt());
					
					FunSQLCompiler compiler = new FunSQLCompiler();
					AbstractStatement serverStmt = compiler.compile(clientStmt.getStmt());
					err = serverStmt.compile();
					if(err.isError())
						break;
					
					err = serverStmt.execute();
					break;
				case CMD_EXECUTE_CALL:
					//TODO: Execute function call and return result sets to client!
					break;
				default:
					err = createCmdError(cmd);
					break;
				}
			} catch (Exception e) {
				err = createServerError(e);
			}

			return err;
		}
	}

	// constants for commands
	public static final int CMD_EXECUTE = 1;
	public static final int CMD_EXECUTE_CALL = 2;
	
	// constructors
	public CompileServer() {
		super();
		this.port = Config.METADATA_PORT;
		
		this.err = Catalog.load();
		this.logger.log(Level.INFO, "Catalog loaded ... ");
	}

	// methods
	/**
	 * Handle incoming client requests
	 */
	protected void handle(Socket client) {
		Handler handler = new Handler(client);
		handler.start();
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
		startServer(new CompileServer());
	}
}
