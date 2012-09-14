package org.xdb.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;

import org.xdb.Config;
import org.xdb.client.statement.ClientStmt;
import org.xdb.error.Error;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.statement.AbstractServerStmt;
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
				case CMD_EXECUTE_W_RESULT:
				case CMD_EXECUTE_WO_RESULT:
					ClientStmt clientStmt = (ClientStmt)in.readObject();
					logger.log(Level.INFO, "CompileServer: Received client stmt:" + clientStmt.getStmt());
					
					FunSQLCompiler compiler = new FunSQLCompiler();
					AbstractServerStmt serverStmt = compiler.compile(clientStmt.getStmt());
					err = compiler.getLastError();
					if(err.isError())
						return err;
					
					err = serverStmt.compile();
					
					if(err.isError())
						return err;
					
					err = serverStmt.execute();

					if(err.isError())
						return err;
					
					//TODO: extract results from statement
					if(cmd == CMD_EXECUTE_W_RESULT){
						
					}
					
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
	public static final int CMD_EXECUTE_WO_RESULT = 1;
	public static final int CMD_EXECUTE_W_RESULT = 2;
	
	// constructors
	public CompileServer() {
		super();
		this.port = Config.COMPILE_PORT;
		
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
	public static synchronized Error deleteCatalog() {
		return Catalog.delete();
	}

	/**
	 * Start server from cmd
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CompileServer server = new CompileServer();
		server.startServer();
	}
}
