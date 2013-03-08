package org.xdb.execute;

import org.xdb.server.AbstractNodeDesc;

/**
 * Describes the properties of a ComputeNode (i.e., URL, port and slots)
 * 
 * @author cbinnig
 * 
 */
public class ComputeNodeDesc extends AbstractNodeDesc {

	private static final long serialVersionUID = 995523462711933305L;

	// URL of compute node
	private ComputeNodeSlot slotDesc;

	// constructors
	public ComputeNodeDesc(ComputeNodeSlot slotDesc, int slots) {
		super(slotDesc.getHost(), slots);
		this.slotDesc = slotDesc;
	}
	
	public ComputeNodeDesc(String url, int port, int slots) {
		this(new ComputeNodeSlot(url, port), slots);
	}

	// getters and setters
	public ComputeNodeSlot getSlotDesc() {
		return slotDesc;
	}

	// methods
	@Override
	public String toString() {
		StringBuffer value = new StringBuffer();
		value.append("(");
		value.append(this.slotDesc);
		value.append(",");
		value.append(this.slots);
		value.append(")");
		return value.toString();
	}

	@Override
	public int hashCode() {
		return this.slotDesc.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		ComputeNodeDesc desc = (ComputeNodeDesc) o;
		if (desc.slotDesc.equals(this.slotDesc))
			return true;
		return false;
	}
}