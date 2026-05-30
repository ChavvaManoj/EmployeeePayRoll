package com.learning.EmployeePayroll.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pay_level")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String levelCode;

    private BigDecimal basicPay;
}
