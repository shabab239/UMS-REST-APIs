package com.shabab.UniversityManagementSystem.security.service;

import com.shabab.UniversityManagementSystem.security.model.User;
import com.shabab.UniversityManagementSystem.security.model.Token;
import com.shabab.UniversityManagementSystem.security.repository.TokenRepository;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 25/08/2024
 */

@Service
public class AuthService {

    @Autowired
    private TokenRepository authRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    public ApiResponse authenticate(Token token) {
        ApiResponse apiResponse = new ApiResponse();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(token.getUsername(), token.getPassword())
            );
            if (authentication != null && authentication.isAuthenticated()) {
                User user = (User) authentication.getPrincipal();
                if (user != null && user.getId() != null) {

                    Map<String, Object> map = new HashMap<>();
                    String jwt = jwtService.generateJwt(user);
                    map.put("jwt", jwt);

                    user.setToken(null);
                    map.put("user", user);

                    apiResponse.setData(map);
                    apiResponse.success("User authenticated");
                    return apiResponse;
                }
            }
        } catch (Exception e) {
            apiResponse.setMessage("Invalid username or password");
        }
        return apiResponse;
    }

    public ApiResponse saveToken(Token token) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            String encodedPassword = encoder.encode(token.getPassword());
            token.setPassword(encodedPassword);

            authRepository.save(token);
            apiResponse.success("Credentials saved successfully");
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage("Sorry, something went wrong");
            return apiResponse;
        }
    }
}


