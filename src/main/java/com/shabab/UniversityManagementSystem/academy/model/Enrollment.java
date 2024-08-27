package com.shabab.UniversityManagementSystem.academy.model;

import com.shabab.UniversityManagementSystem.admin.model.University;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 25/08/2024
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "acd_enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Enrollment date is required")
    @Column(name = "enrollment_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date enrollmentDate;

    @NotNull(message = "Paid Fee is required")
    @Column(name = "fee_paid", nullable = false)
    private Double feePaid;

    @NotNull(message = "Status is required")
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull(message = "Student is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "enrollment_courses",
            joinColumns = @JoinColumn(name = "enrollment_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

}
