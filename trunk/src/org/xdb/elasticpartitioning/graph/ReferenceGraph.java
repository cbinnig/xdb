package org.xdb.elasticpartitioning.graph;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.xdb.elasticpartitioning.MutualRedundancySizeFinder;
import org.xdb.elasticpartitioning.database.ForeignKey;
import org.xdb.elasticpartitioning.database.Query;
import org.xdb.elasticpartitioning.database.Table;
import org.xdb.elasticpartitioning.partition.PartitioningConfiguration;
import org.xdb.elasticpartitioning.partition.PartitioningMetaData;
import org.xdb.elasticpartitioning.partition.PartitioningType;
import org.xdb.elasticpartitioning.util.MyMath;



public class ReferenceGraph {
	private List<Node> nodes;
	private List<Edge> edges;
	//private Map<Node, Set<Node>> nodeNeighbors;
	private Map<String, Node> tableNameToNodeMap;
	private Map<String, Edge> edgeInfoToEdgeMap;
	private List<Query> queries;
	private PartitioningConfiguration partitioningConfiguration;
	private static Map<Edge, Double> redundancyFactor = null;
	private static PartitioningMetaData pmd = null;
	

	public ReferenceGraph() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		tableNameToNodeMap = new HashMap<String, Node>();
		edgeInfoToEdgeMap = new HashMap<String, Edge>();
		queries = new ArrayList<Query>();
		partitioningConfiguration = null;
	}
	public ReferenceGraph(ReferenceGraph original) {
		this();
		
		for (Node node: original.getNodes())
			this.addNode(new Node(node));
		
		for (Edge edge: original.getEdges()){
			this.addEdge(edge.getSource().getContent().getTableName(),
					edge.getDestination().getContent().getTableName(),
					edge.getRelation().getFirstSourceAttribute(),
					edge.getRelation().getFirstTargetAttribute());
		}
		
		for (Query originalQuery: original.queries){
			Query newQuery = new Query(originalQuery.getQueryID());
			for(Edge originalEdge: originalQuery.getJoinPath())
				newQuery.addEquiJoinPredicate(originalEdge.getRelation(), originalEdge.getSource().getContent(),
						originalEdge.getDestination().getContent(), 0);
			this.addQuery(newQuery);
		}
		
		this.partitioningConfiguration = original.partitioningConfiguration;
	}
	
	public void addNode(Node node){
		this.nodes.add(node);
		tableNameToNodeMap.put(node.getContent().getTableName(), node);
	}

	public Edge addEdge(ForeignKey fk, Node source, Node target, double weight){
		Edge e = new Edge(fk, source, target, weight);
		source.addOutEdge(e);
		target.addInEdge(e);
		edges.add(e);
		return e;	
	}
	
	public Edge addEdge(String sourceTable, String targetTable,
			String sourceAttribute, String targetAttribute) {
		// just to make sure that A.a = B.b maps to the same bucket
		// where B.b = A.a has already mapped.
		Node source = getNodeWithTableName(sourceTable);
		Node target = getNodeWithTableName(targetTable);
		ForeignKey fk = new ForeignKey(source.getContent(), target.getContent());
		fk.addRelations(sourceAttribute, targetAttribute);
		Edge e = new Edge(fk, source, target, 0);
		source.addOutEdge(e);
		target.addInEdge(e);
		edges.add(e);
				
		edgeInfoToEdgeMap.put(e.getNormalizedStringRepresentation(), e);
		return e;
	}
	
	public boolean containsNode(Node node) {
		return tableNameToNodeMap.containsKey(node.getContent().getTableName());
	}
	public boolean containsEdge(Edge edge){
		return edgeInfoToEdgeMap.containsKey(edge.getNormalizedStringRepresentation());
	}

	public List<Node> getNodes() {
		return nodes;
	}
	
	public List<Edge> getEdges() {
		return edges;
	}
	
	public PartitioningConfiguration getPartitioningConfiguration() throws GraphNotConnectedException {
		if (partitioningConfiguration == null)
			partitioningConfiguration = findOptimalConfigurationInAConnectedComponent();
		return partitioningConfiguration;
	}
	
	public void setPartitioningConfiguration(PartitioningConfiguration partConfiguration) {
		this.partitioningConfiguration = partConfiguration;
	}
	
	public static void setRedundancyFactor(Map<Edge, Double> redundancyFactor) {
		ReferenceGraph.redundancyFactor = redundancyFactor;
	}
	
	public static void setPartitioningMetaData(PartitioningMetaData pmd) {
		ReferenceGraph.pmd = pmd;
	}

	@Override
	public String toString() {
		String output = new String();
		for (Node n : this.getNodes()){
			for (Edge e : n.getOutEdges()){
				output += n + " --> " + e.getDestination() + " [weight: " + e.getWeight() + "]";
				output += '\n';
			}
		}
		return output;
	}

	public ReferenceGraph findMASP(){
		Map<Table,Set<Table>> forest = new HashMap<Table,Set<Table>>();
		for(Node node: this.nodes){
			//Each set stores the known vertices reachable from this vertex
			//initialize with it self.
			Set<Table> vs = new HashSet<Table>();
			vs.add(node.getContent());
			forest.put(node.getContent(), vs);
		}
		
		// make a copy of edges. We don't want to mess with the real edges
		List<Edge> copyEdges = new ArrayList<Edge>();
		for (Edge edge : edges)
			copyEdges.add(edge.copyEdge());
		
		// Creating an empty graph, and copying nodes (not edges) from the original ReferenceGraph
		ReferenceGraph MASP = new ReferenceGraph();
		for (Node node: nodes){
			Node newNode = new Node(node.getContent());
			MASP.addNode(newNode);
		}
		//sorting the edges
		Collections.sort(copyEdges);
		
		
		while(MASP.edges.size() < this.nodes.size()-1) //while you haven't visited all the vertices at least once
		{
		    Edge edge = copyEdges.remove(0); // popping the edge with the largest weight

		    Set<Table> visited1 = forest.get(edge.getSource().getContent());
		    Set<Table> visited2 = forest.get(edge.getDestination().getContent());
		    if (visited1.equals(visited2)) continue;
		    
		    Node sourceInMASP = MASP.getNodeWithTableName(edge.getSource().getContent().getTableName());
		    Node targetInMASP = MASP.getNodeWithTableName(edge.getDestination().getContent().getTableName());
		    
		    MASP.addEdge(edge.getRelation(), sourceInMASP, targetInMASP, edge.getWeight());
		    visited1.addAll(visited2);
		    for(Table t : visited1)
		    	forest.put(t, visited1);
		}
		return MASP;
	}
	
	public Node getNodeWithTableName(String tableName) {
		if (tableNameToNodeMap.containsKey(tableName))
			return tableNameToNodeMap.get(tableName);
		else return null;
	}
	public Edge getEdgeWithTableNames(String sourceTable, String targetTable, String sourceAttribute, String targetAttribute) {
		String representation = Edge.normalizedStringRepresentation(sourceTable, targetTable, sourceAttribute, targetAttribute);
		if (edgeInfoToEdgeMap.containsKey(representation))
			return edgeInfoToEdgeMap.get(representation);
		else return null;
	}
	
	public void addQuery(Query query) {
		this.queries.add(query);
	}
	
	public List<Query> getQueries() {
		return queries;
	}
	
	public void removeRedundantQueries(){
		boolean[] toBeRemovedQueriesIndexes = new boolean[queries.size()];	// default value is false
		for (int i=0; i<queries.size(); i++){
			if (toBeRemovedQueriesIndexes[i]) continue;
			for (int j=0; j<queries.size();j++){
				if (i==j) continue;
				if (queries.get(i).isSuperSetOf(queries.get(j)))
					toBeRemovedQueriesIndexes[j]=true;
			}
		}
		
		// now remove all the elements with flag=true
		// in order to make sure that deletion doesn't cause mess with iterator, 
		// we start from the end and come to the start
		for (int i=queries.size()-1; i>=0; i--)
			if (toBeRemovedQueriesIndexes[i]){
				System.out.println("Query " + queries.get(i).toString() + " is discarded.");
				queries.remove(i);
			
			}
	}
	
	public SubGraph findBestGraphPartitioning() throws Exception{
		if (ReferenceGraph.redundancyFactor == null)
			throw new Exception("Redundancy factor is not set.");
		if (pmd == null)
			throw new Exception("Partitioning MetaData is not set.");
		
		List<Map<String, SubGraph>> levelSubGraph = new ArrayList<Map<String, SubGraph>>();
		Map<String, SubGraph> bestSubGraph = new HashMap<String, SubGraph>();
		Set<Integer> queryIDs = new HashSet<Integer>();
		for (Query query: queries){
			SubGraph sg = new SubGraph();
			List<Query> queryList = new ArrayList<Query>();
			queryList.add(query);
			sg.addConnectedComponent(queryList);
			bestSubGraph.put(sg.getStringRepresentation(), sg);
			
			queryIDs.add(query.getQueryID());
		}
		levelSubGraph.add(bestSubGraph);
		
		List<List<Integer>> allSubSets = MyMath.powerSet(queryIDs);
		
		Comparator<List<Integer>> comparator = new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				if (o1.size() < o2.size()) return -1;
				else return 1;
			}
		};
		Collections.sort(allSubSets, comparator);
		
		if (allSubSets.get(0).size() != 0)
			throw new Exception("First element of the subset list is expected to be empty set");
		else allSubSets.remove(0);
		
		
		List<Integer> setOfAllQueries = allSubSets.get(allSubSets.size()-1);
		Collections.sort(setOfAllQueries);
		
		
		// we also remove the last element of the list since it contains all queries
		// and cannot be merged with any query (since it already contains them
		allSubSets.remove(allSubSets.size()-1);
		
		
		int level = 1;
		levelSubGraph.add(levelSubGraph.get(0));
		Map<String, SubGraph> bestPreviousLevelSubGraph = levelSubGraph.get(0);
		Map<String, SubGraph> bestCurrentLevelSubGraph = new HashMap<String, SubGraph>();
		
		for (List<Integer> subSet: allSubSets){
			Collections.sort(subSet);
			if (subSet.size() > level){
				System.out.println(subSet.size());
				level = subSet.size();
				// now, the old "current-level" becomes current "old level"
				// and we add a new empty "current-level"
				bestPreviousLevelSubGraph = new HashMap<String, SubGraph>(bestCurrentLevelSubGraph);
				bestCurrentLevelSubGraph = new HashMap<String, SubGraph>();
			}
			
			for (Query query: queries){
				if (subSet.contains(query.getQueryID()))
					continue;
				
				List<Query> wrapper = new ArrayList<Query>();
				wrapper.add(query);
				ReferenceGraph singleQueryRefGraph = levelSubGraph.get(0).get(wrapper.toString()).getConnectedComponents().get(0);
				
				SubGraph sg = bestPreviousLevelSubGraph.get(subSet.toString());
				SubGraph currentGraph = sg.mergeQuery(singleQueryRefGraph);
				//for (ReferenceGraph rg: currentGraph.getConnectedComponents())
				//	System.out.print(rg.getQueries());
				//System.out.println();
				String newSet = currentGraph.getStringRepresentation();
				if (!bestCurrentLevelSubGraph.containsKey(newSet) || bestCurrentLevelSubGraph.get(newSet).getTotalCost() > currentGraph.getTotalCost())
					bestCurrentLevelSubGraph.put(newSet, currentGraph);
			}
		}
		double totalSize=0;
		SubGraph finalGraph = bestCurrentLevelSubGraph.get(setOfAllQueries.toString());
		
		System.out.println("Final Graph:");
		int i=1;
		for (ReferenceGraph refGraph: finalGraph.getConnectedComponents()){
			System.out.println("-------- Component " + i + " --------");
			System.out.println(refGraph.getPartitioningConfiguration().toString());
			System.out.println();
			totalSize += refGraph.getPartitioningConfiguration().getDataSize();
			i++;
		}
		double originalSize = 0;
		for (String tableName: this.tableNameToNodeMap.keySet())
			originalSize += tableNameToNodeMap.get(tableName).getContent().getTableSize();
		System.out.println();
		System.out.println("Size after partitioning: " + Math.round(originalSize*100)/100 );
		System.out.println("Size after partitioning: " + Math.round(totalSize*100)/100 );
		return null;
	}
	
	public static Map<Edge, Double> findEdgesRedundancyFactor(ReferenceGraph refGraph, PartitioningMetaData pmd) throws Exception{
		try{
			MutualRedundancySizeFinder redFinder = new MutualRedundancySizeFinder(pmd.getNumberOfPartitions(), pmd.getSampleSizeRaio());
			Map<Edge, Double> redundancyFactor = new HashMap<Edge, Double>();

			for (Node node: refGraph.getNodes()){
				for (Edge e: node.getOutEdges()){
					Table sourceTable = node.getContent();
					Table destTable = e.getDestination().getContent();

					ForeignKey fk = e.getRelation();
					double sizeAfterPartitioning = redFinder.findRedundancy(sourceTable, destTable, fk);
					double sizeBeforePartitioning = destTable.getTableSize();
					double rf = sizeAfterPartitioning/sizeBeforePartitioning;
					redundancyFactor.put(e, rf);
					System.out.println("Red-factor for edge " + e + " = " + rf);
				}
			}
			
			// dbTime += redFinder.getDbTime();
			// calculationTime += redFinder.getCalculationTime();
			
			System.out.println("Maximum frequency: " + redFinder.getMaxFrequency());
			return redundancyFactor;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	public PartitioningConfiguration findOptimalConfigurationInAConnectedComponent() throws GraphNotConnectedException{
		// Let's iterate over possible candidates of tables
		// for hash partitioning, and find the plan with the minimum redundancy

		PartitioningConfiguration optimalConfig = new PartitioningConfiguration();
		optimalConfig.setDataSize(Double.MAX_VALUE);

		for (Node hashedNode: this.getNodes()){
			//System.out.println("If table " + hashedNode.getContent().getTableName() + " is to be hash-partitioned:");	
			PartitioningConfiguration config = new PartitioningConfiguration();
			Queue<Node> queue = new LinkedList<Node>();
			Set<Node> alreadyVisitedNodes = new HashSet<Node>();
			queue.add(hashedNode);

			Map<Node, Double> sizeFactor = new HashMap<Node, Double>();
			sizeFactor.put(hashedNode, new Double(1));
			config.addPartitionedTable(hashedNode.getContent(), null, PartitioningType.Hash);
			//System.out.println("\t" + "table " + hashedNode.getContent().getTableName() + " will be Hash, size: " + hashedNode.getContent().getTableSize());


			double totalSize = 0;

			while (!queue.isEmpty()){
				Node n = queue.poll();
				alreadyVisitedNodes.add(n);
				totalSize += n.getContent().getTableSize() * sizeFactor.get(n);

				for (Edge outEdge : n.getOutEdges()){

					Node child = outEdge.getDestination();
					if (alreadyVisitedNodes.contains(child)) continue;

					config.addPartitionedTable(child.getContent(), n.getContent(), PartitioningType.ReverseReferece);
					double expectedSizeFactor = redundancyFactor.get(outEdge)*sizeFactor.get(n);
					if (expectedSizeFactor > pmd.getNumberOfPartitions()) expectedSizeFactor = pmd.getNumberOfPartitions();
					sizeFactor.put(child, expectedSizeFactor);
					queue.add(child);
					//System.out.println("\t" + "table " + child.getContent().getTableName() + " will be RR, size: " + child.getContent().getTableSize() * sizeFactor.get(child));

				}	
				for (Edge inEdge : n.getInEdges()){
					Node child = inEdge.getSource();
					if (alreadyVisitedNodes.contains(child)) continue;
					config.addPartitionedTable(child.getContent(), n.getContent(), PartitioningType.Reference);
					double expectedSizeFactor = sizeFactor.get(n);
					if (expectedSizeFactor > pmd.getNumberOfPartitions()) expectedSizeFactor = pmd.getNumberOfPartitions();
					sizeFactor.put(child, expectedSizeFactor);
					queue.add(child);
					//System.out.println("\t" + "table " + child.getContent().getTableName() + " will be R, size: " + child.getContent().getTableSize() * sizeFactor.get(child));

				}
			}
			if (alreadyVisitedNodes.size() < this.getNodes().size()){
				// This means that we've visited fewer nodes than they are actually in the graph. The graph is not connected then.
				throw new GraphNotConnectedException("Graph must be connected in order to find the optimal configuration");
			}
			config.setDataSize(totalSize);
			//System.out.println("\t" + totalSize);
			if (totalSize < optimalConfig.getDataSize()) optimalConfig = config;
		}
		//System.out.println("*********************");
		//System.out.println("The optimal node to hash is: " + optimalConfig );
		return optimalConfig;
	}
	
	public ReferenceGraph mergeWith(Query query) {
		ReferenceGraph newRefGraph = new ReferenceGraph(this);
		for (Edge edge: query.getJoinPath()){
			if (newRefGraph.containsEdge(edge)) continue;
			else{
				if (!newRefGraph.containsNode(edge.getSource()))
					newRefGraph.addNode(new Node(edge.getSource()));
				if (!newRefGraph.containsNode(edge.getDestination()))
					newRefGraph.addNode(new Node(edge.getDestination()));
				newRefGraph.addEdge(edge.getSource().getContent().getTableName(),
						edge.getDestination().getContent().getTableName(),
						edge.getRelation().getFirstSourceAttribute(),
						edge.getRelation().getFirstTargetAttribute());
			}
		}
		newRefGraph.addQuery(query);
		return newRefGraph;
	}
	public Set<Node> getNeighbors(String tableName){
		Node node = this.getNodeWithTableName(tableName);
		return node.getNeighbors();
	}

}