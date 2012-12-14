package org.xdb.client.statement;

import java.io.Serializable;
import java.util.Vector;

/**
 * Client side statement which is sent 
 * to compile server to be executed.
 *  
 * @author cbinnig
 *
 */
public class ClientStmt implements Serializable {

	private static final long serialVersionUID = -6521230894217627053L;
	
	protected String stmt;
	protected Vector<Object> params;

	// Constructor
	public ClientStmt(String stmt) {
		super();
		this.stmt = stmt;
	}
	
	// getter and setter
	public void addParameter(int idx, Object param){
		this.params.add(idx, param);
	}

	public String getStmt() {
		return stmt;
	}

	public void setStmt(String stmt) {
		this.stmt = stmt;
	}
}
