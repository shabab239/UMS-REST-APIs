package com.shabab.UniversityManagementSystem.academy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "acd_programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Program name is required")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull(message = "Program duration year is required")
    @Min(value = 1, message = "Program duration must be a positive number")
    @Column(name = "duration_year", length = 100, nullable = false)
    private Integer durationYear;

    @NotNull(message = "Department is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

}

