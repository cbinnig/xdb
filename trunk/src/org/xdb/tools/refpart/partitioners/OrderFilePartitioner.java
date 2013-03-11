package org.xdb.tools.refpart.partitioners;

import java.util.List;

import org.xdb.logging.XDBLog;
import org.xdb.tools.refpart.AbstractFilePartitioner;
import org.xdb.tools.refpart.DefRefPart.WORK_MODE;
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
		return Integer.valueOf(fields.get(1)).intValue();
	}

	@Override
	protected String getSQLEnd() {
		return "/*!40000 ALTER TABLE `ORDERS` ENABLE KEYS */;\n" + "UNLOCK TABLES;\n";
	}

	@Override
	protected String getSQLStart() {
		return "DROP TABLE IF EXISTS `ORDERS`;\n" + "/*!40101 SET @saved_cs_client     = @@character_set_client */;\n"
				+ "/*!40101 SET character_set_client = utf8 */;\n" + "CREATE TABLE `ORDERS` (\n"
				+ "  `O_ORDERKEY` int(11) NOT NULL,\n" + "  `O_CUSTKEY` int(11) NOT NULL,\n"
				+ "  `O_ORDERSTATUS` char(1) NOT NULL,\n" + "  `O_TOTALPRICE` decimal(15,2) NOT NULL,\n"
				+ "  `O_ORDERDATE` date NOT NULL,\n" + "  `O_ORDERPRIORITY` char(15) NOT NULL,\n"
				+ "  `O_CLERK` char(15) NOT NULL,\n" + "  `O_SHIPPRIORITY` int(11) NOT NULL,\n"
				+ "  `O_COMMENT` varchar(79) NOT NULL,\n" + "  PRIMARY KEY (`O_ORDERKEY`)\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n"
				+ "/*!40101 SET character_set_client = @saved_cs_client */;\n" + "\n" + "--\n"
				+ "-- Dumping data for table `ORDERS`\n" + "--\n" + "\n" + "LOCK TABLES `ORDERS` WRITE;\n"
				+ "/*!40000 ALTER TABLE `ORDERS` DISABLE KEYS */;\n" + "INSERT INTO `ORDERS` VALUES ";
	}

}
