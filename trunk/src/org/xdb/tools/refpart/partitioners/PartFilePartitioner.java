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
		return "/*!40000 ALTER TABLE `PART` ENABLE KEYS */;\n" + "UNLOCK TABLES;";
	}

	@Override
	protected String getSQLStart() {
		return "DROP TABLE IF EXISTS `PART`;\n" + "/*!40101 SET @saved_cs_client     = @@character_set_client */;\n"
				+ "/*!40101 SET character_set_client = utf8 */;\n" + "CREATE TABLE `PART` (\n"
				+ "  `P_PARTKEY` int(11) NOT NULL,\n" + "  `P_NAME` varchar(55) NOT NULL,\n"
				+ "  `P_MFGR` char(25) NOT NULL,\n" + "  `P_BRAND` char(10) NOT NULL,\n"
				+ "  `P_TYPE` varchar(25) NOT NULL,\n" + "  `P_SIZE` int(11) NOT NULL,\n"
				+ "  `P_CONTAINER` char(10) NOT NULL,\n" + "  `P_RETAILPRICE` decimal(15,2) NOT NULL,\n"
				+ "  `P_COMMENT` varchar(23) NOT NULL,\n" + "  PRIMARY KEY (`P_PARTKEY`)\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n"
				+ "/*!40101 SET character_set_client = @saved_cs_client */;\n" + "\n" + "--\n"
				+ "-- Dumping data for table `PART`\n" + "--\n" + "\n" + "LOCK TABLES `PART` WRITE;\n"
				+ "/*!40000 ALTER TABLE `PART` DISABLE KEYS */;\n" + "INSERT INTO `PART` VALUES ";
	}

}
