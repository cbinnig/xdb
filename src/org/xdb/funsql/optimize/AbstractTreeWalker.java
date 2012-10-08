package org.xdb.funsql.optimize;

import java.util.Vector;

import org.xdb.funsql.compile.operator.AbstractOperator;

public abstract class AbstractTreeWalker {
	
	boolean changesDidLastRun = false;
	Vector<AbstractOperator> operatorsToPush = new Vector<AbstractOperator>();
	
	/**
	 * runs walker as often as needed so there are no further changes possible.
	 */
	public void runWalker(){	};
	
	/** 
	 * @return all operators that may could pushed down, but need to wait for other walkers to unify the push down. 
	 */
	public Vector<AbstractOperator> getOperatorsToPush(){
		return operatorsToPush;
	}
	
	/**
	 * adds given operator to the list of possible pushdowns after all walkers reached the stopping point 
	 * (e.g. a join operator)
	 * @param op
	 */
	void addOperatorToPush(AbstractOperator op){
		operatorsToPush.add(op);
	}
	
	/**
	 * pushes a single Operator toPush under the single operator pushedOver. This is only working with Operators
	 * which are located directly above each other
	 * @param toPush
	 * @param pushedOver
	 */
	void pushDown(AbstractOperator toPush, AbstractOperator pushedOver){
		
		
		Vector<AbstractOperator> destinations = toPush.getDestinationOperators();
		if(destinations.size() >= 1){
			for(AbstractOperator dest : destinations){
				dest.getSourceOperators().remove(toPush);
				dest.getSourceOperators().add(pushedOver);
			}
		}
		Vector<AbstractOperator> sources = pushedOver.getSourceOperators();
		if(sources.size() >= 1){
			for(AbstractOperator src : sources){
				src.getDestinationOperators().remove(pushedOver);
				src.getDestinationOperators().add(toPush);
			}
		}
		
		Vector<AbstractOperator> newRootSource = pushedOver.getSourceOperators();
		
		pushedOver.setSourceOperators(pushedOver.getDestinationOperators());
		pushedOver.setDestinationOperators(toPush.getDestinationOperators());
		toPush.setDestinationOperators(toPush.getSourceOperators());
		toPush.setSourceOperators(newRootSource);
		
	};
	
	/**
	 * push a single Operator under several Operators. Only works with Operators directly above each other.
	 * @param toPush
	 * @param pushedOver
	 */
	void pushDown(AbstractOperator toPush, Vector<AbstractOperator> pushedOver){
		
		Vector<AbstractOperator> destinations = toPush.getDestinationOperators();
		if(destinations.size() >= 1){
			for(AbstractOperator dest : destinations){
				dest.getSourceOperators().remove(toPush);
				for(AbstractOperator singlePushedOver : pushedOver)
					dest.getSourceOperators().add(singlePushedOver);
			}
		}
		for(AbstractOperator singlePushedOver : pushedOver){
			
			//TODO: copy operator so toPushInsert can be placed into two or more branches
			AbstractOperator toPushInsert = toPush;

			Vector<AbstractOperator> sources = singlePushedOver.getSourceOperators();
			if(sources.size() >= 1){
				for(AbstractOperator src : sources){
					src.getDestinationOperators().remove(singlePushedOver);
					src.getDestinationOperators().add(toPushInsert);
				}
			}
			

			singlePushedOver.setDestinationOperators(toPush.getDestinationOperators());
			Vector<AbstractOperator> vec = new Vector<AbstractOperator>();
			vec.add(toPushInsert);
			singlePushedOver.setSourceOperators(vec);
			
			//TODO: einstellungen des neuen toPushInserts bzw der "E" in der skizze.
			
			
			
		}

		
		

	}
	

}
