package com.learning.EmployeePayroll.service;

import com.learning.EmployeePayroll.config.KafkaTopics;
import com.learning.EmployeePayroll.dto.SalaryBatchMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SalaryConsumer {

    public SalaryConsumer() {
        System.out.println(
                "SalaryConsumer Constructor Called");
    }

    @PostConstruct
    public void init() {
        System.out.println(
                "SalaryConsumer Started");
    }

        @KafkaListener(
                topics = KafkaTopics.SALARY_PROCESSING,
                groupId = "payroll-group")
        public void consume(String message) {

            System.out.println(
                    ">>>>>>>> RECEIVED STRING <<<<<<<<");

            System.out.println(message);
        }
    }