package org.xdb.tools.tpch.refpart.partitioners;

import java.util.List;

import org.xdb.logging.EnumXDBComponents;
import org.xdb.logging.XDBLog;
import org.xdb.tools.tpch.refpart.AbstractFilePartitioner;
import org.xdb.tools.tpch.refpart.DefRefPart.WORK_MODE;

public class RegionFilePartitioner extends AbstractFilePartitioner {

	public RegionFilePartitioner(int partitionCount, String inputDir, String outputDir, String tempDir, String fileName) {
		super(WORK_MODE.REPLICATE, partitionCount, inputDir, outputDir, tempDir, fileName, "region");

		this.logger = XDBLog.getLogger(EnumXDBComponents.PARTITIONER);
		columnCount = 3;
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
		rv.append("')");
		return rv.toString();
	}

	@Override
	protected void defineReferenceVisitors() {
	}

	@Override
	protected String getSQLEnd() {
		return "/*!40000 alter table `region` enable keys */;\n" + "unlock tables;\n";
	}

	@Override
	protected String getSQLStart() {
		return "drop table if exists `region`;\n" + "/*!40101 set @saved_cs_client     = @@character_set_client */;\n"
				+ "/*!40101 set character_set_client = utf8 */;\n" + "create table `region` (\n"
				+ "  `r_regionkey` int(11) not null,\n" + "  `r_name` char(25) not null,\n"
				+ "  `r_comment` varchar(152) default null,\n" + "  primary key (`r_regionkey`)\n"
				+ ") engine=innodb default charset=latin1;\n"
				+ "/*!40101 set character_set_client = @saved_cs_client */;\n" + "\n" + "--\n"
				+ "-- dumping data for table `region`\n" + "--\n" + "\n" + "lock tables `region` write;\n"
				+ "/*!40000 alter table `region` disable keys */;\n" + "insert into `region` values ";
	}

}
