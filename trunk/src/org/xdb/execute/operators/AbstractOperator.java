package org.xdb.execute.operators;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.utils.Identifier;

/**
 * Executes SQL statements on computing nodes
 * and signals consumer when ready
 * @author cbinnig
 */
public abstract class AbstractOperator implements Serializable {
	private static final long serialVersionUID = -3874534068048724293L;
	
	//unique op id
	protected Identifier operatorId; 
	
	//sourceIDs
	protected HashSet<String> sourceIds  = new HashSet<String>();
	
	//Source Descriptions
	protected HashSet<OperatorDesc> sources  = new HashSet<OperatorDesc>();
	
	//Consumer Descriptions
	protected HashSet<OperatorDesc> consumers = new HashSet<OperatorDesc>();
	
	//DDL statements to set up node
	protected Vector<String> openSQLs = new Vector<String>();
	
	//DDL statements for execution
	protected Vector<String> closeSQLs = new Vector<String>();
		
		
	//last error
	protected Error err = Error.NO_ERROR;

	//constructors
	public AbstractOperator(Identifier nodeId) {
		super();
		this.operatorId = nodeId;
	}

	//getters and setters
	public Identifier getOperatorId() {
		return operatorId;
	}
	
	public void addConsumer(OperatorDesc consumer){
		this.consumers.add(consumer);
	}
	
	public Set<OperatorDesc> getConsumers() {
		return this.consumers;
	}

	public void addOpenSQL(String ddl){
		this.openSQLs.add(ddl);
	}
	
	public void addCloseSQL(String ddl){
		this.closeSQLs.add(ddl);
	}
	
	public Set<String> getSourceIds(){
		return this.sourceIds;
	}
	
	public Set<OperatorDesc> getSources(){
		return this.sources;
	}
	
	public void addSource(OperatorDesc source){
		this.sourceIds.add(source.getOperatorID().toString());
		this.sources.add(source);
	}
	
	public Error getError(){
		return this.err;
	}
	
	//methods
	/**
	 * Opens Node
	 * @return
	 */
	public abstract Error open();
	
	/**
	 * Execute Node
	 * @return
	 */
	public abstract Error execute();
	
	/**
	 * Closes Node
	 * @return
	 */
	public abstract Error close();
}
