package com.shabab.UniversityManagementSystem.academy.service;

import com.shabab.UniversityManagementSystem.academy.model.Department;
import com.shabab.UniversityManagementSystem.academy.model.Faculty;
import com.shabab.UniversityManagementSystem.academy.repository.DepartmentRepository;
import com.shabab.UniversityManagementSystem.academy.repository.FacultyRepository;
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
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Department> departments = departmentRepository.findByUniversity(jwtUtil.getUniversity());
            if (departments.isEmpty()) {
                return response.returnError("No department found");
            }
            response.setData("departments", departments);
            response.success("Successfully retrieved all departments");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse save(Department department) {
        ApiResponse response = new ApiResponse();
        try {
            department.setUniversity(jwtUtil.getUniversity());
            Department dbDepartment = departmentRepository.save(department);
            response.setData("department", dbDepartment);
            response.success("Saved Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse update(Department department) {
        ApiResponse response = new ApiResponse();
        try {
            Department dbDepartment = departmentRepository.findByIdAndUniversity(department.getId(), jwtUtil.getUniversity());
            if (dbDepartment == null || dbDepartment.getId() == null) {
                return response.returnError("Department not found");
            }

            department.setUniversity(jwtUtil.getUniversity());
            Department updatedDepartment = departmentRepository.save(department);
            response.setData("department", updatedDepartment);
            response.success("Updated Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Department department = departmentRepository.findByIdAndUniversity(id, jwtUtil.getUniversity());
            if (department == null || department.getId() == null) {
                return response.returnError("Department not found");
            }
            response.setData("department", department);
            response.success("Successfully retrieved department");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Department department = departmentRepository.findByIdAndUniversity(id, jwtUtil.getUniversity());
            if (department == null || department.getId() == null) {
                return response.returnError("Department not found");
            }
            departmentRepository.deleteById(id);
            response.success("Deleted Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

}
