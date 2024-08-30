package com.shabab.UniversityManagementSystem.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shabab.UniversityManagementSystem.academy.model.Faculty;
import com.shabab.UniversityManagementSystem.academy.model.Program;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ad_universities")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 150, message = "Max 150 Characters")
    @Column(name = "name", length = 150, unique = true, nullable = false)
    private String name;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^\\d{11}$", message = "Contact number must be 11 digits")
    @Column(name = "contact", length = 11, unique = true, nullable = false)
    private String contact;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @NotNull(message = "Established year is required")
    @Column(name = "established_year", nullable = false)
    private Integer establishedYear;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Max 255 Characters")
    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Faculty> faculties;

    public University(Long id) {
        this.id = id;
    }

    public static University init(Long id) {
        University university = new University();
        university.setId(id);
        return university;
    }
}


