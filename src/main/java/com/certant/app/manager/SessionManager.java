package com.certant.app.manager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;

public class SessionManager {
    private static SessionFactory sessionFactory;
    
    /**
     * Retorna singleton de session, se verifica que no exista
     * otra instancia de sessionFactory
     * @return
     * @throws HibernateException 
     */
    public static Session getSession() throws HibernateException {
        if (sessionFactory != null) {
            return sessionFactory.openSession();
        } 
        else {
            Configuration cfg = new Configuration().configure();
            StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
            
            sb.applySettings(cfg.getProperties());
            
            StandardServiceRegistry standardServiceRegistry = sb.build();
            
            //Se setea observer, ya que si no, la conexión con la BD no finaliza
            cfg.setSessionFactoryObserver(
                new SessionFactoryObserver() {
                    @Override
                    public void sessionFactoryCreated(SessionFactory factory) {
                    }

                    @Override
                    public void sessionFactoryClosed(SessionFactory factory) {
                        ((StandardServiceRegistryImpl) standardServiceRegistry).destroy();
                    }
                }
            );
            
            sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);
            
            return sessionFactory.openSession();
        }        
    }
    
    /**
     * Retorna un sessionFactory
     * @return 
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    /**
     * Cierra un sessionFactory
     */
    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}