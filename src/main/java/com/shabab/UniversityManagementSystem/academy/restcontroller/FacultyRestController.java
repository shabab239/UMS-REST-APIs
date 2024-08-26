package com.shabab.UniversityManagementSystem.academy.restcontroller;

import com.shabab.UniversityManagementSystem.academy.model.Faculty;
import com.shabab.UniversityManagementSystem.academy.service.FacultyService;
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
@RequestMapping("/api/faculty")
public class FacultyRestController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/getAll")
    public ApiResponse getAll() {
        return facultyService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Faculty faculty) {
        return facultyService.save(faculty);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Faculty faculty) {
        return facultyService.update(faculty);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return facultyService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return facultyService.deleteById(id);
    }
}
