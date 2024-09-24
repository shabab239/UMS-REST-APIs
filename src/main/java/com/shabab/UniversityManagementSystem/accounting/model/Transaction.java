package com.shabab.UniversityManagementSystem.accounting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shabab.UniversityManagementSystem.academy.model.University;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.*;
import java.time.LocalDateTime;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 01/09/2024
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "acc_transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(nullable = false)
    private Account account;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column( nullable = false)
    private LocalDateTime timestamp;

    private String description;

    @Column(nullable = false)
    private Long universityId; // Loose relation with University

    public enum TransactionType {
        CREDIT,
        DEBIT
    }

    public Transaction(Long id) {
        this.id = id;
    }
}
