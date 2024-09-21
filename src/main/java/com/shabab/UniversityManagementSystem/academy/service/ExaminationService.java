package com.shabab.UniversityManagementSystem.academy.service;

import com.shabab.UniversityManagementSystem.academy.model.*;
import com.shabab.UniversityManagementSystem.academy.repository.*;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.AuthUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private ResultRepository resultRepository;


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
            Semester semester = semesterRepository.findById(
                    examination.getSemester().getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Semester());
            if (semester.getId() == null) {
                return response.returnError("Wrong Semester");
            }
            examination = examinationRepository.save(examination);

            List<Student> students = studentRepository.findAllByExamination(
                    examination.getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            List<Course> courses = courseRepository.findAllByExamination(
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
            List<Student> students = studentRepository.findAllByExamination(
                    examinationId, AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            List<Course> courses = courseRepository.findAllByExamination(
                    examinationId, AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            for (Course course : courses) {
                for (Student student : students) {
                    Mark existingMark = markRepository.getByStudentAndCourse(
                            student.getId(), course.getId(), AuthUtil.getCurrentUniversityId()
                    ).orElse(null);
                    if (existingMark == null) {
                        Mark mark = new Mark();
                        mark.setExamination(new Examination(examinationId));
                        mark.setStudent(student);
                        mark.setCourse(course);
                        markRepository.save(mark);
                    }
                }
            }

            List<Mark> marks = markRepository.getAllByExaminationAndCourse(
                    examinationId, courseId, AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());
            if (marks.isEmpty()) {
                return response.returnError("No mark found");
            }
            response.setData("marks", marks);
            response.success("Successfully retrieved all marks");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse saveMarks(List<Mark> marks) {
        ApiResponse response = new ApiResponse();
        try {
            //Validate later

            List<Mark> finalMarks = new ArrayList<>();
            boolean success;
            for (Mark mark : marks) {
                Mark existingMark = markRepository.findByExaminationIdAndCourseIdAndStudentId(
                        mark.getExamination().getId(), mark.getCourse().getId(), mark.getStudent().getId()
                ).orElse(null);
                if (existingMark != null) {
                    existingMark.setMarkMid(mark.getMarkMid());
                    existingMark.setMarkAttendance(mark.getMarkAttendance());
                    existingMark.setMarkWritten(mark.getMarkWritten());
                    existingMark.setMarkSessional(mark.getMarkSessional());
                    success = existingMark.processMark();
                    finalMarks.add(existingMark);
                } else {
                    success = mark.processMark();
                    finalMarks.add(mark);
                }
                if (!success) {
                    return response.returnError("Full mark exceeded 100.");
                }
            }

            List<Mark> savedMarks = markRepository.saveAll(finalMarks);

            response.setData("marks", savedMarks);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    // Add the following method to ExaminationService.java

    //write test for this method

    @Transactional(rollbackOn = Exception.class)
    public ApiResponse processExamination(Long examinationId) {
        ApiResponse response = new ApiResponse();
        try {
            Examination examination = examinationRepository.getById(
                    examinationId, AuthUtil.getCurrentUniversityId()
            ).orElse(new Examination());

            if (examination.getId() == null) {
                return response.returnError("Examination not found");
            }

            List<Mark> marks = markRepository.getAllByExamination(
                    examinationId, AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            if (marks.isEmpty()) {
                return response.returnError("No marks found for the examination");
            }

            for (Mark mark : marks) {
                boolean success = mark.processMark();
                if (!success) {
                    return response.returnError("Full mark exceeded 100 for student: " + mark.getStudent().getId());
                }
            }

            List<Mark> savedMarks = markRepository.saveAll(marks);

            Map<Student, List<Mark>> marksByStudent = new HashMap<>();

            for (Mark mark : savedMarks) {
                Student student = mark.getStudent();

                // Initialize the list if the student is not yet in the map
                marksByStudent.computeIfAbsent(student, k -> new ArrayList<>());

                // Add the mark to the student's list
                marksByStudent.get(student).add(mark);
            }


            for (Map.Entry<Student, List<Mark>> entry : marksByStudent.entrySet()) {
                Student student = entry.getKey();
                List<Mark> studentMarks = entry.getValue();

                Map<Course, List<Mark>> marksByCourse = studentMarks.stream()
                        .collect(Collectors.groupingBy(Mark::getCourse));

                double totalGradePoints = 0;
                double totalCredits = 0;

                for (Map.Entry<Course, List<Mark>> courseEntry : marksByCourse.entrySet()) {
                    Course course = courseEntry.getKey();
                    List<Mark> courseMarks = courseEntry.getValue();

                    double courseGradePoints = 0;
                    for (Mark mark : courseMarks) {
                        courseGradePoints += mark.getGpa();
                    }
                    double averageGradePoints = courseGradePoints / courseMarks.size();
                    totalGradePoints += averageGradePoints * course.getCredit();
                    totalCredits += course.getCredit();
                }

                double cgpa = totalGradePoints / totalCredits;
                cgpa = Double.parseDouble(String.format("%.2f", cgpa));

                Result result = resultRepository.findByExaminationAndStudent(examination, student)
                        .orElse(new Result());
                result.setExamination(examination);
                result.setStudent(student);
                result.setCgpa(cgpa);
                result.setGrade(getGrade(cgpa));
                result.setStatus(cgpa >= 2.0 ? "Passed" : "Failed");
                resultRepository.save(result);
            }

            response.success("Examination processed and results saved successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse getResult(Long studentId) {
        ApiResponse response = new ApiResponse();
        try {
            Student student = studentRepository.findById(
                    studentId, AuthUtil.getCurrentUniversityId()
            ).orElse(null);
            if (student == null) {
                return response.returnError("Student not found");
            }

            List<Result> results = resultRepository.findAllByStudent(student);
            if (results.isEmpty()) {
                return response.returnError("No results found for the student");
            }

            response.setData("results", results);
            response.success("Successfully retrieved all results for the student");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse getResultsByExamination(Long examinationId) {
        ApiResponse response = new ApiResponse();
        try {
            Examination examination = examinationRepository.getById(
                    examinationId, AuthUtil.getCurrentUniversityId()
            ).orElse(null);
            if (examination == null) {
                return response.returnError("Examination not found");
            }

            List<Result> results = resultRepository.findByExamination(
                    examination
            ).orElse(new ArrayList<>());
            if (results.isEmpty()) {
                return response.returnError("No results found. Process the examination first");
            }

            response.setData("results", results);
            response.success("Successfully retrieved all results for the examination");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    private String getGrade(double cgpa) {
        if (cgpa >= 3.75) {
            return "A";
        } else if (cgpa >= 3.5) {
            return "A-";
        } else if (cgpa >= 3.25) {
            return "B+";
        } else if (cgpa >= 3.0) {
            return "B";
        } else if (cgpa >= 2.75) {
            return "B-";
        } else if (cgpa >= 2.5) {
            return "C+";
        } else if (cgpa >= 2.25) {
            return "C";
        } else if (cgpa >= 2.0) {
            return "D";
        } else {
            return "F";
        }
    }

}
