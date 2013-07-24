/**
 * 
 */
package org.xdb.execute.signals;

import java.io.Serializable;

import org.xdb.utils.Identifier;

/** 
 * Signal which is send to compute node in order 
 * to kill the failed execute operator. 
 * 
 * @author Abdallah
 *
 */
public class KillSignal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	
	private Identifier failedExecOpId;
    
	
	public KillSignal (Identifier failedExecOpId){
		this.setFailedExecOpId(failedExecOpId);
	}
	/**
	 * @return the failedExecOpId
	 */
	public Identifier getFailedExecOpId() {
		return failedExecOpId;
	}

	/**
	 * @param failedExecOpId the failedExecOpId to set
	 */
	public void setFailedExecOpId(Identifier failedExecOpId) {
		this.failedExecOpId = failedExecOpId;
	}

}
