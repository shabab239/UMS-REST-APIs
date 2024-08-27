package com.shabab.UniversityManagementSystem.security.restcontroller;

import com.shabab.UniversityManagementSystem.admin.service.UserService;
import com.shabab.UniversityManagementSystem.security.model.Token;
import com.shabab.UniversityManagementSystem.security.service.AuthService;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 25/08/2024
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/createCredentials")
    public ApiResponse createCredentials(@RequestBody Token token) {
        return authService.saveToken(token);
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody Token token) {
        return authService.authenticate(token);
    }

}
