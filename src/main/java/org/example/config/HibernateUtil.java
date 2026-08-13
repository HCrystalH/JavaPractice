package org.example.config;

import org.example.entity.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static SessionFactory createSessionFactory(){
        return new Configuration()
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
    }
}
