/**
 * 
 */
package org.xdb.funsql.compile.analyze.operator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.operator.AbstractAnnotationVisitor;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.DataExchangeOperator;
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
		// TODO Auto-generated constructor stub
	}


	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		applyGlobalMaterializeRules(ej);
		
		// if we have a child which is a table op, stay in its connection
		// else use the connection of the left child		
		if (ej.getRightChild() instanceof TableOperator) {
			ej.setWishedConnection(ej.getRightChild().getWishedConnection());  
			ej.setWishedConnections(ej.getRightChild().getWishedConnections());
			return Error.NO_ERROR;
		}
		ej.setWishedConnection(ej.getLeftChild().getWishedConnection()); 
		ej.setWishedConnections(ej.getLeftChild().getWishedConnections());
		return Error.NO_ERROR;
	}


	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		applyGlobalMaterializeRules(ej);
		
		// if we have a child which is a table op, stay in its connection
		for (AbstractCompileOperator op : ej.getChildren()) {
			if (op instanceof TableOperator) {
				ej.setWishedConnection(op.getWishedConnection());
				// Union all the connections from the children 
				Set<Connection> unionSet = new HashSet<Connection>();
				unionSet.addAll(ej.getWishedConnections()); 
				unionSet.addAll(op.getWishedConnections());  
				ej.setWishedConnections(new ArrayList<Connection>(unionSet));   
				return Error.NO_ERROR;
			}
		}
		// otherwise just use the partition of the first child
		ej.setWishedConnection(ej.getChild(0).getWishedConnection());
		ej.setWishedConnections(ej.getChild(0).getWishedConnections());
		return Error.NO_ERROR;
	}


	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		applyGlobalMaterializeRules(gs);
		
		gs.setWishedConnection(gs.getChild().getWishedConnection());
		gs.setWishedConnections(gs.getChild().getWishedConnections());
		return Error.NO_ERROR;
	}

	// The first/basic rule "strategy" is to materialize 
	// every aggregation
	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		applyGlobalMaterializeRules(sa);
		
		sa.setWishedConnection(sa.getChild().getWishedConnection()); 
		sa.setWishedConnections(sa.getChild().getWishedConnections());
		return Error.NO_ERROR;
	}


	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		applyGlobalMaterializeRules(gp);
		
		gp.setWishedConnection(gp.getChild().getWishedConnection()); 
		gp.setWishedConnections(gp.getChild().getWishedConnections());
		if(gp.getChild().getType().equals(EnumOperator.GENERIC_AGGREGATION)){
			gp.getResult().setMaterialized(true);
		}
		return Error.NO_ERROR;
	}


	@Override
	public Error visitTableOperator(TableOperator to) {
		applyGlobalMaterializeRules(to);
		
		to.setWishedConnection(to.getConnection());  
		to.setWishedConnections(to.getConnections());
		return Error.NO_ERROR;
	}


	@Override
	public Error visitRename(Rename ro) {
		applyGlobalMaterializeRules(ro);
		
		ro.setWishedConnection(ro.getChild().getWishedConnection()); 
		ro.setWishedConnections(ro.getChild().getWishedConnections());
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


	@Override
	public Error visitDataExchange(DataExchangeOperator deOp) {
		
		deOp.setWishedConnection(deOp.getChild().getWishedConnection()); 
		deOp.setWishedConnections(deOp.getChild().getWishedConnections());
		deOp.getResult().setMaterialized(true);
		return Error.NO_ERROR;
	}

}
