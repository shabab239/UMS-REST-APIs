package com.shabab.UniversityManagementSystem.academy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "acd_semesters")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Semester name is required")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotBlank(message = "Session is required")
    @Pattern(regexp = "^(19|20)\\d{2}-(\\d{2})$", message = "(20XX-XX) format required")
    @Column(name = "session", length = 9, nullable = false)
    private String session;

    @NotBlank(message = "Semester code is required")
    @Pattern(regexp = "^L[1-8]S[1-8]$", message = "Invalid semester code")
    @Column(name = "code", length = 4, nullable = false)
    private String code;

    @NotNull(message = "Program is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    @JsonIgnore
    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Student> students;

    @JsonIgnore
    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Course> courses;

    @JsonIgnore
    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Fee> fees;

    public Semester(Long id) {
        this.id = id;
    }

}

