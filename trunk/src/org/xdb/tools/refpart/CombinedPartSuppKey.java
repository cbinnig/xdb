package org.xdb.tools.refpart;

public class CombinedPartSuppKey {
	private long partKey;
	private long suppKey;

	public CombinedPartSuppKey(long partKey, long suppKey) {
		super();
		this.partKey = partKey;
		this.suppKey = suppKey;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CombinedPartSuppKey other = (CombinedPartSuppKey) obj;
		if (partKey != other.partKey)
			return false;
		if (suppKey != other.suppKey)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (partKey ^ (partKey >>> 32));
		result = prime * result + (int) (suppKey ^ (suppKey >>> 32));
		return result;
	}

}
