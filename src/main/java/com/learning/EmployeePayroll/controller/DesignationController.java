package com.learning.EmployeePayroll.controller;


import com.learning.EmployeePayroll.entity.Designation;
import com.learning.EmployeePayroll.service.DesignationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/designation")
@RequiredArgsConstructor
public class DesignationController {

    private final DesignationService designationService;

    @PostMapping
    public Designation savedesignation(@RequestBody Designation designation){
        return designationService.createDesignation(designation);
    }

    @GetMapping
    public List<Designation> getallDesignations(){
        return designationService.getAllDesignations();
    }




}
