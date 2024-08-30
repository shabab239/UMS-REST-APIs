package com.shabab.UniversityManagementSystem.academy.restcontroller;

import com.shabab.UniversityManagementSystem.academy.model.Course;
import com.shabab.UniversityManagementSystem.academy.service.CourseService;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/course")
public class CourseRestController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return courseService.getAll();
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody Course course) {
        return courseService.save(course);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody Course course) {
        return courseService.update(course);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return courseService.deleteById(id);
    }

}
