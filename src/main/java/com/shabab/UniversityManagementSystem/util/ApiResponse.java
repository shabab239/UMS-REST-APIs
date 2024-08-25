package com.shabab.UniversityManagementSystem.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private HttpStatus status;
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
}

