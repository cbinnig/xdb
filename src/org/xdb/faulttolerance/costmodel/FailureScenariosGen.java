package org.xdb.faulttolerance.costmodel;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FailureScenariosGen { 

	// list of materialization configuration 
	private List<MaterializedPlan> matPlansList = new ArrayList<MaterializedPlan>();

	public FailureScenariosGen(List<MaterializedPlan> matPlansList) { 
		this.matPlansList = matPlansList; 

	}

	/**
	 * @return the matPlansList
	 */
	public List<MaterializedPlan> getMatPlansList() {
		return matPlansList;
	}

	/**
	 * @param matPlansList the matPlansList to set
	 */
	public void setMatPlansList(List<MaterializedPlan> matPlansList) {
		this.matPlansList = matPlansList;
	}

	// For each materialization configuration, calcualte the 
	// the average wasted time
	public void calculateAverageWastedTimePerMatConf() {

		int levelInPlan = 0;  
		int numberOfFailureScenario = 0; 
		char[] copyArray;  
		List<Level> matLevels = new ArrayList<Level>();
		for (MaterializedPlan materializedPlan : matPlansList) { 
			double averageWastedTime = 0.0; 
			double runTimeWithoutFailure = 0.0; 
			double materializationTime = 0.0; 
			matLevels = materializedPlan.getMateriliazedPlan(); 
			for (Level level : matLevels) {
				runTimeWithoutFailure += level.getLevelRuntimeEstimate(); 
				materializationTime += level.getMaterializationRuntimeestimate();
				
			} 
			materializedPlan.setRunTimeWithoutFailure(runTimeWithoutFailure);
			materializedPlan.setMaterializationTime(materializationTime);
			levelInPlan = matLevels.size();  
			numberOfFailureScenario = (int) Math.pow(2, levelInPlan);
			copyArray = new char[levelInPlan];
			// initialize the array
			for(int i=0; i<copyArray.length; i++){
				copyArray[i] = '0';
			}   
			// start from one! 0000 is excluded.
			for(int i=1; i<numberOfFailureScenario; i++){  
				char[] binaryRep = String.format(Integer.toBinaryString(i)).toCharArray();  
				System.arraycopy(binaryRep, 0, copyArray, 
						copyArray.length -binaryRep.length , binaryRep.length);  
				BigDecimal failureScenarioPrability = new BigDecimal(1.0); 
				double wastedTime = 0.0; 
				double dominatorTime = 0.0; 
				double numinatorTime = 0.0;  
				double temp = 0.0; 
				for(int j=0; j<copyArray.length; j++){
					dominatorTime += matLevels.get(j).getLevelRuntimeEstimate();
					if(copyArray[j] == '1'){
						failureScenarioPrability = failureScenarioPrability.multiply(matLevels.get(j).getLevelFailureProbability(), MathContext.DECIMAL128);
						numinatorTime += matLevels.get(j).getLevelRuntimeEstimate();  
					} else { 
						failureScenarioPrability = failureScenarioPrability.multiply(matLevels.get(j).getLevelSuccessProbability(), MathContext.DECIMAL128);
						temp += Math.pow(numinatorTime, 2);
						numinatorTime = 0.0;
					}
				} 
				if(copyArray[copyArray.length-1] == '1'){
					temp += Math.pow(numinatorTime, 2);
				} 
				numinatorTime = temp; 
				wastedTime = 0.5*numinatorTime/dominatorTime; 
				averageWastedTime += failureScenarioPrability.multiply(new BigDecimal(wastedTime)).longValue();
			} 
			materializedPlan.setAverageWastedTime(averageWastedTime);
		}
	} 

	public void calculateRuntTimeForMatConfs(){

		double runTime = 0.0;
		double T;
		double W;
		BigDecimal F;
		int r;
		for (MaterializedPlan materializedPlan : matPlansList) {  
			T = materializedPlan.getRunTimeWithoutFailure(); 
			W = materializedPlan.getAverageWastedTime();
			F = materializedPlan.getFailureProbability(); 
			r = materializedPlan.getReattempts(); 

			// runtime model  
			runTime = T + W*(new BigDecimal(1).subtract(F.pow(r+1, MathContext.DECIMAL128), MathContext.DECIMAL128)).doubleValue()/
					(new BigDecimal(1).subtract(F,MathContext.DECIMAL128)).doubleValue() - W; 
			materializedPlan.setRunTime(runTime);	 
		} 
		Collections.sort(matPlansList); 
		
		for (MaterializedPlan materializedPlan : matPlansList) {  

		     System.out.println(materializedPlan.getFailureProbability());  
		     //System.out.println(materializedPlan.getRunTime()); 

		}

		// the best mat conf
		MaterializedPlan materializedPlan = matPlansList.get(0); 
		List<Level> levels = materializedPlan.getMateriliazedPlan(); 
	    System.out.println("These/this operators should be materialized: ");
		for (Level level : levels) {
			System.out.println("Op: "+level.getSubQquery().get(level.getSubQquery().size()-1).getId());
		}
		//System.out.println("Runtime: "+materializedPlan.getRunTime() +", Re-attempts: "+materializedPlan.getReattempts() + 
			//	", Average Wasted time: "+materializedPlan.getAverageWastedTime());  
		
		
	}

}
