package com.shabab.UniversityManagementSystem.academy.repository;

import com.shabab.UniversityManagementSystem.academy.model.Department;
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

    @Query("SELECT d FROM Department d " +
            "WHERE d.universityId = :universityId")
    Optional<List<Department>> findAll(@Param("universityId") Long universityId);

    @Query("SELECT d FROM Department d " +
            "WHERE d.id = :departmentId " +
            "AND d.universityId = :universityId")
    Optional<Department> findById(@Param("departmentId") Long departmentId,
                                  @Param("universityId") Long universityId);

    @Query("SELECT d FROM Department d " +
            "WHERE d.head.id = :headId " +
            "AND d.universityId = :universityId")
    Optional<Department> findByHeadId(@Param("headId") Long headId,
                                      @Param("universityId") Long universityId);


}
