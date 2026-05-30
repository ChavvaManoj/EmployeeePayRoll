package com.learning.EmployeePayroll.service;


import com.learning.EmployeePayroll.entity.SalaryComponent;
import com.learning.EmployeePayroll.repository.SalaryComponentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SalaryComponentService {

    private final SalaryComponentRepository salaryComponentRepository;

    public SalaryComponent createSalaryComponent(SalaryComponent salaryComponent){
        return salaryComponentRepository.save(salaryComponent);
    }

    public List<SalaryComponent> getAllSalaryComponent(){
        return salaryComponentRepository.findAll();
    }

    public SalaryComponent getSalarycomponetbyId(Long id){
        return salaryComponentRepository.findById(id)
                .orElseThrow(()->new RuntimeException(
                        "Salary Component Not found"
                ));
    }



}
