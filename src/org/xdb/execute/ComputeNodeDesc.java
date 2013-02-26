package org.xdb.execute;

import java.io.Serializable;

import org.xdb.Config;

/**
 * Compute node description 
 * @author cbinnig
 *
 */
public class ComputeNodeDesc implements Serializable {

	private static final long serialVersionUID = 995523462711933305L;

	// URL of compute node
	private ComputeNodeSlot url;

	// slots of compute node
	private Integer slots;

	// constructors
	public ComputeNodeDesc(ComputeNodeSlot url) {
		this.url = url;
		this.slots = Config.COMPUTE_SLOTS;
	}

	// getters and setters
	public ComputeNodeSlot getUrl() {
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