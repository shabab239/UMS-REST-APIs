package com.shabab.UniversityManagementSystem.security.service;

import com.shabab.UniversityManagementSystem.academy.model.Student;
import com.shabab.UniversityManagementSystem.academy.repository.StudentRepository;
import com.shabab.UniversityManagementSystem.security.model.CustomUserDetails;
import com.shabab.UniversityManagementSystem.security.model.Token;
import com.shabab.UniversityManagementSystem.security.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Token token = tokenRepository.findByUsername(username).orElse(null);
        Student student = studentRepository.findByEmail(username).orElse(null);
        if (token == null && student == null) {
            throw new UsernameNotFoundException("User not found");
        } else if (token != null && student == null) {
            return new CustomUserDetails(token.getUser());
        } else {
            return new CustomUserDetails(student);
        }
    }
}
