package com.shabab.UniversityManagementSystem.academy.model;

import com.shabab.UniversityManagementSystem.admin.model.University;
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
@Table(name = "acd_enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enrollment_date")
    @NotNull(message = "Enrollment date is required")
    private String enrollmentDate;

    @Column(name = "fees_paid")
    @NotNull(message = "Fees is required")
    private Double feesPaid;

    @Column(name = "status")
    @NotNull(message = "Status is required")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    @NotNull(message = "Student is required")
    private Student student;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "enrollment_courses",
            joinColumns = @JoinColumn(name = "enrollment_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "university_Id", nullable = false)
    private University university;

}
