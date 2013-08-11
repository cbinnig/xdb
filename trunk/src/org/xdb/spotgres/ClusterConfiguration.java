package org.xdb.spotgres;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.xdb.logging.XDBLog;
import org.xdb.spotgres.pojos.ClusterConstraints;
import org.xdb.spotgres.pojos.NodePrice;
import org.xdb.spotgres.pojos.NodeType;

@SuppressWarnings("unused")
public class ClusterConfiguration {
	private Session session;
	private SessionFactory sessionFactory;
	protected Logger logger;
	
	private ClusterConstraints constraint;
	private ClusterCalculationTools toolkit;
	private Map<String, ClusterPriceHelper> helpers;
	
	private List<NodeType> availableNodeTypes;
	private List<NodeType> adjustedNodeTypes;
	private Map<String, NodePrice> currentSpotPrices;
	private Map<String, NodePrice> onDemandPrices;
	
	private void initHibernate() {
		sessionFactory = HibernateUtil.configureSessionFactory();
		session = sessionFactory.getCurrentSession();
	}
	
	public void setUp() throws IOException {
		initHibernate();
		toolkit=new ClusterCalculationTools();
		sessionFactory = HibernateUtil.configureSessionFactory();
		session = sessionFactory.getCurrentSession();
		logger = XDBLog.getLogger(this.getClass().getName());
	}
	
	
	
	public ClusterConstraints getConstraint() {
		return constraint;
	}

	public void setConstraint(ClusterConstraints constraint) {
		this.constraint = constraint;
	}

	private void initiateHelpers(){
		helpers=new HashMap<String, ClusterPriceHelper>();
		for (NodeType nodeType : availableNodeTypes) {
			ClusterPriceHelper helper = new ClusterPriceHelper();
			helper.setNodeType(nodeType);
			helper.setOnDemandPrice(onDemandPrices.get(nodeType.getTypeName()));
			helper.setCurrentSpotPrice(currentSpotPrices.get(nodeType.getTypeName()));
			helpers.put(nodeType.getTypeName(), helper);
		}
	}
	
	private void execute() throws IOException{
		setUp();
		availableNodeTypes = toolkit.loadNodeTypes();
		currentSpotPrices = toolkit.loadCurrentSpotPrices();
		onDemandPrices = toolkit.loadOnDemandPrices();
		initiateHelpers();
		for (ClusterPriceHelper helper : helpers.values()) {
			System.out.println("Nodetype: " +helper.getNodeType().getTypeName());
			System.out.println("CUs: " +helper.getNodeType().getCuCount());
			System.out.println("RAM: " +helper.getNodeType().getRam());
			System.out.println("CUs with "+ constraint.getRamPerCu()+ "MB RAM: " +helper.getNodeType().getCuByRam(constraint.getRamPerCu()));
			System.out.println("Current Spot Price: " +helper.getCurrentSpotPrice().getPrice());
			System.out.println("Current Spot Price per CU: " +helper.getSpotPricePerCU());
			System.out.println("Current Spot Price per CU&RAM: " +helper.getSpotPricePerCUPerRam(constraint.getRamPerCu()));
			System.out.println("Current OnDemand Price: " +helper.getOnDemandPrice().getPrice());
			System.out.println("Current OnDemand Price per CU: " +helper.getOnDemandPricePerCU());
			System.out.println("Current OnDemand Price per CU&RAM: " +helper.getOnDemandPricePerCUPerRam(constraint.getRamPerCu()));
			int nodeCount=(int) Math.ceil(1d * constraint.getCuCount() / helper.getNodeType().getCuByRam(constraint.getRamPerCu()));
			System.out.println("Nodes of this type neded to match CU/Ram contraints: "+nodeCount);
			System.out.println("Total Price per Hour using Spot Instances: " +nodeCount*helper.getCurrentSpotPrice().getPrice());
			System.out.println("Total Price per Hour using OnDemand Instances: " +nodeCount*helper.getOnDemandPrice().getPrice());
			System.out.println("--------------");
		}
		System.out.println("all loaded!");
	}
	
	public static void main(String[] args) {
		ClusterConfiguration conf = new ClusterConfiguration();
		ClusterConstraints constraint = new ClusterConstraints();
		constraint.setConnectivity(80f);
		constraint.setCuCount(40);
		constraint.setMoneyPerHour(6);
		constraint.setRamPerCu(1024);
		conf.setConstraint(constraint);
		try {
			conf.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
