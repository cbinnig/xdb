package org.xdb.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.Config;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.execute.OperatorDesc;
import org.xdb.execute.operators.AbstractOperator;
import org.xdb.execute.signals.CloseSignal;
import org.xdb.execute.signals.ReadySignal;
import org.xdb.logging.XDBLog;
import org.xdb.server.ComputeServer;

public class ComputeClient {
	// Helpers
	private Logger logger;

	public ComputeClient() {
		this.logger = XDBLog.getLogger(this.getClass().getName());
	}

	/**
	 * Prepares operator on node
	 * @param node
	 * @param op
	 * @return
	 */
	public Error prepareOperator(String node, AbstractOperator op) {
		Error err = Error.NO_ERROR;

		try {
			Socket server = new Socket(node, Config.COMPUTE_PORT);
			ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(ComputeServer.CMD_PREPARE_OP);
			out.flush();
			out.writeObject(op);
			out.flush();
			
			ObjectInputStream in = new ObjectInputStream( server.getInputStream() );
			err = (Error)in.readObject();
			
			server.close();
			
		} catch (Exception e) {
			err = createComputeError(e);
		}

		return err;
	}
	
	/**
	 * Triggers to run operator on node
	 * @param node
	 * @param op
	 * @return
	 */
	public Error executeOperator(String node, AbstractOperator op) {
		Error err = Error.NO_ERROR;
		ReadySignal signal = new ReadySignal(Config.COMPUTE_NOOP_ID, op.getOperatorId());
		
		try {
			Socket server = new Socket(node, Config.COMPUTE_PORT);
			ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(ComputeServer.CMD_READY_SIGNAL);
			out.flush();
			out.writeObject(signal);
			out.flush();
			
			ObjectInputStream in = new ObjectInputStream( server.getInputStream() );
			err = (Error)in.readObject();
			
			server.close();
			
		} catch (Exception e) {
			err = createComputeError(e);
		}
		
		return err;
	}
	
	
	/**
	 * Closes operator on node
	 * @param node
	 * @param op
	 * @return
	 */
	public Error closeOperator(String node, AbstractOperator op) {
		Error err = Error.NO_ERROR;
		CloseSignal signal = new CloseSignal(op.getOperatorId());
		
		try {
			Socket server = new Socket(node, Config.COMPUTE_PORT);
			ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(ComputeServer.CMD_CLOSE_SIGNAL);
			out.flush();
			out.writeObject(signal);
			out.flush();
			
			ObjectInputStream in = new ObjectInputStream( server.getInputStream() );
			err = (Error)in.readObject();
			
			server.close();
			
		} catch (Exception e) {
			err = createComputeError(e);
		}
		
		return err;
	}
	
	/**
	 * Send ready signal
	 * @param op
	 * @param consumer
	 * @return
	 */
	public Error sendReadySignal(AbstractOperator op, OperatorDesc consumer){
		Error err = Error.NO_ERROR;
		
		try {
			Socket consumerSocket = new Socket(consumer.getOperatorNode(),
					Config.COMPUTE_PORT);
			ObjectOutputStream out = new ObjectOutputStream(
					consumerSocket.getOutputStream());
			ReadySignal signal = new ReadySignal(op.getOperatorId(),
					consumer.getOperatorID());
			
			out.writeInt(ComputeServer.CMD_READY_SIGNAL);
			out.flush();
			out.writeObject(signal);
			out.flush();
			
			ObjectInputStream in = new ObjectInputStream( consumerSocket.getInputStream() );
			err = (Error)in.readObject();
			
			consumerSocket.close();
		} catch (Exception e) {
			err = createComputeError(e);
		}
		
		return err;
	}
	
	/**
	 * Send close signal
	 * @param op
	 * @param source
	 * @return
	 */
	public Error sendCloseSignal(OperatorDesc source){
		Error err = Error.NO_ERROR;
		
		try {
			Socket sourceSocket = new Socket(source.getOperatorNode(),
					Config.COMPUTE_PORT);
			ObjectOutputStream out = new ObjectOutputStream(
					sourceSocket.getOutputStream());
			CloseSignal signal = new CloseSignal(source.getOperatorID());
			out.writeInt(ComputeServer.CMD_CLOSE_SIGNAL);
			out.flush();
			out.writeObject(signal);
			out.flush();
			
			ObjectInputStream in = new ObjectInputStream( sourceSocket.getInputStream() );
			err = (Error)in.readObject();
			
			sourceSocket.close();
		} catch (Exception e) {
			err = createComputeError(e);
		}
		
		return err;
	}
	
	/**
	 * Create error
	 * @param e
	 * @return
	 */
	private Error createComputeError(Exception e) {
		e.printStackTrace();
		
		String[] args = { e.toString() };
		Error err = new Error(EnumError.COMPUTE_ERROR, args);
		logger.log(Level.SEVERE, err.toString());
		return err;
	}
}
