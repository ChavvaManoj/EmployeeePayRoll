package com.learning.EmployeePayroll.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "salary_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer month;

    private Integer year;

    private Double basicPay;

    private Double hraAmount;

    private Double daAmount;

    private Double grossSalary;

    private Double netSalary;

    private String status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "salary_run_id")
    private SalaryRun salaryRun;
}
