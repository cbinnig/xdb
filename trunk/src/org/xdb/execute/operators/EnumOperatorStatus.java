/**
 * 
 */
package org.xdb.execute.operators;

import org.xdb.Config;

/**
 * @author Abdallah
 * 
 */
public enum EnumOperatorStatus {
	INIT, //operator is only initialized
	DEPLOYED, // Operator is deployed without failure (First Deployment)
	REDEPLOYED, // Operator is re-deployed after failure.
	RUNNING, // Operator is executing
	FINISHED, // Operator is finished
	ABORTED, // Operator is failed and can not recovered
	FAILED; // Operator is failed and can not be recovered
	
	public boolean isRepairableFailure() {
		switch (this) {
		case ABORTED:
			return true;
		default:
			return false;
		}
	}
	
	public boolean isNonRepairableFailure() {
		switch (this) {
		case FAILED:
			return true;
		default:
			return false;
		}
	}
	
	
	public boolean isAlive() {
		switch (this) {
		case FAILED:
		case ABORTED:
			return false;
		default:
			return true;
		}
	}
	
	public static EnumOperatorStatus getRuntimeFailure(){
		if(Config.QUERYTRACKER_MONITOR_ACTIVATED)
			return EnumOperatorStatus.ABORTED;
		else 
			return EnumOperatorStatus.FAILED;
	}
}
