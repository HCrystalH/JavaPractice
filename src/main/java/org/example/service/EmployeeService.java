package org.example.service;

import org.example.dto.request.EmployeeRequest;
import org.example.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    void add (EmployeeRequest employee);
    EmployeeResponse findById(int id);
    List<EmployeeResponse> findAll();
    void update(int id, EmployeeRequest employee);
    void delete(int id);
}
