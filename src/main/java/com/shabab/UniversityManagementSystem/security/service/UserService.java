package com.shabab.UniversityManagementSystem.security.service;

import com.shabab.UniversityManagementSystem.academy.model.Course;
import com.shabab.UniversityManagementSystem.academy.model.Department;
import com.shabab.UniversityManagementSystem.academy.model.Faculty;
import com.shabab.UniversityManagementSystem.academy.repository.CourseRepository;
import com.shabab.UniversityManagementSystem.academy.repository.DepartmentRepository;
import com.shabab.UniversityManagementSystem.academy.repository.FacultyRepository;
import com.shabab.UniversityManagementSystem.accounting.Account;
import com.shabab.UniversityManagementSystem.accounting.AccountRepository;
import com.shabab.UniversityManagementSystem.security.model.User;
import com.shabab.UniversityManagementSystem.security.repository.UserRepository;
import com.shabab.UniversityManagementSystem.security.model.Token;
import com.shabab.UniversityManagementSystem.security.repository.TokenRepository;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.AuthUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
 * Created on: 25/08/2024
 */

@Service
public class UserService {

    @Value("${avatar.user.dir}")
    private String userAvatarDir;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CourseRepository courseRepository;

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<User> users = userRepository.findAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());
            if (users.isEmpty()) {
                return response.returnError("No user found");
            }
            response.setData("users", users);
            response.success("Successfully retrieved all users");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public ApiResponse save(User user, MultipartFile avatar) {
        ApiResponse response = new ApiResponse();
        try {
            if (avatar != null && !avatar.isEmpty()) {
                Path directoryPath = Paths.get(userAvatarDir);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                }

                String originalFilename = avatar.getOriginalFilename();
                String fileExtension = originalFilename != null ?
                        originalFilename.substring(originalFilename.lastIndexOf('.')) : "";
                String randomFileName = UUID.randomUUID() + fileExtension;
                Path filePath = directoryPath.resolve(randomFileName);

                Files.copy(avatar.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                user.setAvatar("avatar/user/" + randomFileName);
            }


            Account account = new Account();
            account.setName(user.getName() + " Cash A/C");
            account.setBalance(0.0);
            account = accountRepository.save(account);

            user.setAccount(account);
            user.setUniversity(AuthUtil.getCurrentUniversity());
            userRepository.save(user);

            response.setData("user", user);
            response.success("Saved Successfully. Account created");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse update(User user, MultipartFile avatar) {
        ApiResponse response = new ApiResponse();
        try {
            User dbUser = userRepository.findById(
                    user.getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new User());
            if (dbUser.getId() == null) {
                return response.returnError("User not found");
            }

            if (avatar != null && !avatar.isEmpty()) {
                Path directoryPath = Paths.get(userAvatarDir);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                }

                String originalFilename = avatar.getOriginalFilename();
                String fileExtension = originalFilename != null ?
                        originalFilename.substring(originalFilename.lastIndexOf('.')) : "";
                String randomFileName = UUID.randomUUID().toString() + fileExtension;
                Path filePath = directoryPath.resolve(randomFileName);

                Files.copy(avatar.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                user.setAvatar("avatar/user/" + randomFileName);
            }

            user.setAccount(dbUser.getAccount());
            user.setUniversity(AuthUtil.getCurrentUniversity());
            userRepository.save(user);
            response.setData("user", user);
            response.success("Updated Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            User user = userRepository.findById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(new User());
            if (user.getId() == null) {
                return response.returnError("User not found");
            }
            response.setData("user", user);
            response.success("Successfully retrieved user");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse deleteById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            User user = userRepository.findById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(new User());
            if (user.getId() == null) {
                return response.returnError("User not found");
            }

            List<Course> courses = courseRepository.findByTeacherId(
                    user.getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>()); //TODO: Check if this is correct

            for (Course course : courses) {
                course.getTeachers().remove(user);
            }

            Department department = departmentRepository.findByHeadId(
                    user.getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Department());
            if (department.getId() != null) {
                department.setHead(null);
                departmentRepository.save(department);
            }

            Faculty faculty = facultyRepository.findByDeanId(
                    user.getId(), AuthUtil.getCurrentUniversityId()
            ).orElse(new Faculty());
            if (faculty.getId() != null) {
                faculty.setDean(null);
                facultyRepository.save(faculty);
            }

            userRepository.deleteById(id);
            response.success("Deleted Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public ApiResponse saveToken(Token token) {
        ApiResponse response = new ApiResponse();
        try {
            if (token.getUser() == null || token.getUser().getId() == null) {
                return response.returnError("User is required");
            }
            token.setPassword(
                    new BCryptPasswordEncoder(12).encode(token.getPassword())
            );
            tokenRepository.save(token);

            response.success("Saved Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

}