package com.shabab.UniversityManagementSystem.academy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shabab.UniversityManagementSystem.security.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "acd_departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department name is required")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="head_id")
    private User head;

    @NotNull(message = "Faculty is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="faculty_id", nullable = false)
    private Faculty faculty;

    @Column(name = "university_id", nullable = false)
    private Long universityId; // Loose relation with University

    @JsonIgnore
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Program> programs;

    public Department(Long id) {
        this.id = id;
    }

}
