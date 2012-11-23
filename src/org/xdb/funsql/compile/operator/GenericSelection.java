package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.xdb.error.Error;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
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
			new StringTemplate("SELECT <RESULTS> FROM <<OP1>> AS <OP1>");
	
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
	
	//methods
	
	/**
	 * Remove selection from plan
	 */
	public Map<AbstractOperator, Integer> cut(){
		HashMap<AbstractOperator, Integer> cutInfo = new HashMap<AbstractOperator, Integer>();
		
		//Modify child
		AbstractOperator child = this.getChild();
		child.removeParent(this);
		child.addParents(this.parents);
		
		//Modify parents
		for(AbstractOperator p: this.parents){
			int childIdx = p.children.indexOf(this);
			p.children.set(childIdx, child);
			p.renameAttributes(this.getOperatorId().toString(), child.getOperatorId().toString());
			cutInfo.put(p, childIdx);
		}
		
		//Modify operator
		this.parents.clear();
		
		return cutInfo;
	}
	
	/**
	 * Add selection to plan below given parent
	 * @param parent
	 */
	public void paste(AbstractOperator parent, Integer childIdx){
		//Get old child 
		AbstractOperator child = parent.children.get(childIdx);
		
		//modify parent
		parent.renameAttributes(child.getOperatorId().toString(), this.operatorId.toString());
		parent.children.set(childIdx, this);
		
		//modify selection 
		this.setChild(child);
		this.parents.add(parent);
		ResultDesc newSelResult = child.getResult(0).clone();
		TokenAttribute.renameTable(newSelResult.getAttributes(), this.getOperatorId().toString());
		this.setResult(0, newSelResult);
		
		//modify child
		int parentIdx = child.parents.indexOf(parent);
		child.parents.set(parentIdx, this);
	}
	
	@Override
	public String toSqlString() {
		final HashMap<String, String> vars = new HashMap<String, String>();
		final String opDummy = getChild().getOperatorId().toString();
		
		vars.put("RESULTS", SetUtils.stringifyAttributes("", getResultAttributes()));
		vars.put("OP1", opDummy);
		return sqlTemplate.toString(vars);
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
	
	@Override
	public void renameAttributes(String oldId, String newId) {
		TokenAttribute.renameTable(this.predicate.getAttributes(), oldId, newId);
	}
	
	@Override
	public void renameForPushDown(Collection<TokenAttribute> selAtts) {
		TokenAttribute.renameTable(selAtts, this.getChild().getOperatorId().toString());
	}
}
