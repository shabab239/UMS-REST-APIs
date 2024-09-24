package com.shabab.UniversityManagementSystem.academy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shabab.UniversityManagementSystem.security.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/09/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "acd_halls")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Hall name is required")
    @Size(max = 300, message = "Max 300 Characters")
    @Column(nullable = false, length = 300)
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    private User provost;

    @Column(nullable = false)
    private Long universityId; // Loose relation with University

    @JsonManagedReference
    @OneToMany(mappedBy = "hall", fetch = FetchType.LAZY)
    private List<Student> students;

    public Hall(Long id) {
        this.id = id;
    }
}
