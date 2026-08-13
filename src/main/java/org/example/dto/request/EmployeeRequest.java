package org.example.dto.request;

import java.math.BigDecimal;

public record EmployeeRequest(
        String name,
        int age,
        String department,
        BigDecimal salary
) {

}
