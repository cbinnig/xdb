package org.xdb.spotgres;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

  private static SessionFactory sessionFactory;
 private static ServiceRegistry serviceRegistry;

  public static SessionFactory configureSessionFactory() throws HibernateException {
     Configuration configuration = new Configuration();
     configuration.configure();
     serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
     sessionFactory = configuration.buildSessionFactory(serviceRegistry);
     return sessionFactory;
 }
}