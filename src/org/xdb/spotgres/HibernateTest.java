package org.xdb.spotgres;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.xdb.spotgres.pojos.NodeType;

public class HibernateTest {
	public static void main(String[] args) {
        Session session = null;
        SessionFactory sessionFactory =new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();

        NodeType nodeType = new NodeType();

        nodeType.setTypeName("m1.xlarge");
        nodeType.setCpuCount(8);
        nodeType.setRam(16*1024*1024);
        nodeType.setHdd(80);
        
        session.save(nodeType);
        session.flush();
        session.close();		
	}
}
