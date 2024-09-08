package com.shabab.UniversityManagementSystem.academy.restcontroller;

import com.shabab.UniversityManagementSystem.academy.model.Student;
import com.shabab.UniversityManagementSystem.academy.service.StudentService;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/getAllBySemester")
    public ApiResponse getAllBySemester(@RequestParam(required = false) Long semesterId) {
        if (semesterId == null) {
            return new ApiResponse(false, "Semester ID is required");
        }
        return studentService.getAllBySemester(semesterId);
    }

    @GetMapping("/getAllByExamination")
    public ApiResponse getAllByExamination(@RequestParam(required = false) Long examinationId) {
        if (examinationId == null) {
            return new ApiResponse(false, "Examination ID is required");
        }
        return studentService.getAllByExamination(examinationId);
    }

}
