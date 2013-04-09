package org.xdb.funsql.parallelize;

import java.io.Serializable;

public enum EnumPartitionType implements Serializable{
	ROUND_ROBIN,
	HASH,
	NO_PARTITION
}
