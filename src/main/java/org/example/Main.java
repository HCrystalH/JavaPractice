package org.example;

import org.example.config.DbInitializer;
import org.example.config.HibernateUtil;
import org.example.dto.request.EmployeeRequest;
import org.example.repository.EmployeeRepository;
import org.example.repository.EmployeeRepositoryImpl;
import org.example.service.EmployeeService;
import org.example.service.EmployeeServiceImpl;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl(sessionFactory);
        EmployeeService employeeService =  new EmployeeServiceImpl(employeeRepository);

        EmployeeRequest request = new EmployeeRequest(
                "Hoang",
                24,
                "IT",
                new BigDecimal("7000.00")
        );
        employeeService.add(request);

        sessionFactory.close();
    }
}