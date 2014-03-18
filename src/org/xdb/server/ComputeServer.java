package org.xdb.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.execute.ComputeNode;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.execute.signals.CloseSignal;
import org.xdb.execute.signals.KillSignal;
import org.xdb.execute.signals.ReadySignal;
import org.xdb.execute.signals.RestartSignal;

/**
 * Server which accepts compute commands and calls handler (separate thread)
 * 
 * @author cbinnig
 * 
 */
public class ComputeServer extends AbstractServer {

	/**
	 * Handle for compute commands
	 * 
	 * @author cbinnig
	 * 
	 */
	private class Handler extends AbstractHandler {
		// constructor
		public Handler(final Socket client) {
			super(client);
			logger = ComputeServer.this.logger;
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
			logger.log(Level.INFO, "ComputeServer: Read command from client:" + cmd);
			try {

				switch (cmd) {
				case CMD_STOP_SERVER:
					logger.log(Level.INFO, "Received CMD_STOP_SERVER");
					ComputeServer.this.stopServer();
					break;
				case CMD_PING_SERVER:
					//logger.log(Level.INFO, "Received CMD_PING_SERVER");
					break;
				case CMD_OPEN_OP:
					final AbstractExecuteOperator op = (AbstractExecuteOperator) in.readObject();
					logger.log(Level.INFO, "Received operator:" + op.getOperatorId());
					err = compute.openOperator(op);
					break;
				case CMD_READY_SIGNAL:
					final ReadySignal readSignal = (ReadySignal) in.readObject();
					logger.log(Level.INFO, "Received ready signal for operator:" + readSignal.getConsumer());
					err = compute.signalOperator(readSignal);
					break;
				case CMD_CLOSE_SIGNAL:
					final CloseSignal closeSignal = (CloseSignal) in.readObject();
					logger.log(Level.INFO, "Received close signal for operator:" + closeSignal.getConsumer());
					err = compute.closeOperator(closeSignal);
					break; 
				case CMD_KILL_SIGNAL: 
					final KillSignal killSignal = (KillSignal) in.readObject(); 
					logger.log(Level.INFO, "Received kill signal for operator:" + killSignal.getFailedExecOpId());
                    err = compute.killOperator(killSignal); 
                    break; 
				case CMD_RESTART_SERVER: 
					final RestartSignal restartSignal = (RestartSignal) in.readObject();  
					logger.log(Level.INFO, "Received restart server signal");
                    err = ComputeServer.this.restartComputeNode(restartSignal); 
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
	public static final int CMD_OPEN_OP = 1;
	public static final int CMD_READY_SIGNAL = 2;
	public static final int CMD_CLOSE_SIGNAL = 3;
	public static final int CMD_KILL_SIGNAL = 4;

	// Compute node which executes commands
	private final ComputeNode compute;

	// constructors
	public ComputeServer(final int port) throws Exception {
		super();

		this.port = port;
		this.compute = new ComputeNode(port);
	}
    
	// restart the server for DoomDB/Fault Tolerance 
	public Error restartComputeNode(RestartSignal restartSignal) {
		// stop Mysql and rerun it again. It is assumed to kill 
		// the compute node automatically, otherwise we kill it 
		// manually 
		MysqlRunManager sqlManager = new MysqlRunManager();
		sqlManager.start();  
		// kill the compute node if needed   
		
		// stop the compute server  
		ComputeServer.this.stopServer();
        // wait the meantime between failure 
		// the time the compute server supposed to be off! 
		try {
			Thread.sleep(restartSignal.getTimeToRepair());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
		this.startServer();
		
		return this.err;
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
	
	public ComputeNodeDesc getComputeNode(){
		return this.compute.getComputeNode();
	}

	@Override
	public synchronized void startServer(){
		super.startServer();
		
		this.err = this.compute.startup();
	}
	
	/**
	 * Start server from command line
	 * 
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(final String[] args) throws Exception {
		int port = Config.COMPUTE_PORT;
		if(args.length>=1){
			port = Integer.parseInt(args[0]);
		}
		final ComputeServer server = new ComputeServer( port );
		server.startServer();
		
		if(server.getError().isError()){
			server.stopServer();
			System.out.println("Compute server error ("+server.getError()+")");
		}
	}
}
