package org.xdb.spotgres.pojos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
import javax.persistence.Transient;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.annotations.GenericGenerator;
import org.xdb.spotgres.pojos.ClusterSetup.NodeMap.NodeEntry;
import org.xdb.spotgres.pojos.NodePrice.PRICETYPE;

@Entity(name = "ClusterSetup")
public class ClusterSetup {
	
	
	@Transient
	private ObjectMapper mapper = new ObjectMapper();
	
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="clusterSetup_clusterNode", joinColumns={@JoinColumn(name="clusterSetupId")},inverseJoinColumns={@JoinColumn(name="clusterNodeId")})
    @JoinColumn(name="clusterNodeId")
    private List<ClusterNode> nodesList;
	
    @Transient
    float availability;
    
	public ClusterSetup() {
	}

	public static class NodeMap{
		public static class NodeEntry {
			private String type;
			private int count;
			private float bidPrice;
			private NodePrice.PRICETYPE bidType;
			
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
			public float getBidPrice() {
				return bidPrice;
			}
			public void setBidPrice(float bidPrice) {
				this.bidPrice = bidPrice;
			}
			public NodePrice.PRICETYPE getBidType() {
				return bidType;
			}
			public void setBidType(NodePrice.PRICETYPE bidType) {
				this.bidType = bidType;
			}
			public NodeEntry(){
				
			}
			
			@JsonIgnore
			public float getPrice(){
				return bidPrice*count;
			}
			
			public NodeEntry(String type) {
				super();
				this.type = type;
				this.count=0;
				this.bidPrice=0f;
				this.bidType=PRICETYPE.SPOT;
			}
			public NodeEntry(String type, int count, float bidPrice, PRICETYPE bidType) {
				super();
				this.type = type;
				this.count = count;
				this.bidPrice = bidPrice;
				this.bidType = bidType;
			}
			
			/**
			 * Checks whether the provided entry is of the same type and count
			 * !!! IGNORES THE BID PRICE !!!
			 * @param otherEntry
			 * @return
			 */
			public boolean isTheSameEntry(NodeEntry otherEntry){
				boolean returnValue = false;
				if (otherEntry != null){
					if(this.type.equals(otherEntry.getType())){
						if(this.count==otherEntry.getCount()){
							returnValue=true;
						}
					}
				}
				return returnValue;
			}
		}
		private Collection<NodeEntry> nodes;
		public Collection<NodeEntry> getNodes() {
			return nodes;
		}
		public void setNodes(Collection<NodeEntry> nodes) {
			this.nodes = nodes;
		}
		public void addEntry(String type, NodeEntry entry){
			
		}
		public NodeMap(Map<String, NodeEntry> map) {
			nodes = new ArrayList<NodeEntry>();
			for (Entry<String, NodeEntry> entry : map.entrySet()) {
				nodes.add(entry.getValue());
			}
		}
		public NodeMap(){
			
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
	Map<String, NodeEntry> nodeEntryMap = new HashMap<String, NodeEntry>();;

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

	public Map<String, NodeEntry> getNodes() {
		return nodeEntryMap;
	}

	public void setNodes(Map<String, NodeEntry> nodes) {
		this.nodeEntryMap = nodes;
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
		NodeMap nodeMap = new NodeMap(nodeEntryMap);
		nodesJSON=mapper.writeValueAsString(nodeMap);
	}
	
	private void transformJSONtoMap() throws JsonParseException, JsonMappingException, IOException{
		NodeMap nodeMap = mapper.readValue(nodesJSON, NodeMap.class);
		nodeEntryMap = new HashMap<String, NodeEntry>();
		if (nodeMap != null){
			for (NodeEntry entry : nodeMap.getNodes()) {
				nodeEntryMap.put(entry.getType(), entry);
			}			
		}
	}
	
	public void addNodes(String nodeType, int amount){
		NodeEntry nodeEntry = nodeEntryMap.get(nodeType);
		if (nodeEntry == null){
			nodeEntry = new NodeEntry(nodeType);
		}
		Integer initialAmount = nodeEntry.getCount();
		if (initialAmount == null){
			initialAmount = Integer.valueOf(amount);
		} else {
			initialAmount += amount;
		}
		nodeEntry.setCount(initialAmount);
		nodeEntryMap.put(nodeType, nodeEntry);
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
	
	public void setBidPrice(String nodeType, float bidPrice, PRICETYPE priceType){
		NodeEntry nodeEntry = nodeEntryMap.get(nodeType);
		if (nodeEntry == null){
			nodeEntry = new NodeEntry(nodeType);
		}
		nodeEntry.setBidPrice(bidPrice);
		nodeEntry.setBidType(priceType);
		nodeEntryMap.put(nodeType, nodeEntry);
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
		for (Entry<String, NodeEntry> nodeType : nodeEntryMap.entrySet()) {
			returnValue.append(nodeType.getValue().getCount());
			returnValue.append("x ");
			returnValue.append(nodeType.getKey());
			returnValue.append(" @ ");
			returnValue.append(nodeType.getValue().getBidPrice());
			returnValue.append("\n");
		}		
		return returnValue.toString();
		
	}
	
	/**
	 * Checks whether the provided cluster setup consists of the same node types & count.
	 * !!! IGNORES THE BID PRICE !!!
	 * @param otherSetup
	 * @return
	 */
	public boolean isTheSameSetup(ClusterSetup otherSetup){
		boolean returnValue = false;
		Map<String, NodeEntry> otherNodes = otherSetup.getNodes();
		if (this.nodeEntryMap.size() == otherNodes.size()){
			boolean isTheSame = true;
			Iterator<NodeEntry> entryIter = nodeEntryMap.values().iterator();
			while (entryIter.hasNext() && isTheSame) {
				ClusterSetup.NodeMap.NodeEntry nodeEntry = (ClusterSetup.NodeMap.NodeEntry) entryIter.next();
				isTheSame=nodeEntry.isTheSameEntry(otherNodes.get(nodeEntry.getType()));
			}
			returnValue = isTheSame;
		}
		return returnValue;
	}

	@Override
	public ClusterSetup clone() throws CloneNotSupportedException {
		ClusterSetup clone = new ClusterSetup();
		clone.setNodesJSON(String.valueOf(this.nodesJSON));
		return clone;
	}
	
	@JsonIgnore
	public float getClusterPrice(){
		float returnValue = 0f;
		for (NodeEntry currentEntry:nodeEntryMap.values()){
			returnValue += currentEntry.getPrice();
		}
		return returnValue;
	}

	public float getAvailability() {
		return availability;
	}

	public void setAvailability(float availability) {
		this.availability = availability;
	}
	
	
}
