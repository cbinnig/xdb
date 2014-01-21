package org.xdb.client;

import java.util.List;

import org.xdb.Config;
import org.xdb.client.statement.ClientStmt;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.doomdb.DoomDBSchema;
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
	private DoomDBSchema schema = null;
	
	private CompileClient cClient = new CompileClient();
	private MasterTrackerClient mClient = new MasterTrackerClient();
	
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
	private Error executeDDLs(String[] schemaDDLs){
		Error err = new Error();
		for(String schemaDDL: schemaDDLs){
			err = cClient.executeStmt(schemaDDL);
			if(err.isError())
				return err;
		}
		return err;
	}
	
	@Override
	public void setSchema(DoomDBSchema schema) {	
		Error err = cClient.resetCatalog();
		this.raiseError(err);
		
		err = this.executeDDLs(schema.getCreateDDL());
		this.raiseError(err);

		this.schema = schema;
	}
	
	@Override
	public void setQuery(String query) {
		if(this.schema==null){
			throw new RuntimeException("Provide a schema before!");
		}
		
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
	public void startQuery() {
		if(this.dplan==null){
			throw new RuntimeException("Provide a query before!");
		}
		
		new Thread()
        {
            public void run() {
            	Error err =  mClient.executeDoomDBPlan(dplan.getPlanDesc());
        		raiseError(err);
            }
        }.start();
	}
	
	@Override
	public boolean isQueryFinished() {
		if(this.dplan==null){
			throw new RuntimeException("Provide a query before starting it!");
		}
		Tuple<Error, Boolean> result =  mClient.isDoomDBPlanFinished(dplan.getPlanDesc());
		this.raiseError(result.getObject1());
		return result.getObject2();
	}

	@Override
	public int getNodeCount() {
		if(this.schema!=null)
			return this.schema.getPartitions();
		
		return 0;
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
	public void killNode(String node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recoverNode(String node) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public long getTime() {
		// TODO Auto-generated method stub
		return 0;
	}
}
