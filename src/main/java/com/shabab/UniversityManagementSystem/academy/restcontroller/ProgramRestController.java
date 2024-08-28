package com.shabab.UniversityManagementSystem.academy.restcontroller;

import com.shabab.UniversityManagementSystem.academy.model.Program;
import com.shabab.UniversityManagementSystem.academy.service.ProgramService;
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
@RequestMapping("/api/program")
public class ProgramRestController {

    @Autowired
    private ProgramService programService;

    @GetMapping("/getAll")
    public ApiResponse getAll() {
        return programService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Program program) {
        return programService.save(program);
    }

    @PutMapping("/update")
    public ApiResponse update(@RequestBody Program program) {
        return programService.update(program);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return programService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return programService.deleteById(id);
    }

}
