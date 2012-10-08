package org.xdb.funsql.optimize;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.utils.Identifier;

public class TreeWalker {
	CompilePlan compilePlan;

	TreeWalker(CompilePlan compilePlan){
		this.compilePlan = compilePlan;
	}
	
	public TreeWalker(AbstractOperator operators) {
		// TODO Auto-generated constructor stub
	}

	public void runWalker(){
		//get all operations and root-operation-ids
		Collection<Identifier> roots = compilePlan.getRoots();
		Collection<AbstractOperator> operations = compilePlan.getOperators();
		
//		ArrayList<AbstractOperator> arrOp = new ArrayList<AbstractOperator>(operations);
//		HashSet<AbstractOperator> hashOp = new HashSet<AbstractOperator>(operations);
		
		//throw warning if no operation is found
		if(roots.isEmpty() || operations.isEmpty()){
			/*
			 * TODO: Give Warning! Break!
			 * if both empty: no compilePlan available
			 * if only one empty: there is some data missing
			 */
		}
		
		//get iterators for access to IDs and operations
		Iterator<Identifier> iteratorRoot = roots.iterator();
		Iterator<AbstractOperator> iteratorOp = operations.iterator();

		if(iteratorRoot.hasNext()){
			Identifier tempID = iteratorRoot.next();
			
			AbstractOperator tempOperation = null;
			while(iteratorOp.hasNext()){
				tempOperation = iteratorOp.next();
				if(tempOperation.getOperatorId().equals(tempID))
					break;
			}
			
			if(isPushDownPossible(tempOperation.getType())){
				pushDownOperator(tempOperation);
			}
			
		}
		
		//...
	}

	private void pushDownOperator(AbstractOperator operator) {
		if(!isPushDownPossible(operator.getType()))
			return;
		
		Vector<AbstractOperator> sourceOp = operator.getSourceOperators();
		
		//...
		
	}

	private boolean isPushDownPossible(EnumOperator type) {
		switch(type){
			case EQUI_SELECTION:
			case SIMPLE_PROJECTION:
				return true;
			default:
				return false;
		}
	}

	public CompilePlan getCompilePlan() {
		return compilePlan;
	}

	public void setCompilePlan(CompilePlan compilePlan) {
		this.compilePlan = compilePlan;
	}
	
}
