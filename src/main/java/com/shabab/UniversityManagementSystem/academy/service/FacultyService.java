package com.shabab.UniversityManagementSystem.academy.service;

import com.shabab.UniversityManagementSystem.academy.model.Faculty;
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
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Faculty> faculties = facultyRepository.findAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(null);
            if (faculties == null) {
                return response.returnError("No faculty found");
            }
            response.setData("faculties", faculties);
            response.success("Successfully retrieved all faculties");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse save(Faculty faculty) {
        ApiResponse response = new ApiResponse();
        try {
            faculty.setUniversity(AuthUtil.getCurrentUniversity());
            Faculty dbFaculty = facultyRepository.save(faculty);
            response.setData("faculty", dbFaculty);
            response.success("Saved Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse update(Faculty faculty) {
        ApiResponse response = new ApiResponse();
        try {
            Faculty dbFaculty = facultyRepository.findById(
                    faculty.getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(null);
            if (dbFaculty == null) {
                return response.returnError("Faculty not found");
            }
            faculty.setUniversity(AuthUtil.getCurrentUniversity());
            Faculty updatedFaculty = facultyRepository.save(faculty);
            response.setData("faculty", updatedFaculty);
            response.success("Updated Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Faculty faculty = facultyRepository.findById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(null);
            if (faculty == null) {
                return response.returnError("Faculty not found");
            }
            response.setData("faculty", faculty);
            response.success("Successfully retrieved faculty");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Faculty faculty = facultyRepository.findById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(null);
            if (faculty == null) {
                return response.returnError("Faculty not found");
            }
            facultyRepository.deleteById(id);
            response.success("Deleted Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

}
