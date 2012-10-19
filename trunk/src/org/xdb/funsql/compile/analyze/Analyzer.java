package org.xdb.funsql.compile.analyze;

import java.util.HashMap;

import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.types.EnumSimpleType;
import org.xdb.utils.Identifier;

public class Analyzer {
	private CompilePlan compilePlan;
	
	public Analyzer(CompilePlan compilePlan) {
		super();
		this.compilePlan = compilePlan;
	}

	public void analyze(){
		//Create result desc
		for(Identifier rootId: compilePlan.getRoots()){
			AbstractOperator root = this.compilePlan.getOperators(rootId);
			
			CreateResultDesc resultDescVisitor = new CreateResultDesc(root,  new HashMap<AbstractExpression, EnumSimpleType>());
			resultDescVisitor.visit();
		}
	}
}
