package com.learning.EmployeePayroll.service;

import com.learning.EmployeePayroll.entity.*;
import com.learning.EmployeePayroll.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        for (Employee employee : employees) {

            try {

                processEmployeeSalary(
                        employee,
                        salaryRun);

                salaryRun.setProcessedEmployees(
                        salaryRun.getProcessedEmployees() + 1);

            } catch (Exception ex) {

                salaryRun.setFailedEmployees(
                        salaryRun.getFailedEmployees() + 1);
            }
        }

        salaryRun.setStatus("COMPLETED");

        salaryRunRepository.save(salaryRun);
    }

    private void processEmployeeSalary(
            Employee employee,
            SalaryRun salaryRun) {

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
}