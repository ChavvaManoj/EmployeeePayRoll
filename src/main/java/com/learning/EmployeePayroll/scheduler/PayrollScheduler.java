package com.learning.EmployeePayroll.scheduler;


import com.learning.EmployeePayroll.entity.SalaryRun;
import com.learning.EmployeePayroll.repository.SalaryRunRepository;
import com.learning.EmployeePayroll.service.PayrollCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PayrollScheduler {

    private final SalaryRunRepository salaryRunRepository;
    private final PayrollCalculationService payrollCalculationService;

    @Scheduled(fixedDelay = 10000000)
    public void processPendingSalaryProcess(){
        System.out.println("Checking for pending payrolls...");
        List<SalaryRun> pendingRuns = salaryRunRepository.findByStatus("PENDING");
        System.out.println("Pending Runs Found : " + pendingRuns.size());
        for (SalaryRun salaryRun : pendingRuns){
            System.out.println("Pending Salary Runs" + salaryRun.getId());
            payrollCalculationService.processSalaryRun(salaryRun.getId());
            System.out.println("Scheduler Triggered : " + LocalDateTime.now());
        }

    }
}

