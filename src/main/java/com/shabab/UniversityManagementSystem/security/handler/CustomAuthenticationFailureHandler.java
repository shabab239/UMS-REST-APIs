package com.shabab.UniversityManagementSystem.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 28/08/2024
 */

@Component("CustomAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler implements Customizer<ExceptionHandlingConfigurer<HttpSecurity>> {

    @Override
    public void customize(ExceptionHandlingConfigurer<HttpSecurity> httpSecurityExceptionHandlingConfigurer) {
        httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                if (authException instanceof UsernameNotFoundException) {
                    ApiResponse apiResponse = new ApiResponse(false, "Invalid username or password");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    ObjectMapper mapper = new ObjectMapper();
                    String json = mapper.writeValueAsString(apiResponse);
                    response.getWriter().write(json);
                }
            }
        });
    }
}
