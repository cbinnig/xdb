/**
 * 
 */
package org.xdb.funsql.compile.analyze.operator;

import java.util.HashSet;


import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.operator.AbstractAnnotationVisitor;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.SQLCombined;
import org.xdb.funsql.compile.operator.SQLJoin;
import org.xdb.funsql.compile.operator.SQLUnary;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.metadata.Connection;

/** 
 * set the wished connections list and the naive materialization  strategy 
 * for robustness purposes. They are set but not used yet!  
 * @author Abdallah 
 *
 */
public class RobustnessOrientedAnnotationVisitor extends AbstractAnnotationVisitor { 


	/**
	 * @param root
	 */
	public RobustnessOrientedAnnotationVisitor(AbstractCompileOperator root) {
		super(root);
	}


	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		applyGlobalMaterializeRules(ej);
		
		// if we have a child which is a table op, stay in its connection
		// else use the connection of the left child		
		if (ej.getRightChild() instanceof TableOperator) {
			ej.addWishedConnections(ej.getRightChild().getWishedConnections());
			return Error.NO_ERROR;
		}
		ej.addWishedConnections(ej.getLeftChild().getWishedConnections());
		return Error.NO_ERROR;
	}


	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		applyGlobalMaterializeRules(ej);
		
		// if we have a child which is a table op, stay in its connection
		for (AbstractCompileOperator op : ej.getChildren()) {
			ej.addWishedConnections(op.getWishedConnections());   
			
		}
		// otherwise just use the partition of the first child
		ej.addWishedConnections(ej.getChild(0).getWishedConnections());
		return Error.NO_ERROR;
	}


	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		applyGlobalMaterializeRules(gs);
		
		gs.addWishedConnections(gs.getChild().getWishedConnections());
		return Error.NO_ERROR;
	}

	// The first/basic rule "strategy" is to materialize 
	// every aggregation
	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		applyGlobalMaterializeRules(sa);
		
		sa.addWishedConnections(sa.getChild().getWishedConnections());
		return Error.NO_ERROR;
	}


	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		applyGlobalMaterializeRules(gp);
		
		gp.addWishedConnections(gp.getChild().getWishedConnections());
		if(gp.getChild().getType().equals(EnumOperator.GENERIC_AGGREGATION)){
			gp.getResult().materialize(true);
		}
		return Error.NO_ERROR;
	}


	@Override
	public Error visitTableOperator(TableOperator to) {
		applyGlobalMaterializeRules(to);
		
		to.addWishedConnections(new HashSet<Connection>(to.getConnections()));
		return Error.NO_ERROR;
	}


	@Override
	public Error visitRename(Rename ro) {
		applyGlobalMaterializeRules(ro);
		
		ro.addWishedConnections(ro.getChild().getWishedConnections());
		return Error.NO_ERROR;
	}


	@Override
	public Error visitSQLUnary(SQLUnary absOp){
		applyGlobalMaterializeRules(absOp);
		return Error.NO_ERROR;
	}


	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		applyGlobalMaterializeRules(absOp);
		return Error.NO_ERROR;
	}
}
