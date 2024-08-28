package com.shabab.UniversityManagementSystem.academy.repository;

import com.shabab.UniversityManagementSystem.academy.model.Fee;
import com.shabab.UniversityManagementSystem.academy.model.Student;
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
public interface FeeRepository extends JpaRepository<Fee, Long> {

    @Query("SELECT f FROM Fee f WHERE f.semester.program.department.faculty.university.id = :universityId")
    Optional<List<Fee>> getAll(@Param("universityId") Long universityId);

    @Query("SELECT f FROM Fee f WHERE f.id = :id AND f.semester.program.department.faculty.university.id = :universityId")
    Optional<Fee> getById(@Param("id") Long id, @Param("universityId") Long universityId);
    
}

