package com.learning.EmployeePayroll.repository;

import com.learning.EmployeePayroll.entity.SalaryComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalaryComponentRepository extends JpaRepository<SalaryComponent, Long> {

    Optional<SalaryComponent>
    findByComponentCodeAndActiveTrue(
            String componentCode);
}