package com.learning.EmployeePayroll.controller;

import com.learning.EmployeePayroll.dto.SalaryBatchMessage;
import com.learning.EmployeePayroll.service.SalaryProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final SalaryProducer salaryProducer;

    @GetMapping("/test")
    public String testKafka() {

        SalaryBatchMessage message =
                new SalaryBatchMessage(
                        1L,
                        List.of(1L, 2L, 3L, 4L, 5L)
                );

        salaryProducer.sendMessage(
                message);

        return "Message Sent";
    }
}
