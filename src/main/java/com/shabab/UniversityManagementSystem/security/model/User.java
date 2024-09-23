package com.shabab.UniversityManagementSystem.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shabab.UniversityManagementSystem.academy.model.University;
import com.shabab.UniversityManagementSystem.accounting.model.Account;
import com.shabab.UniversityManagementSystem.accounting.repository.AccountRepository;
import com.shabab.UniversityManagementSystem.security.repository.UserRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;

import lombok.*;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sec_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotBlank(message = "Cell number is required")
    @Pattern(regexp = "^\\d{11}$", message = "Cell number must be 11 digits")
    @Column(name = "cell", length = 11, nullable = false, unique = true)
    private String cell;

    @NotBlank(message = "Status is required")
    @Column(name = "status", nullable = false)
    private String status;

    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Size(max = 10, message = "Max 10 Characters")
    @Column(name = "gender", length = 10)
    private String gender;

    @Size(max = 255, message = "Maximum 255 Characters")
    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "avatar")
    private String avatar;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Temporal(TemporalType.DATE)
    @Column(name = "joining_date")
    private Date joiningDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Token token;

    public User(Long id) {
        this.id = id;
    }

    public enum Role {
        ROLE_ADMIN,
        ROLE_TEACHER,
        ROLE_STAFF
    }

    public Account createAccount(AccountRepository accountRepository, UserRepository userRepository) {
        Account account = new Account();
        account.setName(name + " Cash A/C");
        account.setBalance(0.0);
        accountRepository.save(account);

        this.account = account;
        userRepository.save(this);
        return account;
    }

}

