package Utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
    private static final SessionFactory sF = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try{
            // create the servise registry from hibernate.cfg.xml
            ServiceRegistry sR = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

            //Create metadata sources using the specified servise registry.
            Metadata metadata = new MetadataSources(sR).getMetadataBuilder().build();

            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex){
            System.err.println("init sesfactory creation failed" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFacroty(){
        return sF;
    }

    public static void shutdown(){
        getSessionFacroty().close();
    }
}
