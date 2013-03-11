package org.xdb.tools.refpart;

public final class DefRefPart {
	public static enum FILE_MODE {
		HASH, SQL
	}

	public static enum WORK_MODE {
		FILE, HASH, REPLICATE
	}
	public static final String COLUMN_DELIMITER = "|";
	public static final Character FILENAME_DELIMITER = '.';
	public static final String HASH_FILE_EXTENSTION = "hsh";
	public static final int MAX_INSERT_COUNT = 2500;
	public static final int PARTITION_COUNT = 10;
	public static final String ROOT_DIR = "";
	public static final String SQL_FILE_EXTENSTION = "sql";
	public static final String TPCH_FILE_EXTENSTION = "tbl";;
}
