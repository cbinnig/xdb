package org.xdb.client;

import java.util.List;

import org.xdb.Config;
import org.xdb.client.statement.ClientStmt;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.doomdb.IDoomDBClient;
import org.xdb.error.Error;
import org.xdb.logging.XDBLog;
import org.xdb.server.CompileServer;
import org.xdb.utils.Tuple;

/**
 * Client to talk to Compute Server.
 */
public class DoomDBClient extends AbstractClient implements IDoomDBClient {
	private DoomDBPlan dplan = null;
	
	// constructors
	public DoomDBClient() {
		this.logger = XDBLog.getLogger(this.getClass().getName());
		this.port = Config.COMPILE_PORT;
		this.url = Config.COMPILE_URL;
	}
	
	//getters and setters
	private void raiseError(Error err){
		if(err.isError()){
			throw new RuntimeException(err.toString());
		}
	}
	
	//methods
	@Override
	public void setQuery(String query) {
		ClientStmt clientStmt = new ClientStmt(query);
		Object[] args = { clientStmt };
		Tuple<Error, Object> result =  this.executeCmdWithResult(this.url, this.port,
				CompileServer.CMD_DOOMDB_COMPILE, args);
		this.raiseError(result.getObject1());
		
		this.dplan = (DoomDBPlan)result.getObject2();
	}

	@Override
	public DoomDBPlan getPlan() {
		return this.dplan;
	}
	
	
	@Override
	public boolean isQueryFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startQuery() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCurrentNode(String operation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getPossibleNodes(String operation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNodeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void killNode(String node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void recoverNode(String node) {
		// TODO Auto-generated method stub
		
	}
}
