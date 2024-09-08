package com.shabab.UniversityManagementSystem.academy.service;

import com.shabab.UniversityManagementSystem.academy.model.University;
import com.shabab.UniversityManagementSystem.academy.repository.UniversityRepository;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 25/08/2024
 */

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

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
            ).orElse(new University());
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
            ).orElse(new University());
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
            ).orElse(new University());
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