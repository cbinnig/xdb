package org.xdb.tools.tpch.refpart.partitioners;

import java.util.List;

import org.xdb.logging.EnumXDBComponents;
import org.xdb.logging.XDBLog;
import org.xdb.tools.tpch.refpart.AbstractFilePartitioner;
import org.xdb.tools.tpch.refpart.DefRefPart.WORK_MODE;
import org.xdb.tools.tpch.refpart.writers.IDRefWriter;

public class CustomerFilePartitioner extends AbstractFilePartitioner {

	public CustomerFilePartitioner(int partitionCount, String inputDir, String outputDir, String tempDir,
			String fileName) {
		super(WORK_MODE.HASH, partitionCount, inputDir, outputDir, tempDir, fileName, "customer");

		this.logger = XDBLog.getLogger(EnumXDBComponents.PARTITIONER);
		columnCount = 8;
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
		rv.append("',");
		rv.append(fields.get(3));
		rv.append(",'");
		rv.append(fields.get(4));
		rv.append("',");
		rv.append(fields.get(5));
		rv.append(",'");
		rv.append(fields.get(6));
		rv.append("','");
		rv.append(fields.get(7));
		rv.append("')");
		return rv.toString();
	}

	@Override
	protected void defineReferenceVisitors() {
		addReferenceVisitor(new IDRefWriter(outputHashFile, partitionCount));
	}

	@Override
	protected String getSQLEnd() {
		return "/*!40000 alter table `customer` enable keys */;\n" + "unlock tables;";
	}

	@Override
	protected String getSQLStart() {
		return "drop table if exists `customer`;\n"
				+ "/*!40101 set @saved_cs_client     = @@character_set_client */;\n"
				+ "/*!40101 set character_set_client = utf8 */;\n" + "create table `customer` (\n"
				+ "  `c_custkey` int(11) not null,\n" + "  `c_name` varchar(25) not null,\n"
				+ "  `c_address` varchar(40) not null,\n" + "  `c_nationkey` int(11) not null,\n"
				+ "  `c_phone` char(15) not null,\n" + "  `c_acctbal` decimal(15,2) not null,\n"
				+ "  `c_mktsegment` char(10) not null,\n" + "  `c_comment` varchar(117) not null,\n"
				+ "  primary key (`c_custkey`)\n" + ") engine=innodb default charset=latin1;\n"
				+ "/*!40101 set character_set_client = @saved_cs_client */;\n" + "\n" + "--\n"
				+ "-- dumping data for table `customer`\n" + "--\n" + "\n" + "lock tables `customer` write;\n"
				+ "/*!40000 alter table `customer` disable keys */;" + "insert into `customer` values ";
	}

}
