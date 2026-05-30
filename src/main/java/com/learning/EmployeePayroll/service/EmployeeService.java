package com.learning.EmployeePayroll.service;

import com.learning.EmployeePayroll.dto.EmployeeRequest;
import com.learning.EmployeePayroll.entity.*;
import com.learning.EmployeePayroll.repository.DepartmentRepository;
import com.learning.EmployeePayroll.repository.DesignationRepository;
import com.learning.EmployeePayroll.repository.EmployeeRepository;
import com.learning.EmployeePayroll.repository.PayLevelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final DesignationRepository designationRepository;
    private final PayLevelRepository payLevelRepository;

    public Employee createEmployee(EmployeeRequest employeeRequest) {

        Department department = departmentRepository
                .findById(employeeRequest.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Designation designation = designationRepository
                .findById(employeeRequest.getDesignationId())
                .orElseThrow(() -> new RuntimeException("Designation not found"));

        PayLevel payLevel = payLevelRepository
                .findById(employeeRequest.getPayLevelId())
                .orElseThrow(() -> new RuntimeException("Pay Level not found"));

        Employee employee = new Employee();

        employee.setEmployeeCode(employeeRequest.getEmployeeCode());
        employee.setEmployeeName(employeeRequest.getEmployeeName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setMobileNumber(employeeRequest.getMobileNumber());

        employee.setDepartment(department);
        employee.setDesignation(designation);
        employee.setPayLevel(payLevel);

        mapPostingDetails(employee, employeeRequest);
        mapPaymentDetails(employee, employeeRequest);

        return employeeRepository.save(employee);
    }

    private void mapPostingDetails(
            Employee employee,
            EmployeeRequest employeeRequest) {

        List<PostingDetail> postings =
                employeeRequest.getPostingDetails()
                        .stream()
                        .map(dto -> {

                            PostingDetail posting =
                                    new PostingDetail();

                            posting.setOfficeName(dto.getOfficeName());
                            posting.setLocation(dto.getLocation());
                            posting.setFromDate(dto.getFromDate());
                            posting.setToDate(dto.getToDate());
                            posting.setActive(dto.getActive());

                            posting.setEmployee(employee);

                            return posting;
                        })
                        .toList();

        employee.setPostingDetails(postings);
    }

    private void mapPaymentDetails(
            Employee employee,
            EmployeeRequest employeeRequest) {

        List<PaymentDetail> payments =
                employeeRequest.getPaymentDetails()
                        .stream()
                        .map(dto -> {

                            PaymentDetail payment =
                                    new PaymentDetail();

                            payment.setBankName(dto.getBankName());
                            payment.setAccountNumber(dto.getAccountNumber());
                            payment.setIfscCode(dto.getIfscCode());
                            payment.setEffectiveFrom(dto.getEffectiveFrom());
                            payment.setEffectiveTo(dto.getEffectiveTo());
                            payment.setActive(dto.getActive());

                            payment.setEmployee(employee);

                            return payment;
                        })
                        .toList();

        employee.setPaymentDetails(payments);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));
    }

    public Employee updateEmployee(
            Long id,
            EmployeeRequest employeeRequest) {

        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        Department department = departmentRepository
                .findById(employeeRequest.getDepartmentId())
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));

        Designation designation = designationRepository
                .findById(employeeRequest.getDesignationId())
                .orElseThrow(() ->
                        new RuntimeException("Designation not found"));

        PayLevel payLevel = payLevelRepository
                .findById(employeeRequest.getPayLevelId())
                .orElseThrow(() ->
                        new RuntimeException("Pay level not found"));

        employee.setEmployeeCode(employeeRequest.getEmployeeCode());
        employee.setEmployeeName(employeeRequest.getEmployeeName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setMobileNumber(employeeRequest.getMobileNumber());

        employee.setDepartment(department);
        employee.setDesignation(designation);
        employee.setPayLevel(payLevel);

        employee.getPostingDetails().clear();
        employee.getPaymentDetails().clear();

        mapPostingDetails(employee, employeeRequest);
        mapPaymentDetails(employee, employeeRequest);

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        employeeRepository.delete(employee);
    }

}
