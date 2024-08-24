package com.shabab.UniversityManagementSystem.admin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ad_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Cell number is required")
    @Pattern(regexp = "^\\d{11}$", message = "Cell number must be 11 digits")
    private String cell;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Gender is required")
    private String gender;

    private String address;

    private byte[] avatar;

    @NotBlank(message = "Status is required")
    private String status;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String bloodGroup;

    @Temporal(TemporalType.DATE)
    private Date joiningDate;

    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute institute;

}
