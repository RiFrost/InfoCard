package com.webtech.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiException {

    private final String message;
    private int httpStatus;
    private final String path;
    private Map<String, String> validationErrors;
    private ZonedDateTime timestamp;

    public ApiException(String message,
                        int httpStatus,
                        String path) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.path = path;
        timestamp = ZonedDateTime.now(ZoneId.of("Z"));
    }

    public String getMessage() {
        return message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
