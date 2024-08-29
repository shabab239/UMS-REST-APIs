package com.shabab.UniversityManagementSystem.academy.repository;

import com.shabab.UniversityManagementSystem.academy.model.Department;
import com.shabab.UniversityManagementSystem.academy.model.Student;
import com.shabab.UniversityManagementSystem.admin.model.University;
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
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<List<Department>> findAllByFaculty_University(University university);

    Optional<Department> findByIdAndFaculty_University(Long id, University university);

    Optional<Department> findByHead_Id(Long id);


}
