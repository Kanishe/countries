package com.aka.countries.exception;

import com.aka.countries.controller.error.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.application.version}")
    private String apiVersion;

    @ExceptionHandler(NotFoundCountryByIdException.class)
    public ResponseEntity<ApiError> handleException(NotFoundCountryByIdException e, HttpServletRequest request) {
        log.error(request.getRequestURI(), e);
        return new ResponseEntity<>(
                new ApiError(
                        apiVersion,
                        HttpStatus.NOT_FOUND.toString(),
                        "Country not found",
                        request.getRequestURI(),
                        e.getMessage()
                ),
                HttpStatus.NOT_FOUND);
    }
}
