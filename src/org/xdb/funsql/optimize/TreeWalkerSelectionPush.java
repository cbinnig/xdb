package org.xdb.funsql.optimize;

import java.util.Vector;

import org.xdb.funsql.compile.AbstractTreeVisitor;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EnumPushDown;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.TableOperator;

public class TreeWalkerSelectionPush extends AbstractTreeVisitor { //implements ITreeVisitor {
	
	//	AbstractOperator thisRoot = null;
	GenericSelection tempSel = null;
//	Vector<GenericSelection> waitToPush = new Vector<GenericSelection>();

	public TreeWalkerSelectionPush(AbstractOperator root) {
		super(root);
	}
	
//	public TreeWalkerSelectionPush(AbstractOperator root) {
//		thisRoot = root;
//	}
	
//	public void runWalker() {
//		visit(thisRoot);
//	}

//	@Override
//	public void visit(AbstractOperator absOp) {
//		switch(absOp.getType()){
//		case EQUI_JOIN:
//			visitEquiJoin((EquiJoin) absOp);
//			break;
//		case GENERIC_SELECTION:
//			visitGenericSelection((GenericSelection) absOp);
//			break;
////		case FUNCTION_CALL:
////			visitFunctionCall((FunctionCall) absOp);
////			break;
//		case SIMPLE_AGGREGATION:
//			visitSimpleAggregation((SimpleAggregation) absOp);
//			break;
////		case GENERIC_PROJECTION:
//		case SIMPLE_PROJECTION:
//			visitGenericProjection((GenericProjection) absOp);
//			break;
//		case TABLE:
//			visitTableOperator((TableOperator) absOp);
//			break;
//		}
//	}

	@Override
	public void visitEquiJoin(EquiJoin ej) {

		if(tempSel != null){
			if(ej.isPushDownAllowed(EnumPushDown.SELECTION_PUSHDOWN)){
				/*
				 * TODO: check auf welche seite zu pushen und dann nur dahin
				 */
				visit(ej.getLeftChild());
				visit(ej.getRightChild());
			}
			else if(ej.isPushDownAllowed(EnumPushDown.STOP_PUSHDOWN)){
//				waitToPush.add(tempSel);
//				tempSel = null;
//				visit(thisRoot);
				
				if(!checkJoinSide(tempSel, ej)){
					visit(ej.getLeftChild());
				}
				else{
					visit(ej.getRightChild());
				}
				
			}
			else{
				pushDown(tempSel, ej);
			}
		}
		else{
			/*
			 * TODO: neuen Walker fuer jede Seite?
			 */
			visit(ej.getLeftChild());
			visit(ej.getRightChild());
		}
		
	}

	@Override
	public void visitGenericSelection(GenericSelection gs) {
		if(tempSel == null){
//			if(!waitToPush.contains(gs))
				tempSel = gs;
				visit(gs.getChild());
		}
		else{
			if(gs.isPushDownAllowed(EnumPushDown.SELECTION_PUSHDOWN)){
				visit(gs.getChild());
			}
			else{
				pushDown(tempSel, gs);
			}
		}
	}

	@Override
	public void visitFunctionCall(FunctionCall fc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitGenericAggregation(GenericAggregation sa) {
		if(tempSel != null){
			if(sa.isPushDownAllowed(EnumPushDown.SELECTION_PUSHDOWN)){
				visit(sa.getChild());
			}
			else{
				pushDown(tempSel, sa);
			}
		}
		else{
			visit(sa.getChild());
		}
	}

	@Override
	public void visitGenericProjection(GenericProjection gp) {
		
		if(tempSel != null){
			if(gp.isPushDownAllowed(EnumPushDown.SELECTION_PUSHDOWN)){
				visit(gp.getChild());
			}
			else{
				pushDown(tempSel, gp);
			}
		}
		else{
			visit(gp.getChild());
		}

	}

	@Override
	public void visitTableOperator(TableOperator to) {
		
		pushDown(tempSel,to);
		
	}
	
	private void pushDown(GenericSelection toPush, AbstractOperator pushAbove) {
		
		AbstractOperator toPushChild = toPush.getChild();
		Vector<AbstractOperator> toPushParents = toPush.getDestinationOperators();

		toPushChild.getDestinationOperators().remove(toPush);
		for(AbstractOperator parent : toPushParents){
			parent.getSourceOperators().remove(toPush);
			parent.getSourceOperators().add(toPushChild);
			toPushChild.getDestinationOperators().add(parent);
		}

		toPush.getDestinationOperators().removeAllElements();
		toPush.getSourceOperators().removeAllElements();
		Vector<AbstractOperator> newParents = pushAbove.getDestinationOperators();
		
		for(AbstractOperator newParent : newParents){
			newParent.getSourceOperators().remove(pushAbove);
			newParent.getSourceOperators().add(toPush);
			toPush.getDestinationOperators().add(newParent);
		}
		
		toPush.getSourceOperators().add(pushAbove);
		Vector<AbstractOperator> pushAboveParent = new Vector<AbstractOperator>();
		pushAboveParent.add(toPush);
		pushAbove.setDestinationOperators(pushAboveParent);
	}
	
	/**
	 * check for errors, also false if there is an error <br />
	 * if this function is used, selectionUnion needs to be used!
	 * @param toPush
	 * @param join
	 * @return false = left; true = right 
	 */
	private boolean checkJoinSide(GenericSelection toPush, EquiJoin join) {
		
		/*
		 * 
		 * TODO: check to wich side of a join the selecetion can be pushed 
		 * 
		 * 
		 */
		return true;
//		TokenTable toPushTable = toPush.getResult(0).getAttribute(0).getTable();
//		
//		if(toPushTable.equals(join.getLeftChild().getResult(0).getAttribute(0).getTable())){
//			return false;
//		}
//		else if(toPushTable.equals(join.getRightChild().getResult(0).getAttribute(0).getTable())){
//			return true;
//		}
//		else{
//			// new Error
//		}
//		
//		return false;
	}

//	public Vector<GenericSelection> getWaitToPush() {
//		return waitToPush;
//	}

}
