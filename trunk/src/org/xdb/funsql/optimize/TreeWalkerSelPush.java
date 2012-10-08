package org.xdb.funsql.optimize;

import java.util.Vector;

import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EnumPushDown;

public class TreeWalkerSelPush extends AbstractTreeWalker {
	
	AbstractOperator root;
	
	TreeWalkerSelPush(AbstractOperator root){
		this.root = root;
	}

	@Override
	public void runWalker() {
		
		Vector<AbstractOperator> sources = root.getSourceOperators();
		
		if(sources.size() == 1){
			AbstractOperator sourceOperator = sources.get(0); 
			if(sourceOperator.isPushDownAllowed(EnumPushDown.SELECTION_PUSHDOWN)){
				
				pushDown(root, sourceOperator);
//				Vector<AbstractOperator> newRootSource = sourceOperator.getSourceOperators();
//				sourceOperator.setSourceOperators(sourceOperator.getDestinationOperators());
//				sourceOperator.setDestinationOperators(root.getDestinationOperators());
//				root.setDestinationOperators(root.getSourceOperators());
//				root.setSourceOperators(newRootSource);
			}
		}
		else{
			//TODO: stop pushdown and check if all sources allow pushdown
			int countPushDownAble = 0;
			for(AbstractOperator operator : sources) {
				if(operator.isPushDownAllowed(EnumPushDown.SELECTION_PUSHDOWN)){
					countPushDownAble++;
				}
			}
			if(countPushDownAble == sources.size()){
				pushDown(root, sources);
				//TODO: organize pushdown to multiple sources
				// ansatz in abstractTreeWalker... fertig machen!!!!
			}
		}
		
		

	}

}
