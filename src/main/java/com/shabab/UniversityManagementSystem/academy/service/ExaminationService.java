package com.shabab.UniversityManagementSystem.academy.service;

import com.shabab.UniversityManagementSystem.academy.model.*;
import com.shabab.UniversityManagementSystem.academy.repository.*;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.AuthUtil;
import jakarta.transaction.Transactional;
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

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MarkRepository markRepository;


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

    @Transactional(rollbackOn = Exception.class)
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

            List<Student> students = studentRepository.getAllByExamination(
                    examination.getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            List<Course> courses = courseRepository.getAllByExamination(
                    examination.getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            for (Course course : courses) {
                for (Student student : students) {
                    Mark mark = new Mark();
                    mark.setExamination(examination);
                    mark.setStudent(student);
                    mark.setCourse(course);
                    markRepository.save(mark);
                }
            }

            response.setData("examination", examination);
            response.success("Saved Successfully. Marks Initiated");
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

    public ApiResponse getAllMarksByExaminationAndCourse(Long examinationId, Long courseId) {
        ApiResponse response = new ApiResponse();
        try {
            List<Mark> marks = markRepository.getAllByExaminationAndCourse(
                    examinationId, courseId, AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());
            if (marks.isEmpty()) {
                return response.returnError("No mark found");
            }
            response.setData("marks", marks);
            response.success("Successfully retrieved all examinations");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse saveMarks(List<Mark> marks) {
        ApiResponse response = new ApiResponse();
        try {
            //Validate later

            for (Mark mark : marks) {
                boolean success = mark.processMark();
                if (!success) {
                    return response.returnError("Full mark exceeded 100.");
                }
            }

            List<Mark> savedMarks = markRepository.saveAll(marks);

            response.setData("marks", savedMarks);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

}
