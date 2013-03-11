package org.xdb.tools.refpart.partitioners;

import java.util.List;

import org.xdb.logging.XDBLog;
import org.xdb.tools.refpart.AbstractFilePartitioner;
import org.xdb.tools.refpart.DefRefPart.WORK_MODE;

public class NationFilePartitioner extends AbstractFilePartitioner {

	public NationFilePartitioner(int partitionCount, String inputDir, String outputDir, String tempDir, String fileName) {
		super(WORK_MODE.REPLICATE, partitionCount, inputDir, outputDir, tempDir, fileName, "nation");

		this.logger = XDBLog.getLogger("Nation File Partitioner");
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
		return "/*!40000 ALTER TABLE `NATION` ENABLE KEYS */;\n" + "UNLOCK TABLES;\n";
	}

	@Override
	protected String getSQLStart() {
		return "DROP TABLE IF EXISTS `NATION`;\n" + "/*!40101 SET @saved_cs_client     = @@character_set_client */;\n"
				+ "/*!40101 SET character_set_client = utf8 */;\n" + "CREATE TABLE `NATION` (\n"
				+ "  `N_NATIONKEY` int(11) NOT NULL,\n" + "  `N_NAME` char(25) NOT NULL,\n"
				+ "  `N_REGIONKEY` int(11) NOT NULL,\n" + "  `N_COMMENT` varchar(152) DEFAULT NULL,\n"
				+ "  PRIMARY KEY (`N_NATIONKEY`)\n" + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n"
				+ "/*!40101 SET character_set_client = @saved_cs_client */;\n" + "\n" + "--\n"
				+ "-- Dumping data for table `NATION`\n" + "--\n" + "\n" + "LOCK TABLES `NATION` WRITE;\n"
				+ "/*!40000 ALTER TABLE `NATION` DISABLE KEYS */;\n" + "INSERT INTO `NATION` VALUES ";
	}

}
