package com.workspace.task.payload;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {

    private Long employeeId;

    private String empName;

    private String email;

    private Long mobileNumber;

    private BigDecimal salary;

    private String department;

    private String designation;

}
