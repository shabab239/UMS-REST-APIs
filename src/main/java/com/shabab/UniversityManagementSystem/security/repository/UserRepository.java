package com.shabab.UniversityManagementSystem.security.repository;

import com.shabab.UniversityManagementSystem.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 25/08/2024
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u " +
            "WHERE u.university.id = :universityId")
    Optional<List<User>> findAll(@Param("universityId") Long universityId);

    @Query("SELECT u FROM User u " +
            "WHERE u.id = :userId " +
            "AND u.university.id = :universityId")
    Optional<User> findById(@Param("userId") Long userId,
                            @Param("universityId") Long universityId);

}
