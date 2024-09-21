package com.shabab.UniversityManagementSystem.academy.restcontroller;

import com.shabab.UniversityManagementSystem.academy.model.Fee;
import com.shabab.UniversityManagementSystem.academy.service.FeeService;
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

@CrossOrigin
@RestController
@RequestMapping("/api/fee")
public class FeeRestController {

    @Autowired
    private FeeService feeService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return feeService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return feeService.getById(id);
    }

    @GetMapping("/getImposedFees")
    public ApiResponse getImposedFees(@RequestParam(required = false) Long studentId) {
        if (studentId == null) {
            return new ApiResponse().returnError("Student id is required");
        }
        return feeService.getImposedFees(studentId);
    }

    @PostMapping("/saveFees")
    public ApiResponse saveFees(@Valid @RequestBody List<Fee> fees) {
        return feeService.saveFees(fees);
    }

    @PostMapping("/collectFees")
    public ApiResponse collectFees(@Valid @RequestBody List<Fee> fees) {
        return feeService.collectFees(fees);
    }

}
