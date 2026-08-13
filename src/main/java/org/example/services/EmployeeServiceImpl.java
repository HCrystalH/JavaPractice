package org.example.services;

import org.example.dto.request.EmployeeRequest;
import org.example.dto.response.EmployeeResponse;
import org.example.entities.Employee;
import org.example.repository.EmployeeRepository;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    // Constructor injection
    EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void add(EmployeeRequest employeeRequest) {
        if(employeeRequest == null){
            // for simple just print out
            System.out.println("Employee is null");
            return;
        }

        employeeRepository.save(convertToEntity(employeeRequest));
    }

    @Override
    public EmployeeResponse findById(int id) {
         return convertToDto(employeeRepository.findById(id).orElse(null));
    }

    @Override
    public List<EmployeeResponse> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public void update(EmployeeRequest employee) {
        employeeRepository.update(convertToEntity(employee));
    }

    @Override
    public void delete(EmployeeRequest employee) {
        if(findById(employee.id()) == null){
            System.out.println("Employee not found");
            return;
        }

        employeeRepository.delete(convertToEntity(employee));
    }

    // Helper method
    // Mapper
    private Employee convertToEntity(EmployeeRequest employeeRequest){
        Employee employee = new Employee();

        employee.setId(employeeRequest.id());   // id for delete, update request
        employee.setName(employeeRequest.name());
        employee.setSalary(employeeRequest.salary());
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
