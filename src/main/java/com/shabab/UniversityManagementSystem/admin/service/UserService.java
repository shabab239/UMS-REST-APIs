package com.shabab.UniversityManagementSystem.admin.service;

import com.shabab.UniversityManagementSystem.admin.model.User;
import com.shabab.UniversityManagementSystem.admin.repository.UserRepository;
import com.shabab.UniversityManagementSystem.security.model.Token;
import com.shabab.UniversityManagementSystem.security.repository.AuthRepository;
import com.shabab.UniversityManagementSystem.security.service.AuthService;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 25/08/2024
 */

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Token token = authRepository.findByUsername(username);

        if (token == null || token.getUser() == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        User user = token.getUser();
        user.setUsername(token.getUsername());
        user.setPassword(token.getPassword());
        return token.getUser();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found")
        );
    }

    public ApiResponse getAllUsers() {
        ApiResponse response = new ApiResponse();
        try {
            List<User> users = userRepository.findAll();
            response.setData("users", users);
            response.success("Successfully retrieved all users");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

}