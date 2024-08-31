package com.shabab.UniversityManagementSystem.academy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 31/08/2024
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "acd_results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Exam is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_id", nullable = false)
    private Examination examination;

    @NotNull(message = "Student is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @NotNull(message = "Course is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @NotNull(message = "Mid Marks is required")
    @Min(value = 0, message = "Must be a positive number")
    @Column(name = "mark_mid", nullable = false)
    private Double markMid;

    @NotNull(message = "Attendance Marks is required")
    @Min(value = 0, message = "Must be a positive number")
    @Column(name = "mark_attendance", nullable = false)
    private Double markAttendance;

    @NotNull(message = "Written Marks is required")
    @Min(value = 0, message = "Must be a positive number")
    @Column(name = "mark_written", nullable = false)
    private Double markWritten;

    @NotNull(message = "Sessional Type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "sessional_type", nullable = false)
    private Result.SessionalType sessionalType;

    @NotNull(message = "Sessional Marks is required")
    @Min(value = 0, message = "Must be a positive number")
    @Column(name = "mark_sessional", nullable = false)
    private Double markSessional;

    @Column(name = "grade", length = 2) //A+, A- etc
    private String grade;

    @Column(name = "status", length = 6) //Passed, Failed
    private String status;

    public Result(Long id) {
        this.id = id;
    }

    public enum SessionalType {
        PRACTICAL,
        PRESENTATION
    }
}
