package com.learning.EmployeePayroll.service;


import com.learning.EmployeePayroll.entity.SalaryRun;
import com.learning.EmployeePayroll.repository.SalaryRunRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalaryRunService {

    private final SalaryRunRepository salaryRunRepository;

    public SalaryRun createSalaryRun(
            Integer month,
            Integer year) {

        SalaryRun salaryRun = new SalaryRun();

        salaryRun.setMonth(month);
        salaryRun.setYear(year);

        salaryRun.setStatus("PENDING");

        salaryRun.setTotalEmployees(0);
        salaryRun.setProcessedEmployees(0);
        salaryRun.setFailedEmployees(0);

        return salaryRunRepository.save(salaryRun);
    }

}
