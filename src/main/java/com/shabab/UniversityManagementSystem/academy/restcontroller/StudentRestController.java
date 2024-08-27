package com.shabab.UniversityManagementSystem.academy.restcontroller;

import com.shabab.UniversityManagementSystem.academy.model.Student;
import com.shabab.UniversityManagementSystem.academy.service.StudentService;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getAll")
    public ApiResponse getAll() {
        return studentService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("/update")
    public ApiResponse update(@RequestBody Student student) {
        return studentService.update(student);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return studentService.deleteById(id);
    }

}
