package org.xdb.funsql.compile;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.logging.XDBLog;
import org.xdb.utils.Identifier;

/**
 * Represents a compiled plan which can be used for optimization and code generation 
 * @author cbinnig
 *
 */
public class CompilePlan implements Serializable {

	private static final long serialVersionUID = -9194089079571372541L;

	// unique plan and operator id
	private Identifier planId;
	
	// last plan operator id
	private Integer lastOpId=1;
		
	// plan info
	private HashMap<Identifier, AbstractOperator> operators;
	private HashSet<Identifier> roots;
	
	// logger
	private Logger logger;
	
	// last error
	private Error err = Error.NO_ERROR;
		

	// constructor
	public CompilePlan(Identifier planId) {
		this.planId = planId;
		this.operators = new HashMap<Identifier, AbstractOperator>();
		this.roots = new HashSet<Identifier>();
		
		
		this.logger = XDBLog.getLogger(this.getClass().getName());
	}

	// getter and setter
	public Identifier getPlanId() {
		return this.planId;
	}
	
	public Collection<AbstractOperator> getOperators() {
		return operators.values();
	}

	public HashSet<Identifier> getRoots() {
		return roots;
	}
	
	public Error getLastError() {
		return err;
	}
	
	// methods

	/**
	 * Adds operator to plan and indicates if it is a root operator.
	 * For each operator a unique operator ID is generated!
	 * @param op
	 * @param isRoot
	 */
	public void addOperator(AbstractOperator op, boolean isRoot){
		Identifier opId = this.planId.clone().append(lastOpId++);
		op.setOperatorId(opId);
		
		logger.log(Level.INFO, "Add operator" + op.toString() + " to compile plan "+ this.planId);
		
		this.operators.put(opId, op);
		if(isRoot){
			this.roots.add(opId);
		}
	}
}
