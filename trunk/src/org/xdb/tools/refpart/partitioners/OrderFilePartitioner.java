package org.xdb.tools.refpart.partitioners;

import java.util.List;

import org.xdb.logging.XDBLog;
import org.xdb.tools.refpart.AbstractFilePartitioner;
import org.xdb.tools.refpart.DefRefPart.WORK_MODE;
import org.xdb.tools.refpart.util.MurmurHash;
import org.xdb.tools.refpart.writers.IDRefWriter;

public class OrderFilePartitioner extends AbstractFilePartitioner {

	public OrderFilePartitioner(int partitionCount, String inputDir, String outputDir, String tempDir, String fileName) {
		super(WORK_MODE.HASH, partitionCount, inputDir, outputDir, tempDir, fileName, "orders");

		this.logger = XDBLog.getLogger("Order File Partitioner");
		columnCount = 9;
	}

	@Override
	protected String buildSQLRow(List<String> fields) {
		StringBuilder rv = new StringBuilder();
		rv.append("(");
		rv.append(fields.get(0));
		rv.append(",");
		rv.append(fields.get(1));
		rv.append(",'");
		rv.append(fields.get(2));
		rv.append("',");
		rv.append(fields.get(3));
		rv.append(",'");
		rv.append(fields.get(4));
		rv.append("','");
		rv.append(fields.get(5));
		rv.append("','");
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
		addReferenceVisitor(new IDRefWriter(outputHashFile, partitionCount));
	}

	@Override
	protected int getHashValue(List<String> fields) {
		return MurmurHash.hash32(fields.get(1));
	}

	@Override
	protected String getSQLEnd() {
		return "/*!40000 alter table `orders` enable keys */;\n" + "unlock tables;\n";
	}

	@Override
	protected String getSQLStart() {
		return "drop table if exists `orders`;\n" + "/*!40101 set @saved_cs_client     = @@character_set_client */;\n"
				+ "/*!40101 set character_set_client = utf8 */;\n" + "create table `orders` (\n"
				+ "  `o_orderkey` int(11) not null,\n" + "  `o_custkey` int(11) not null,\n"
				+ "  `o_orderstatus` char(1) not null,\n" + "  `o_totalprice` decimal(15,2) not null,\n"
				+ "  `o_orderdate` date not null,\n" + "  `o_orderpriority` char(15) not null,\n"
				+ "  `o_clerk` char(15) not null,\n" + "  `o_shippriority` int(11) not null,\n"
				+ "  `o_comment` varchar(79) not null,\n" + "  primary key (`o_orderkey`)\n"
				+ ") engine=innodb default charset=latin1;\n"
				+ "/*!40101 set character_set_client = @saved_cs_client */;\n" + "\n" + "--\n"
				+ "-- dumping data for table `orders`\n" + "--\n" + "\n" + "lock tables `orders` write;\n"
				+ "/*!40000 alter table `orders` disable keys */;\n" + "insert into `orders` values ";
	}

}
