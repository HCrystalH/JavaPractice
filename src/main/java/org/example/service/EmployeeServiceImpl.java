package org.example.service;

import org.example.dto.request.EmployeeRequest;
import org.example.dto.response.EmployeeResponse;
import org.example.entity.Employee;
import org.example.exception.ResourceNotFound;
import org.example.repository.EmployeeRepository;

import java.util.List;

/**
 * ID must not be duplicated
 * update, delete must handle NOT FOUND employee
 * findById NOT EXIST must return suitable response
 */
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    // Constructor injection
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void add(EmployeeRequest employeeRequest) {
        if(employeeRequest == null){
            throw new IllegalArgumentException("Employee request is null");
        }

        employeeRepository.save(convertToEntity(employeeRequest));
    }

    @Override
    public EmployeeResponse findById(int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee == null){
            throw new ResourceNotFound("Employee not found");
        }
        return convertToDto(employee);
    }

    @Override
    public List<EmployeeResponse> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public void update(int id, EmployeeRequest employee) {
        Employee employeeEntity = employeeRepository.findById(id).orElse(null);
        if(employeeEntity == null){
            throw new ResourceNotFound("Employee not found");
        }

        // Convert data from Request to entity
        employeeEntity.setAge(employee.age());
        employeeEntity.setName(employee.name());
        employeeEntity.setDepartment(employee.department());
        employeeEntity.setSalary(employee.salary());

        employeeRepository.update(employeeEntity);
    }

    @Override
    public void delete(int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee == null){
            throw new ResourceNotFound("Employee not found");
        }

        employeeRepository.delete(employee);
    }

    // Helper method
    // Mapper
    private Employee convertToEntity(EmployeeRequest employeeRequest){
        Employee employee = new Employee();

        employee.setName(employeeRequest.name());
        employee.setAge(employeeRequest.age());
        employee.setDepartment(employeeRequest.department());
        employee.setSalary(employeeRequest.salary());

        return employee;
    }

    private EmployeeResponse convertToDto(Employee employee){
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getAge(),
                employee.getDepartment(),
                employee.getSalary()
        );
    }
}
