package com.shabab.UniversityManagementSystem.academy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shabab.UniversityManagementSystem.academy.model.exam.Result;
import com.shabab.UniversityManagementSystem.accounting.model.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

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
@Table(name = "acd_students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ID Number is required")
    @Column(nullable = false, unique = true)
    private String idNo;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(length = 100, nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Max 100 Characters")
    @Email(message = "Invalid email format")
    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Min(value = 6, message = "Minimum 6 Characters")
    private String password;

    @NotBlank(message = "Cell is required")
    @Pattern(regexp = "^\\d{11}$", message = "Cell number must be 11 digits")
    @Column(length = 11, nullable = false)
    private String cell;

    @NotNull(message = "Admission Date is required")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date admissionDate;

    @Size(max = 10, message = "Max 10 Characters")
    @Column(length = 10)
    private String gender;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Size(max = 3, message = "Max 3 Characters")
    @Column(length = 3)
    private String bloodGroup;

    @Size(max = 15, message = "Max 15 Characters")
    @Column(length = 15)
    private String religion;

    @Column(name = "avatar")
    private String avatar;

    @Size(max = 100, message = "Max 100 Characters")
    @Column(length = 100)
    private String fatherName;

    @Pattern(regexp = "^\\d{11}$", message = "Father's Cell number must be 11 digits")
    @Column(length = 11)
    private String fatherCell;

    @Size(max = 100, message = "Max 100 Characters")
    @Column(length = 100)
    private String motherName;

    @Pattern(regexp = "^\\d{11}$", message = "Mother's Cell number must be 11 digits")
    @Column(length = 11)
    private String motherCell;

    @Size(max = 400, message = "Max 400 Characters")
    @Column(length = 400)
    private String presentAddress;

    @Size(max = 400, message = "Max 400 Characters")
    @Column(length = 400)
    private String permanentAddress;

    @Column(nullable = false)
    private String status;

    @JsonBackReference
    @NotNull(message = "Semester is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Semester semester;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Account account;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Hall hall;

    @JsonManagedReference
    @OneToMany(mappedBy = "result", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Result> results;

    public Student(Long id) {
        this.id = id;
    }
}