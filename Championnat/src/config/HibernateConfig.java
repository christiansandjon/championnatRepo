package config;

import java.util.Properties;


import org.hibernate.*;
import org.hibernate.boot.registry.*;
import  org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;


public class HibernateConfig
{
    private static final SessionFactory sessionFactory = buildSessionFactory();
 
    private static SessionFactory buildSessionFactory() 
    {
        try {
           
        	Configuration configuration = new Configuration();
        	Properties settings = new Properties();
        	settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        	settings.put(Environment.URL,"jdbc:mysql://localhost:3306/championnat?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        	settings.put(Environment.USER,"root");
        	settings.put(Environment.PASS,"root");
        	settings.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
        	settings.put(Environment.SHOW_SQL,"true");
        	settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
        	settings.put(Environment.HBM2DDL_AUTO,"update");
        	configuration.setProperties(settings);
        	configuration.addAnnotatedClass(model.User.class);
        	configuration.addAnnotatedClass(model.Championnat.class);
        	configuration.addAnnotatedClass(model.Equipe.class);
        	configuration.addAnnotatedClass(model.Joueur.class);
        	configuration.addAnnotatedClass(model.Match.class);
        	configuration.addAnnotatedClass(model.But.class);
        	configuration.addAnnotatedClass(model.Cartons.class);
        //	configuration.addAnnotatedClass(com.talkin.model.beans.Language.class);
        	
        	ServiceRegistry serviceRegistry =    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	
        	SessionFactory session = configuration.buildSessionFactory(serviceRegistry);
        	
        	return session;
 
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}