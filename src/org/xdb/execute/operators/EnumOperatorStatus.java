/**
 * 
 */
package org.xdb.execute.operators;

import org.xdb.Config;

/**
 * Operator status for executing in XDB
 * 
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
	
	/**
	 * returns true if operator has a failure
	 * @return
	 */
	public boolean isFailure() {
		switch (this) {
		case FAILED:
			return true;
		case ABORTED:
			return true;
		default:
			return false;
		}
	}
	
	/**
	 * returns true if operator has a non-repairable failure
	 * @return
	 */
	public boolean isNonRepairableFailure() {
		switch (this) {
		case FAILED:
			return true;
		default:
			return false;
		}
	}
	
	/**
	 * returns true if operator is alive
	 * @return
	 */
	public boolean isAlive() {
		switch (this) {
		case FAILED:
		case ABORTED:
			return false;
		default:
			return true;
		}
	}
	
	public boolean isFinished() {
		switch (this) {
		case FINISHED:
			return true;
		default:
			return false;
		}
	}
	
	/**
	 * returns status, which should be used for indicating runtime failures during execution
	 * @return
	 */
	public static EnumOperatorStatus getRuntimeFailure(){
		if(Config.QUERYTRACKER_MONITOR_ACTIVATED)
			return EnumOperatorStatus.ABORTED;
		else 
			return EnumOperatorStatus.FAILED;
	}
	
	/**
	 * Returns color code for status
	 * 
	 * @return
	 */
	public String getColor() {
		switch (this) {
		case DEPLOYED:
		case REDEPLOYED:
			return "GREY";
		case ABORTED:
		case FAILED:
			return "RED";
		case RUNNING:
			return "ORANGE";
		case FINISHED:
			return "GREEN";
		default:
			return "WHITE";
		}
	}
}
