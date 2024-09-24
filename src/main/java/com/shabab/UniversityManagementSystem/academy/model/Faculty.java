package com.shabab.UniversityManagementSystem.academy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shabab.UniversityManagementSystem.security.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name = "acd_faculties")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(length = 100, nullable = false)
    private String name;

    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(length = 100, unique = true)
    private String email;

    @Pattern(regexp = "^\\d{11}$", message = "Cell number must be 11 digits")
    @Column(length = 11, unique = true)
    private String contact;

    @OneToOne(fetch = FetchType.EAGER)
    private User dean;

    @JsonManagedReference
    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Department> departments;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private University university;

    public Faculty(Long id) {
        this.id = id;
    }
}
