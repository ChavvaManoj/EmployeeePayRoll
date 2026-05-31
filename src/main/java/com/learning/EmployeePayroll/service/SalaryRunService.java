package com.learning.EmployeePayroll.service;


import com.learning.EmployeePayroll.entity.SalaryRun;
import com.learning.EmployeePayroll.repository.SalaryRunRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        salaryRun.setStartedAt(LocalDateTime.now());
        return salaryRunRepository.save(salaryRun);
    }

    public Optional<SalaryRun> getSalaryRunStatus(long id) {
        return salaryRunRepository.findById(id);
        
    }

    public List<SalaryRun> getAllSalaryRunStatus(){
        return salaryRunRepository.findAll();
    }
}
