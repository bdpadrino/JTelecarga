package sys.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;


public class HibernateUtilST {
    

    private static final SessionFactory sessionFactory;

    static {
            try {
            	Configuration cfg = new Configuration().configure("hibernateST.cfg.xml");        	
            	StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
            	sb.applySettings(cfg.getProperties());
            	StandardServiceRegistry standardServiceRegistry = sb.build();           	
            	sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);      	
            } catch (Throwable th) {
                    System.err.println("Enitial SessionFactory creation failed" + th);
                    throw new ExceptionInInitializerError(th);
            }
    }
    public static SessionFactory getSessionFactory() {
            return sessionFactory;
    }
        
    public static Connection getConnectionHibernate() throws SQLException{
        Connection c = getSessionFactory().getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class).getConnection();
        return c;    
    } 
}

