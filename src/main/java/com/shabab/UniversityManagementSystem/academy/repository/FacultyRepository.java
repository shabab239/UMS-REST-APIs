package com.shabab.UniversityManagementSystem.academy.repository;


import com.shabab.UniversityManagementSystem.academy.model.Faculty;
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
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Optional<List<Faculty>> findByUniversity(University university);

    Optional<Faculty> findByIdAndUniversity(Long id, University university);

    Optional<Faculty> findByDean_Id(Long id);

}
