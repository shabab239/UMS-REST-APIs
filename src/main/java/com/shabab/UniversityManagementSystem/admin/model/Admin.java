package com.shabab.UniversityManagementSystem.admin.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User {

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ADMIN"));
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
