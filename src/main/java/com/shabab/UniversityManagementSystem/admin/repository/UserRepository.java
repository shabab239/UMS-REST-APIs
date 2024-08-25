package com.shabab.UniversityManagementSystem.admin.repository;

import com.shabab.UniversityManagementSystem.admin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
