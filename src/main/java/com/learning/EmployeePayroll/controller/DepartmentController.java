package com.learning.EmployeePayroll.controller;


import com.learning.EmployeePayroll.entity.Department;
import com.learning.EmployeePayroll.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public Department createDepartment(
            @RequestBody Department department) {

        return departmentService.createDepartment(department);
    }

    @GetMapping
    public List<Department> getAllDepartments() {

        return departmentService.getAllDepartments();
    }
}
