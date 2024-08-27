package com.shabab.UniversityManagementSystem.academy.model;


import com.shabab.UniversityManagementSystem.admin.model.University;
import com.shabab.UniversityManagementSystem.admin.model.User;
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

    @NotNull(message = "Course code is required")
    @Min(value = 1, message = "Credits must be a positive number")
    @Column(name = "credits", nullable = false)
    private Integer credits;

    @NotNull(message = "Course fee is required")
    @Min(value = 1, message = "Fee must be a positive number")
    @Column(name = "fee", nullable = false)
    private Double fee;

    @NotNull(message = "Department is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

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
}
