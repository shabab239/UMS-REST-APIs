package com.shabab.UniversityManagementSystem.academy.restcontroller;

import com.shabab.UniversityManagementSystem.academy.model.Faculty;
import com.shabab.UniversityManagementSystem.academy.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@RestController
@RequestMapping("/api/faculty")
public class FacultyRestController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/list")
    public List<Faculty> list() {
        return facultyService.getAllFaculties();
    }

    @PostMapping("/save")
    public void save(@RequestBody Faculty faculty) {
        facultyService.saveFaculty(faculty);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Faculty faculty) {
        facultyService.saveFaculty(faculty);
    }
}
