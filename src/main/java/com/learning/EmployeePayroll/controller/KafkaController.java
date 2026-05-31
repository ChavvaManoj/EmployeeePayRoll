package com.learning.EmployeePayroll.controller;

import com.learning.EmployeePayroll.service.SalaryProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final SalaryProducer salaryProducer;

    @GetMapping("/test")
    public String testKafka() {

        salaryProducer.sendMessage(
                "Hello Kafka");

        return "Message Sent";
    }
}
