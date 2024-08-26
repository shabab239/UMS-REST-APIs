package com.shabab.UniversityManagementSystem.academy.repository;


import com.shabab.UniversityManagementSystem.academy.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
