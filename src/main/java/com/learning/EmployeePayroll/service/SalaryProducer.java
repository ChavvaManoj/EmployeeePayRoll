package com.learning.EmployeePayroll.service;

import com.learning.EmployeePayroll.config.KafkaTopics;
import com.learning.EmployeePayroll.dto.SalaryBatchMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalaryProducer {

    private final KafkaTemplate<String, SalaryBatchMessage> kafkaTemplate;

    public void sendMessage(SalaryBatchMessage message) {

        kafkaTemplate.send(
                KafkaTopics.SALARY_PROCESSING,
                message);

        System.out.println(
                "Message Sent : " + message);
    }
}