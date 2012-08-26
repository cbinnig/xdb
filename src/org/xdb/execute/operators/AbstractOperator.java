package org.xdb.execute.operators;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.execute.OperatorDesc;

/**
 * Executes SQL statements on computing nodes
 * and signals consumer when ready
 * @author cbinnig
 */
public abstract class AbstractOperator implements Serializable {
	private static final long serialVersionUID = -3874534068048724293L;
	
	protected Integer operatorId; //unique op id
	protected HashSet<Integer> sourceIds  = new HashSet<Integer>();
	protected HashSet<OperatorDesc> sources  = new HashSet<OperatorDesc>();
	protected HashSet<OperatorDesc> consumers = new HashSet<OperatorDesc>();
	
	//DDL statements to set up node
	protected Vector<String> prepareSQLs = new Vector<String>();
	
	//DML statements for execution
	protected Vector<String> executeSQLs = new Vector<String>();
	
	//DDL statements for execution
	protected Vector<String> closeSQLs = new Vector<String>();
		
		
	//last error
	protected Error err = Error.NO_ERROR;

	//constructors
	public AbstractOperator(Integer nodeId) {
		super();
		this.operatorId = nodeId;
	}

	//getters and setters
	public Integer getOperatorId() {
		return operatorId;
	}
	
	public void addConsumer(OperatorDesc consumer){
		this.consumers.add(consumer);
	}
	
	public Set<OperatorDesc> getConsumers() {
		return this.consumers;
	}

	public void addPrepareSQL(String ddl){
		this.prepareSQLs.add(ddl);
	}

	public void addExecuteSQL(String dml){
		this.executeSQLs.add(dml);
	}
	
	public void addCloseSQL(String ddl){
		this.closeSQLs.add(ddl);
	}
	
	public Set<Integer> getSourceIds(){
		return this.sourceIds;
	}
	
	public Set<OperatorDesc> getSources(){
		return this.sources;
	}
	
	public void addSource(OperatorDesc source){
		this.sourceIds.add(source.getOperatorID());
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
	 * Execute prepare DDL statements
	 */
	public abstract Error prepare();
	
	/**
	 * Execute DML statements
	 */
	public abstract Error execute();
	
	/**
	 * Closes Node
	 * @return
	 */
	public abstract Error close();
}
