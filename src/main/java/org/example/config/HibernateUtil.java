package org.example.config;

import lombok.Getter;
import org.example.entity.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    @Getter
    private static final SessionFactory sessFactory =
            new Configuration()
                    .addAnnotatedClass(Employee.class)
                    .buildSessionFactory();

    public static SessionFactory createSessionFactory(){
        return new Configuration()
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
    }
}
