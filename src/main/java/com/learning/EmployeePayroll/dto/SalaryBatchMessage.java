package com.learning.EmployeePayroll.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryBatchMessage {

    private Long salaryRunId;

    private List<Long> employeeIds;
}