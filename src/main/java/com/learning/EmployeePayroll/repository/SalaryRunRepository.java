package com.learning.EmployeePayroll.repository;

import com.learning.EmployeePayroll.entity.SalaryRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRunRepository extends JpaRepository<SalaryRun, Long> {
}