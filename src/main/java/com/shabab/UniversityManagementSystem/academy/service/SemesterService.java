package com.shabab.UniversityManagementSystem.academy.service;


import com.shabab.UniversityManagementSystem.academy.model.Fee;
import com.shabab.UniversityManagementSystem.academy.model.Program;
import com.shabab.UniversityManagementSystem.academy.model.Semester;
import com.shabab.UniversityManagementSystem.academy.repository.FeeRepository;
import com.shabab.UniversityManagementSystem.academy.repository.ProgramRepository;
import com.shabab.UniversityManagementSystem.academy.repository.SemesterRepository;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */
@Service
public class SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private FeeRepository feeRepository;

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Semester> semesters = semesterRepository.getAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());
            if (semesters.isEmpty()) {
                return response.returnError("No semesters found");
            }
            response.setData("semesters", semesters);
            response.success("Semesters retrieved successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse save(Semester semester) {
        ApiResponse response = new ApiResponse();
        try {
            Program program = programRepository.findByIdAndDepartment_Faculty_University(
                    semester.getProgram().getId(), AuthUtil.getCurrentUniversity()
            ).orElse(new Program());

            if (program.getId() == null) {
                return response.returnError("Invalid Program");
            }

            Semester savedSemester = semesterRepository.save(semester);
            response.setData("semester", savedSemester);
            response.success("Semester saved successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse update(Semester semester) {
        ApiResponse response = new ApiResponse();
        try {
            Semester dbSemester = semesterRepository.getById(
                    semester.getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Semester());

            if (dbSemester.getId() == null) {
                return response.returnError("Semester not found");
            }

            Semester updatedSemester = semesterRepository.save(semester);
            response.setData("semester", updatedSemester);
            response.success("Semester updated successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Semester semester = semesterRepository.getById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(new Semester());
            if (semester.getId() == null) {
                return response.returnError("Semester not found");
            }
            response.setData("semester", semester);
            response.success("Successfully retrieved semester");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Semester semester = semesterRepository.getById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(new Semester());
            if (semester.getId() == null) {
                return response.returnError("Semester not found");
            }
            semesterRepository.delete(semester);
            response.success("Semester deleted successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse saveFees(List<Fee> fees) {
        ApiResponse response = new ApiResponse();
        try {
            feeRepository.saveAll(fees);
            response.success("Saved successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }
}

