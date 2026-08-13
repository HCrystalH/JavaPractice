package org.example.services;

import org.example.dto.request.EmployeeRequest;
import org.example.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    void add (EmployeeRequest employee);
    EmployeeResponse findById(int id);
    List<EmployeeResponse> findAll();
    void update(EmployeeRequest employee);
    void delete(EmployeeRequest employee);
}
