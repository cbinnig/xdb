package org.xdb.spotgres;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.xdb.spotgres.pojos.NodeType;

public class HibernateTest {
	public static void main(String[] args) {
		Session session = null;
		SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
		session = sessionFactory.getCurrentSession();

		org.hibernate.Transaction tx = session.beginTransaction();

		NodeType nodeType = new NodeType();

		nodeType.setTypeName("m1.xlarge");
		nodeType.setCpuCount(8);
		nodeType.setRam(16 * 1024 * 1024);
		nodeType.setHdd(80);

		session.persist(nodeType);
		tx.commit();
	}
}
