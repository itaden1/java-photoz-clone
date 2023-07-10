package com.ethanshearer.photoz.clone.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class APIError {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    private String details;
    private List<APISubError> errors;

    private APIError() {
        this.timestamp = LocalDateTime.now();
    }
    public APIError(HttpStatus status) {
        this();
        this.status = status;
    }
    APIError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.details = ex.getLocalizedMessage();
    }
    APIError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.details = ex.getLocalizedMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }
}
