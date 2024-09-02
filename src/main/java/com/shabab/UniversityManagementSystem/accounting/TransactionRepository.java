package com.shabab.UniversityManagementSystem.accounting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 01/09/2024
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<List<Transaction>> findByUniversityId(Long universityId);

}
