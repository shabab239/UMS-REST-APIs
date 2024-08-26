package com.shabab.UniversityManagementSystem.academy.model;


import com.shabab.UniversityManagementSystem.admin.model.University;
import com.shabab.UniversityManagementSystem.admin.model.User;
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
@Table(name = "acd_courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Course name is required")
    private String name;

    @Column(name = "code")
    @NotBlank(message = "Course code is required")
    private String code;

    @Column(name = "credits")
    @Min(value = 1, message = "Credits must be a positive number")
    private Integer credits;

    @Column(name = "fee")
    @NotNull(message = "Course fee is required")
    private Double fee;

    @Column(name = "description")
    @NotBlank(message = "Course description is required")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @NotNull(message = "Department is required")
    private Department department;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_teachers",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<User> teachers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "university_Id", nullable = false)
    private University university;
}
