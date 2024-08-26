package com.shabab.UniversityManagementSystem.security.restcontroller;

import com.shabab.UniversityManagementSystem.admin.service.UserService;
import com.shabab.UniversityManagementSystem.security.model.Token;
import com.shabab.UniversityManagementSystem.security.service.AuthService;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
