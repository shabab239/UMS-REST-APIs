package com.shabab.UniversityManagementSystem.academy.repository;

import com.shabab.UniversityManagementSystem.academy.model.Mark;
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

public interface MarkRepository extends JpaRepository<Mark, Long> {

    @Query("SELECT mark FROM Mark mark WHERE mark.examination.id = :examinationId " +
            "AND mark.course.id = :courseId " +
            "AND mark.examination.semester.program.department.faculty.university.id = :universityId")
    Optional<List<Mark>> getAllByExaminationAndCourse(@Param("examinationId") Long examinationId,
                                @Param("courseId") Long courseId,
                                @Param("universityId") Long universityId);

    /*@Query("SELECT mark FROM Mark mark WHERE mark.examination.id = :examinationId " +
            "AND mark.student.id = :studentId " +
            "AND mark.examination.semester.program.department.faculty.university.id = :universityId")
    Optional<List<Mark>> getAllByExaminationAndStudent(@Param("examinationId") Long examinationId,
                                @Param("studentId") Long studentId,
                                @Param("universityId") Long universityId);*/

    @Query("SELECT mark FROM Mark mark WHERE mark.id = :id " +
            "AND mark.examination.semester.program.department.faculty.university.id = :universityId")
    Optional<Mark> getById(@Param("id") Long id, @Param("universityId") Long universityId);

    Optional<Mark> findByExaminationIdAndCourseIdAndStudentId(Long examinationId, Long courseId, Long studentId);

}
