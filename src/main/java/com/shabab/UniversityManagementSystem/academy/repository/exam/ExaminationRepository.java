package com.shabab.UniversityManagementSystem.academy.repository.exam;

import com.shabab.UniversityManagementSystem.academy.model.exam.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 31/08/2024
 */

public interface ExaminationRepository extends JpaRepository<Examination, Long> {

    @Query("SELECT e FROM Examination e WHERE e.semester.program.department.faculty.university.id = :universityId")
    Optional<List<Examination>> getAll(@Param("universityId") Long universityId);

    @Query("SELECT e FROM Examination e WHERE e.id = :id AND e.semester.program.department.faculty.university.id = :universityId")
    Optional<Examination> getById(@Param("id") Long id, @Param("universityId") Long universityId);

}
