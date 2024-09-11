package com.shabab.UniversityManagementSystem.security.model;

import com.shabab.UniversityManagementSystem.academy.model.Student;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private User user;

    private Student student;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public CustomUserDetails(Student student) {
        this.student = student;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user != null && student == null) {
            return List.of(new SimpleGrantedAuthority(user.getRole().name()));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
        }

    }

    @Override
    public String getPassword() {
        if (user != null && student == null) {
            return user.getToken().getPassword();
        } else {
            return student.getPassword();
        }
    }

    @Override
    public String getUsername() {
        if (user != null && student == null) {
            return user.getToken().getUsername();
        } else {
            return student.getEmail();
        }
    }
}
