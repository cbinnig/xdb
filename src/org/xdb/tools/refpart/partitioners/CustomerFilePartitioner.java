package org.xdb.tools.refpart.partitioners;

import java.util.List;

import org.xdb.logging.XDBLog;
import org.xdb.tools.refpart.AbstractFilePartitioner;
import org.xdb.tools.refpart.DefRefPart.WORK_MODE;
import org.xdb.tools.refpart.writers.IDRefWriter;

public class CustomerFilePartitioner extends AbstractFilePartitioner {

	public CustomerFilePartitioner(int partitionCount, String inputDir, String outputDir, String tempDir,
			String fileName) {
		super(WORK_MODE.HASH, partitionCount, inputDir, outputDir, tempDir, fileName, "customer");

		this.logger = XDBLog.getLogger(this.getClass().getName());
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
		return "/*!40000 ALTER TABLE `CUSTOMER` ENABLE KEYS */;\n" + "UNLOCK TABLES;";
	}

	@Override
	protected String getSQLStart() {
		return "DROP TABLE IF EXISTS `CUSTOMER`;\n"
				+ "/*!40101 SET @saved_cs_client     = @@character_set_client */;\n"
				+ "/*!40101 SET character_set_client = utf8 */;\n" + "CREATE TABLE `CUSTOMER` (\n"
				+ "  `C_CUSTKEY` int(11) NOT NULL,\n" + "  `C_NAME` varchar(25) NOT NULL,\n"
				+ "  `C_ADDRESS` varchar(40) NOT NULL,\n" + "  `C_NATIONKEY` int(11) NOT NULL,\n"
				+ "  `C_PHONE` char(15) NOT NULL,\n" + "  `C_ACCTBAL` decimal(15,2) NOT NULL,\n"
				+ "  `C_MKTSEGMENT` char(10) NOT NULL,\n" + "  `C_COMMENT` varchar(117) NOT NULL,\n"
				+ "  PRIMARY KEY (`C_CUSTKEY`)\n" + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n"
				+ "/*!40101 SET character_set_client = @saved_cs_client */;\n" + "\n" + "--\n"
				+ "-- Dumping data for table `CUSTOMER`\n" + "--\n" + "\n" + "LOCK TABLES `CUSTOMER` WRITE;\n"
				+ "/*!40000 ALTER TABLE `CUSTOMER` DISABLE KEYS */;" + "INSERT INTO `CUSTOMER` VALUES ";
	}

}
