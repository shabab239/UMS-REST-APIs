package com.shabab.UniversityManagementSystem.academy.service;

import com.shabab.UniversityManagementSystem.academy.model.Hall;
import com.shabab.UniversityManagementSystem.academy.repository.HallRepository;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/09/2024
 */

@Service
public class HallService {

    @Autowired
    private HallRepository hallRepository;

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Hall> halls = hallRepository.findAllByUniversityId(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(null);
            if (halls == null) {
                return response.returnError("No hall found");
            }
            response.setData("halls", halls);
            response.success("Successfully retrieved all halls");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse save(Hall hall) {
        ApiResponse response = new ApiResponse();
        try {
            hall.setUniversityId(AuthUtil.getCurrentUniversityId());
            hallRepository.save(hall);
            response.setData("hall", hall);
            response.success("Saved Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse update(Hall hall) {
        ApiResponse response = new ApiResponse();
        try {
            Hall dbHall = hallRepository.findByIdAndUniversityId(
                    hall.getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(null);
            if (dbHall == null) {
                return response.returnError("Hall not found");
            }
            hall.setUniversityId(AuthUtil.getCurrentUniversityId());
            hallRepository.save(hall);
            response.setData("hall", hall);
            response.success("Updated Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Hall hall = hallRepository.findByIdAndUniversityId(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(null);
            if (hall == null) {
                return response.returnError("Hall not found");
            }
            response.setData("hall", hall);
            response.success("Successfully retrieved hall");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Hall hall = hallRepository.findByIdAndUniversityId(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(null);
            if (hall == null) {
                return response.returnError("Hall not found");
            }
            hallRepository.deleteById(id);
            response.success("Deleted Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

}