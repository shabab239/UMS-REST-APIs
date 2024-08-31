package com.shabab.UniversityManagementSystem.academy.repository;

import com.shabab.UniversityManagementSystem.academy.model.Department;
import com.shabab.UniversityManagementSystem.academy.model.Result;
import com.shabab.UniversityManagementSystem.academy.model.Student;
import com.shabab.UniversityManagementSystem.admin.model.University;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.semester.program.department.faculty.university.id = :universityId")
    Optional<List<Student>> getAll(@Param("universityId") Long universityId);

    @Query("SELECT s FROM Student s WHERE s.id = :id AND s.semester.program.department.faculty.university.id = :universityId")
    Optional<Student> getById(@Param("id") Long id, @Param("universityId") Long universityId);

    @Query("SELECT s FROM Student s WHERE s.semester.id = :semesterId AND s.semester.program.department.faculty.university.id = :universityId")
    Optional<List<Student>> getAllBySemester(@Param("semesterId") Long semesterId, @Param("universityId") Long universityId);

    @Query("SELECT s FROM Student s " +
            "JOIN Semester sem on sem.id = s.semester.id " +
            "JOIN Examination e on e.semester.id = sem.id " +
            "WHERE s.semester.id = :semesterId " +
            "AND sem.program.department.faculty.university.id = :universityId")
    Optional<List<Student>> getAllByExamination(@Param("semesterId") Long semesterId,
                                                @Param("universityId") Long universityId);

}

