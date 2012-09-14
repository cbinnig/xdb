package org.xdb.execute;

import java.io.Serializable;

import org.xdb.Config;

public class ComputeNodeDesc implements Serializable {

	private static final long serialVersionUID = 995523462711933305L;

	// URL of compute node
	private String url;

	// slots of compute node
	private Integer slots;

	// constructors
	public ComputeNodeDesc(String url) {
		this.url = url;
		this.slots = Config.COMPUTE_SLOTS;
	}

	public ComputeNodeDesc(String url, Integer slots) {
		this.url = url;
		this.slots = slots;
	}

	// getters and setters
	public String getUrl() {
		return url;
	}

	public Integer getSlots() {
		return slots;
	}
	
	// methods
	@Override 
	public String toString(){
		StringBuffer value = new StringBuffer();
		value.append("(");
		value.append(this.url);
		value.append(",");
		value.append(this.slots);
		value.append(")");
		return value.toString();
	}
}
