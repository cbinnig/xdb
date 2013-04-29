package org.xdb.tools.tpch.refpart.partitioners;

import java.util.List;

import org.xdb.logging.XDBLog;
import org.xdb.tools.tpch.refpart.AbstractFilePartitioner;
import org.xdb.tools.tpch.refpart.DefRefPart.WORK_MODE;

public class SupplierFilePartitioner extends AbstractFilePartitioner {

	public SupplierFilePartitioner(int partitionCount, String inputDir, String outputDir, String tempDir,
			String fileName) {
		super(WORK_MODE.FILE, partitionCount, inputDir, outputDir, tempDir, fileName, "supplier");

		this.logger = XDBLog.getLogger("Supplier File Partitioner");
		columnCount = 7;
		this.inputHashFile = tempDir + "lineitemSupp";
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
		rv.append("')");
		return rv.toString();
	}

	@Override
	protected void defineReferenceVisitors() {

	}

	@Override
	protected String getSQLEnd() {
		return "/*!40000 alter table `supplier` enable keys */;\n" + "unlock tables;";
	}

	@Override
	protected String getSQLStart() {
		return "drop table if exists `supplier`;\n"
				+ "/*!40101 set @saved_cs_client     = @@character_set_client */;\n"
				+ "/*!40101 set character_set_client = utf8 */;\n" + "create table `supplier` (\n"
				+ "  `s_suppkey` int(11) not null,\n" + "  `s_name` char(25) not null,\n"
				+ "  `s_address` varchar(40) not null,\n" + "  `s_nationkey` int(11) not null,\n"
				+ "  `s_phone` char(15) not null,\n" + "  `s_acctbal` decimal(15,2) not null,\n"
				+ "  `s_comment` varchar(101) not null,\n" + "  primary key (`s_suppkey`)\n"
				+ ") engine=innodb default charset=latin1;\n"
				+ "/*!40101 set character_set_client = @saved_cs_client */;\n" + "\n" + "--\n"
				+ "-- dumping data for table `supplier`\n" + "--\n" + "\n" + "lock tables `supplier` write;\n"
				+ "/*!40000 alter table `supplier` disable keys */;\n" + "insert into `supplier` values ";
	}

}
