package com.shabab.UniversityManagementSystem.academy.model;

import com.fasterxml.jackson.annotation.*;
import com.shabab.UniversityManagementSystem.academy.model.exam.Examination;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "acd_semesters")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Semester name is required")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(length = 100, nullable = false)
    private String name;

    @NotBlank(message = "Session is required")
    @Pattern(regexp = "^(19|20)\\d{2}-(\\d{2})$", message = "(20XX-XX) format required")
    @Column(length = 9, nullable = false)
    private String session;

    @NotBlank(message = "Semester code is required")
    @Pattern(regexp = "^L[1-8]S[1-8]$", message = "Invalid semester code")
    @Column(length = 4, nullable = false)
    private String code;

    @JsonBackReference
    @NotNull(message = "Program is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Program program;

    @Column(name = "university_id", nullable = false)
    private Long universityId; // Loose relation with University

    @JsonManagedReference
    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Student> students;

    @JsonManagedReference
    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Course> courses;

    @JsonManagedReference
    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Examination> examinations;

    @JsonManagedReference
    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Fee> fees;

    public Semester(Long id) {
        this.id = id;
    }
}

