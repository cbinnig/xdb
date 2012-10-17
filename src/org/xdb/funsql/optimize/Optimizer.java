package org.xdb.funsql.optimize;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.utils.Identifier;

public class Optimizer {
	CompilePlan compilePlan;
	
	public Optimizer(CompilePlan compilePlan) {
		super();
		this.compilePlan = compilePlan;
	}

	public void optimize(){
		
//		Vector<TreeWalkerSelSplit> treeWalkerSelSplitVector = new Vector<TreeWalkerSelSplit>();
		Vector<TreeWalkerSelectionSplit> treeWalkerSelectionSplitVector = new Vector<TreeWalkerSelectionSplit>();
		Vector<TreeWalkerSelectionPush> treeWalkerSelectionPushVector = new Vector<TreeWalkerSelectionPush>();
		Vector<TreeWalkerSelectionUnion> treeWalkerSelectionUnionVector = new Vector<TreeWalkerSelectionUnion>();
//		Vector<TreeWalkerProjPush> treeWalkerProjPushVector = new Vector<TreeWalkerProjPush>();


		Iterator<Identifier> roots = compilePlan.getRoots().iterator();
		
		/*
		 * Selection Push Down
		 */
		roots = compilePlan.getRoots().iterator();
		while(roots.hasNext()){
			Identifier id = roots.next();
			TreeWalkerSelectionSplit walkerSelectionSplit = new TreeWalkerSelectionSplit(compilePlan.getOperators(id));
			treeWalkerSelectionSplitVector.add(walkerSelectionSplit);
			walkerSelectionSplit.runWalker();
			
			TreeWalkerSelectionPush walkerSelectionPush = new TreeWalkerSelectionPush(compilePlan.getOperators(id));
			treeWalkerSelectionPushVector.add(walkerSelectionPush);
			walkerSelectionPush.runWalker();
			
			TreeWalkerSelectionUnion walkerSelectionUnion = new TreeWalkerSelectionUnion(compilePlan.getOperators(id));
			treeWalkerSelectionUnionVector.add(walkerSelectionUnion);
			walkerSelectionUnion.runWalker();			
		}
		
//		Vector<GenericSelection> waitToPush = new Vector<GenericSelection>();
//		for(TreeWalkerSelectionPush walker : treeWalkerSelectionPushVector){
//			if(!walker.getWaitToPush().isEmpty()){
//				waitToPush.addAll(walker.getWaitToPush());
//			}
//		}
//		if(!waitToPush.isEmpty()){
//			for(GenericSelection sel : waitToPush){
//				for(GenericSelection otherSel : waitToPush){
//					if(sel.equals(otherSel))
//						continue;
//					if(sel.getChild().equals(otherSel.getChild())){
//						combineSelections(sel, otherSel);
//					}
//				}
//			}
//		}
//		for(TreeWalkerSelectionPush walker : treeWalkerSelectionPushVector){
//			walker.continuePushDown();
//		}
		
		
		
//		findNewRoots();
		
		
		
		
		
		
		
	}


//		Vector<TreeWalkerSelSplit> treeWalkerSelSplitVector = new Vector<TreeWalkerSelSplit>();
//		Vector<TreeWalkerSelPush> treeWalkerSelPushVector = new Vector<TreeWalkerSelPush>();
//		Vector<TreeWalkerProjPush> treeWalkerProjPushVector = new Vector<TreeWalkerProjPush>();
//
//		/*
//		 * Selection Split Walker.
//		 */
//		Iterator<Identifier> roots = compilePlan.getRoots().iterator();
//		while(roots.hasNext()){
//			Identifier id = roots.next();
//
//			//new walker for every root.
//			TreeWalkerSelSplit walkerSelSplit = new TreeWalkerSelSplit(compilePlan.getOperators(id));
//			treeWalkerSelSplitVector.add(walkerSelSplit);
//			walkerSelSplit.runWalker();
//		}
//		//if walker was stopped, check why and if moving further is possible
//		for(int i=0;i<treeWalkerSelSplitVector.size();i++){
//			if(treeWalkerSelSplitVector.get(i).getOperatorsToPush().size() > 0){
//				/*
//				 * 
//				 * TODO: if different push downs are allowed: combine if possible and push down further
//				 * 
//				 */
//			}
//		}
//		//find new roots
//		findNewRoots();
//		
//		/*
//		 * Selection Push Down
//		 */
//		roots = compilePlan.getRoots().iterator();
//		while(roots.hasNext()){
//			Identifier id = roots.next();
//			
//			TreeWalkerSelPush walkerSelPush = new TreeWalkerSelPush(compilePlan.getOperators(id));
//			treeWalkerSelPushVector.add(walkerSelPush);
//			walkerSelPush.runWalker();
//		}
//		for(int i=0;i<treeWalkerSelPushVector.size();i++){
//			if(treeWalkerSelPushVector.get(i).getOperatorsToPush().size() > 0){
//				
//				Vector<AbstractOperator> toPush = new Vector<AbstractOperator>();
//				for(TreeWalkerSelPush walker : treeWalkerSelPushVector){
//					toPush.addAll(walker.getOperatorsToPush());
//				}
//				
//				/*
//				 * 
//				 * TODO: if different push downs are allowed: combine if possible and push down further
//				 * 
//				 */
//			}
//		}
//		findNewRoots();
//		
//		/*
//		 * Projection Push Down
//		 */
//		roots = compilePlan.getRoots().iterator();
//		while(roots.hasNext()){
//			Identifier id = roots.next();
//			
//			TreeWalkerProjPush walkerProjPush = new TreeWalkerProjPush(compilePlan.getOperators(id));
//			treeWalkerProjPushVector.add(walkerProjPush);
//			walkerProjPush.runWalker();
//		}
//		for(int i=0;i<treeWalkerProjPushVector.size();i++){
//			if(treeWalkerProjPushVector.get(i).getOperatorsToPush().size() > 0){
//				/*
//				 * 
//				 * TODO: if different push downs are allowed: combine if possible and push down further
//				 * 
//				 */
//			}
//		}
//		findNewRoots();
//		
//	}

	private void findNewRoots() {
		boolean changesDid = false;
		do{
			for(Identifier id : compilePlan.getRoots()){
				AbstractOperator op = compilePlan.getOperators(id);
				if(op.getDestinationOperators() != null){
					compilePlan.getRoots().remove(op.getOperatorId());
					for(AbstractOperator newRoot : op.getDestinationOperators()){
						if(!compilePlan.getRoots().contains(newRoot.getOperatorId())){
							compilePlan.getRoots().add(newRoot.getOperatorId());
							changesDid = true;
						}
					}
				}
			}
		}while(changesDid);
	}
	
	private void combineSelections(GenericSelection sel, GenericSelection otherSel) {
		
			if(sel.getResult(0).getAttribute(0).getTable() == otherSel.getResult(0).getAttribute(0).getTable()){
				
				
			}
			
		
	}
}
