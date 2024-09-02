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

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Fee fee) {
        return feeService.save(fee);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Fee fee) {
        return feeService.update(fee);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return feeService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return feeService.deleteById(id);
    }

    @PostMapping("/saveFees")
    public ApiResponse saveFees(@Valid @RequestBody List<Fee> fees,
                                @RequestParam(required = false) Long semesterId) {
        return feeService.saveFees(semesterId, fees);
    }

    @PostMapping("/collectFees")
    public ApiResponse collectFees(@Valid @RequestBody List<Fee> fees,
                                @RequestParam(required = false) Long semesterId) {
        return feeService.collectFees(semesterId, fees);
    }

}
