package com.shabab.UniversityManagementSystem.admin.service;

import com.shabab.UniversityManagementSystem.academy.model.Course;
import com.shabab.UniversityManagementSystem.academy.model.Department;
import com.shabab.UniversityManagementSystem.academy.model.Faculty;
import com.shabab.UniversityManagementSystem.academy.repository.DepartmentRepository;
import com.shabab.UniversityManagementSystem.academy.repository.FacultyRepository;
import com.shabab.UniversityManagementSystem.admin.model.User;
import com.shabab.UniversityManagementSystem.admin.repository.UserRepository;
import com.shabab.UniversityManagementSystem.security.model.Token;
import com.shabab.UniversityManagementSystem.security.repository.AuthRepository;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Value("${avatar.user.dir}")
    private String userAvatarDir;

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<User> users = userRepository.findByUniversity(
                    AuthUtil.getCurrentUniversity()
            ).orElse(new ArrayList<>());
            if (users.isEmpty()) {
                return response.returnError("No users found");
            }
            response.setData("users", users);
            response.success("Successfully retrieved all users");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

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

            user.setUniversity(AuthUtil.getCurrentUniversity());
            User dbUser = userRepository.save(user);
            response.setData("user", dbUser);
            response.success("Saved Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse update(User user, MultipartFile avatar) {
        ApiResponse response = new ApiResponse();
        try {
            User dbUser = userRepository.findByIdAndUniversity(
                    user.getId(), AuthUtil.getCurrentUniversity()
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

            user.setUniversity(AuthUtil.getCurrentUniversity());
            User updatedUser = userRepository.save(user);
            response.setData("user", updatedUser);
            response.success("Updated Successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            User user = userRepository.findByIdAndUniversity(
                    id, AuthUtil.getCurrentUniversity()
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
            User user = userRepository.findByIdAndUniversity(
                    id, AuthUtil.getCurrentUniversity()
            ).orElse(new User());
            if (user.getId() == null) {
                return response.returnError("User not found");
            }

            for (Course course : user.getCourses()) {
                course.getTeachers().remove(user);
            }

            Department department = departmentRepository.findByHead_Id(
                    user.getId()
            ).orElse(new Department());
            if (department.getId() != null) {
                department.setHead(null);
                departmentRepository.save(department);
            }

            Faculty faculty = facultyRepository.findByDean_Id(
                    user.getId()
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Token token = authRepository.findByUsername(username);

        if (token == null || token.getUser() == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        User user = token.getUser();
        user.setUsername(token.getUsername());
        user.setPassword(token.getPassword());
        return token.getUser();
    }

}