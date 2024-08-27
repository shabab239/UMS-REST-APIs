package com.shabab.UniversityManagementSystem.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 25/08/2024
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private String message;
    private Map<String, Object> data;
    private Map<String, String> errors;
    private boolean isSuccessful = false;

    public ApiResponse(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public ApiResponse(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    public void setData(String key, Object value) {
        Map<String, Object> data = new HashMap<>();
        data.put(key, value);
        this.data = data;
    }

    public void success(String message) {
        this.isSuccessful = true;
        this.message = message;
    }

    public void error(String message) {
        this.isSuccessful = false;
        this.message = message;
    }

    public ApiResponse returnError(String message) {
        this.isSuccessful = false;
        this.message = message;
        return this;
    }

    public ApiResponse returnError(Exception e) {
        this.isSuccessful = false;
        this.message = e.getMessage();
        return this;
    }

    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{\"error\": \"Error converting ApiResponse to JSON.\"}";
        }
    }
}

