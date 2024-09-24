package com.shabab.UniversityManagementSystem.academy.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "acd_programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Program name is required")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull(message = "Program duration year is required")
    @Min(value = 1, message = "Program duration must be a positive number")
    @Column(length = 100, nullable = false)
    private Integer durationYear;

    @JsonBackReference
    @NotNull(message = "Department is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Department department;

    @Column(nullable = false)
    private Long universityId; // Loose relation with University

    @JsonManagedReference
    @OneToMany(mappedBy = "program", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Semester> semesters;

    public Program(Long id) {
        this.id = id;
    }
}

