package com.shabab.UniversityManagementSystem.academy.repository;


import com.shabab.UniversityManagementSystem.academy.model.Faculty;
import com.shabab.UniversityManagementSystem.academy.model.Hall;
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
public interface HallRepository extends JpaRepository<Hall, Long> {

    Optional<Hall> findByIdAndUniversityId(Long id, Long universityId);

    Optional<List<Hall>> findAllByUniversityId(Long universityId);

}
