package com.shabab.UniversityManagementSystem.academy.repository;

import com.shabab.UniversityManagementSystem.academy.model.Student;
import com.shabab.UniversityManagementSystem.admin.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByUniversity(University university);

    Student findByIdAndUniversity(Long id, University university);

}
