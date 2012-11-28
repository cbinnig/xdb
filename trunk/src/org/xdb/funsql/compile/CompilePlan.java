package org.xdb.funsql.compile;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.logging.XDBLog;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphFactory;
import com.oy.shared.lm.graph.GraphNode;
import com.oy.shared.lm.out.GRAPHtoDOTtoGIF;

/**
 * Represents a compiled plan which can be used for optimization and code
 * generation
 * 
 * @author cbinnig
 * 
 */
public class CompilePlan implements Serializable {

	private static final long serialVersionUID = -9194089079571372541L;

	// unique plan and operator id
	private Identifier planId;
	private static Integer lastPlanId = 1;

	// last plan operator id
	private Integer lastOpId = 1;

	// plan info
	private HashMap<Identifier, AbstractCompileOperator> operators = new HashMap<Identifier, AbstractCompileOperator>();
	private Vector<Identifier> roots = new Vector<Identifier>();
	private HashSet<Identifier> leaves = new HashSet<Identifier>();
	
	// logger
	private transient Logger logger;

	// last error
	private Error err = new Error();

	// constructor
	public CompilePlan() {
		this.planId = new Identifier(lastPlanId++);
		this.logger = XDBLog.getLogger(this.getClass().getName());
	}

	// getter and setter
	public Identifier getPlanId() {
		return this.planId;
	}

	public Collection<AbstractCompileOperator> getOperators() {
		return operators.values();
	}

	public AbstractCompileOperator getOperators(Identifier opId) {
		return operators.get(opId);
	}

	public Collection<Identifier> getRootIds() {
		return roots;
	}
	
	public Identifier getRootId(int i) {
		return roots.get(0);
	}
	
	public Collection<AbstractCompileOperator> getRoots(){
		Vector<AbstractCompileOperator> rootOps = new Vector<AbstractCompileOperator>(this.roots.size());
		for(Identifier rootId: this.roots){
			rootOps.add(this.operators.get(rootId));
		}
		return rootOps;
	}
	
	public AbstractCompileOperator getRoot(int i){
		return this.operators.get(this.roots.get(i));
	}
	
	public void addRootId(Identifier root){
		this.roots.add(root);
	}
	
	public void addSubPlan(CompilePlan plan) {
		this.operators.putAll(plan.operators);
		this.leaves.addAll(plan.leaves);
	}

	public Error getLastError() {
		return err;
	}

	// methods
	/**
	 * replaces a leaf Operator with a given variable name in plan with new leaf
	 * @param varKey
	 * @param newLeafOp
	 */
	public void replaceVariable(String varKey, AbstractCompileOperator rootOfSubplan){
		HashSet<Identifier> removedLeaves = new HashSet<Identifier>();
		for(Identifier leafId: this.leaves){
			TableOperator leafOp = (TableOperator)this.operators.get(leafId);
			if(leafOp.getTable().getName().equals(varKey)){
				Rename newLeafOp = new Rename(rootOfSubplan);
				this.replaceOperator(leafOp.getOperatorId(), newLeafOp, false);
				leafOp.replace(newLeafOp);
				removedLeaves.add(leafOp.getOperatorId());
			}
		}
		this.leaves.removeAll(removedLeaves);
	}
		
	/**
	 * Adds operator to plan and indicates if it is a root operator. For each
	 * operator a unique operator ID is generated!
	 * 
	 * @param op
	 * @param isRoot
	 */
	public void addOperator(AbstractCompileOperator op, boolean isRoot) {
		Identifier opId = this.planId.clone().append(lastOpId++);
		op.setOperatorId(opId);

		logger.log(Level.INFO, "Add operator" + op.toString()
				+ " to compile plan " + this.planId);

		this.operators.put(opId, op);
		if (isRoot) {
			this.roots.add(opId);
		}
		
		if(op.isLeaf())
			this.leaves.add(op.getOperatorId());
	}
	
	/**
	 * Replace operator with given id
	 * @param opId
	 * @param op
	 * @param isRoot
	 */
	public void replaceOperator(Identifier opId, AbstractCompileOperator op, boolean isRoot) {
		op.setOperatorId(opId);

		logger.log(Level.INFO, "Add operator" + op.toString()
				+ " to compile plan " + this.planId);

		this.operators.put(opId, op);
	}

	/**
	 * Generates a visual graph representation of the compile plan
	 */
	public Error traceGraph(String fileName) {
		fileName += this.planId;
		Error error = new Error();
		Graph graph = GraphFactory.newGraph();

		HashMap<Identifier, GraphNode> nodes = new HashMap<Identifier, GraphNode>();
		for (Identifier root : this.roots) {
			GraphNode node = graph.addNode();
			AbstractCompileOperator rootOp = this.operators.get(root);
			node.getInfo().setCaption(rootOp.getType().toString());
			nodes.put(root, node);
			rootOp.traceGraph(graph, nodes);
		}

		// output graph to *.gif
		final String path = Config.DOT_TRACE_PATH;
		final String dotFileName = path + fileName + ".dot";
		final String gifFileName = path + fileName + ".gif";
		final String exeFileName = Config.DOT_EXE;
		try {
			GRAPHtoDOTtoGIF.transform(graph, dotFileName, gifFileName,
					exeFileName);
		} catch (IOException e) {
			return FunSQLCompiler.createGenericCompileErr(e.getMessage());
		}
		return error;
	}
}
