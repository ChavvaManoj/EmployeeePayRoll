package com.learning.EmployeePayroll.controller;


import com.learning.EmployeePayroll.entity.SalaryComponent;
import com.learning.EmployeePayroll.service.SalaryComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salary-components")
@RequiredArgsConstructor
public class SalaryComponentController {

    private final SalaryComponentService salaryComponentService;

    @PostMapping
    public SalaryComponent createSalaryComponent(@RequestBody SalaryComponent salaryComponent){
        return salaryComponentService.createSalaryComponent(salaryComponent);
    }

    @GetMapping
    public List<SalaryComponent> getAllSalaryComponents(){
        return salaryComponentService.getAllSalaryComponent();
    }

    @GetMapping("/{id}")
    public SalaryComponent getSalaryComponentbyId(@PathVariable long id){
        return salaryComponentService.getSalarycomponetbyId(id);
    }

}
