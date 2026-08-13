package org.example.dto.request;

public record EmployeeRequest(
        int id,
        String name,
        int age,
        String department,
        double salary
) {
    public EmployeeRequest(int id, String name, int age, String department, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int age() {
        return age;
    }

    @Override
    public String department() {
        return department;
    }

    @Override
    public double salary() {
        return salary;
    }
}
