package com.shabab.UniversityManagementSystem.academy.restcontroller;

import com.shabab.UniversityManagementSystem.academy.model.Department;
import com.shabab.UniversityManagementSystem.academy.service.DepartmentService;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@RestController
@RequestMapping("/api/department")
public class DepartmentRestController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/getAll")
    public ApiResponse getAll() {
        return departmentService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Department department) {
        return departmentService.save(department);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Department department) {
        return departmentService.update(department);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return departmentService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return departmentService.deleteById(id);
    }

}
