package org.xdb.funsql.compile.operator;

import java.util.HashMap;

import org.xdb.error.Error;
import org.xdb.funsql.compile.TreeVisitor;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenLiteral;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class EquiSelection extends AbstractUnaryOperator {
	private static final long serialVersionUID = 5178586492851005421L;
	
	private TokenAttribute attribtue;
	private TokenLiteral value;
	
	//constructors
	public EquiSelection(AbstractOperator child) {
		super(child);
		this.type = EnumOperator.EQUI_SELECTION;
	}

	//getters and setters
	public TokenAttribute getAttribtue() {
		return attribtue;
	}

	public void setAttribtue(TokenAttribute attribtue) {
		this.attribtue = attribtue;
	}

	public TokenLiteral getValue() {
		return value;
	}

	public void setValue(TokenLiteral value) {
		this.value = value;
	}
	
	@Override
	public String toSqlString() {
		// TODO: generate sql
		return null;
	}

	@Override
	void accept(TreeVisitor v) {
		v.visitEquiSelection(this);
	}
	
	@Override
	public Error traceGraph(Graph g, HashMap<Identifier, GraphNode> nodes){
		Error err = super.traceGraph(g, nodes);
		if(err.isError())
			return err;
		
		GraphNode node = nodes.get(this.operatorId);
		node.getInfo().setFooter(this.attribtue.toString()+"="+this.value.toString());
		return err;
	}
}
