package org.xdb.funsql.optimize;


import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.operator.AbstractTreeVisitor;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EnumPushDown;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenTable;
import org.xdb.utils.Identifier;

public class TreeWalkerSelectionPush extends AbstractTreeVisitor { //implements ITreeVisitor {
	
	GenericSelection tempSel = null;
	HashMap<Identifier, Vector<GenericSelection>> waitToPushMap = new HashMap<Identifier, Vector<GenericSelection>>();

	public TreeWalkerSelectionPush(AbstractOperator root) {
		super(root);
	}
	
	@Override
	public Error visitEquiJoin(EquiJoin ej) {

		if(tempSel != null){
			if(ej.isPushDownAllowed(EnumPushDown.SELECTION_PUSHDOWN)){
				/*
				 * TODO: check auf welche seite zu pushen und dann nur dahin
				 */
				visit(ej.getLeftChild());
				visit(ej.getRightChild());
			}
			else if(ej.isPushDownAllowed(EnumPushDown.STOP_PUSHDOWN)){
				
				if(waitToPushMap.containsKey(ej.getOperatorId())){
					Vector<GenericSelection> tempVector = waitToPushMap.get(ej.getOperatorId());
					tempVector.add(tempSel);
					waitToPushMap.put(ej.getOperatorId(), tempVector);
				}
				else{
					Vector<GenericSelection> tempVector = new Vector<GenericSelection>();
					tempVector.add(tempSel);
					waitToPushMap.put(ej.getOperatorId(), tempVector);
				}
					
				tempSel = null;
				visit(thisRoot);
				
			}
			else{
				pushDown(tempSel, ej);
			}
		}
		else{
			/*
			 * TODO: new walker for every side?
			 * is this possible or should we create an error here?
			 */
			visit(ej.getLeftChild());
			visit(ej.getRightChild());
		}
		return new Error();
		
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		if(tempSel == null){
			
			boolean contains = false;
			for(Identifier id : waitToPushMap.keySet()){
				if(waitToPushMap.get(id).contains(gs)){
					contains=true;
					visit(gs.getChild());
					break;
				}
			}

			if(!contains){
				tempSel = gs;
				visit(gs.getChild());
			}
		}
		else{
			if(gs.isPushDownAllowed(EnumPushDown.SELECTION_PUSHDOWN)){
				visit(gs.getChild());
			}
			else{
				pushDown(tempSel, gs);
			}
		}
		return new Error();
	}

	@Override
	public Error visitFunctionCall(FunctionCall fc) {
		return new Error();

	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
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
		return new Error();
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		
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
		return new Error();

	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		if(tempSel != null)
			pushDown(tempSel,to);
		return new Error();
		
	}
	
	private void pushDown(GenericSelection toPush, AbstractOperator pushAbove) {
		
		TokenTable table = toPush.getChild().getResult(0).getAttribute(0).getTable();
		
		AbstractOperator parentRename = toPush.getDestinationOperators().get(0);
		
		switch(parentRename.getType()){
		case EQUI_JOIN:
			if(((EquiJoin) parentRename).getLeftChild().equals(pushAbove)){
				((EquiJoin) parentRename).getLeftTokenAttribute().setTable(table);
			}
			else{
				((EquiJoin) parentRename).getRightTokenAttribute().setTable(table);
			}
			break;
		case GENERIC_SELECTION:
			for(TokenAttribute att : ((GenericSelection) parentRename).getPredicate().getAttributes()){
				att.setTable(table);
			}
			break;
		case GENERIC_AGGREGATION:
			for(AbstractExpression exp : ((GenericAggregation) parentRename).getGroupExpressions()){
				for(TokenAttribute att : exp.getAttributes()){
					att.setTable(table);
				}
			}
			break;
		case GENERIC_PROJECTION:
			for(AbstractExpression exp :((GenericProjection) parentRename).getExpressions()){
				for(TokenAttribute att : exp.getAttributes()){
					att.setTable(table);
				}
			}
			break;
		}
		

		TokenTable table2 = pushAbove.getResult(0).getAttribute(0).getTable();
		for(TokenAttribute att : toPush.getPredicate().getAttributes()){
			att.setTable(table2);
		}
		
		
		for(AbstractOperator newDest : pushAbove.getDestinationOperators()){
			switch(newDest.getType()){
			case EQUI_JOIN:
				if(((EquiJoin) newDest).getLeftChild().equals(pushAbove)){
					((EquiJoin) newDest).getLeftTokenAttribute().setTable(toPush.getOperatorId().toString());
				}
				else{
					((EquiJoin) newDest).getRightTokenAttribute().setTable(toPush.getOperatorId().toString());
				}
				break;
			case GENERIC_SELECTION:
				for(TokenAttribute att : ((GenericSelection) newDest).getPredicate().getAttributes()){
					att.setTable(toPush.getOperatorId().toString());
				}
				break;
			case GENERIC_AGGREGATION:
				for(AbstractExpression exp : ((GenericAggregation) newDest).getGroupExpressions()){
					for(TokenAttribute att : exp.getAttributes()){
						att.setTable(toPush.getOperatorId().toString());
					}
				}
				break;
			case GENERIC_PROJECTION:
				for(AbstractExpression exp :((GenericProjection) newDest).getExpressions()){
					for(TokenAttribute att : exp.getAttributes()){
						att.setTable(toPush.getOperatorId().toString());
					}
				}
				break;
			}
			
		}
		
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
	static public boolean checkJoinSide(GenericSelection toPush, EquiJoin join) {
		
		ResultDesc resDescToPush = toPush.getResult(0);
		ResultDesc resDescJoin = join.getResult(0);
		
		for(int i=0;i<resDescJoin.getNumAttributes();i++){
			
			boolean attributeFound = false;
			for(TokenAttribute att :  toPush.getPredicate().getAttributes()){
				if(att.getName().equals(resDescJoin.getAttribute(i).getName())){
					attributeFound=true;
				}
			}

			if(attributeFound){
				if(i < join.getLeftChild().getResult(0).getNumAttributes()){
					Vector<TokenAttribute> toDeleteAtt = new Vector<TokenAttribute>();
					for(TokenAttribute att : resDescToPush.getAttributes()){
						for(TokenAttribute attRight : join.getRightChild().getResult(0).getAttributes()){
							if(att.getName().equals(attRight.getName())){
								toDeleteAtt.add(att);
							}
						}
					}
					for(int y=0; y<toDeleteAtt.size(); y++){
						if(resDescToPush.getAttributes().contains(toDeleteAtt.get(y))){
							int idx = resDescToPush.getAttributes().indexOf(toDeleteAtt.get(y));
							resDescToPush.getAttributes().remove(toDeleteAtt.get(y));
							resDescToPush.getTypes().remove(idx);
						}
					}

//					resDescToPush.getAttributes().removeAll(toDeleteAtt);
	
					return false;
				}
				else{
					Vector<TokenAttribute> toDelete = new Vector<TokenAttribute>();
					for(TokenAttribute att : resDescToPush.getAttributes()){
						for(TokenAttribute attLeft : join.getLeftChild().getResult(0).getAttributes()){
							
//							System.out.println(toPush.getOperatorId() + " : " + att.getName()+" - "+attLeft.getName());
							
							if(att.getName().equals(attLeft.getName())){
								toDelete.add(att);
							}
						}
						
					}
					for(int y=0; y<toDelete.size(); y++){
						if(resDescToPush.getAttributes().contains(toDelete.get(y))){
							int idx = resDescToPush.getAttributes().indexOf(toDelete.get(y));
							resDescToPush.getAttributes().remove(toDelete.get(y));
							resDescToPush.getTypes().remove(idx);
						}
					}
//					resDescToPush.getAttributes().removeAll(toDelete);
					
					return true;
				}
			}
		}
		
		return false;
	}

	public boolean pushFurther(boolean pushFurtherAllowed){
		
		if(waitToPushMap.isEmpty())
			return false;
		
		boolean changesDid = false;
		
		Set<Identifier> keys = waitToPushMap.keySet();
		for(Identifier key : keys){
			Vector<GenericSelection> selVec = waitToPushMap.get(key);

			EquiJoin join = (EquiJoin) getJoin(thisRoot, key);
			if(join == null){
				//Error
				return false;
			}
			
			if(pushFurtherAllowed){
				Vector<GenericSelection> toDelete = new Vector<GenericSelection>();
				for(GenericSelection pushSel : selVec){
					tempSel = pushSel;
					toDelete.add(tempSel);
					
					if(checkJoinSide(tempSel, join))
						visit(join.getRightChild());
					else
						visit(join.getLeftChild());
					
					changesDid = true;
				}
				selVec.removeAll(toDelete);
			}
			else{
				for(GenericSelection pushSel : selVec){
					pushDown(pushSel, join);
				}
			}
		}
	
		return changesDid;
	}
	
	public EquiJoin getJoin(AbstractOperator root, Identifier id){
		if(root.getOperatorId().equals(id))
			return (EquiJoin) root;
		else{
			for(int i=0;i<root.getSourceOperators().size();i++){
				AbstractOperator op = getJoin(root.getSourceOperators().elementAt(i), id);
				if(op.getOperatorId().equals(id))
					return (EquiJoin) op;
			}
		}

		return null;
	}

	public HashMap<Identifier, Vector<GenericSelection>> getWaitToPushMap() {
		return waitToPushMap;
	}

}
