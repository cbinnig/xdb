package org.xdb.elasticpartitioning;

import java.io.File;
import java.sql.SQLException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xdb.elasticpartitioning.database.DatabaseAbstractionLayer;
import org.xdb.elasticpartitioning.database.DatabaseConfig;
import org.xdb.elasticpartitioning.database.ForeignKey;
import org.xdb.elasticpartitioning.database.Query;
import org.xdb.elasticpartitioning.database.Table;
import org.xdb.elasticpartitioning.graph.Edge;
import org.xdb.elasticpartitioning.graph.ReferenceGraph;
import org.xdb.elasticpartitioning.partition.PartitioningMetaData;
import org.xdb.elasticpartitioning.util.Settings;

public class Runner {
	private long dbTime = 0;
	private long calculationTime = 0;
	DatabaseConfig dc;
	PartitioningMetaData partitioningMetaData;
	
	
	public ReferenceGraph loadXMLConfig(String XMLAddress) throws Exception{
		try{

			// Reading the schema.xml file
			File fXmlFile = new File(XMLAddress);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// First: Reading the database metadata info from XML
			dc = loadDatabaseMetaDataInfoFromXML(doc);

			// Second: Connecting to the database
			DatabaseAbstractionLayer.initialize(dc.getUserName(), dc.getPassword(), dc.getServerName(), dc.getPort() , dc.getDatabaseName());
			DatabaseAbstractionLayer db = DatabaseAbstractionLayer.getInstance();

			// Third: Reading partitioning metadata
			partitioningMetaData = loadPartitioningMetaDataFromXML(doc);

			// Fourth: Extracting tables info (and fetch their size from the database) 
			ReferenceGraph refGraph = loadTableInfoFromXML(doc, db);


			if (partitioningMetaData.getWorkloadDrivenPartitioning() == false){
				// Fifth: Extracting references
				// we only extract references if workload-partitioning tag is set to false
				loadForeignKeysFromXML(doc, refGraph);
			}
			else{
				// Fifth: Extracting queries
				// we only extract queries if workload-partitioning tag is set to true
				loadQueriesFromXML(doc, refGraph);
			}


			return refGraph;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	private static DatabaseConfig loadDatabaseMetaDataInfoFromXML(Document doc) throws Exception{
		NodeList nList = null;
		Element element;

		nList = doc.getElementsByTagName("database-config");
		Node configNode =  nList.item(0);
		element = (Element)configNode;
		String db_username = element.getElementsByTagName("username").item(0).getTextContent();
		String db_password = element.getElementsByTagName("password").item(0).getTextContent();
		String db_server = element.getElementsByTagName("server").item(0).getTextContent();
		String db_port = element.getElementsByTagName("port").item(0).getTextContent();
		String db_name = element.getElementsByTagName("dbname").item(0).getTextContent();
		Settings.NULL_SYMBOL = element.getElementsByTagName("null-symbol").item(0).getTextContent(); 
		return new DatabaseConfig(db_username, db_password, db_server, db_port, db_name);
	}

	private static PartitioningMetaData loadPartitioningMetaDataFromXML(Document doc) throws Exception{
		NodeList nList = null;
		Element element;

		nList = doc.getElementsByTagName("partitioning-config");
		Node configNode =  nList.item(0);
		element = (Element)configNode;

		String workload = element.getElementsByTagName("workload-driven").item(0).getTextContent();

		boolean workloadDrivenPartitioning;

		if (workload.equalsIgnoreCase("true"))
			workloadDrivenPartitioning = true;
		else if (workload.equalsIgnoreCase("false"))
			workloadDrivenPartitioning = false;
		else 
			throw new Exception("The tag 'workload-driven-partitioning' can either be set to true or false");

		int numberOfPartitions = Integer.parseInt(element.getElementsByTagName("partition-cnt").item(0).getTextContent());
		double sampleSizeRaio = Double.parseDouble(element.getElementsByTagName("samplesize-ratio").item(0).getTextContent());
		return new PartitioningMetaData(workloadDrivenPartitioning, numberOfPartitions, sampleSizeRaio);
	}

	private ReferenceGraph loadTableInfoFromXML(Document doc, DatabaseAbstractionLayer db) throws SQLException{
		NodeList nList = null;
		Element element;

		nList = doc.getElementsByTagName("tables").item(0).getChildNodes();
		//Map<String, Table> tablesMap = new HashMap<String, Table>();
		ReferenceGraph refGraph = new ReferenceGraph();
		Table table;

		for (int i=0; i<nList.getLength(); i++){
			if (nList.item(i).getNodeName().equals("table")){
				element = (Element) nList.item(i);
				table = new Table(element.getElementsByTagName("name").item(0).getTextContent());
				table.setTableSize(db.getTableSize(table));
				//tablesMap.put(table.getTableName(), table);
				refGraph.addNode(new org.xdb.elasticpartitioning.graph.Node(table));
			}
		}
		return refGraph;
	}

	/**
	 * This function CHANGES its ReferenceGraph input, and add edges to it
	 * @param doc
	 * @param refGraph
	 */
	private void loadForeignKeysFromXML(Document doc, ReferenceGraph refGraph) {
		NodeList nList = null;
		Element element;

		nList = doc.getElementsByTagName("references").item(0).getChildNodes();
		Table sTable;
		Table tTable;


		for (int i=0; i<nList.getLength(); i++){
			if (nList.item(i).getNodeName().equals("reference")){
				element = (Element) nList.item(i);
				if (element.getElementsByTagName("type").item(0).getTextContent().equals("foreign-key")){
					String sourceTable = element.getElementsByTagName("source-table").item(0).getTextContent();
					String targetTable = element.getElementsByTagName("target-table").item(0).getTextContent();
					System.out.println(sourceTable + " | " + targetTable);

					sTable = refGraph.getNodeWithTableName(sourceTable).getContent();
					tTable = refGraph.getNodeWithTableName(targetTable).getContent();

					ForeignKey foreignKey = new ForeignKey(sTable, tTable);

					NodeList attributesList = element.getElementsByTagName("reference-attributes").item(0).getChildNodes();
					for (int j=0; j<attributesList.getLength(); j++){
						if (attributesList.item(j).getNodeName().equals("reference-attribute")){
							Element attributeE = (Element) attributesList.item(j);
							String sourceAtt = attributeE.getElementsByTagName("source-attribute").item(0).getTextContent();
							String targetAtt = attributeE.getElementsByTagName("target-attribute").item(0).getTextContent();
							foreignKey.addRelations(sourceAtt, targetAtt);
						}
					}
					int w = Integer.parseInt(element.getElementsByTagName("weight").item(0).getTextContent());

					sTable.setForeignKey(tTable, foreignKey);

					//adding the edge in the reference graph
					org.xdb.elasticpartitioning.graph.Node sourceNode = refGraph.getNodeWithTableName(sourceTable);
					org.xdb.elasticpartitioning.graph.Node targetNode = refGraph.getNodeWithTableName(targetTable);
					double weight = w * Math.min(sTable.getTableSize(), tTable.getTableSize());
					refGraph.addEdge(foreignKey, sourceNode, targetNode, weight);
				}
			}
		}
	}

	private void loadQueriesFromXML(Document doc, ReferenceGraph refGraph) {
		NodeList nList = null;
		Element element;

		nList = doc.getElementsByTagName("queries").item(0).getChildNodes();

		Query query;
		int queryID;
		
		String sourceTable;
		String targetTable;
		String sourceAttribute;
		String targetAttribute;
		
		for (int i=0; i<nList.getLength(); i++){
			if (nList.item(i).getNodeName().equals("query")){
				element = (Element) nList.item(i);
				queryID = Integer.parseInt(element.getElementsByTagName("query-number").item(0).getTextContent());
				query = new Query(queryID);
				NodeList joinRelations = element.getElementsByTagName("equi-join");
				for (int j=0; j<joinRelations.getLength(); j++){
					sourceTable = element.getElementsByTagName("source-table").item(j).getTextContent();
					targetTable = element.getElementsByTagName("target-table").item(j).getTextContent();
					sourceAttribute = element.getElementsByTagName("source-attribute").item(j).getTextContent();
					targetAttribute = element.getElementsByTagName("target-attribute").item(j).getTextContent();
					Edge edge = refGraph.getEdgeWithTableNames(sourceTable, targetTable, sourceAttribute, targetAttribute);
					if (edge==null)
						edge = refGraph.addEdge(sourceTable, targetTable, sourceAttribute, targetAttribute);
					query.addEquiJoinPredicate(edge);						
				}
				refGraph.addQuery(query);
			}
		}
	}

	

	public static void main(String[] args) {
		Runner runner = new Runner();
		try {
			long elapsedTime;
			long t1, t2;
			// First, read the XML file, and make the reference graph
			t1 = System.nanoTime();
			ReferenceGraph refGraph = runner.loadXMLConfig(Settings.SCHEMA_FILE);
			t2 = System.nanoTime();
			elapsedTime = (t2-t1)/1000000;
			runner.dbTime += elapsedTime;
			
			Map<Edge, Double> redundancyFactor = ReferenceGraph.findEdgesRedundancyFactor(refGraph, runner.partitioningMetaData);
			ReferenceGraph.setRedundancyFactor(redundancyFactor);
			
			ReferenceGraph.setPartitioningMetaData(runner.partitioningMetaData);

			
			if (runner.partitioningMetaData.getWorkloadDrivenPartitioning()){
				refGraph.removeRedundantQueries();
				refGraph.findBestGraphPartitioning();
				
			}

			else{
				// Then, make the MASP
				t1 = System.nanoTime();
				ReferenceGraph MASP = refGraph.findMASP();
				t2 = System.nanoTime();
				elapsedTime = (t2-t1)/1000000;
				runner.calculationTime += elapsedTime;


				System.out.println(MASP);
				System.out.println();

				//Map<Edge, Double> redundancyFactor = ReferenceGraph.findEdgesRedundancyFactor(MASP, runner.partitioningMetaData);
				System.out.println("Redundancy factors:");

				// printing the redundancyFactor
				t1 = System.nanoTime();
				for (Edge e : redundancyFactor.keySet())
					System.out.println(e + " : " + redundancyFactor.get(e));
				MASP.findOptimalConfigurationInAConnectedComponent();
				t2 = System.nanoTime();
				elapsedTime = (t2-t1)/1000000;
				runner.calculationTime += elapsedTime;

				System.out.println("Time spent on DB/file operations(ms): " + runner.dbTime);
				System.out.println("Time spent on algorithm calculations(ms): " + runner.calculationTime);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	/*
	public static void main(String[] args) {
		ReferenceGraph masp = new ReferenceGraph();
		Table ta = new Table("a");
		Table tb = new Table("b");
		Table tc = new Table("c");
		Table td = new Table("d");
		Table te = new Table("e");
		Table tf = new Table("f");

		ta.setTableSize(80);
		tb.setTableSize(90);
		tc.setTableSize(30);
		td.setTableSize(20);
		te.setTableSize(40);
		tf.setTableSize(80);

		org.xdb.elasticpartitioning.graph.Node a = new org.xdb.elasticpartitioning.graph.Node(ta);
		org.xdb.elasticpartitioning.graph.Node b = new org.xdb.elasticpartitioning.graph.Node(tb);
		org.xdb.elasticpartitioning.graph.Node c = new org.xdb.elasticpartitioning.graph.Node(tc);
		org.xdb.elasticpartitioning.graph.Node d = new org.xdb.elasticpartitioning.graph.Node(td);
		org.xdb.elasticpartitioning.graph.Node e = new org.xdb.elasticpartitioning.graph.Node(te);
		org.xdb.elasticpartitioning.graph.Node f = new org.xdb.elasticpartitioning.graph.Node(tf);


		masp.addNode(a);
		masp.addNode(b);
		masp.addNode(c);
		masp.addNode(d);
		masp.addNode(e);
		masp.addNode(f);

		Edge ab = masp.addEdge(new ForeignKey(tb, ta), b, a, 1);
		Edge bc = masp.addEdge(new ForeignKey(tc, tb), c, b, 1);
		Edge cd = masp.addEdge(new ForeignKey(tc, td), c, d, 1);
		Edge ce = masp.addEdge(new ForeignKey(tc, te), c, e, 1);
		Edge ef = masp.addEdge(new ForeignKey(te, tf), e, f, 1);

		Map<Edge, Double> redundancyFactor = new HashMap<Edge, Double>();
		redundancyFactor.put(ab, 3.0);
		redundancyFactor.put(bc, 4.0);
		redundancyFactor.put(cd, 2.0);
		redundancyFactor.put(ce, 5.0);
		redundancyFactor.put(ef, 5.0);

		Runner.findOptimalConfigurationInMASP(masp, redundancyFactor);
	}
	 */
}