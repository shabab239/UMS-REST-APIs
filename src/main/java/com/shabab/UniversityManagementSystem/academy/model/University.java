package com.shabab.UniversityManagementSystem.academy.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "acd_universities")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 150, message = "Max 150 Characters")
    @Column(length = 150, unique = true, nullable = false)
    private String name;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^\\d{11}$", message = "Contact number must be 11 digits")
    @Column(length = 11, unique = true, nullable = false)
    private String contact;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @NotNull(message = "Established year is required")
    @Column(nullable = false)
    private Integer establishedYear;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Max 255 Characters")
    @Column(nullable = false)
    private String address;

    @JsonManagedReference
    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Faculty> faculties;

    public University(Long id) {
        this.id = id;
    }

}


