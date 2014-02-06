package org.xdb.elasticpartitioning;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xdb.elasticpartitioning.graph.Edge;
import org.xdb.elasticpartitioning.graph.ReferenceGraph;
import org.xdb.elasticpartitioning.util.FilesLocation;

public class Runner {
	int numberOfPartitions;
	
	public ReferenceGraph loadXMLConfig(String XMLAddress) throws Exception{
		try{

			// Reading the schema.xml file
			File fXmlFile = new File(XMLAddress);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);


			NodeList nList = null;
			Element element;

			// Extracting database config info (username, password, ...)
			nList = doc.getElementsByTagName("config");
			Node configNode =  nList.item(0);
			element = (Element)configNode;
			String db_username = element.getElementsByTagName("username").item(0).getTextContent();
			String db_password = element.getElementsByTagName("password").item(0).getTextContent();
			String db_server = element.getElementsByTagName("server").item(0).getTextContent();
			String db_port = element.getElementsByTagName("port").item(0).getTextContent();
			String db_name = element.getElementsByTagName("dbname").item(0).getTextContent();
			numberOfPartitions = Integer.parseInt(element.getElementsByTagName("partition-cnt").item(0).getTextContent());
			

			// Extracting database Schema
			// First: tables info (and extract their size from the database)
			DatabaseAbstractionLayer.initialize(db_username, db_password, db_server, db_port, db_name);
			DatabaseAbstractionLayer db = DatabaseAbstractionLayer.getInstance();

			nList = doc.getElementsByTagName("tables").item(0).getChildNodes();
			Map<String, Table> tablesMap = new HashMap<String, Table>();
			ReferenceGraph refGraph = new ReferenceGraph();
			Table table;

			for (int i=0; i<nList.getLength(); i++){
				if (nList.item(i).getNodeName().equals("table")){
					element = (Element) nList.item(i);
					table = new Table(element.getElementsByTagName("name").item(0).getTextContent());
					table.setTableSize(db.getTableSize(table));
					tablesMap.put(table.getTableName(), table);
					refGraph.addNode(new org.xdb.elasticpartitioning.graph.Node(table));
				}
			}

			// Second: Extracting references
			nList = doc.getElementsByTagName("references").item(0).getChildNodes();
			Table sTable;
			Table tTable;


			for (int i=0; i<nList.getLength(); i++){
				if (nList.item(i).getNodeName().equals("reference")){
					element = (Element) nList.item(i);
					if (element.getElementsByTagName("type").item(0).getTextContent().equals("foreign-key")){
						String sourceTable = element.getElementsByTagName("source-table").item(0).getTextContent();
						String targetTable = element.getElementsByTagName("target-table").item(0).getTextContent();
						
						sTable = tablesMap.get(sourceTable);
						tTable = tablesMap.get(targetTable);
						
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
			return refGraph;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	public Map<Edge, Double> findEdgesRedundancyFactor(ReferenceGraph MASP) throws Exception{
		try{
			MutualRedundancySizeFinder redFinder = new MutualRedundancySizeFinder(numberOfPartitions);
			Map<Edge, Double> redundancyFactor = new HashMap<Edge, Double>();

			for (org.xdb.elasticpartitioning.graph.Node node: MASP.getNodes()){
				for (Edge e: node.getOutEdges()){
					Table table = node.getContent();
					Table destTable = e.getDestination().getContent();
					ForeignKey fk = e.getRelation();
					double sizeAfterPartitioning = redFinder.findRedundancy(table, fk);
					double sizeBeforePartitioning = destTable.getTableSize();
					double rf = sizeAfterPartitioning/sizeBeforePartitioning;
					redundancyFactor.put(e, rf);
					System.out.println("Redundancy factor for Edge " + e + " is " + rf);
				}
			}
			return redundancyFactor;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	public void findOptimalConfigurationInMASP(ReferenceGraph MASP, Map<Edge, Double> redundancyFactor){
		// Let's iterate over possible candidates of tables
		// for hash partitioning, and find the plan with the minimum redundancy
		
		PartitioningConfiguration optimalConfig = new PartitioningConfiguration();
		optimalConfig.setDataSize(Double.MAX_VALUE);
		
		for (org.xdb.elasticpartitioning.graph.Node hashedNode: MASP.getNodes()){
			System.out.println("If table " + hashedNode.getContent().getTableName() + " is to be hash-partitioned:");	
			PartitioningConfiguration config = new PartitioningConfiguration();
			Queue<org.xdb.elasticpartitioning.graph.Node> queue = new LinkedList<org.xdb.elasticpartitioning.graph.Node>();
			Set<org.xdb.elasticpartitioning.graph.Node> alreadyVisitedNodes = new HashSet<org.xdb.elasticpartitioning.graph.Node>();
			queue.add(hashedNode);

			Map<org.xdb.elasticpartitioning.graph.Node, Double> sizeFactor = new HashMap<org.xdb.elasticpartitioning.graph.Node, Double>();
			sizeFactor.put(hashedNode, new Double(1));
			config.addPartitionedNode(hashedNode, PartitioningType.Hash);
			System.out.println("\t" + "table " + hashedNode.getContent().getTableName() + " will be Hash, size: " + hashedNode.getContent().getTableSize());


			double totalSize = 0;

			while (!queue.isEmpty()){
				org.xdb.elasticpartitioning.graph.Node n = queue.poll();
				alreadyVisitedNodes.add(n);
				totalSize += n.getContent().getTableSize() * sizeFactor.get(n);

				for (Edge outEdge : n.getOutEdges()){
					
					org.xdb.elasticpartitioning.graph.Node child = outEdge.getDestination();
					if (alreadyVisitedNodes.contains(child)) continue;
					
					config.addPartitionedNode(child, PartitioningType.ReverseReferece);
					double expectedSizeFactor = redundancyFactor.get(outEdge)*sizeFactor.get(n);
					if (expectedSizeFactor > numberOfPartitions) expectedSizeFactor = numberOfPartitions;
					sizeFactor.put(child, expectedSizeFactor);
					queue.add(child);
					System.out.println("\t" + "table " + child.getContent().getTableName() + " will be RR, size: " + child.getContent().getTableSize() * sizeFactor.get(child));

				}	
				for (Edge inEdge : n.getInEdges()){
					org.xdb.elasticpartitioning.graph.Node child = inEdge.getSource();
					if (alreadyVisitedNodes.contains(child)) continue;
					config.addPartitionedNode(child, PartitioningType.Reference);
					double expectedSizeFactor = sizeFactor.get(n);
					if (expectedSizeFactor > numberOfPartitions) expectedSizeFactor = numberOfPartitions;
					sizeFactor.put(child, expectedSizeFactor);
					queue.add(child);
					System.out.println("\t" + "table " + child.getContent().getTableName() + " will be R, size: " + child.getContent().getTableSize() * sizeFactor.get(child));

				}
			}
			config.setDataSize(totalSize);
			System.out.println("\t" + totalSize);
			if (totalSize < optimalConfig.getDataSize()) optimalConfig = config;
		}
		System.out.println("*********************");
		System.out.println("The optimal node to hash is: " + optimalConfig );
	}
	public static void main(String[] args) {
		Runner runner = new Runner();
		try {
			// First, read the XML file, and make the reference graph
			ReferenceGraph refGraph = runner.loadXMLConfig(FilesLocation.SCHEMA_FILE);

			// Then, make the MASP
			ReferenceGraph MASP = refGraph.findMASP();
			System.out.println(MASP);
			Map<Edge, Double> redundancyFactor = runner.findEdgesRedundancyFactor(MASP);
			System.out.println("Redundancy factors are calculated.");
			
			// printing the redundancyFactor
			for (Edge e : redundancyFactor.keySet())
				System.out.println(e + " : " + redundancyFactor.get(e));
			runner.findOptimalConfigurationInMASP(MASP, redundancyFactor);;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}