package com.learning.EmployeePayroll.repository;

import com.learning.EmployeePayroll.entity.SalaryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryDetailRepository extends JpaRepository<SalaryDetail, Long> {
}
