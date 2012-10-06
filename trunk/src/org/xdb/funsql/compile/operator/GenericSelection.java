package org.xdb.funsql.compile.operator;

import java.util.HashMap;

import org.xdb.error.Error;
import org.xdb.funsql.compile.TreeVisitor;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class GenericSelection extends AbstractUnaryOperator {
	private static final long serialVersionUID = 5178586492851005421L;
	
	private AbstractPredicate predicate;
	
	//constructors
	public GenericSelection(AbstractOperator child) {
		super(child);
		this.type = EnumOperator.GENERIC_SELECTION;
	}

	//getters and setters
	public AbstractPredicate getPredicate() {
		return predicate;
	}

	public void setPredicate(AbstractPredicate predicate) {
		this.predicate = predicate;
	}	
	
	@Override
	public String toSqlString() {
		// TODO: generate sql
		return null;
	}

	@Override
	void accept(TreeVisitor v) {
		v.visitGenericSelection(this);
	}
	
	@Override
	public Error traceGraph(Graph g, HashMap<Identifier, GraphNode> nodes){
		Error err = super.traceGraph(g, nodes);
		if(err.isError())
			return err;
		
		GraphNode node = nodes.get(this.operatorId);
		node.getInfo().setFooter(this.predicate.toString());
		return err;
	}
}
