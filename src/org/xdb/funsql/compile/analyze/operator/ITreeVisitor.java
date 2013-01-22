package org.xdb.funsql.compile.analyze.operator;

import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.SQLJoin;
import org.xdb.funsql.compile.operator.SQLUnary;
import org.xdb.funsql.compile.operator.TableOperator;

public interface ITreeVisitor{
	
	Error visit(AbstractCompileOperator absOp);
	Error visitEquiJoin(EquiJoin ej);
	Error visitSQLJoin(SQLJoin ej);
	Error visitGenericSelection(GenericSelection gs);
	Error visitFunctionCall(FunctionCall fc);
	Error visitGenericAggregation(GenericAggregation sa);
	Error visitGenericProjection(GenericProjection gp);
	Error visitTableOperator(TableOperator to);
	Error visitRename(Rename ro);
	Error visitSQLUnary(SQLUnary absOp);
}
