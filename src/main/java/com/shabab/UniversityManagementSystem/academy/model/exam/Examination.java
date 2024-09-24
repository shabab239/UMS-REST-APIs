package com.shabab.UniversityManagementSystem.academy.model.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shabab.UniversityManagementSystem.academy.model.Semester;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import lombok.*;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 31/08/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "acd_exams")
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Exam name is required")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull(message = "Exam date is required")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull(message = "Semester is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Semester semester;

    @JsonIgnore
    @OneToMany(mappedBy = "examination", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Mark> marks;

    public Examination(Long id) {
        this.id = id;
    }
}
