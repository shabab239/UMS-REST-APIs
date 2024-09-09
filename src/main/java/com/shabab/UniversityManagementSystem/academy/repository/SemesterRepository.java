package com.shabab.UniversityManagementSystem.academy.repository;

import com.shabab.UniversityManagementSystem.academy.model.Semester;
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
public interface SemesterRepository extends JpaRepository<Semester, Long> {

    @Query("SELECT s FROM Semester s " +
            "WHERE s.universityId = :universityId")
    Optional<List<Semester>> findAll(@Param("universityId") Long universityId);

    @Query("SELECT s FROM Semester s " +
            "WHERE s.id = :semesterId " +
            "AND s.universityId = :universityId")
    Optional<Semester> findById(@Param("semesterId") Long semesterId,
                                @Param("universityId") Long universityId);

}

