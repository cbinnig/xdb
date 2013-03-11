package org.xdb.tools.refpart.partitioners;

import java.util.List;

import org.xdb.logging.XDBLog;
import org.xdb.tools.refpart.AbstractFilePartitioner;
import org.xdb.tools.refpart.CombinedPartSuppKey;
import org.xdb.tools.refpart.DefRefPart.WORK_MODE;

public class PartSuppFilePartitioner extends AbstractFilePartitioner {

	public PartSuppFilePartitioner(int partitionCount, String inputDir, String outputDir, String tempDir,
			String fileName) {
		super(WORK_MODE.FILE, partitionCount, inputDir, outputDir, tempDir, fileName, "partsupp");

		this.logger = XDBLog.getLogger("PartSupp File Partitioner");
		columnCount = 5;
		this.inputHashFile = tempDir + "lineitemPartSupp";
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
		rv.append(",'");
		rv.append(fields.get(4));
		rv.append("')");
		return rv.toString();
	}

	@Override
	protected void defineReferenceVisitors() {

	}

	@Override
	protected int getHashValue(List<String> fields) {
		CombinedPartSuppKey cpsk = new CombinedPartSuppKey(Long.valueOf(fields.get(1)), Long.valueOf(fields.get(2)));
		return cpsk.hashCode();
	}

	@Override
	protected String getSQLEnd() {
		return "/*!40000 ALTER TABLE `PARTSUPP` ENABLE KEYS */;\n" + "UNLOCK TABLES;";
	}

	@Override
	protected String getSQLStart() {
		return "DROP TABLE IF EXISTS `PARTSUPP`;\n"
				+ "/*!40101 SET @saved_cs_client     = @@character_set_client */;\n"
				+ "/*!40101 SET character_set_client = utf8 */;\n" + "CREATE TABLE `PARTSUPP` (\n"
				+ "  `PS_PARTKEY` int(11) NOT NULL,\n" + "  `PS_SUPPKEY` int(11) NOT NULL,\n"
				+ "  `PS_AVAILQTY` int(11) NOT NULL,\n" + "  `PS_SUPPLYCOST` decimal(15,2) NOT NULL,\n"
				+ "  `PS_COMMENT` varchar(199) NOT NULL,\n" + "  PRIMARY KEY (`PS_PARTKEY`,`PS_SUPPKEY`)\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n"
				+ "/*!40101 SET character_set_client = @saved_cs_client */;\n" + "\n" + "--\n"
				+ "-- Dumping data for table `PARTSUPP`\n" + "--\n" + "\n" + "LOCK TABLES `PARTSUPP` WRITE;\n"
				+ "/*!40000 ALTER TABLE `PARTSUPP` DISABLE KEYS */;\n" + "INSERT INTO `PARTSUPP` VALUES";
	}

}
