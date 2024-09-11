package com.shabab.UniversityManagementSystem.security.repository;

import com.shabab.UniversityManagementSystem.security.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 25/08/2024
 */

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByUsername(String username);

}
