package org.xdb.doomdb;

import java.io.Serializable;
import java.util.Map;

import org.xdb.execute.operators.OperatorDesc;
import org.xdb.utils.Identifier;
import org.xdb.error.Error;

public class DoomDBPlanStatus implements Serializable {

	private static final long serialVersionUID = -547716826397599040L;

	private Boolean isFinished = false;
	private Map<Identifier, OperatorDesc> deployment;
	private Error err;
	
	public DoomDBPlanStatus(Boolean isFinished,
			Map<Identifier, OperatorDesc> deployment,
			Error err) {
		super();
		this.isFinished = isFinished;
		this.deployment = deployment;
		this.err = err;
	}

	//getters and setters
	public Boolean isFinished() {
		return isFinished;
	}

	public Map<Identifier, OperatorDesc> getDeployment() {
		return deployment;
	}

	public Error getError() {
		return err;
	}
}
