package org.xdb.doomdb;

import java.io.Serializable;

import org.xdb.client.statement.ClientStmt;

public class QueryWithStats implements Serializable{

	private static final long serialVersionUID = 5151290500147768991L;
	
	// clientStmt 
	private ClientStmt clientStmt;  
    // queryStmt 
	private QueryStats queryStats; 
	
	/**
	 * 
	 * @param clientStmt
	 * @param queryStats
	 */
	public QueryWithStats(ClientStmt clientStmt, QueryStats queryStats){
		this.clientStmt = clientStmt; 
		this.queryStats = queryStats;
	}

	/**
	 * @return the clientStmt
	 */
	public ClientStmt getClientStmt() {
		return clientStmt;
	}

	/**
	 * @param clientStmt the clientStmt to set
	 */
	public void setClientStmt(ClientStmt clientStmt) {
		this.clientStmt = clientStmt;
	}

	/**
	 * @return the queryStats
	 */
	public QueryStats getQueryStats() {
		return queryStats;
	}

	/**
	 * @param queryStats the queryStats to set
	 */
	public void setQueryStats(QueryStats queryStats) {
		this.queryStats = queryStats;
	}

}
