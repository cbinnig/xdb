package org.xdb.faulttolerance.costmodel;

import java.util.ArrayList;
import java.util.List;

import org.xdb.utils.Identifier;

public class CostModelPlanMergeOpRTVisitor { 
	
	private List<Identifier> newNonMatOps = new ArrayList<Identifier>();

	/**
	 * @return the newNonMatOps
	 */
	public List<Identifier> getNewNonMatOps() {
		return newNonMatOps;
	}

	/**
	 * @param newNonMatOps the newNonMatOps to set
	 */
	public void setNewNonMatOps(List<Identifier> newNonMatOps) {
		this.newNonMatOps = newNonMatOps;
	} 
	

}
