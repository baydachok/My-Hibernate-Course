package ru.baydak.util;

import ru.baydak.converter.BirthdayConverter;
import ru.baydak.entity.Audit;
import ru.baydak.entity.Revision;
import ru.baydak.interceptor.GlobalInterceptor;
import ru.baydak.listener.AuditTableListener;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;

@UtilityClass // private constructor and final class
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = buildConfiguration();
        configuration.configure();
        return configuration.buildSessionFactory();
    }

    private static void registerListeners(SessionFactory sessionFactory) {
        SessionFactoryImpl sessionFactoryImpl = sessionFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry listenerRegistry = sessionFactoryImpl.getServiceRegistry().getService(EventListenerRegistry.class);
        AuditTableListener auditTableListener = new AuditTableListener();

        if (listenerRegistry != null) {
            listenerRegistry.appendListeners(EventType.PRE_INSERT, auditTableListener);
            listenerRegistry.appendListeners(EventType.PRE_DELETE, auditTableListener);
        }
    }

    public static Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAttributeConverter(new BirthdayConverter(), true);
        configuration.addAnnotatedClass(Audit.class);
        configuration.addAnnotatedClass(Revision.class);
        configuration.setInterceptor(new GlobalInterceptor());
        return configuration;
    }
}
