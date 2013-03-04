package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.Identifier;
import org.xdb.utils.SetUtils;
import org.xdb.utils.StringTemplate;
import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

public class Union extends AbstractBinaryOperator {
	private static final long serialVersionUID = -7557353401586940254L;

	private boolean distinctMerge = false;
	private final StringTemplate unionAllTemplate = 
			new StringTemplate(" UNION ALL (<<<OP>>>)");
	private final StringTemplate unionDistinctTemplate =
			new StringTemplate(" UNION DISTINCT (<<<OP>>>)"); 

	//constructors
	/**
	 * Constructs new union operator with default merge methodology (UNION ALL - no duplicate-row removal).
	 */
	public Union(AbstractCompileOperator leftChild, AbstractCompileOperator rightChild) {
		super(leftChild, rightChild); 
		this.type = EnumOperator.UNION;
		this.distinctMerge = false;
	}
	
	/**
	 * Constructs new union operator with chosen value for merge methodology.
	 * @param distinct If true, UNION DISTINCT (eliminate duplicate rows) - if false, UNION ALL (no duplicate-row removal).
	 */
	public Union(AbstractCompileOperator leftChild, AbstractCompileOperator rightChild, boolean distinct) {
		super(leftChild, rightChild);
		this.type = EnumOperator.UNION;
		this.distinctMerge = distinct;
	}

	//getters and setters
	public void setDistinctMerge(boolean distinct) {
		this.distinctMerge = distinct;
	}

	@Override
	public String toSqlString() {
		final HashMap<String, String> vars = new HashMap<String, String>();
		String templateString = "";
		
		int size = this.getChildren().size();
		for(int idx = 0; idx < size; idx++){
			final AbstractCompileOperator child = this.getChild(idx);
			
			final String opDummy = "OP"+idx;
			vars.put(opDummy, child.getOperatorId().toString());
			
			if(idx == 0) {
				templateString = "<<"+opDummy+">>";
				continue;
			}
			
			
			Map<String, String> appendMap = new HashMap<String, String>();
			appendMap.put("OP", opDummy);
			
			if(this.distinctMerge)
				templateString += unionDistinctTemplate.toString(appendMap);
			else
				templateString += unionAllTemplate.toString(appendMap);
		}
		
		return new StringTemplate(templateString).toString(vars);
	}

	
	@Override
	public Error traceOperator(Graph g, HashMap<Identifier, GraphNode> nodes){
		Error err = super.traceOperator(g, nodes);
		if(err.isError())
			return err;
		
		GraphNode node = nodes.get(this.operatorId);
		if(this.distinctMerge)
			node.getInfo().setFooter("DISTINCT");
		else
			node.getInfo().setFooter("ALL");
		return err;
	}

	@Override
	public void renameAttributes(String oldId, String newId) {
		//TODO?
	}
	
	@Override
	public void renameForPushDown(Collection<TokenAttribute> selAtts, int childIdx) {
		TokenAttribute.renameTable(selAtts, this.getChild(childIdx).getOperatorId().toString());
	}

	@Override
	public boolean renameOperator(HashMap<String, String> renamedAttributes,
			Vector<String> renamedOps) {
		//TODO?
		return super.renameOperator(renamedAttributes, renamedOps);
	}
}
