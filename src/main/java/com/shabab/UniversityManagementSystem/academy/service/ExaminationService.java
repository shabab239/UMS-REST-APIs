package com.shabab.UniversityManagementSystem.academy.service;

import com.shabab.UniversityManagementSystem.academy.model.Examination;
import com.shabab.UniversityManagementSystem.academy.model.Examination;
import com.shabab.UniversityManagementSystem.academy.model.Semester;
import com.shabab.UniversityManagementSystem.academy.repository.ExaminationRepository;
import com.shabab.UniversityManagementSystem.academy.repository.ExaminationRepository;
import com.shabab.UniversityManagementSystem.academy.repository.SemesterRepository;
import com.shabab.UniversityManagementSystem.admin.model.User;
import com.shabab.UniversityManagementSystem.admin.repository.UserRepository;
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
public class ExaminationService {

    @Autowired
    private ExaminationRepository examinationRepository;

    @Autowired
    private SemesterRepository semesterRepository;


    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Examination> examinations = examinationRepository.getAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());
            if (examinations.isEmpty()) {
                return response.returnError("No examination found");
            }
            response.setData("examinations", examinations);
            response.success("Successfully retrieved all examinations");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse save(Examination examination) {
        ApiResponse response = new ApiResponse();
        try {
            Semester semester = semesterRepository.getById(
                    examination.getSemester().getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Semester());
            if (semester.getId() == null) {
                return response.returnError("Wrong Semester");
            }
            examination = examinationRepository.save(examination);
            response.setData("examination", examination);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse update(Examination examination) {
        ApiResponse response = new ApiResponse();
        try {
            Examination dbExamination = examinationRepository.getById(
                    examination.getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Examination());
            if (dbExamination.getId() == null) {
                return response.returnError("Examination not found");
            }

            examination = examinationRepository.save(examination);
            response.setData("examination", examination);
            response.success("Updated Successfully");
            return response;
        } catch (Exception e) {
            response.returnError(e.getMessage());
            return response;
        }
    }


    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Examination examination = examinationRepository.getById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(new Examination());
            if (examination.getId() == null) {
                return response.returnError("Examination not found");
            }
            response.setData("examination", examination);
            response.success("Successfully retrieved examination");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Examination examination = examinationRepository.getById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(new Examination());
            if (examination.getId() == null) {
                return response.returnError("Examination not found");
            }
            examinationRepository.deleteById(id);
            response.success("Deleted Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

}
