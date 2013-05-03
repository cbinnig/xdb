package org.xdb.funsql.compile.analyze.operator;

import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.DataExchangeOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.SQLCombined;
import org.xdb.funsql.compile.operator.SQLJoin;
import org.xdb.funsql.compile.operator.SQLUnary;
import org.xdb.funsql.compile.operator.TableOperator;

/**
 * Puts the wished connection into each operator of the tree. Naive implementation.
 * @author Timo Jacobs
 *
 */
public class ConnectionAnnotationVisitor extends AbstractBottomUpTreeVisitor {

	public ConnectionAnnotationVisitor(AbstractCompileOperator root) {
		super(root);
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		// if we have a child which is a table op, stay in its connection
		// else use the connection of the left child		
		if (ej.getRightChild() instanceof TableOperator) {
			ej.setWishedConnection(ej.getRightChild().getWishedConnection());
			return Error.NO_ERROR;
		}
		ej.setWishedConnection(ej.getLeftChild().getWishedConnection());
		return Error.NO_ERROR;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		// if we have a child which is a table op, stay in its connection
		for (AbstractCompileOperator op : ej.getChildren()) {
			if (op instanceof TableOperator) {
				ej.setWishedConnection(op.getWishedConnection());
				return Error.NO_ERROR;
			}
		}
		// otherwise just use the partition of the first child
		ej.setWishedConnection(ej.getChild(0).getWishedConnection());
		return Error.NO_ERROR;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		gs.setWishedConnection(gs.getChild().getWishedConnection());
		return Error.NO_ERROR;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		sa.setWishedConnection(sa.getChild().getWishedConnection());
		return Error.NO_ERROR;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		gp.setWishedConnection(gp.getChild().getWishedConnection());
		return Error.NO_ERROR;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		to.setWishedConnection(to.getConnection());
		return Error.NO_ERROR;
	}

	@Override
	public Error visitRename(Rename ro) {
		ro.setWishedConnection(ro.getChild().getWishedConnection());
		return Error.NO_ERROR;
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) {
		// do nothing
		return Error.NO_ERROR;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		// do nothing
		return Error.NO_ERROR;
	}

	@Override
	public Error visitDataExchange(DataExchangeOperator deOp) {
		deOp.setWishedConnection(deOp.getChild().getWishedConnection());
		return Error.NO_ERROR;
	}

}
