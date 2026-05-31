package com.learning.EmployeePayroll.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "salary_run")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer month;

    private Integer year;

    private String status;

    private Integer totalEmployees;

    private Integer processedEmployees;

    private Integer failedEmployees;

    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private Long durationMs;
}