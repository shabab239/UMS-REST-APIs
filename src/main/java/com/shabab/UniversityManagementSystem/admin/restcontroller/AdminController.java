package com.shabab.UniversityManagementSystem.admin.restcontroller;

import com.shabab.UniversityManagementSystem.admin.service.UserService;
import com.shabab.UniversityManagementSystem.security.jwt.JwtUtil;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/getAllUsers")
    public ApiResponse getAllUsers() {
        return userService.getAllUsers();
    }
/*
    @PostMapping("/save")
    public void save(@RequestBody Department department) {
        departmentService.saveDepartment(department);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Department department) {
        departmentService.saveDepartment(department);
    }
    */
}
