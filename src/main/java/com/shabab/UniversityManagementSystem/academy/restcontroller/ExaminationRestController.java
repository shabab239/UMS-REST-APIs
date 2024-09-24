package com.shabab.UniversityManagementSystem.academy.restcontroller;

import com.shabab.UniversityManagementSystem.academy.model.exam.Examination;
import com.shabab.UniversityManagementSystem.academy.model.exam.Mark;
import com.shabab.UniversityManagementSystem.academy.service.ExaminationService;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 31/08/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/examination")
public class ExaminationRestController {


    @Autowired
    private ExaminationService examinationService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return examinationService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Examination examination) {
        return examinationService.save(examination);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Examination examination) {
        return examinationService.update(examination);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return examinationService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return examinationService.deleteById(id);
    }

    @GetMapping("/getAllMarksByExaminationAndCourse")
    public ApiResponse getAllMarksByExaminationAndCourse(
            @RequestParam(required = false) Long examinationId,
            @RequestParam(required = false) Long courseId
    ) {
        return examinationService.getAllMarksByExaminationAndCourse(examinationId, courseId);
    }

    @PostMapping("/saveMarks")
    public ApiResponse saveMarks(
            @RequestBody(required = false) List<Mark> marks
    ) {
        return examinationService.saveMarks(marks);
    }

    @PostMapping("/processExamination")
    public ApiResponse processExamination(@RequestParam(required = false) Long examinationId) {
        if (examinationId == null) {
            return new ApiResponse().returnError("Examination ID is required");
        }
        return examinationService.processExamination(examinationId);
    }

    @GetMapping("/getResult")
    public ApiResponse getResult(@RequestParam(required = false) Long studentId) {
        if (studentId == null) {
            return new ApiResponse().returnError("Student ID is required");
        }
        return examinationService.getResult(studentId);
    }

    @GetMapping("/getResultsByExamination/{examinationId}")
    public ApiResponse getResultsByExamination(@PathVariable Long examinationId) {
        return examinationService.getResultsByExamination(examinationId);
    }
}
