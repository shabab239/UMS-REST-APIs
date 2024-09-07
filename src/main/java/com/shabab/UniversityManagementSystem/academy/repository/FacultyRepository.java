package com.shabab.UniversityManagementSystem.academy.repository;


import com.shabab.UniversityManagementSystem.academy.model.Faculty;
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
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    @Query("SELECT f FROM Faculty f " +
            "WHERE f.university.id = :universityId")
    Optional<List<Faculty>> findAll(@Param("universityId") Long universityId);

    @Query("SELECT f FROM Faculty f " +
            "WHERE f.id = :facultyId " +
            "AND f.university.id = :universityId")
    Optional<Faculty> findById(@Param("facultyId") Long facultyId,
                               @Param("universityId") Long universityId);

    @Query("SELECT f FROM Faculty f " +
            "WHERE f.dean.id = :deanId " +
            "AND f.university.id = :universityId")
    Optional<Faculty> findByDeanId(@Param("deanId") Long deanId,
                                   @Param("universityId") Long universityId);

}
