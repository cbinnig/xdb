package org.xdb.funsql.compile;

import org.xdb.funsql.compile.operator.*;

public interface TreeVisitor{
	
	void visitEquiJoin(EquiJoin ej);
	void visitGenericSelection(GenericSelection es);
	void visitFunctionCall(FunctionCall fc);
	void visitSimpleAggregation(SimpleAggregation sa);
	void visitSimpleProjection(GenericProjection sp);
	void visitTableOperator(TableOperator to);
}
