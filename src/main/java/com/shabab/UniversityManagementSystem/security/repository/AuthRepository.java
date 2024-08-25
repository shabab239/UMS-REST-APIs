package com.shabab.UniversityManagementSystem.security.repository;

import com.shabab.UniversityManagementSystem.security.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Token, Long> {

    Token findByUsername(String username);

}
