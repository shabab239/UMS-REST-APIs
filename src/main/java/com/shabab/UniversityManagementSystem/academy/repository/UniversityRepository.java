package com.shabab.UniversityManagementSystem.academy.repository;

import com.shabab.UniversityManagementSystem.academy.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 25/08/2024
 */

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
}
