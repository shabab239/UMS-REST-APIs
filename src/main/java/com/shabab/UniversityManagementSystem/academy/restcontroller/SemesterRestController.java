package com.shabab.UniversityManagementSystem.academy.restcontroller;

import com.shabab.UniversityManagementSystem.academy.model.Semester;
import com.shabab.UniversityManagementSystem.academy.service.SemesterService;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@RestController
@RequestMapping("/api/semester")
public class SemesterRestController {

    @Autowired
    private SemesterService semesterService;

    @GetMapping("/getAll")
    public ApiResponse getAll() {
        return semesterService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Semester semester) {
        return semesterService.save(semester);
    }

    @PutMapping("/update")
    public ApiResponse update(@RequestBody Semester semester) {
        return semesterService.update(semester);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return semesterService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return semesterService.deleteById(id);
    }

}
