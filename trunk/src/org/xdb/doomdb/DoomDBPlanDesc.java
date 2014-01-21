package org.xdb.doomdb;

import java.io.Serializable;

import org.xdb.utils.Identifier;

public class DoomDBPlanDesc implements Serializable{
	private static final long serialVersionUID = -1060150702820680212L;
	
	private Identifier compilePlanId;
	private Identifier qtrackerPlanId;
	
	public DoomDBPlanDesc(Identifier compilePlanId, Identifier qtrackerPlanId) {
		this.compilePlanId = compilePlanId;
		this.qtrackerPlanId = qtrackerPlanId;
	}
	
	public Identifier getCompilePlanId() {
		return compilePlanId;
	}

	public Identifier getQtrackerPlanId() {
		return qtrackerPlanId;
	}
	
	@Override
	public String toString(){
		return this.compilePlanId.toString();
	}
}
