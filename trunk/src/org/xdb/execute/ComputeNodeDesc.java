package org.xdb.execute;

import java.io.Serializable;

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
	private int slots;

	// constructors
	public ComputeNodeDesc(ComputeNodeSlot url, int slots) {
		this.url = url;
		this.slots = slots;
	}

	// getters and setters
	public ComputeNodeSlot getUrl() {
		return url;
	}

	public int getSlots() {
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
	
	@Override
	public int hashCode(){
		return this.url.hashCode();
	}
	
	@Override
	public boolean equals(Object o){
		ComputeNodeDesc desc = (ComputeNodeDesc)o;
		if(desc.url.equals(this.url))
			return true;
		return false;
	}
}