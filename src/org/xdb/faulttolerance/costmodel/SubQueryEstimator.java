package org.xdb.faulttolerance.costmodel;

import java.util.List;

/**
 * @author Abdallah 
 * 
 * SubQueryEstimator: calculate the level runtime and 
 * the materialization cost for a certain level
 *
 */
public class SubQueryEstimator {
	
	public SubQueryEstimator() { 
		
 	}
    
	private double calculateLevelRuntinme(Level level){
		List<CostModelOperator> ops = level.getSubQquery(); 
		
		// sum-up all operators runtime in one level
		// TODO: using a cost model for estimate or 
		// using the database cost estimator. 
		double levelRunTime = 0.0;
		for (CostModelOperator op : ops) {
			levelRunTime += op.getOpRunTimeEstimate();
		}
		return levelRunTime;
	} 
	
	private double calculateLevelMaterializationTime(Level level){
		// the materialization cost in the level, it is the cost 
		// to materialize the last operator in the level. 
		// it is calculated by getting the cardinality 
		// from the estimator and then calculate the cost 
		// of writing each record on the memory depending 
		// in the memory write speed. (materialization in table memory)  
		int levelLength = level.getSubQquery().size();  
		double levelMaterializationRunTime = 0.0;
		levelMaterializationRunTime = 
				level.getSubQquery().get(levelLength-1).getOpMaterializationTimeEstimate();
		return levelMaterializationRunTime;
	}

	/**
	 * @return the levelRunTime
	 */
	public double getLevelRunTime(Level level) {
		return calculateLevelRuntinme(level) + calculateLevelMaterializationTime(level);
	} 
	
	public double getMaterializationTime(Level level){
		return calculateLevelMaterializationTime(level);
	}

}
