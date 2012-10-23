package org.xdb.funsql.compile.analyze.operator;

import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.TableOperator;

public interface ITreeVisitor{
	
	Error visit(AbstractOperator absOp);
	Error visitEquiJoin(EquiJoin ej);
	Error visitGenericSelection(GenericSelection gs);
	Error visitFunctionCall(FunctionCall fc);
	Error visitGenericAggregation(GenericAggregation sa);
	Error visitGenericProjection(GenericProjection gp);
	Error visitTableOperator(TableOperator to);
}
