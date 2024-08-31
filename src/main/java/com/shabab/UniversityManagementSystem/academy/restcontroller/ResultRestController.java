package com.shabab.UniversityManagementSystem.academy.restcontroller;

import com.shabab.UniversityManagementSystem.academy.model.Result;
import com.shabab.UniversityManagementSystem.academy.service.ResultService;
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
@RequestMapping("/api/result")
public class ResultRestController {


    @Autowired
    private ResultService resultService;

    @GetMapping("/getAllByExamination")
    public ApiResponse getAllByExamination(@RequestParam(required = false) Long examinationId) {
        if (examinationId == null) {
            return new ApiResponse(false, "Examination ID is required");
        }
        return resultService.getAll(examinationId);
    }

    @PostMapping("/saveAll")
    public ApiResponse saveAll(@Valid @RequestBody List<Result> results) {
        return resultService.saveAll(results);
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Result result) {
        return resultService.save(result);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Result result) {
        return resultService.update(result);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return resultService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return resultService.deleteById(id);
    }

    @PostMapping("/process")
    public ApiResponse process(@RequestParam(required = false) Long examinationId) {
        if (examinationId == null) {
            return new ApiResponse(false, "Examination ID is required");
        }
        return resultService.process(examinationId);
    }

}
