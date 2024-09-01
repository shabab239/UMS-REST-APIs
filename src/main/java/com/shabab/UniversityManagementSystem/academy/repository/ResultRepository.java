package com.shabab.UniversityManagementSystem.academy.repository;

import com.shabab.UniversityManagementSystem.academy.model.Examination;
import com.shabab.UniversityManagementSystem.academy.model.Result;
import com.shabab.UniversityManagementSystem.academy.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 02/09/2024
 */
public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findAllByStudent(Student student);
    Optional<Result> findByExaminationAndStudent(Examination examination, Student student);

}
