package org.xdb.doomdb;

import java.io.Serializable;
import java.util.Map;

import org.xdb.execute.operators.OperatorDesc;
import org.xdb.utils.Identifier;

public class DoomDBPlanStatus implements Serializable {

	private static final long serialVersionUID = -547716826397599040L;

	private Boolean isFinished = false;
	private Map<Identifier, OperatorDesc> deployment;
	
	public DoomDBPlanStatus(Boolean isFinished,
			Map<Identifier, OperatorDesc> deployment) {
		super();
		this.isFinished = isFinished;
		this.deployment = deployment;
	}

	//getters and setters
	public Boolean isFinished() {
		return isFinished;
	}

	public Map<Identifier, OperatorDesc> getDeployment() {
		return deployment;
	}
}
