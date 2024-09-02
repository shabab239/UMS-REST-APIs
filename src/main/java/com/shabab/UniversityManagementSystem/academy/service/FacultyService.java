package com.shabab.UniversityManagementSystem.academy.service;

import com.shabab.UniversityManagementSystem.academy.model.Faculty;
import com.shabab.UniversityManagementSystem.academy.repository.FacultyRepository;
import com.shabab.UniversityManagementSystem.admin.model.University;
import com.shabab.UniversityManagementSystem.admin.model.User;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            List<Faculty> faculties = facultyRepository.findAllByUniversity(
                    AuthUtil.getCurrentUniversity()
            ).orElse(new ArrayList<>());
            if (faculties.isEmpty()) {
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
            Faculty dbFaculty = facultyRepository.findByIdAndUniversity(
                    faculty.getId(), AuthUtil.getCurrentUniversity()
            ).orElse(new Faculty());
            if (dbFaculty.getId() == null) {
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
            Faculty faculty = facultyRepository.findByIdAndUniversity(
                    id, AuthUtil.getCurrentUniversity()
            ).orElse(new Faculty());
            if (faculty.getId() == null) {
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
            Faculty faculty = facultyRepository.findByIdAndUniversity(
                    id, AuthUtil.getCurrentUniversity()
            ).orElse(new Faculty());
            if (faculty.getId() == null) {
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
