package com.learning.EmployeePayroll.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "salary_component")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String componentCode;

    private String componentName;

    private Double percentage;

    private Boolean active;
}
