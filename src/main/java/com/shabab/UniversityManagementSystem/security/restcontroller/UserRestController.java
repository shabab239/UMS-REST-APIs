package com.shabab.UniversityManagementSystem.security.restcontroller;

import com.shabab.UniversityManagementSystem.security.model.User;
import com.shabab.UniversityManagementSystem.security.service.UserService;
import com.shabab.UniversityManagementSystem.security.model.Token;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 26/08/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ApiResponse getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestPart("user") User user,
                            @RequestPart(value = "avatar", required = false) MultipartFile avatar) {
        return userService.save(user, avatar);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestPart("user") User user,
                              @RequestPart(value = "avatar", required = false) MultipartFile avatar) {
        return userService.update(user, avatar);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @PostMapping("/saveToken")
    public ApiResponse saveToken(@Valid @RequestBody Token token) {
        return userService.saveToken(token);
    }
}

