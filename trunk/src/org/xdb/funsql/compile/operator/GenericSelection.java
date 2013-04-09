package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.HashMap;

import org.xdb.error.Error;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.Identifier;
import org.xdb.utils.SetUtils;
import org.xdb.utils.StringTemplate;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class GenericSelection extends AbstractUnaryOperator {
	private static final long serialVersionUID = 5178586492851005421L;
	
	private AbstractPredicate predicate;
	
	private final StringTemplate sqlTemplate = 
			new StringTemplate("SELECT <RESULTS> FROM (<<OP1>>) AS <OP1> WHERE <PRED>");
	
	private final StringTemplate sqlTemplate2 = 
			new StringTemplate("SELECT <RESULTS> FROM <<OP1>> AS <OP1> WHERE <PRED>");
	
	//constructors
	public GenericSelection(AbstractCompileOperator child) {
		super(child);
		this.type = EnumOperator.GENERIC_SELECTION;
	}
	/**
	 * Copy Constructor
	 * @param toCopy Element to copy
	 */
	public GenericSelection(GenericSelection toCopy){
		super(toCopy);
		this.predicate = toCopy.predicate.clone();
	}

	//getters and setters
	public AbstractPredicate getPredicate() {
		return predicate;
	}

	public void setPredicate(AbstractPredicate predicate) {
		this.predicate = predicate;
	}	
	
	//methods
	@Override
	public String toSqlString() {
		final HashMap<String, String> vars = new HashMap<String, String>();
		vars.put("RESULTS", SetUtils.buildAliasString(getChild().getResultTableAttributes(), getResultAttributes()));
		vars.put("OP1", getChild().getOperatorId().toString());
		vars.put("PRED", predicate.toSqlString());
		if (this.getChild().getType().equals(EnumOperator.TABLE)){
			return sqlTemplate2.toString(vars);
		}else {
			return sqlTemplate.toString(vars);
		}
	
	}
	
	@Override
	public Error traceOperator(Graph g, HashMap<Identifier, GraphNode> nodes){
		Error err = super.traceOperator(g, nodes);
		if(err.isError())
			return err;
		
		GraphNode node = nodes.get(this.operatorId);
		node.getInfo().setFooter(this.predicate.toString()+  AbstractToken.NEWLINE + node.getInfo().getFooter());
		return err;
	}
	
	@Override
	public void renameAttributes(String oldId, String newId) {
		TokenAttribute.renameTable(this.predicate.getAttributes(), oldId, newId);
	}
	
	@Override
	public void renameForPushDown(Collection<TokenAttribute> selAtts) {
		TokenAttribute.renameTable(selAtts, this.getChild().getOperatorId().toString());
	}
	
	@Override
	public GenericSelection clone() throws CloneNotSupportedException {
		
		GenericSelection gs = (GenericSelection) super.clone();
		gs.predicate = predicate.clone();
		return gs;
	}
}
