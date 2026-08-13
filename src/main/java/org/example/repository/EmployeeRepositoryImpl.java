package org.example.repository;

import org.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;


public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final SessionFactory sessionFactory;

    public EmployeeRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Employee employee) {
        try(Session session = this.sessionFactory.openSession()){
            session.beginTransaction();

            session.persist(employee);

            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Employee employee) {
        try(Session session = this.sessionFactory.openSession()){
            session.beginTransaction();

            session.merge(employee);

            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Employee employee) {
        try(Session session = this.sessionFactory.openSession()){
            session.beginTransaction();

            session.remove(employee);

            session.getTransaction().commit();
        }
    }

    @Override
    public List<Employee> findAll() {
        try(Session session = this.sessionFactory.openSession()){
            return session
                    .createQuery("FROM Employee ", Employee.class)
                    .getResultList();
        }
    }

    @Override
    public Optional<Employee> findById(int id) {
        try(Session session = this.sessionFactory.openSession()){
            return Optional.ofNullable(
                    session.find(Employee.class, id)
            );
        }
    }
}
