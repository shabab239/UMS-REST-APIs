package com.shabab.UniversityManagementSystem.util;

import com.shabab.UniversityManagementSystem.academy.model.University;
import com.shabab.UniversityManagementSystem.security.model.CustomUserDetails;
import com.shabab.UniversityManagementSystem.security.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 28/08/2024
 */

public class AuthUtil {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static User getCurrentUser() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getUser();
    }

    public static Long getCurrentUserId() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getUser().getId();
    }

    public static University getCurrentUniversity() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getUser().getUniversity();
    }

    public static Long getCurrentUniversityId() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getUser().getUniversity().getId();
    }

}
