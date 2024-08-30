package com.shabab.UniversityManagementSystem.academy.service;


import com.shabab.UniversityManagementSystem.academy.model.Semester;
import com.shabab.UniversityManagementSystem.academy.model.Student;
import com.shabab.UniversityManagementSystem.academy.repository.SemesterRepository;
import com.shabab.UniversityManagementSystem.academy.repository.StudentRepository;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Value("${avatar.student.dir}")
    private String studentAvatarDir;

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Student> students = studentRepository.getAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());
            if (students.isEmpty()) {
                return response.returnError("No student found");
            }
            response.setData("students", students);
            response.success("Students retrieved successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse save(Student student, MultipartFile avatar) {
        ApiResponse response = new ApiResponse();
        try {
            Semester semester = semesterRepository.getById(
                    student.getSemester().getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Semester());

            if (semester.getId() == null) {
                return response.returnError("Wrong Semester");
            }

            if (avatar != null && !avatar.isEmpty()) {
                Path directoryPath = Paths.get(studentAvatarDir);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                }

                String originalFilename = avatar.getOriginalFilename();
                String fileExtension = originalFilename != null ?
                        originalFilename.substring(originalFilename.lastIndexOf('.')) : "";
                String randomFileName = UUID.randomUUID() + fileExtension;
                Path filePath = directoryPath.resolve(randomFileName);

                Files.copy(avatar.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                student.setAvatar("avatar/student/" + randomFileName);
            }

            Student savedStudent = studentRepository.save(student);
            response.setData("student", savedStudent);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }


    public ApiResponse update(Student student, MultipartFile avatar) {
        ApiResponse response = new ApiResponse();
        try {
            Student dbStudent = studentRepository.getById(
                    student.getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Student());

            if (dbStudent.getId() == null) {
                return response.returnError("Student not found");
            }

            if (avatar != null && !avatar.isEmpty()) {
                Path directoryPath = Paths.get(studentAvatarDir);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                }

                String originalFilename = avatar.getOriginalFilename();
                String fileExtension = originalFilename != null ?
                        originalFilename.substring(originalFilename.lastIndexOf('.')) : "";
                String randomFileName = UUID.randomUUID() + fileExtension;
                Path filePath = directoryPath.resolve(randomFileName);

                Files.copy(avatar.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                student.setAvatar("avatar/student/" + randomFileName);
            }

            Student updatedStudent = studentRepository.save(student);
            response.setData("student", updatedStudent);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }


    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Student student = studentRepository.getById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(new Student());
            if (student.getId() == null) {
                return response.returnError("Student not found");
            }
            response.setData("student", student);
            response.success("Successfully retrieved student");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Student student = studentRepository.getById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(new Student());
            if (student.getId() == null) {
                return response.returnError("Student not found");
            }
            studentRepository.delete(student);
            response.success("Deleted Successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

}
