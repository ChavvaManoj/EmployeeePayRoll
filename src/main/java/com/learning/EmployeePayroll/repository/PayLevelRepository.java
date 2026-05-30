package com.learning.EmployeePayroll.repository;

import com.learning.EmployeePayroll.entity.PayLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayLevelRepository
        extends JpaRepository<PayLevel, Long> {
}
