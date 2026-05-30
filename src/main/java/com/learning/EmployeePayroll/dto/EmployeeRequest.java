package com.learning.EmployeePayroll.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeRequest {

    private String employeeCode;

    private String employeeName;

    private String email;

    private String mobileNumber;

    private Long departmentId;

    private Long designationId;

    private Long payLevelId;

    private List<PostingDetailRequest> postingDetails;

    private List<PaymentDetailRequest> paymentDetails;
}
