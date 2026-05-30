package com.learning.EmployeePayroll.controller;


import com.learning.EmployeePayroll.entity.PayLevel;
import com.learning.EmployeePayroll.service.PayLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pay-levels")
@RequiredArgsConstructor
public class PayLevelController {

    private final PayLevelService payLevelService;

    @PostMapping
    public PayLevel savePayLevel(
            @RequestBody PayLevel payLevel) {

        return payLevelService.createpaylevel(payLevel);
    }

    @GetMapping
    public List<PayLevel> getAllPayLevels() {
        return payLevelService.getAllPayLevels();
    }
}
