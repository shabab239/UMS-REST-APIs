package com.shabab.UniversityManagementSystem.academy.service;

import com.shabab.UniversityManagementSystem.academy.model.Course;
import com.shabab.UniversityManagementSystem.academy.model.Semester;
import com.shabab.UniversityManagementSystem.academy.repository.CourseRepository;
import com.shabab.UniversityManagementSystem.academy.repository.SemesterRepository;
import com.shabab.UniversityManagementSystem.security.model.User;
import com.shabab.UniversityManagementSystem.security.repository.UserRepository;
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
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private UserRepository userRepository;


    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Course> courses = courseRepository.getAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());
            if (courses.isEmpty()) {
                return response.returnError("No course found");
            }
            response.setData("courses", courses);
            response.success("Successfully retrieved all courses");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse save(Course course) {
        ApiResponse response = new ApiResponse();
        try {
            Semester semester = semesterRepository.findById(
                    course.getSemester().getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Semester());
            if (semester.getId() == null) {
                return response.returnError("Wrong Semester");
            }
            course = courseRepository.save(course);
            response.setData("course", course);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse update(Course course) {
        ApiResponse response = new ApiResponse();
        try {
            Course dbCourse = courseRepository.getById(
                    course.getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Course());
            if (dbCourse.getId() == null) {
                return response.returnError("Course not found");
            }

            dbCourse.setName(course.getName());
            dbCourse.setCode(course.getCode());
            dbCourse.setCredit(course.getCredit());
            dbCourse.setDescription(course.getDescription());
            dbCourse.setSemester(course.getSemester());

            if (course.getTeachers() != null) {
                List<User> teachers = new ArrayList<>();
                for (User teacher : course.getTeachers()) {
                    User existingTeacher = userRepository.findById(teacher.getId())
                            .orElseThrow(() -> new RuntimeException("Teacher with ID " + teacher.getId() + " not found"));
                    teachers.add(existingTeacher);
                }
                dbCourse.setTeachers(teachers);
            }

            dbCourse = courseRepository.save(dbCourse);
            response.setData("course", dbCourse);
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
            Course course = courseRepository.getById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(new Course());
            if (course.getId() == null) {
                return response.returnError("Course not found");
            }
            response.setData("course", course);
            response.success("Successfully retrieved course");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Course course = courseRepository.getById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(new Course());
            if (course.getId() == null) {
                return response.returnError("Course not found");
            }
            courseRepository.deleteById(id);
            response.success("Deleted Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse getAllByExamination(Long examinationId) {
        ApiResponse response = new ApiResponse();
        try {
            List<Course> courses = courseRepository.getAllByExamination(
                    examinationId, AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());
            if (courses.isEmpty()) {
                return response.returnError("No course found for this examination");
            }
            response.setData("courses", courses);
            response.success("Successfully retrieved all courses for this examination");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

}
