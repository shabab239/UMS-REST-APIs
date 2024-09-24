package com.shabab.UniversityManagementSystem.academy.restcontroller;

import com.shabab.UniversityManagementSystem.academy.model.Hall;
import com.shabab.UniversityManagementSystem.academy.service.HallService;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/09/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/hall")
public class HallRestController {

    @Autowired
    private HallService hallService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return hallService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Hall hall) {
        return hallService.save(hall);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Hall hall) {
        return hallService.update(hall);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return hallService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return hallService.deleteById(id);
    }

}