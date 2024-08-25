package com.shabab.UniversityManagementSystem.academy.model;

import com.shabab.UniversityManagementSystem.admin.model.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@DiscriminatorValue("Teacher")
public class Teacher extends User {

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("TEACHER"));
    }

    @Override
    public String getPassword() {
        return "admin_password"; // Replace with actual password handling
    }

    @Override
    public String getUsername() {
        return "admin_username"; // Replace with actual username handling
    }

}
