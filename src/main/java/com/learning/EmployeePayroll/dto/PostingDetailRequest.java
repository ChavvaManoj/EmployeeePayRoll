package com.learning.EmployeePayroll.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PostingDetailRequest {

    private String officeName;

    private String location;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Boolean active;
}