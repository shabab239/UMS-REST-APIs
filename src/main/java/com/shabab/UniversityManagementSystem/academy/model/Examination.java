package com.shabab.UniversityManagementSystem.academy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 31/08/2024
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "acd_exams")
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Exam name is required")
    @Size(max = 100, message = "Max 100 Characters")
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull(message = "Exam date is required")
    @Column(name = "exam_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull(message = "Semester is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    @JsonIgnore
    @OneToMany(mappedBy = "examination", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mark> marks;

    public Examination(Long id) {
        this.id = id;
    }
}
