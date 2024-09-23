package com.shabab.UniversityManagementSystem.accounting.repository;

import com.shabab.UniversityManagementSystem.accounting.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 01/09/2024
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
