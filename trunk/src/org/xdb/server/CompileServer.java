package org.xdb.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;

import org.xdb.Config;
import org.xdb.client.statement.ClientStmt;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompileServerNode;
import org.xdb.metadata.Catalog;
import org.xdb.utils.Tuple;

/**
 * 
 * @author cbinnig
 */
public class CompileServer extends AbstractServer {

	private class Handler extends AbstractHandler {
		// constructor
		public Handler(final Socket client) {
			super(client);
			logger = CompileServer.this.logger;
		}

		/**
		 * Handle incoming cmd
		 * 
		 * @return
		 * @throws IOException
		 */
		@Override
		protected Error handle(final ObjectOutputStream out, final ObjectInputStream in) throws IOException {
			Error err = new Error();

			final int cmd = in.readInt();
			logger.log(Level.INFO, "CompileServer: Read command from client:" + cmd);
			try {

				switch (cmd) {
				case CMD_RESET_CATALOG:
					err = resetCatalog();
					break;
				case CMD_EXECUTE_W_RESULT:
				case CMD_EXECUTE_WO_RESULT:
					final ClientStmt execStmt = (ClientStmt)in.readObject();
					logger.log(Level.INFO, "CompileServer: Received client stmt:" + execStmt.getStmt());
					err = compileNode.compileAndExecuteStmt(execStmt);
					break;
				case CMD_DOOMDB_COMPILE:
					final ClientStmt compileStmt = (ClientStmt)in.readObject();
					logger.log(Level.INFO, "CompileServer: Received client stmt:" + compileStmt.getStmt());
					Tuple<Error, DoomDBPlan> result = compileNode.doomDBCompileStmt(compileStmt);
					out.writeObject(result.getObject2());
					err = result.getObject1();
					break;
				default:
					err = createCmdError(cmd);
					break;
				}
			} catch (final Exception e) {
				err = createServerError(e);
			}

			return err;
		}
	}

	// constants for commands
	public static final int CMD_RESET_CATALOG= 1;
	public static final int CMD_EXECUTE_WO_RESULT = 2;
	public static final int CMD_EXECUTE_W_RESULT = 3;

	public static final int CMD_DOOMDB_COMPILE = 100;
	
	private final CompileServerNode compileNode;
	
	// constructors
	public CompileServer() {
		super();
		this.port = Config.COMPILE_PORT;
		this.compileNode = new CompileServerNode();
		
		err = Catalog.load();
		logger.log(Level.INFO, "Catalog loaded ... ");
	}

	// methods
	/**
	 * Handle incoming client requests
	 */
	@Override
	protected void handle(final Socket client) {
		final Handler handler = new Handler(client);
		handler.start();
	}

	@Override
	public synchronized void startServer(){
		super.startServer();
	}
	
	@Override
	public synchronized void stopServer(){
		super.stopServer();
	}
	
	/**
	 * Deleted catalog content
	 * 
	 * @return
	 */
	public static synchronized Error deleteCatalog() {
		return Catalog.delete();
	}

	
	public static synchronized Error resetCatalog() {
		Error err =  Catalog.delete();
		if(err.isError())
			return err;
		
		return Catalog.load();
	}
	
	/**
	 * Start server from cmd
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		final CompileServer server = new CompileServer();
		server.startServer();
		
		if(server.getError().isError()){
			server.stopServer();
			System.out.println("Compile server error ("+server.getError()+")");
		}
	}
}
