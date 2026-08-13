package org.example.repository;

import org.example.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    void save(Employee employee);
    void update(Employee employee);
    void delete(Employee employee);
    List<Employee> findAll();
    Optional<Employee> findById(int id);
}
