package org.xdb.funsql.compile;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractOperator;
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
	private HashMap<Identifier, AbstractOperator> operators;
	private HashSet<Identifier> roots;

	// logger
	private Logger logger;

	// last error
	private Error err = new Error();

	// constructor
	public CompilePlan() {
		this.operators = new HashMap<Identifier, AbstractOperator>();
		this.roots = new HashSet<Identifier>();

		this.planId = new Identifier(lastPlanId++);
		this.logger = XDBLog.getLogger(this.getClass().getName());
	}

	// getter and setter
	public Identifier getPlanId() {
		return this.planId;
	}

	public Collection<AbstractOperator> getOperators() {
		return operators.values();
	}

	public AbstractOperator getOperators(Identifier opId) {
		return operators.get(opId);
	}

	public Collection<Identifier> getRoots() {
		return roots;
	}

	public Error getLastError() {
		return err;
	}

	// methods
	/**
	 * Adds operator to plan and indicates if it is a root operator. For each
	 * operator a unique operator ID is generated!
	 * 
	 * @param op
	 * @param isRoot
	 */
	public void addOperator(AbstractOperator op, boolean isRoot) {
		Identifier opId = this.planId.clone().append(lastOpId++);
		op.setOperatorId(opId);

		logger.log(Level.INFO, "Add operator" + op.toString()
				+ " to compile plan " + this.planId);

		this.operators.put(opId, op);
		if (isRoot) {
			this.roots.add(opId);
		}
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
			AbstractOperator rootOp = this.operators.get(root);
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
