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
import java.util.Optional;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private ExaminationRepository examinationRepository;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    public ApiResponse process(Long examinationId) {
        ApiResponse response = new ApiResponse();
        try {
            Examination examination = examinationRepository.getById(
                    examinationId, AuthUtil.getCurrentUniversityId()
            ).orElse(new Examination());
            if (examination.getId() == null) {
                return response.returnError("Examination not found");
            }

            List<Result> results = resultRepository.getAll(
                    examinationId, AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            if (results.isEmpty()) {
                return response.returnError("No result found to be processed");
            }

            for (Result result : results) {
                double totalMarks = result.getMarkMid() +
                        result.getMarkAttendance() +
                        result.getMarkWritten() +
                        result.getMarkSessional();

                String grade;
                String status;

                if (totalMarks >= 80) {
                    grade = "A+";
                    status = "Passed";
                } else if (totalMarks >= 75) {
                    grade = "A";
                    status = "Passed";
                } else if (totalMarks >= 70) {
                    grade = "A-";
                    status = "Passed";
                } else if (totalMarks >= 65) {
                    grade = "B+";
                    status = "Passed";
                } else if (totalMarks >= 60) {
                    grade = "B";
                    status = "Passed";
                } else if (totalMarks >= 55) {
                    grade = "B-";
                    status = "Passed";
                } else if (totalMarks >= 50) {
                    grade = "C";
                    status = "Passed";
                } else if (totalMarks >= 45) {
                    grade = "D";
                    status = "Passed";
                } else {
                    grade = "F";
                    status = "Failed";
                }

                result.setGrade(grade);
                result.setStatus(status);

                resultRepository.save(result);
            }

            response.success("Processed Successfully");
        } catch (Exception e) {
            return response.returnError(e.getMessage());
        }
        return response;
    }

    public ApiResponse getAll(Long examinationId) {
        ApiResponse response = new ApiResponse();
        try {
            List<Result> results = resultRepository.getAll(
                    examinationId, AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());
            if (results.isEmpty()) {
                return response.returnError("No result found for this examination");
            }
            response.setData("results", results);
            response.success("Successfully retrieved all results for this examination");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse save(Result result) {
        ApiResponse response = new ApiResponse();
        try {
            Examination examination = examinationRepository.getById(
                    result.getExamination().getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Examination());
            if (examination.getId() == null) {
                return response.returnError("Wrong Examination");
            }

            Semester semester = semesterRepository.getById(
                    result.getExamination().getSemester().getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Semester());
            if (semester.getId() == null) {
                return response.returnError("Wrong Semester");
            }

            Course course = courseRepository.getById(
                    result.getCourse().getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Course());
            if (course.getId() == null) {
                return response.returnError("Wrong Course");
            }

            Student student = studentRepository.getById(
                    result.getStudent().getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Student());
            if (student.getId() == null) {
                return response.returnError("Wrong Student");
            }

            Result existingResult = resultRepository.findByExaminationIdAndCourseIdAndStudentId(
                    result.getExamination().getId(),
                    result.getCourse().getId(),
                    result.getStudent().getId()
            ).orElse(new Result());

            if (existingResult.getId() != null) {
                existingResult.setMarkMid(result.getMarkMid());
                existingResult.setMarkAttendance(result.getMarkAttendance());
                existingResult.setMarkWritten(result.getMarkWritten());
                existingResult.setSessionalType(result.getSessionalType());
                existingResult.setMarkSessional(result.getMarkSessional());
                existingResult.setGrade(result.getGrade());
                existingResult.setStatus(result.getStatus());
                result = existingResult;
            }

            result = resultRepository.save(result);
            response.setData("result", result);
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    @Transactional(rollbackOn = Exception.class)
    public ApiResponse saveAll(List<Result> results) {
        ApiResponse response = new ApiResponse();
        try {
            for (Result result : results) {
                save(result);
            }
            response.success("Saved Successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse update(Result result) {
        ApiResponse response = new ApiResponse();
        try {
            Result dbResult = resultRepository.getById(
                    result.getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Result());
            if (dbResult.getId() == null) {
                return response.returnError("Result not found");
            }

            Examination examination = examinationRepository.getById(
                    result.getExamination().getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Examination());
            if (examination.getId() == null) {
                return response.returnError("Wrong Examination");
            }

            Semester semester = semesterRepository.getById(
                    result.getExamination().getSemester().getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Semester());
            if (semester.getId() == null) {
                return response.returnError("Wrong Semester");
            }

            Course course = courseRepository.getById(
                    result.getCourse().getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Course());
            if (course.getId() == null) {
                return response.returnError("Wrong Course");
            }

            result = resultRepository.save(result);
            response.setData("result", result);
            response.success("Updated Successfully");
        } catch (Exception e) {
            return response.returnError(e.getMessage());
        }
        return response;
    }

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Result result = resultRepository.getById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(new Result());
            if (result.getId() == null) {
                return response.returnError("Result not found");
            }
            response.setData("result", result);
            response.success("Successfully retrieved result");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Result result = resultRepository.getById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(new Result());
            if (result.getId() == null) {
                return response.returnError("Result not found");
            }
            resultRepository.deleteById(id);
            response.success("Deleted Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

}
