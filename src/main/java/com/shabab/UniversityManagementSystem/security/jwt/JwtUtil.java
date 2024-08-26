package com.shabab.UniversityManagementSystem.security.jwt;

import com.shabab.UniversityManagementSystem.admin.model.University;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 26/08/2024
 */

@Component
public class JwtUtil {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private HttpServletRequest request;

    public Long getUserId() {
        String jwt = extractJwtFromRequest();
        return jwtService.extractUserId(jwt);
    }

    public University getUniversity() {
        String jwt = extractJwtFromRequest();
        Long universityId = jwtService.extractUniversityId(jwt);
        return University.init(universityId);
    }

    public Long getUniversityId() {
        String jwt = extractJwtFromRequest();
        return jwtService.extractUniversityId(jwt);
    }

    private String extractJwtFromRequest() {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new RuntimeException("JWT Token is missing or invalid");
    }

}
