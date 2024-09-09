package com.shabab.UniversityManagementSystem.academy.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.*;
import lombok.NoArgsConstructor;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 31/08/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "exm_marks")
public class Mark {

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

    @Min(value = 0, message = "Must be a positive number")
    @Column(name = "mark_mid")
    private Double markMid;

    @Min(value = 0, message = "Must be a positive number")
    @Column(name = "mark_attendance")
    private Double markAttendance;

    @Min(value = 0, message = "Must be a positive number")
    @Column(name = "mark_written")
    private Double markWritten;

    @Enumerated(EnumType.STRING)
    @Column(name = "sessional_type")
    private Mark.SessionalType sessionalType;

    @Min(value = 0, message = "Must be a positive number")
    @Column(name = "mark_sessional")
    private Double markSessional;

    @Column(name = "total_mark") //100
    private Double totalMark;

    @Column(name = "gpa") //4.00, 3.50 etc
    private Double gpa;

    @Column(name = "grade", length = 2) //A+, A- etc
    private String grade;

    @Column(name = "status", length = 6) //Passed, Failed
    private String status;

    public Mark(Long id) {
        this.id = id;
    }

    public enum SessionalType {
        PRACTICAL,
        PRESENTATION
    }

    public boolean processMark() {
        double totalMarks = (this.getMarkMid() != null ? this.getMarkMid() : 0) +
                (this.getMarkAttendance() != null ? this.getMarkAttendance() : 0) +
                (this.getMarkWritten() != null ? this.getMarkWritten() : 0) +
                (this.getMarkSessional() != null ? this.getMarkSessional() : 0);

        if (totalMarks > 100) {
            return false;
        }

        String grade;
        String status;

        if (totalMarks >= 80) {
            gpa = 4.00;
            grade = "A+";
            status = "Passed";
        } else if (totalMarks >= 75) {
            gpa = 3.75;
            grade = "A";
            status = "Passed";
        } else if (totalMarks >= 70) {
            gpa = 3.50;
            grade = "A-";
            status = "Passed";
        } else if (totalMarks >= 65) {
            gpa = 3.25;
            grade = "B+";
            status = "Passed";
        } else if (totalMarks >= 60) {
            gpa = 3.00;
            grade = "B";
            status = "Passed";
        } else if (totalMarks >= 55) {
            gpa = 2.75;
            grade = "B-";
            status = "Passed";
        } else if (totalMarks >= 50) {
            gpa = 2.50;
            grade = "C+";
            status = "Passed";
        } else if (totalMarks >= 45) {
            gpa = 2.25;
            grade = "C-";
            status = "Passed";
        } else if (totalMarks >= 40) {
            gpa = 2.00;
            grade = "D";
            status = "Passed";
        } else {
            gpa = 0.00;
            grade = "F";
            status = "Failed";
        }

        this.setGpa(gpa);
        this.setGrade(grade);
        this.setStatus(status);
        this.setTotalMark(totalMarks);
        return true;
    }
}
