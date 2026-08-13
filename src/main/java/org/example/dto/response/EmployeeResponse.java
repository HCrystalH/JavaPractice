package org.example.dto.response;

public record EmployeeResponse(
        int id,
        String name,
        int age,
        String department,
        double salary
) {

    public EmployeeResponse(int id, String name, int age, String department, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public int id() {
        return id;
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
