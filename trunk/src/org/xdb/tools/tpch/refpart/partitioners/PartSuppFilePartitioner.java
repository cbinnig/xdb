package org.xdb.tools.tpch.refpart.partitioners;

import java.util.List;

import org.xdb.logging.XDBLog;
import org.xdb.tools.tpch.refpart.AbstractFilePartitioner;
import org.xdb.tools.tpch.refpart.CombinedPartSuppKey;
import org.xdb.tools.tpch.refpart.DefRefPart.WORK_MODE;

public class PartSuppFilePartitioner extends AbstractFilePartitioner {

	public PartSuppFilePartitioner(int partitionCount, String inputDir, String outputDir, String tempDir,
			String fileName) {
		super(WORK_MODE.FILE, partitionCount, inputDir, outputDir, tempDir, fileName, "partsupp");

		this.logger = XDBLog.getLogger("PartSupp File Partitioner");
		columnCount = 5;
		this.inputHashFile = tempDir + "lineitemPartSupp";
	}

	@Override
	protected String buildSQLRow(List<String> fields) {
		StringBuilder rv = new StringBuilder();
		rv.append("(");
		rv.append(fields.get(0));
		rv.append(",");
		rv.append(fields.get(1));
		rv.append(",");
		rv.append(fields.get(2));
		rv.append(",");
		rv.append(fields.get(3));
		rv.append(",'");
		rv.append(fields.get(4));
		rv.append("')");
		return rv.toString();
	}

	@Override
	protected void defineReferenceVisitors() {

	}

	@Override
	protected int getHashValue(List<String> fields) {
		CombinedPartSuppKey cpsk = new CombinedPartSuppKey(Long.valueOf(fields.get(1)), Long.valueOf(fields.get(2)));
		return cpsk.hashCode();
	}

	@Override
	protected String getSQLEnd() {
		return "/*!40000 alter table `partsupp` enable keys */;\n" + "unlock tables;";
	}

	@Override
	protected String getSQLStart() {
		return "drop table if exists `partsupp`;\n"
				+ "/*!40101 set @saved_cs_client     = @@character_set_client */;\n"
				+ "/*!40101 set character_set_client = utf8 */;\n" + "create table `partsupp` (\n"
				+ "  `ps_partkey` int(11) not null,\n" + "  `ps_suppkey` int(11) not null,\n"
				+ "  `ps_availqty` int(11) not null,\n" + "  `ps_supplycost` decimal(15,2) not null,\n"
				+ "  `ps_comment` varchar(199) not null,\n" + "  primary key (`ps_partkey`,`ps_suppkey`)\n"
				+ ") engine=innodb default charset=latin1;\n"
				+ "/*!40101 set character_set_client = @saved_cs_client */;\n" + "\n" + "--\n"
				+ "-- dumping data for table `partsupp`\n" + "--\n" + "\n" + "lock tables `partsupp` write;\n"
				+ "/*!40000 alter table `partsupp` disable keys */;\n" + "insert into `partsupp` values";
	}

}
