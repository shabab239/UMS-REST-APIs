package com.shabab.UniversityManagementSystem.academy.service;


import com.shabab.UniversityManagementSystem.academy.model.Fee;
import com.shabab.UniversityManagementSystem.academy.model.Semester;
import com.shabab.UniversityManagementSystem.academy.model.Student;
import com.shabab.UniversityManagementSystem.academy.repository.FeeRepository;
import com.shabab.UniversityManagementSystem.academy.repository.SemesterRepository;
import com.shabab.UniversityManagementSystem.academy.repository.StudentRepository;
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
public class FeeService {

    @Autowired
    private FeeRepository feeRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Fee> fees = feeRepository.getAll(AuthUtil.getCurrentUniversityId()).orElseThrow();
            if (fees.isEmpty()) {
                return response.returnError("No fees found");
            }
            response.setData("fees", fees);
            response.success("Fees retrieved successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse save(Fee fee) {
        ApiResponse response = new ApiResponse();
        try {
            Semester semester = semesterRepository.getById(
                    fee.getSemester().getId(), AuthUtil.getCurrentUniversityId()
            ).orElseThrow();

            if (semester.getId() == null) {
                return response.returnError("Invalid semester");
            }

            Fee savedFee = feeRepository.save(fee);
            response.setData("fee", savedFee);
            response.success("Fee saved successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse update(Fee fee) {
        ApiResponse response = new ApiResponse();
        try {
            Fee dbFee = feeRepository.getById(
                    fee.getId(), AuthUtil.getCurrentUniversityId()
            ).orElseThrow();

            if (dbFee.getId() == null) {
                return response.returnError("Fee not found");
            }

            Fee updatedFee = feeRepository.save(fee);
            response.setData("fee", updatedFee);
            response.success("Fee updated successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Fee fee = feeRepository.getById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElseThrow();
            if (fee.getId() == null) {
                return response.returnError("Fee not found");
            }
            response.setData("fee", fee);
            response.success("Successfully retrieved fee");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Fee fee = feeRepository.getById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElseThrow();
            if (fee.getId() == null) {
                return response.returnError("Fee not found");
            }
            feeRepository.delete(fee);
            response.success("Fee deleted successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }
}

