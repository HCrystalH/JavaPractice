package org.example.dto.response;

import java.math.BigDecimal;

public record EmployeeResponse(
        int id,
        String name,
        int age,
        String department,
        BigDecimal salary
) {
}
