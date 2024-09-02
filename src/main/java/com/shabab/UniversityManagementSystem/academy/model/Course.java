package com.shabab.UniversityManagementSystem.academy.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.shabab.UniversityManagementSystem.admin.model.University;
import com.shabab.UniversityManagementSystem.admin.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;
import lombok.*;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 25/08/2024
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "acd_courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course name is required")
    @Size(max = 60, message = "Max 60 Characters")
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @NotBlank(message = "Course code is required")
    @Size(max = 10, message = "Max 10 Characters")
    @Column(name = "code", length = 10, nullable = false)
    private String code;

    @NotNull(message = "Course credit is required")
    @Min(value = 1, message = "Credit must be a positive number")
    @Column(name = "credit", nullable = false)
    private Integer credit;

    @NotNull(message = "Semester is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_teachers",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<User> teachers = new ArrayList<>();

    /*Optional*/

    @Size(max = 200, message = "Max 200 Characters")
    @Column(name = "description", length = 200)
    private String description;

    public Course(Long id) {
        this.id = id;
    }
}
