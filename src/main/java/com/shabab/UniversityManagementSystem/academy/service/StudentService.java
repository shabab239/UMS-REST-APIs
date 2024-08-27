package com.shabab.UniversityManagementSystem.academy.service;


import com.shabab.UniversityManagementSystem.academy.model.Department;
import com.shabab.UniversityManagementSystem.academy.model.Faculty;
import com.shabab.UniversityManagementSystem.academy.model.Student;
import com.shabab.UniversityManagementSystem.academy.repository.DepartmentRepository;
import com.shabab.UniversityManagementSystem.academy.repository.StudentRepository;
import com.shabab.UniversityManagementSystem.security.jwt.JwtUtil;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Student> students = studentRepository.findByUniversity(jwtUtil.getUniversity());
            response.setData("students", students);
            response.success("Students retrieved successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse save(Student student) {
        ApiResponse response = new ApiResponse();
        try {
            student.setUniversity(jwtUtil.getUniversity());
            Student savedStudent = studentRepository.save(student);
            response.setData("student", savedStudent);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse update(Student student) {
        ApiResponse response = new ApiResponse();
        try {
            Student dbStudent = studentRepository.findByIdAndUniversity(student.getId(), jwtUtil.getUniversity());
            if (dbStudent == null || dbStudent.getId() == null) {
                return response.returnError("Student not found");
            }
            student.setUniversity(jwtUtil.getUniversity());
            Student updatedStudent = studentRepository.save(student);
            response.setData("student", updatedStudent);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    
    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Student student = studentRepository.findByIdAndUniversity(id, jwtUtil.getUniversity());
            if (student == null || student.getId() == null) {
                return response.returnError("Student not found");
            }
            response.setData("student", student);
            response.success("Successfully retrieved student");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Student student = studentRepository.findByIdAndUniversity(id, jwtUtil.getUniversity());
            if (student == null || student.getId() == null) {
                return response.returnError("Student not found");
            }
            studentRepository.delete(student);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

}
