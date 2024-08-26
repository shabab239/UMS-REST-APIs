package com.shabab.UniversityManagementSystem.security.repository;

import com.shabab.UniversityManagementSystem.security.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 25/08/2024
 */

@Repository
public interface AuthRepository extends JpaRepository<Token, Long> {

    Token findByUsername(String username);

}
