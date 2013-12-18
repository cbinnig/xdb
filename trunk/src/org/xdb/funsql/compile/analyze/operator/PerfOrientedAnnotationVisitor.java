package org.xdb.funsql.compile.analyze.operator;

import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
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
 * Set wished connections and materialize flags for max performance.
 * @author Timo Jacobs
 *
 */
public class PerfOrientedAnnotationVisitor extends AbstractAnnotationVisitor {

	public PerfOrientedAnnotationVisitor(AbstractCompileOperator root) {
		super(root);
	}


	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		// if we have a child which is a table op, stay in its connection
		// else use the connection of the left child		
		if (ej.getRightChild() instanceof TableOperator) {
			ej.addWishedConnection(ej.getRightChild().getWishedConnection());
			return Error.NO_ERROR;
		}
		ej.addWishedConnection(ej.getLeftChild().getWishedConnection());
		
		applyGlobalMaterializeRules(ej);
		
		return Error.NO_ERROR;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		// if we have a child which is a table op, stay in its connection
		for (AbstractCompileOperator op : ej.getChildren()) {
			if (op instanceof TableOperator) {
				ej.addWishedConnection(op.getWishedConnection());
				return Error.NO_ERROR;
			}
		}
		// otherwise just use the partition of the first child
		ej.addWishedConnection(ej.getChild(0).getWishedConnection());
		
		applyGlobalMaterializeRules(ej);
		
		return Error.NO_ERROR;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		gs.addWishedConnection(gs.getChild().getWishedConnection());
		
		applyGlobalMaterializeRules(gs);
		
		return Error.NO_ERROR;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		sa.addWishedConnection(sa.getChild().getWishedConnection());
		
		applyGlobalMaterializeRules(sa);
		
		return Error.NO_ERROR;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		gp.addWishedConnection(gp.getChild().getWishedConnection());
		
		applyGlobalMaterializeRules(gp);
		
		return Error.NO_ERROR;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		to.addWishedConnection(to.getConnection());
		
		applyGlobalMaterializeRules(to);
		
		return Error.NO_ERROR;
	}

	@Override
	public Error visitRename(Rename ro) {
		ro.addWishedConnection(ro.getChild().getWishedConnection());
		
		applyGlobalMaterializeRules(ro);
		
		return Error.NO_ERROR;
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) {
		applyGlobalMaterializeRules(absOp);
		
		return Error.NO_ERROR;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		applyGlobalMaterializeRules(absOp);
		
		return Error.NO_ERROR;
	}
}
