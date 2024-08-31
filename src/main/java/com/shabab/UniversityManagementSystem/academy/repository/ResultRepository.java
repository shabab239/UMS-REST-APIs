package com.shabab.UniversityManagementSystem.academy.repository;

import com.shabab.UniversityManagementSystem.academy.model.Course;
import com.shabab.UniversityManagementSystem.academy.model.Examination;
import com.shabab.UniversityManagementSystem.academy.model.Result;
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

public interface ResultRepository extends JpaRepository<Result, Long> {

    @Query("SELECT r FROM Result r WHERE r.examination.id = :examinationId AND r.examination.semester.program.department.faculty.university.id = :universityId")
    Optional<List<Result>> getAll(@Param("examinationId") Long examinationId, @Param("universityId") Long universityId);

    @Query("SELECT r FROM Result r WHERE r.id = :id AND r.examination.semester.program.department.faculty.university.id = :universityId")
    Optional<Result> getById(@Param("id") Long id, @Param("universityId") Long universityId);

    Optional<Result> findByExaminationIdAndCourseIdAndStudentId(Long examinationId, Long courseId, Long studentId);

}
