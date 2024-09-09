package com.shabab.UniversityManagementSystem.academy.repository;

import com.shabab.UniversityManagementSystem.academy.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    @Query("SELECT p FROM Program p " +
            "WHERE p.universityId = :universityId")
    Optional<List<Program>> findAll(@Param("universityId") Long universityId);

    @Query("SELECT p FROM Program p " +
            "WHERE p.id = :programId " +
            "AND p.universityId = :universityId")
    Optional<Program> findById(@Param("programId") Long programId,
                               @Param("universityId") Long universityId);


}
