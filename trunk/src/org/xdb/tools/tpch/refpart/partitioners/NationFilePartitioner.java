package org.xdb.tools.tpch.refpart.partitioners;

import java.util.List;

import org.xdb.logging.EnumXDBComponents;
import org.xdb.logging.XDBLog;
import org.xdb.tools.tpch.refpart.AbstractFilePartitioner;
import org.xdb.tools.tpch.refpart.DefRefPart.WORK_MODE;

public class NationFilePartitioner extends AbstractFilePartitioner {

	public NationFilePartitioner(int partitionCount, String inputDir, String outputDir, String tempDir, String fileName) {
		super(WORK_MODE.REPLICATE, partitionCount, inputDir, outputDir, tempDir, fileName, "nation");

		this.logger = XDBLog.getLogger(EnumXDBComponents.PARTITIONER);
		columnCount = 4;
	}

	@Override
	protected String buildSQLRow(List<String> fields) {
		StringBuilder rv = new StringBuilder();
		rv.append("(");
		rv.append(fields.get(0));
		rv.append(",'");
		rv.append(fields.get(1));
		rv.append("',");
		rv.append(fields.get(2));
		rv.append(",'");
		rv.append(fields.get(3));
		rv.append("')");
		return rv.toString();
	}

	@Override
	protected void defineReferenceVisitors() {
	}

	@Override
	protected String getSQLEnd() {
		return "/*!40000 alter table `nation` enable keys */;\n" + "unlock tables;\n";
	}

	@Override
	protected String getSQLStart() {
		return "drop table if exists `nation`;\n" + "/*!40101 set @saved_cs_client     = @@character_set_client */;\n"
				+ "/*!40101 set character_set_client = utf8 */;\n" + "create table `nation` (\n"
				+ "  `n_nationkey` int(11) not null,\n" + "  `n_name` char(25) not null,\n"
				+ "  `n_regionkey` int(11) not null,\n" + "  `n_comment` varchar(152) default null,\n"
				+ "  primary key (`n_nationkey`)\n" + ") engine=innodb default charset=latin1;\n"
				+ "/*!40101 set character_set_client = @saved_cs_client */;\n" + "\n" + "--\n"
				+ "-- dumping data for table `nation`\n" + "--\n" + "\n" + "lock tables `nation` write;\n"
				+ "/*!40000 alter table `nation` disable keys */;\n" + "insert into `nation` values ";
	}

}
