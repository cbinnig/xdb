package org.xdb.funsql.compile;

import org.xdb.funsql.compile.operator.*;

public interface TreeVisitor{
	
	abstract void visitEquiJoin(EquiJoin ej);
	abstract void visitEquiSelection(EquiSelection es);
	abstract void visitGenericSelection(GenericSelection es);
	abstract void visitFunctionCall(FunctionCall fc);
	abstract void visitSimpleAggregation(SimpleAggregation sa);
	abstract void visitSimpleProjection(SimpleProjection sp);
	abstract void visitTableOperator(TableOperator to);

}
