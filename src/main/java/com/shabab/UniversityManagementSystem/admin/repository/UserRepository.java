package com.shabab.UniversityManagementSystem.admin.repository;

import com.shabab.UniversityManagementSystem.admin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 25/08/2024
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
