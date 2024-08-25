package com.shabab.UniversityManagementSystem.security.service;

import com.shabab.UniversityManagementSystem.admin.repository.UserRepository;
import com.shabab.UniversityManagementSystem.security.model.Token;
import com.shabab.UniversityManagementSystem.security.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TokenService implements UserDetailsService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Token token = tokenRepository.findByUsername(username);

        if (token != null && token.getId() == null) {
            return token.getUser();
        }

        return null;
    }
}
