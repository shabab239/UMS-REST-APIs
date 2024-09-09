package com.shabab.UniversityManagementSystem.admin.service;

import com.shabab.UniversityManagementSystem.academy.model.*;
import com.shabab.UniversityManagementSystem.academy.repository.*;
import com.shabab.UniversityManagementSystem.security.model.User;
import com.shabab.UniversityManagementSystem.security.repository.UserRepository;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 02/09/2024
 */

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private CourseRepository courseRepository;

    public ApiResponse dashboard() {
        ApiResponse response = new ApiResponse();
        try {
            List<User> users = userRepository.findAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            List<Student> students = studentRepository.getAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            List<Faculty> faculties = facultyRepository.findAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            List<Program> programs = programRepository.findAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            List<Semester> semesters = semesterRepository.findAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            List<Course> courses = courseRepository.getAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            Map<String, Object> dashboardData = new HashMap<>();
            dashboardData.put("users", users);
            dashboardData.put("students", students);
            dashboardData.put("faculties", faculties);
            dashboardData.put("programs", programs);
            dashboardData.put("semesters", semesters);
            dashboardData.put("courses", courses);

            response.setData("dashboardData", dashboardData);
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

}