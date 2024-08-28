package com.shabab.UniversityManagementSystem.admin.service;

import com.shabab.UniversityManagementSystem.admin.model.University;
import com.shabab.UniversityManagementSystem.admin.repository.UniversityRepository;
import com.shabab.UniversityManagementSystem.security.repository.AuthRepository;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 25/08/2024
 */

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private AuthRepository authRepository;

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<University> universities = universityRepository.findAll();
            if (universities.isEmpty()) {
                return response.returnError("No university found");
            }
            response.setData("universities", universities);
            response.success("Successfully retrieved all universities");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse save(University university) {
        ApiResponse response = new ApiResponse();
        try {
            University dbUniversity = universityRepository.save(university);
            response.setData("university", dbUniversity);
            response.success("Saved Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse update(University university) {
        ApiResponse response = new ApiResponse();
        try {
            University dbUniversity = universityRepository.findById(
                    university.getId()
            ).orElseThrow();
            if (dbUniversity.getId() == null) {
                return response.returnError("University not found");
            }
            University updatedUniversity = universityRepository.save(university);
            response.setData("university", updatedUniversity);
            response.success("Updated Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            University university = universityRepository.findById(
                    id
            ).orElseThrow();
            if (university.getId() == null) {
                return response.returnError("University not found");
            }
            response.setData("university", university);
            response.success("Successfully retrieved university");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            University university = universityRepository.findById(
                    id
            ).orElseThrow();
            if (university.getId() == null) {
                return response.returnError("University not found");
            }
            universityRepository.deleteById(id);
            response.success("Deleted Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

}