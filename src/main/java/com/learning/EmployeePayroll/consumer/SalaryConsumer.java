package com.learning.EmployeePayroll.consumer;

import jakarta.annotation.PostConstruct;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SalaryConsumer {

    @PostConstruct
    public void init() {
        System.out.println("SalaryConsumer Started");
    }

    @KafkaListener(
            topics = "salary-processing",
            groupId = "payroll-group")
    public void consume(String message) {

        System.out.println(
                ">>>>>>>> RECEIVED <<<<<<<<");

        System.out.println(message);
    }
}