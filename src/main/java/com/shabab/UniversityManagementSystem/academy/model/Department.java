package com.shabab.UniversityManagementSystem.academy.model;

import com.shabab.UniversityManagementSystem.admin.model.University;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "acd_departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department name is required")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @NotNull(message = "Faculty is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="faculty_id", nullable = false)
    private Faculty faculty;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

}
