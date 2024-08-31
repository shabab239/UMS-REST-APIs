package com.shabab.UniversityManagementSystem.academy.restcontroller;

import com.shabab.UniversityManagementSystem.academy.model.Examination;
import com.shabab.UniversityManagementSystem.academy.service.ExaminationService;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
