package com.shabab.UniversityManagementSystem.admin.service;

import com.shabab.UniversityManagementSystem.academy.model.*;
import com.shabab.UniversityManagementSystem.academy.repository.*;
import com.shabab.UniversityManagementSystem.accounting.Account;
import com.shabab.UniversityManagementSystem.accounting.AccountRepository;
import com.shabab.UniversityManagementSystem.admin.model.User;
import com.shabab.UniversityManagementSystem.admin.repository.UserRepository;
import com.shabab.UniversityManagementSystem.security.model.Token;
import com.shabab.UniversityManagementSystem.security.repository.AuthRepository;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.AuthUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
            List<User> users = userRepository.findAllByUniversity(
                    AuthUtil.getCurrentUniversity()
            ).orElse(new ArrayList<>());

            List<Student> students = studentRepository.getAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            List<Faculty> faculties = facultyRepository.findAllByUniversity(
                    AuthUtil.getCurrentUniversity()
            ).orElse(new ArrayList<>());

            List<Program> programs = programRepository.findAllByDepartment_Faculty_University(
                    AuthUtil.getCurrentUniversity()
            ).orElse(new ArrayList<>());

            List<Semester> semesters = semesterRepository.getAll(
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