package com.shabab.UniversityManagementSystem.academy.service;

import com.shabab.UniversityManagementSystem.academy.model.Department;
import com.shabab.UniversityManagementSystem.academy.model.Faculty;
import com.shabab.UniversityManagementSystem.academy.repository.DepartmentRepository;
import com.shabab.UniversityManagementSystem.academy.repository.FacultyRepository;
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
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Department> departments = departmentRepository.findAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(null);
            if (departments == null) {
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
            Faculty faculty = facultyRepository.findById(
                    department.getFaculty().getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(null);
            if (faculty == null) {
                return response.returnError("Wrong Faculty");
            }
            department.setUniversityId(AuthUtil.getCurrentUniversityId());
            departmentRepository.save(department);
            response.setData("department", department);
            response.success("Saved Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse update(Department department) {
        ApiResponse response = new ApiResponse();
        try {
            Department dbDepartment =
                    departmentRepository.findById(
                            department.getId(), AuthUtil.getCurrentUniversityId()
                    ).orElse(null);
            if (dbDepartment == null) {
                return response.returnError("Department not found");
            }
            department.setUniversityId(AuthUtil.getCurrentUniversityId());
            departmentRepository.save(department);
            response.setData("department", department);
            response.success("Updated Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Department department = departmentRepository.findById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(null);
            if (department == null) {
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
            Department department = departmentRepository.findById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(null);
            if (department == null) {
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
