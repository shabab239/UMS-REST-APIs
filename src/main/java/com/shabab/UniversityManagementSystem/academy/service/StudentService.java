package com.shabab.UniversityManagementSystem.academy.service;


import com.shabab.UniversityManagementSystem.academy.model.Department;
import com.shabab.UniversityManagementSystem.academy.model.Faculty;
import com.shabab.UniversityManagementSystem.academy.model.Student;
import com.shabab.UniversityManagementSystem.academy.repository.DepartmentRepository;
import com.shabab.UniversityManagementSystem.academy.repository.StudentRepository;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.AuthUtil;
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
    private DepartmentRepository departmentRepository;


    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Student> students = studentRepository.getAll(AuthUtil.getCurrentUniversityId()).orElseThrow();
            if (students.isEmpty()) {
                return response.returnError("No student found");
            }
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
            Department department = departmentRepository
                    .getById(student.getDepartment().getId(), AuthUtil.getCurrentUniversityId())
                    .orElseThrow();

            if (department.getId() == null) {
                return response.returnError("Wrong Department");
            }

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
            Student dbStudent = studentRepository.getById(student.getId(), AuthUtil.getCurrentUniversityId()).orElseThrow();

            if (dbStudent.getId() == null) {
                return response.returnError("Student not found");
            }

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
            Student student = studentRepository.getById(id, AuthUtil.getCurrentUniversityId()).orElseThrow();
            if (student.getId() == null) {
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
            Student student = studentRepository.getById(id, AuthUtil.getCurrentUniversityId()).orElseThrow();
            if (student.getId() == null) {
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
