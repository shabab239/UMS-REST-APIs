package com.shabab.UniversityManagementSystem.academy.model;

import com.shabab.UniversityManagementSystem.admin.model.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Teacher")
public class Teacher extends User {

}
