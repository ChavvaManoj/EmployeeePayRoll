package com.learning.EmployeePayroll.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PaymentDetailRequest {

    private String bankName;

    private String accountNumber;

    private String ifscCode;

    private LocalDate effectiveFrom;

    private LocalDate effectiveTo;

    private Boolean active;
}