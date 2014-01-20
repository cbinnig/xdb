package org.xdb.funsql.compile;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.logging.XDBLog;
import org.xdb.utils.Dotty;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphFactory;
import com.oy.shared.lm.graph.GraphNode;

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

	// only set if the plan is a copied plan; Mapping from old to new Operator
	private Map<AbstractCompileOperator, AbstractCompileOperator> oldOptoNewOpMap;
	private Vector<Identifier> copyVistorRoots = new Vector<Identifier>();

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

	public AbstractCompileOperator getOperator(Identifier opId) {
		return operators.get(opId);
	}

	public Collection<Identifier> getRootIds() {
		return roots;
	}

	public Identifier getRootId(int i) {
		return roots.get(0);
	}

	public Collection<AbstractCompileOperator> getRootsCollection() {
		Vector<AbstractCompileOperator> rootOps = new Vector<AbstractCompileOperator>(
				this.roots.size());
		for (Identifier rootId : this.roots) {
			rootOps.add(this.operators.get(rootId));
		}
		return rootOps;
	}

	public Collection<AbstractCompileOperator> getCopyRootsCollection() {
		Vector<AbstractCompileOperator> rootOps = new Vector<AbstractCompileOperator>(
				this.getCopyVistorRoots().size());
		for (Identifier rootId : this.copyVistorRoots) {
			rootOps.add(this.operators.get(rootId));
		}
		return rootOps;
	}

	public void setRootsCollection(Collection<AbstractCompileOperator> roots) {
		this.roots.clear();

		for (AbstractCompileOperator abstractCompileOperator : roots) {
			this.roots.add(abstractCompileOperator.getOperatorId());
		}

	}

	public AbstractCompileOperator getRoot(int i) {
		return this.operators.get(this.roots.get(i));
	}

	public void addRootId(Identifier root) {
		this.roots.add(root);
	}

	public void removeRootId(Identifier root) {
		this.roots.remove(root);
	}

	public boolean isInPlan(Identifier id) {
		return this.operators.containsKey(id);
	}

	public void addSubPlan(CompilePlan plan) {
		this.operators.putAll(plan.operators);
		this.leaves.addAll(plan.leaves);
	}

	public Error getLastError() {
		return err;
	}

	public void setLastOpId(Integer lastOpId) {
		this.lastOpId = lastOpId;
	}

	public HashSet<Identifier> getLeaves() {
		return leaves;
	}

	public void setLeaves(HashSet<Identifier> leaves) {
		this.leaves = leaves;
	}

	public void setOperators(
			HashMap<Identifier, AbstractCompileOperator> operators) {
		this.operators = operators;
	}

	public void setRoots(Vector<Identifier> roots) {
		this.roots = roots;
	}

	public Vector<Identifier> getRoots() {
		return roots;
	}

	public Map<AbstractCompileOperator, AbstractCompileOperator> getOldOptoNewOpMap() {
		return oldOptoNewOpMap;
	}

	public Vector<Identifier> getCopyVistorRoots() {
		return copyVistorRoots;
	}

	// methods

	/**
	 * Initialize transient attributes after shipping
	 */
	public void init() {
		this.logger = XDBLog.getLogger(this.getClass().getName());
	}

	/**
	 * Removes operator from plan
	 * 
	 * @param opId
	 */
	public void removeOperator(Identifier opId) {
		this.operators.remove(opId);
		this.leaves.remove(opId);
		this.roots.remove(opId);
	}

	/**
	 * replaces a leaf Operator with a given variable name in plan with new leaf
	 * 
	 * @param varKey
	 * @param newLeafOp
	 */
	public void replaceVariable(String varKey,
			AbstractCompileOperator rootOfSubplan) {
		HashSet<Identifier> removedLeaves = new HashSet<Identifier>();
		for (Identifier leafId : this.leaves) {
			TableOperator leafOp = (TableOperator) this.operators.get(leafId);
			if (leafOp.getTableName().equals(varKey)) {
				Rename newLeafOp = new Rename(rootOfSubplan);
				this.replaceOperator(leafOp.getOperatorId(), newLeafOp);
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

		if (op.isLeaf())
			this.leaves.add(op.getOperatorId());
	}

	/**
	 * Replace operator with given id
	 * 
	 * @param opId
	 * @param op
	 * @param isRoot
	 */
	public void replaceOperator(Identifier opId, AbstractCompileOperator op) {
		//remove operator from plan
		this.operators.remove(op.getOperatorId());
		
		//set new id and replace
		op.setOperatorId(opId);

		logger.log(Level.INFO, "Add operator" + op.toString()
				+ " to compile plan " + this.planId);

		this.operators.put(opId, op);
	}

	/**
	 * Generates a visual graph representation of the compile plan
	 */
	public Error tracePlan(String fileName) {
		fileName += this.planId;
		Error error = new Error();
		Graph graph = GraphFactory.newGraph();

		HashMap<Identifier, GraphNode> nodes = new HashMap<Identifier, GraphNode>();
		for (Identifier root : this.roots) {
			GraphNode node = graph.addNode();
			AbstractCompileOperator rootOp = this.operators.get(root);
			node.getInfo().setCaption(rootOp.getType().toString());
			nodes.put(root, node);
			rootOp.traceOperator(graph, nodes);
		}

		Dotty.dot2Img(graph, fileName);
		return error;
	}
}