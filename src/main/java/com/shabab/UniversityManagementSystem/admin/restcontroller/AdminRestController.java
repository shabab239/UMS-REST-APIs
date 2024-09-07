package com.shabab.UniversityManagementSystem.admin.restcontroller;

import com.shabab.UniversityManagementSystem.admin.service.AdminService;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 02/09/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public ApiResponse dashboard() {
        return adminService.dashboard();
    }

}
