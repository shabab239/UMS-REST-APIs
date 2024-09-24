package com.shabab.UniversityManagementSystem.academy.model.exam;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shabab.UniversityManagementSystem.academy.model.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 02/09/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "exm_results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Exam is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Examination examination;

    @JsonBackReference
    @NotNull(message = "Student is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Student student;

    private Double cgpa; // 4.00, 3.50, etc.

    @Column(length = 2)
    private String grade; // A+, A-, etc.

    @Column(length = 6)
    private String status; // Passed, Failed

    public Result(Long id) {
        this.id = id;
    }
}
