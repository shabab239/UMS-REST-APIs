package com.shabab.UniversityManagementSystem.admin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@Entity
@Table(name = "ad_users")
@Data
public class User implements UserDetails {

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

    @NotNull(message = "Gender is required")
    private Character gender;

    @Size(max = 500, message = "Maximum 500 Characters")
    private String address;

    private byte[] avatar;

    @NotNull(message = "Status is required")
    private Integer status;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String bloodGroup;

    @Temporal(TemporalType.DATE)
    private Date joiningDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "university_Id", nullable = false)
    private University university;

    @Transient
    String username;

    @Transient
    String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getIdString() {
        return String.valueOf(id);
    }

    public enum Role {
        ADMIN,
        TEACHER,
        STAFF
    }

    public static final Map<String, String> GENDERS = new HashMap<String, String>() {{
        put("M", "Male");
        put("F", "Female");
        put("O", "Other");
    }};

    public static final Map<String, String> STATUSES = new HashMap<String, String>() {{
        put("1", "Active");
        put("0", "Inactive");
        put("2", "Suspended");
    }};

    public static final Map<String, String> BLOOD_GROUPS = new HashMap<String, String>() {{
        put("A+", "A Positive");
        put("A-", "A Negative");
        put("B+", "B Positive");
        put("B-", "B Negative");
        put("AB+", "AB Positive");
        put("AB-", "AB Negative");
        put("O+", "O Positive");
        put("O-", "O Negative");
    }};
}
