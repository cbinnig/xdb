package org.xdb.tools.refpart.partitioners;

import java.util.List;

import org.xdb.logging.XDBLog;
import org.xdb.tools.refpart.AbstractFilePartitioner;
import org.xdb.tools.refpart.DefRefPart.WORK_MODE;

public class PartFilePartitioner extends AbstractFilePartitioner {

	public PartFilePartitioner(int partitionCount, String inputDir, String outputDir, String tempDir, String fileName) {
		super(WORK_MODE.FILE, partitionCount, inputDir, outputDir, tempDir, fileName, "part");

		this.logger = XDBLog.getLogger("Part File Partitioner");
		columnCount = 9;
		this.inputHashFile = tempDir + "lineitemPart";
	}

	@Override
	protected String buildSQLRow(List<String> fields) {
		StringBuilder rv = new StringBuilder();
		rv.append("(");
		rv.append(fields.get(0));
		rv.append(",'");
		rv.append(fields.get(1));
		rv.append("','");
		rv.append(fields.get(2));
		rv.append("','");
		rv.append(fields.get(3));
		rv.append("','");
		rv.append(fields.get(4));
		rv.append("',");
		rv.append(fields.get(5));
		rv.append(",'");
		rv.append(fields.get(6));
		rv.append("',");
		rv.append(fields.get(7));
		rv.append(",'");
		rv.append(fields.get(8));
		rv.append("')");
		return rv.toString();
	}

	@Override
	protected void defineReferenceVisitors() {

	}

	@Override
	protected String getSQLEnd() {
		return "/*!40000 alter table `part` enable keys */;\n" + "unlock tables;";
	}

	@Override
	protected String getSQLStart() {
		return "drop table if exists `part`;\n" + "/*!40101 set @saved_cs_client     = @@character_set_client */;\n"
				+ "/*!40101 set character_set_client = utf8 */;\n" + "create table `part` (\n"
				+ "  `p_partkey` int(11) not null,\n" + "  `p_name` varchar(55) not null,\n"
				+ "  `p_mfgr` char(25) not null,\n" + "  `p_brand` char(10) not null,\n"
				+ "  `p_type` varchar(25) not null,\n" + "  `p_size` int(11) not null,\n"
				+ "  `p_container` char(10) not null,\n" + "  `p_retailprice` decimal(15,2) not null,\n"
				+ "  `p_comment` varchar(23) not null,\n" + "  primary key (`p_partkey`)\n"
				+ ") engine=innodb default charset=latin1;\n"
				+ "/*!40101 set character_set_client = @saved_cs_client */;\n" + "\n" + "--\n"
				+ "-- dumping data for table `part`\n" + "--\n" + "\n" + "lock tables `part` write;\n"
				+ "/*!40000 alter table `part` disable keys */;\n" + "insert into `part` values ";
	}

}
