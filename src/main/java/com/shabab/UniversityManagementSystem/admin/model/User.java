package com.shabab.UniversityManagementSystem.admin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "ad_users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
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

    @NotBlank(message = "Gender is required")
    private Character gender;

    @Size(max = 500, message = "Maximum 500 Characters")
    private String address;

    private byte[] avatar;

    @NotBlank(message = "Status is required")
    private Integer status;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String bloodGroup;

    @Temporal(TemporalType.DATE)
    private Date joiningDate;

    @JoinColumn(name = "institute_id")
    private Long instituteId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
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
