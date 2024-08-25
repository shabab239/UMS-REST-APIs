package com.shabab.UniversityManagementSystem.security.service;

import com.shabab.UniversityManagementSystem.admin.model.User;
import com.shabab.UniversityManagementSystem.admin.repository.UserRepository;
import com.shabab.UniversityManagementSystem.admin.service.UserService;
import com.shabab.UniversityManagementSystem.security.model.Token;
import com.shabab.UniversityManagementSystem.security.repository.AuthRepository;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    /*private final AuthenticationManager authenticationManager;

    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }*/

    /*public ApiResponse authenticate(Token token) {
        ApiResponse apiResponse = new ApiResponse();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(token.getUsername(), token.getPassword())
        );
        if (authentication != null && authentication.isAuthenticated()) {
            User dbUser = userService.getUserById(token.getUser().getId());
            apiResponse.setData("user", dbUser);
            apiResponse.setSuccessful(true);
            apiResponse.setMessage("User authenticated");
            return apiResponse;
        }

        apiResponse.setMessage("Wrong username or password");
        return apiResponse;
    }*/

    public ApiResponse saveToken(Token token) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            String encodedPassword = encoder.encode(token.getPassword());
            token.setPassword(encodedPassword);

            authRepository.save(token);
            apiResponse.setSuccessful(true);
            apiResponse.setMessage("Credentials saved successfully");
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage("Sorry, something went wrong");
            return apiResponse;
        }
    }
}


