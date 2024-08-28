package com.shabab.UniversityManagementSystem.academy.service;

import com.shabab.UniversityManagementSystem.academy.model.Department;
import com.shabab.UniversityManagementSystem.academy.model.Program;
import com.shabab.UniversityManagementSystem.academy.repository.DepartmentRepository;
import com.shabab.UniversityManagementSystem.academy.repository.ProgramRepository;
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
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Program> programs = programRepository.findAllByDepartment_Faculty_University(
                    AuthUtil.getCurrentUniversity()
            ).orElseThrow();
            if (programs.isEmpty()) {
                return response.returnError("No program found");
            }
            response.setData("programs", programs);
            response.success("Successfully retrieved all programs");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse save(Program program) {
        ApiResponse response = new ApiResponse();
        try {
            Department department = departmentRepository.findByIdAndFaculty_University(
                    program.getDepartment().getId(), AuthUtil.getCurrentUniversity()
            ).orElseThrow();
            if (department.getId() == null) {
                return response.returnError("Wrong Department");
            }
            program = programRepository.save(program);
            response.setData("program", program);
            response.success("Saved Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse update(Program program) {
        ApiResponse response = new ApiResponse();
        try {
            Program dbProgram = programRepository.findByIdAndDepartment_Faculty_University(
                    program.getId(), AuthUtil.getCurrentUniversity()
            ).orElseThrow();
            if (dbProgram.getId() == null) {
                return response.returnError("Program not found");
            }
            Program updatedProgram = programRepository.save(program);
            response.setData("program", updatedProgram);
            response.success("Updated Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Program program = programRepository.findByIdAndDepartment_Faculty_University(
                    id, AuthUtil.getCurrentUniversity()
            ).orElseThrow();
            if (program.getId() == null) {
                return response.returnError("Program not found");
            }
            response.setData("program", program);
            response.success("Successfully retrieved program");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Program program = programRepository.findByIdAndDepartment_Faculty_University(
                    id, AuthUtil.getCurrentUniversity()
            ).orElseThrow();
            if (program.getId() == null) {
                return response.returnError("Program not found");
            }
            programRepository.deleteById(id);
            response.success("Deleted Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

}
