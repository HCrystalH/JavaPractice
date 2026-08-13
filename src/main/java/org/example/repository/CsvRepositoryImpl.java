package org.example.repository;

import org.example.entity.Employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CsvRepositoryImpl implements EmployeeRepository{
    private final Path filePath = Path.of("data", "employees.csv");

    @Override
    public void save(Employee employee) {
        // Open the existing CSV
        List<Employee> employeeList = findAll();

        // Add the new Employee
        employeeList.add(employee);

        // Write everything back
        writeAll(employeeList);
    }

    @Override
    public void update(Employee employee) {
        // findAll
        List<Employee> employeeList = findAll();

        // find employee with matching ID
        for (int i = 0 ; i< employeeList.size(); i++){
            if(employeeList.get(i).getId() == employee.getId()) {
                // replace it
                employeeList.set(i, employee);

                // update CSV
                writeAll(employeeList);
                return;
            }
        }

        throw new RuntimeException("Employee not found");
    }

    @Override
    public void delete(Employee employee) {
        List<Employee> employeeList = findAll();

        employeeList.removeIf(e -> e.getId() == employee.getId());

        writeAll(employeeList);
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(filePath)){
            String line;

            // skip header: id,name,age,department,salary
            reader.readLine();

            while( (line = reader.readLine()) != null){
                Employee employee = fromCsv(line);

                employeeList.add(employee);
            }

        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return employeeList;
    }

    @Override
    public Optional<Employee> findById(int id) {
        return findAll()
                .stream()
                .filter(employee -> employee.getId() == id)
                .findFirst();
    }

    // Helper methods
    private Employee fromCsv(String line){
        // convert line -> Employee
        String[] values = line.split(",");

        int id = Integer.parseInt(values[0]);
        String name = values[1];
        int age = Integer.parseInt(values[2]);
        String department = values[3];
        BigDecimal salary = new BigDecimal(values[4]);

        return new Employee(id,name,age,department,salary);
    }

    private String toCsv(Employee employee){
        // Convert to csv
        return employee.getId() + ","
                + employee.getName() + ","
                + employee.getAge() + ","
                + employee.getDepartment() + ","
                + employee.getSalary();
    }

    private void writeAll(List<Employee> employeeList){
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)){
            writer.write("id,name,age,department,salary");
            writer.newLine();

            for(Employee employee : employeeList){
                writer.write(toCsv(employee));
                writer.newLine();
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
