package org.xdb.spotgres.pojos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.xdb.spotgres.pojos.ClusterSetup.NodeMap.NodeEntry;

@Entity(name = "ClusterSetup")
public class ClusterSetup {
	
	
	@Transient
	private ObjectMapper mapper = new ObjectMapper();
	
	@Transient
	private Map<String, NodeType> nodeTypes;
	
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="clusterSetup_clusterNode", joinColumns={@JoinColumn(name="clusterSetupId")},inverseJoinColumns={@JoinColumn(name="clusterNodeId")})
    @JoinColumn(name="clusterNodeId")
    private List<ClusterNode> nodesList;
	
	public ClusterSetup() {
	}

	public static class NodeMap{
		public static class NodeEntry {
			private String type;
			private int count;
			public String getType() {
				return type;
			}
			public void setType(String type) {
				this.type = type;
			}
			public int getCount() {
				return count;
			}
			public void setCount(int count) {
				this.count = count;
			}
			public NodeEntry(String type, int count) {
				super();
				this.type = type;
				this.count = count;
			}
			
		}
		private Collection<NodeEntry> nodes;
		public Collection<NodeEntry> getNodes() {
			return nodes;
		}
		public void setNodes(Collection<NodeEntry> nodes) {
			this.nodes = nodes;
		}
		public void addEntry(String type, int count){
			
		}
		public NodeMap(Map<String, Integer> map) {
			nodes = new ArrayList<NodeEntry>();
			for (Entry<String, Integer> entry : map.entrySet()) {
				nodes.add(new NodeEntry(entry.getKey(), entry.getValue().intValue()));
			}
		}
		
	}
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name="clusterSetupId")
	int clusterSetupId;
	
	@Column(name="nodesJSON")
	String nodesJSON;
	
	@Transient
	Map<String, Integer> nodesTypeCount = new HashMap<String, Integer>();;

	public String getNodesJSON() {
		return nodesJSON;
	}

	public void setNodesJSON(String nodesJSON) {
		this.nodesJSON = nodesJSON;
		try {
			transformJSONtoMap();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Integer> getNodes() {
		return nodesTypeCount;
	}

	public void setNodes(Map<String, Integer> nodes) {
		this.nodesTypeCount = nodes;
		try {
			updateNodeMap();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getClusterSetupId() {
		return clusterSetupId;
	}
	
	private void updateNodeMap() throws JsonGenerationException, JsonMappingException, IOException{
		NodeMap nodeMap = new NodeMap(nodesTypeCount);
		nodesJSON=mapper.writeValueAsString(nodeMap);
	}
	
	private void transformJSONtoMap() throws JsonParseException, JsonMappingException, IOException{
		NodeMap nodeMap = mapper.readValue(nodesJSON, NodeMap.class);
		nodesTypeCount = new HashMap<String, Integer>();
		for (NodeEntry entry : nodeMap.getNodes()) {
			nodesTypeCount.put(entry.getType(), entry.getCount());
		}
	}
	
	public void addNodes(String nodeType, int amount){
		Integer initialAmount = nodesTypeCount.get(nodeType);
		if (initialAmount == null){
			initialAmount = Integer.valueOf(amount);
		} else {
			initialAmount += amount;
		}
		nodesTypeCount.put(nodeType, initialAmount);
		try {
			updateNodeMap();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		StringBuilder returnValue = new StringBuilder();
		returnValue.append("Cluster Setup\n");
		returnValue.append("-------------\n");
		for (Entry<String, Integer> nodeType : nodesTypeCount.entrySet()) {
			returnValue.append(nodeType.getValue().toString());
			returnValue.append("x ");
			returnValue.append(nodeType.getKey()).append("\n");
		}		
		return returnValue.toString();
		
	}
	
	
}
