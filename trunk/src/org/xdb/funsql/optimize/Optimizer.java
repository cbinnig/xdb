package org.xdb.funsql.optimize;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.utils.Identifier;

public class Optimizer {
	CompilePlan compilePlan;
	
	public Optimizer(CompilePlan compilePlan) {
		super();
		this.compilePlan = compilePlan;
	}

	public void optimize(){
		
		Vector<TreeWalkerSelectionSplit> treeWalkerSelectionSplitVector = new Vector<TreeWalkerSelectionSplit>();
		Vector<TreeWalkerSelectionPush> treeWalkerSelectionPushVector = new Vector<TreeWalkerSelectionPush>();
		Vector<TreeWalkerSelectionUnion> treeWalkerSelectionUnionVector = new Vector<TreeWalkerSelectionUnion>();

		Iterator<Identifier> roots = compilePlan.getRootIds().iterator();
		
		/*
		 * Selection Push Down
		 */
		roots = compilePlan.getRootIds().iterator();
		while(roots.hasNext()){
			Identifier id = roots.next();
			TreeWalkerSelectionSplit walkerSelectionSplit = new TreeWalkerSelectionSplit(compilePlan.getOperators(id));
			treeWalkerSelectionSplitVector.add(walkerSelectionSplit);
			walkerSelectionSplit.visit();
			
			TreeWalkerSelectionPush walkerSelectionPush = new TreeWalkerSelectionPush(compilePlan.getOperators(id));
			treeWalkerSelectionPushVector.add(walkerSelectionPush);
			walkerSelectionPush.visit();
			
			TreeWalkerSelectionUnion walkerSelectionUnion = new TreeWalkerSelectionUnion(compilePlan.getOperators(id));
			treeWalkerSelectionUnionVector.add(walkerSelectionUnion);
			walkerSelectionUnion.visit();			
		}
		
		boolean changesDid = true;
		while(changesDid){
			HashMap<Identifier, Vector<GenericSelection>> allWaitingSelMap = new HashMap<Identifier, Vector<GenericSelection>>();
			for(TreeWalkerSelectionPush walker : treeWalkerSelectionPushVector){
				allWaitingSelMap.putAll(walker.getWaitToPushMap());
			}
			if(!allWaitingSelMap.isEmpty()){
				boolean pushAllowed = checkPushDownOneSide(allWaitingSelMap);
				for(TreeWalkerSelectionPush walker : treeWalkerSelectionPushVector){
					changesDid = walker.pushFurther(pushAllowed);
				}
			}
		}
	}



	private boolean checkPushDownOneSide(HashMap<Identifier, Vector<GenericSelection>> allWaitingSelMap) {

		Set<Identifier> keys = allWaitingSelMap.keySet();
		for(Identifier key : keys){
			Vector<GenericSelection> selVec = allWaitingSelMap.get(key);
			boolean directionChanged = false;
			
			if(selVec.size() == 0)
				continue;
			GenericSelection sel = selVec.elementAt(0);
			
			EquiJoin join = (EquiJoin) compilePlan.getOperators(key);
			
			boolean lastDirection = TreeWalkerSelectionPush.checkJoinSide(sel, join);
			
			for(int i=1;i<selVec.size();i++){
				sel = selVec.elementAt(i);
				if(sel.getChild().getType() == EnumOperator.EQUI_JOIN){
					join = (EquiJoin) sel.getChild();
					
					if(TreeWalkerSelectionPush.checkJoinSide(sel, join) != lastDirection)
						directionChanged = true;
				}
				else{
					//Error
					return false;
				}
			}
			
			if(directionChanged)
				return false;
		}
	
		return true;
	}

	protected void findNewRoots() {
		boolean changesDid = false;
		do{
			for(Identifier id : compilePlan.getRootIds()){
				AbstractOperator op = compilePlan.getOperators(id);
				if(op.getDestinationOperators() != null){
					compilePlan.getRootIds().remove(op.getOperatorId());
					for(AbstractOperator newRoot : op.getDestinationOperators()){
						if(!compilePlan.getRootIds().contains(newRoot.getOperatorId())){
							compilePlan.getRootIds().add(newRoot.getOperatorId());
							changesDid = true;
						}
					}
				}
			}
		}while(changesDid);
	}
	
	protected void combineSelections(GenericSelection sel, GenericSelection otherSel) {
		
			if(sel.getResult(0).getAttribute(0).getTable() == otherSel.getResult(0).getAttribute(0).getTable()){
				
				
			}
			
		
	}
}
