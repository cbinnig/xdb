package org.xdb.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.xdb.Config;
import org.xdb.client.statement.ClientStmt;
import org.xdb.error.Error;
import org.xdb.logging.XDBLog;
import org.xdb.server.CompileServer;

/**
 * Client to talk to Compute Server.
 */
public class CompileClient extends AbstractClient{

	// constructors
	public CompileClient() {
		this.logger = XDBLog.getLogger(this.getClass().getName());
		this.port = Config.COMPILE_PORT;
		this.url = Config.COMPILE_URL;
	}

	/**
	 * Execute a given FunSQL statement on compile server
	 * @param stmt
	 * @return
	 */
	public Error executeStmt(String stmt) {
		Error err = new Error();
		ClientStmt clientStmt = new ClientStmt(stmt);
		
		try {
			Socket server = new Socket(this.url, this.port);
			ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());

			out.writeInt(CompileServer.CMD_EXECUTE_WO_RESULT);
			out.flush();
			out.writeObject(clientStmt);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(
					server.getInputStream());
			err = (Error) in.readObject();

			server.close();

		} catch (Exception e) {
			err = createClientError(e);
		}

		return err;
	}
}
