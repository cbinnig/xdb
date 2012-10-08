package org.xdb.funsql.optimize;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import org.xdb.funsql.compile.CompilePlan;
import org.xdb.utils.Identifier;

public class Optimizer {
	CompilePlan compilePlan;
	
	public Optimizer(CompilePlan compilePlan) {
		super();
		this.compilePlan = compilePlan;
	}

	public void optimize(){
		
		Iterator<Identifier> roots = compilePlan.getRoots().iterator();
		
		Vector<TreeWalkerSelSplit> treeWalkerSelSplitVector = new Vector<TreeWalkerSelSplit>();
		Vector<TreeWalkerSelPush> treeWalkerSelPushVector = new Vector<TreeWalkerSelPush>();
		Vector<TreeWalkerProjPush> treeWalkerProjPushVector = new Vector<TreeWalkerProjPush>();
		
		while(roots.hasNext()){
			Identifier id = roots.next();

			TreeWalkerSelSplit walkerSelSplit = new TreeWalkerSelSplit(compilePlan.getOperators(id));
			treeWalkerSelSplitVector.add(walkerSelSplit);
			TreeWalkerSelPush walkerSelPush = new TreeWalkerSelPush(compilePlan.getOperators(id));
			treeWalkerSelPushVector.add(walkerSelPush);
			TreeWalkerProjPush walkerProjPush = new TreeWalkerProjPush(compilePlan.getOperators(id));
			treeWalkerProjPushVector.add(walkerProjPush);
			
			walkerSelSplit.runWalker();
			walkerSelPush.runWalker();
			walkerProjPush.runWalker();
			
		}
		
		for(int i=0;i<treeWalkerSelSplitVector.size();i++){
			if(treeWalkerSelSplitVector.get(i).getOperatorsToPush().size() > 0){
				//TODO: if different push downs are allowed: combine if possible and push down further
			}
		}
		for(int i=0;i<treeWalkerSelPushVector.size();i++){
			if(treeWalkerSelPushVector.get(i).getOperatorsToPush().size() > 0){
				//TODO: if different push downs are allowed: combine if possible and push down further
			}
		}
		for(int i=0;i<treeWalkerProjPushVector.size();i++){
			if(treeWalkerProjPushVector.get(i).getOperatorsToPush().size() > 0){
				//TODO: if different push downs are allowed: combine if possible and push down further
			}
		}
		
	}

}
