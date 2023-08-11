package ru.reydenge.simpleserviceh.util;

import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.reydenge.simpleserviceh.entity.Elective;
import ru.reydenge.simpleserviceh.entity.Student;
import ru.reydenge.simpleserviceh.entity.University;

public class HBUtils {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {

            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            configuration.addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Elective.class)
                    .addAnnotatedClass(University.class);

            sessionFactory = configuration
                    .buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }
}
