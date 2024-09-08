package com.shabab.UniversityManagementSystem.security.config;

import com.shabab.UniversityManagementSystem.security.service.UserService;
import com.shabab.UniversityManagementSystem.security.filter.JwtFilter;
import com.shabab.UniversityManagementSystem.security.handler.CustomAuthenticationFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 25/08/2024
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        //.requestMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/avatar/**").permitAll() //authenticated req did not work, will try to fix later
                        //.requestMatchers("/api/admin/**").hasRole("ADMIN")
                        //.requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/**").hasAnyRole("USER", "ADMIN", "TEACHER", "STAFF") //allow all temporarily
                        .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN", "TEACHER", "STAFF")
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(customAuthenticationFailureHandler)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        authProvider.setUserDetailsService(userService);
        authProvider.setHideUserNotFoundExceptions(false);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}



