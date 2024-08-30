package com.shabab.UniversityManagementSystem.academy.restcontroller;

import com.shabab.UniversityManagementSystem.academy.model.Student;
import com.shabab.UniversityManagementSystem.academy.service.StudentService;
import com.shabab.UniversityManagementSystem.admin.model.User;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return studentService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestPart("student") Student student,
                            @RequestPart(value = "avatar", required = false) MultipartFile avatar) {
        return studentService.save(student, avatar);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestPart("student") Student student,
                              @RequestPart(value = "avatar", required = false) MultipartFile avatar) {
        return studentService.update(student, avatar);
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
