package com.learning.EmployeePayroll.controller;

import com.learning.EmployeePayroll.entity.SalaryRun;
import com.learning.EmployeePayroll.service.PayrollCalculationService;
import com.learning.EmployeePayroll.service.SalaryRunService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payroll")
@RequiredArgsConstructor
public class PayrollController {

    private final PayrollCalculationService payrollCalculationService;
    private final SalaryRunService salaryRunService;

    @PostMapping("/process")
    public String processSalary(
            @RequestParam Integer month,
            @RequestParam Integer year) {

        SalaryRun salaryRun =
                salaryRunService.createSalaryRun(month, year);

        payrollCalculationService.processSalaryRun(
                salaryRun.getId());

        return "Payroll Processed Successfully";
    }
}