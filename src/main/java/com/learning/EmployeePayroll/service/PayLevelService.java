package com.learning.EmployeePayroll.service;

import com.learning.EmployeePayroll.entity.PayLevel;
import com.learning.EmployeePayroll.repository.PayLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayLevelService {
    private final PayLevelRepository payLevelRepository;

    public PayLevel createpaylevel(PayLevel payLevel){
        return payLevelRepository.save(payLevel);
    }

    public List<PayLevel> getAllPayLevels(){
        return payLevelRepository.findAll();
    }
}