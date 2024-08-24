package com.shabab.UniversityManagementSystem.academy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "acd_students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "ID Number is required")
    @Min(value = 0, message = "ID Number cannot be negative")
    private Integer idNo;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Cell number is required")
    @Pattern(regexp = "^\\d{11}$", message = "Cell number must be 11 digits")
    private String cell;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Date Of Birth is required")
    private String dateOfBirth;

    private String bloodGroup;
    private String religion;
    private byte[] avatar;

    @NotBlank(message = "Father's Name is required")
    private String fatherName;

    @NotBlank(message = "Father's Cell is required")
    @Pattern(regexp = "^\\d{11}$", message = "Father's Cell number must be 11 digits")
    private String fatherCell;

    @NotBlank(message = "Mother's Name is required")
    private String motherName;

    @NotBlank(message = "Mother's Cell is required")
    @Pattern(regexp = "^\\d{11}$", message = "Mother's Cell number must be 11 digits")
    private String motherCell;

    @NotBlank(message = "Present Address is required")
    private String presentAddress;

    @NotBlank(message = "Permanent Address is required")
    private String permanentAddress;

    @NotBlank(message = "Admission Date is required")
    private String admissionDate;

    @NotBlank(message = "Session is required")
    private String session;

    @NotBlank(message = "Status is required")
    private String status;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @NotNull(message = "Department is required")
    private Department department;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();

}