package org.xdb.spotgres;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.xdb.logging.XDBLog;
import org.xdb.spotgres.pojos.ClusterConstraints;
import org.xdb.spotgres.pojos.NodeType;

@SuppressWarnings("unused")
public class ClusterConfiguration {
	private Session session;
	private SessionFactory sessionFactory;
	protected Logger logger;
	
	private ClusterConstraints constraint;
	private List<NodeType> availableNodeTypes;
	private List<NodeType> adjustedNodeTypes;
	
	private void initHibernate() {
		sessionFactory = HibernateUtil.configureSessionFactory();
		session = sessionFactory.getCurrentSession();
	}
	
	public void setUp() throws IOException {
		initHibernate();
		logger = XDBLog.getLogger(this.getClass().getName());
	}
	
	@SuppressWarnings("unchecked")
	private void loadNodeTypes(){
		logger.log(Level.INFO, "Calculating availability per NodeType & Zone");
		session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		this.availableNodeTypes = session.createQuery("from NodeType").list();
		tx.commit();
		for (NodeType nodeType : availableNodeTypes) {
			System.err.println(nodeType);
		}
	}
	
	private void execute() throws IOException{
		setUp();
		loadNodeTypes();
	}
	
	public static void main(String[] args) {
		ClusterConfiguration conf = new ClusterConfiguration();
		try {
			conf.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
