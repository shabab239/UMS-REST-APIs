package com.shabab.UniversityManagementSystem.security.service;

import com.shabab.UniversityManagementSystem.admin.model.User;
import com.shabab.UniversityManagementSystem.admin.service.UserService;
import com.shabab.UniversityManagementSystem.security.model.Token;
import com.shabab.UniversityManagementSystem.security.repository.AuthRepository;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiResponse authenticate(Token token) {
        ApiResponse apiResponse = new ApiResponse();

        Token dbToken = authRepository.findByUsername(token.getUsername());

        if (dbToken != null) {
            boolean authenticated = passwordEncoder.matches(token.getPassword(), token.getPassword());
            if (authenticated) {
                User dbUser = userService.getUserById(dbToken.getUser().getId());
                apiResponse.setData("user", dbUser);
                apiResponse.setSuccessful(true);
                apiResponse.setMessage("User authenticated");
                return apiResponse;
            } else {
                apiResponse.setMessage("Wrong username or password");
            }
        }
        return apiResponse;
    }

}
