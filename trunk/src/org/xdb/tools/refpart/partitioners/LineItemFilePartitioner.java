package org.xdb.tools.refpart.partitioners;

import java.util.List;

import org.xdb.logging.XDBLog;
import org.xdb.tools.refpart.AbstractFilePartitioner;
import org.xdb.tools.refpart.DefRefPart.WORK_MODE;
import org.xdb.tools.refpart.writers.LineItemPartRefWriter;
import org.xdb.tools.refpart.writers.LineItemPartSuppRefWriter;
import org.xdb.tools.refpart.writers.LineItemSuppRefWriter;

public class LineItemFilePartitioner extends AbstractFilePartitioner {

	public LineItemFilePartitioner(int partitionCount, String inputDir, String outputDir, String tempDir,
			String fileName) {
		super(WORK_MODE.FILE, partitionCount, inputDir, outputDir, tempDir, fileName, "lineitem");
		this.logger = XDBLog.getLogger("LineItem File Partitioner");
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
		return "/*!40000 ALTER TABLE `LINEITEM` ENABLE KEYS */;\n" + "UNLOCK TABLES;";
	}

	@Override
	protected String getSQLStart() {
		return "DROP TABLE IF EXISTS `LINEITEM`;\n"
				+ "/*!40101 SET @saved_cs_client     = @@character_set_client */;\n"
				+ "/*!40101 SET character_set_client = utf8 */;\n" + "CREATE TABLE `LINEITEM` (\n"
				+ "  `L_ORDERKEY` int(11) NOT NULL,\n" + "  `L_PARTKEY` int(11) NOT NULL,\n"
				+ "  `L_SUPPKEY` int(11) NOT NULL,\n" + "  `L_LINENUMBER` int(11) NOT NULL,\n"
				+ "  `L_QUANTITY` decimal(15,2) NOT NULL,\n" + "  `L_EXTENDEDPRICE` decimal(15,2) NOT NULL,\n"
				+ "  `L_DISCOUNT` decimal(15,2) NOT NULL,\n" + "  `L_TAX` decimal(15,2) NOT NULL,\n"
				+ "  `L_RETURNFLAG` char(1) NOT NULL,\n" + "  `L_LINESTATUS` char(1) NOT NULL,\n"
				+ "  `L_SHIPDATE` date NOT NULL,\n" + "  `L_COMMITDATE` date NOT NULL,\n"
				+ "  `L_RECEIPTDATE` date NOT NULL,\n" + "  `L_SHIPINSTRUCT` char(25) NOT NULL,\n"
				+ "  `L_SHIPMODE` char(10) NOT NULL,\n" + "  `L_COMMENT` varchar(44) NOT NULL,\n"
				+ "  PRIMARY KEY (`L_ORDERKEY`,`L_LINENUMBER`)\n" + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n"
				+ "/*!40101 SET character_set_client = @saved_cs_client */;\n" + "\n" + "--\n"
				+ "-- Dumping data for table `LINEITEM`\n" + "--\n" + "\n" + "LOCK TABLES `LINEITEM` WRITE;\n"
				+ "/*!40000 ALTER TABLE `LINEITEM` DISABLE KEYS */;\n" + "INSERT INTO `LINEITEM` VALUES ";
	}

}
