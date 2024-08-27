package com.shabab.UniversityManagementSystem.admin.service;

import com.shabab.UniversityManagementSystem.admin.model.User;
import com.shabab.UniversityManagementSystem.admin.repository.UserRepository;
import com.shabab.UniversityManagementSystem.security.jwt.JwtUtil;
import com.shabab.UniversityManagementSystem.security.model.Token;
import com.shabab.UniversityManagementSystem.security.repository.AuthRepository;
import com.shabab.UniversityManagementSystem.security.service.AuthService;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.validation.InvalidCredentialsException;
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

    @Autowired
    private JwtUtil jwtUtil;

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<User> users = userRepository.findByUniversity(jwtUtil.getUniversity());
            if (users.isEmpty()) {
                return response.returnError("No users found");
            }
            response.setData("users", users);
            response.success("Successfully retrieved all users");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse save(User user) {
        ApiResponse response = new ApiResponse();
        try {
            if (user.getRole() == null || user.getRole().equals("")) {
                return response.returnError("Role is required");
            }
            user.setUniversity(jwtUtil.getUniversity());
            User dbUser = userRepository.save(user);
            response.setData("user", dbUser);
            response.success("Saved Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse update(User user) {
        ApiResponse response = new ApiResponse();
        try {
            User dbUser = userRepository.findByIdAndUniversity(user.getId(), jwtUtil.getUniversity());
            if (dbUser == null || dbUser.getId() == null) {
                return response.returnError("User not found");
            }
            user.setRole(dbUser.getRole());
            user.setUniversity(jwtUtil.getUniversity());
            User updatedUser = userRepository.save(user);
            response.setData("user", updatedUser);
            response.success("Updated Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            User user = userRepository.findByIdAndUniversity(id, jwtUtil.getUniversity());
            if (user == null || user.getId() == null) {
                return response.returnError("User not found");
            }
            response.setData("user", user);
            response.success("Successfully retrieved user");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            User user = userRepository.findByIdAndUniversity(id, jwtUtil.getUniversity());
            if (user == null || user.getId() == null) {
                return response.returnError("User not found");
            }
            userRepository.deleteById(id);
            response.success("Deleted Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws InvalidCredentialsException {
        Token token = authRepository.findByUsername(username);

        if (token == null || token.getUser() == null) {
            throw new InvalidCredentialsException("Invalid username or password. Please try again.");
        }

        User user = token.getUser();
        user.setUsername(token.getUsername());
        user.setPassword(token.getPassword());
        return token.getUser();
    }

}