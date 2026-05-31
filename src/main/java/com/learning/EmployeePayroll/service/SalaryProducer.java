package com.learning.EmployeePayroll.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalaryProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {

        kafkaTemplate.send(
                "salary-processing",
                message);

        System.out.println(
                "Message Sent : " + message);
    }
}