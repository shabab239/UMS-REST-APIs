package com.shabab.UniversityManagementSystem.academy.repository;

import com.shabab.UniversityManagementSystem.academy.model.Department;
import com.shabab.UniversityManagementSystem.academy.model.Program;
import com.shabab.UniversityManagementSystem.admin.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    Optional<List<Program>> findAllByDepartment_Faculty_University(University university);

    Optional<Program> findByIdAndDepartment_Faculty_University(Long id, University university);


}
