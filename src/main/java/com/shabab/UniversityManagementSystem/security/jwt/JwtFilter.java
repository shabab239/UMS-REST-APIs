package com.shabab.UniversityManagementSystem.security.jwt;

import com.shabab.UniversityManagementSystem.admin.service.UserService;
import com.shabab.UniversityManagementSystem.validation.InvalidCredentialsException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 26/08/2024
 */

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String jwt = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                username = jwtService.extractUsername(jwt);
            } catch (Exception e) {
                throw new JwtException("Invalid JWT");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            try {
                UserDetails userDetails = applicationContext.getBean(UserService.class)
                        .loadUserByUsername(username);

                if (jwtService.validateJwt(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (InvalidCredentialsException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Invalid username or password. Please try again.\"}");
                return;
            }

        }
        filterChain.doFilter(request, response);
    }


}
