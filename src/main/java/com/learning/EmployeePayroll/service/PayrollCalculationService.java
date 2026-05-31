package com.learning.EmployeePayroll.service;

import com.learning.EmployeePayroll.entity.*;
import com.learning.EmployeePayroll.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class PayrollCalculationService {

    private final EmployeeRepository employeeRepository;
    private final SalaryRunRepository salaryRunRepository;
    private final SalaryDetailRepository salaryDetailRepository;
    private final SalaryComponentRepository salaryComponentRepository;

    public void processSalaryRun(Long salaryRunId) {

        SalaryRun salaryRun = salaryRunRepository
                .findById(salaryRunId)
                .orElseThrow(() ->
                        new RuntimeException("Salary Run not found"));

        salaryRun.setStatus("IN_PROGRESS");

        List<Employee> employees =
                employeeRepository.findAll();

        salaryRun.setTotalEmployees(
                employees.size());

        salaryRunRepository.save(salaryRun);

        List<List<Employee>> batches =
                createBatches(employees, 5);

        ExecutorService executorService =
                Executors.newFixedThreadPool(3);

        for (List<Employee> batch : batches) {

            executorService.submit(() -> {

                processBatch(
                        batch,
                        salaryRun);

            });
        }

        executorService.shutdown();

        try {

            executorService.awaitTermination(
                    1,
                    TimeUnit.HOURS);

        } catch (InterruptedException ex) {

            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "Payroll processing interrupted",
                    ex);
        }

        salaryRun.setStatus("COMPLETED");

        salaryRunRepository.save(salaryRun);
    }

    private void processEmployeeSalary(
            Employee employee,
            SalaryRun salaryRun) {

        try {

            Thread.sleep(1000);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        double basicPay =
                employee.getPayLevel()
                        .getBasicPay()
                        .doubleValue();

        SalaryComponent hraComponent =
                salaryComponentRepository
                        .findByComponentCodeAndActiveTrue("HRA")
                        .orElseThrow(() ->
                                new RuntimeException("HRA not configured"));

        SalaryComponent daComponent =
                salaryComponentRepository
                        .findByComponentCodeAndActiveTrue("DA")
                        .orElseThrow(() ->
                                new RuntimeException("DA not configured"));

        double hraAmount =
                basicPay * hraComponent.getPercentage() / 100;

        double daAmount =
                basicPay * daComponent.getPercentage() / 100;

        double grossSalary =
                basicPay + hraAmount + daAmount;

        double netSalary =
                grossSalary;

        SalaryDetail salaryDetail =
                new SalaryDetail();

        salaryDetail.setMonth(
                salaryRun.getMonth());

        salaryDetail.setYear(
                salaryRun.getYear());

        salaryDetail.setBasicPay(
                basicPay);

        salaryDetail.setHraAmount(
                hraAmount);

        salaryDetail.setDaAmount(
                daAmount);

        salaryDetail.setGrossSalary(
                grossSalary);

        salaryDetail.setNetSalary(
                netSalary);

        salaryDetail.setStatus(
                "SUCCESS");

        salaryDetail.setEmployee(
                employee);

        salaryDetail.setSalaryRun(
                salaryRun);

        salaryDetailRepository.save(
                salaryDetail);
    }

    private List<List<Employee>> createBatches(
            List<Employee> employees,
            int batchSize) {

        List<List<Employee>> batches =
                new ArrayList<>();

        for (int i = 0;
             i < employees.size();
             i += batchSize) {

            batches.add(
                    employees.subList(
                            i,
                            Math.min(
                                    i + batchSize,
                                    employees.size())));
        }

        return batches;
    }

    private void processBatch(
            List<Employee> batch,
            SalaryRun salaryRun) {

        for (Employee employee : batch) {

            try {

                System.out.println(
                        Thread.currentThread().getName()
                                + " processing "
                                + employee.getEmployeeCode());

                processEmployeeSalary(
                        employee,
                        salaryRun);

                synchronized (salaryRun) {

                    salaryRun.setProcessedEmployees(
                            salaryRun.getProcessedEmployees() + 1);
                }

            } catch (Exception ex) {

                synchronized (salaryRun) {

                    salaryRun.setFailedEmployees(
                            salaryRun.getFailedEmployees() + 1);
                }
            }
            System.out.println(salaryRun.getProcessedEmployees() + " Processed Employees count , Failed Employees " +salaryRun.getFailedEmployees() );
        }
    }
}
