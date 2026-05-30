package com.learning.EmployeePayroll.service;

import com.learning.EmployeePayroll.entity.Designation;
import com.learning.EmployeePayroll.repository.DesignationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignationService {

    private final DesignationRepository designationRepository;

    public Designation createDesignation(
            Designation designation) {

        return designationRepository.save(designation);
    }

    public List<Designation> getAllDesignations() {
        return designationRepository.findAll();
    }
}