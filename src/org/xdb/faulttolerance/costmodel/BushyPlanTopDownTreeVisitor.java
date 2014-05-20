package org.xdb.faulttolerance.costmodel;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.operator.AbstractTopDownTreeVisitor;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.SQLCombined;
import org.xdb.funsql.compile.operator.SQLJoin;
import org.xdb.funsql.compile.operator.SQLUnary;
import org.xdb.funsql.compile.operator.TableOperator;

public class BushyPlanTopDownTreeVisitor extends AbstractTopDownTreeVisitor {

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		return null;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Error visitRename(Rename ro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		// TODO Auto-generated method stub
		return null;
	}

}
