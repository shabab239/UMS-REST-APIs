package com.shabab.UniversityManagementSystem.academy.model;

import com.shabab.UniversityManagementSystem.admin.model.University;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "acd_students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ID Number is required")
    @Column(name = "id_no", nullable = false, unique = true)
    private String idNo;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Max 100 Characters")
    @Email(message = "Invalid email format")
    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Cell is required")
    @Pattern(regexp = "^\\d{11}$", message = "Cell number must be 11 digits")
    @Column(name = "cell", length = 11, nullable = false)
    private String cell;

    @NotNull(message = "Admission Date is required")
    @Column(name = "admission_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date admissionDate;

    @NotBlank(message = "Session is required")
    @Column(name = "session", nullable = false)
    private String session;

    @NotBlank(message = "Status is required")
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull(message = "Semester is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    /*Optional*/

    @Size(max = 10, message = "Max 10 Characters")
    @Column(name = "gender", length = 10)
    private String gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "religion")
    private String religion;

    @Column(name = "avatar")
    private String avatar;

    @Size(max = 100, message = "Max 100 Characters")
    @Column(name = "father_name", length = 100)
    private String fatherName;

    @Pattern(regexp = "^\\d{11}$", message = "Father's Cell number must be 11 digits")
    @Column(name = "father_cell", length = 11)
    private String fatherCell;

    @Size(max = 100, message = "Max 100 Characters")
    @Column(name = "mother_name", length = 100)
    private String motherName;

    @Pattern(regexp = "^\\d{11}$", message = "Mother's Cell number must be 11 digits")
    @Column(name = "mother_cell", length = 11)
    private String motherCell;

    @Size(max = 400, message = "Max 400 Characters")
    @Column(name = "present_address", length = 400)
    private String presentAddress;

    @Size(max = 400, message = "Max 400 Characters")
    @Column(name = "permanent_address", length = 400)
    private String permanentAddress;

}