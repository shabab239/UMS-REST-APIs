package com.shabab.UniversityManagementSystem.academy.repository;

import com.shabab.UniversityManagementSystem.academy.model.Course;
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
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c " +
            "WHERE c.semester.universityId = :universityId")
    Optional<List<Course>> findAll(@Param("universityId") Long universityId);

    @Query("SELECT c FROM Course c " +
            "WHERE c.id = :courseId " +
            "AND c.semester.universityId = :universityId")
    Optional<Course> findById(@Param("courseId") Long courseId,
                              @Param("universityId") Long universityId);

    @Query("SELECT c FROM Course c " +
            "JOIN c.teachers t " +
            "WHERE t.id IN (:teacherId) " + //TODO fix this
            "AND c.semester.universityId = :universityId")
    Optional<List<Course>> findByTeacherId(@Param("teacherId") Long teacherId,
                                           @Param("universityId") Long universityId);

    @Query("SELECT c FROM Course c " +
            "JOIN Semester sem on sem.id = c.semester.id " +
            "JOIN Examination e on e.semester.id = sem.id " +
            "WHERE e.id = :examinationId " +
            "AND sem.universityId = :universityId")
    Optional<List<Course>> findAllByExamination(@Param("examinationId") Long examinationId,
                                                @Param("universityId") Long universityId);
}
