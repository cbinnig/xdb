package org.xdb.tools.tpch.refpart.partitioners;

import java.util.List;

import org.xdb.logging.EnumXDBComponents;
import org.xdb.logging.XDBLog;
import org.xdb.tools.tpch.refpart.AbstractFilePartitioner;
import org.xdb.tools.tpch.refpart.DefRefPart.WORK_MODE;
import org.xdb.tools.tpch.refpart.writers.LineItemPartRefWriter;
import org.xdb.tools.tpch.refpart.writers.LineItemPartSuppRefWriter;
import org.xdb.tools.tpch.refpart.writers.LineItemSuppRefWriter;

public class LineItemFilePartitioner extends AbstractFilePartitioner {

	public LineItemFilePartitioner(int partitionCount, String inputDir, String outputDir, String tempDir,
			String fileName) {
		super(WORK_MODE.FILE, partitionCount, inputDir, outputDir, tempDir, fileName, "lineitem");
		this.logger = XDBLog.getLogger(EnumXDBComponents.PARTITIONER);
		columnCount = 16;
		this.inputHashFile = tempDir + "orders";
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
		rv.append(",");
		rv.append(fields.get(4));
		rv.append(",");
		rv.append(fields.get(5));
		rv.append(",");
		rv.append(fields.get(6));
		rv.append(",");
		rv.append(fields.get(7));
		rv.append(",'");
		rv.append(fields.get(8));
		rv.append("','");
		rv.append(fields.get(9));
		rv.append("','");
		rv.append(fields.get(10));
		rv.append("','");
		rv.append(fields.get(11));
		rv.append("','");
		rv.append(fields.get(12));
		rv.append("','");
		rv.append(fields.get(13));
		rv.append("','");
		rv.append(fields.get(14));
		rv.append("','");
		rv.append(fields.get(15));
		rv.append("')");
		return rv.toString();
	}

	@Override
	protected void defineReferenceVisitors() {
		addReferenceVisitor(new LineItemPartRefWriter(outputHashFile + "Part", partitionCount));
		addReferenceVisitor(new LineItemSuppRefWriter(outputHashFile + "Supp", partitionCount));
		addReferenceVisitor(new LineItemPartSuppRefWriter(outputHashFile + "PartSupp", partitionCount));
	}

	@Override
	protected String getSQLEnd() {
		return "/*!40000 alter table `lineitem` enable keys */;\n" + "unlock tables;";
	}

	@Override
	protected String getSQLStart() {
		return "drop table if exists `lineitem`;\n"
				+ "/*!40101 set @saved_cs_client     = @@character_set_client */;\n"
				+ "/*!40101 set character_set_client = utf8 */;\n" + "create table `lineitem` (\n"
				+ "  `l_orderkey` int(11) not null,\n" + "  `l_partkey` int(11) not null,\n"
				+ "  `l_suppkey` int(11) not null,\n" + "  `l_linenumber` int(11) not null,\n"
				+ "  `l_quantity` decimal(15,2) not null,\n" + "  `l_extendedprice` decimal(15,2) not null,\n"
				+ "  `l_discount` decimal(15,2) not null,\n" + "  `l_tax` decimal(15,2) not null,\n"
				+ "  `l_returnflag` char(1) not null,\n" + "  `l_linestatus` char(1) not null,\n"
				+ "  `l_shipdate` date not null,\n" + "  `l_commitdate` date not null,\n"
				+ "  `l_receiptdate` date not null,\n" + "  `l_shipinstruct` char(25) not null,\n"
				+ "  `l_shipmode` char(10) not null,\n" + "  `l_comment` varchar(44) not null,\n"
				+ "  primary key (`l_orderkey`,`l_linenumber`)\n" + ") engine=innodb default charset=latin1;\n"
				+ "/*!40101 set character_set_client = @saved_cs_client */;\n" + "\n" + "--\n"
				+ "-- dumping data for table `lineitem`\n" + "--\n" + "\n" + "lock tables `lineitem` write;\n"
				+ "/*!40000 alter table `lineitem` disable keys */;\n" + "insert into `lineitem` values ";
	}

}
