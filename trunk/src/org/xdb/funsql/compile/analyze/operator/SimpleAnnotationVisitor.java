/**
 * 
 */
package org.xdb.funsql.compile.analyze.operator;

import org.xdb.error.Error;
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

/** 
 * set the wished connections list and the naive materialization strategy 
 * @author Abdallah 
 *
 */
public class SimpleAnnotationVisitor extends AbstractAnnotationVisitor { 


	/**
	 * @param root
	 */
	public SimpleAnnotationVisitor(AbstractCompileOperator root) {
		super(root);
	}


	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		applyGlobalMaterializeRules(ej);
		
		ej.addWishedConnections(ej.getRightChild().getWishedConnections());
		ej.addWishedConnections(ej.getLeftChild().getWishedConnections());
		
		return Error.NO_ERROR;
	}


	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		applyGlobalMaterializeRules(ej);
		
		for (AbstractCompileOperator op : ej.getChildren()) {
			ej.addWishedConnections(op.getWishedConnections());   
			
		}
		return Error.NO_ERROR;
	}


	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		applyGlobalMaterializeRules(gs);
		
		gs.addWishedConnections(gs.getChild().getWishedConnections());
		return Error.NO_ERROR;
	}

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
		
		to.addWishedConnections(to.getConnections());
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
